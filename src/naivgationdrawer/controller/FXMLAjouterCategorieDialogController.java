/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naivgationdrawer.controller;

import naivgationdrawer.Entite.Categorie;
import naivgationdrawer.Entite.Categorie_parent;
import naivgationdrawer.Services.ICategorie_parent;
import naivgationdrawer.connection.Database;
import naivgationdrawer.connection.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXMLAjouterCategorieDialogController implements Initializable {

    @FXML private Text actiontarget;
   // @FXML
   // private TextField id;
    @FXML
    private ComboBox categorie_parent_id;
    @FXML
    private TextField nom;
    @FXML
    private Button confirmer;
    @FXML
    private Button cancel;
    
    
    
     private ArrayList<Categorie_parent> listCategorie_parent;
    
    private ObservableList<Categorie_parent> observableListCategorie_parent;
 

    //Atributos para manipulação de Banco de Dados
   private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ICategorie_parent ICategorie_parent = new ICategorie_parent();
  

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Categorie Categorie;
    
    
    
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
           
        if (validarEntradaDeDados()) {
     
             //Categorie.setId(Integer.parseInt(id.getText()));
            Categorie.setCategorie_parent((Categorie_parent) categorie_parent_id.getSelectionModel().getSelectedItem());
           
            //Categorie
            Categorie.setNom(nom.getText());
            //Categorie.setString(id.getText());
             buttonConfirmarClicked = true;
            dialogStage.close();
             TrayNotification tray = new TrayNotification("Notification !", "vous avez ajouter un nouveaux categorie", NotificationType.SUCCESS);
           tray.showAndDismiss(javafx.util.Duration.seconds(2));
        }
    
        actiontarget.setText("il ya un probléme dans l'ajout ");
    }
      @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ICategorie_parent.setConnection(connection);
        afficherComboBoxCategorieP();
      
    }    
    
     public void afficherComboBoxCategorieP() {
        listCategorie_parent = ICategorie_parent.select();
        observableListCategorie_parent = FXCollections.observableArrayList(listCategorie_parent);
        categorie_parent_id.setItems(observableListCategorie_parent);
    }
    
    
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Categorie getCategorie() {
        return this.Categorie;
    }

    public void setCategorie(Categorie Categorie) {
        this.Categorie = Categorie;
        //this.id.setText(String.valueOf(Categorie.getId()));
        this.categorie_parent_id.setStyle(String.valueOf(Categorie.getCategorie_parent()));
        this.nom.setText(Categorie.getNom());
        
        
        
        
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
   
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (categorie_parent_id.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "categorie no valide!\n";
        }
        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "nom no valide!\n";
        }
        /*if (id.getText() == null || id.getText().length() == 0) {
            errorMessage += "id  no valide!\n";
        }*/
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Il y a une faute dans le saisie,SVP, Corriger...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
