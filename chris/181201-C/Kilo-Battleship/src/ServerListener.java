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
  private Shot newShot = null;
  private boolean isGameOver = false;

  /**
   * Constructor
   * @param _inputStream From containing Connection
   */
  ServerListener(ObjectInputStream _inputStream) {
    super(_inputStream);
  }

  synchronized void flagGameOver() {
    isGameOver = true;
  }

  private synchronized boolean isGameOver() {
    return  isGameOver;
  }

  /**
   * updateObject()
   * Override ListenerInterface interface
   * Updates current Shot from Client input.
   */
  @Override
  public void updateObject() {
    try {
      Shot tmpShot = (Shot) inputStream.readObject();
      setNewShot(tmpShot);
      flagNewObject();
    }
    catch(Exception e) {
      e.printStackTrace();
      try { inputStream.close(); } catch(Exception ignore) { }
    }
  }

  /**
   * getShot()
   * Override ListenerInterface interface
   * Returns current Shot
   * @return Shot
   */
  @Override
  public synchronized Shot getNewShot() {
    return newShot;
  }

  private synchronized void setNewShot(Shot _shot) {
    newShot = _shot;
  }

  /**
   * run()
   * Override Runnable interface
   * ServerListener thread
   */
  @Override
  public void run() {
    while (!isGameOver()) {
      try {
        updateObject();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
}