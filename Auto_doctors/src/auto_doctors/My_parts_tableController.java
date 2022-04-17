/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class My_parts_tableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Pane edit_parts_pane;

    @FXML
    private Pane remove_parts_pane;
    @FXML
    private Pane add_parts_pane;

    
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
     
     public void addparts_btn (ActionEvent event) throws IOException {
                
            add_parts_pane.setVisible(true);
            edit_parts_pane.setVisible(false);
            remove_parts_pane.setVisible(false);
               
    }
       public void editparts_btn (ActionEvent event) throws IOException {
                
            add_parts_pane.setVisible(false);
            edit_parts_pane.setVisible(true);
            remove_parts_pane.setVisible(false);
               
    }
        public void removeparts_btn (ActionEvent event) throws IOException {
                
            add_parts_pane.setVisible(false);
            edit_parts_pane.setVisible(false);
            remove_parts_pane.setVisible(true);
               
    }
}
