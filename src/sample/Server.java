package sample;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT = 4999;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    private static ArrayList<ServerConnection> connections = new ArrayList<>();

    //private final static String FILENAME = "C:\\Downloads\\test.txt";
    private final static String DIRECTORY = "C:\\Downloads\\SERVER";
    private static boolean shouldRun;

    //boolean shouldRun = true;

    public static void main(String[] args) throws IOException { // main
        ServerSocket ss = new ServerSocket(PORT);

        try {
            System.out.println("Waiting for client");
            while (shouldRun) {
                Socket s = ss.accept();
                ServerConnection sc = new ServerConnection(s);
                System.out.println("Client accepted");
                connections.add(sc);
            }
        } catch (Exception e) {}

        // =============================== STUFF BELOW

        System.out.println("Waiting for client");
        Socket s = ss.accept();
        System.out.println("Client accepted");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

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

                // Send client file list information
                out.println("bruh.txt:mihai.txt:e.png");

            } else if (request.startsWith("UPLOAD")) {

                // Receive file from client
                System.out.println("Receiving file: " + request.replace("UPLOAD ", ""));
                String fileName = in.readLine(); // IN: FILE NAME
                FileOutputStream fout = new FileOutputStream(DIRECTORY+"\\"+fileName);
                InputStream is = s.getInputStream();
                int length = Integer.parseInt(in.readLine()); // IN: ARRAY LENGTH
                System.out.println("LENGTH: " + length);
                byte[] fileBytes = new byte[length];
                is.read(fileBytes); // IN: BYTE ARRAY
                fout.write(fileBytes, 0, length);
                System.out.println("File received");

            } else if (request.startsWith("DOWNLOAD")) {

                // Send file to client
                System.out.println("Sending file: " + request.replace("DOWNLOAD ", ""));
                File file = new File(request.replace("DOWNLOAD ",""));
                FileInputStream fin = new FileInputStream(request.replace("DOWNLOAD ", ""));
                OutputStream os = s.getOutputStream();
                int length = (int) file.length();
                byte[] fileBytes = new byte[length];
                fin.read(fileBytes);
                out.println(file.getName()); // OUT: FILE NAME
                out.println(length); // OUT: ARRAY LENGTH
                System.out.println("e");
                os.write(fileBytes,0,length); // OUT: BYTE ARRAY

                System.out.println("File sent");
            }
        }
    } // main
}