package edu.ucdenver.csci3800.fall2018.kilobattleship;
/**
 * Kilo-BattleShip
 * by Caffeinated Coders Implement Everything
 *
 * CU Denver CSCI 3800 - Advanced Programming with Java and Python
 * Fall 2018
 *
 * @author Chris Frey
 * @author Todd Labo
 * @author Andrew Lewis
 * @author Brian Sumner
 */

import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * The ServerHandler class manages communications and gameplay between the Server and its Clients.
 */
public class ServerHandler implements Runnable {
  private static int totalConnectionNum;
  private int connectionNum;

  private ExecutorService threadManager;
  private ServerListener listener;
  private ServerNotifier notifier;

  private Shot currentShot = null;
  private Board currentBoard = null;

  private boolean hasWinner = false;
  private boolean isGameStarted = false;
  private boolean isGameOver = false;

  /**
   * Constructor: ServerHandler(Socket _socket, ExecutorService _threadManager)
   * @param _socket Player socket from Server
   * @param _threadManager Thread pool from Server
   */
  ServerHandler(Socket _socket, ExecutorService _threadManager) {
    try {
      ++totalConnectionNum;
      connectionNum = totalConnectionNum;
      threadManager = _threadManager;
      listener = new ServerListener(_socket);
      notifier = new ServerNotifier(_socket);

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * totalConnectionNum()
   * @return totalConnectionNum
   */
  static int totalConnectionNum() {
    return totalConnectionNum;
  }

  /**
   * connectionNum()
   * @return connectionNum
   */
  public int connectionNum() {
    return connectionNum;
  }

  synchronized void flagDisconnect() {
    listener.flagDisconnect();
  }

  /**
   * killStreams()
   */
  private void killStreams() {
    listener.killProcess();
    notifier.killProcess();
  }

  /**
   * isGameOver()
   * @return isGameOver
   */
  synchronized boolean isGameOver() {
    return isGameOver;
  }

  /**
   * flagGameOver()
   */
  private synchronized void flagGameOver() {
    isGameOver = true;
  }

  /**
   * getCurrentShot()
   * @return Shot Returns currentShot
   */
  private synchronized Shot getCurrentShot() {
    return currentShot;
  }

  /**
   * setCurrentShot(Shot _currentShot)
   * @param _currentShot Newest Shot
   */
  private synchronized void setCurrentShot(Shot _currentShot) {
    currentShot = _currentShot;
  }

  /**
   * setCurrentBoard(Board _currentBoard)
   * @param _currentBoard From containing ServerHandler
   */
  private synchronized void setCurrentBoard(Board _currentBoard) {
    currentBoard = _currentBoard;
  }

  /**
   * getCurrentBoard()
   * @return Board Returns current Board
   */
  synchronized Board getCurrentBoard() {
    return currentBoard;
  }

  /**
   * initializeBoard()
   */
  synchronized void initializeBoard() {
    currentBoard = new Board();
    currentBoard.createBoard();
    notifier.setNewBoard(currentBoard);
  }

  /**
   * startGame()
   */
  synchronized void startGame() {
    isGameStarted = true;
  }

  /**
   * hasGameStarted()
   * @return isGameStarted
   */
  private synchronized boolean hasGameStarted() {
    return isGameStarted;
  }

  /**
   * flagWinner()
   */
  private synchronized void flagWinner() {
    hasWinner = true;
  }

  /**
   * hasWinner()
   * @return hasWinner
   */
  synchronized boolean hasWinner() {
    return hasWinner;
  }

  /**
   * run() provides the Runnable thread for the ServerHandler class
   */
  @Override
  public void run() {
    threadManager.execute(listener);
    threadManager.execute(notifier);
    Board tmpBoard;

    try {

      while (!hasGameStarted()) { Thread.sleep(50); }

      while (!getCurrentBoard().isGameOver() && !listener.hasDisconnect() && !notifier.hasDisconnect()) {

        if (listener.hasNewObject()) {
          listener.flagOldObject();
          this.setCurrentShot(new Shot(listener.getNewShot()));
          tmpBoard = new Board(getCurrentBoard());
          tmpBoard.setShot(getCurrentShot().getX(), getCurrentShot().getY());
          setCurrentBoard(tmpBoard);

          notifier.setNewBoard(getCurrentBoard());
        }
      }

      if (getCurrentBoard().isGameOver()) {
        this.flagWinner();
        this.flagGameOver();
      }
      else {
        this.flagGameOver();
      }

      Thread.sleep(5000);

      this.killStreams();

      System.out.println(Server.getTimeStamp() + "Player #" + connectionNum() + " disconnecting...");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
