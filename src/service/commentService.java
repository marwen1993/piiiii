package service;

import DataBase.DataSource;
import Interface.commentInterface;
import controller.comunityController;
import controller.signInController;
import model.recycleViewModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class commentService implements commentInterface {

    DataSource dataSource;
    PreparedStatement statement;
    ResultSet result;
    public commentService(){
        dataSource=new DataSource();

    }


    @Override
    public void insertComment(String comment) {
        try {
            String sqlInsert = "INSERT INTO games.comment (name, username, comment, gamename, gameDesc, permission)  VALUES (?,?,?,?,?,?)";

            statement = dataSource.getConnection().prepareStatement(sqlInsert);

            statement.setString(1, signInController.name);
            statement.setString(2, signInController.userName);
            statement.setString(3, comment);
            statement.setString(4, comunityController.gameName);
            statement.setString(5, comunityController.gameDesc);
            statement.setString(6, signInController.commentper+"");

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
    public ArrayList<recycleViewModel> addDataToRecylcleView() {
       ArrayList<recycleViewModel> arrayList=new ArrayList<>();
        try {
            String sqlSelect="SELECT  * FROM games.comment WHERE permission='1' AND gamename='"+comunityController.gameName+"' AND gameDesc='"+comunityController.gameDesc+"'";
            statement=dataSource.getConnection().prepareStatement(sqlSelect);
            result=statement.executeQuery();
            while (result.next()){
                recycleViewModel recycleViewModel=new recycleViewModel();
                recycleViewModel.setName(result.getString(1));
                recycleViewModel.setUsername(result.getString(2));
                recycleViewModel.setComment(result.getString(3));
                arrayList.add(recycleViewModel);
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
        return arrayList;
    }
}
