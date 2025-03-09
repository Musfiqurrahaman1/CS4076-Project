// ClientGUI.java
import javafx.application.Application;
import javafx.stage.Stage;

public class GUIClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ClientController controller = new ClientController();
        ClientView view = new ClientView();
        primaryStage.setTitle("Lecture Scheduler Client");
        primaryStage.setScene(view.createScene(controller));
        primaryStage.show();
    }
}

// OtherController.java, AddLectureController.java, RemoveLectureController.java, ViewScheduleController.java
// These files can be extended as needed for handling different actions.
