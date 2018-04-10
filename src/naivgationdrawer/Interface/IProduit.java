
package naivgationdrawer.Interface;

import naivgationdrawer.Entite.Produit;
import java.util.ArrayList;

public interface IProduit {
   public boolean inserir(Produit Produit) ;

    public boolean alterar(Produit Produit);
    public boolean changer(Produit Produit);
    public boolean remover(Produit Produit);
    public ArrayList<Produit> listar() ;
    public ArrayList<Produit> list() ;
    public ArrayList<Produit> listt();
 
}
