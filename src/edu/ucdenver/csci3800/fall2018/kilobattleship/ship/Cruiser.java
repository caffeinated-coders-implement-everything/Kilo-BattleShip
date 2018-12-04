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


/**
 * The Cruiser class describes a Cruiser ship object.
 */
public class Cruiser extends Ship {
  private static final int SIZE = 3;
  private static final int ID = 3;

  /**
   * Constructor: Cruiser()
   */
  public Cruiser() {
    super(SIZE, ID);
  }
}

/*
// Additional variables and methods for future game expansion

// Variables
private int size = 3;
private int totalHealth = 3;
private  int currentHealth = 3;
private int id = 3;

// Constructor
public Cruiser() {
super();
this.setSize(size);
this.setTotalHealth(totalHealth);
this.setCurrentHealth(currentHealth);
this.setId(id);
this.setShipName("Cruiser");
}
*/


