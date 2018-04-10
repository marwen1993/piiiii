
package naivgationdrawer.controller;
import java.awt.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

public class FXMLMainController implements Initializable {

    @FXML
    private javafx.scene.control.MenuItem menuitmcategorieparent;
    @FXML
    private javafx.scene.control.MenuItem menuitmproduit;
    @FXML
    private javafx.scene.control.MenuItem menuitmproduitdis;
    @FXML
    private javafx.scene.control.MenuItem statjeux;
    @FXML
        private AnchorPane anchorPane;
    @FXML
    private Menu menucataegorie;
    @FXML
    private Menu menuproduit;
    @FXML
    private Menu menustat;
    
    @FXML
    private javafx.scene.control.MenuItem menucategorie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
       public void hundelcategorie() throws IOException
   {
       AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLCategorie.fxml"));
       anchorPane.getChildren().setAll(a);
   }
    @FXML
        public void hundelcategorieParent() throws IOException
   {
       AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLCatecorieparent.fxml"));
       anchorPane.getChildren().setAll(a);
   }
        
    @FXML
        public void hundelproduit() throws IOException
   {
       AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLProduit.fxml"));
       anchorPane.getChildren().setAll(a);
   }   

    @FXML
    public void hundelproduitcategorie() throws IOException {
         AnchorPane a= (AnchorPane) FXMLLoader.load(getClass().getResource("/view/FXMLAfficherproduitcategorie.fxml"));
       anchorPane.getChildren().setAll(a);
    }
      
}
