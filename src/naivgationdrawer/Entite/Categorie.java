
package naivgationdrawer.Entite;


public class Categorie{

private int id;
private Categorie_parent categorie_parent;
private String nom;

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Categorie() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie_parent getCategorie_parent() {
        return categorie_parent;
    }

    public void setCategorie_parent(Categorie_parent categorie_parent) {
        this.categorie_parent = categorie_parent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

@Override
    public String toString() {
        return this.nom;
    }

}
