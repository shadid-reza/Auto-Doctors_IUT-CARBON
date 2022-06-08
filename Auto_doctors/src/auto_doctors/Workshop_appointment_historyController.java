/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Workshop_appointment_historyController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<AppointmentTableWorkshop, Integer> app_id;

    @FXML
    private TableView<AppointmentTableWorkshop> app_table;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> apptime;

    @FXML
    private Button back_button;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> comptime;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> model;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> parts;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> service;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> status;

    @FXML
    private TableColumn<AppointmentTableWorkshop, String> user_name;

    ObservableList<AppointmentTableWorkshop> obslist = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
            int workshop_ID = getCurrent.current_ID();
            PreparedStatement ps = conn.prepareStatement("select ID,User_NID,Service_ID,Parts_ID,Appointment_Time,Completion_Time ,Status , Car_Model from appointments where Workshop_ID=?");
            ps.setInt(1, workshop_ID);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                obslist.add(new AppointmentTableWorkshop(resultSet.getInt("ID"),
                        resultSet.getString("User_NID"),
                        getService.ServiceName(resultSet.getInt("Service_ID")), getParts.PartsName(resultSet.getInt("Parts_ID")),
                        (resultSet.getTimestamp("Appointment_Time")).toString(), (resultSet.getTimestamp("Completion_Time")).toString(),
                        resultSet.getString("Status"), resultSet.getString("Car_Model")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
      //  Integer app_id;
    //String a_name ,a_service, a_parts , app_time ,comp_time, a_status , a_carmodel;

        
        app_id.setCellValueFactory(new PropertyValueFactory<>("app_id"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("a_name"));
        service.setCellValueFactory(new PropertyValueFactory<>("a_service"));
        parts.setCellValueFactory(new PropertyValueFactory<>("a_parts"));
        apptime.setCellValueFactory(new PropertyValueFactory<>("app_time"));
        comptime.setCellValueFactory(new PropertyValueFactory<>("comp_time"));
        status.setCellValueFactory(new PropertyValueFactory<>("a_status"));
        model.setCellValueFactory(new PropertyValueFactory<>("a_carmodel"));

        app_table.setItems(obslist);

        

    }

    public void HandleBackBtnWorkshop(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
