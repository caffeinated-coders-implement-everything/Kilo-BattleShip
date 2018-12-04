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
 * The Battleship class describes a Battleship ship object.
 */
public class Battleship extends Ship {
  private static final int SIZE = 4;
  private static final int ID = 4;

  /**
   * Constructor: Battleship()
   */
  public Battleship() {
    super(SIZE, ID);
  }
}

/*
// Additional variables and methods for future game expansion

// Variables
private int totalHealth = 4;
private int currentHealth = 4;

// Constructor
setTotalHealth(totalHealth);
setCurrentHealth(currentHealth);
*/


