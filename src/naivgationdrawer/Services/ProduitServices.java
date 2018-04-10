
package naivgationdrawer.Services;

import naivgationdrawer.connection.myconection;
import naivgationdrawer.Entite.Categorie;
import naivgationdrawer.Entite.Produit;
import naivgationdrawer.Interface.IProduit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ProduitServices implements IProduit{

  private PreparedStatement ps;
    //conection
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
Connection con;
 
    public ProduitServices() {
      con=myconection.getInsatance("jdbc:mysql://localhost:3306/pidevgame","root","").getConnection();
    }
    
///les methodes
    
    @Override
    public boolean inserir(Produit Produit) {
      String sql = "INSERT INTO produit(categorie_id,nom,libelle,prix,nombre_point,stocke,visibilite,image) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
           // stmt.setInt(1,Produit.getId());
            stmt.setInt(1,Produit.getCategorie_id().getId());
            
            stmt.setString(2,Produit.getNom());
            stmt.setString(3,Produit.getLibelle());
            stmt.setDouble(4, Produit.getPrix());
            stmt.setInt(5,Produit.getNombre_point());
            
            stmt.setInt(6,Produit.getStocke());
            stmt.setInt(7,Produit.getVisibilite());
            stmt.setString(8,Produit.getImage());
        
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean alterar(Produit Produit) {
    String sql = "UPDATE produit SET id=?,categorie_id=?,nom=?,libelle=?,prix=?,nombre_point=?,stocke=?,visibilite=?,image=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
           stmt.setInt(1,Produit.getId());
            stmt.setInt(2,Produit.getCategorie_id().getId());
          
            
            stmt.setString(3,Produit.getNom());
            stmt.setString(4,Produit.getLibelle());
            stmt.setDouble(5, Produit.getPrix());
            stmt.setInt(6,Produit.getNombre_point());
            
            stmt.setInt(7,Produit.getStocke());
            stmt.setInt(8,Produit.getVisibilite());
            stmt.setString(9,Produit.getImage());
            stmt.setInt(10,Produit.getId());
          
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
      
    }

    @Override
    public boolean remover(Produit Produit) {
     String sql = "DELETE FROM produit WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,Produit.getId());
           
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public ArrayList<Produit> listar() {
        String sql = "SELECT * FROM produit";
        ArrayList<Produit> retorno;
        retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produit Produit = new Produit();
                Categorie Categorie = new Categorie();
                
                Produit.setId(resultado.getInt("id"));
                Categorie.setId(resultado.getInt("categorie_id"));
                
                Produit.setNom(resultado.getString("nom"));
                Produit.setLibelle(resultado.getString("libelle"));
                Produit.setPrix((float) resultado.getDouble("prix"));
                Produit.setNombre_point(resultado.getInt("nombre_point"));
                Produit.setStocke(resultado.getInt("stocke"));
                Produit.setVisibilite(resultado.getInt("visibilite"));
                Produit.setImage(resultado.getString("image"));
                
                
                
                ICategorieService ICategorieService = new ICategorieService();
                ICategorieService.setConnection(connection);
                 
                
                
                Categorie = ICategorieService.buscar(Categorie);

                Produit.setCategorie_id(Categorie);
                
               
                retorno.add(Produit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }


    @Override
    public ArrayList<Produit> list() {
       
       String sql = "select min(prix),id,categorie_id, nom,libelle,prix,nombre_point,stocke,visibilite,image from produit group by categorie_id";
       //String sql = "select * from produit order by prix DESC ";
  
        ArrayList<Produit> retorno;
        retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produit Produit = new Produit();
                Categorie Categorie = new Categorie();
                
                Produit.setId(resultado.getInt("id"));
                Categorie.setId(resultado.getInt("categorie_id"));
                
                Produit.setNom(resultado.getString("nom"));
                Produit.setLibelle(resultado.getString("libelle"));
                Produit.setPrix((float) resultado.getDouble("prix"));
                Produit.setNombre_point(resultado.getInt("nombre_point"));
                Produit.setStocke(resultado.getInt("stocke"));
                Produit.setVisibilite(resultado.getInt("visibilite"));
                Produit.setImage(resultado.getString("image"));
                
                
                
                ICategorieService ICategorieService = new ICategorieService();
                ICategorieService.setConnection(connection);
                 
                
                
                Categorie = ICategorieService.buscar(Categorie);

                Produit.setCategorie_id(Categorie);
                
               
                retorno.add(Produit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;        
      
    }
    @Override
    public ArrayList<Produit> listt() {
       
       //String sql = "select max(prix),id,categorie_id, nom,libelle,prix,nombre_point,stocke,visibilite,image from produit group by categorie_id";
       String sql = "select * from produit order by prix DESC ";
  
        ArrayList<Produit> retorno;
        retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produit Produit = new Produit();
                Categorie Categorie = new Categorie();
                
                Produit.setId(resultado.getInt("id"));
                Categorie.setId(resultado.getInt("categorie_id"));
                
                Produit.setNom(resultado.getString("nom"));
                Produit.setLibelle(resultado.getString("libelle"));
                Produit.setPrix((float) resultado.getDouble("prix"));
                Produit.setNombre_point(resultado.getInt("nombre_point"));
                Produit.setStocke(resultado.getInt("stocke"));
                Produit.setVisibilite(resultado.getInt("visibilite"));
                Produit.setImage(resultado.getString("image"));
                
                
                
                ICategorieService ICategorieService = new ICategorieService();
                ICategorieService.setConnection(connection);
                 
                
                
                Categorie = ICategorieService.buscar(Categorie);

                Produit.setCategorie_id(Categorie);
                
               
                retorno.add(Produit);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;        
      
    }
    @Override
    public boolean changer(Produit Produit) {
    
    String sql = "UPDATE produit SET visibilite=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
              stmt.setInt(1,Produit.getVisibilite());
              stmt.setInt(2,Produit.getId());
          
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ICategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
//    public ResultSet getResultSet() {
//  String sql = "SELECT * FROM produit";
//ResultSet rs ;
//        ObservableList<Produit> produit = FXCollections.observableArrayList();
//        try {
//     
//       ps = connection.prepareStatement(sql);
//             rs = ps.executeQuery();
//           
//            return rs;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
