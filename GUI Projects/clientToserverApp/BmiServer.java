package clientToserverApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BmiServer {
    private static boolean isMeters;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1313)) {
			System.out.println("Server is ready for the connection.");

			while (true) {
			    Socket clientSocket = serverSocket.accept();
			    System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostName());

			    // handle client connection with threads
			    Thread thread = new Thread(() -> handleClient(clientSocket));
			    thread.start();
			}
		}
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String input;
            while ((input = reader.readLine()) != null) {
                // parse the inputs by the client
                String[] values = input.split(",");
                double height = Double.parseDouble(values[0]);
                double weight = Double.parseDouble(values[1]);

                // calculate needed values for the response
                double bmi = BmiCalculator.calculate(height, weight, isMeters);
                String result = BmiCalculator.BMItoCategory(bmi);

                // send the response to the client
                writer.println(String.format("BMI: %.4f, Result: %s", bmi, result));

                // print the results to the console
                System.out.printf("Results: Height=%.2f, Weight=%.2f, BMI=%.4f, Result=%s%n", height, weight,
                        bmi, result);
            }

            System.out.println("Client disconnected.");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
