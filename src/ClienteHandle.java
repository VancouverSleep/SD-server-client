import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandle implements Runnable {
    private Socket cliente;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteHandle(Socket cliente) {
        this.cliente = cliente;
        try {
            out = new PrintWriter(cliente.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {
        String line = null;
        out.println("You have been connected to the server");

        try {
            while ((line = in.readLine()) != null) {
                if(line.equalsIgnoreCase("kill")) {
                    try {
                        endConnection();
                    } catch (IOException e) {
                        System.out.println("Cliente closed Connection");
                    }
                }
                System.out.println(line);
                out.println("Server received: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void endConnection() throws IOException {
        cliente.close();
    }
}
