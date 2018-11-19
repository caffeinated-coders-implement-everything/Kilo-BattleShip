public class Outgoing {

    private int boardLength = 20;
    private int boardWidth = 20;
    private int[][] _playerGameBoard = new int[boardLength][boardWidth];
    private int[][] _opponentGameBoard = new int[boardLength][boardWidth];
    private int playerScore;
    private int opponentScore;

    public void setGameBoards(int[][] playerGameBoard, int[][] opponentGameBoard) {

        _playerGameBoard = playerGameBoard;
        _opponentGameBoard = opponentGameBoard;

    }

    public int[][] getPlayerGameBoard() {
        return _playerGameBoard;
    }

    public int[][] get_opponentGameBoard() {
        return _playerGameBoard;
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
}