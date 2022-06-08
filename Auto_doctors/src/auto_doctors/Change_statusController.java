/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Change_statusController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField app_ID;

    @FXML
    private ComboBox<String> status_box;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        status_box.getItems().addAll("Ongoing");
        status_box.getItems().addAll("Done");
        status_box.getItems().addAll("Pending");
        status_box.setValue("Pending");
    }

    public void handleChangeBtn(ActionEvent event) throws IOException, SQLException {

        if (!app_ID.getText().trim().isEmpty() && !status_box.getValue().trim().isEmpty()) {
            int ID = Integer.parseInt(app_ID.getText());
            String status = status_box.getValue();
            Connection conn = null;
            ResultSet resultSet = null;
            jdbc connection = new jdbc();
            try {
                conn = connection.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //check if the appointment ID is under current workshop
            PreparedStatement ps = conn.prepareStatement("Select Workshop_ID from appointments where ID=?");
            ps.setInt(1, ID);
            resultSet = ps.executeQuery();
            if (resultSet.isBeforeFirst()) {
                
                ps=conn.prepareStatement("Update appointments set status=? where ID=?");
                ps.setString(1, status);
                ps.setInt(2, ID);
                ps.executeUpdate();
                DBUtils.tempChange("Appointment Status updated Successfully!");
                
                
            } else {
                DBUtils.error("This apoointment is not under your workshop!");
            }
            conn.close();
        } else {
            DBUtils.error("Please fill in all the information");
        }
    }

}
