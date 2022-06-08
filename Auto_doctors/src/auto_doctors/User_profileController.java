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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class User_profileController implements Initializable {
    
    @FXML
    private Button logout;

    @FXML
    private TableView<User_profileModel> recent_table;

    @FXML
    private Label tf_address;

    @FXML
    private Label tf_contact;

    @FXML
    private Label tf_email;

    @FXML
    private Label tf_name;

    @FXML
    private Label username_show;

    @FXML
    private TableColumn<User_profileModel, String> w_date;

    @FXML
    private TableColumn<User_profileModel, String> w_name;

    @FXML
    private TableColumn<User_profileModel, String> w_service;

    ObservableList<User_profileModel> obslist = FXCollections.observableArrayList();
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //username_show.setText("Horny Shahriar");
        
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
            String user_ID = getCurrent_u.getCurrentUser();
            PreparedStatement ps = conn.prepareStatement("select Workshop_ID,Service_ID,Appointment_Time from appointments where User_NID=?");
            ps.setString(1, user_ID);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                obslist.add(new User_profileModel(getWorkshopName.getName(resultSet.getInt("Workshop_ID")),
                        getService.ServiceName(resultSet.getInt("Service_ID")),
                        (resultSet.getTimestamp("Appointment_Time")).toString()));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Workshop_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
      //  String a_name , a_service , a_date; 
    //String a_name ,a_service, a_parts , app_time ,comp_time, a_status , a_carmodel;

        
        w_name.setCellValueFactory(new PropertyValueFactory<>("a_name"));
        w_service.setCellValueFactory(new PropertyValueFactory<>("a_service"));
        w_date.setCellValueFactory(new PropertyValueFactory<>("a_date"));

        recent_table.setItems(obslist);

        //labels setting
        try {
            conn=connection.getConnection();
            PreparedStatement ps=conn.prepareStatement("select NID,Mobile,Email,Home from users where NID=?");
            ps.setString(1, getCurrent_u.getCurrentUser());
            ResultSet rs=ps.executeQuery();
            rs.next();
            username_show.setText(rs.getString("NID"));
            tf_name.setText(rs.getString("NID"));
            tf_contact.setText(rs.getString("Mobile"));
            tf_address.setText(rs.getString("Home"));
            tf_email.setText(rs.getString("Email"));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User_profileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }    
    
    
     public void MakeAppointmentBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/appointment_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
     
     public void SearchWorkshopsPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/search_workshops.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
      
      public void SearchPartsPageBtn (ActionEvent event) throws IOException {
         
         
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/servicesAndParts.fxml"));

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
