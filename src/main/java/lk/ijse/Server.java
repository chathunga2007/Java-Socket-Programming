package lk.ijse;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server Started!");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String msg = dis.readUTF();
            System.out.println("Message from Client: " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
