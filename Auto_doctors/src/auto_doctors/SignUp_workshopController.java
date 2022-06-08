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
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class SignUp_workshopController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public int current_ID;
    @FXML
    private TextField tf_contact;

    @FXML
    private TextField tf_cpassword;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_location;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void HandleBackBtnSignUp2(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/Home_page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void WorkShopSignupCont(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        if (!tf_name.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()
                && !tf_cpassword.getText().trim().isEmpty() && !tf_contact.getText().trim().isEmpty()
                && !tf_email.getText().trim().isEmpty() && !tf_location.getText().trim().isEmpty()) {
            if (tf_password.getText().equals(tf_cpassword.getText())) {
                //System.out.println("yessssssssss");
                int ID = DBUtils.signUpWorkshop(event, tf_name.getText(), DigestUtils.md5Hex(tf_password.getText()),
                        tf_contact.getText(), tf_email.getText(), tf_location.getText());

                if (ID != 0) {
                    String s = "Entry Successful. Please fill up the next page!";
                    DBUtils.tempChange(s);

                    Connection conn = null;
                    ResultSet resultSet = null;
                    PreparedStatement ps = null;
                    jdbc connection = new jdbc();
                    try {
                        conn = connection.getConnection();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //updating current workshop
                    ps = conn.prepareStatement("Insert into current values(?)");
                    ps.setInt(1, ID);
                    ps.execute();

                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    root = FXMLLoader.load(getClass().getResource("/auto_doctors/signup2_workshop.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    
                    ID=69;

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Passwords Dont Match");
                alert.show();
                System.out.println("Passwords Dont Match");
            }
        } else {
            System.out.println("Please Fill In All the Infornmation");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill In All the Infornmation");
            alert.show();
        }
    }
}
