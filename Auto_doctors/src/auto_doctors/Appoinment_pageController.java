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
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private AnchorPane slider;
     
     @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;
    
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
    
}
