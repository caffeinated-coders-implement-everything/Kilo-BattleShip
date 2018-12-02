/*
  Runs on it's own thread on the Server side and provides
  public, synchronized methods for the ServerHandler to
  grab Shots sent from the Client.

  SRP: Loop(Get and store the latest Shot from the Client)

  -Chris
*/

import java.io.ObjectInputStream;

public class ServerListener extends Listener {

  // Current shot. Updated constantly from contained runnable
  private Shot shot = null;

  /**
   * Constructor
   * @param _inputStream From containing Connection
   */
  ServerListener(ObjectInputStream _inputStream) {
    super(_inputStream);
  }

  /**
   * updateObject()
   * Override ListenerInterface interface
   * Updates current Shot from Client input.
   */
  @Override
  public void updateObject() {
    try {
      this.shot = (Shot)this.inputStream.readObject();
    }
    catch(Exception e) {
      // e.printStackTrace();
      // Need to ignore EOF "flag"
    }
  }

  /**
   * getShot()
   * Override ListenerInterface interface
   * Returns current Shot
   * @return Shot
   */
  @Override
  public Shot getShot() {
    return shot;
  }

  /**
   * run()
   * Override Runnable interface
   * ServerListener thread
   */
  @Override
  public void run() {
    while(true) {
      updateObject();
    }
  }
}