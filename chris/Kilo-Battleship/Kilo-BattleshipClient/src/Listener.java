import java.io.ObjectInputStream;

abstract class Listener implements Runnable, ListenerInterface {
  final ObjectInputStream inputStream;

  Listener(ObjectInputStream _inputStream) {
    this.inputStream = _inputStream;
  }
}
