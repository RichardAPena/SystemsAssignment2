package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Server {

    private final static int PORT = 13337;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("Waiting for client");
        Socket client = listener.accept();
        System.out.println("Client connected");
        ClientHandler clientThread = new ClientHandler(client);
        clients.add(clientThread);

        pool.execute(clientThread);
    }

    public static String test() {
        return "suh";
    }
}
