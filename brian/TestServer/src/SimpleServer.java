import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {
        try {
            ServerSocket socketServer = new ServerSocket(9888, 5);

            while (true) {

                System.out.println("OPENING CONNECTION NOW");

                Socket connection = socketServer.accept();
                PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                //Thread.sleep(3000); //wait for 3 seconds (do processing.)

                output.println("SERVER ACCEPTED CONNECTION");
                System.out.println("SERVER CONNECTED TO CLIENT");

                ObjectInputStream objectInput = new ObjectInputStream( connection.getInputStream() );
                Person newPerson = (Person) objectInput.readObject();

                System.out.println("RECEIVED OBJECT FROM CLIENT");

                ObjectOutputStream objectOutput = new ObjectOutputStream((connection.getOutputStream()));
                newPerson.setName("new name");
                objectOutput.writeObject(newPerson);
                objectOutput.flush();

                System.out.println("SENT OBJECT BACK TO CLIENT");

                output.close();
                input.close();
                objectInput.close();
                objectOutput.close();
                connection.close();

                System.out.println("CLOSED CONNECTION");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
