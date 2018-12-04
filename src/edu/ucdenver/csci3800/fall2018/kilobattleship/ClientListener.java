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

import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * The ClientListener class receives Board objects from the Server to the Client.
 */
public class ClientListener implements Runnable {
  private ObjectInputStream inputStream;
  private final Socket socket;
  private Board newBoard = null;

  private boolean isGameOver = false;
  private boolean hasDisconnect = false;

  /**
   * Constructor: ServerListener(Socket _socket)
   * @param _socket From containing Handler
   */
  ClientListener(Socket _socket) {
    socket = _socket;
  }

  /**
   * getNewBoard()
   * @return newBoard
   */
  synchronized Board getNewBoard() {
    return newBoard;
  }

  /**
   * setNewBoard(Board _incomingBoard)
   * @param _incomingBoard Board from server
   */
  private synchronized void setNewBoard(Board _incomingBoard) {
    newBoard = _incomingBoard;
  }

  /**
   * getSocket()
   * @return socket
   */
  private synchronized Socket getSocket() {
    return socket;
  }

  /**
   * hasDisconnect()
   * @return hasDisconnect
   */
  synchronized boolean hasDisconnect() {
    return hasDisconnect;
  }

  /**
   * flagDisconnect()
   */
  private synchronized void flagDisconnect() {
    hasDisconnect = true;
  }

  /**
   * killProcess()
   */
  synchronized void killProcess() {
    isGameOver = true;
  }

  synchronized boolean isGameOver() {
    return isGameOver;
  }

  /**
   * updateBoard()
   */
  private void updateBoard() {
    try {
        Board incomingBoard = (Board) inputStream.readObject();
        this.setNewBoard(incomingBoard);

    } catch (SocketTimeoutException ste) {
      if (this.getNewBoard() != null) {
        System.out.println("\nConnection to server lost...");
        this.killProcess();
      }
    } catch (Exception ignoreRest) { }
  }

  /**
   * run() provides the Runnable thread for the ClientListener class
   */
  @Override
  public void run() {
    try {
      inputStream = new ObjectInputStream(socket.getInputStream());

      while (!this.isGameOver) { this.updateBoard(); }

      this.inputStream.close();

    } catch(Exception e) {
        e.printStackTrace();
    }
  }
}