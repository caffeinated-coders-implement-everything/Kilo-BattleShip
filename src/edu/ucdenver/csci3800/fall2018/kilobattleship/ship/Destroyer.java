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
 * The Destroyer class describes a Destroyer Ship object.
 */
public class Destroyer extends Ship {
  private static final int SIZE = 2;
  private static final int ID = 1;

  /**
   * Constructor: Destroyer()
   */
  public Destroyer() {
    super(SIZE, ID);
  }
}

/*
// Additional variables and methods for future game expansion

// Variables
private int size = 2;
private int totalHealth = 2;
private int currentHealth = 2;
private int id = 1;

// Constructor
public Destroyer(){
super();
this.setSize(size);
this.setTotalHealth(totalHealth);
this.setCurrentHealth(currentHealth);
this.setId(id);
this.setShipName("Destroyer");
}
*/

