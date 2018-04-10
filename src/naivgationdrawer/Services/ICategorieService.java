
package naivgationdrawer.Services;

import naivgationdrawer.Entite.Categorie;
import naivgationdrawer.Entite.Categorie_parent;
import naivgationdrawer.Entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import naivgationdrawer.connection.myconection;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;


public class ICategorieService implements naivgationdrawer.Interface.ICategorie{
 Connection con;
    public ICategorieService() {
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
    public boolean ajouterCategorie(Categorie C) {
        
       String sql = "INSERT INTO categorie(categorie_parent_id,nom) VALUES(?,?)";     
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setInt(1,C.getId());
            stmt.setInt(1,C.getCategorie_parent().getId());
            stmt.setString(2,C.getNom());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public int Calculertotal() {
        String req = "SELECT COUNT(*) FROM produit ";
        Produit type = null;
        int nombreLignes = 0;
        try {
            PreparedStatement ps=connection.prepareStatement(req);
           // ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                nombreLignes = resultSet.getInt(1);
            }

           // resultSet.last();
            //on récupère le numéro de la ligne 
           // nombreLignes = resultSet.getRow();
            //on replace le curseur avant la première ligne 
           // resultSet.beforeFirst();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }
     public int Calculer(boolean visibilite) {
        String req = "SELECT COUNT(*) AS count FROM produit where visibilite =?";
       Produit u = null;
        int nombreLignes = 0;
        try {
             PreparedStatement ps=connection.prepareStatement(req);
            ps.setBoolean(1, visibilite);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                nombreLignes = resultSet.getInt(1);
            }
        //  resultSet.last(); 
//            //on récupère le numéro de la ligne 
          // nombreLignes = resultSet.getRow(); 
//            //on replace le curseur avant la première ligne 
          // resultSet.beforeFirst(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLignes;
    }
    

    

    @Override
  
      public boolean supprimerCategorie(Categorie Categorie) {
        String sql = "DELETE FROM categorie WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,Categorie.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }



    @Override
    public ArrayList<Categorie> afficher() {
        ArrayList<Categorie> cat= new ArrayList();
        list().stream().forEach((et) -> {
         cat.add(et);
     });
    return cat;
    }

    @Override
    public ArrayList<Categorie> select() {
     ArrayList<Categorie> etudiants=new ArrayList();
     Categorie_parent categoria = new Categorie_parent();
        try {
            String req= "select * from categorie";
            Statement stm = con.createStatement();
          ResultSet result=stm.executeQuery(req);
          
          while(result.next()){
              
                Categorie Categorie = new Categorie();
                Categorie_parent Categorie_parent = new Categorie_parent();
                

                Categorie.setId(result.getInt("id"));
                Categorie.setNom(result.getString("nom"));
                Categorie_parent.setId(result.getInt("id"));
               
                
                Categorie.setCategorie_parent(Categorie_parent);
               
                etudiants.add(Categorie);
         
          }
          
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etudiants;
    }

    @Override
    public boolean modifierCategorie(Categorie C) {
     String sql = "UPDATE categorie SET  categorie_parent_id=?, nom=?,  WHERE id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setInt(1,C.getCategorie_parent_id().getId());
            stmt.setString(2,C.getNom());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }   
    }
      
    @Override
     public boolean alterar(Categorie C) {
        String sql = "UPDATE categorie SET id=?,categorie_parent_id=?,nom=?  WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
            stmt.setInt(1,C.getId());
            stmt.setInt(2,C.getCategorie_parent().getId());
            stmt.setString(3,C.getNom());
           stmt.setInt(4,C.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
   
    @Override
    public List<Categorie> listar() {
        String sql = "SELECT * FROM categorie";
        List<Categorie> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Categorie Categorie = new Categorie();
                Categorie_parent Categorie_parent = new Categorie_parent();
                

                Categorie.setId(resultado.getInt("id"));
                Categorie.setNom(resultado.getString("nom"));
                Categorie_parent.setId(resultado.getInt("id"));
                //cliente.setCdCliente(resultado.getInt("cdCliente"));

                //Obtendo os dados completos do Cliente associado à Venda
                ICategorie_parent ICategorie_parent = new ICategorie_parent();
                ICategorie_parent.setConnection(connection);
    
                
                
                Categorie_parent = ICategorie_parent.buscar(Categorie_parent);

                
                Categorie.setCategorie_parent(Categorie_parent);
               
                retorno.add(Categorie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public ArrayList<Categorie> list() {
    String sql = "SELECT * FROM categorie";
        ArrayList<Categorie> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Categorie Categorie = new Categorie();
                Categorie_parent Categorie_parent = new Categorie_parent();
                

                Categorie.setId(resultado.getInt("id"));
                Categorie.setNom(resultado.getString("nom"));
                Categorie_parent.setId(resultado.getInt("id"));
                //cliente.setCdCliente(resultado.getInt("cdCliente"));

                //Obtendo os dados completos do Cliente associado à Venda
                ICategorie_parent ICategorie_parent = new ICategorie_parent();
                ICategorie_parent.setConnection(connection);
                Categorie_parent = ICategorie_parent.buscar(Categorie_parent);

                
                Categorie.setCategorie_parent(Categorie_parent);
               
                retorno.add(Categorie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public Categorie buscar(Categorie Categorie) {   
        String sql = "SELECT * FROM categorie WHERE id=?";
        Categorie retorno = new Categorie();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,Categorie.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Categorie_parent Categorie_parent = new Categorie_parent();
               
                Categorie.setId(resultado.getInt("id"));
                Categorie.setNom(resultado.getString("nom"));
                Categorie_parent.setId(resultado.getInt("id"));
                Categorie.setCategorie_parent(Categorie_parent);
                
                retorno = Categorie;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
   
}
