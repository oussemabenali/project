/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author ksaay
 */
public class Livraison {
    private int id;
    private String dateliv;
    private String localisation;
    private Livreur livreur;
    private Produit prod;

    public Livraison(String localisation, Livreur livreur, Produit prod) {
        this.localisation = localisation;
        this.livreur = livreur;
        this.prod = prod;
    }


    
    

    public Livraison(int id, String dateliv, String localisation, Livreur livreur,Produit prod) {
        this.id = id;
        this.dateliv = dateliv;
        this.localisation = localisation;
        this.livreur = livreur;
        this.prod = prod;
    }

    public Livraison(String dateliv, String localisation, Livreur livreur,Produit prod) {
        this.dateliv = dateliv;
        this.localisation = localisation;
        this.livreur = livreur;
        this.prod = prod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateliv() {
        return dateliv;
    }

    public void setDateliv(String dateliv) {
        this.dateliv = dateliv;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", dateliv=" + dateliv + ", localisation=" + localisation + ", livreur=" + livreur + ", prod=" + prod + '}';
    }
    
    

 
    
}
