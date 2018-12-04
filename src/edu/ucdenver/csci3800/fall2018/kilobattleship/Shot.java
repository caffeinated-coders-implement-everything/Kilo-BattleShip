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

import java.io.Serializable;

/**
 * The Shot class allows the Client to send to the Server the locations where the player is firing.
 */
public class Shot implements Serializable {
  private int x;
  private int y;

  /**
   * Constructor: Shot(int _x, int _y) creates a new Shot object
   * @param _x x coordinate
   * @param _y y coordinate
   */
  Shot(int _x, int _y) {
    x = _x;
    y = _y;
  }

  /**
   * Constructor: Shot(Shot _shot) copies a Shot object
   * @param _shot Shot to be copied
   */
  Shot(Shot _shot) {
    x = _shot.getX();
    y = _shot.getY();
  }

  /**
   * setX(int x)
   * @param x x coordinate
   */
  public synchronized void setX(int x) {
      this.x = x;
  }

  /**
   * getX()
   * @return x coordinate
   */
  synchronized int getX() {
      return x;
  }

  /**
   * setY(int y)
   * @param y y coordinate
   */
  public synchronized void setY(int y) {
      this.y = y;
  }

  /**
   * getY()
   * @return y coordinate
   */
  synchronized int getY() { return y; }

  /**
   * getShotKey()
   * @return x y coordinates concatenated as a String
   */
  synchronized String getShotKey() {
    return Integer.toString(x) + Integer.toString(y);
  }

}
