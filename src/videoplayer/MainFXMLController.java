package videoplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MainFXMLController implements Initializable {
    @FXML
    private Button playBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label timeLbl;
    @FXML
    private AnchorPane mainAnchorPane;
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Timeline t;
    private boolean enabledControls, playing;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enabledControls = false;
        playing = false;
        
        volumeSlider.setFocusTraversable(false);
        seekSlider.setFocusTraversable(false);
        
        file = new File(VideoPlayer.SOURCE);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        
        mediaView.setOnMousePressed(e -> {
                enableControls();
                if(playing) pauseMP();
                else playMP();
            });
        
        mediaPlayer.setVolume(0.05);
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(enabledControls && VideoPlayer.keyPressed != null) {
                    switch(VideoPlayer.keyPressed) {
                        case UP:
                            volumeSlider.setValue(volumeSlider.getValue() + 1);
                            changeVolume(null);
                            break;
                        case DOWN:
                            volumeSlider.setValue(volumeSlider.getValue() - 1);
                            changeVolume(null);
                            break;
                        case RIGHT:
                            seekSlider.setValue(seekSlider.getValue() + 5000);
                            seekTime(null);
                            break;
                        case LEFT:
                            seekSlider.setValue(seekSlider.getValue() - 5000);
                            seekTime(null);
                            break;
                        case SPACE:
                            if(playing) pauseClick(null);
                            else playClick(null);
                            break;
                    }
                }
            }
        }.start();
    }

    @FXML
    private void playClick(ActionEvent event) {
        if(!playing) {
            playing = true;
            mediaPlayer.play();
            seekSlider.setMax(mediaPlayer.getTotalDuration().toMillis());
            t = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                seekSlider.setValue(mediaPlayer.getCurrentTime().toMillis());
                timeLbl.setText(returnTime(mediaPlayer.getCurrentTime().toSeconds()) + "/" + returnTime(mediaPlayer.getTotalDuration().toSeconds()));
            }));
            t.setCycleCount(Animation.INDEFINITE);
            t.play();
        }
    }

    @FXML
    private void pauseClick(ActionEvent event) {
        if(playing) {
            playing = false;
            mediaPlayer.pause();
        }
    }

    @FXML
    private void changeVolume(MouseEvent event) {
        mediaPlayer.setVolume(volumeSlider.getValue() / 100);
    }

    @FXML
    private void seekTime(MouseEvent event) {
        mediaPlayer.seek(Duration.millis(seekSlider.getValue()));
    }
    
    private String returnTime(double tSeconds) {
        double hours = Math.floor(tSeconds / 3600);
        double minutes = Math.floor((tSeconds % 3600) / 60);
        double seconds = tSeconds % 60;

        return String.format("%01d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
    }

    private void enableControls() {
        if(!enabledControls) {
            enabledControls = true;
            System.out.println("ENABLED");
        }
    }

    @FXML
    private void disableControls() {
        if(enabledControls) {
            enabledControls = false;
            System.out.println("DISABLED");
        }
    }
    
    public void pauseMP() {
        pauseClick(null);
    }
    
    public void playMP() {
        playClick(null);
    }
}
