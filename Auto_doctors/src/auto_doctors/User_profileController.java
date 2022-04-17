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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class User_profileController implements Initializable {
    
    @FXML
    private Label sexrum;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sexrum.setText("Horny Shahriar");
    }    
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    
     public void MakeAppointmentBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/appointment_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
