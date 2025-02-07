This is a chat application built in Java, allowing two users (User 1 and User 2) to communicate through a server-client connection using sockets. The application consists of two main parts: the server and the client.

The server (User 1) waits for a connection from the client (User 2) and is responsible for receiving and sending messages. Once User 2 connects, both users can chat in real-time by sending messages back and forth.

Each user provides their name at the start of the conversation, which is displayed alongside their messages. If either user types "exit," the chat session will end.

To run the application, the server starts by listening on port 1234 for an incoming connection. Once the client connects, they can exchange messages until one of them decides to exit the chat. The application requires Java 8 for communication between the server and client works.
