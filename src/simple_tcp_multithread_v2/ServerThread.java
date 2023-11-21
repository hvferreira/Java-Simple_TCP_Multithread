package simple_tcp_multithread_v2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    private ServerMain server_main;

    public ServerThread (Socket socket, ServerMain server_main) {
        this.socket = socket;
        this.server_main = server_main;
    }

    @Override
    public void run() {
        try {

            int client_number = server_main.getClientNumber();

            System.out.println("Client " + client_number + ". has connected.");

            // I/O buffers:
            BufferedReader in_socket = new BufferedReader(new InputStreamReader (socket.getInputStream()));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter (socket.getOutputStream()), true);

            // SOLUTION
            String message = "message"; // any string will do here
            String backwards = "message"; // any string will do here too

            while(!(message.equalsIgnoreCase("EXIT"))) { // as long as the (previous) message is not "EXIT" (or "exit"), get into the loop
                message = in_socket.readLine(); // read the newest message from the socket
                out_socket.println(backwards = new StringBuffer(message).reverse().toString()); // reverse the received string and send it back
            }
            // SOLUTION

            socket.close();
            System.out.println("Client " + client_number + ". has disconnected.");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
