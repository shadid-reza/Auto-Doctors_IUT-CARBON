/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package auto_doctors;

import java.awt.font.GlyphVector;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class Login_pageController implements Initializable {

    @FXML
    private TextField log_username, log_password;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ChoiceBox<String> myChoiceBox;
    private final String[] option = {"User", "Workshop"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myChoiceBox.setValue("User");
        myChoiceBox.getItems().addAll(option);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void HandleBackBtnLogin1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/auto_doctors/home_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void handleLogIn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        //System.out.println("Hello World");
        if ("User".equals(myChoiceBox.getValue())) {
            if (!log_username.getText().trim().isEmpty() && !log_password.getText().trim().isEmpty()) {
                int temp = DBUtils.logInUser(event, log_username.getText(), log_password.getText());
                if (temp == 1) {
                    root = FXMLLoader.load(getClass().getResource("/auto_doctors/user_profile.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                System.out.println("Please Fill In All the Infornmation");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill In All the Infornmation");
                alert.show();
            }
        } else if ("Workshop".equals(myChoiceBox.getValue())) {
            if (!log_username.getText().trim().isEmpty() && !log_password.getText().trim().isEmpty()) {
                int flag = DBUtils.logInWorkshop(event, log_username.getText(), log_password.getText());
                if (flag == 1) {
                    root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            } else {
                System.out.println("Please Fill In All the Infornmation");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill In All the Infornmation");
                alert.show();
            }
        }
    }

    public void signUpBtnHandler(ActionEvent event) throws IOException {
        if ("User".equals(myChoiceBox.getValue())) {
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/signup_page.fxml"));
        } else if ("Workshop".equals(myChoiceBox.getValue())) {
            root = FXMLLoader.load(getClass().getResource("/auto_doctors/appointment_page.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}
