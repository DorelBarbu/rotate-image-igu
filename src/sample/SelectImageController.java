package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectImageController {
    Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    private Image image;

    private String imagePath;

    @FXML
    private ImageView imageView;

    @FXML
    private Button nextButton;

    @FXML
    private Button displayInfoButton;

    @FXML
    public void handlePressBrowse(ActionEvent event) {
        System.out.println("Browse button pressed");
        /* Open a new file chooser */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        /* Save the chosen file */
        File file = fileChooser.showOpenDialog(null);
        if(file == null) {
            System.out.println("File button cancled");
        } else {
            System.out.println("Chosen: " + file.getAbsolutePath());
            try {
                /* Display the selected image in the ImageView */
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.image = image;
                this.imagePath = file.getAbsolutePath();
                imageView.setImage(image);
                /* Enable the next button */
                nextButton.setDisable(false);
                /* Enable the display info button */
                displayInfoButton.setDisable(false);
            } catch(IOException e) {
                System.out.println("Cannot read image");
            }

        }

    }

    @FXML
    public void displayInfo(ActionEvent event) {
        Stage stage = this.primaryStage;
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        TableView table = new TableView();

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handlePressNext(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            /* Open the RotateImage screen */
            fxmlLoader.setLocation(getClass().getResource("RotateImage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 758, 511);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
            /* Set the image to the rotate image screen */
            RotateImageController rotateImageController = fxmlLoader.getController();
            rotateImageController.setImage(this.image);
            /* Set the sample.Image for the RotateScreen */
            rotateImageController.setSampleImage(new sample.Image(this.imagePath));
            /* Create a dropdown for selecting the rotation option */
            final ComboBox rotateOptionComboBox = new ComboBox();
            rotateOptionComboBox.getItems().addAll(
                    "Clockwise 90",
                    "Clockwise 180",
                    "Counterclockwise 90",
                    "Counterclockwise 180"
            );
            rotateOptionComboBox.setPromptText("Select a rotate option");
            /* Add the dropdown to the rotate image screen */
            rotateImageController.getLeftPanel().getChildren().add(rotateOptionComboBox);
            /* Create a rotate button */
            Button rotateButton = new Button();
            rotateButton.setText("Rotate");
            rotateButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("Rotate button clicked");
                    if(rotateOptionComboBox.getValue() != null) {
                        String rotateValue = rotateOptionComboBox.getValue().toString();
                        ImageView rotatedImage = rotateImageController.getRotatedImage();
                        rotatedImage.setRotate(0);
                        switch (rotateValue) {
                            case "Clockwise 90":
                                rotatedImage.setRotate(90);
                                break;
                            case "Clockwise 180":
                                rotatedImage.setRotate(180);
                                break;
                            case "Counterclockwise 90":
                                rotatedImage.setRotate(-90);
                                break;
                            case "Counterclockwise 180":
                                rotatedImage.setRotate(-180);
                                break;
                        }
                    }

                }
            });
            /* Add the rotate butotn to the screen */
            rotateImageController.getLeftPanel().getChildren().add(rotateButton);

        } catch(IOException e) {
            System.out.println("Cannot open rotate image screen");
        }
    }
}
