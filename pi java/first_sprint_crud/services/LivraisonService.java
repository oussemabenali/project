/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;


import first_sprint_crud.entities.Livraison;
import first_sprint_crud.entities.Livreur;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aziz
 */
public class LivraisonService implements IService<Livraison> {
     Connection cnx;

    public LivraisonService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public List<Livraison> recuperer() {
       List<Livraison> Livraisons = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM livraison liv inner join livreur  l on l.id = liv.livreur_id inner join produit p on p.id = liv.prod_id";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt(1);
            int  id_livreur = rs.getInt(2);
            int id_prod = rs.getInt(3);
            String date_livraison = rs.getString("dateliv");
            String localisation = rs.getString(5);
            String nom_livreur = rs.getString(7);
            String prenom_livreur = rs.getString(8);
         
            String nom = rs.getString(10);
            double prix = rs.getDouble(11);
            String description = rs.getString(12);
            String image = rs.getString(13);
            image = MyDB.url_upload + image;

          
            Livreur livreur = new Livreur(id_livreur,nom_livreur,prenom_livreur);
            Produit prod = new Produit(id_prod, nom, prix, description, image);
            Livraison livraison = new Livraison(id, date_livraison, localisation,livreur,prod);
            Livraisons.add(livraison);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des Livraisons : " + ex.getMessage());
    }

    return Livraisons;
        
    }
    public void supprimer(int id) {
 try {
            String req = "delete from livraison  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("livraison deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void ajouter(Livraison c) {

  DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();
              
        try {
   String req = "insert into livraison(livreur_id,dateliv,localisation,prod_id)"
   + "values( '" + c.getLivreur().getId() + "' ,   '" + date.format(now)+  "' ,  '" + c.getLocalisation()+ "' ,  '" + c.getProd().getId() +   "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    @Override
    public void modifier(Livraison c) {
        
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE livraison SET livreur_id = ?, dateliv = ?, localisation = ?, prod_id = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setInt(1, c.getLivreur().getId());
        ps.setString(2, c.getDateliv());
        ps.setString(3, c.getLocalisation());
        ps.setInt(4, c.getProd().getId());
        ps.setInt(5, c.getId());
        
        
          ps.executeUpdate();
            System.out.println("Livraison Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }

        
    }
    
}
