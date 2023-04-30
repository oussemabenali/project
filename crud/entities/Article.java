/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author ouss
 */
public class Article {
    private int id;
    private String sujet_art;
    private String titre;
    private String image;

    public Article(){
        
    }
    public Article(int id, String sujet_art, String titre, String image) {
        this.id = id;
        this.sujet_art = sujet_art;
        this.titre = titre;
        this.image = image;
    }
     public Article(String sujet_art, String titre, String image) {
        this.sujet_art = sujet_art;
        this.titre = titre;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getSujet_art() {
        return sujet_art;
    }

    public void setSujet_art(String sujet_art) {
        this.sujet_art = sujet_art;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", sujet_art=" + sujet_art + ", titre=" + titre + ", image=" + image + '}';
    }

    public void setId(int id) {
        this.id = id;
    }
     
    
}
