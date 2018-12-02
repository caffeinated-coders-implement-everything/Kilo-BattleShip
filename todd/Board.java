import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Board {

    private int _fleetSize;
    private  int _sunkShips;

    private int boardLength = 20;
    private int boardWidth = 20;
    private Integer[][] playerGameBoard;
    private List<Ships> ships;

    public Board(){
        playerGameBoard = new Integer[boardLength][boardWidth];
        this.ships = new ArrayList<Ships>();
        _fleetSize = 0;


    }
    public Integer[][] createBoard (){
        Carrier carrier = new Carrier();
        ships.add(carrier);
        this.seedBoard(carrier.getSize(), carrier.getId());


        Battleship battleship = new Battleship();
        ships.add(battleship);
        this.seedBoard(battleship.getSize(), battleship.getId());

        Cruiser cruiser = new Cruiser();
        ships.add(cruiser);
        this.seedBoard(cruiser.getSize(), cruiser.getId());

        Submarine submarine = new Submarine();
        ships.add(submarine);
        this.seedBoard(submarine.getSize(), submarine.getId());

        Destroyer destroyer = new Destroyer();
        ships.add(destroyer);
        this.seedBoard(destroyer.getSize(), destroyer.getId());

        //this.printBoard();
        //System.out.println();
        //System.out.println();
        return playerGameBoard;
    }

    public void setFleetSize(int fleetSize){
        _fleetSize = fleetSize;
    }

    public void setSunkShips(int sunkShips){
        _sunkShips = sunkShips;
    }

    public int getfleetSize(){
        return this._fleetSize;
    }

    public List<Ships> returnShipList(){
        return this.ships;
    }

    private void seedBoard(int shipSize, int shipID) {
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
            _fleetSize++;
        }



    private boolean checkSpaceIsOpen(int shipSize, int orientation, int x, int y){
        boolean spaceCheck = true;

        //  if orientation is horizontal, just need to check x direction
        if (orientation == 0){
            int ship = shipSize + x;
            for(; x < ship; x++){
                if (playerGameBoard[y][x] != null){
                    spaceCheck = false;
                    break;
                }
            }
        }

        // if orientation is vertical, just need to check y direction
        else{
            int ship = shipSize + y;
            for(; y < ship; y++){
                if (playerGameBoard[y][x] != null){
                    spaceCheck = false;
                    break;
                }
            }
        }
        return spaceCheck;
    }



    public void printBoard() {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        int miss = 10;
        int emptyWater = 0;

        System.out.println("-----------------------------------------------------------------------------");


        for (int y = 0; y < playerGameBoard.length; y++) {
            //System.out.print(y);
            System.out.print(" ");
            for (int x = 0; x < playerGameBoard[y].length; x++) {
                if (playerGameBoard[y][x] != null && playerGameBoard[y][x] != miss && playerGameBoard[y][x] > 0) {
                    System.out.print(playerGameBoard[y][x]);
                }
                else if (playerGameBoard[y][x] != null && playerGameBoard[y][x] < 0) {
                    int shipHit = playerGameBoard[y][x];
                    switch (shipHit){
                        case -1:
                            System.out.print(ANSI_RED + "1" + ANSI_RESET);
                            break;
                        case -2:
                            System.out.print(ANSI_RED + "2" + ANSI_RESET);
                            break;
                        case -3:
                            System.out.print(ANSI_RED + "3" + ANSI_RESET);
                            break;
                        case -4:
                            System.out.print(ANSI_RED + "4" + ANSI_RESET);
                            break;
                        case -5:
                            System.out.print(ANSI_RED + "5" + ANSI_RESET);

                    }
                }

                else if(playerGameBoard[y][x] != null && playerGameBoard[y][x] == miss){
                    System.out.print(0);
                }
                else {
                    System.out.print(" ");
                }
                System.out.print(" ");

            }
            System.out.println();
        } System.out.println("-----------------------------------------------------------------------------");
    }

    // checks for a hit/miss and updates the board array accordingly
    // currently returns a char, but needs to return true/false
    public char checkForHit(Integer[][] boardArray, int x, int y, List shipList){
        char result;    // change this to boolean
        int emptyWater = 0;
        int miss = 10;
        int fleetSize = 5;
        int modifier = -1;
        int i = 0;
        List<Ships> ships = shipList;

        // if the space is empty water
        if (boardArray[y][x] == null){
            boardArray[y][x] = miss;
             result = 'M';    // change this to false
        }

        // if the space is occupied by a ship, and not previously hit
        else if (boardArray[y][x] > 0 && boardArray[y][x] <= fleetSize) {
            int shipId = boardArray[y][x];
            switch (shipId){    //  needs to be done without a case statement if we dynamically scale up the fleet size
                case 5:
                    ships.get(0).decrementHealth();
                    break;
                case 4:
                    ships.get(1).decrementHealth();
                    break;
                case 3:
                    ships.get(2).decrementHealth();
                    break;
                case 2:
                    ships.get(3).decrementHealth();
                    break;
                case 1:
                    ships.get(4).decrementHealth();
                    break;
            }

            boardArray[y][x] = (boardArray[y][x]) * modifier;
            result = 'H';    // change this to true
        }
        // else, the space has already been hit, or a shot was already fired here
        else {
            result = 'A';   // May not be needed, but if it is change to false
        }
        return result;
    }

    public void printScore(List shipList){
        List<Ships> ships = shipList;   // this conversion shouldn't be needed but shipList.get(index).getShipName  doesn't work, why?

        if (ships.get(0).getCurrentHealth() > 0){
            System.out.println(ships.get(0).getShipName() + " Health  = " + ships.get(0).getCurrentHealth() + "/" + ships.get(0).getHealth());
        }
        else {
            ships.get(0).printShipSunk();
        }

        if (ships.get(1).getCurrentHealth() > 0){
            System.out.println(ships.get(1).getShipName() + " Health  = " + ships.get(1).getCurrentHealth() + "/"+ ships.get(1).getHealth());
        }
        else {
            ships.get(1).printShipSunk();
        }

        if (ships.get(2).getCurrentHealth() > 0){
        System.out.println(ships.get(2).getShipName() + " Health  = " + ships.get(2).getCurrentHealth() + "/"+ ships.get(2).getHealth());
        }
        else {
            ships.get(2).printShipSunk();
        }

        if (ships.get(3).getCurrentHealth() > 0){
        System.out.println(ships.get(3).getShipName() + " Health  = " + ships.get(3).getCurrentHealth() + "/" + ships.get(3).getHealth());
        }
        else {
            ships.get(3).printShipSunk();
        }

        if (ships.get(4).getCurrentHealth() > 0){
        System.out.println(ships.get(4).getShipName() + " Health  = " + ships.get(4).getCurrentHealth() + "/" + ships.get(4).getHealth());
        }
        else {
            ships.get(4).printShipSunk();
        }
    }
}
