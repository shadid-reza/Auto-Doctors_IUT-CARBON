package auto_doctors;

import javafx.event.ActionEvent;
import java.sql.*;
import auto_doctors.jdbc;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Shihab Ahmad
 */
public class DBUtils {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        try {
            root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void tempChange(String s) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(s);
        alert.show();
    }

    public static void error(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(s);
        alert.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String contact, String email, String address) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheck = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        psCheck = conn.prepareStatement("SELECT * FROM users WHERE NID= ?");
        psCheck.setString(1, username);
        resultSet = psCheck.executeQuery();
        if (resultSet.isBeforeFirst()) {
            System.out.println("User Already Exists");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User Already exists");
            alert.show();
        } else {
            psInsert = conn.prepareStatement("INSERT INTO users(NID, Mobile, Email, Password, Home) VALUES (?, ?, ?, ?, ?)");
            psInsert.setString(1, username);
            psInsert.setString(2, contact);
            psInsert.setString(3, email);
            psInsert.setString(4, password);
            psInsert.setString(5, address);
            psInsert.executeUpdate();
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psCheck != null) {
            try {
                psCheck.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int signUpWorkshop(ActionEvent event, String name, String password, String mobile, String email, String location) throws ClassNotFoundException, SQLException {
        int ID = 0;
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheck = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        psCheck = conn.prepareStatement("SELECT * FROM workshops WHERE Name= ?");
        psCheck.setString(1, name);
        resultSet = psCheck.executeQuery();
        if (resultSet.isBeforeFirst()) {
            System.out.println("Username Already Exists. Type in a different name!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username Already Exists. Type in a different name!");
            alert.show();
        } else {
            //generating ID

            String query = "select * from counter where type='workshop'";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ID = rs.getInt(2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //ID generated
            //updating database counter
            String increment = "UPDATE counter set count=count+1 where type='workshop'";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(increment);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //updated
            //insert new workshop to database
            psInsert = conn.prepareStatement("INSERT INTO workshops(ID, Name,Mobile, Email, Location, Password) VALUES (?, ?, ?, ?, ?,?)");
            psInsert.setInt(1, ID);
            psInsert.setString(2, name);
            psInsert.setString(3, mobile);
            psInsert.setString(4, email);
            psInsert.setString(5, location);
            psInsert.setString(6, password);
            psInsert.executeUpdate();
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psInsert != null) {
            try {
                psInsert.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (psCheck != null) {
            try {
                psCheck.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ID;
    }

    public static int logInUser(ActionEvent event, String username, String Password) throws ClassNotFoundException, SQLException, IOException {
        int flag = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        ps = conn.prepareStatement("SELECT password FROM users WHERE NID = ?");
        ps.setString(1, username);
        rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("User Not Found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect");
            alert.show();
        } else {
            while (rs.next()) {
                String retrievedPassword = rs.getString("password");
                if (retrievedPassword.equals(DigestUtils.md5Hex(Password))) {
                    PreparedStatement psCurrent = conn.prepareStatement("Insert into current_u values(?)");
                    psCurrent.setString(1, username);
                    psCurrent.execute();
                    tempChange("Log in Successful");
                    flag = 1;

                } else {
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect");
                    alert.show();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static int logInWorkshop(ActionEvent event, String username, String Password) throws ClassNotFoundException, SQLException {
        int flag = 0;
        System.out.println("Hello World");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        ps = conn.prepareStatement("SELECT password FROM workshops WHERE Name = ?");
        ps.setString(1, username);
        rs = ps.executeQuery();
        if (!rs.isBeforeFirst()) {
            System.out.println("Workshop Not Found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect");
            alert.show();
        } else {
            while (rs.next()) {
                String retrievedPassword = rs.getString("password");
                if (retrievedPassword.equals(DigestUtils.md5Hex(Password))) {

                    //get ID from name
                    PreparedStatement psGetID = conn.prepareStatement("select ID from workshops where name=?");
                    psGetID.setString(1, username);
                    ResultSet rs_currentID = psGetID.executeQuery();
                    rs_currentID.next();
                    int temp = rs_currentID.getInt("ID");
                    //insert current ID
                    PreparedStatement psCurrent = conn.prepareStatement("Insert into current values(?)");
                    psCurrent.setInt(1, temp);
                    psCurrent.execute();
                    tempChange("Log in Successful");
                    flag = 1;
                } else {
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect");
                    alert.show();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}
