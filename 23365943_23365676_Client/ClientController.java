// ClientController.java
import java.io.*;
import java.net.*;
import javafx.scene.control.TextArea;

public class ClientController {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    public ClientController() {
        try {
            socket = new Socket("localhost", 1234);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String request, TextArea responseArea) {
        output.println(request);
        try {
            String response = input.readLine();
            responseArea.appendText(response + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
