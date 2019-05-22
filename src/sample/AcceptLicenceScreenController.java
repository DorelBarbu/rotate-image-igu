package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AcceptLicenceScreenController {
    @FXML
    private Button cancelButton;

    @FXML
    private void handlePressCancel(ActionEvent event) {
        System.out.println("Cancel button pressed");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
