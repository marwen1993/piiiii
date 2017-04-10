package controller;

import Main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/8/2017.
 */
public class MainWindowController implements Initializable {
    @FXML
    private Pane imagePane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Pane imagePane2;

    @FXML
    private JFXHamburger humburger;

    @FXML
    private Pane imagePane1;

    @FXML
    private Pane imagePane8;

    @FXML
    private Pane imagePane7;

    @FXML
    private Pane imagePane9;

    @FXML
    private Pane imagePane4;

    @FXML
    private Pane imagePane3;

    @FXML
    private Pane imagePane6;

    @FXML
    private Pane imagePane5;

  public static   int x=0;

    @FXML
    private JFXButton zx;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigationDrawerFXML.fxml"));
        AnchorPane pane = loader.load();
        drawer.setSidePane(pane);
        HamburgerBackArrowBasicTransition hamburderTrans = new HamburgerBackArrowBasicTransition(humburger);
        hamburderTrans.setRate(-1);
        humburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hamburderTrans.setRate(hamburderTrans.getRate() * -1);
            hamburderTrans.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }

        });
    } catch (IOException ex) {
        ex.printStackTrace();
    }


        imagePane.setStyle(" -fx-background-image: url(\"/src/game1.jpg\");");
        imagePane1.setStyle(" -fx-background-image: url(\"/src/game2.jpg\");");
        imagePane2.setStyle(" -fx-background-image: url(\"/src/game3.jpg\");");
        imagePane3.setStyle(" -fx-background-image: url(\"/src/game4.jpg\");");
        imagePane4.setStyle(" -fx-background-image: url(\"/src/game5.jpg\");");
        imagePane5.setStyle(" -fx-background-image: url(\"/src/game6.jpg\");");
        imagePane6.setStyle(" -fx-background-image: url(\"/src/game7.jpg\");");
        imagePane7.setStyle(" -fx-background-image: url(\"/src/game8.jpg\");");
        imagePane8.setStyle(" -fx-background-image: url(\"/src/game9.jpg\");");
        imagePane9.setStyle(" -fx-background-image: url(\"/src/game10.jpg\");");
    animation();
    }

    Main main;
    Stage stage;
    public void main(Main main, Stage stage){
        this.main=main;
        this.stage=stage;
    }

    public void animation(){

        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(6), imagePane9);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> {
            TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(1),imagePane8);
            translateTransition.setToX(-1050);
            translateTransition.play();

            translateTransition.setOnFinished(event1 -> {
                FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(4), imagePane7);
                fadeTransition1.setFromValue(1);
                fadeTransition1.setToValue(0);
                fadeTransition1.play();
                fadeTransition1.setOnFinished(event2 -> {
                    TranslateTransition translateTransition1=new TranslateTransition(Duration.seconds(1.5),imagePane6);
                    translateTransition1.setToX(-1500);
                    translateTransition1.play();
                    translateTransition1.setOnFinished(event3 -> {
                        FadeTransition fadeTransition7=new FadeTransition(Duration.seconds(3), imagePane5);
                        fadeTransition7.setFromValue(1);
                        fadeTransition7.setToValue(0);
                        fadeTransition7.play();
                        fadeTransition7.setOnFinished(event4 -> {
                            FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3), imagePane4);
                            fadeTransition2.setFromValue(1);
                            fadeTransition2.setToValue(0);
                            fadeTransition2.play();
                            fadeTransition2.setOnFinished(event5 -> {
                                TranslateTransition translateTransition3=new TranslateTransition(Duration.seconds(1.5),imagePane3);
                                translateTransition3.setToX(1500);
                                translateTransition3.play();
                                translateTransition3.setOnFinished(event6 -> {
                                    FadeTransition fadeTransition8=new FadeTransition(Duration.seconds(3), imagePane2);
                                    fadeTransition8.setFromValue(1);
                                    fadeTransition8.setToValue(0);
                                    fadeTransition8.play();
                                    fadeTransition8.setOnFinished(event7 -> {
                                        FadeTransition fadeTransition3=new FadeTransition(Duration.seconds(3), imagePane1);
                                        fadeTransition3.setFromValue(1);
                                        fadeTransition3.setToValue(0);
                                        fadeTransition3.play();
                                        fadeTransition3.setOnFinished(event8 -> {


                                            FadeTransition fadeTransition4=new FadeTransition(Duration.seconds(3), imagePane1);
                                            fadeTransition4.setFromValue(0);
                                            fadeTransition4.setToValue(1);
                                            fadeTransition4.play();
                                            fadeTransition4.setOnFinished(event0 -> {
                                                FadeTransition fadeTransition11=new FadeTransition(Duration.seconds(3), imagePane2);
                                                fadeTransition11.setFromValue(0);
                                                fadeTransition11.setToValue(1);
                                                fadeTransition11.play();
                                                fadeTransition11.setOnFinished(event00 -> {
                                                    TranslateTransition translateTransition5=new TranslateTransition(Duration.seconds(2),imagePane3);
                                                    translateTransition5.setToX(0);
                                                    translateTransition5.play();
                                                    translateTransition5.setOnFinished(event000 -> {
                                                        FadeTransition fadeTransition5=new FadeTransition(Duration.seconds(3), imagePane4);
                                                        fadeTransition5.setFromValue(0);
                                                        fadeTransition5.setToValue(1);
                                                        fadeTransition5.play();
                                                        fadeTransition5.setOnFinished(event0000 -> {
                                                            FadeTransition fadeTransition10=new FadeTransition(Duration.seconds(3), imagePane5);
                                                            fadeTransition10.setFromValue(0);
                                                            fadeTransition10.setToValue(1);
                                                            fadeTransition10.play();
                                                            fadeTransition10.setOnFinished(event11 -> {
                                                                TranslateTransition translateTransition6=new TranslateTransition(Duration.seconds(2),imagePane6);
                                                                translateTransition6.setToX(0);
                                                                translateTransition6.play();
                                                                translateTransition6.setOnFinished(event51 -> {
                                                                    FadeTransition fadeTransition6=new FadeTransition(Duration.seconds(3), imagePane7);
                                                                    fadeTransition6.setFromValue(0);
                                                                    fadeTransition6.setToValue(1);
                                                                    fadeTransition6.play();
                                                                    fadeTransition6.setOnFinished(event61 -> {
                                                                        TranslateTransition translateTransition0=new TranslateTransition(Duration.seconds(2),imagePane8);
                                                                        translateTransition0.setToX(0);
                                                                        translateTransition0.play();
                                                                        translateTransition0.setOnFinished(event71 -> {
                                                                            FadeTransition fadeTransition0=new FadeTransition(Duration.seconds(3), imagePane9);
                                                                            fadeTransition0.setFromValue(0);
                                                                            fadeTransition0.setToValue(1);
                                                                            fadeTransition0.play();
                                                                            animation();
                                                                        });
                                                                    });
                                                                });
                                                            });
                                                        });
                                                    });
                                                });
                                            });

                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });

    }


   public static String [] actionImages={"preview_uncharted-2-among-thieves.jpg","preview_god-of-war-3.jpg","preview_hitman.jpeg","preview_starcraft-2.jpg","preview_tom-clancys-rainbow-six-siege-patriots.jpg",
   "preview_w2k16-finn-balor.jpg","preview_overwatch-game.jpg","preview_genji-overwatch.jpg","preview_tom-clancys-rainbow-six-siege-ubisoft-montreal.jpg","preview_w2k16-stone-cold.jpg"};
    public static String [] actionNames ={"Uncharted 2: Among Thieves ","God of War 3 ","Hitman ","Starcraft 2 ","Tom Clancy's Rainbow Six Siege Patriots",
    "W2K16 Finn Balor ","Overwatch Game","Genji Overwatch ","Tom Clancy's Rainbow Six Siege Ubisoft Montreal ","W2K16 Stone Cold "};


    public static String [] adventureImages={"preview_overwatch-lineup.jpg","preview_beyond-the-stars.png","preview_rise-of-the-tomb-raider-poster.jpg","preview_lara-croft-rise-of-the-tomb-raider-concept.jpg","preview_lara-croft-rise-of-the-tomb-raider-in-game.jpg",
            "preview_final-fantasy-xv-close-details.jpg","preview_tom-clancys-rainbow-six-siege.jpg","preview_naruto-shippuden-ultimate-ninja-storm-4-poster.jpg","preview_need-for-speed-poster.jpg","preview_for-honor-poster.jpg"};
    public static String [] adventureNames ={"Overwatch Lineup","Beyond The Stars","Rise Of The Tomb Raider ",
            "Lara Croft Rise of The Tomb Raider Concept","Lara Croft Rise of The Tomb Raider In Game",
           "Final Fantasy XV Close Details", "Tom Clancy's Rainbow Six Siege","Naruto Shippuden Ultimate Ninja Storm 4 ","Need For Speed ","For Honor Poster"};



    public static String [] StealthShooterImages={"preview_lara-croft-rise-of-the-tomb-raider-bow-and-arrow.jpg","preview_rise-of-the-tomb-raider-lara-croft-fighting-bear-art.jpg","preview_halo-5-guardian.jpg","preview_halo-5.jpg","preview_call-of-duty-black-ops-iii-fight.jpg",
            "preview_star-wars-battlefront-leaked-alpha.jpg","preview_rise-of-the-tomb-raider-character.jpg","preview_call-of-duty-black-ops-iii-robots.jpg","preview_call-of-duty-black-ops-iii.jpg","preview_call-of-duty-black-ops-iii-quad-tank.jpg"};
    public static String [] StealthShooterNames ={"Lara Croft Rise of The Tomb Raider Bow and Arrow","Rise of the Tomb Raider Lara Croft Fighting Bear Art","Halo 5 Guardian","Halo 5","Call of Duty Black Ops III Fight ",
            "Star Wars Battlefront Leaked Alpha","Rise of the Tomb Raider Character","Call of Duty Black Ops III Robots","Call of Duty Black Ops III","Call of Duty Black Ops III Quad Tank "};

    @FXML
    void actionGameAction(ActionEvent event) {

        x=0;
        Main.gamesType = "Action";
        main.closeMain();
        main.gamesWindow();

    }

    @FXML
    void adventureGamesAction(ActionEvent event) {
        x=1;
        Main.gamesType="Adventure";
        main.closeMain();
        main.gamesWindow();
    }

    @FXML
    void StealthShooterGamesAction(ActionEvent event) {
        x=2;
        Main.gamesType="StealthShooter";
        main.closeMain();
        main.gamesWindow();


    }

    @FXML
    void BackAction(ActionEvent event){
        main.closeMain();
        main.signInWindow();
    }


}
