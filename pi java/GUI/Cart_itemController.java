/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.panier;
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
import javafxpi.JavafxPi;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Cart_itemController implements Initializable {

    @FXML
    private AnchorPane pane1;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView image;
    @FXML
    private Label nom1;
    @FXML
    private Label qte1;
    @FXML
    private Label prix1;
    @FXML
    private ImageView delete;
 panier panier1;
    @FXML
    private ImageView moins;
    @FXML
    private ImageView plus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void setData(panier pan)
    {
        panier1=pan;
       
        Image image1;
       
            image1 = new Image(pan.getProd().getImage());
            image.setImage(image1);
      
        
        Image image2;
        Image image3;
        Image image4;
            
            image2 = new Image("/Images/delete.png");
            delete.setImage(image2);
            
            image3 = new Image("Images/plus.jpg");
            
            image4= new Image("Images/moins.png");
            moins.setImage(image4);
            plus.setImage(image3);
            
            nom1.setText("Nom: "+pan.getProd().getNom());
            qte1.setText("Qunatite: "+ pan.getQte());
            prix1.setText("Prix: "+ pan.getPrix());
        
        
        
            
        
    }

    @FXML
    private void deleteaction(MouseEvent event) {
         JavafxPi.pan.remove(panier1);
        pane1.getChildren().removeAll(pane1.getChildren());
    }

    @FXML
    private void moinsaction(MouseEvent event) {
        if(panier1.getQte()>1){
         panier1.setQte(panier1.getQte()-1);
        setData(panier1);
        }
    }

    @FXML
    private void plusaction(MouseEvent event) {
        panier1.setQte(panier1.getQte()+1);
        setData(panier1);
                
    }
    
}
