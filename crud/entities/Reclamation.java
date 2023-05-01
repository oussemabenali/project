/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class Reclamation {
    private int id;
    private String date_rec;
    private String sujet;
    private String description;

    public Reclamation(int id) {
        this.id = id;
    }
    
    

    public Reclamation(int id, String date_rec, String sujet, String description) {
        this.id = id;
        this.date_rec = date_rec;
        this.sujet = sujet;
        this.description = description;
    }
    
     public Reclamation(String date_rec, String sujet, String description) {
         
        this.date_rec = date_rec;
        this.sujet = sujet;
        this.description = description;
    }

    public Reclamation(int id, String sujet, String description) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
    }
     
     
     

    public Reclamation(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
    }
    
     
     

    public int getId() {
        return id;
    }

    public String getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", date_rec=" + date_rec + ", sujet=" + sujet + ", description=" + description + '}';
    }
     
     


    
    
}
