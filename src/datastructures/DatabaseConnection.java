package datastructures;

import java.sql.*;

public class DatabaseConnection {
	private String dbURL;
	private Connection conn;
	private Statement stmt;
	
	public DatabaseConnection(String url) {
		dbURL = url;
		conn = null;
		stmt = null;
	}
	public Connection createConnection() {
		try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
		return conn;
	}
	
	
	public void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}
