package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectImageController {

    @FXML
    ImageView imageView;

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
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
            } catch(IOException e) {
                System.out.println("Cannot read image");
            }

        }

    }
}
