package sample;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    private final static int PORT = 4999;
    public final static String DIRECTORY = "C:\\Downloads\\SERVER";
    private static ArrayList<ServerConnection> connections = new ArrayList<>();
    private static boolean shouldRun = true;

    public static void main(String[] args) throws IOException { // main
        ServerSocket ss = new ServerSocket(PORT);

        System.out.println("Waiting for client");
        while (shouldRun) { // Waits for a client and keeps accepting them
            Socket s = ss.accept();
            System.out.println("Client accepted");
            ServerConnection sc = new ServerConnection(s);
            sc.start();
            connections.add(sc);
        }
    }
}