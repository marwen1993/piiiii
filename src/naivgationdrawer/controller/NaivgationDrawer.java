package naivgationdrawer.controller;


import naivgationdrawer.controller.MainWindowController;
import naivgationdrawer.controller.splashController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NaivgationDrawer extends Application {
    public   Stage signUpStage,signInStage,splashStage,mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
      this.splashStage=primaryStage;
      splashWindow();
    }
    
    public void splashWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(NaivgationDrawer.class.getResource("splashScreen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            splashController controller=loader.getController();
            controller.main(this,splashStage);
            splashStage=new Stage();
            splashStage.setResizable(false);
            splashStage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(NaivgationDrawer.class.getResource("StyleSheet.css").toExternalForm());
            splashStage.setScene(scene);
            splashStage.getIcons().addAll(new Image("/src/one.png"));
            splashStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void mainWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(NaivgationDrawer.class.getResource("FXMLhomeDocument.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            MainWindowController controller=loader.getController();
            controller.main(this,mainStage);
             mainStage=new Stage();
            scene.getStylesheets().add(NaivgationDrawer.class.getResource("StyleSheet.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.getIcons().addAll(new Image("/src/one.png"));
            mainStage.setTitle("Main");
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 
    public static void main(String[] args) {
        launch(args);}
    
}
