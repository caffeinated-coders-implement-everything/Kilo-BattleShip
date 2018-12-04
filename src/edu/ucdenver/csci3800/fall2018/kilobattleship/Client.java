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

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Client class contains the main() method to run the Client program.
 */
public class Client implements Serializable {

  // You must review the SERVER_IP before running the Client program:
  private static final String SERVER_IP = "127.0.0.1";

  // Define the port to connect to the server on here:
  private static final int SERVER_PORT = 7777;


  /**
   * main() is the main method of the Client program
   * @param args Unused
   */
  public static void main(String[] args) {
    try {
      // Create "thread manager"
      ExecutorService threadManager = Executors.newCachedThreadPool();

      Socket socket = new Socket(SERVER_IP, SERVER_PORT);
      socket.setSoTimeout(250);

      InputHandler inputHandler = new InputHandler(threadManager, new ClientNotifier(socket));

      ClientListener listener = new ClientListener(socket);

      // Starts listener thread
      threadManager.execute(listener);

      // Starts board refresh thread
      threadManager.execute(inputHandler);

      while (listener.getNewBoard() == null) {
        System.out.println("Waiting for other player...");
        Thread.sleep(1000);
      }

      Board copyBoard = new Board(listener.getNewBoard());

      while (copyBoard.isWinner() == null && !inputHandler.isGameOver() && !listener.isGameOver()) {
        copyBoard.printBoard(inputHandler.getX(), inputHandler.getY());
        copyBoard = new Board(listener.getNewBoard());
        Thread.sleep(1000);
      }

      copyBoard.printBoard(inputHandler.getX(), inputHandler.getY());

      inputHandler.flagGameOver();
      listener.killProcess();

      if (copyBoard.isWinner() == null) {
        System.out.println("Your opponent has disconnected. Your fleet lives another day.");
      }
      else if (copyBoard.isWinner()) {
        System.out.println("\nYou won!");
      }
      else if (!copyBoard.isWinner()) {
        System.out.println("\nYou lost :(");
      }

      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("\nA random nuke just dropped on everyone. That happens sometimes...");
      System.exit(-1);
    }
  }
}

