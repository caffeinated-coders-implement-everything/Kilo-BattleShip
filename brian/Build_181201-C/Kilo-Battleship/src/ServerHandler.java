/*
  Runs on it's own thread on the server side to process
  a mutation of the currentBoard based on the currentShot

  SRP: Loop(Process currentShot )

  -Chris
*/

import java.util.concurrent.ExecutorService;

public class ServerHandler implements Runnable {

  private Connection player;
  private ExecutorService threadManager;
  private boolean isWinner = false;

  /**
   * Constructor
   * @param _player From containing Game
   */
  ServerHandler(Connection _player, ExecutorService _threadManager) {
    player = _player;
    threadManager = _threadManager;
  }

  private synchronized void flagWinner() {
    isWinner = true;
  }

  synchronized boolean hasWinner() {
    return isWinner;
  }

  void flagGameOver() {
    player.flagGameOver();
  }

  /**
   *
   */
  synchronized void startGame() {
    player.startGame();
  }

  synchronized void initializeBoard() {
    player.initializeBoard();
  }

  /**
   *
   * @return currentBoard
   */
  synchronized Board getCurrentBoard() {
    return player.getCurrentBoard();
  }

  /**
   * run()
   * ServerHandler thread
   */
  @Override
  public void run() {
    threadManager.execute(player);

    while(!player.hasGameStarted()) {
      try {
        Thread.sleep(50);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    while (!player.isGameOver()) {
      if(player.hasNewShot()) {
        player.flagOldShot();
        Board tmpBoard = new Board(player.getCurrentBoard());
        tmpBoard.setShot(player.getCurrentShot().getX(), player.getCurrentShot().getY());
        player.setCurrentBoard(tmpBoard);

        if (tmpBoard.isWinner() != null) {
          flagWinner();
          try {
            Thread.sleep(5000);
          }
          catch(Exception e) {
            e.printStackTrace();
          }
        }
      }
      try {
        Thread.sleep(10);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}
