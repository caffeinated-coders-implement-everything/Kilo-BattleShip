/*
  Runs on it's own thread on the client side and provides
  public, methods for the Client to grab
  Boards sent from the Server.

  SRP: Loop(Get and store the latest Board from the server)

  -Chris
*/

import java.io.ObjectInputStream;

public class ClientListener extends Listener {

  // Current Board. Updated constantly from contained runnable
  private Board newBoard = null;

  /**
   * Constructor
   * @param _inputStream From containing Client
   */
  ClientListener(ObjectInputStream _inputStream) {
    super(_inputStream);
  }

  /**
   * updateObject()
   * Override ListenerInterface interface
   * Updates current Board from Server processes
   */
  @Override
  public void updateObject() {
    try {
      Board incomingBoard = (Board) inputStream.readObject();
      setNewBoard(incomingBoard);
    }
    catch(Exception ignore1) {
      try {
        inputStream.close();
        System.out.println("Connection to server lost");
        System.exit(0);
      }
      catch(Exception ignore2) { }
    }
  }

  private synchronized void setNewBoard(Board _incomingBoard) {
    newBoard = _incomingBoard;
  }

  /**
   * getBoard()
   * Override ListenerInterface interface
   * Returns current Board
   * @return
   */
  @Override
  public synchronized Board getNewBoard() {
    return newBoard;
  }

  /**
   * run()
   * Override Runnable interface
   * ClientListener thread
   */
  @Override
  public void run() {
    while(true) {
      try {
        updateObject();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}