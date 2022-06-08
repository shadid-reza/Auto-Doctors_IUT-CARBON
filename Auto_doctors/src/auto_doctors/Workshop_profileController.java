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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Button logout;

    @FXML
    private TableView<PendingTableWorkshop> pen_table;

    @FXML
    private TableColumn<PendingTableWorkshop, Integer> u_appid;

    @FXML
    private TableColumn<PendingTableWorkshop, String> u_date;

    @FXML
    private TableColumn<PendingTableWorkshop, String> u_name;

    @FXML
    private TableColumn<PendingTableWorkshop, String> u_parts;

    @FXML
    private TableColumn<PendingTableWorkshop, String> u_service;

    @FXML
    private TableColumn<PendingTableWorkshop, String> u_status;

    @FXML
    private Label username_show;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<PendingTableWorkshop> obslist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
            int workshop_ID = getCurrent.current_ID();
            PreparedStatement ps = conn.prepareStatement("select ID,User_NID,Service_ID,Parts_ID,Appointment_Time,Status from appointments where Workshop_ID=? AND Status!='Done'");
            ps.setInt(1, workshop_ID);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                obslist.add(new PendingTableWorkshop(resultSet.getInt("ID"),
                        resultSet.getString("User_NID"),
                        getService.ServiceName(resultSet.getInt("Service_ID")), getParts.PartsName(resultSet.getInt("Parts_ID")),
                        (resultSet.getTimestamp("Appointment_Time")).toString(), resultSet.getString("Status")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        u_appid.setCellValueFactory(new PropertyValueFactory<>("app_id"));
        u_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        u_service.setCellValueFactory(new PropertyValueFactory<>("service"));
        u_parts.setCellValueFactory(new PropertyValueFactory<>("parts"));
        u_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        u_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        pen_table.setItems(obslist);

        try {
            PreparedStatement psGetWorkshopName;
            psGetWorkshopName = conn.prepareStatement("Select Name from workshops where ID=?");
            psGetWorkshopName.setInt(1, getCurrent.current_ID());
            ResultSet rsWorkName=psGetWorkshopName.executeQuery();
            rsWorkName.next();
            String workshop_name=rsWorkName.getString("Name");
            username_show.setText(workshop_name);

        } catch (SQLException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void MyServicesPageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_services_table.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void MyPartsPageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void AppointmentHistoryBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_appointment_history.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void changeStatus(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/change_status.fxml"));
        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     public void refBtn(ActionEvent event) throws IOException {

        //pen_table.refresh();
        
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
}
