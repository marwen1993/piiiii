
package naivgationdrawer.controller;

import naivgationdrawer.Entite.Categorie;
import naivgationdrawer.Entite.Produit;
import naivgationdrawer.Services.ICategorieService;
import naivgationdrawer.connection.Database;
import naivgationdrawer.connection.DatabaseFactory;
import naivgationdrawer.connection.Sendmail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.VoiceUtils;

/**
 * FXML Controller class
 *
 * @author Computer
 */
public class FXMLAjouterProduitDialogController implements Initializable {

    
    @FXML private Text actiontarget;


     private ArrayList<Categorie> listCategorie;
    
    private ObservableList<Categorie> observableListCategorie;
 

    //Atributos para manipulação de Banco de Dados
   private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ICategorieService ICategorieService = new ICategorieService();
  

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    static Produit Prod;
    @FXML
    //private TextField id;
    //@FXML
    private TextField libelle;
    @FXML
    private TextField nom;
    
    @FXML
    private TextField prix;
    @FXML
    private TextField nombre_point;
    @FXML
    private TextField stocke;
    @FXML
     private TextField image;
    @FXML
    private ComboBox categorie_id;
    @FXML
    private Button confirmer;
    @FXML
    private Button cancel;
    @FXML
    private ImageView imgVw;
    @FXML
    private Button uplodimage;
    
    static String imge;
    
    
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
           VoiceUtils v=new VoiceUtils("kevin16");
         String[] s={"veuillez remplir soignesement les cases"};
        v.sayMultiple(s);
           if (validarEntradaDeDados()) {
     
           
            Prod.setUser_id(null);
            Prod.setVisibilite(1);
            //Prod.setId(Integer.parseInt(id.getText()));
            Prod.setCategorie_id((Categorie) categorie_id.getSelectionModel().getSelectedItem());
            Prod.setImage(imge);
  
            Prod.setNom(nom.getText());
            Prod.setLibelle(libelle.getText());
            Prod.setNombre_point(Integer.parseInt(nombre_point.getText()));
            Prod.setPrix(Float.parseFloat(prix.getText()));
            Prod.setStocke(Integer.parseInt(stocke.getText()));
            Sendmail ss  = new Sendmail();
      /*try {
          ss.sendMail("aymen.braham@esprit.tn", "98520816kyoubraham", "Nouvouté", "un nouveaux produit est ajouter  ");
       } catch (MessagingException ex) {
            Logger.getLogger(FXMLAjouterProduitDialogController.class.getName()).log(Level.SEVERE, null, ex);
       }*/
             buttonConfirmarClicked = true;
            dialogStage.close();
             TrayNotification tray = new TrayNotification("Notification !", "vous avez ajouter un nouveaux produits ainsi que un mail a été envoyer vesr tt les utlisateur  ", NotificationType.SUCCESS);
           tray.showAndDismiss(javafx.util.Duration.seconds(2));
       
        }
    
        //actiontarget.setText("il ya un probléme dans l'ajout ");
    }
      @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ICategorieService.setConnection(connection);
        afficherComboBoxProduit();
    }  
    
    
    
      public void afficherComboBoxProduit() {
        listCategorie = ICategorieService.afficher();
        observableListCategorie = FXCollections.observableArrayList(listCategorie);
        categorie_id.setItems(observableListCategorie);
    }
    
    
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Produit getProduit() {
        return this.Prod;
    }

    public void setProduit(Produit Produit) {
        this.Prod = Produit;
        
        //this.id.setText(String.valueOf(Produit.getId()));
        this.categorie_id.setStyle(String.valueOf(Produit.getCategorie_id()));
        this.nom.setText(Produit.getNom());
       
//       this.image.setText(Produit.getImage());
        this.libelle.setText(Produit.getLibelle());
        this.nombre_point.setText(String.valueOf(Produit.getNombre_point()));
        this.prix.setText(String.valueOf(Produit.getPrix()));
        this.stocke.setText(String.valueOf(Produit.getStocke()));
        
        
        
        
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
   
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (categorie_id.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "la categorie est non valide!\n";
        }
        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "le nom est invalide!\n";
        }
        //if (id.getText() == null || id.getText().length() == 0) {
           // errorMessage += "id  inválido!\n";
       // }
        
        
        
/*        if (image.getText() == null || nom.getText().length() == 0) {
            errorMessage += "nom inválido!\n";
        }*/
      /*  if (nombre_point.getText() == null || id.getText().length() == 0) {
            errorMessage += "id  inválido!\n";
        }
        if (prix.getText() == null || id.getText().length() == 0) {
            errorMessage += "id  inválido!\n";
        }
        */
        
        if (libelle.getText() == null || nom.getText().length() == 0) {
            errorMessage += "le nom est invalide!\n";
        }
       /* if (stocke.getText() == null || id.getText().length() == 0) {
            errorMessage += "id  inválido!\n";
        }*/
        if (errorMessage.length() == 0) {
            return true;
            
        } 
        
        
        
        
        else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'inscription");
            alert.setHeaderText("champs invalide,veuillez les corriger");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    @FXML
    private void processUpload(ActionEvent event) {
     FileChooser fileChooser = new FileChooser();
                     FileChooser.ExtensionFilter exjpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                     FileChooser.ExtensionFilter exjpg2 = new FileChooser.ExtensionFilter("JPEG (Joint Photographic Experts Group)", "*.jpeg");
                     FileChooser.ExtensionFilter expng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                     fileChooser.getExtensionFilters().addAll(exjpg,exjpg2, expng);
                     fileChooser.setTitle("Choose an image File");

                     File file = fileChooser.showOpenDialog(null);
                     
                             if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                
                   Image img = new Image(file.toURI().toString() ); 
                                imgVw.setImage(img);
                                imgVw.setFitHeight(150);
                                imgVw.setFitWidth(150);
                                imge = file.getName();
                                System.out.println(file.toURI().toString());
            } 
            else 
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
            }
 
    }
    }
    
    
    }
    

