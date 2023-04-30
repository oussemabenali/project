/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;

import first_sprint_crud.entities.Article;


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
 * @author ouss
 */
public class ArticleService implements IService<Article> {
    
     Connection cnx;

    public ArticleService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Article a) {


        try {
   String req = "insert into article(sujet_art,titre,image)"
   + "values( '" + a.getSujet_art()+ "' ,   '" + a.getTitre()+  "', '" + a.getImage()+  "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Article Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
    
     @Override
    public void modifier(Article a) {
         if (a.getImage().contains(MyDB.url_upload)) {
            a.setImage(a.getImage().replace(MyDB.url_upload, ""));
        }
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE article SET sujet_art = ?, titre = ?, image = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, a.getSujet_art());
        ps.setString(2, a.getTitre());
        ps.setString(3, a.getImage());
        ps.setInt(4, a.getId());
        
          ps.executeUpdate();
            System.out.println("Article Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
        
    }
    
    @Override
    public void supprimer(int id) {
 try {
            String req = "delete from article  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Article deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public List<Article> recuperer() {
       List<Article> articles = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM article";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String sujet = rs.getString("sujet_art");
            String titre = rs.getString("titre");
            String image = rs.getString("image");
            image = MyDB.url_upload + image;

            Article article = new Article(id, sujet, titre,image);
            articles.add(article);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des articles : " + ex.getMessage());
    }

    return articles;
        
    }
    
    public Article recupererById(int idF) {
         Article  article = null;
    try {
        
        String sql = "SELECT * FROM article where id=" + " '"+ idF + "'  ";
     Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String sujet = rs.getString("sujet_art");
            String titre = rs.getString("titre");
            String image = rs.getString("image");
          

             article = new Article(id, sujet, titre,image);
            
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des articles : " + ex.getMessage());
    }
    
    return article;
    
    
    }
    
    public List<Article> recupererBySujetTitre(String s) {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT * FROM article";
        
         try {
        
      if(s.length()>=1)
      sql = "SELECT * FROM article where sujet_art LIKE '" + "%" +s+ "%" + "'  or titre LIKE '" + "%" +s+ "%" + "'  ";
   
          Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String sujet = rs.getString("sujet_art");
            String titre = rs.getString("titre");
            String image = rs.getString("image");
          image = MyDB.url_upload + image;

            Article article = new Article(id, sujet, titre,image);
            articles.add(article);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des articles : " + ex.getMessage());
    }

    return articles;
         
    }
    
}
