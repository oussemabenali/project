/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;





//import org.controlsfx.control.Notifications;




import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import first_sprint_crud.entities.Reclamation;

import first_sprint_crud.entities.Reponse;


import first_sprint_crud.services.ReclamationService;
import first_sprint_crud.services.ReponseService;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class MainReclamationItemController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label sujet;
    @FXML
    private Label desc;
    @FXML
    private ImageView delete;
    @FXML
    private Label reponse;
    
    int id;
    int idrep;
    
    ReclamationService psrec = new ReclamationService(); 
    ReponseService psrep = new ReponseService();
    @FXML
    private AnchorPane pane1;
    Reclamation reclam;

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
    
     public void setData(Reclamation pan)
    {
       
            id = pan.getId();
    reclam = pan;
            Image image2;
            
            image2 = new Image("/Images/delete.png");
            delete.setImage(image2);
            sujet.setText("Sujet:  "+ pan.getSujet() +  " \n date creation :  \n " +pan.getDate_rec());
            desc.setText("Description:  "+pan.getDescription());
        
      
       if(psrep.recupererReclamationAvecReponse(pan.getId())!=null)
       {
          idrep = psrep.recupererReclamationAvecReponse(pan.getId()).getId();
           reponse.setText("Reponse:  " + psrep.recupererReclamationAvecReponse(id).getReponse());
       }
       else
       reponse.setText("Reponse:  Aucune Reponse");  
            
        
    }

    @FXML
    private void deleteaction(MouseEvent event) {
         psrep.supprimer(idrep);
         psrec.supprimer(id);
         pane1.getChildren().removeAll(pane1.getChildren());
    }

 
    
}
