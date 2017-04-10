package controller;

import DataBase.DataSource;
import DataBase.SendMessage;
import Interface.userInterface;
import Main.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.userModel;
import service.userService;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/8/2017.
 */
public class signupFXMLConroller implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField email;

    @FXML
    private AnchorPane rootPane;

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
        dataSource=new DataSource();
        userService=new userService();


    }



    public void showSnackBar(String msg){
        JFXSnackbar snackbar=new JFXSnackbar(rootPane);
        snackbar.setAlignment(Pos.BOTTOM_RIGHT);
        snackbar.setPrefHeight(30);
        snackbar.setPrefWidth(rootPane.getPrefWidth());
        snackbar.show(msg,4000);
    }


    @FXML
    void regist(ActionEvent event) {

        if (fullName.getText().isEmpty()==true){
            showSnackBar("please enter your full name");
        }else if (userName.getText().isEmpty()==true){
            showSnackBar("please enter your username");

        }
        else if (email.getText().isEmpty()==true){
            showSnackBar("please enter your email");
        }
        else if (password.getText().isEmpty()==true){
            showSnackBar("please enter your password");

        }else {
            try {
                String sqlSelect="SELECT * FROM games.user WHERE username='"+userName.getText()+"'";
                statement=dataSource.getConnection().prepareStatement(sqlSelect);

                result=statement.executeQuery();

                if (result.next()){
                    if (result.getString("email").equals(email.getText())){
                        showSnackBar("your username and email are already exist");
                    }else {
                        showSnackBar("username is already exist");
                    }
                }else if (!result.next()){
                       // insert(fullName.getText(),userName.getText(),email.getText(),password.getText());
                    userModel userModel=new userModel("",fullName.getText(),userName.getText(),email.getText(),password.getText(),"","","","");
                    userService.insertUser(userModel);
                        showSnackBar("CONGRATS , "+userName.getText()+" has been added successfuly .");

                        fullName.setText("");
                        userName.setText("");
                        password.setText("");
                        email.setText("");
                        main.signInWindow();
                        main.closeSignUp();
                        SendMessage ms = new SendMessage();
                        ms.sendMessageCode("29688045", "");

                }

            }catch (SQLException e){
                e.printStackTrace();
            }


        }


    }

    @FXML
    void cancelAction(ActionEvent event) {
        main.closeSignUp();
        main.signInWindow();
    }

}
