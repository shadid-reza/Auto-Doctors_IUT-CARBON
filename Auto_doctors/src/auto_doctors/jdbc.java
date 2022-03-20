package auto_doctors;

import java.sql.*;

public class jdbc {
    public Connection connection;
    public Connection getConnection() throws ClassNotFoundException {
        String url ="jdbc:mysql://localhost:3307/shihab";
        String username ="root";
        String password = "" ;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = (Connection)DriverManager.getConnection(url,username,password);
        System.out.println("Connection Successful");
//        Statement stmt=connection.createStatement();
//        ResultSet rset= stmt.executeQuery("Select * From information where id=2");
//        rset.next();
//        String s=rset.getString("name");
//        System.out.println("Name is " + s);
        }
        catch(SQLException e){
            System.out.println("Exception"+" " + e.getMessage());
        }
        return connection;
    }
}
