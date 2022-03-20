/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

import javafx.event.ActionEvent;
import java.sql.*;
import auto_doctors.JDBC;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 *
 * @author Shihab Ahmad
 */
public class DBUtils {
    
    public static void changeScene(ActionEvent event, String fxmlFile){
        Parent root = null;
        try{
            root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        } catch(IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 600,400));
        stage.show();
    }
    
    public static void signUpUser(ActionEvent event, String username, String password, String contact) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheck = null;
        ResultSet resultSet = null;
        JDBC connection = new JDBC();
        conn = connection.getConnection();
        psCheck=conn.prepareStatement("SELECT * FROM users WHERE username= ?");
        psCheck.setString(1,username);
        resultSet = psCheck.executeQuery();
        if(resultSet.isBeforeFirst()){
            System.out.println("User Already Exists");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User Already exists");
            alert.show();
        }
        else{
            psInsert = conn.prepareStatement("INSERT INTO users(NID, Mobile, Email, Password, Home) VALUES (?, ?, ?, ?, ?)");
            psInsert.setString(1, username);
            psInsert.setString(2, contact);
            psInsert.setString(3, password);
            psInsert.setString(4, "NULL");
            psInsert.setString(5, "NULL");
            psInsert.executeUpdate();
        }
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(psInsert!=null){
            try{
                psInsert.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(psCheck!=null){
            try{
                psCheck.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void logInUser(ActionEvent event, String username, String Password) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JDBC connection = new JDBC();
        conn = connection.getConnection();
        ps=conn.prepareStatement("SELECT password FROM users WHERE username = ?");
        ps.setString(1,username);
        rs = ps.executeQuery();
        if(!rs.isBeforeFirst()){
            System.out.println("User Not Found");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect");
            alert.show();
        }
        else{
            while(rs.next()){
                String retrievedPassword = rs.getString("password");
                if(retrievedPassword.equals(Password)){
                    //changeScene()
                }
                else{
                    System.out.println("Password did not match");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Provided credentials are incorrect");
                    alert.show();
                }
            }
        }
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try{
                ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
