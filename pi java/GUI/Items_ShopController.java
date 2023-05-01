/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import first_sprint_crud.entities.Produit;
import first_sprint_crud.entities.FavoriteProducts;
import first_sprint_crud.services.FavoriteProductsService;
import first_sprint_crud.services.ProduitService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Items_ShopController implements Initializable {

    @FXML
    private Label namelabel1;
    @FXML
    private Label prixlabel1;
    @FXML
    private ImageView prodimg1;
    
    private Listner listner;
    
     private Produit prod;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView heart;
    
    @FXML 
    private void click(MouseEvent mouseEvent)
    {
        listner.onClickListener(prod);
    }
    
    FavoriteProductsService fp = new FavoriteProductsService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
       
    }    
    
     public void  refresh()
     {
         vbox.getChildren().removeAll(vbox.getChildren());
     }
    public void setData(Produit prod, Listner listner) 
    {
         Image image2 = new Image("/Images/heart_border.png");
          Image  image1 = new Image("/Images/heart.png");
          int x=0;
          
       
        this.prod=prod;
        this.listner=listner;
        namelabel1.setText(prod.getNom());
        prixlabel1.setText(prod.getPrix()+ "DT");
        
        Image image;
        image = new Image(prod.getImage());
        prodimg1.setImage(image);
        
          for(Produit p : fp.recupererFavorite()){
              if(this.prod.getId()==p.getId()){
                 heart.setImage(image1);  
                 x=1;
              }
         }
          if(x==0)
          {
               heart.setImage(image2); 
          }
          
       
       
        
    }    

    @FXML
    private void heartaction(MouseEvent event) {
         Image image2 = new Image("/Images/heart_border.png");
          Image  image1 = new Image("/Images/heart.png");
          int x=0;
        for(Produit p : fp.recupererFavorite()){
            if(this.prod.getId()==p.getId()){
            heart.setImage(image1); 
            fp.supprimer(p.getId());
            x=1;
            }
        }
        if(x==0)
        {
            FavoriteProducts f= new FavoriteProducts(this.prod);
            heart.setImage(image2); 
            fp.ajouter(f);
        }
        setData(this.prod,this.listner);
        
    }
}