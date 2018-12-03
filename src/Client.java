import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Serializable {

  // You must review the SERVER_IP before running the Client program:
  private static final String SERVER_IP = "127.0.0.1";

  // Define the port to connect to the server on here:
  private static final int SERVER_PORT = 7777;


  public static void main(String[] args) {
    try {
      Socket socket = new Socket(SERVER_IP, SERVER_PORT);


      ClientListener listener = new ClientListener(socket);

      // Create "thread manager"
      ExecutorService threadManager = Executors.newCachedThreadPool();

      InputHandler inputHandler = new InputHandler(threadManager, new ClientNotifier(socket));

      // Starts listener thread
      threadManager.execute(listener);

      // Starts board refresh thread
      threadManager.execute(inputHandler);

      while (listener.getNewBoard() == null) {
        System.out.println("Waiting for other player...");
        Thread.sleep(1000);
      }

      Board copyBoard = new Board(listener.getNewBoard());

      while (copyBoard.isWinner() == null && !listener.hasDisconnect()) {
        copyBoard.printBoard(inputHandler.getX(), inputHandler.getY());
        copyBoard = new Board(listener.getNewBoard());
        Thread.sleep(1000);
      }

      copyBoard.printBoard(inputHandler.getX(), inputHandler.getY());

      if (copyBoard.isWinner()) {
        System.out.println("\nYou won!");
      }
      else if (!copyBoard.isWinner()) {
        System.out.println("\nYou lost :(");
      }
      else {
        System.out.println("A player has disconnected. Your fleet lives another day.");
      }

      inputHandler.flagGameOver();
      listener.killProcess();

      Thread.sleep(1000);

      System.exit(0);
    }
    catch (Exception e) {
      System.out.println("\nA random nuke just dropped on everyone. That happens sometimes...");
      System.exit(-1);
    }
  }
}

