/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import first_sprint_crud.entities.Livraison;
import first_sprint_crud.entities.Livreur;
import first_sprint_crud.services.ProduitService;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.services.LivraisonService;

import first_sprint_crud.services.LivreurService;
import first_sprint_crud.entities.Livreur;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Livraison_itemController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView image;
    @FXML
    private Label nom1;
    @FXML
    private Label prix1;
    @FXML
    private Label local;
    @FXML
    private ImageView delete;
    
        
    ProduitService psm = new ProduitService(); 
    LivraisonService psl = new LivraisonService();
    LivreurService psliv = new LivreurService();

    Livraison liv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  public void setData(Livraison pan)
    {
       
        liv = pan;
        Image image1;
       
        image1 = new Image(pan.getProd().getImage());
        image.setImage(image1);
        
        Image image2;
            
            image2 = new Image("/Images/cancel.png");
            delete.setImage(image2);
            
            nom1.setText("Nom: "+pan.getProd().getNom());
            
            local.setText("Localisation: "+pan.getLocalisation());
           
            prix1.setText("Prix: "+ pan.getProd().getPrix());
        
        
        
            
        
    }
    @FXML
    private void deleteaction(MouseEvent event) {
       psl.supprimer(liv.getId());
       pane1.getChildren().removeAll(pane1.getChildren());
       
        
        
    }
    
}
