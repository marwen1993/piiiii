package Main;

import controller.MainWindowController;
import controller.splashController;
import controller.signInController;
import controller.gamesController;
import controller.signupFXMLConroller;
import controller.comunityCommentController;
import controller.comunityController;
import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


      public   Stage comunityAdminStage, gamesStage,commentStage,comunityStage,adminStage, signUpStage,signInStage,splashStage,mainStage;

      public static String gamesType="",gameName="";
      @Override
    public void start(Stage primaryStage) throws Exception{
      this.splashStage=primaryStage;
      splashWindow();
    }


    public void splashWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/splashScreen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            splashController controller=loader.getController();
            controller.main(this,splashStage);
            splashStage=new Stage();
            splashStage.setResizable(false);
            splashStage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            splashStage.setScene(scene);
            splashStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            splashStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gamesWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/gamesScreen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
           gamesController controller=loader.getController();
           controller.main(this,gamesStage);
            gamesStage=new Stage();
            gamesStage.setResizable(false);
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            gamesStage.setScene(scene);
            gamesStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            gamesStage.setTitle(gamesType);
            gamesStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void mainWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/FXML_homeDocument.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            MainWindowController controller=loader.getController();
            controller.main(this,mainStage);
             mainStage=new Stage();
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            mainStage.setTitle("Main");
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void signInWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/FXMLSignInDocument.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            signInController controller=loader.getController();
            controller.main(this,signInStage);
            signInStage=new Stage();
            signInStage.setResizable(false);
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            signInStage.setScene(scene);
            signInStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));

            signInStage.setTitle("Sign in");
            signInStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUpWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/signUpfxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            signupFXMLConroller conroller=loader.getController();
            conroller.main(this,signUpStage);
            signUpStage=new Stage();
            signUpStage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            signUpStage.setScene(scene);
            signUpStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));

            signUpStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/adminScreen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            adminStage=new Stage();
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            adminStage.setScene(scene);
            adminStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
          adminStage.setTitle("Administration");
            adminStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comunityWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/comunity_Screen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            comunityController comunityController=loader.getController();
            comunityController.main(this,comunityStage);
            comunityStage=new Stage();
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            comunityStage.setScene(scene);
            comunityStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            comunityStage.setTitle("Game Comunity");
            comunityStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commentsWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/comunity_CommentScreen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            comunityCommentController controller=loader.getController();
            controller.main(this,commentStage);
            commentStage=new Stage();
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            commentStage.setScene(scene);
            commentStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            commentStage.setTitle(gameName);
            commentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void comunityAdminWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/adminComunity_Screen.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            comunityAdminStage=new Stage();
            scene.getStylesheets().add(Main.class.getResource("/style/StyleSheet.css").toExternalForm());
            comunityAdminStage.initModality(Modality.APPLICATION_MODAL);
            comunityAdminStage.setScene(scene);
            comunityAdminStage.getIcons().addAll(new Image("/src/608ZzH6Ky9Rj6xQaT4YpEZfOc58TYE3FYbTyHkiKj3OLOFXbB1IHxyNYK_H70ayh4kY=w300.png"));
            comunityAdminStage.setTitle(signInController.userName);
            comunityAdminStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void closeSignUp(){
    this.signUpStage.close();
    }
    public void closeSignIn(){
        this.signInStage.close();
    }
    public void closeMain(){
        this.mainStage.close();
    }
    public void closeGames(){
        this.gamesStage.close();
    }
    public void closeComunity(){
        this.comunityStage.close();
    }
    public void closeGComments(){
        this.commentStage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
