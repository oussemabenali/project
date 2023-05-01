/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import first_sprint_crud.entities.Reclamation;
import first_sprint_crud.entities.Reponse;
import first_sprint_crud.services.ReclamationService;
import first_sprint_crud.services.ReponseService;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class MainReposeController implements Initializable {

    @FXML
    private TableView<Reclamation> tabview;
    @FXML
    private TableColumn<Reclamation, Integer> idT;
    @FXML
    private TableColumn<Reclamation, String> sujetT;
    @FXML
    private TableColumn<Reclamation, String> descT;
    @FXML
    private TextArea reponse;
    int val;
    int idrep;
    
        ReclamationService psrec = new ReclamationService(); 
        ReponseService psrep = new ReponseService();
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Label resp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     add.setVisible(false);
     update.setVisible(false);
        
         show();
    }    

    @FXML
    private void save(ActionEvent event) {
        
                 if(reponse.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
         }
                 
                 else{
                     List<Reclamation> reclamations = psrec.recuperer();
                     for (Reclamation rec : reclamations){
                         if(rec.getId()==val){
                          Reponse reponsee = new Reponse(rec,reponse.getText());
                          psrep.ajouter(reponsee);
                          reponse.setText("");
                         }
                     }
                     
                     
                 }
    }

      public void show()
    {
       
          

      

      idT.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
       sujetT.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("sujet"));
       descT.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
        ObservableList<Reclamation> items = FXCollections.observableArrayList(psrec.recuperer());
        
        tabview.setItems(items);

    }

    @FXML
    private void reponseget(MouseEvent event) {
        add.setVisible(true);
        
      Node node = (Node) event.getTarget();
    if (node instanceof TableCell) {
    TableCell cell = (TableCell) node;
    int  value = cell.getIndex();
    if (value > tabview.getItems().size()-1)
            return;
   // System.out.println(value);
    //System.out.println("Selected value: " + value);
    val = tabview.getItems().get(value).getId();
  Reponse rep  =  psrep.recupererReclamationAvecReponse(tabview.getItems().get(value).getId());
  //System.out.println(tabview.getItems().get(value).getId());
     resp.setText("Response to [ "+tabview.getItems().get(value).getId()+" ]");
    if(rep==null){
         add.setVisible(true);
          update.setVisible(false);
          reponse.setText("");
    }
    else
    {
        idrep = rep.getId();
         update.setVisible(true);
         add.setVisible(false);
         reponse.setText(rep.getReponse());
    }
    
}
    }

    @FXML
    private void update(ActionEvent event) {
        
           if(reponse.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
         }
                 
                 else{
                     List<Reclamation> reclamations = psrec.recuperer();
                     for (Reclamation rec : reclamations){
                         if(rec.getId()==val){
                          Reponse reponsee = new Reponse(idrep,rec,reponse.getText());
                          psrep.update(reponsee);
                          reponse.setText("");
                         }
                     }
                     
                     
                 }
    }
    
}
