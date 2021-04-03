package sample;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

    private final static String HOST = "localhost";
    private final static int PORT = 13337;

    public static void main(String[] args) throws IOException {
        Socket s = new Socket(HOST, PORT);
        ClientHandler client = new ClientHandler(s); // TODO: idk make this do something
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        Scanner scan = new Scanner(System.in);
        String keyboard = "";

        while (!keyboard.equals("quit")) {
            /*
            READ:
            DIR
            UPLOAD filename
            DOWNLOAD filename
             */
            keyboard = scan.nextLine();
            out.println(keyboard);
            if (keyboard.equals("DIR")) {
                String fileList = in.readLine();
                String[] fileArray = fileList.split(":");
                for (String value : fileArray) System.out.println(value);
            } else if (keyboard.startsWith("UPLOAD")) {
                System.out.println("Sending file: " + keyboard.replace("UPLOAD ", ""));
            } else if (keyboard.startsWith("DOWNLOAD")) {
                System.out.println("Receiving file: " + keyboard.replace("DOWNLOAD ", ""));
            }
        }
    }
}