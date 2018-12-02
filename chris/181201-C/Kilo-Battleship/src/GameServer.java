/*
  Runs on it's own thread on the server side to
  manage top level game logic and Handler threads.

  SRP: Constant(Gen player1 thread, wait for player2, gen player2 thread)
       Loop(Checking if game is over and stuff)

  -Chris
*/

import java.util.concurrent.ExecutorService;

public class GameServer implements Runnable {
  private static int gameNumTracker;
  private int gameNumber;

  private ExecutorService threadManager;
  private Connection player1Connection = null;
  private Connection player2Connection = null;

  GameServer(ExecutorService _threadManager) {
    ++gameNumTracker;
    gameNumber = gameNumTracker;

    threadManager = _threadManager;
  }

  static int getGameNumTracker() {
    return gameNumTracker;
  }

  private int getGameNumber() {
    return gameNumber;
  }

  /**
   * setPlayer1()
   * Sets player1Connection
   * @param _player1Connection From containing ConnectionServer
   */
  synchronized void setPlayer1(Connection _player1Connection) {
    player1Connection = _player1Connection;
  }

  /**
   * setPlayer2()
   * Sets player2Connection
   * @param _player2Connection From containing ConnectionServer
   * */
  synchronized void setPlayer2(Connection _player2Connection) {
    player2Connection = _player2Connection;
  }

  /**
   * isGameFull()
   * @return boolean Returns if there are two connections or not
   */
  private synchronized boolean isGameFull() {
    return player1Connection != null && player2Connection != null;
  }

  /**
   * run()
   * Override Runnable interface
   * GameServer thread
   */
  @Override
  public void run() {
    ServerHandler player1Handler;
    ServerHandler player2Handler;
    int lastSecond = ConnectionServer.getSeconds();
    int printCycle;

    player1Handler = new ServerHandler(player1Connection, threadManager);

    threadManager.execute(player1Handler);

    while(!isGameFull()) {
      try {
        printCycle = ConnectionServer.getSeconds() ;

        if ((printCycle != lastSecond) && (printCycle == 0 || (printCycle) % 5 == 0)) {
          System.out.println(ConnectionServer.getTimeStamp() + "GameServer #" + gameNumber +
              " is running. Awaiting Connection #2");
          lastSecond = printCycle;
        }

        Thread.sleep(500);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    player2Handler = new ServerHandler(player2Connection, threadManager);

    threadManager.execute(player2Handler);

    player1Handler.initializeBoard();
    player2Handler.initializeBoard();

    player1Handler.getCurrentBoard().setOpponentGameBoard(player2Handler.getCurrentBoard().getPlayerGameBoard());
    player2Handler.getCurrentBoard().setOpponentGameBoard(player1Handler.getCurrentBoard().getPlayerGameBoard());

    player1Handler.startGame();
    player2Handler.startGame();

    while (true) {
      try {
         printCycle = ConnectionServer.getSeconds();

        if ((printCycle != lastSecond) && (printCycle == 0 || printCycle % 10 == 0)) {
          System.out.println(ConnectionServer.getTimeStamp() + "Game server #" + getGameNumber() + " is running...");
          lastSecond = printCycle;
        }

        if (player1Handler.hasWinner() || player2Handler.hasWinner()) {
          try {
            if (player1Handler.hasWinner()) {
              player2Handler.getCurrentBoard().setLoser();
            }
            else {
              player1Handler.getCurrentBoard().setLoser();
            }

            Thread.sleep(500); // Ensure next Board is sent
          }
          catch (Exception e) {
            e.printStackTrace();
          }

          player1Handler.flagGameOver();
          player2Handler.flagGameOver();

          Thread.sleep(5000);
          System.out.println(ConnectionServer.getTimeStamp() + "Game server #" + getGameNumber() + " is shutting down...");
          break;
        }
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}
