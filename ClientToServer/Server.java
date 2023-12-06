import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1333);
        Socket socket = server.accept();

        System.out.println("Client is ready to connect.");
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(in);

        String message = reader.readLine();
        System.out.println(message);

        System.out.println("Hello");
        System.out.println("stop");
    }
}
