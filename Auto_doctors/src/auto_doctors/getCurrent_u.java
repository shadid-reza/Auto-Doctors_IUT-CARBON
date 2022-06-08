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
public class getCurrent_u {

    public static String getCurrentUser() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        ps=conn.prepareStatement("select * from current_u");
        rs=ps.executeQuery();
        rs.next();
        return rs.getString("name");
    }

}
