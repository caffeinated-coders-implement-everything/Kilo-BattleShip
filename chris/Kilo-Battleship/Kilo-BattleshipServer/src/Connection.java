/*
  Runs on it's own thread on the server side and provides
  public, synchronized methods for the ServerHandler to get
  currentShot when a new Shot is available Filters Shot
  objects that have already been presented to ServerHandler.

  SRP: Loop(Provide current Shots to ServerHandler current Boards to ServerNotifier)

  -Chris
*/

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Connection implements Runnable {
  // TODO: Change to private vars and make getters
  public static int connectionNumTracker;
  public int connectionNumber;

  private ExecutorService threadManager;
  private boolean newShot;
  private Shot currentShot;
  private Shot prevShot;
  private Shot tmpShot;
  private Board currentBoard;
  private Board prevBoard;
  private Board tmpBoard;
  private ServerListener listener;
  private ServerNotifier notifier;

  /**
   * Constructor
   * Takes in player socket and sets up in/out stream channels
   * @param _socket Player socket from Server
   */
  Connection(Socket _socket, ExecutorService _threadManager) {
    ++connectionNumTracker;
    connectionNumber = connectionNumTracker;

    threadManager = _threadManager;
    newShot = false;
    currentShot = null;
    prevShot = null;
    currentBoard = new Board();
    prevBoard = null;
    tmpBoard = null;
    tmpShot = null;

    try {
      ObjectOutputStream outputStream = new ObjectOutputStream(_socket.getOutputStream());
      ObjectInputStream inputStream = new ObjectInputStream(_socket.getInputStream());
      listener = new ServerListener(inputStream);
      notifier = new ServerNotifier(outputStream);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * flagNewShot()
   * Activates boolean flag when new Shot is received
   */
  private void flagNewShot() {
    newShot = true;
  }

  /**
   * hasNewShot()
   * @return boolean Returns if a new Shot is available for the containing ServerHandler
   */
  public boolean hasNewShot() {
    return newShot;
  }

  /**
   * getCurrentShot()
   * @return Shot Returns currentShot
   */
  public Shot getCurrentShot() {
    return currentShot;
  }

  private void setCurrentShot(Shot _currentShot) {
    currentShot = _currentShot;
  }

  /**
   * setCurrentShot()
   * Sets currentShot
   * @param _currentBoard From containing ServerHandler
   */
  public void setCurrentBoard(Board _currentBoard) {
    currentBoard = _currentBoard;
  }

  /**
   * getCurrentBoard()
   * @return Board Returns current Board
   */
  public Board getCurrentBoard() {
    return currentBoard;
  }

  /**
   * run()
   * Override Runnable interface
   * Connection thread
   */
  @Override
  public void run() {
    // Starts listener thread
    threadManager.execute(listener);

    // Starts notifier thread
    threadManager.execute(notifier);

    /*
      This thread may need to hand off one of the
      logic branches to another thread. We'll see
      how it keeps up.

      May need some re-work if we run into resource
      locking issues.
    */

    while(true) {
      tmpShot = listener.getShot();

      if(prevShot != tmpShot) {
        setCurrentShot(tmpShot);
        flagNewShot();
        prevShot = tmpShot;
      }

      tmpBoard = getCurrentBoard();

      if(prevBoard != tmpBoard) {
        notifier.setObject(tmpBoard);
        prevBoard = tmpBoard;
      }

    }
  }
}
