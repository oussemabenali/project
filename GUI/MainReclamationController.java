/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import first_sprint_crud.entities.Coach;
import first_sprint_crud.entities.Reclamation;
import first_sprint_crud.entities.Reponse;


import first_sprint_crud.services.ReclamationService;
import first_sprint_crud.services.ReponseService;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import first_sprint_crud.entities.RendezVous;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author arij
 */
public class MainReclamationController implements Initializable {

    @FXML
    private TextField searchtxt;
    @FXML
    private VBox ChosenProdCard;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea desc;
    @FXML
    private Button Add;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;
    
    ReclamationService psrec = new ReclamationService(); 
    ReponseService psrep = new ReponseService();
  
    private Stage stage;
    private Scene scene;
    
    private Timeline searchTimeline;
    @FXML
    private ImageView pdf;
    @FXML
    private ComboBox<String> date;
    int t=0;
    String searchText="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (psrec.recuperer().isEmpty())
        {
            prodvide.setText("Reclamation vide");
             
        }
            else
         prodvide.setText("Reclamation:");
        
        ObservableList<String> listpr =  FXCollections.observableArrayList();
               listpr.add("Croissant");
               listpr.add("Decroissant");
               
               date.setItems(listpr);
        
         show(searchText);
    } 
    
    
      public void show(String search)
    {
         // Retrieve a list of coaches from the service
        
        List<Reclamation> reclamation ;
        if(search.length() == 0)
            if(t==0)
        reclamation= psrec.recuperer();
            else
            reclamation = psrec.recupererBySujet(search,t); 
        else
        {
            reclamation = psrec.recupererBySujet(search,t);
        }
        
        
        
        int column=0;
        int row=1;
        int x=0;
        
        
          FXMLLoader fxmlloader2 = new  FXMLLoader();
                fxmlloader2.setLocation(getClass().getResource("MainReclamationItem.fxml"));
        try {
            AnchorPane pane2 = fxmlloader2.load();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                GUI.MainReclamationItemController items2 = fxmlloader2.getController();
                items2.refresh();
                grid.getChildren().removeAll(grid.getChildren());
       
         try {
        for(Reclamation pan : reclamation)
        {
            
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("MainReclamationItem.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
               MainReclamationItemController items = fxmlloader.getController();
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
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void searchtxtaction(ActionEvent event) {
    }
    
      @FXML
private void searchtxtaction2(KeyEvent event) {
    if (searchTimeline != null) {
        searchTimeline.stop();
    }

    searchTimeline = new Timeline(new KeyFrame(Duration.millis(300), actionEvent -> {
         searchText = searchtxt.getText();
        show(searchText);
    }));

    searchTimeline.play();
}
    

    @FXML
    private void AddToCart(ActionEvent event) {
             if (sujet.getText().isEmpty() || desc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else{
            
           
            
        
            Reclamation r = new Reclamation(sujet.getText(),desc.getText());
            psrec.ajouter(r);
            sujet.setText("");
            desc.setText("");
            
      
      TrayNotification tray  = new TrayNotification();
      AnimationType type = AnimationType.POPUP;
      tray.setAnimationType(type);
      tray.setTitle("Reclamation Ajouter");
      tray.setMessage("Reclamation  ajouté avec succès ");
      tray.setNotificationType(NotificationType.SUCCESS);
      tray.showAndDismiss(Duration.millis(10000));
            
            
            show(searchText);
            
        }
    }



      @FXML
    private void pdfaction(MouseEvent event) throws DocumentException {
          try {
              if (psrec.recuperer()!=null){
            String message = "              --------**reclamation**-------- \n\n\n";
            
            String file_name = "src/Reclamation.pdf";
            
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Paragraph para = new Paragraph(message);
            document.add(para);
          
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cl1 = new PdfPCell(new Phrase("Id"));
            table.addCell(cl1);
            PdfPCell cl2 = new PdfPCell(new Phrase("Sujet"));
            table.addCell(cl2);
            PdfPCell cl3 = new PdfPCell(new Phrase("Description"));
            table.addCell(cl3);
            PdfPCell cl4 = new PdfPCell(new Phrase("Date Reclamation"));
            table.addCell(cl4);
       

            
            
            
            
            table.setHeaderRows(1);
            document.add(table);
            
                int i =1;
                for (Reclamation r :psrec.recuperer() ){
                table.addCell("" + i);
                table.addCell("" + r.getSujet());
                table.addCell("" + r.getDescription());
                table.addCell("" + r.getDate_rec());
                i++;
                }

            
            document.add(table);
            
            document.close();
            
            
            
             Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Pdf ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.showInformation();
              }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainReclamationItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectdate(ActionEvent event) {
       String   dateT = date.getSelectionModel().getSelectedItem().toString();
       
    if (date == null) {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText(" nothing is selected, so do nothing");

            alert.showAndWait(); 
    }
    
  if(dateT =="Croissant"){
      t=1;
  }
  
    if(dateT =="Decroissant"){
      t=2;
    }
  show(searchText);
   
    }

    
}
