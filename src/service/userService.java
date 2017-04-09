package service;

import DataBase.DataSource;
import Interface.userInterface;
import Main.Main;
import controller.signInController;
import java.sql.Connection;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.util.Duration;
import model.userModel;
import org.controlsfx.control.Notifications;
import DataBase.SMTP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DataBase.DataSource;
//import DataBase.TestMail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import DataBase.SendMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class userService implements userInterface {

    //DataSource dataSource;
    private PreparedStatement statement;
      private Connection connection;
    ResultSet result;
    //TestMail test = new TestMail();
    String subject="Your Game-Hub  permission !!!";
    String body= "vous avez eu la permission d'accéder à notre application merci d'être avec nous"
            + "vous pouvez accéder à votre compte maintenant,on utilisons votre username et votre mot de passe";  
    public userService(){
        //dataSource=new DataSource();
         connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void insertUser(userModel um) {
        try {
            String sqlInsert = "INSERT INTO games.user (fullname, username, email, password, roles, enabled,comment,createComunity) VALUES (?,?,?,?,?,?,?,?)";

           statement = connection.prepareStatement(sqlInsert);
           //statement=dataSource.getConnection().prepareStatement(sqlInsert);

            statement.setString(1, um.getFullName());
            statement.setString(2, um.getUsername());
            statement.setString(3, um.getEmail());
            statement.setString(4, um.getPassword());
            statement.setString(5, "user");
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setInt(8, 0);

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();
        } catch (NumberFormatException c) {

        } catch (NullPointerException cc) {
            cc.printStackTrace();
        } catch (Error e) {
        } catch (Exception f) {
            f.printStackTrace();
        } finally {
        }

    }

    @Override
    public ArrayList<userModel> selectAll() {
        ArrayList<userModel> arrayList=new ArrayList<>();
        try {
            String sqlSelect="SELECT  * FROM games.user WHERE username!='amani'";
            //statement=dataSource.getConnection().prepareStatement(sqlSelect);
             statement = connection.prepareStatement(sqlSelect);
            result=statement.executeQuery();
            while (result.next()){
                System.out.println(result.getString("username"));
                arrayList.add(new userModel(result.getInt("id")+"",result.getString("fullname"),result.getString("username"),result.getString("email"),result.getString("password"),result.getString("roles"),result.getInt("enabled")+"",result.getInt("comment")+"",result.getInt("createComunity")+""));
            }
        }catch (SQLException x){
            x.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public void deleteUser(int index, TreeItem<userModel> treeItem, ObservableList<userModel>userList) {
        try {
            String deleteSql="DELETE FROM games.user WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
            //statement=dataSource.getConnection().prepareStatement(deleteSql);
             statement = connection.prepareStatement(deleteSql);
            statement.executeUpdate();
            userList.remove(index);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void ChangePermissionEnabled(TreeItem<userModel> treeItem) {
        if (treeItem.getValue().getEnabled().equals("0")){
            try {
                String update="UPDATE games.user SET enabled=1 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),"1",treeItem.getValue().getComment(),treeItem.getValue().getCreate()));
                
            }catch (SQLException e){
                e.printStackTrace();
            }
            String s=treeItem.getValue().getEmail();
                SMTP.send(s,subject, body);
                System.out.println("envoie avec succés");
        }
        else if(treeItem.getValue().getEnabled().equals("1")) {
            try {
                String update="UPDATE games.user SET enabled=0 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),"0",treeItem.getValue().getComment(),treeItem.getValue().getCreate()));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void ChangeRole(TreeItem<userModel> treeItem) {
        if (treeItem.getValue().getRole().equals("user")){
            try {
                String update="UPDATE games.user SET roles='admin' ,comment=1,enabled=1,createComunity=1 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),"admin","1","1","1"));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else if (treeItem.getValue().getRole().equals("admin")){
            try {
                String update="UPDATE games.user SET roles='user' WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),"user",treeItem.getValue().getEnabled(),treeItem.getValue().getComment(),treeItem.getValue().getCreate()));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void ChangeComunityCreationPermission(TreeItem<userModel> treeItem) {
        if (treeItem.getValue().getCreate().equals("1")){
            try {
                String update="UPDATE games.user SET createComunity ='0' WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
               //statement=dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),treeItem.getValue().getEnabled(),treeItem.getValue().getComment(),"0"));
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else if (treeItem.getValue().getCreate().equals("0")){
            try {
                String update="UPDATE games.user SET createComunity=1 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),treeItem.getValue().getEnabled(),treeItem.getValue().getComment(),"1"));
            }catch (SQLException e){
                e.printStackTrace();
            }

            try {
                String update="UPDATE games.comunity SET permission='1' WHERE username='"+treeItem.getValue().getUsername()+"' ";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void ChangeCommentPermission(TreeItem<userModel> treeItem) {
        if (treeItem.getValue().getComment().equals("1")){
            try {
                String update="UPDATE games.user SET comment =0 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),treeItem.getValue().getEnabled(),"0",treeItem.getValue().getCreate()));
            }catch (SQLException e){
                e.printStackTrace();
            }

            }else if (treeItem.getValue().getComment().equals("0")){

            try {
                String update="UPDATE games.user SET comment=1 WHERE username='"+treeItem.getValue().getUsername()+"' AND email='"+treeItem.getValue().getEmail()+"'";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
                treeItem.setValue(new userModel(treeItem.getValue().getId(),treeItem.getValue().getFullName(),treeItem.getValue().getUsername(),treeItem.getValue().getEmail(),treeItem.getValue().getPassword(),treeItem.getValue().getRole(),treeItem.getValue().getEnabled(),"1",treeItem.getValue().getCreate()));
            }catch (SQLException e){
                e.printStackTrace();
            }

            try {
                String update="UPDATE games.comment SET permission='1' WHERE username='"+treeItem.getValue().getUsername()+"' ";
                //statement=dataSource.getConnection().prepareStatement(update);
                 statement = connection.prepareStatement(update);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void signin(String username, String password, Main main,signInController signInController) {
        try {
            String sqlSelect="SELECT * FROM games.user WHERE username='"+username+"' AND password='"+password+"' AND roles='admin'";
           //statement=dataSource.getConnection().prepareStatement(sqlSelect);
            statement = connection.prepareStatement(sqlSelect);

            result=statement.executeQuery();
            if (result.next()){
                main.adminWindow();
                main.closeSignIn();

                Notifications notification = Notifications.create()
                        // .graphic(new ImageView(image))
                        .title("signin completion ")
                        .text(username + " signin successfully")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showInformation();

            }else if (!result.next()){
                signInController.showSnackBar("username or password is incorrect");
            }
        }catch (SQLException x){
            x.printStackTrace();
        }

        try {
            String sqlSelect="SELECT * FROM games.user WHERE username='"+username+"' AND password='"+password+"' AND roles='user'";
            //statement=dataSource.getConnection().prepareStatement(sqlSelect);
             statement = connection.prepareStatement(sqlSelect);

            result=statement.executeQuery();
            if (result.next()){
                if (result.getInt("enabled")==1){
                    signInController.name=result.getString("fullname");
                    signInController.userName=result.getString("username");
                    signInController.commentper=result.getInt("comment");
                    signInController.createPer=result.getInt("createComunity")+"";
                    main.mainWindow();
                    main.closeSignIn();
                    Notifications notification = Notifications.create()
                            // .graphic(new ImageView(image))
                            .title("signin completion ")
                            .text(username + " signin successfully")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT);
                    notification.showInformation();
                  
                    /*try {
                        test.sendMail("mzeh.amani@gmail.com","jet'aimerou7ibilel","salut","la vie en rose");
                    } catch (MessagingException ex) {
                        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                   // SendMessage ms = new SendMessage();
                    //ms.sendMessageCode("29688045", "");
        
                }else {
                    signInController.showSnackBar("this user needs a permission from the admin to complete the signin operation !");
                }
            }else if (!result.next()){
                signInController.showSnackBar("username or password is incorrect");
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
   }
}
