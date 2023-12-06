import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1333)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Server has been connected");
            writer.flush();

            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(in);

            String message = "";
            Scanner scan = new Scanner(System.in);
            while (!message.equals("stop")) {
                System.out.print("Enter your message or stop to stop the server: ");
                message = scan.nextLine();
                System.out.println("Message: " + message);

                if (message.equals("stop")) {
                    System.out.println("Server stopped.");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
