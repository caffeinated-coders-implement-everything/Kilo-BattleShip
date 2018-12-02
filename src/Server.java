/*
  Main server thread. Accepts new connections,
  creates a Connection, creates a Game, and passes the connection
  to a Game.

  SRP: Loop(Direct new Client connections)

  -Chris
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server
{
  private static final DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("(yyyy/MM/dd HH:mm:ss)");

  synchronized static int getSeconds() {
    return LocalDateTime.now().getSecond();
  }

  synchronized static String getTimeStamp() {
    return timeStampFormatter.format(LocalDateTime.now()) + ": ";
  }

  public static void main(String[] args) {
    Socket socket1;
    Socket socket2;
    Connection player1;
    Connection player2;
    GameServer gameServer;

    try {
      ServerSocket serverSocket = new ServerSocket(7777);

      while (true) {
        ExecutorService threadManager = Executors.newCachedThreadPool();

        System.out.println(getTimeStamp() + "Connection server running. Awaiting next connection...");

        socket1 = serverSocket.accept();
        player1 = new Connection(socket1, threadManager);

        System.out.println(getTimeStamp() + "Connection #" + Connection.getConnectionNumTracker() + " established");

        gameServer = new GameServer(threadManager);

        System.out.println(getTimeStamp() + "Game server #" + GameServer.getGameNumTracker() + " created");

        gameServer.setPlayer1(player1);

        System.out.println(getTimeStamp() + "Connection #" + Connection.getConnectionNumTracker() +
            " passed to GameServer #" + GameServer.getGameNumTracker());

        // shutdownNow() is called on this thread pool in the GameServer class
        threadManager.execute(gameServer);

        socket2 = serverSocket.accept();

        player2 = new Connection(socket2, threadManager);

        System.out.println(getTimeStamp() + "Connection #" + Connection.getConnectionNumTracker() +
            " established");

        gameServer.setPlayer2(player2);

        System.out.println(getTimeStamp() + "Connection #" + Connection.getConnectionNumTracker() +
            " passed to GameServer #" + GameServer.getGameNumTracker());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
