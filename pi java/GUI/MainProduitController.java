/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import first_sprint_crud.entities.Produit;


import first_sprint_crud.services.ProduitService;

import first_sprint_crud.util.MyDB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;

import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
/**
 * FXML Controller class
 *
 * @author aziz
 */
public class MainProduitController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TableView<Produit> tabview;
    @FXML
    private TableColumn<Produit, Integer> idT;
    @FXML
    private TableColumn<Produit, String> nomT;
    @FXML
    private TableColumn<Produit, String> descriptionT;
    @FXML
    private TableColumn<Produit, Double> prixT;
    @FXML
    private TableColumn<Produit, String> MediaT;
    @FXML
    private ImageView imgv;
    @FXML
    private TextField prix;
      @FXML
    private Label imgurl;
    
    ProduitService psm = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
           TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 0, c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            } else {
                return null;
            }
        });
        
        tabview.setEditable(true);
        nomT.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionT.setCellFactory(TextFieldTableCell.forTableColumn());
        prixT.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        show();
    }   
     public String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    public void show()
    {
          MediaT.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Produit, String> cell = new TableCell<Produit, String>() {
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        Image image = new Image(item); // Convert string to Image
                        imageview.setImage(image);
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });
        idT.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
        nomT.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        descriptionT.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        prixT.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
        MediaT.setCellValueFactory(new PropertyValueFactory<Produit, String>("image"));
         
         ObservableList<Produit> items = FXCollections.observableArrayList(psm.recuperer());
        tabview.setItems(items);
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
           if (nom.getText().isEmpty() || description.getText().isEmpty() || imgurl.getText().isEmpty() || prix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else {

            Image img3 = new Image("file:/" + imgurl.getText());

// Extract the file extension from the image URL
            String extension = "";
            int i = imgurl.getText().lastIndexOf('.');
            if (i > 0) {
                extension = imgurl.getText().substring(i + 1);
            }

            String nameImage = generateRandomPassword(10) + "." + extension;
            String imagepath = imgurl.getText();
            // System.out.println(imagepath);
            Path source = Paths.get(imagepath); //original file
            Path targetDir = Paths.get(MyDB.url_target);

            Files.createDirectories(targetDir);//in case target directory didn't exist
            Path target = targetDir.resolve(MyDB.url_target + nameImage);// create new path ending with `name` content
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

            Produit p = new Produit(nom.getText(), Double.parseDouble(prix.getText()),description.getText(), nameImage);

            psm.ajouter(p);
            
            nom.setText("");
            prix.setText("");
            description.setText("");
            imgurl.setText("");
            imgv.setImage(null);
            
           
            show();
        }

        
    }

    @FXML
    private void onremove(ActionEvent event) {
         Produit p = tabview.getSelectionModel().getSelectedItem();
       if(p!=null){
         psm.supprimer(p.getId());
      show();
       }
       else
          System.out.println("select item");
    }
    @FXML
    private void upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File f = fileChooser.showOpenDialog(new Stage());
        imgurl.setText(f.getAbsoluteFile().toURI().toString());

        if (imgurl.getText().startsWith("file:/")) {
            imgurl.setText(imgurl.getText().substring("file:/".length()));
        }
        System.out.println(imgurl.getText());

        try {
            FileInputStream fis = new FileInputStream(f);
            Image image = new Image(fis);
            imgv.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditNom(TableColumn.CellEditEvent<Produit, String> event) {
        
             Produit produit = event.getRowValue();
            produit.setNom(event.getNewValue());
            psm.modifier(produit);
    }

    @FXML
    private void onEditDescription(TableColumn.CellEditEvent<Produit, String> event) {
           Produit produit = event.getRowValue();
            produit.setDescription(event.getNewValue());
            psm.modifier(produit);
    }

    @FXML
    private void onEditPrix(TableColumn.CellEditEvent<Produit, Double> event) {
           Produit produit = event.getRowValue();
            produit.setPrix(event.getNewValue());
            psm.modifier(produit);
    }

    @FXML
    private void onEditMedia(TableColumn.CellEditEvent<Produit, String> event) {
           Produit produit = tabview.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File f = fileChooser.showOpenDialog(new Stage());
         produit.setImage(f.getAbsoluteFile().toURI().toString());
        psm.modifier(produit);
    }
}
