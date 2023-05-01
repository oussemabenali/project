/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import first_sprint_crud.entities.Livraison;
import first_sprint_crud.entities.Livreur;
import first_sprint_crud.services.ProduitService;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.services.LivraisonService;

import first_sprint_crud.services.LivreurService;
import first_sprint_crud.entities.Livreur;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LivraisonController implements Initializable {

    @FXML
    private ImageView home;
    @FXML
    private ImageView shop;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
        ProduitService psm = new ProduitService(); 
    LivraisonService psl = new LivraisonService();
    LivreurService psliv = new LivreurService();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
          if (psl.recuperer().size()==0)
        {
            prodvide.setText("Panier vide");
           
        }
            else
         prodvide.setText("Votre Produits:");  
          
ObservableList<Livraison> items = FXCollections.observableArrayList(psl.recuperer());
            
        show(items);
    }   
    
    
    public void show(ObservableList<Livraison> items2 )
    {
        int column=0;
        int row=1;
        int x=0;
       
         try {
        for(Livraison pan :items2)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Livraison_item.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                GUI.Livraison_itemController items = fxmlloader.getController();
                items.setData(pan);
                
                if(column == 1 )
                {
                    column = 0;
                    row++;
                }
                
                grid.add(pane,column++,row);
                
                //width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                
                
                GridPane.setMargin(pane, new Insets(10));
    
        }
        } catch (IOException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

     @FXML
    private void homeaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("MainFront.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void shopaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    
}
