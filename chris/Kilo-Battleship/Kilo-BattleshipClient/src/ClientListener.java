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
  private Board board;

  /**
   * Constructor
   * @param _inputStream From containing Client
   */
  ClientListener(ObjectInputStream _inputStream) {
    super(_inputStream);
    board = new Board();
  }

  /**
   * updateObject()
   * Override ListenerInterface interface
   * Updates current Board from Server processes
   */
  @Override
  public void updateObject() {
    try {
      this.board = (Board) this.inputStream.readObject();
    }
    catch(Exception e) {
      // e.printStackTrace();
      // Commented out to ignore EOF "flag"
    }
  }

  /**
   * getBoard()
   * Override ListenerInterface interface
   * Returns current Board
   * @return
   */
  @Override
  public Board getBoard() {
    return this.board;
  }

  /**
   * run()
   * Override Runnable interface
   * ClientListener thread
   */
  @Override
  public void run() {
    while(true) {
      this.updateObject();
    }
  }
}