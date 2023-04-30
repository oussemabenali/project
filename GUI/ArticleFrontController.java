/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Article;
import first_sprint_crud.entities.Livreur;

import first_sprint_crud.services.ArticleService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ouss
 */
public class ArticleFrontController implements Initializable {

    @FXML
    private ListView<Article> listA;
    ArticleService psm = new ArticleService();
    public static Article selectedarticle_;//= new Article();
    @FXML
    private Button retour;
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show("");
    }

    @FXML
    private void ret(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void show(String s)
            
    {
        
        listA.setCellFactory(param -> {
            final HBox hbox = new HBox();
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(250);
            imageview.setFitWidth(400);
            final Button viewBtn = new Button("View");

            ListCell<Article> cell = new ListCell<Article>() {
                public void updateItem(Article item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        Image image = new Image(item.getImage());
                        imageview.setImage(image);
                        hbox.getChildren().addAll(imageview, viewBtn);
                        setGraphic(hbox);
                    } else {
                        setGraphic(null);
                    }
                }
            };

            viewBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Article selectedArticle = cell.getItem();
                        selectedarticle_ = selectedArticle;
                        //FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleDetails.fxml"));
                        Parent root = FXMLLoader.load(getClass().getResource("ArticleDetails.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        /*
            Parent root = loader.load();
            ArticleDetailsController controller = loader.getController();
            //controller.initData(selectedArticle);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();*/
                        // int articleId = selectedArticle.getId();
                        // do something with the article ID
                    } catch (IOException ex) {
                        Logger.getLogger(ArticleFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            return cell;
        });

        ObservableList<Article> items = null ;
        if(s.length() == 0)
        {
             items=  FXCollections.observableArrayList(psm.recuperer());
           
            
        }
        else
        {
             items=  FXCollections.observableArrayList(psm.recupererBySujetTitre(s));
        }
        
        listA.setItems(items);
    }


}
