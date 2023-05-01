/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import first_sprint_crud.entities.Livraison;
import first_sprint_crud.entities.Livreur;
import first_sprint_crud.entities.panier;
import first_sprint_crud.services.ProduitService;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.services.LivraisonService;

import first_sprint_crud.services.LivreurService;
import first_sprint_crud.entities.Livreur;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafxpi.JavafxPi;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ShopinCartController implements Initializable {

    @FXML
    private ImageView home;
    @FXML
    private ImageView shop;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;
    @FXML
    private Button commande;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField local;
    
    ProduitService psm = new ProduitService(); 
    LivraisonService psl = new LivraisonService();
    LivreurService psliv = new LivreurService();
    @FXML
    private Label livreurL;
    @FXML
    private ComboBox<String> livreur;
    @FXML
    private Label localisation;
    
     ObservableMap<String, Livreur> livMap = FXCollections.observableHashMap();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          if (JavafxPi.pan.size()==0)
        {
            prodvide.setText("Panier vide");
             commande.setVisible(false);
             livreurL.setVisible(false);
             localisation.setVisible(false);
             livreur.setVisible(false);
             local.setVisible(false);
             
        }
            else
         prodvide.setText("Votre Produits:");  
          
          for (Livreur c : psliv.recuperer()) {
    String key = c.getNom() + " " + c.getPrenom();
    livMap.put(key, c);
}
livreur.setItems(FXCollections.observableArrayList(livMap.keySet()));
            
        showpanier();
    }   
    
     public void showpanier()
    {
        int column=0;
        int row=1;
        int x=0;
       
         try {
        for(panier pan : JavafxPi.pan)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Cart_item.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                Cart_itemController items = fxmlloader.getController();
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

    @FXML
    private void commandeaction(ActionEvent event) throws IOException {
        
        
        
       
        if(!local.getText().isEmpty() || livreur.getSelectionModel().getSelectedItem() == null ){
            
        Random r = new Random();
        String  y =""+ r.nextInt(99999);
        JavafxPi.myWeb2=y;
          
            String selectedCoach = livreur.getSelectionModel().getSelectedItem();
            Livreur c = livMap.get(selectedCoach);
            
            JavafxPi.l=c;
            JavafxPi.local=local.getText();
            
            
            
   Parent root = FXMLLoader.load(getClass().getResource("Commande_verif.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show(); 
        }
    }

    @FXML
    private void selectLivreur(ActionEvent event) {
    }
    
}
