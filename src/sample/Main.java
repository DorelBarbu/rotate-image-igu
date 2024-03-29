package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AcceptLicenceScreen.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        primaryStage.setTitle("Licence Agreement");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
        ((AcceptLicenceScreenController) fxmlLoader.getController()).setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
