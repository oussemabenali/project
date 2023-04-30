/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;

import first_sprint_crud.entities.Article;
import first_sprint_crud.entities.Commentaire;
import first_sprint_crud.entities.Livraison;
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
public class CommentaireService implements IService<Commentaire> {
    
     Connection cnx;

    public CommentaireService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Commentaire t) {
         try {
   String req = "insert into commentaire(article_id,contenu)"
   + "values( '" + t.getArticle().getId()+ "' ,   '" + t.getContenu()+  "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
      
    }

    @Override
    public void modifier(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from Commentaire  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Comment deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commentaire> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
    public List<Commentaire> recupererby_article(int ida) {
        
       List<Commentaire> comments = new ArrayList<>();
    try {
        
        String sql = "SELECT * from commentaire c  inner join article a on c.article_id = a.id  where c.article_id=" + " '"+ ida + "'  ";
     Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt(1);
            String contenu = rs.getString("contenu");
            
            Article a = new Article();
            a.setId(ida);
            //a.setId(ida);
             Commentaire comment = new Commentaire(id, contenu,a);
            comments.add(comment);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des articles : " + ex.getMessage());
    }
    
    return comments;
    
    
    }
    
    
    
}
