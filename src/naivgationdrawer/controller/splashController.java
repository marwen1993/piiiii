package naivgationdrawer.controller;

import naivgationdrawer.controller.NaivgationDrawer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
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


    NaivgationDrawer main;
    Stage stage;
    public void main(NaivgationDrawer main, Stage stage){
        this.main=main;
        this.stage=stage;
    }

    @FXML
    AnchorPane splashAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


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
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(6), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();

                        fadeout.setOnFinished(e ->{
                            main.splashStage.close();
                            main.mainWindow();

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
