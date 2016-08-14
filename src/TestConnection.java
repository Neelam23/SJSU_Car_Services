import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
 
public class TestConnection {
 
  public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
     
        String driver   = "com.mysql.jdbc.Driver";
        String db       = "sys";
        String url      = "jdbc:mysql://127.0.0.1:3306" + db;
        String user     = "root";
        String pass     = "123456789";
  
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys",user,pass);
            System.out.println("Connected to database : " + db);
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
     
    }
}