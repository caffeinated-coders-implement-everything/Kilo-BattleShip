import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    private static boolean serverConnection = true;

    public static void main(String[] args){

        Game game = new Game();

        try {
            Socket client = new Socket("localhost", 9888);

            PrintWriter output = new PrintWriter(client.getOutputStream(),true);

            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String response = input.readLine(); System.out.println(response);

            output.close();
            input.close();
            client.close();

        }catch(Exception e){
            System.out.println("Server connection failed.");
            serverConnection = false;
        }

        game.updateBoard();

    }
}




