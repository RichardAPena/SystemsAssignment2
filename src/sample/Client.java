package sample;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

    private final static String HOST = "localhost";
    private final static int PORT = 13337;

    //private final static String FILENAME = "C:\\Downloads\\afafa.txt";
    private final static String DIRECTORY = "C:\\Downloads\\CLIENT";

    public static void main(String[] args) throws IOException {
        Socket s = new Socket(HOST, PORT);
        //ClientHandler client = new ClientHandler(s); // TODO: idk make this do something
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

                // Receive file list information from server
                String fileList = in.readLine();
                String[] fileArray = fileList.split(":");
                for (String value : fileArray) System.out.println(value);

            } else if (keyboard.startsWith("UPLOAD")) {

                // Send file to server
                System.out.println("Sending file: " + keyboard.replace("UPLOAD ", ""));
                File file = new File(keyboard.replace("UPLOAD ",""));
                FileInputStream fin = new FileInputStream(keyboard.replace("UPLOAD ",""));
                OutputStream os = s.getOutputStream();
                int length = (int) file.length();
                byte[] fileBytes = new byte[length];
                fin.read(fileBytes);
                out.println(file.getName()); // OUT: FILE NAME
                out.println(length); // OUT: ARRAY LENGTH
                os.write(fileBytes); // OUT: BYTE ARRAY
                System.out.println("File sent");

            } else if (keyboard.startsWith("DOWNLOAD")) {

                // Receive file from server
                System.out.println("Receiving file: " + keyboard.replace("DOWNLOAD ", ""));
                String fileName = in.readLine(); // IN: FILE NAME
                FileOutputStream fout = new FileOutputStream(DIRECTORY+"\\"+fileName);
                InputStream is = s.getInputStream();
                int length = Integer.parseInt(in.readLine()); // IN: ARRAY LENGTH
                System.out.println("LENGTH " + length);
                byte[] fileBytes = new byte[length];
                is.read(fileBytes); // IN: BYTE ARRAY
                fout.write(fileBytes, 0, length);
                System.out.println("File received");

            }
        }
    }
}