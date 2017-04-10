package controller;

import DataBase.DataSource;
import Main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.userService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class signInController  implements Initializable{

    @FXML
    private JFXButton signinBTN;

    @FXML
    private JFXButton signupBTN;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXButton forgotPasswordBRN;

    @FXML
    private ImageView logoImage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXPasswordField passwordTF;

    public static String userName="",createPer="",name="";
    public static int commentper;

    Main main;
    Stage stage;
        public void main(Main main, Stage stage){
            this.main=main;
            this.stage=stage;
        }

    DataSource dataSource;
    PreparedStatement statement=null;
    ResultSet result;
    userService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSource=  new DataSource();
        userService=new userService();

        RotateTransition rotateTransition=new RotateTransition(Duration.seconds(122), logoImage);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(10*720);
        rotateTransition.play();

    }


    @FXML
    void signinAction(ActionEvent event) {
        userService.signin(usernameTF.getText(),passwordTF.getText(),main,this);

    }

    @FXML
    void signupAction(ActionEvent event) {
            main.closeSignIn();
            main.signUpWindow();
    }

    public void showSnackBar(String msg){
        JFXSnackbar snackbar=new JFXSnackbar(rootPane);
        snackbar.setAlignment(Pos.BOTTOM_RIGHT);

        snackbar.setPrefHeight(30);
        snackbar.setPrefWidth(rootPane.getPrefWidth());
        snackbar.show(msg,4000);
    }

}
