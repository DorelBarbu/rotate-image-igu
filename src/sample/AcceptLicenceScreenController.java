package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;


import java.io.IOException;

public class AcceptLicenceScreenController {
    Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private Button cancelButton;

    @FXML
    private Button nextButton;

    @FXML
    private CheckBox termsCheckbox;

    @FXML
    private void handlePressCancel(ActionEvent event) {
        System.out.println("Cancel button pressed");
        /* Close the current scene */
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();

    }

    @FXML
    private void handlePressNext(ActionEvent event) {
        System.out.println("Next button pressed");
        /*Open the select image screen*/
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SelectImage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            ((SelectImageController)fxmlLoader.getController()).setStage(this.primaryStage);
            stage.show();
        } catch(IOException e) {
            System.out.println("ERROR: Cannot open the SelectImage screen");
        }
        /* Close the current scene */
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    private void handleClickCheckbox(ActionEvent event) {
        if(termsCheckbox.isSelected() == true) {
            nextButton.setDisable(false);
        } else {
            nextButton.setDisable(true);
        }
    }
}
