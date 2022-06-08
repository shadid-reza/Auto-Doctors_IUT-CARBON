/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Maps1Controller implements Initializable {

    @FXML
    private WebView web1;

    @FXML
    private Label label1;

    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Maps1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = conn.prepareStatement("select workshop_name,workshop_lat,workshop_lon from nearest");
            resultSet = ps.executeQuery();
            resultSet.next();
            String name = resultSet.getString("workshop_name");
            double lat = resultSet.getDouble("workshop_lat");
            double lon = resultSet.getDouble("workshop_lon");
            label1.setText(name);
            
            String s1=String.valueOf(lat);
            String s2=String.valueOf(lon);
            
            engine = web1.getEngine();
            engine.load("https://www.google.com/maps/dir/Your+Location/"+s1+","+s2);
            
            ps=conn.prepareStatement("Delete from nearest where 1=1");
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Maps1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
