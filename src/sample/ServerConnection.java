package sample;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread {

    Socket s;
    BufferedReader in;
    PrintWriter out;

    boolean shouldRun = true;

    public ServerConnection(Socket socket) throws IOException {
        super("ServerConnectionThread");
        this.s = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            String request = "";

            request = in.readLine();
            System.out.println("REQUEST: " + request);
            if (request.equals("DIR")) {

                //File serverDirectory = new File(Main.serverDir);
                // Send client file list information
                String[] filesList = Main.serverDir.list();
                String fileString = "";
                for (int i=0; i<filesList.length; i++) {
                    fileString += filesList[i];
                    if (i<filesList.length-1) fileString += ":";
                }
                out.println(fileString);

            } else if (request.startsWith("UPLOAD")) {

                // Receive file from client
                System.out.println("Receiving file: " + request.replace("UPLOAD ", ""));
                String fileName = in.readLine(); // IN: FILE NAME
                FileOutputStream fout = new FileOutputStream(Server.DIRECTORY + "\\" + fileName);
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
                File file = new File(request.replace("DOWNLOAD ", ""));
                FileInputStream fin = new FileInputStream(request.replace("DOWNLOAD ", ""));
                OutputStream os = s.getOutputStream();
                int length = (int) file.length();
                byte[] fileBytes = new byte[length];
                fin.read(fileBytes);
                out.println(file.getName()); // OUT: FILE NAME
                out.println(length); // OUT: ARRAY LENGTH
                System.out.println("e");
                os.write(fileBytes, 0, length); // OUT: BYTE ARRAY

                System.out.println("File sent");

        }
    } catch (Exception e) {}

    }

}
