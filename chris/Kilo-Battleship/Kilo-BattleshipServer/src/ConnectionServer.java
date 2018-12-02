/*
  Main server thread. Accepts new connections,
  creates a Connection, creates a Game, and passes the connection
  to a Game.

  SRP: Loop(Direct new Client connections)

  -Chris
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public class ConnectionServer {
  // private static final int PORT = 9888;

  public static void main(String[] args) {
    ServerSocket serverSocket;
    Socket socket;
    Connection player;
    GameServer gameServer;
    ExecutorService threadManager;

    try {
      serverSocket = new ServerSocket(9888);

      threadManager = Executors.newCachedThreadPool();

      while (true) {
        socket = serverSocket.accept();
        player = new Connection(socket, threadManager);

        System.out.println("Connection #" + player.connectionNumTracker + " established");

        gameServer = new GameServer(threadManager);

        System.out.println("GameServer #" + gameServer.gameNumTracker + " created");

        gameServer.setPlayer1(player);

        System.out.println("Player #" + player.connectionNumTracker + " passed to GameServer #" + gameServer.gameNumTracker);

        threadManager.execute(gameServer);

        socket = serverSocket.accept();

        System.out.println("Connection #" + player.connectionNumTracker + " established");

        player = new Connection(socket, threadManager);
        gameServer.setPlayer2(player);

        System.out.println("Player #" + player.connectionNumTracker + " passed to GameServer #" + gameServer.gameNumTracker);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
