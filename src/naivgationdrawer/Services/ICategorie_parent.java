

package naivgationdrawer.Services;

import naivgationdrawer.connection.myconection;
import naivgationdrawer.Entite.Categorie_parent;
import static com.sun.javafx.scene.CameraHelper.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Computer
 */
public class ICategorie_parent implements naivgationdrawer.Interface.ICategorie_parent{
    Connection con;
    public ICategorie_parent() {
      con=myconection.getInsatance("jdbc:mysql://localhost:3306/pidevgame","root","").getConnection();
    }
    
  private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    

    @Override
    public ArrayList<Categorie_parent> afficher(){
      ArrayList<Categorie_parent> cat= new ArrayList();
    for (Categorie_parent et : select()){
          
        cat.add(et);
    
    }
    return cat;
    }

    @Override
    public ArrayList <Categorie_parent> select(){
        
        ArrayList<Categorie_parent> etudiants=new ArrayList();
        try {
            String req= "select * from categorie_parent";
            Statement stm = con.createStatement();
          ResultSet result=stm.executeQuery(req);
          
          while(result.next()){
              Categorie_parent e =new Categorie_parent();
                      e.setId(result.getInt(1));
                      e.setNom(result.getString(2));
                      
                      etudiants.add(e);
          }
          
        } catch (SQLException ex) {
           Logger.getLogger(ICategorie_parent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etudiants;   
    }

    @Override
    public Categorie_parent buscar(Categorie_parent Categorie_parent) {
       String sql = "SELECT * FROM categorie_parent WHERE id=?";
        Categorie_parent retorno = new Categorie_parent();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Categorie_parent.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Categorie_parent.setId(resultado.getInt("id"));
                Categorie_parent.setNom(resultado.getString("nom"));
                retorno = Categorie_parent;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categorie_parent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    
  
}
