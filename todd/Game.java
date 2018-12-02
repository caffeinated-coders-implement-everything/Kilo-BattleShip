import java.util.Scanner;
import java.util.*;
import java.util.Random;

    public class Game {

        Scanner scanner = new Scanner(System.in);
        private int boardLength = 20;
        private int boardWidth = 20;
        private Integer[][] playerOneGameBoard;
        private Integer[][] playerTwoGameBoard;


        public Game(){

            playerOneGameBoard = new Integer[boardLength][boardWidth];

            playerTwoGameBoard = new Integer[boardLength][boardWidth];

            Board playerOneBoard = new Board();
            playerOneGameBoard = playerOneBoard.createBoard();

            Board playerTwoBoard = new Board();
            playerTwoGameBoard = playerTwoBoard.createBoard();
        }


        // this method will query the server for an updated gameboard.
        public void updateBoard() {

            // Not only should this method get a 2D array from the server, but also a boolean for whether
            // there is a gameover or a win/loss, and a hit/miss.

            for (int rows = 0; rows < boardLength; rows++) {
                for (int columns = 0; columns < boardWidth; columns++) {
                    //System.out.println();
                    //take server input and load it into 2D array.
                    //playerGameBoard[rows][columns] = server input
                }
            }
        }

    }

