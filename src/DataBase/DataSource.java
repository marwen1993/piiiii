package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DataSource {

    private  String url="jdbc:mysql://localhost:3306/games";
    private  String login="root";
    private  String password="";
    private Connection connection;

    private  static DataSource datasource;

    public  DataSource() {
        try{
            connection = DriverManager.getConnection(url,login,password);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public  static DataSource getInstance() {
        if(datasource==null) {
            datasource = new DataSource();
        }
        return datasource;

    }



}
