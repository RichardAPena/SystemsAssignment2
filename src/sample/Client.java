package sample;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

    private final static String HOST = "localhost";
    private final static int PORT = 13337;

    private final static String FILENAME = "C:\\Downloads\\afafa.txt";
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(HOST, PORT);
        //ClientHandler client = new ClientHandler(s); // TODO: idk make this do something
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        //FileInputStream fin = new FileInputStream();
        //fin.read(b, 0, b.length);

        InputStream is = s.getInputStream();
        FileOutputStream fout = new FileOutputStream(FILENAME);
        int arrLength = is.read();
        System.out.println(arrLength);
        byte[] b = new byte[arrLength];
        is.read(b);
        fout.write(b);

        Scanner scan = new Scanner(System.in);
        String keyboard = "";

            /*
            while (!keyboard.equals("quit")) {

                // READ:
                // DIR
                // UPLOAD filename
                // DOWNLOAD filename

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

             */
    }
}