/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auto_doctors;

import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shihab Ahmad
 */
public class getMinDistance {

    public static String minDistance(double la1, double ln1) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        ResultSet resultSet = null;
        jdbc connection = new jdbc();
        conn = connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select Latitude,Longitude,Name from workshops");
        resultSet = ps.executeQuery();
        double min_dist = Integer.MAX_VALUE;
        String nearest = "NULL";
        double w_la = 0, w_ln = 0;
        while (resultSet.next()) {

            double la2 = resultSet.getDouble("Latitude");
            double ln2 = resultSet.getDouble("Longitude");
            String temp_workshop = resultSet.getString("Name");
            double temp_dist = distance.meters(la1, ln1, la2, ln2);
            if (temp_dist < min_dist) {
                min_dist = temp_dist;
                nearest = temp_workshop;
                w_la = la2;
                w_ln = ln2;
            }
        }

        PreparedStatement psUP = conn.prepareStatement("Insert into nearest values(?,?,?,?,?)");
        psUP.setString(1, nearest);
        psUP.setDouble(2, la1);
        psUP.setDouble(3, ln1);
        psUP.setDouble(4, w_la);
        psUP.setDouble(5, w_ln);
        psUP.executeUpdate();
        conn.close();
        return nearest;

    }

}
