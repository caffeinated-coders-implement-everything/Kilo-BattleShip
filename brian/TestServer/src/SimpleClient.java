import java.io.*;
import java.net.Socket;
public class SimpleClient implements Serializable
{
    public static void main(String[] args)
    {
        try
        {
            Socket client = new Socket("localhost", 9888);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String response = input.readLine();
            System.out.println(response);

            Person newPerson = new Person();
            newPerson.setName("Hi Bro");
            newPerson.setAge(167);
            System.out.println("Original name of person:  " + newPerson);

            ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
            objectOutput.flush();
            objectOutput.writeObject(newPerson);
            objectOutput.flush();

            ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
            System.out.println(objectInput.readObject());

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
    }
}