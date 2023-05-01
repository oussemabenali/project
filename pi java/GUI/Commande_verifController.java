/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import first_sprint_crud.entities.panier;
import first_sprint_crud.services.LivraisonService;
import first_sprint_crud.entities.Livraison;
import javafxpi.JavafxPi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.services.ProduitService;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.*;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Commande_verifController implements Initializable {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView qrvVew;
    @FXML
    private TextField veriftxt;
    @FXML
    private Button verifbtn;
    
  
    
      String myWeb=JavafxPi.myWeb2;
      
       ProduitService psm = new ProduitService(); 
    LivraisonService psl = new LivraisonService();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
     
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
   
            BitMatrix byteMatrix;
        try {
            byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
       
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
         } catch (WriterException ex) {
            java.util.logging.Logger.getLogger(Commande_verifController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        qrvVew.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }    

    @FXML
    private void verifbtnaction(ActionEvent event) throws IOException {
       
        if(veriftxt.getText().equals(myWeb))
        {
           for( panier panier2 : JavafxPi.pan)
            {
                 
            
                Produit pr =  psm.recupererById(panier2.getProd().getId());
                Livraison liv = new Livraison(JavafxPi.local,JavafxPi.l,pr);
                psl.ajouter(liv);
                
              
            }
            

            
             for( panier panier2 : JavafxPi.pan)
            {         
      TrayNotification tray  = new TrayNotification();
      AnimationType type = AnimationType.POPUP;
      tray.setAnimationType(type);
      tray.setTitle("Produit Ajouter");
      tray.setMessage("Produit avec le nom: "+ panier2.getProd().getNom()+ " est ajouté avec succès ");
      tray.setNotificationType(NotificationType.WARNING);
      Image image = new Image(panier2.getProd().getImage());
      tray.setImage(image);
      tray.showAndDismiss(Duration.millis(10000));
            }
             
             JavafxPi.pan.clear();
             
   Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
             
            }
        
             
    }
    
}
