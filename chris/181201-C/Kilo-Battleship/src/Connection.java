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
  private static int connectionNumTracker;
  private int connectionNumber;

  private ExecutorService threadManager;
  private ServerListener listener;
  private ServerNotifier notifier;

  private Shot currentShot = null;
  private Board currentBoard = null;
  private boolean newShot = false;
  private boolean isGameStarted = false;
  private boolean isGameOver = false;

  /**
   * Constructor
   * Takes in player socket and sets up in/out stream channels
   * @param _socket Player socket from Server
   */
  Connection(Socket _socket, ExecutorService _threadManager) {
    ++connectionNumTracker;
    connectionNumber = connectionNumTracker;

    threadManager = _threadManager;

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

  public static int getConnectionNumTracker() {
    return connectionNumTracker;
  }

  public int getConnectionNumber() {
    return connectionNumber;
  }

  synchronized void flagGameOver() {
    isGameOver = true;
    killCommunication();
  }

  private void killCommunication() {
    listener.flagGameOver();
    notifier.flagGameOver();
  }

  synchronized boolean isGameOver() {
    return isGameOver;
  }

  /**
   * hasNewShot()
   * @return boolean Returns if a new Shot is available for the containing ServerHandler
   */
  public synchronized boolean hasNewShot() {
    return newShot;
  }

  /**
   * getCurrentShot()
   * @return Shot Returns currentShot
   */
  public synchronized Shot getCurrentShot() {
    return currentShot;
  }

  public synchronized void setCurrentShot(Shot _currentShot) {
    currentShot = _currentShot;
  }

  /**
   * flagNewShot()
   * Activates boolean flag when new Shot is received
   */
  private synchronized void flagNewShot() {
    newShot = true;
  }

  /**
   * flagOldShot()
   * Activates boolean flag when newShot is consumed
   */
  public synchronized void flagOldShot() {
    newShot = false;
  }

  /**
   * setCurrentShot()
   * Sets currentShot
   * @param _currentBoard From containing ServerHandler
   */
  public synchronized void setCurrentBoard(Board _currentBoard) {
    currentBoard = _currentBoard;
  }

  /**
   * getCurrentBoard()
   * @return Board Returns current Board
   */
  public synchronized Board getCurrentBoard() {
    return currentBoard;
  }

  public synchronized void initializeBoard() {
    currentBoard = new Board();
    currentBoard.createBoard();
    notifier.setNewBoard(currentBoard);
  }

  public synchronized void startGame() {
    isGameStarted = true;
  }

  public synchronized boolean hasGameStarted() {
    return isGameStarted;
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

    // Stalls thread until game start
    while (!hasGameStarted()) {
      try {
        Thread.sleep(50);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    do {
      if (listener.hasNewObject()) {
        listener.flagOldObject();
        setCurrentShot(new Shot(listener.getNewShot()));
        flagNewShot();
      }

      notifier.setNewBoard(getCurrentBoard());
    } while (getCurrentBoard().isWinner() == null);

    try {
      Thread.sleep(2000);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(ConnectionServer.getTimeStamp() + "Connection #" + getConnectionNumber() + " disconnecting...");
  }
}
