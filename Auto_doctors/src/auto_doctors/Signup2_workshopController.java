/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.sql.*;
import com.mysql.cj.xdevapi.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Signup2_workshopController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private ComboBox<String> parts_box;

    @FXML
    private ComboBox<String> service_box;

    @FXML
    private TextField tf_long_lat;

    @FXML
    private TextField tf_parts_price;

    @FXML
    private TextField tf_service_price;

//    @FXML
//    private Pane add_parts_pane;
//    @FXML
//    private Pane add_service_pane;
//    @FXML
//    private Pane back_button_signup11;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (java.sql.Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select name from services_m");
            while (rs.next()) {
                service_box.getItems().addAll(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from parts_m");
            while (rs.next()) {
                parts_box.getItems().addAll(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void findCoordinateBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/web.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addServicesBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String name = service_box.getValue();

        PreparedStatement ps = conn.prepareStatement("select id from services_m where name=?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int service_id = rs.getInt(1);
        int price = Integer.parseInt(tf_service_price.getText());
        int workshop_id = getCurrent.current_ID();

        //check if the part already exists for the current workshop
        PreparedStatement psCheck = conn.prepareStatement("Select * from services where workshop_ID=? AND service_ID=?");
        psCheck.setInt(1, workshop_id);
        psCheck.setInt(2, service_id);
        ResultSet rsCheck = psCheck.executeQuery();
        if (rsCheck.isBeforeFirst()) {
            System.out.println("Service Already Exists for current Workshop");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Service Already Exists for current Workshop");
            alert.show();
        } else {
            ps = conn.prepareStatement("Insert into services values(?,?,?,?,?)");
            ps.setInt(1, service_id);
            ps.setInt(2, workshop_id);
            ps.setString(3, name);
            ps.setInt(4, price);
            ps.setString(5, "Available");
            ps.executeUpdate();
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtils.tempChange("Service has been added successfully");
        }
        service_box.getSelectionModel().clearSelection();
        tf_service_price.clear();
    }

    public void addPartsBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!parts_box.getValue().trim().isEmpty() && !tf_parts_price.getText().trim().isEmpty()) {
            String name = parts_box.getValue();

            int price = Integer.parseInt(tf_parts_price.getText());

            PreparedStatement ps = conn.prepareStatement("select id from parts_m where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int parts_id = rs.getInt(1);
            int workshop_id = getCurrent.current_ID();
            //check if the part already exists for the current workshop
            PreparedStatement psCheck = conn.prepareStatement("Select * from parts where workshop_ID=? AND parts_ID=?");
            psCheck.setInt(1, workshop_id);
            psCheck.setInt(2, parts_id);
            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.isBeforeFirst()) {
                System.out.println("Part Already Exists for current Workshop");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Part Already Exists for current Workshop");
                alert.show();
            } else {            //Check done
                ps = conn.prepareStatement("Insert into parts values(?,?,?,?,?)");
                ps.setInt(1, parts_id);
                ps.setString(2, name);
                ps.setInt(3, workshop_id);
                ps.setInt(4, price);
                ps.setString(5, "Available");
                ps.executeUpdate();
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                DBUtils.tempChange("Part has been added successfully");
            }
        } else {
            DBUtils.tempChange("Please fill in the input fields");
        }
        parts_box.getSelectionModel().clearSelection();
        parts_box.setValue("Select Parts");
        tf_parts_price.clear();
    }

    public void confirmSignUp(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!tf_long_lat.getText().trim().isEmpty()) {
            double[] temp = Lat_Long_Extraction.split_LatLong(tf_long_lat.getText());
            PreparedStatement psInsert = conn.prepareStatement("Update workshops set latitude=? , longitude=? where ID=?");
            psInsert.setDouble(1, temp[0]);
            psInsert.setDouble(2, temp[1]);
            psInsert.setInt(3, getCurrent.current_ID());
            psInsert.executeUpdate();
            DBUtils.tempChange("Workshop sign up done successfully");
            //Load Home Page if successful
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/home_page.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("Please Fill In Latitude and Longitude Infornmation");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill In Latitude and Longitude Infornmation");
            alert.show();
        }

    }

}
