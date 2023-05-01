/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import first_sprint_crud.entities.Produit;
import first_sprint_crud.services.FavoriteProductsService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Items_FavoriteController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label namelabel1;
    @FXML
    private Label prixlabel1;
    @FXML
    private ImageView prodimg1;
    @FXML
    private ImageView heart;
    
    private Listner listner;
    
     private Produit prod;
    @FXML
    private AnchorPane panel;
    
     private Stage stage;
    private Scene scene;
    private Parent root;

    
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
          Image  image1 = new Image("/Images/broke.png");
          int x=0;
          
       
        this.prod=prod;
        this.listner=listner;
        namelabel1.setText(prod.getNom());
        prixlabel1.setText(prod.getPrix()+ "DT");
        
        Image image;
        image = new Image(prod.getImage());
        prodimg1.setImage(image);
        heart.setImage(image1);
        
          
          
       
       
        
    }    

    @FXML
    private void heartaction(MouseEvent event) throws IOException {
        
        
   fp.supprimer(this.prod.getId());
      
   Parent root = FXMLLoader.load(getClass().getResource("FavoriteProducts.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
        
      
                 
                
    }

    
    
}
