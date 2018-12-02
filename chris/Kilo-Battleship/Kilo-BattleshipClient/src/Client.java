/*
  Kilo-Battleship Game Client
*/


import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Serializable {
  // private static final int PORT = 9888;
  // private static final String IP = "192.168.1.10";

  public static void main(String[] args) {
    try {
      Socket socket = new Socket("localhost", 9888);
      ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
      ClientListener listener = new ClientListener(inputStream);
      ClientNotifier notifier = new ClientNotifier(outputStream);

      // Create "thread manager"
      ExecutorService threadManager = Executors.newCachedThreadPool();

      // Starts listener thread
      threadManager.execute(listener);

      // Starts notifier thread
      threadManager.execute(notifier);

      /*
        This is the meat and potatoes of the client's
        main thread. This loop will be responsible for
        displaying the Board objects to the user console
        as they come in from the ClientListener. printBoard()
        is 'currently' returning a hardcoded String for testing.
      */
      while (true) {
        listener.getBoard().printBoard();
        Thread.sleep(1000);
      }
    }
    catch (Exception e) {
      // e.printStackTrace();
      // Commented out to ignore EOF "flag"
    }
  }
}

