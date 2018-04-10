
package naivgationdrawer.Entite;

import java.io.File;



public class Produit {
    private int id;
    private Categorie categorie_id;
    private String user_id;
    private String nom;
    private String libelle;
    private double prix;
    private int nombre_point;
    private int stocke;
    private int visibilite;
    private String image;
    

    public Produit(int id, String nom, String libelle, double prix, int nombre_point, int stocke, String image) {
        this.id = id;
        this.nom = nom;
        this.libelle = libelle;
        this.prix = prix;
        this.nombre_point = nombre_point;
        this.stocke = stocke;
        this.image = image;
        
    }

    public Produit() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(Categorie categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNombre_point() {
        return nombre_point;
    }

    public void setNombre_point(int nombre_point) {
        this.nombre_point = nombre_point;
    }

    public int getStocke() {
        return stocke;
    }

    public void setStocke(int stocke) {
        this.stocke = stocke;
    }

    public int getVisibilite() {
        return visibilite;
    }

    public void setVisibilite(int visibilite) {
        this.visibilite = visibilite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
