import java.io.Serializable;

public class Board implements Serializable {
  private int boardLength = 10;
  private int boardWidth = 10;
  private int[][] _playerGameBoard = new int[boardLength][boardWidth];
  private int[][] _opponentGameBoard = new int[boardLength][boardWidth];
  private int playerScore = -1;
  private int opponentScore = -1;
  private int playerNum = 0;
  private static int playerNumTracker = 0;

  Board() {
    ++playerNumTracker;
    if(playerNumTracker == 1) {
      playerNum = 1;
    }
    else {
      playerNum = 2;
      playerNumTracker = 0;
    }
  }

  public void setGameBoards(int[][] playerGameBoard, int[][] opponentGameBoard) {
    _playerGameBoard = playerGameBoard;
    _opponentGameBoard = opponentGameBoard;
  }

  public int[][] getPlayerGameBoard() {
    return this._playerGameBoard;
  }

  public int[][] get_opponentGameBoard() {
    return this._playerGameBoard;
  }

  public void setPlayerScore(int playerScore) {
    this.playerScore = playerScore;
  }

  public int getPlayerScore() {
    return playerScore;
  }

  public void setOpponentScore(int opponentScore) {
    this.opponentScore = opponentScore;
  }

  public int getOpponentScore() {
    return opponentScore;
  }

  public int getPlayerNum() {
    return playerNum;
  }

  public void printBoard() {
    System.out.println("[T][E][S][T][ ][B][O][A][R][D]\n" +
        "[E][E][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
        "[S][ ][S][ ][ ][ ][ ][ ][ ][ ]\n" +
        "[T][ ][ ][T][ ][ ][ ][ ][ ][ ]\n" +
        "[ ][ ][ ][ ][ ][ ][ ][ ][ ][ ]\n" +
        "[B][ ][ ][ ][ ][B][ ][ ][ ][ ]\n" +
        "[O][ ][ ][ ][ ][ ][O][ ][ ][ ]\n" +
        "[A][ ][ ][ ][ ][ ][ ][A][ ][ ]\n" +
        "[R][ ][ ][ ][ ][ ][ ][ ][R][ ]\n" +
        "[D][ ][ ][ ][ ][ ][ ][ ][ ][D]\n");

    /*
      Placeholder code for test output to client.
      This is where the 50 for loops will go for printing
      out this dirty bastard.
    */
  }
}