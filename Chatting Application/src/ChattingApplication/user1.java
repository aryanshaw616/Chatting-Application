package ChattingApplication;

import java.io.*;
import java.net.*;
import java.util.*;

public class user1 {

    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take User 1's name as input
        System.out.print("Enter your name (User 1): ");
        String userName = sc.nextLine();  // Name of User 1 (server)

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println(userName + " is waiting for User 2 to connect...");

            // Accept client connection (User 2)
            clientSocket = serverSocket.accept();
            System.out.println("User 2 connected.");

            // Initialize I/O streams
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Thread to handle receiving messages from User 2
            new Thread(() -> {
                try {
                    String messageFromClient;
                    while ((messageFromClient = in.readLine()) != null) {
                        System.out.println(messageFromClient);  // Show User 2's name and message
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Handle sending messages from User 1 (server)
            String messageToSend;
            while (!(messageToSend = sc.nextLine()).equalsIgnoreCase("exit")) {
                out.println(userName + ": " + messageToSend); // Send with User 1's name
                System.out.println("You: " + messageToSend); // Print as "You: message" in User 1's terminal
            }

            System.out.println("Chat ended by " + userName + ".");
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
