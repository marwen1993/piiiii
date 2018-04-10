

package naivgationdrawer.controller;


import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FXMLCatecorieparentController implements Initializable {
    @FXML
    private TableView<Categorie_parent> parent;
    @FXML
    private TableColumn<Categorie_parent,Integer> id;
    @FXML
    private TableColumn<Categorie_parent,String> nom;
    @FXML
    private Label labeleidparent;
    @FXML
    private Label lablenamparent;

    private ArrayList<Categorie_parent> listCategorie_parent;
    private ObservableList<Categorie_parent>ObservablelistCategorie_parent;
    
    
    private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    //private final Categorie Categorie = new Categorie();
   // private final ICategorie_parent ICategorie_parent = new ICategorie_parent();
    private final ICategorie_parent ICategorie_parent = new ICategorie_parent() ;
    @FXML
    private JFXButton retour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ICategorie_parent.setConnection(connection);
        afficherTableCategorieparent();
        selecionarItemTableViewClientes(null);
        parent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }    
        public void afficherTableCategorieparent(){
       listCategorie_parent = ICategorie_parent.afficher();
       selecionarItemTableViewClientes(null);
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
       
        ObservablelistCategorie_parent = FXCollections.observableArrayList(listCategorie_parent);
        parent.setItems(ObservablelistCategorie_parent); 
    }
    public void selecionarItemTableViewClientes(Categorie_parent Categorie_parent){
        
        if (Categorie_parent != null) {
            labeleidparent.setText(String.valueOf(Categorie_parent.getId()));
            lablenamparent.setText(Categorie_parent.getNom());
            
          
        } else {
            labeleidparent.setText("");
            lablenamparent.setText("");
            
           
        }
        //System.out.println("Clienti selectionnado " + Categorie.toString());
    }

    @FXML
    private void handleretour(ActionEvent event) throws IOException  {
        
        Parent p1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                String css = this.getClass().getResource("home.css").toExternalForm();
        test1.getStylesheets().add(css);
                App1.show();
    }
}
