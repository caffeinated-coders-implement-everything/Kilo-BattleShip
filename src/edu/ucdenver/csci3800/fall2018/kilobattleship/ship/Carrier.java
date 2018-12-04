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
 * The Carrier class describes a Carrier ship object.
 */
public class Carrier extends Ship {
  private static final int SIZE = 5;
  private static final int ID = 5;

  /**
   * Constructor: Carrier()
   */
  public Carrier() {
    super(SIZE, ID);
  }
}

/*
// Additional variables and methods for future game expansion

// Variables
private int size = 5;
private int totalHealth = 5;
private  int currentHealth = 5;
private int id = 5;

// Constructor
public Carrier() {
super();
this.setSize(size);
this.setTotalHealth(totalHealth);
this.setCurrentHealth(currentHealth);
this.setId(id);
this.setShipName("Carrier");
}
*/
