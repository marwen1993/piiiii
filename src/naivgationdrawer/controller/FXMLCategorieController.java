
package naivgationdrawer.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import naivgationdrawer.Entite.Categorie;
import naivgationdrawer.Services.ICategorieService;
import naivgationdrawer.connection.Database;
import naivgationdrawer.connection.DatabaseFactory;


public class FXMLCategorieController implements Initializable {

   
    @FXML
    private TableView<Categorie> categorie;
    
     @FXML
    private TableColumn<Categorie,Integer> id;
    
     @FXML
    private TableColumn<Categorie ,String> nom;
    
     @FXML
    private Button buttonajouter;
    
     @FXML
    private Button buttonmodifier;
     
     @FXML
    private Button buttonsuupprimer;
    
    @FXML
    private Label labelid;
    @FXML
    private Label labelnom;
    @FXML
    private Label lablenomcategorie;
    
    private ArrayList<Categorie> listCategorie;
   
    private ObservableList<Categorie>ObservablelistCategoriee;
    
    
    private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    //private final Categorie Categorie = new Categorie();
    private final ICategorieService ICategorieService = new ICategorieService();
   // private final ICategorie_parent ICategorie_parent =new ICategorie_parent();
    @FXML
    private TextField serachField;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ICategorieService.setConnection(connection);
        afficherTableCategorie();
        selecionarItemTableViewClientes(null);
        categorie.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }    
    
    public void afficherTableCategorie(){
       listCategorie = ICategorieService.afficher();
       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        ObservablelistCategoriee = FXCollections.observableArrayList(listCategorie);
        categorie.setItems(ObservablelistCategoriee); 
    }
    public void selecionarItemTableViewClientes(Categorie Categorie){
        
        if (Categorie != null) {
            labelid.setText(String.valueOf(Categorie.getId()));
            labelnom.setText(Categorie.getNom());
            
            lablenomcategorie.setText(String.valueOf(Categorie.getCategorie_parent()));
        } else {
            labelid.setText("");
            labelnom.setText("");
            lablenomcategorie.setText("");
           
        }
        //System.out.println("Clienti selectionnado " + Categorie.toString());

    }
    @FXML
    public void handleButtonInserir() throws IOException {
        Categorie Categorie = new Categorie();
        boolean buttonConfirmarClicked = showFXMLAjouterCategorieDialogController(Categorie);
        if (buttonConfirmarClicked) {
            ICategorieService.ajouterCategorie(Categorie);
            afficherTableCategorie();
        }
    }
         @FXML
    public void handleButtonAlterar() throws IOException {
        Categorie Categorie = categorie.getSelectionModel().getSelectedItem();
        if (Categorie != null) {
            boolean buttonConfirmarClicked = showFXMLAjouterCategorieDialogController(Categorie);
            if (buttonConfirmarClicked) {
               ICategorieService.alterar(Categorie);
                afficherTableCategorie();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("SVP choisir un categorie !");
            alert.show();
        }
    }
    
        @FXML
    public void handleButtonRemover() throws IOException, SQLException {
      
        Categorie Categorie = categorie.getSelectionModel().getSelectedItem();
        if (categorie != null) {
            ICategorieService.supprimerCategorie(Categorie);
            afficherTableCategorie();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("SVP choisir un categorie!");
            alert1.show();
        } 
    }
    

     public boolean showFXMLAjouterCategorieDialogController(Categorie Categorie) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAjouterCategorieDialogController.class.getResource("FXMLAjouterCategorieDialog.fxml"));
        //AnchorPane page = (AnchorPane) loader.load();
        GridPane page = (GridPane) loader.load();
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registre un nouveaux categorie");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        // Setando a Venda no Controller.
        FXMLAjouterCategorieDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCategorie(Categorie);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }

    @FXML
    private void handleretour(ActionEvent event) throws IOException {
        Parent p1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene test1 = new Scene(p1);
                Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App1.setScene(test1);
                String css = this.getClass().getResource("home.css").toExternalForm();
        test1.getStylesheets().add(css);
                App1.show();
    }

    
    private final ObservableList<Categorie> data=FXCollections.observableArrayList();
FilteredList<Categorie>filetredData=new FilteredList<>(data,e->true);
    
    @FXML
    private void serachAmicale(ActionEvent event) {
             serachField.textProperty().addListener((observableValue,oldValue,newValue)->{
             filetredData.setPredicate((Predicate<? super Categorie>)Categorie->{
                 if(newValue==null||newValue.isEmpty()){
                     return true;
                 }
      
                 
                 String  lowerCaseFilter = newValue.toLowerCase();
                
                 if (Categorie.getNom().toLowerCase().contains(lowerCaseFilter)){
                     
                     return true;
                    
                 }
                
                 return false;
             });
             
         });
        
            SortedList<Categorie>sortedData=new SortedList<>(filetredData);
            sortedData.comparatorProperty().bind(categorie.comparatorProperty());
            categorie.setItems(sortedData);
            
             
    }
    
    
    
}
