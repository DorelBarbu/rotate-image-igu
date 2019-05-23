package sample;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RotateImageController {
    @FXML
    private ImageView originalImage;
    @FXML
    private ImageView rotatedImage;
    @FXML
    private VBox leftPanel;

    private Image image;

    private sample.Image sampleImage;

    public void setImage(Image newImage) {
        this.image = newImage;
        originalImage.setImage(newImage);
        rotatedImage.setImage(newImage);
    }

    public void setSampleImage(sample.Image sampleImage) {
        this.sampleImage = sampleImage;
    }

    public VBox getLeftPanel() {
        return this.leftPanel;
    }

    public ImageView getRotatedImage() {
        return this.rotatedImage;
    }
}
