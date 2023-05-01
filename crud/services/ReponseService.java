/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;

import first_sprint_crud.entities.Programme;
import first_sprint_crud.entities.Reponse;
import first_sprint_crud.entities.Reclamation;
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
 * @author arij
 */
public class ReponseService {
       Connection cnx;

    public ReponseService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
     public void ajouter(Reponse r) {
        try {
   String req = "insert into reponse(reclamation_id,reponse)"
   + "values( '" + r.getRec().getId()+ "',   '" + r.getReponse()+  "')";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
        
    }
     
         public void supprimer(int id) {
 try {
            String req = "delete from reponse where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Reponse deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         
         
           public List recuperer() {
  List<Reponse> reponses = new ArrayList<>();
    try {
        String sql = "select * from reponse ";

        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int recId = rs.getInt("id");

            
            Reponse reponse =  new Reponse();
            
              reponses.add(reponse);
        
        }

    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
    }

    return reponses;
}
         public Reponse recupererReclamationAvecReponse(int idF) {
    Reponse reponse = null;
    try {
        String sql = "SELECT r.id, r.date_rec, r.sujet, r.description, rp.id as idrep, rp.reponse " +
                     "FROM reclamation r " +
                     "INNER JOIN reponse rp ON r.id = rp.reclamation_id " +
                     "WHERE r.id = " + " '"+ idF + "'";

        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            int recId = rs.getInt("id");
            String dateRec = rs.getString("date_rec");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            
             int repId = rs.getInt("idrep");
             String reponseS = rs.getString("reponse");

            Reclamation reclamation = new Reclamation(recId, dateRec, sujet, description);
            
            reponse =  new Reponse(repId,reclamation,reponseS);
        }

    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la réclamation : " + ex.getMessage());
    }

    return reponse;
}
         
         
         public void update(Reponse r) {
        
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE reponse SET reclamation_id = ?, reponse = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setInt(1, r.getRec().getId());
        ps.setString(2, r.getReponse());
        ps.setInt(3, r.getId());
        
          ps.executeUpdate();
            System.out.println("Reponse Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
         }

}
