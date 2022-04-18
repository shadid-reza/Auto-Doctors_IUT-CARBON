/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Workshop_profileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void MyServicesPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_services_table.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void MyPartsPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    public void AppointmentHistoryBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_appointment_history.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    public void ChangeStatusBtn (ActionEvent event) throws IOException {
         
         
     
    }
}
