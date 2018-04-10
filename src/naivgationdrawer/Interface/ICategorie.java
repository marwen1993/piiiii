
package naivgationdrawer.Interface;

import naivgationdrawer.Entite.Categorie;
import java.util.ArrayList;
import java.util.List;


public interface ICategorie {
     public boolean ajouterCategorie(Categorie C);
     public boolean modifierCategorie(Categorie C) ;
     public boolean supprimerCategorie(Categorie Categorie);
     ArrayList<Categorie> afficher();
     ArrayList <Categorie> select();
     ArrayList <Categorie> list();
     public List<Categorie> listar();
     public boolean alterar(Categorie Categorie);
      Categorie buscar(Categorie Categorie);
    
}
