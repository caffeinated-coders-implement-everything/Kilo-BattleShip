import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Simple_Server {

    public static void main(String[] args) {

        try {

            ServerSocket socketServer = new ServerSocket(9888, 5);
            while (true) {

                Socket connection = socketServer.accept();

                PrintWriter output = new PrintWriter(connection.getOutputStream(), true);

                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                Thread.sleep(3000); //wait for 3 seconds (do processing.)

                output.println("Connected to Java server.");

                output.close();
                input.close();
                connection.close();

            }

        }catch(Exception e){
            System.out.println("Could not connect to server");
        }
    }
}
