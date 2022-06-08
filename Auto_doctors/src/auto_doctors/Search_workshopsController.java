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
import java.util.Set;
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
public class Search_workshopsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button enter_btn_location;

    @FXML
    private Button enter_btn_name;

    @FXML
    private TextField tf_location_search;

    @FXML
    private TextField tf_name_search;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private TableView<ServiceAndParts_user> s_workshop_table;

    @FXML
    private BorderPane slider;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_email;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_location;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_name;

    @FXML
    private TableColumn<ServiceAndParts_user, String> w_number;

    ObservableList<ServiceAndParts_user> obslist = FXCollections.observableArrayList();

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

        w_location.setCellValueFactory(new PropertyValueFactory<>("location"));

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

    public void SearchPartsPageBtn(ActionEvent event) throws IOException {

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

    public void HandleBackBtnUsers(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void NameBtn(ActionEvent event) throws IOException {

        enter_btn_name.setVisible(true);
        tf_name_search.setVisible(true);

        enter_btn_location.setVisible(false);
        tf_location_search.setVisible(false);

    }

    public void LocationBtn(ActionEvent event) throws IOException {

        enter_btn_location.setVisible(true);
        tf_location_search.setVisible(true);

        enter_btn_name.setVisible(false);
        tf_name_search.setVisible(false);
    }

    public void EnterBtnName(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        s_workshop_table.getItems().clear();
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();

        String name = tf_name_search.getText();
        PreparedStatement ps = conn.prepareStatement("select Name,Mobile,Email,Location from workshops where Name Like ?");
        ps.setString(1, name+"%");
        resultSet = ps.executeQuery();
        if (resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                obslist.add(new ServiceAndParts_user(resultSet.getString("Name"), resultSet.getString("Mobile"),
                        resultSet.getString("Email"), resultSet.getString("Location")));
            }

            w_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));
            w_number.setCellValueFactory(new PropertyValueFactory<>("c_contact"));
            w_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));
            w_location.setCellValueFactory(new PropertyValueFactory<>("c_location"));

            s_workshop_table.setItems(obslist);
        } else {
            DBUtils.error("No such workshop exists!");
        }

        enter_btn_location.setVisible(false);
        tf_location_search.setVisible(false);

        enter_btn_name.setVisible(false);
        tf_name_search.setVisible(false);
        tf_name_search.clear();
    }

    public void EnterBtnLocation(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        s_workshop_table.getItems().clear();
        
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();

        String name = tf_location_search.getText();
        PreparedStatement ps = conn.prepareStatement("select Name,Mobile,Email,Location from workshops where Location Like ?");
        ps.setString(1, name+"%");
        resultSet = ps.executeQuery();
        if (resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                obslist.add(new ServiceAndParts_user(resultSet.getString("Name"), resultSet.getString("Mobile"),
                        resultSet.getString("Email"), resultSet.getString("Location")));
            }

            w_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));
            w_number.setCellValueFactory(new PropertyValueFactory<>("c_contact"));
            w_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));
            w_location.setCellValueFactory(new PropertyValueFactory<>("c_location"));

            s_workshop_table.setItems(obslist);
            
        } else {
            DBUtils.error("No such workshop exists!");
        }

        enter_btn_location.setVisible(false);
        tf_location_search.setVisible(false);

        enter_btn_name.setVisible(false);
        tf_name_search.setVisible(false);
        tf_location_search.clear();
    }

}
