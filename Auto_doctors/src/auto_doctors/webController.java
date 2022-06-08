/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shihab Ahmad
 */
public class webController implements Initializable {
    @FXML
    private WebView web1;

    private WebEngine engine;
    @FXML
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
        engine=web1.getEngine();
        engine.load("https://www.google.com/maps/dir/Your+Location");
    }

    public void handleDone(ActionEvent event) throws IOException{
      //  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      //  stage.close();
    }
    
}

