import java.util.Scanner;
import java.io.*;
import java.net.Socket;

public class Game implements Serializable {

    Scanner scanner = new Scanner(System.in);
    private int boardLength = 20;
    private int boardWidth = 20;
    private int[][] playerGameBoard = new int [boardLength][boardWidth];
    private int[][] opponentGameBoard = new int [boardLength][boardWidth];
    private String _ipAddress;

    public Game(String ipAddress){
        _ipAddress = ipAddress;
    }

    // this method will query the server for an updated gameboard.
    public void updateBoard(int x, int y){

        try
        {
            Socket client = new Socket(_ipAddress, 9888);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String response = input.readLine();
            System.out.println(response);

            Incoming shotsFired = new Incoming();
            shotsFired.setX(x);
            shotsFired.setY(y);

            ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
            objectOutput.flush();
            objectOutput.writeObject(shotsFired);
            objectOutput.flush();

            ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
            Outgoing serverReply  = (Outgoing) objectInput.readObject();

            output.close();
            input.close();

            objectOutput.close();
            objectInput.close();
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    // Not only should this method get a 2D array from the server, but also a boolean for whether
        // there is a gameover or a win/loss, and a hit/miss.

        for(int rows = 0; rows < boardLength; rows++){
            for(int columns = 0; columns < boardWidth; columns++){
                //take server input and load it into 2D array.
                //playerGameBoard[rows][columns] = server input
            }
        }
    }

    // this method moves the cursor
    public void playerMove(int x, int y){

        int playerMove = scanner.nextInt();

        switch(playerMove){

            case 1:
                if(y != 1){
                    y -= 1;
                }
                printGameBoard(x, y);
                break;
            case 2:
                if(y != 8){
                    y += 1;
                }
                printGameBoard(x, y);
                break;
            case 3:
                if(x != 1){
                    x -= 1;
                }
                printGameBoard(x, y);
                break;
            case 4:
                if(x != 8){
                    x += 1;
                }
                printGameBoard(x, y);
                break;
            case 9:
                sendMove(x, y);
                break;
            case 0:
                System.out.println("See you next time!");
                System.exit(0);
                break;
            default:
                System.out.println("ERROR! Invalid key!.");
                System.out.println("LEFT - 1");
                System.out.println("Right - 2");
                System.out.println("UP - 3");
                System.out.println("DOWN - 4");
                System.out.println("FIRE - 9");
                System.out.println("EXIT - 0");
        }
    }

    // this method will send the coordinates of the player's shot to the server.
    public void sendMove(int x, int y){

        // the user's coordinate selection will be sent to the server. Then the cursor will be moved back to (0, 0);

        x = 0;
        y = 0;

        updateBoard(x, y);
        printGameBoard(x, y);

    }

    public void printGameBoard(int x, int y){

        System.out.println();
        System.out.print("\u001B[42m");
        System.out.print("\u001B[0m");

        for(int rows = 1; rows < boardLength-1; rows++){
            System.out.print("\u001B[42m");
            System.out.print("|");
            for(int columns = 1; columns < boardWidth-1; columns++){

                // prints cursor
                if(rows == x && columns == y){
                    System.out.print("\u001B[0m");
                    System.out.print("\u001B[41m");
                    System.out.print("  ");
                    System.out.print("\u001B[42m");
                    System.out.print("|");

                // prints empty space
                } else if(playerGameBoard[rows][columns] == 0){
                    System.out.print("\u001B[42m");
                    System.out.print("__|");

                // prints your ships
                }else if (playerGameBoard[rows][columns] == 1){
                    System.out.print("\u001B[0m");
                    System.out.print("\u001B[40m");
                    System.out.print("  ");
                    System.out.print("\u001B[42m");
                    System.out.print("|");
                // prints hit damage, probably going to be a red X.
                }else if (playerGameBoard[rows][columns] == 2){
                    System.out.print("\u001B[0m");
                    System.out.print("\u001B[47m");
                    System.out.print("  ");
                    System.out.print("\u001B[42m");
                    System.out.print("|");
                }
            }

            // reset color to clear and print newline
            System.out.print("\u001B[0m");
            System.out.println();
        }

        System.out.println("Please select from the following options.");
        System.out.println("LEFT - 1");
        System.out.println("Right - 2");
        System.out.println("UP - 3");
        System.out.println("DOWN - 4");
        System.out.println("FIRE - 9");
        System.out.println("EXIT - 0");

        playerMove(x, y);
    }
}
