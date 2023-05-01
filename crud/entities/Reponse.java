/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author USER
 */
public class Reponse {
     private int id;
     private Reclamation rec;
     private String reponse;

    public Reponse(int id, Reclamation rec, String reponse) {
        this.id = id;
        this.rec = rec;
        this.reponse = reponse;
    }

    public Reponse(Reclamation rec, String reponse) {
        this.rec = rec;
        this.reponse = reponse;
    }

    public int getId() {
        return id;
    }

    public Reclamation getRec() {
        return rec;
    }

    public void setRec(Reclamation rec) {
        this.rec = rec;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", rec=" + rec + ", reponse=" + reponse + '}';
    }
     
     
    
}
