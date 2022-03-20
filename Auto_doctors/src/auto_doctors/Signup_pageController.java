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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Signup_pageController {

    @FXML
    private Button btn_signUp;
    @FXML
    private TextField tf_username,tf_password,tf_contact,tf_cpassword;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
     public void HandleBackBtnSignUp1 (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/Home_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
      public void handleSignUp (ActionEvent event) throws IOException {
      
          if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()
                  && !tf_cpassword.getText().trim().isEmpty() && !tf_contact.getText().trim().isEmpty())
          {
              
          }
          else{
              System.out.println("Please Fill In All the Infornmation");
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("Please Fill In All the Infornmation");
              alert.show();
          }
    }
     
}
