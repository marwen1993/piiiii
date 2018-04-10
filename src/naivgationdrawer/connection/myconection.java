/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naivgationdrawer.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Computer
 */
public class myconection {
    private Connection con;
    private static myconection instance=null;
    private myconection(String url,String login,String pwd)
    {
        try {
            con=DriverManager.getConnection(url,login,pwd);
        } catch (SQLException ex) {
            Logger.getLogger(myconection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public static myconection getInsatance(String url,String login,String pwd){
     if(instance==null)
     {
         instance = new myconection(url,login,pwd);
     }
         return instance;
 }
 public Connection getConnection()
 {
     return con;
 }
}
