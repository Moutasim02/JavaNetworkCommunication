package tcp_socket.with_cli_interactivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client() throws Exception {
        Socket socket = new Socket("localhost", 2022); // IP address and Port number
        if (socket.isConnected()) {
            System.out.println("Successful Connection!");
        }

        // I/O Buffers
        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); // true: auto-flush: Send even if buffer is full
        Scanner sc = new Scanner(System.in);

        String userNumber;
        try {
            while ((in_socket.readLine()).startsWith("Guess")) {
                System.out.println("Server says: Guess a number in range of [1 - 10]");
                userNumber = sc.nextLine();
                out_socket.println(userNumber);
            }
        } catch (NullPointerException e) {
            System.out.println("Can't send null value to server!");
        }


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
