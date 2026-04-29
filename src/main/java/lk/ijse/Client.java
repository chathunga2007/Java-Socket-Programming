package lk.ijse;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket remoteSocket = new Socket("127.0.0.1", 3000);
            DataOutputStream dos = new DataOutputStream(remoteSocket.getOutputStream());
            dos.writeUTF("Hello Server!");
            dos.flush();
            remoteSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}