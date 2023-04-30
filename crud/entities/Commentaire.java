/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.entities;

/**
 *
 * @author ouss
 */
public class Commentaire {
      private int id;
      private String contenu;
      private Article article;

    public Commentaire(int id, String contenu, Article article) {
        this.id = id;
        this.contenu = contenu;
        this.article = article;
    }

    public Commentaire(String contenu, Article article) {
        this.contenu = contenu;
        this.article = article;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "contenu=" + contenu + ", article=" + article + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
