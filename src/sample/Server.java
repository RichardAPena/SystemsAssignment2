package sample;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT = 13337;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("Waiting for client");
        Socket s = ss.accept();
        System.out.println("Client accepted");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String request = "";

        while (!request.equals("quit")) {
            /*
            RESPOND TO:
            DIR
            UPLOAD filename
            DOWNLOAD filename
             */
            request = in.readLine();
            System.out.println("REQUEST: " + request);
            if (request.equals("DIR")) {
                out.println("bruh.txt:mihai.txt:e.png");
            } else if (request.startsWith("UPLOAD")) {
                System.out.println("Receiving file: " + request.replace("UPLOAD ", ""));
            } else if (request.startsWith("DOWNLOAD")) {
                System.out.println("Sending file: " + request.replace("DOWNLOAD ", ""));
            }
        }
    }
}