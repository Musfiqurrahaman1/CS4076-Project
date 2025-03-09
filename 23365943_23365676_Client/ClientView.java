// ClientView.java
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;


public class ClientView {
    public Scene createScene(ClientController controller) {
        Label label = new Label("Enter Request (Add, Remove, Display):");
        TextField requestField = new TextField();
        Button sendButton = new Button("Send");
        TextArea responseArea = new TextArea();
        Button stopButton = new Button("STOP");

        sendButton.setOnAction(e -> controller.sendRequest(requestField.getText(), responseArea));
        stopButton.setOnAction(e -> controller.sendRequest("STOP", responseArea));

        return new Scene(new VBox(10, label, requestField, sendButton, responseArea, stopButton), 400, 300);
    }
}
