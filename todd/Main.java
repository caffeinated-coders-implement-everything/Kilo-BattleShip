import java.util.*;
import java.util.List;
import java.util.ArrayList;
public class Main {


    public static void main(String[] args) {
        int[][] playerOneGameBoard;
        playerOneGameBoard = new int[20][20];
        List<Ships> ships = new ArrayList<Ships>();


        Board playerOneBoard = new Board();
        playerOneGameBoard = playerOneBoard.createBoard();
        playerOneBoard.printBoard();
        ships = playerOneBoard.returnShipList();
        for (int i = 0; i < ships.size(); i++){
            System.out.println(ships.get(i).getShipName() + " = "  + ships.get(i).getHealth());
        }


        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 0 ,0, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,1, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 0,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 0 ,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,0, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,3, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,7, ships));


        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,1, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19 ,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,0, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,3, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 1,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 1,7, ships));


        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,1, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,17, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,12, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 0 ,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,2, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,4, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 4,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,18, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,6, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 17,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,18, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,6, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 0,18, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,6, ships));


        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19 ,19, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19, 9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19, 4, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 19 ,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,0, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,3, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,15, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 18,7, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5 ,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 5,15, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6 ,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 6,15, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7 ,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 7,15, ships));


        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8 ,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 8,15, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9 ,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,15, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,7, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,9, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,10, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,11, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,12, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,13, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,14, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 10,15, ships));

        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 9,5, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 3,8, ships));
        System.out.print(playerOneBoard.checkForHit(playerOneGameBoard, 2,7, ships));
        System.out.println();
        playerOneBoard.printBoard();
        playerOneBoard.printScore(ships);
       // game.printBoard();
        //System.out.println();
        //System.out.println();

        //game.seedBoard(4);
        //game.printBoard();
       // System.out.println();
       // System.out.println();

       // game.seedBoard(3);
       // game.printBoard();
       // System.out.println();
       // System.out.println();

       // game.seedBoard(3);
       // game.printBoard();
       // System.out.println();
       // System.out.println();

        //game.seedBoard(2);
        //game.printBoard();
        //System.out.println();
        //System.out.println();
    }
}

