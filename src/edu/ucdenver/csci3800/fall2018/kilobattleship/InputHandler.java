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

import java.util.Scanner;
import java.util.concurrent.ExecutorService;

/**
 * The InputHandler class manages user input for the Client program.
 */
public class InputHandler implements Runnable {
  private Scanner keyListener = new Scanner(System.in);
  private ExecutorService threadManager;
  private ClientNotifier notifier;

  private boolean isGameOver = false;
  private int x;
  private int y;


  /**
   * Constructor: InputHandler(ExecutorService _threadManager, ClientNotifier _notifier)
   * @param _threadManager Passed from Client
   * @param _notifier Passed from Client
   */
  InputHandler(ExecutorService _threadManager, ClientNotifier _notifier) {
    threadManager = _threadManager;
    notifier = _notifier;
  }

  /**
   * isGameOver() allows the GameServer to determine when the game has ended
   * @return isGameOver
   */
  public synchronized boolean isGameOver() {
    return isGameOver;
  }

  /**
   * flagGameOver() sets the isGameOver flag to true
   */
  synchronized void flagGameOver() {
    isGameOver = true;
  }

  /**
   * getX()
   * @return x coordinate
   */
  synchronized int getX() {
    return x;
  }

  /**
   * getY()
   * @return y coordinate
   */
  synchronized int getY() {
    return y;
  }

  /**
   * incrementX()
   */
  private synchronized void incrementX() {
    ++x;
  }

  /**
   * decrementX()
   */
  private synchronized void decrementX() {
    --x;
  }

  /**
   * incrementY()
   */
  private synchronized void incrementY() {
    ++y;
  }

  /**
   * decrementY()
   */
  private synchronized void decrementY() {
    --y;
  }

  synchronized boolean hasDisconnect() {
    return notifier.hasDisconnect();
  }

  /**
   * run() provides the Runnable thread for the InputHandler class
   */
  @Override
  public void run() {
    Shot newShot;
    char keyPress;

    threadManager.execute(notifier);

    while(!this.isGameOver()) {
      try {
        keyPress = keyListener.nextLine().charAt(0);
        keyListener.reset();

        if ((keyPress == 'w' || keyPress == 'W') && x != 0) {
          this.decrementX();
        }
        else if ((keyPress == 's' || keyPress == 'S') && x != (Board.BOARD_LENGTH - 1)) {
          this.incrementX();
        }
        else if ((keyPress == 'a' || keyPress == 'A') && y != 0) {
          this.decrementY();
        }
        else if ((keyPress == 'd' || keyPress == 'D') && y != (Board.BOARD_WIDTH - 1)) {
          this.incrementY();
        }
        else if (keyPress == 'f' || keyPress == 'F') {

          newShot = new Shot(x, y);
          notifier.setNewShot(newShot);
        }
      }
      catch (Exception e) {
        try {
          keyListener.nextLine();
        }
        catch (Exception ignore) { }
      }
    }

    notifier.killProcess();

    try {
      Thread.sleep(500);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

