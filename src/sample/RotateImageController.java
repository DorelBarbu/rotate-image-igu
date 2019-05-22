package sample;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RotateImageController {
    @FXML
    ImageView originalImage;
    @FXML
    ImageView rotatedImage;

    Image image;

    public void setImage(Image newImage) {
        this.image = newImage;
        originalImage.setImage(newImage);
        rotatedImage.setImage(newImage);
    }
}
