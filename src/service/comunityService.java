package service;

import DataBase.DataSource;
import Interface.comunityInterface;
import com.jfoenix.controls.JFXButton;
import controller.comunityController;
import controller.signInController;
import java.sql.Connection;
import javafx.scene.control.Label;
import model.recycleViewModel;
import org.kairos.layouts.RecyclerView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class comunityService implements comunityInterface {
     private Connection connection;
    DataSource dataSource;
    PreparedStatement statement;
    ResultSet result;
    public comunityService(){
        //dataSource=new DataSource();
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void insertComunity(recycleViewModel rm) {

        try {
        String sqlInsert = "INSERT INTO games.comunity  VALUES (?,?,?,?,?)";

        //statement = dataSource.getConnection().prepareStatement(sqlInsert);
        statement = connection.prepareStatement(sqlInsert);

        statement.setString(1, rm.getName());
        statement.setString(2, rm.getComment());
        statement.setString(3, signInController.createPer);
        statement.setString(4, signInController.userName);
        statement.setString(5, "follow");

        statement.executeUpdate();

    } catch (SQLException ex) {

        ex.printStackTrace();
    } catch (NumberFormatException c) {
        c.printStackTrace();
    } catch (NullPointerException cc) {
        cc.printStackTrace();
    } catch (Error e) {
    } catch (Exception f) {
        f.printStackTrace();
    } finally {
    }
    }

    @Override
    public void insertFollow(recycleViewModel rm) {
        try {
            String sqlInsert = "INSERT INTO games.follow  VALUES (?,?,?,?)";

            //statement = dataSource.getConnection().prepareStatement(sqlInsert);
            statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, rm.getName());
            statement.setString(2, rm.getComment());
            statement.setString(3, signInController.userName);
            statement.setString(4, "Unfollow");

            statement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();
        } catch (NumberFormatException c) {
            c.printStackTrace();
        } catch (NullPointerException cc) {
            cc.printStackTrace();
        } catch (Error e) {
        } catch (Exception f) {
            f.printStackTrace();
        } finally {
        }
    }

    @Override
    public void changeComunityPermission(String name, String description, String username,JFXButton button) {

        if (button.getText().equals("Hide")==true){
            try {

                String update = "UPDATE games.comunity SET permission ='0'  WHERE username='"+signInController.userName+"' AND  name='" +name+ "' AND description='"+description+"'";
                //statement = dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                button.setText("Unhide");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       else if (button.getText().equals("Unhide")==true){
            try {

                String update = "UPDATE games.comunity SET permission ='1'  WHERE username='"+signInController.userName+"' AND  name='" +name+ "' AND description='"+description+"'";
                //statement = dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                button.setText("Hide");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<recycleViewModel> selectAllIntoList() {
        ArrayList<recycleViewModel> arrayList=new ArrayList<>();
        try {
            String sqlSelect="SELECT  * FROM games.comunity WHERE permission='1'";
            //statement=dataSource.getConnection().prepareStatement(sqlSelect);
            statement = connection.prepareStatement(sqlSelect);
            result=statement.executeQuery();
            while (result.next()){
                recycleViewModel recycleViewModel=new recycleViewModel();
                recycleViewModel.setName(result.getString(1));
                recycleViewModel.setComment(result.getString(2));
                arrayList.add(recycleViewModel);
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public void addDataToRecyclerView(String name, String description, RecyclerView<recycleViewModel> recycleView) {
        try {
            String sqlSelect="SELECT  * FROM games.follow  WHERE name='"+name+"' AND description='"+description+"' AND username='"+signInController.userName+"'";
            //statement=dataSource.getConnection().prepareStatement(sqlSelect);
            statement = connection.prepareStatement(sqlSelect);
            result=statement.executeQuery();
            if (result.next()){
                recycleViewModel recycleViewModel=new recycleViewModel();
                recycleViewModel.setName(result.getString(1));
                recycleViewModel.setComment(result.getString(2));
                if (result.getString("follow").equals("Unfollow")){
                    recycleViewModel.setFollow("Unfollow");
                }else {
                    recycleViewModel.setFollow("Follow");
                }
                recycleView.getItems().addAll(recycleViewModel);
            }else if (!result.next()){
                recycleViewModel recycleViewModel=new recycleViewModel();
                recycleViewModel.setName(name);
                recycleViewModel.setComment(description);
                recycleViewModel.setFollow("Follow");
                recycleView.getItems().addAll(recycleViewModel);
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
    }

    @Override
    public void follow(JFXButton follow, Label name,Label description,comunityService service) {

        if (follow.getText().equals("Follow")){

            try {

                String sqlSelect="SELECT  * FROM games.follow WHERE name='"+name.getText()+"' AND description='"+description.getText()+"' AND username='"+signInController.userName+"'";
                //statement=dataSource.getConnection().prepareStatement(sqlSelect);
                statement = connection.prepareStatement(sqlSelect);
                result=statement.executeQuery();

                if (result.next()){

                    if(result.getString("follow").equals("Follow")){

                        try {
                            String update = "UPDATE games.follow SET follow ='Unfollow'  WHERE  name='" + name.getText() + "' AND description='"+description.getText()+"' AND username='"+signInController.userName+"'";
                            //statement = dataSource.getConnection().prepareStatement(update);
                            statement = connection.prepareStatement(update);
                            statement.executeUpdate();
                           follow.setText("Unfollow");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (!result.next()){

                    recycleViewModel recycleViewModel1=new recycleViewModel();
                    recycleViewModel1.setName(name.getText());
                    recycleViewModel1.setComment(description.getText());
                    service.insertFollow(recycleViewModel1);
                    follow.setText("Unfollow");

                }
            }catch (SQLException x){
                x.printStackTrace();
            }

        }
        else if (follow.getText().equals("Unfollow")){
            try {

                String update = "UPDATE games.follow SET follow ='Follow'  WHERE  name='" + name.getText() + "' AND description='"+description.getText()+"'";
                //statement = dataSource.getConnection().prepareStatement(update);
                statement = connection.prepareStatement(update);
                statement.executeUpdate();
                follow.setText("Follow");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public ArrayList<recycleViewModel> selectAllIntoAdminList() {
        ArrayList<recycleViewModel> arrayList=new ArrayList<>();
        try {
            String sqlSelect="SELECT  * FROM games.comunity WHERE username='"+signInController.userName+"'";
            //statement=dataSource.getConnection().prepareStatement(sqlSelect);
            statement = connection.prepareStatement(sqlSelect);
            result=statement.executeQuery();
            while (result.next()){
                recycleViewModel recycleViewModel=new recycleViewModel();
                recycleViewModel.setName(result.getString("name"));
                recycleViewModel.setComment(result.getString("description"));
                if (result.getString("permission").equals("0")){
                    recycleViewModel.setFollow("Unhide");
                }else {
                    recycleViewModel.setFollow("Hide");
                }
                arrayList.add(recycleViewModel);
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
        return arrayList;
    }


}
