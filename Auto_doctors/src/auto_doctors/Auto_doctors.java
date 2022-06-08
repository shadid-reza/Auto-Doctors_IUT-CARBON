/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package auto_doctors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hmsha
 */
public class Auto_doctors extends Application {
    
    @Override
    
    public void start(Stage stage) throws Exception
    {   
        Parent root = FXMLLoader.load(getClass().getResource("/auto_doctors/home_page.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle(("AUTO DOCTOR"));
        //stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        
        stage.show();
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

//hello