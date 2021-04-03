package sample;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final static int PORT = 13337;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(4);

    private final static String FILENAME = "C:\\Downloads\\test.txt";
    private final static String DIRECTORY = "C:\\Downloads\\SERVER";

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("Waiting for client");
        Socket s = ss.accept();
        System.out.println("Client accepted");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        //DataInputStream din = new DataInputStream(s.getInputStream());
        //DataOutputStream dout = new DataOutputStream(s.getOutputStream());

//        File file = new File(FILENAME);
//        FileInputStream fin = new FileInputStream(FILENAME);
        //FileOutputStream fout = new FileOutputStream("");

//        int length = (int) file.length();
//        byte[] b = new byte[length];
//        fin.read(b, 0, b.length);
//        OutputStream os = s.getOutputStream();
//        os.write(b.length);
//        os.write(b, 0, b.length);

//        System.out.println("FILE SENT");

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
                // TODO: Receive file from client
                String fileName = in.readLine();
                FileOutputStream fout = new FileOutputStream(DIRECTORY+"\\"+fileName);
                File file = new File(DIRECTORY+"\\"+fileName);
                int length = (int) file.length();
                byte[] fileBytes = new byte[length];
                s.getInputStream().read(fileBytes);
                fout.write(fileBytes);
                System.out.println("File received");
            } else if (request.startsWith("DOWNLOAD")) {

                // Send file to client
                System.out.println("Sending file: " + request.replace("DOWNLOAD ", ""));
                // TODO: Send file to client
                File file = new File(request.replace("DOWNLOAD ",""));
                FileInputStream fin = new FileInputStream(request.replace("DOWNLOAD ", ""));
                int length = (int) file.length();
                byte[] fileBytes = new byte[length];
                fin.read(fileBytes);
                out.println(file.getName());
                s.getOutputStream().write(length);
                s.getOutputStream().write(fileBytes);
                System.out.println("File sent");
            }
        }
    }
}