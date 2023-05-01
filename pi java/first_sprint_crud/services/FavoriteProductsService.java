/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;



import first_sprint_crud.entities.FavoriteProducts;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aziz
 */
public class FavoriteProductsService {
    Connection cnx;

    public FavoriteProductsService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
   
    public void ajouter(FavoriteProducts f) {
        try {
   String req = "insert into favorite_products(product_id_id)"
   + "values( '" + f.getProd().getId()+ "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Favorite Product Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    
    public void supprimer(int id) {
 try {
            String req = "delete from favorite_products  where product_id_id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Favorite Product deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
 public List<Produit> recupererFavorite() {
   List<Produit> produits = new ArrayList<>();
   try {
        String sql = "SELECT p.id, p.nom_prod, p.prix_prod, p.description, p.photo " +
                     "FROM produit p " +
                     "JOIN favorite_products f ON p.id = f.product_id_id";

        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom_prod");
            double prix = rs.getDouble("prix_prod");
            String description = rs.getString("description");
            String image = rs.getString("photo");
            image = MyDB.url_upload + image;
            
            Produit produit = new Produit(id, nom, prix, description, image);
            produits.add(produit);
        }

    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des produits : " + ex.getMessage());
    }

    return produits;
}
    
}
