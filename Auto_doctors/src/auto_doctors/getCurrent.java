/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shihab Ahmad
 */
class getCurrent {
    public static int current_ID() throws ClassNotFoundException, SQLException {
        int ID=0;
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        try (java.sql.Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from current");
            while (rs.next()) {
                ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
}
