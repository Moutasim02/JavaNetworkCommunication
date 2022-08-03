package tcp_socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() throws Exception { // General Exception is strictly prohibited -> Hard to debug
        // ServerSocket: hold port in the operating system
        ServerSocket serverSocket = new ServerSocket(2022);
        System.out.println("Port " + serverSocket.getLocalPort() + " is now open");

        Socket socket = serverSocket.accept(); // blocking method to accept connection
                                                // (Doesn't continue to next lines unless connection established/ throw error)
        System.out.println("Client in " + socket.getInetAddress() + " has connected");

        // I/O Buffers:
        BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream())); // input: InputStreamReader -> Make the text human-readable
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // output

        out_socket.println("Welcome!"); // send to client
        String message = in_socket.readLine();
        System.out.println("Client says: " + message);

        serverSocket.close();
        socket.close();
        in_socket.close();
        out_socket.close();
        System.out.println("Closed!");
    }

    public static void main(String[] args) {

        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
