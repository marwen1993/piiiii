package controller;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mohamed on 3/8/2017.
 */
public class NavigationDrawerFXMLController implements Initializable {

    @FXML
    private Label NameLB;

    @FXML
    private Label usernameLB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NameLB.setText(signInController.name);
        usernameLB.setText(signInController.userName);
    }

    @FXML
    void comunityAction(ActionEvent event){
        new Main().comunityWindow();
    }

    @FXML
    void about(ActionEvent event) {
    }

    @FXML
    void exit(ActionEvent event) {
    }
}
