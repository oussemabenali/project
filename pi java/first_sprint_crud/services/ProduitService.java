/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;       

import first_sprint_crud.entities.Produit;

import first_sprint_crud.util.MyDB;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

/**
 *
 * @author aziz
 */
public class ProduitService implements IService<Produit> {
    Connection cnx;

    public ProduitService() {
        cnx = MyDB.getInstance().getConnection();
    }
    

   @Override
    public void ajouter(Produit p) {

        DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();

        try {
   String req = "insert into produit(nom_prod,prix_prod,description,photo)"
   + "values( '" + p.getNom()+ "' ,   '" + p.getPrix()+ "' , '" + p.getDescription()+ "' ,'" + p.getImage()+ "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }

    @Override
    public void modifier(Produit p) {
        if (p.getImage().contains(MyDB.url_upload)) {
            p.setImage(p.getImage().replace(MyDB.url_upload, ""));
        }
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE produit SET nom_prod = ?, prix_prod = ?, description = ?, photo = ? WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, p.getNom());
        ps.setDouble(2, p.getPrix());
        ps.setString(3, p.getDescription());
        ps.setString(4, p.getImage());
        ps.setInt(5, p.getId());
        
          ps.executeUpdate();
            System.out.println("Produit Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }

        
    }

   @Override
    public void supprimer(int id) {
 try {
            String req = "delete from produit  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Produit deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> recuperer() {
       List<Produit> produits = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM produit";

       
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
    
    
    
  
     
    
    
    public Produit recupererById(int idF) {
    Produit produit = null ;
    try {
        
    
      
        String sql = "SELECT * FROM produit where id=" + " '"+ idF + "'  ";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom_prod");
            double prix = rs.getDouble("prix_prod");
            String description = rs.getString("description");
            String image = rs.getString("photo");
            image = MyDB.url_upload + image;
             produit = new Produit(id, nom, prix, description, image);
           
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de produit : " + ex.getMessage());
    }

    return produit;
        
    }
    
    
    public List<Produit> recupererByNameAndPrixOrderByPrix(String nomF , double prixFMin , double prixFMax , int p ) {
       List<Produit> produits = new ArrayList<>();
       String sql="SELECT * FROM produit";
    try {
        
        
         if(prixFMin!=-1 && prixFMax!=-1 && nomF=="" && p==0)
      {
          sql = "SELECT * FROM produit where prix_prod  BETWEEN " + " '"+ prixFMin + "'  AND " + " '"+ prixFMax + "' ";
      }
         
          if(prixFMin!=-1 && prixFMax!=-1 && nomF!="" && p==0 )
      {
    sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" + "' and prix_prod BETWEEN '" + prixFMin+ "'  AND " + " '"+ prixFMax + "'  ";    
      }
          
                if(prixFMin==-1 && prixFMax==-1 && nomF!="" && p==0)
      {
          sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" +"' ";
      }
        
    
      if(prixFMin!=-1 && prixFMax!=-1 && nomF=="" && p==1)
      {
          sql = "SELECT * FROM produit where prix_prod  BETWEEN " + " '"+ prixFMin + "'  AND " + " '"+ prixFMax + "' order by prix_prod ";
      }
      
        if(prixFMin!=-1 && prixFMax!=-1 && nomF=="" && p==2)
      {
          sql = "SELECT * FROM produit where prix_prod  BETWEEN " + " '"+ prixFMin + "'  AND " + " '"+ prixFMax + "' order by prix_prod desc ";
      }
        
      
       if(prixFMin!=-1 && prixFMax!=-1 && nomF!="" && p==1 )
      {
   sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" + "' and prix_prod BETWEEN '" + prixFMin+ "'  AND " + " '"+ prixFMax + "' order by prix_prod ";    
      }
       
         if(prixFMin!=-1 && prixFMax!=-1 && nomF!="" && p==2 )
      {
   sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" + "' and prix_prod BETWEEN '" + prixFMin+ "'  AND " + " '"+ prixFMax + "' order by prix_prod desc ";    
      }
       
       if(prixFMin==-1 && prixFMax==-1 && nomF!="" && p==1)
      {
          sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" +"'order by prix_prod ";
      }
       
        if(prixFMin==-1 && prixFMax==-1 && nomF!="" && p==2)
      {
          sql = "SELECT * FROM produit where nom_prod  LIKE '" + "%" +nomF+ "%" +"'order by prix_prod desc ";
      }
        
         if(prixFMin==-1 && prixFMax==-1 && nomF=="" && p==1)
      {
         sql = "SELECT * FROM produit order by prix_prod";
      }
         
            if(prixFMin==-1 && prixFMax==-1 && nomF=="" && p==2)
      {
         sql = "SELECT * FROM produit order by prix_prod desc";
      }
        
       
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
    
    
      /* public List<Produit> recupererOrderByPrix(int p) {
       List<Produit> produits = new ArrayList<>();
       String sql="SELECT * FROM produit";
       
    try {
        
    
        
      if(p==1){
         sql = "SELECT * FROM produit order by prix_prod";
      }
        if(p==2){
         sql = "SELECT * FROM produit order by prix_prod desc";
      }
      
      
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom_prod");
            double prix = rs.getDouble("prix_prod");
            String description = rs.getString("description");
            String image = rs.getString("photo");

            Produit produit = new Produit(id, nom, prix, description, image);
            produits.add(produit);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des produits : " + ex.getMessage());
    }

    return produits;
        
    }*/
    
    
}
