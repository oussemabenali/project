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

import first_sprint_crud.entities.Livreur;


import first_sprint_crud.util.MyDB;



import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author aziz
 */
public class LivreurService implements IService<Livreur> {
    
     Connection cnx;

    public LivreurService() {
        cnx = MyDB.getInstance().getConnection();
    }
    

   @Override
    public void ajouter(Livreur l) {


        try {
   String req = "insert into livreur(nom,prenom)"
   + "values( '" + l.getNom()+ "' ,   '" + l.getPrenom()+  "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livreur Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    
      @Override
    public void modifier(Livreur l) {
        
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE livreur SET nom = ?, prenom = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, l.getNom());
        ps.setString(2, l.getPrenom());
        ps.setInt(3, l.getId());
        
          ps.executeUpdate();
            System.out.println("Livreur Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }

        
    }
    
    @Override
    public void supprimer(int id) {
 try {
            String req = "delete from livreur  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Livreur deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public List<Livreur> recuperer() {
       List<Livreur> livreurs = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM livreur";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

            Livreur livreur = new Livreur(id, nom, prenom);
            livreurs.add(livreur);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des Livreurs : " + ex.getMessage());
    }

    return livreurs;
        
    }
    
    public Livreur recupererById(int idF) {
      Livreur  livreur = null;
    try {
        
    
      
        String sql = "SELECT * FROM livreur where id=" + " '"+ idF + "'  ";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

             livreur = new Livreur(id, nom, prenom);
   
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de Livreur : " + ex.getMessage());
    }

    return livreur;
    
    }
    
    
    public List<Livreur> recupererByNomPrenom(String nomF ,String prenomF) {
     List<Livreur> livreurs = new ArrayList<>();
     String sql = "SELECT * FROM livreur";
    try {
        
    
          if(nomF!="" && prenomF=="")
         sql = "SELECT * FROM livreur where nom LIKE '" + "%" +nomF+ "%" + "'  ";
          
           if(nomF=="" && prenomF!="")
         sql = "SELECT * FROM livreur where prenom LIKE '" + "%" +prenomF+ "%" + "'  ";
           
             if(nomF!="" && prenomF!="")
         sql = "SELECT * FROM livreur where nom LIKE '" + "%" +nomF+ "%" + "' and prenom like '"  + "%" +prenomF+ "%" + "'  " ;

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
          

            Livreur livreur = new Livreur(id, nom, prenom);
            livreurs.add(livreur);
   
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de coach : " + ex.getMessage());
    }

    return livreurs;
    
    }
    
}
