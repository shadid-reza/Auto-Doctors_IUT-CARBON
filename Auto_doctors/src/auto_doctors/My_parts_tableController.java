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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hmsha
 */
public class My_parts_tableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<ServicesTableWorkshop> parts_table;

    @FXML
    private TableColumn<ServicesTableWorkshop, String> m_avail;

    @FXML
    private TableColumn<ServicesTableWorkshop, Integer> m_id;

    @FXML
    private TableColumn<ServicesTableWorkshop, String> m_name;

    @FXML
    private TableColumn<ServicesTableWorkshop, Integer> m_price;
    @FXML
    private Pane edit_parts_pane;

    @FXML
    private Pane remove_parts_pane;
    @FXML
    private Pane add_parts_pane;

    @FXML
    private ComboBox<String> addParts_box;

    @FXML
    private TextField addParts_price;

    @FXML
    private ComboBox<String> editParts_box1;

    @FXML
    private TextField editPrice_box;

    @FXML
    private ComboBox<String> editparts_box2;

    @FXML
    private ComboBox<String> removeParts_box;

    ObservableList<ServicesTableWorkshop> obslist = FXCollections.observableArrayList();

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
            PreparedStatement ps = conn.prepareStatement("select parts_ID,name,price,availability from parts where workshop_ID=?");
            ps.setInt(1, workshop_ID);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                obslist.add(new ServicesTableWorkshop(resultSet.getInt("parts_ID"),
                        resultSet.getInt("price"), resultSet.getString("name"), resultSet.getString("availability")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(My_services_tableController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(My_services_tableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Integer app_id, a_price;
        //String a_name, a_avail;
        m_id.setCellValueFactory(new PropertyValueFactory<>("app_id"));
        m_name.setCellValueFactory(new PropertyValueFactory<>("a_name"));
        m_price.setCellValueFactory(new PropertyValueFactory<>("a_price"));
        m_avail.setCellValueFactory(new PropertyValueFactory<>("a_avail"));

        parts_table.setItems(obslist);

        try (java.sql.Statement state = conn.createStatement()) {
            ResultSet rs = state.executeQuery("select name from parts_m");
            while (rs.next()) {
                addParts_box.getItems().addAll(rs.getString("name"));
                editParts_box1.getItems().addAll(rs.getString("name"));
                removeParts_box.getItems().addAll(rs.getString("name"));
            }
            editparts_box2.getItems().addAll("Available");
            editparts_box2.getItems().addAll("Unavailable");
            editparts_box2.setValue("Available");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void HandleBackBtnWorkshop(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/workshop_profile.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void addparts_btn1(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        add_parts_pane.setVisible(true);
        edit_parts_pane.setVisible(false);
        remove_parts_pane.setVisible(false);

    }

    public void addparts_btn2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        Connection conn = null;
        //ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!addParts_box.getValue().trim().isEmpty() && !addParts_price.getText().trim().isEmpty()) {
            String name = addParts_box.getValue();

            int price = Integer.parseInt(addParts_price.getText());

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

                //Refreshing the table
                root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } else {
            DBUtils.tempChange("Please fill in the input fields");
        }

    }

    public void editparts_btn1(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        add_parts_pane.setVisible(false);
        edit_parts_pane.setVisible(true);
        remove_parts_pane.setVisible(false);

    }

    public void editparts_btn2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!editParts_box1.getValue().trim().isEmpty() && !editparts_box2.getValue().trim().isEmpty()
                && !editPrice_box.getText().trim().isEmpty()) {

            String parts_name = editParts_box1.getValue();
            String parts_available = editparts_box2.getValue();
            int new_price = Integer.parseInt(editPrice_box.getText());

            PreparedStatement ps = conn.prepareStatement("select id from parts_m where name=?");
            ps.setString(1, parts_name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int parts_id = rs.getInt(1);
            int workshop_id = getCurrent.current_ID();
            //check if the part exists for the current workshop or not
            PreparedStatement psCheck = conn.prepareStatement("Select * from parts where workshop_ID=? AND parts_ID=?");
            psCheck.setInt(1, workshop_id);
            psCheck.setInt(2, parts_id);
            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.isBeforeFirst()) {
                PreparedStatement psEdit = conn.prepareStatement("update parts set price = ?, availability = ? where workshop_ID=? AND parts_ID=?");
                psEdit.setInt(1, new_price);
                psEdit.setString(2, parts_available);
                psEdit.setInt(3, workshop_id);
                psEdit.setInt(4, parts_id);
                psEdit.executeUpdate();
                DBUtils.tempChange("Edit done successfully!");

                //Refreshing the table
                root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                DBUtils.error("You currently dont have this part!");
            }
        } else {
            DBUtils.error("Please fill in the input fields");
        }

    }

    public void removeparts_btn1(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        add_parts_pane.setVisible(false);
        edit_parts_pane.setVisible(false);
        remove_parts_pane.setVisible(true);

    }

    public void removeparts_btn2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        try {
            conn = connection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup2_workshopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!removeParts_box.getValue().trim().isEmpty()) {

            String parts_name = removeParts_box.getValue();

            PreparedStatement ps = conn.prepareStatement("select id from parts_m where name=?");
            ps.setString(1, parts_name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int parts_id = rs.getInt(1);
            int workshop_id = getCurrent.current_ID();
            //check if the part exists for the current workshop or not
            PreparedStatement psCheck = conn.prepareStatement("Select * from parts where workshop_ID=? AND parts_ID=?");
            psCheck.setInt(1, workshop_id);
            psCheck.setInt(2, parts_id);
            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.isBeforeFirst()) {
                PreparedStatement psRemove = conn.prepareStatement("Delete from parts where workshop_ID=? AND parts_ID=?");
                psRemove.setInt(1, workshop_id);
                psRemove.setInt(2, parts_id);
                psRemove.executeUpdate();
                DBUtils.tempChange("Deletetion Done Successfully!");

                //Refreshing the table
                root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                DBUtils.error("You dont currently have the part that you have selected!");
            }
        } else {
            DBUtils.error("Please fill in the input fields");
        }

    }

    public void refreshBtn(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/auto_doctors/my_parts_table.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
