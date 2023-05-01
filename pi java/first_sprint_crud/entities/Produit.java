/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class Produit {
    private int id;
    private String nom;
    private double prix;
    private String description;
    private String image;

    public Produit(int id) {
        this.id = id;
    }
    
    

    public Produit(int id, String nom, double prix, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public Produit(String nom, double prix, String description, String image) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
