package GUI;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import org.controlsfx.control.Notifications;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import first_sprint_crud.entities.Commentaire;
import first_sprint_crud.services.CommentaireService;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ArticleDetailsController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label title;
    @FXML
    private Label descr;
    @FXML
    private ListView<Commentaire> listv;

    CommentaireService psc = new CommentaireService();
    @FXML
    private TextField commetinput;
    @FXML
    private Button submit;
    @FXML
    private Button retour;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView qrid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        show();
        
    }
    private void show()
    {
         while(!listv.getItems().isEmpty())
        listv.getItems().remove(0);
         title.setText(ArticleFrontController.selectedarticle_.getTitre());
        descr.setText(ArticleFrontController.selectedarticle_.getSujet_art());
         Image image = new Image(ArticleFrontController.selectedarticle_.getImage()); // Convert string to Image
                        img.setImage(image);
        List<Commentaire> comments = psc.recupererby_article(ArticleFrontController.selectedarticle_.getId());

        // Create a list cell factory for the comments list view
        listv.setCellFactory(param -> {
            ListCell<Commentaire> cell = new ListCell<Commentaire>() {
                @Override
                protected void updateItem(Commentaire comment, boolean empty) {
                    super.updateItem(comment, empty);
                    if (comment != null) {
                        // Create a label to display the comment content
                        Label contentLabel = new Label(comment.getContenu());

                        // Create a button to delete the comment
                        Button deleteButton = new Button("Delete");

                        // Set the action listener for the delete button
                        deleteButton.setOnAction(event -> {
                            psc.supprimer(comment.getId());
                            listv.getItems().remove(comment);
                        });

                        // Add the content label and delete button to a new HBox
                        HBox hbox = new HBox(contentLabel, deleteButton);

                        // Set the cell's graphic to the HBox
                        setGraphic(hbox);
                    } else {
                        setGraphic(null);
                    }
                }
            };
            return cell;
        });

        // Add each comment to the list view
         for (Commentaire comment : comments) {
            try {
      File myObj = new File("C:\\coachingjava\\src\\GUI\\bad_words.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
      
         if( comment.getContenu().indexOf(data)!= -1)
            {
               comment.setContenu( comment.getContenu().replace(data, "****"));
            }
      }
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
            listv.getItems().add(comment);
        }
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Article Title "+ArticleFrontController.selectedarticle_.getTitre()+"\n"+" Content : "+ArticleFrontController.selectedarticle_.getSujet_art();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
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
            
            
            qrid.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
    }

    @FXML
    private void save(ActionEvent event) {
         if (commetinput.getText().isEmpty() ) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Creation Error");
            alert.setContentText("Comment must  not be empty !");
         }
         else
         {
              Commentaire c = new Commentaire(commetinput.getText(),ArticleFrontController.selectedarticle_);

            psc.ajouter(c);
            //listv.getItems().add(c);
            show();
            commetinput.setText("");
             Notifications.create()
                            .title("Saved")
                            .text("your comment has been added!")
                            .show();
         }
    }

    @FXML
    private void ret(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArticleFront.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
