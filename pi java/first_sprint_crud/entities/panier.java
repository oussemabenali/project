/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first_sprint_crud.entities;

import java.util.ArrayList;

/**
 *
 * @author Lord
 */
public class panier {
    
    int id;
    String taille ;
    int  qte;
    double prix;
    Produit prod;
    
    

    public panier() {
    }

    public panier(int qte, double prix, Produit prod) {
        this.qte = qte;
        this.prix = prix;
        this.prod = prod;
    }


    
    

    public panier(String taille, int qte, double prix, Produit prod) {
        this.taille = taille;
        this.qte = qte;
        this.prix = prix;
        this.prod = prod;
    }

    public panier(int id, String taille, int qte, double prix, Produit prod) {
        this.id = id;
        this.taille = taille;
        this.qte = qte;
        this.prix = prix;
        this.prod = prod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "panier{" + "taille=" + taille + ", qte=" + qte + ", prix=" + prix + ", prod=" + prod + '}';
    }

   
    
    
    
}
