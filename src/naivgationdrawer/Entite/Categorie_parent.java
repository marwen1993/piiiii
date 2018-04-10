package naivgationdrawer.Entite;

import java.io.Serializable;

public class Categorie_parent implements Serializable{
    private String nom;
    private int id;

    public Categorie_parent() {
    }

    public Categorie_parent(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     @Override
    public String toString() {
        return this.nom;
    
    }
   
    
}
