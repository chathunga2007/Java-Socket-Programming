package lk.ijse.SimpleChatApplication;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.Socket;

public class ClientController {
    @FXML private TextArea chatArea;
    @FXML private TextField inputField;

    private DataOutputStream out;
    private DataInputStream in;

    public void initialize() {
        // Run network listening in a separate thread to keep UI responsive
        new Thread(() -> {
            try {
                // Connect to server using IP and Port
                Socket socket = new Socket("127.0.0.1", 3000);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                Platform.runLater(() -> chatArea.appendText("Connected to Server!\n"));

                while (true) {
                    String msg = in.readUTF();
                    // Update UI safely on the JavaFX Application Thread
                    Platform.runLater(() -> chatArea.appendText("Server: " + msg + "\n"));
                }
            } catch (IOException e) {
                Platform.runLater(() -> chatArea.appendText("Connection failed or lost.\n"));
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
                chatArea.setText("Me: " + msg + "\n");
                inputField.clear();
            }
        } catch (IOException e) {
            chatArea.setText("Error: Could not send message.\n");
        }
    }
}