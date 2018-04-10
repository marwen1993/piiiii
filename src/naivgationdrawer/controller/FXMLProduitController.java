
package naivgationdrawer.controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import naivgationdrawer.Entite.Produit;
import naivgationdrawer.Services.Csv;
import naivgationdrawer.Services.ProduitServices;
import naivgationdrawer.connection.Database;
import naivgationdrawer.connection.DatabaseFactory;
import static naivgationdrawer.controller.FXMLAjouterProduitDialogController.Prod;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.relique.jdbc.csv.CsvDriver;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class FXMLProduitController implements Initializable {
    @FXML
    private TableView<Produit> produit;
    @FXML
    private TableColumn<Produit, Integer> id;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private Label labelid;
    @FXML
    private Label labelcategorie;
    @FXML
    private Label labelnom;
    @FXML
    private Label lablelibelle;
    @FXML
    private Label labelprix;
    @FXML
    private Label labelnb;
    @FXML
    private Label labelstock;
    @FXML
    private Label labelvisibilite;
     @FXML
    private ImageView image;    

    
    private ArrayList<Produit>  listeProduit;
    private ObservableList<Produit>ObservablelistProduit;
    private final Database database =DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ProduitServices ProduitServices = new ProduitServices();
        //private ImageView image;
    @FXML
    private Button buttonsuupprimer;
    @FXML
    private Button buttonajouter;
    @FXML
    private Button buttonmodifier;
    @FXML
    private JFXButton imprID;
    @FXML
    private JFXButton Bexcel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    ProduitServices.setConnection(connection);
        afficherTableProduit();
        selecionarItemTableViewClientes(null);
        produit.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }    
    
    public void afficherTableProduit(){
       listeProduit = ProduitServices.listar();
       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
         ObservablelistProduit = FXCollections.observableArrayList(listeProduit);
        produit.setItems(ObservablelistProduit);
    }
    public void selecionarItemTableViewClientes(Produit Produit){
        
      if (Produit != null) {
           labelid.setText(String.valueOf(Produit.getId()));
           labelnom.setText(Produit.getNom());
           labelcategorie.setText(String.valueOf(Produit.getCategorie_id()));
           lablelibelle.setText(Produit.getLibelle());
           labelprix.setText(String.valueOf(Produit.getPrix()));
           labelnb.setText(String.valueOf(Produit.getNombre_point()));
           labelstock.setText(String.valueOf(Produit.getStocke()));
            labelvisibilite.setText(String.valueOf(Produit.getVisibilite()));
            Image imag = new Image("http://localhost/pidev/web/bundles/upload//"+String.valueOf(Produit.getImage()));
           // Image imag = new Image("C:/wamp/www/PiDev/web/bundles/upload"+Produit.getImage());
            image.setImage(imag);
           
        } else {
            labelid.setText("");
            labelnom.setText("");
            labelcategorie.setText("");
           lablelibelle.setText("");
            labelprix.setText("");
            labelnb.setText("");
           
            labelstock.setText("");
            labelvisibilite.setText("");
        }
        //System.out.println("Clienti selectionnado " + Categorie.toString());

    }  
    
    @FXML
       public void handleButtonInserir() throws IOException {
        Produit Produit = new Produit();
        
        boolean buttonConfirmarClicked = showFXMLAjouterProduitDialogController(Produit);
       // System.out.println("aaaaaaaaaaaaaaaaaa");
        if (buttonConfirmarClicked) {
            ProduitServices.inserir(Prod);
            
            afficherTableProduit();
        }
    }
    @FXML
             public void handleButtonAlterar() throws IOException {
        Produit Produit = produit.getSelectionModel().getSelectedItem();
        if (Produit != null) {
            boolean buttonConfirmarClicked = showFXMLAjouterProduitDialogController(Produit);
            if (buttonConfirmarClicked) {
               ProduitServices.alterar(Produit);
                afficherTableProduit();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }
    
    @FXML
            public void handleButtonRemover() throws IOException, SQLException {
      
        Produit Produit = produit.getSelectionModel().getSelectedItem();
        if (Produit != null) {
            ProduitServices.remover(Produit);
            afficherTableProduit();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Por favor, escolha um cliente na Tabela!");
            alert1.show();
        } 
    }
  public boolean showFXMLAjouterProduitDialogController(Produit Produit) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAjouterProduitDialogController.class.getResource("FXMLAjouterProduitDialog.fxml"));
        //AnchorPane page = (AnchorPane) loader.load();
        GridPane page = (GridPane) loader.load();
        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Produit");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        // Setando a Venda no Controller.
        FXMLAjouterProduitDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduit(Produit);
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
       // dialogStage.show();
        return controller.isButtonConfirmarClicked();
    }
        

    @FXML
    private void printAction(ActionEvent event) {
          PrinterJob pj = PrinterJob.getPrinterJob();
      pj.setJobName(" Print Component ");
     pj.setPrintable (new Printable() {

            @Override
      public int print(Graphics pg, PageFormat pf, int pageNum) throws PrinterException {
         if (pageNum > 0){
            return Printable.NO_SUCH_PAGE;
        }
         Graphics2D g2 = (Graphics2D) pg;
       g2.translate(pf.getImageableX(), pf.getImageableY());
        WritableImage image = produit.snapshot(new SnapshotParameters(), null);
                g2.drawImage(SwingFXUtils.fromFXImage(image, null),null, 1, 1);
                return Printable.PAGE_EXISTS;
     }            
     });
     if (pj.printDialog() == false)
     return;
     try {
         pj.print();
      } catch (PrinterException ex) {
     //handle exception
      }
    }

//    @FXML
//    private void exportExcel(MouseEvent event) {
//        ProduitServices us = new ProduitServices();
//            try {
//            File tempFile = new File("C:/Users/Computer/Desktop/liste_Produit.csv");
//            FileOutputStream fout = new FileOutputStream(tempFile);
//            PrintStream out = new PrintStream(fout);
//            CsvDriver.writeToCsv(us.getResultSet(),out, true);         
//            ArrayList<ArrayList<String>> allRowAndColData = null;
//            ArrayList<String> oneRowData = null;
//           
//            String fName = "C:/Users/Computer/Desktop/liste_Produit.csv";
//            String currentLine;
//            FileInputStream fis = new FileInputStream(fName);
//            DataInputStream myInput = new DataInputStream(fis);
//            int i = 0;
//            allRowAndColData = new ArrayList<ArrayList<String>>();
//            while ((currentLine = myInput.readLine()) != null) {
//            oneRowData = new ArrayList<String>();
//            String oneRowArray[] = currentLine.split(",");
//            for (int j = 0; j < oneRowArray.length; j++) {
//                oneRowData.add(oneRowArray[j]);
//            }
//            allRowAndColData.add(oneRowData);
//            i++;
//            }
//            HSSFWorkbook workBook = new HSSFWorkbook();
//            HSSFSheet sheet = workBook.createSheet("sheet1");
//            for ( i = 0; i < allRowAndColData.size(); i++) {
//            ArrayList<?> ardata = (ArrayList<?>) allRowAndColData.get(i);
//            HSSFRow row = sheet.createRow((short) 0 + i);
//            for (int k = 0; k < ardata.size(); k++) {
//            System.out.print(ardata.get(k));
//            HSSFCell cell = row.createCell((short) k);
//            cell.setCellValue(ardata.get(k).toString());
//           }
//           System.out.println();
//           }
//           FileOutputStream fileOutputStream =  new FileOutputStream("C:/Users/Computer/Desktop/liste_Produit.xls");
//           workBook.write(fileOutputStream);
//           fileOutputStream.close();
//           TrayNotification tray = new TrayNotification("Notification !", "Liste des Poduits exporter avec succée", NotificationType.SUCCESS);
//           tray.showAndDismiss(javafx.util.Duration.seconds(2));
//           } catch (Exception ex) {
//            Logger.getLogger(naivgationdrawer.controller.NaivgationDrawer.getInstance().getLoggedUser().toString()).log(Level.SEVERE, null, ex);
//           }
//           }
    
     @FXML
    private void onexelClicked(ActionEvent event) {
          
        ProduitServices Vservice= new ProduitServices();
        Csv csv=new Csv(ObservablelistProduit);
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
    
