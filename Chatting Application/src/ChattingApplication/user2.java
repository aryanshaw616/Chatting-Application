package ChattingApplication;

import java.io.*;
import java.net.*;
import java.util.*;

public class user2 {

    private static Socket sock;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take User 2's name as input
        System.out.print("Enter your name (User 2): ");
        String userName = sc.nextLine();  // Name of User 2 (client)

        try {
            // Connect to the server (User 1)
            sock = new Socket("localhost", 1234);
            System.out.println("Connected to User 1.");

            // Initialize I/O streams
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);

            // Thread to handle receiving messages from User 1 (server)
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);  // Show User 1's name and message
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Handle sending messages from User 2 (client)
            String msgToSend;
            while (!(msgToSend = sc.nextLine()).equalsIgnoreCase("exit")) {
                out.println(userName + ": " + msgToSend); // Send with User 2's name
            }

            System.out.println("Chat ended by " + userName + ".");
            sock.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
