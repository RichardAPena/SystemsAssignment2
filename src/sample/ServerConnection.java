package sample;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    //FileInputStream fin;
    //FileOutputStream fout;
    //DataInputStream din;
    //DataOutputStream dout;
    boolean shouldRun = true;

    public ServerConnection(Socket socket) throws IOException {
        super("ServerConnectionThread");
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        //fin = new FileInputStream(socket.getInputStream());
        //fout = new FileOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {

            //din = new DataInputStream(socket.getInputStream());
            //dout = new DataOutputStream(socket.getOutputStream());

            //while (shouldRun) {
                //while (din.available() == 0) {
                //    Thread.sleep(1);
                //}
                //String textIn = din.readUTF();
                //sendStringToClient(textIn);
            //}
            //din.close();
            //dout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
