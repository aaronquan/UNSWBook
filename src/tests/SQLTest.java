package tests;
import java.sql.*;

public class SQLTest {

	public static void main(String[] args) {
		String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
		String tableName = "UNSWBOOKUSER";
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			
			Connection conn = DriverManager.getConnection(dbUrl);
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from "+tableName);
			ResultSetMetaData rsmd = results.getMetaData();
			for (int i=1; i<=rsmd.getColumnCount(); i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }
			System.out.println("\n-------------------------------------------------");
			while(results.next())
            {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
            conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
