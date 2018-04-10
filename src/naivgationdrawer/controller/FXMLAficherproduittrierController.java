/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naivgationdrawer.controller;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import naivgationdrawer.Entite.Produit;
import naivgationdrawer.Services.ProduitServices;
import naivgationdrawer.connection.Database;
import naivgationdrawer.connection.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXMLAficherproduittrierController implements Initializable {
     @FXML
    private Label labelname;
    @FXML
    private Label labelcategorie;
    @FXML
    private Label labelprix;
    @FXML
    private Label labelvis;
    @FXML
    private Label labelstock;

     private ArrayList<Produit>  listeProduit;
    private ObservableList<Produit>ObservablelistProduit;
    private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProduitServices ProduitServices = new ProduitServices();
    @FXML
    private TableView<Produit> produit;
    @FXML
    private TableColumn<Produit,Integer> id;
    @FXML
    private TableColumn<Produit, Integer> categorie_id;
    @FXML
    private TableColumn<Produit, String> nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitServices.setConnection(connection);
        afficherTableProduit();
        selecionarItemTableViewClientes(null);
        produit.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }    

   public void afficherTableProduit(){
       listeProduit = ProduitServices.listt();
       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorie_id.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
         ObservablelistProduit = FXCollections.observableArrayList(listeProduit);
        produit.setItems(ObservablelistProduit);
    }
    public void selecionarItemTableViewClientes(Produit Produit){
        
      if (Produit != null) {
           
           labelname.setText(Produit.getNom());
           labelcategorie.setText(String.valueOf(Produit.getCategorie_id()));      
           labelprix.setText(String.valueOf(Produit.getPrix()));
           labelvis.setText(String.valueOf(Produit.getVisibilite()));
           labelstock.setText(String.valueOf(Produit.getStocke()));
           
        } else {
            labelvis.setText("");
            labelname.setText("");
            labelcategorie.setText("");
           labelstock.setText("");
            labelprix.setText("");
        }
    }

    @FXML
    private void handleChange(ActionEvent event) {
         Produit Produit = produit.getSelectionModel().getSelectedItem();
         if (Produit != null) {
           
            if (Produit.getVisibilite()==1) {
                Produit.setVisibilite(0);
               ProduitServices.changer(Produit);
                afficherTableProduit();
            }else
            {
                Produit.setVisibilite(1);
                ProduitServices.changer(Produit);
                afficherTableProduit();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
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
