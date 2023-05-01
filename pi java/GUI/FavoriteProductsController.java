/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import first_sprint_crud.entities.FavoriteProducts;
import first_sprint_crud.entities.Produit;
import first_sprint_crud.entities.panier;
import first_sprint_crud.services.FavoriteProductsService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxpi.JavafxPi;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class FavoriteProductsController implements Initializable {

    @FXML
    private VBox ChosenProdCard;
    @FXML
    private Label namalabel;
    @FXML
    private Label prixlabel;
    @FXML
    private ImageView prodimg;
    @FXML
    private Button Add;
    @FXML
    private ImageView home;
    @FXML
    private ImageView favorite;
    @FXML
    private ImageView cart;
    @FXML
    private Label countlbl;
    @FXML
    private ImageView delivery;
    @FXML
    private ImageView time;
    private ComboBox<String> cbprix;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    
     String prix1="";
    String nom1="";
    int p=0;
    
       private Listner listner;
       Produit prod2;
       
        private Stage stage;
    private Scene scene;
    private Parent root;

     
    FavoriteProductsService psprod = new FavoriteProductsService();
    @FXML
    private ImageView shop;
    
    
     private void setchosenproduit(Produit prod)
    {
        namalabel.setText(prod.getNom());
        prixlabel.setText(prod.getPrix() +"DT");
        Image image;
        image = new Image(prod.getImage());
        prodimg.setImage(image);

     }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image1;
         
         
     
      String size = String.valueOf(JavafxPi.pan.size());
      countlbl.setText(size);
      
         
      
            
           
            
            Image image3 = new Image("/Images/ic_delivery.png");
           delivery.setImage(image3);
           
           Image image4 = new Image("/Images/ic_stopwatch.png");
            time.setImage(image4);
            
            ObservableList<String> listc22 =  FXCollections.observableArrayList();
            
              if(psprod.recupererFavorite().size()>0)
         {
            setchosenproduit(psprod.recupererFavorite().get(0));
            prod2=psprod.recupererFavorite().get(0);
            listner = new Listner() {
                @Override
                public void onClickListener(Produit prod) {
                   setchosenproduit(prod);
                prod2=prod;
                }
            };
         }
              ObservableList<Produit> items = FXCollections.observableArrayList(psprod.recupererFavorite());
              showshop(items);
              
              ObservableList<String> listpr =  FXCollections.observableArrayList();
               listpr.add("Croissant");
               listpr.add("Decroissant");
               
              
    }

public void showshop(ObservableList<Produit> list)
    {
        System.out.println("listt" + list);
         int column=0;
        int row=1;
        int x=0;
        
        
          FXMLLoader fxmlloader2 = new  FXMLLoader();
                fxmlloader2.setLocation(getClass().getResource("Items_Favorite.fxml"));
        try {
            AnchorPane pane2 = fxmlloader2.load();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                GUI.Items_FavoriteController items2 = fxmlloader2.getController();
                items2.refresh();
                grid.getChildren().removeAll(grid.getChildren());
       
         try {
        for(Produit prod : list)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Items_Favorite.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                GUI.Items_FavoriteController items = fxmlloader.getController();
                items.setData(prod,listner);
                
                if(column == 3 )
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

    private void searchtxtaction2(KeyEvent event) {
         nom1= event.getText();
      //ObservableList<Produit> items = FXCollections.observableArrayList(psprod.recupererByNameAndPrixOrderByPrix(nom1, -1, -1,p));
       //showshop(items);
    }


    @FXML
    private void AddToCart(ActionEvent event) {
        int x =0;
        int qte2=1;
        for(panier pan2: JavafxPi.pan)
        {
            if(pan2.getProd().getId()==prod2.getId())
            {
            x=1;
            break;
            }
        }
        
        if(x==0){
        panier p = new panier(qte2,prod2.getPrix()*qte2,prod2);
        JavafxPi.pan.add(p);
        String size = String.valueOf(JavafxPi.pan.size());
        countlbl.setText(size);
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
    private void Cartaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("ShopinCart.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }



    private void refreshaction(ActionEvent event) {
           ObservableList<Produit> items = FXCollections.observableArrayList(psprod.recupererFavorite());
           showshop(items);
    }

    @FXML
    private void favoriteaction(MouseEvent event) {
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
