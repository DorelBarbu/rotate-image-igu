package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectImageController {

    private Image image;

    @FXML
    ImageView imageView;

    @FXML
    Button nextButton;

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
                imageView.setImage(image);
                /* Enable the next button */
                nextButton.setDisable(false);
            } catch(IOException e) {
                System.out.println("Cannot read image");
            }

        }

    }

    @FXML
    public void handlePressNext(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            /* Open the RotateImage screen */
            fxmlLoader.setLocation(getClass().getResource("RotateImage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 772, 520);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
            /* Set the image to the rotate image screen */
            RotateImageController rotateImageController = fxmlLoader.getController();
            rotateImageController.setImage(this.image);
        } catch(IOException e) {
            System.out.println("Cannot open rotate image screen");
        }
    }
}
