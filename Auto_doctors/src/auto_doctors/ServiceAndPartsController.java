/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class ServiceAndPartsController implements Initializable {

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
    private ComboBox<String> parts_combox;

    @FXML
    private Button search_btn;
    @FXML
    private Button search_btn1;

    @FXML
    private ComboBox<String> service_combox;

    @FXML
    private BorderPane slider;
    
    @FXML
    private TableView<ServiceAndParts_user> main_table;
    
    @FXML
    private TableColumn<ServiceAndParts_user, String> w_contact;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_email;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_location;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_name;

    ObservableList<ServiceAndParts_user> obslist = FXCollections.observableArrayList();

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
            conn=connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAndPartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
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

        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from parts_m");
            while (rs.next()) {

                parts_combox.getItems().addAll(rs.getString("name"));
                parts_combox.setValue(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from services_m");
            while (rs.next()) {

                service_combox.getItems().addAll(rs.getString("name"));
                service_combox.setValue(rs.getString("name"));
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

    public void MakeAppointmentPageBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/appointment_page.fxml"));

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
    
       public void logOutBtn(ActionEvent event) throws IOException {   
         root = FXMLLoader.load(getClass().getResource("/auto_doctors/home_page.fxml"));
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

    public void PartsNameBtn(ActionEvent event) throws IOException {
        parts_combox.setVisible(true);
        search_btn.setVisible(false);
        service_combox.setVisible(false);
        search_btn1.setVisible(true);

    }

    public void ServicesNameBtn(ActionEvent event) throws IOException {

        parts_combox.setVisible(false);
        search_btn.setVisible(true);
        service_combox.setVisible(true);
        search_btn1.setVisible(false);
    }

    //for service
    public void SearchBtn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        
        parts_combox.setVisible(false);
        search_btn.setVisible(false);
        service_combox.setVisible(false);
        search_btn1.setVisible(false);

        main_table.getItems().clear();
        
        
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        String selected = service_combox.getValue();
        PreparedStatement ps = conn.prepareStatement("select Workshop_ID from services where Name=?");
        ps.setString(1, selected);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int ID = resultSet.getInt("Workshop_ID");
            PreparedStatement psGetWorkshop = conn.prepareStatement("select Name,Mobile,Email,Location from workshops where ID=?");
            psGetWorkshop.setInt(1, ID);
            ResultSet entry = psGetWorkshop.executeQuery();
            entry.next();
            obslist.add(new ServiceAndParts_user(entry.getString("Name"),
                    entry.getString("Mobile"), entry.getString("Email"), entry.getString("Location")));
            
            w_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));
            w_contact.setCellValueFactory(new PropertyValueFactory<>("c_contact"));
            w_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));
            w_location.setCellValueFactory(new PropertyValueFactory<>("c_location"));
            
            main_table.setItems(obslist);
        }
        service_combox.getSelectionModel().clearSelection();
    }

    //for parts
    public void SearchBtn1(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        parts_combox.setVisible(false);
        search_btn.setVisible(false);
        service_combox.setVisible(false);
        search_btn1.setVisible(false);
        
        main_table.getItems().clear();
        
        
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        String selected = parts_combox.getValue();
        PreparedStatement ps = conn.prepareStatement("select Workshop_ID from parts where name=?");
        ps.setString(1, selected);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int ID = resultSet.getInt("Workshop_ID");
            PreparedStatement psGetWorkshop = conn.prepareStatement("select Name,Mobile,Email,Location from workshops where ID=?");
            psGetWorkshop.setInt(1, ID);
            ResultSet entry = psGetWorkshop.executeQuery();
            entry.next();
            obslist.add(new ServiceAndParts_user(entry.getString("Name"),
                    entry.getString("Mobile"), entry.getString("Email"), entry.getString("Location")));
            
            w_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));
            w_contact.setCellValueFactory(new PropertyValueFactory<>("c_contact"));
            w_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));
            w_location.setCellValueFactory(new PropertyValueFactory<>("c_location"));
            
            main_table.setItems(obslist);
        }
        parts_combox.getSelectionModel().clearSelection();
    }

}
