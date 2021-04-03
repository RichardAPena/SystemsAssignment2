package sample;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private DataInputStream din;
    private DataOutputStream dout;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        din = new DataInputStream(client.getInputStream());
        dout = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // in.close();
            // out.close();
        }
    }
}
