/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import first_sprint_crud.entities.Livreur;
import first_sprint_crud.services.LivreurService;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class MainLivreurController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TableView<Livreur> tabview;
    @FXML
    private TableColumn<Livreur, Integer> idT;
    @FXML
    private TableColumn<Livreur, String> nomT;
    @FXML
    private TableColumn<Livreur, String> prenomT;
    
    LivreurService psm = new LivreurService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         tabview.setEditable(true);

        // Set cell factory for nomT
        nomT.setCellFactory(TextFieldTableCell.forTableColumn());
       
        // Set cell factory for prenomT
        prenomT.setCellFactory(TextFieldTableCell.forTableColumn());
        
         show();
    } 
    
      public void show()
    {
       
          

      

      idT.setCellValueFactory(new PropertyValueFactory<Livreur,Integer>("id"));
       nomT.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));
       prenomT.setCellValueFactory(new PropertyValueFactory<Livreur,String>("prenom"));
        ObservableList<Livreur> items = FXCollections.observableArrayList(psm.recuperer());
        
        tabview.setItems(items);

    }

    @FXML
    private void save(ActionEvent event) {
        
        if(nom.getText().isEmpty()  || prenom.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
        Livreur l = new Livreur(nom.getText(),prenom.getText());
        psm.ajouter(l);
        
        nom.setText("");
        prenom.setText("");
        
        show();
        
        
        
        }
    }

    @FXML
    private void onremove(ActionEvent event) {
      Livreur l  = tabview.getSelectionModel().getSelectedItem();
     if(l!=null){
      psm.supprimer(l.getId());
        show();
         }
       else
          System.out.println("select item");
    }

    @FXML
    private void onEditNom(TableColumn.CellEditEvent<Livreur, String> event) {
     Livreur livreur   = event.getRowValue();
            livreur.setNom(event.getNewValue());
            psm.modifier(livreur);
    }

    @FXML
    private void onEditPrenom(TableColumn.CellEditEvent<Livreur, String> event) {
        Livreur livreur   = event.getRowValue();
            livreur.setPrenom(event.getNewValue());
            psm.modifier(livreur);
    }
    
}
