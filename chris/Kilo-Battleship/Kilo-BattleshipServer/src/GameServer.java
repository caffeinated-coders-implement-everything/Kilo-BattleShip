/*
  Runs on it's own thread on the server side to
  manage top level game logic and Handler threads.

  SRP: Constant(Gen player1 thread, wait for player2, gen player2 thread)
       Loop(Checking if game is over and stuff)

  -Chris
*/

import java.util.concurrent.ExecutorService;

public class GameServer implements Runnable {
  // TODO: Change to private vars and make getters
  public static int gameNumTracker;
  public int gameNumber;

  private ExecutorService threadManager;
  private Connection player1Connection = null;
  private Connection player2Connection = null;
  private ServerHandler player1Handler = null;
  private ServerHandler player2Handler = null;

  GameServer(ExecutorService _threadManager) {
    ++gameNumTracker;
    gameNumber = gameNumTracker;

    threadManager = _threadManager;
  }

  /**
   * setPlayer1()
   * Sets player1Connection
   * @param _player1Connection From containing ConnectionServer
   */
   void setPlayer1(Connection _player1Connection) {
    player1Connection = _player1Connection;
  }

  /**
   * setPlayer2()
   * Sets player2Connection
   * @param _player2Connection
   */
   void setPlayer2(Connection _player2Connection) {
    player2Connection = _player2Connection;
  }

  /**
   * isGameFull()
   * @return boolean Returns if there are two connections or not
   */
  private  boolean isGameFull() {
    return player1Connection != null && player2Connection != null;
  }

  /**
   * run()
   * Override Runnable interface
   * GameServer thread
   */
  @Override
  public void run() {
    player1Handler = new ServerHandler(player1Connection, threadManager);

    threadManager.execute(player1Connection);

    while(!isGameFull()) {
      try {
        System.out.println("GameServer #" + gameNumber + " is running...");

        Thread.sleep(5000);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    player2Handler = new ServerHandler(player2Connection, threadManager);
    threadManager.execute(player2Connection);

    while(true) {
      try {
        /*
          This is where all the top level game logic
          will go. Basically checking if the game is over.

          Most up to date Board objects can be grabbed from
          player1Connection.getCurrentBoard and player2Connection.getCurrentBoard

          Static tracking variables in Board class?
        */



        Thread.sleep(5000);
        System.out.println("GameServer #" + gameNumber + " is running...");
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}
