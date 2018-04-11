/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivgationdrawer.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import naivgationdrawer.Entite.Produit;
import naivgationdrawer.Services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author chevc
 */
public class ProduitFrontFXMLController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> tfnom;
    @FXML
    private TableColumn<Produit, String> tfcategorie;
    @FXML
    private TableColumn<Produit, Double> tfprix;
    @FXML
    private TableColumn<Produit, String> tfimage;

    
    
    ProduitServices service = new ProduitServices();
     ObservableList<Produit> listP = FXCollections.observableArrayList(service.listar());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfnom.setCellValueFactory(new PropertyValueFactory("nom"));
    tfcategorie.setCellValueFactory(new PropertyValueFactory("categorie_id"));
    tfprix.setCellValueFactory(new PropertyValueFactory("prix"));
    tfimage.setCellValueFactory(new PropertyValueFactory("image"));
    }    
     
}
