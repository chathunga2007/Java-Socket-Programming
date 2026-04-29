package lk.ijse.ServerToClient;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3306);
            System.out.println("Client Started!");

            Socket socket = serverSocket.accept();
            System.out.println("Server Connected!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String msg = dis.readUTF();
            System.out.println("Message from Server: " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
