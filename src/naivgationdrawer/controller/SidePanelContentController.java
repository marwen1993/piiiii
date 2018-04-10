package naivgationdrawer.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton b4;
    @FXML
    private JFXButton b5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void changeColor(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
           case "categorie":
            {
                Parent p1 = FXMLLoader.load(getClass().getResource("FXMLCategorie.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                
                App1.show();
            }
                break;
            
            case "categorie parent":
            {   Parent p1 = FXMLLoader.load(getClass().getResource("FXMLCatecorieparent.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
//                String css = this.getClass().getResource("categorie.css").toExternalForm();
//        test1.getStylesheets().add(css);
                App1.show();}
                break;
            case "produit":
            { 
                Parent p1 = FXMLLoader.load(getClass().getResource("FXMLProduit.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();}
                break;
            case "afficher les produit par prix":
            { 
                Parent p1 = FXMLLoader.load(getClass().getResource("FXMLAficherproduittrier.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();}
                break;    
            case "afficher les produit par categorie selon le plus bas prix":
            { 
                
                Parent p1 = FXMLLoader.load(getClass().getResource("FXMLAfficherproduitcategorie.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                App1.show();}
                break;    
                
                
        }
        
    }

    @FXML
    private void exit(ActionEvent event) {
        try {
        Parent niv1_parent = FXMLLoader.load(getClass().getResource("/view/FXML_AdminHome"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
