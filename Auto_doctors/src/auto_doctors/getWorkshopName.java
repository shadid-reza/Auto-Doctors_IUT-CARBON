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
public class getWorkshopName {

    public static String getName(int ID) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        ps=conn.prepareStatement("select Name from workshops where ID=?");
        ps.setInt(1, ID);
        rs=ps.executeQuery();
        rs.next();
        return rs.getString("Name");
    }
}
