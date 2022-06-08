/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shihab Ahmad
 */
public class getParts {

    public static String PartsName(int ID) throws ClassNotFoundException, SQLException {
        String name;
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select name from parts_m where id=?");
        ps.setInt(1, ID);
        resultSet = ps.executeQuery();
        resultSet.next();
        name = resultSet.getString(1);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
}
