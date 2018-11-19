public class Outgoing {

    private int boardLength = 20;
    private int boardWidth = 20;
    private int[][] _playerGameBoard = new int [boardLength][boardWidth];
    private int[][] _opponentGameBoard = new int [boardLength][boardWidth];
    private boolean _gameOver;
    private boolean _winLose;
    private boolean _hitMiss;

    public void setGameBoards(int[][]playerGameBoard, int[][]opponentGameBoard){

        _playerGameBoard = playerGameBoard;
        _opponentGameBoard = opponentGameBoard;

    }

    public int[][] getPlayerGameBoard(){
        return _playerGameBoard;
    }

    public int[][] getOpponentGameBoard(){
        return _opponentGameBoard;
    }

    public void set_gameOver(boolean gameOver) {
        _gameOver = gameOver;
    }

    public boolean get_gameOver(){
        return _gameOver;
    }

    public void set_winLose(boolean winLose) {
        _winLose = winLose;
    }

    public boolean getWinLose(){
        return _winLose;
    }

    public void set_hitMiss(boolean hitMiss) {
        _hitMiss = hitMiss;
    }

    public boolean get_hitMiss(){
        return _hitMiss;
    }
}
