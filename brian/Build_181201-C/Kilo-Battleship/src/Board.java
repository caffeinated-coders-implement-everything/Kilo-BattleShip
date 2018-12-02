import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Serializable {
  public static final int boardLength = 10;
  public static final int boardWidth = 10;

  private int fleetSize = 0;
  private int sunkShips = 0;
  private int playerScore = 0;
  private int opponentScore = 0;
  private Integer hitPoints = null; // IMPORTANT
  private Boolean isWinner = null;

  private Integer[][] playerGameBoard;
  private Integer[][] opponentGameBoard;

  private List<Ships> ships;

  Board() {
    playerGameBoard = new Integer[boardLength][boardWidth];
    opponentGameBoard = new Integer[boardLength][boardWidth];
    ships = new ArrayList<>();
  }

  Board(Board _board) {
    fleetSize = _board.getfleetSize();
    sunkShips = _board.getSunkShips();
    playerScore = _board.getPlayerScore();
    opponentScore = _board.getOpponentScore();
    hitPoints = _board.getHitPoints();
    isWinner = _board.isWinner();

    playerGameBoard = _board.getPlayerGameBoard();
    opponentGameBoard = _board.getOpponentGameBoard();

    ships = new ArrayList<>(_board.getShips());
  }

  public synchronized void setWinner() {
    isWinner = true;
  }

  public synchronized void setLoser() {
    isWinner = false;
  }

  public synchronized Boolean isWinner() {
    return isWinner;
  }

  private synchronized int getHitPoints() {
    return hitPoints;
  }

  synchronized boolean isGameOver() {
    return hitPoints == null || hitPoints < 1;
  }

  private synchronized List<Ships> getShips() {
    return ships;
  }

  void setShot(int _x, int _y) {
    if (getOpponentGameBoard()[_x][_y] == null) {
      getOpponentGameBoard()[_x][_y] = 10;
    }
    else {
      getOpponentGameBoard()[_x][_y] *= -1;
      --hitPoints;

      if (hitPoints == 0) {
        setWinner();
      }
    }
  }

  public synchronized void createBoard() {
    hitPoints = 0;
    Carrier carrier = new Carrier();
    ships.add(carrier);
    seedBoard(carrier.getSize(), carrier.getId());
    hitPoints += carrier.getSize();

    Battleship battleship = new Battleship();
    ships.add(battleship);
    seedBoard(battleship.getSize(), battleship.getId());
    hitPoints += battleship.getSize();

    Cruiser cruiser = new Cruiser();
    ships.add(cruiser);
    seedBoard(cruiser.getSize(), cruiser.getId());
    hitPoints += cruiser.getSize();

    Submarine submarine = new Submarine();
    ships.add(submarine);
    seedBoard(submarine.getSize(), submarine.getId());
    hitPoints += submarine.getSize();

    Destroyer destroyer = new Destroyer();
    ships.add(destroyer);
    seedBoard(destroyer.getSize(), destroyer.getId());
    hitPoints += destroyer.getSize();
  }

  public synchronized void setFleetSize(int _fleetSize) {
    fleetSize = _fleetSize;
  }

  public synchronized void setSunkShips(int _sunkShips) {
    sunkShips = _sunkShips;
  }

  public synchronized int getfleetSize() {
    return fleetSize;
  }

  public synchronized int getSunkShips() {
    return sunkShips;
  }

  public synchronized List<Ships> returnShipList() {
    return ships;
  }

  private synchronized void seedBoard(int shipSize, int shipID) {
    Random rand = new Random();
    int x;
    int y;
    int orientation;

    do {
      do{
        x = rand.nextInt(boardWidth) - shipSize;
      } while (x < 0);

      do{
        y = rand.nextInt(boardWidth) - shipSize;
      } while (y < 0);

      orientation = rand.nextInt(2);

    } while (!checkSpaceIsOpen(shipSize, orientation, x, y));

    // now actually assign the ship to the boardArray
    if (orientation == 0) {
      int ship = shipSize + x;
      for (int i = x; i < ship; i++) {
        playerGameBoard[y][i] = shipID;
      }
    }
    else{
      int ship = shipSize + y;
      for (int j = y; j < ship; j++) {
        playerGameBoard[j][x] = shipID;
      }
    }
    fleetSize++;
  }

  private synchronized boolean checkSpaceIsOpen(int shipSize, int orientation, int x, int y) {
    boolean spaceCheck = true;

    //  if orientation is horizontal, just need to check x direction
    if (orientation == 0) {
      int ship = shipSize + x;
      for(; x < ship; x++){
        if (playerGameBoard[y][x] != null) {
          spaceCheck = false;
          break;
        }
      }
    }
    // if orientation is vertical, just need to check y direction
    else {
      int ship = shipSize + y;
      for(; y < ship; y++){
        if (playerGameBoard[y][x] != null) {
          spaceCheck = false;
          break;
        }
      }
    }

    return spaceCheck;
  }

  public synchronized void setGameBoards(int[][] playerGameBoard, int[][] opponentGameBoard) {
    playerGameBoard = playerGameBoard;
    opponentGameBoard = opponentGameBoard;
  }

  public synchronized void setOpponentGameBoard(Integer[][] _opponentGameBoard) {
    opponentGameBoard = _opponentGameBoard;
  }

  public synchronized Integer[][] getPlayerGameBoard() {
    return playerGameBoard;
  }

  public synchronized Integer[][] getOpponentGameBoard() {
    return opponentGameBoard;
  }

  public synchronized void setPlayerScore(int playerScore) {
    playerScore = playerScore;
  }

  public synchronized int getPlayerScore() {
    return playerScore;
  }

  public synchronized void setOpponentScore(int opponentScore) {
    opponentScore = opponentScore;
  }

  public synchronized int getOpponentScore() {
    return opponentScore;
  }

  public synchronized void printBoard(int x, int y) {

    while(opponentGameBoard == null || playerGameBoard == null) {
      try {
        Thread.sleep(10);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    System.out.println();
    System.out.print("\u001B[44m");
    System.out.print("\u001B[0m");

    for (int rows = 0; rows < boardLength; rows++) {

      System.out.print("\u001B[44m");
      System.out.print("|");

      for (int columns = 0; columns < boardWidth; columns++) {
        // prints cursor
        if (rows == x && columns == y) {
          System.out.print("\u001B[0m");
          System.out.print("\u001B[41m");
          System.out.print("  ");
          System.out.print("\u001B[44m");
          System.out.print("|");
          // prints empty space
        }
        else if (opponentGameBoard[rows][columns] == null) {
          System.out.print("\u001B[44m");
          System.out.print("__|");
          // prints your ships
        }
        else if (opponentGameBoard[rows][columns] == 10) {
          System.out.print("\u001B[0m");
          System.out.print("\033[0;100m");
          System.out.print("  ");
          System.out.print("\u001B[44m");
          System.out.print("|");
        }
        else if (opponentGameBoard[rows][columns] > 0) {
          System.out.print("\u001B[44m");
          System.out.print("__|");

         /*
          System.out.print("\033[38;5;7m");
          System.out.print("__|");
          */
        }
        else if (opponentGameBoard[rows][columns] < 0) {
          System.out.print("\u001B[0m");
          System.out.print("\u001B[31m");
          System.out.print("***");
        }
      }

      System.out.print("\u001B[0m");
      System.out.print("\u001B[30m");
      System.out.print("  ");
      System.out.print("\u001B[30m");
      System.out.print(" ");

      for (int columns = 0; columns < boardWidth; columns++) {
        // prints water
        if (playerGameBoard[rows][columns] == null) {
          System.out.print("\u001B[44m");
          System.out.print("__|");
          // prints your ships
        }
        else if (playerGameBoard[rows][columns] == 10) {
            System.out.print("\u001B[0m");
            System.out.print("\033[0;100m");
            System.out.print("  ");
            System.out.print("\u001B[44m");
            System.out.print("|");
          }
          else if (playerGameBoard[rows][columns] > 0) {
            System.out.print("\033[38;5;7m");
            System.out.print("__|");
          }
          else if (playerGameBoard[rows][columns] < 0) {
            System.out.print("\u001B[0m");
            System.out.print("\u001B[31m");
            System.out.print("***");
          }
      }
      // reset color to clear and print newline
      System.out.print("\u001B[0m");
      System.out.println();
    }

    System.out.println("Please select from the following options.");
    System.out.println("   UP - W");
    System.out.println(" LEFT - A");
    System.out.println(" DOWN - S");
    System.out.println("RIGHT - D");
    System.out.println(" FIRE - F");
  }
}