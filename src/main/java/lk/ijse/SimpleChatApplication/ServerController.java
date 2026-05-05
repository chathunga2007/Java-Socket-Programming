package lk.ijse.SimpleChatApplication;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    @FXML private TextArea chatArea;
    @FXML private TextField inputField;

    private DataOutputStream out;

    public void initialize() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(3000)) {
                Platform.runLater(() -> chatArea.appendText("Server started. Waiting for client...\n"));

                // This blocks the background thread, not the UI thread
                Socket socket = serverSocket.accept();
                Platform.runLater(() -> chatArea.appendText("Client connected!\n"));

                out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                while (true) {
                    String message = in.readUTF();
                    Platform.runLater(() -> chatArea.appendText("Client: " + message + "\n"));
                }
            } catch (IOException e) {
                Platform.runLater(() -> chatArea.appendText("Server Error: " + e.getMessage() + "\n"));
            }
        }).start();
    }

    @FXML
    private void sendMessage() {
        try {
            String msg = inputField.getText().trim();
            if (out != null && !msg.isEmpty()) {
                out.writeUTF(msg);
                out.flush();
                chatArea.appendText("Me: " + msg + "\n");
                inputField.clear();
            }
        } catch (IOException e) {
            chatArea.appendText("Error: Could not send reply.\n");
        }
    }
}