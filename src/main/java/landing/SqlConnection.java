package landing;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
// This class can be used to initialize the database connection
public class SqlConnection {
    public static Connection initializeDatabase()
        throws SQLException, ClassNotFoundException
    {
        // Initializing the information related to connection 
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database details to access the database
        String dbName = "jswbankaakash";
        String dbUsername = "root";
        String dbPassword = "root";
  
       Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                                                     dbUsername, 
                                                     dbPassword);
        return con;
    }
}

