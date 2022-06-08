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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Home_pageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox<String> myChoiceBox;
    private final String[] option = {"User", "Workshop"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //removing the current from current table
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement psRemove = conn.prepareStatement("Delete from current where 1=1");
            psRemove.executeUpdate();
            psRemove=conn.prepareStatement("Delete from current_u where 1=1");
            psRemove.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Home_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        myChoiceBox.setValue("User");
        myChoiceBox.getItems().addAll(option);

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void loginBtnHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/login_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void signUpBtnHandler(ActionEvent event) throws IOException {

        if ("User".equals(myChoiceBox.getValue())) {
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/signup_page.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else if ("Workshop".equals(myChoiceBox.getValue())) {
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/signUp_workshop.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }
}
