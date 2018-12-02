import java.io.ObjectInputStream;

abstract class Listener implements Runnable, ListenerInterface {
  final ObjectInputStream inputStream;
  private boolean isNewObject = false;

  Listener(ObjectInputStream _inputStream) {
    this.inputStream = _inputStream;
  }

  public synchronized boolean hasNewObject() {
    return isNewObject;
  }

  public synchronized void flagNewObject() {
    isNewObject = true;
  }

  public synchronized void flagOldObject() {
    isNewObject = false;
  }
}
