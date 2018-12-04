package edu.ucdenver.csci3800.fall2018.kilobattleship.ship;
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
 * The abstract Ship class is inherited by the individual ship classes.
 */
public abstract class Ship implements Serializable {
  private final int SIZE;
  private final int ID;

  /**
   * Constructor: Ship(int _SIZE, int _ID)
   * @param _SIZE Size of ship
   * @param _ID ID of ship
   */
  Ship(int _SIZE, int _ID) {
    SIZE = _SIZE;
    ID = _ID;
  }

  /**
   * getSize()
   * @return SIZE
   */
  public int getSize() {
    return SIZE;
  }

  /**
   * getID()
   * @return ID
   */
  public int getID() {
    return ID;
  }


}

/*
// Additional variables and methods for future game expansion

// Variables
private int _totalHealth;
private int _currentHealth;
private int healthDecrement = 1;
private String _shipName;

// Functions
public void setSize(int size){
this._size = size;
}

public void setTotalHealth(int health) {
this._totalHealth = health;
}

public void setCurrentHealth(int health) {
this._currentHealth = health;
}

public void setId(int id){
this._id = id;
}

public void setShipName(String shipName){
this._shipName = shipName;
}

public int getHealth(){
return this._totalHealth;
}

public int getCurrentHealth(){
return this._currentHealth;
}

public String getShipName(){return _shipName;}

public void decrementHealth(){
_currentHealth -= healthDecrement;
}

public void printShipSunk(){
System.out.println(this.getShipName() + " sunk!");
}
*/
