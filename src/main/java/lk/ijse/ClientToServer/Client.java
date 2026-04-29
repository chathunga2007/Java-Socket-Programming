package lk.ijse.ClientToServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Socket remoteSocket = new Socket("127.0.0.1", 3000);
            DataOutputStream dos = new DataOutputStream(remoteSocket.getOutputStream());
            System.out.print("Enter your msg: ");
            String msg = sc.nextLine();
            dos.writeUTF(msg);
            dos.flush();
            remoteSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}