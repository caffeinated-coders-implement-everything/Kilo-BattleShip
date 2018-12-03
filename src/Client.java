/*
  Kilo-Battleship Game Client
*/


import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Serializable {
  private static final int PORT = 7777;
  private static final String IP = "127.0.0.1";

  public static void main(String[] args) {
    try {
      Socket socket = new Socket(IP, PORT);
      ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
      ClientListener listener = new ClientListener(inputStream);
      ClientNotifier notifier = new ClientNotifier(outputStream);

      // Create "thread manager"
      ExecutorService threadManager = Executors.newCachedThreadPool();

      InputHandler cursorManager = new InputHandler(threadManager, notifier);

      // Starts listener thread
      threadManager.execute(listener);

      // Starts board refresh thread
      threadManager.execute(cursorManager);

      while (listener.getNewBoard() == null) {
        System.out.println("Waiting for Player 2...");
        Thread.sleep(1000);
      }

      Board copyBoard = new Board(listener.getNewBoard());

      while (copyBoard.isWinner() == null) {
        copyBoard.printBoard(cursorManager.getX(), cursorManager.getY());
        copyBoard = new Board(listener.getNewBoard());
        Thread.sleep(1000);
      }

      // Flag threads to shutdown

      try {
        Thread.sleep(1000);
      }
      catch (Exception e) {
        e.printStackTrace();
      }

      copyBoard.printBoard(cursorManager.getX(), cursorManager.getY());

      if (copyBoard.isWinner()) {
        System.out.println("You won");
      }
      else {
        System.out.println("You lost");
      }
    }
    catch (Exception e) {
       e.printStackTrace();
      // Commented out to ignore EOF "flag"
    }
  }
}

