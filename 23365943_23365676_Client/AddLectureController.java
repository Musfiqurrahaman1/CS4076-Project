public class AddLectureController {
    private ClientController clientController;

    public AddLectureController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void addLecture(String details, javafx.scene.control.TextArea responseArea) {
        clientController.sendRequest("Add," + details, responseArea);
    }
}