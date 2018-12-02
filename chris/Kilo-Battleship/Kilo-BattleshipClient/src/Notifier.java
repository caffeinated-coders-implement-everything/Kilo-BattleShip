import java.io.ObjectOutputStream;

abstract class Notifier implements Runnable, NotifierInterface {
  final ObjectOutputStream outputStream;

  Notifier(ObjectOutputStream _outputStream) {
    this.outputStream = _outputStream;
  }
}
