/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button back_button;

    @FXML
    private ComboBox<String> parts_comb;

    @FXML
    private ComboBox<String> service_comb;

    @FXML
    private AnchorPane slider;

    @FXML
    private TextField tf_coordinates;

    @FXML
    private TextField tf_model;

    @FXML
    private ComboBox<String> workshop_comb;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        slider.setTranslateX(-200);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-200);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });
        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-200);
            slide.play();
            slider.setTranslateX(0);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAndPartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from parts_m");
            while (rs.next()) {

                parts_comb.getItems().addAll(rs.getString("name"));
                //parts_comb.setValue(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from services_m");
            while (rs.next()) {

                service_comb.getItems().addAll(rs.getString("name"));
                //service_comb.setValue(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from workshops");
            while (rs.next()) {

                workshop_comb.getItems().addAll(rs.getString("name"));
                workshop_comb.setValue(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAndPartsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void UserProfilePageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void SearchWorkshopsPageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/search_workshops.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void SearchPartsPageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/servicesAndParts.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void HandleBackBtnUsers(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void findCoordinateBtn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/web.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
        public void logOutBtn(ActionEvent event) throws IOException {   
         root = FXMLLoader.load(getClass().getResource("/auto_doctors/home_page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
    }
    

    public void SearchClosestBtn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        if (!tf_coordinates.getText().trim().isEmpty()) {

            double xyz[];

            xyz = new double[2];

            xyz = Lat_Long_Extraction.split_LatLong(tf_coordinates.getText());

            String ans = getMinDistance.minDistance(xyz[0], xyz[1]);

            root = FXMLLoader.load(getClass().getResource("/auto_doctors/maps1.fxml"));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {

            DBUtils.error("Please fill up the coordinates feild .");
        }
    }

    public void instAppBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (!workshop_comb.getValue().trim().isEmpty() && !tf_model.getText().trim().isEmpty() && (!service_comb.getValue().trim().isEmpty() || !parts_comb.getValue().trim().isEmpty())) {

            String selected_service = service_comb.getValue();
            String selected_parts = parts_comb.getValue();
            String selected_model = tf_model.getText();
            String selected_workshop = workshop_comb.getValue();

            //get appointment count
            Connection conn = null;
            ResultSet resultSet = null;
            jdbc connection = new jdbc();
            try {
                conn = connection.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServiceAndPartsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement ps = conn.prepareStatement("select count from counter where type='appointment'");
            resultSet = ps.executeQuery();
            resultSet.next();
            int app_ID = resultSet.getInt("count");

            //increase counter
            PreparedStatement psUpdate = conn.prepareStatement("update counter set count=count+1 where type='appointment'");
            psUpdate.executeUpdate();

            String user_NID = getCurrent_u.getCurrentUser();
            int workshop_ID = getWorkshopID.WorkshopID(selected_workshop);
            String status = "Pending";
            String description = "NULL";
            int service_ID = getServiceID.serviceID(selected_service);
            int parts_ID = getPartsID.partsID(selected_parts);
            String completionTime = "NULL";
            //String appointment_time= Time_Extraction.getTime();

            PreparedStatement psInsert = conn.prepareStatement("Insert into appointments values(?,?,?,?,?,?,?,?,?,?)");
            psInsert.setInt(1, app_ID);
            psInsert.setString(2, user_NID);
            psInsert.setInt(3, workshop_ID);
            psInsert.setString(4, status);
            psInsert.setString(5, description);
            psInsert.setInt(6, service_ID);
            psInsert.setInt(7, parts_ID);
            psInsert.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            psInsert.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            psInsert.setString(10, selected_model);
            psInsert.executeUpdate();

            DBUtils.tempChange("Appointment Created Succcessfully");

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //go back to profile
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } else {

            DBUtils.error("Please fill up all of the information.");
        }

    }

}
