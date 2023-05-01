/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class FavoriteProducts {
    private int id;
    private Produit prod;

    public FavoriteProducts(int id, Produit prod) {
        this.id = id;
        this.prod = prod;
    }

    public FavoriteProducts(Produit prod) {
        this.prod = prod;
    }

    public int getId() {
        return id;
    }

   
    public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "FavoriteProducts{" + "id=" + id + ", prod=" + prod + '}';
    }
    
    
}
