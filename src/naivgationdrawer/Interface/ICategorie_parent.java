

package naivgationdrawer.Interface;

import naivgationdrawer.Entite.Categorie_parent;
import java.util.ArrayList;




public interface ICategorie_parent {
    
     ArrayList<Categorie_parent> afficher();
     ArrayList <Categorie_parent> select();
      Categorie_parent buscar(Categorie_parent Categorie_parent); 
}
