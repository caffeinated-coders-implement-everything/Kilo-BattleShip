package edu.ucdenver.csci3800.fall2018.kilobattleship;
/**
 * Kilo-BattleShip
 * by Caffeinated Coders Implement Everything
 *
 * CU Denver CSCI 3800 - Advanced Programming with Java and Python
 * Fall 2018
 *
 * @author Chris Frey
 * @author Todd Labo
 * @author Andrew Lewis
 * @author Brian Sumner
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * The Server class contains the main() method to run the Server program.
 */
public class Server
{
  // Define the port the server will accept connections on here:
  private static final int SERVER_PORT = 7777;

  private static final DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("(yyyy/MM/dd HH:mm:ss)");

  /**
   * getSeconds()
   * @return LocalDateTime.now().getSecond()
   */
  synchronized static int getSeconds() {
    return LocalDateTime.now().getSecond();
  }

  /**
   * getTimeStamp()
   * @return LocalDateTime.now()
   */
  synchronized static String getTimeStamp() {
    return timeStampFormatter.format(LocalDateTime.now()) + ": ";
  }

  /**
   * main() is the main method of the Server program
   * @param args Unused
   */
  public static void main(String[] args) {
    Socket socket1;
    Socket socket2;
    ServerHandler player1;
    ServerHandler player2;
    GameServer gameServer;


    try {
      ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

      while (true) {
        ExecutorService threadManager = Executors.newCachedThreadPool();

        System.out.println(getTimeStamp() + "Connection server running. Awaiting next connection...");

        socket1 = serverSocket.accept();
        socket1.setPerformancePreferences(1, 2,0);

        player1 = new ServerHandler(socket1, threadManager);

        System.out.println(getTimeStamp() + "Player #" + ServerHandler.totalConnectionNum() + " connection established");

        gameServer = new GameServer(threadManager);

        System.out.println(getTimeStamp() + "Game server #" + GameServer.getGameNumTracker() + " created");

        gameServer.setPlayer1(player1);

        System.out.println(getTimeStamp() + "Player #" + ServerHandler.totalConnectionNum() +
            " passed to game server #" + GameServer.getGameNumTracker());

        threadManager.execute(gameServer);

        socket2 = serverSocket.accept();
        socket2.setPerformancePreferences(1, 2, 0);

        player2 = new ServerHandler(socket2, threadManager);

        System.out.println(getTimeStamp() + "Player #" + ServerHandler.totalConnectionNum() +
            " connection established");

        gameServer.setPlayer2(player2);

        System.out.println(getTimeStamp() + "Player #" + ServerHandler.totalConnectionNum() +
            " passed to GameServer #" + GameServer.getGameNumTracker());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
