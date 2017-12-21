package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(1337);
        } catch (IOException e) {
        }
        System.out.println("Server Started");
        Socket cliente = null;

        while (true) {
            try {
                System.out.println("Waiting for connection...");
                cliente = serverSocket.accept();
            } catch (IOException e) {

            }
            System.out.println("Client connected at " + cliente.getRemoteSocketAddress());
            new Thread(new ClienteHandle(cliente)).start();
        }
    }
}
