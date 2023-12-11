package clientToserverApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BmiClient {
    private BmiCalculatorApp gui;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public BmiClient(BmiCalculatorApp gui) {
        this.gui = gui;
        try {
            // initialize
            socket = new Socket("localhost", 1313);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // display a message whether it is succesful connection
            System.out.println("Client connected to the server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // send a request to the server to update gui
    public void sendRequest(double height, double weight, boolean isMeters) {
        try {
            writer.println(height + "," + weight);

            String response = reader.readLine();
            gui.updateResultLabel(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close the connection of client to the server
    public void close() {
        try {
            socket.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
