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
public class getWorkshopID {

    public static int WorkshopID(String name) throws ClassNotFoundException, SQLException {
        int ID=0;
        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select id from workshops where name=?");
        ps.setString(1, name);
        resultSet = ps.executeQuery();
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
            ID = resultSet.getInt(1);
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ID;
    }

}
