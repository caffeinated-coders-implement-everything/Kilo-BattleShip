/*
  Runs on it's own thread on the server side to process
  a mutation of the currentBoard based on the currentShot

  SRP: Loop(Process currentShot )

  -Chris
*/

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

public class ServerHandler implements Runnable {
  private ExecutorService threadManager;
  private Connection player;
  private ShotProcess shotProcess;
  private LinkedList<ShotProcess> processQueue;
  private Board masterBoard;

  /**
   * Constructor
   * @param _player From containing Game
   */
  ServerHandler(Connection _player, ExecutorService _threadManager) {
    threadManager = _threadManager;
    player = _player;
    shotProcess = null;
    processQueue = new LinkedList<>();
    masterBoard = new Board();
  }

  /**
   * run()
   * ServerHandler thread
   */
  @Override
  public void run() {
    while(true) {
      if(player.hasNewShot()) {
        shotProcess = new ShotProcess(player.getCurrentShot(), masterBoard);

        threadManager.execute(shotProcess);

        processQueue.add(shotProcess);
      }

      if(processQueue.peek() != null && processQueue.element().isDead()) {
          player.setCurrentBoard(processQueue.poll().getCurrentBoard());
      }
    }
  }
}
