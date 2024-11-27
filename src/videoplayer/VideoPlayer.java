package videoplayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VideoPlayer extends Application {
    public static String SOURCE = "";
    
    public static KeyCode keyPressed;
    public static Stage primStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("appCSS.css").toExternalForm());
            
            root.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                keyPressed = e.getCode();
            });
            
            root.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
                keyPressed = null;
            });
            
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    keyPressed = null;
                }
            }.start();
            
            primStage.setTitle("VideoPlayer");
            primStage.setScene(scene);
            primStage.show();
            primStage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
