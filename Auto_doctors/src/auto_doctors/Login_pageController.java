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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Login_pageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField user_name;
    
    @FXML
    private ChoiceBox<String> myChoiceBox;
    private final String[] option = {"User", "Workshop"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        myChoiceBox.setValue("User");
        myChoiceBox.getItems().addAll(option);
    }    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
     public void HandleBackBtnLogin1 (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/Home_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
     
     public void HandleLoginBtn (ActionEvent event) throws IOException {
         
        if( "User".equals(myChoiceBox.getValue()))
        {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        }
        else if( "Workshop".equals(myChoiceBox.getValue()))
        {
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        }
        }
    
     
     public void signUpBtnHandler (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/signup_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
     
}
