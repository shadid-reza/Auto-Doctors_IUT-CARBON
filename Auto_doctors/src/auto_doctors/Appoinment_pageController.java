/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Appoinment_pageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TextField long_lat;
    
     @FXML
    private AnchorPane slider;
     
     @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        slider.setTranslateX(-200);
        Menu.setOnMouseClicked(event-> {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                slide.setToX(0);
                slide.play();
                slider.setTranslateX(-200);
                slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            }); 
        });
        MenuBack.setOnMouseClicked(event-> {
                TranslateTransition slide = new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(slider);
                slide.setToX(-200);
                slide.play();
                slider.setTranslateX(0);
                slide.setOnFinished((ActionEvent e)->{
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            }); 
        });
       
    }    
    
    
     
    
    
     public void UserProfilePageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
      public void SearchWorkshopsPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/search_workshops.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
      
      public void SearchPartsPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/search_parts.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
      
       public void HandleBackBtnUsers (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
       
       public void SearchClosestBtn (ActionEvent event) throws IOException {
         
         
       String long_l =  long_lat.getText();
       double[] lati_long =  Lat_Long_Extraction.split_LatLong(long_l);
       
       double latitude = lati_long[0];
       double longitude = lati_long[1];
       
           
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/maps.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
