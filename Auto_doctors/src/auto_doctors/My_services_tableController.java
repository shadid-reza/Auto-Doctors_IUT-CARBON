/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class My_services_tableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    @FXML   
    private Button add_services;
    @FXML   
    private Button edit_services;
    @FXML   
    private Button remove_services;

    @FXML
    private Pane add_service_pane;
    
     @FXML
    private Pane edit_service_pane;

    @FXML
    private Pane remove_service_pane;

    
    private Stage stage;
    private Scene scene;
    private Parent root;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    
    
     public void HandleBackBtnWorkshop (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
      public void add_btn (ActionEvent event) throws IOException {
                
            add_service_pane.setVisible(true);
            edit_service_pane.setVisible(false);
            remove_service_pane.setVisible(false);
               
    }
       public void edit_btn (ActionEvent event) throws IOException {
                
            add_service_pane.setVisible(false);
            edit_service_pane.setVisible(true);
            remove_service_pane.setVisible(false);
               
    }
        public void remove_btn (ActionEvent event) throws IOException {
                
            add_service_pane.setVisible(false);
            edit_service_pane.setVisible(false);
            remove_service_pane.setVisible(true);
               
    }
    
}