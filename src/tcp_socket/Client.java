package tcp_socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public Client() throws Exception {
        Socket socket = new Socket("localhost", 2022); // IP address and Port number
        if (socket.isConnected()) {
            System.out.println("Successful Connection!");
        }

        // I/O Buffers
        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // true: auto-flush: Send even if buffer is full

        // Protocol: Set of Rules
        String message = in_socket.readLine();
        System.out.println("Server says: " + message);
        out_socket.println("Thanks! See you");


        socket.close();
        System.out.println("Socket closed!");
    }

    public static void main(String[] args) {

        try {
            new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
