package controller;



import Main.Main;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class splashController implements Initializable {


    Main main;
    Stage stage;
    public void main(Main main, Stage stage){
        this.main=main;
        this.stage=stage;
    }
    @FXML
    private ImageView imageView;

    @FXML
    AnchorPane splashAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(1),imageView);
        translateTransition.setToY(-300);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(2.5),imageView);
            translateTransition1.setToY(0);
            translateTransition1.play();
        });

        new splash().start();
    }

    class splash extends Thread{

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1500);
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(3), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0.3);
                        fadeout.setCycleCount(1);
                        fadeout.play();

                        fadeout.setOnFinished(e ->{
                            main.splashStage.close();
                            main.signInWindow();

                        });

                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

    }

}
