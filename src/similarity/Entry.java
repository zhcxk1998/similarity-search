package similarity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author BB小天使
 * @author Yumen
 * @version 1.0
 */
public class Entry extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Fa学研究小组");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/logo.png")));
        Scene scene=new Scene(root);
        scene.getStylesheets().add("similarity/css/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
