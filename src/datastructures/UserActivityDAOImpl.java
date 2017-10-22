package datastructures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserActivityDAOImpl implements UserActivityDAO{

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String addUserActivity = "INSERT into UNSWBOOKUSERACTIVITY (userid, report, reported) values (?, ?, CURRENT_TIMESTAMP)";
	private String getActivityStmt = "SELECT report, reported from UNSWBOOKUSERACTIVITY where userid = ? ORDER BY reported DESC";
	
	public UserActivityDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	
	
	@Override
	public List<UserActivity> getUserActivity(int userID) {
		try {
			PreparedStatement stmt = conn.prepareStatement(getActivityStmt);
			stmt.setInt(1, userID);
			boolean success = stmt.execute();
			ResultSet results = stmt.getResultSet();
			List<UserActivity> uaList = new ArrayList<UserActivity>();
			while (results.next()) {
				uaList.add(UserActivity.createActivity(userID, results.getString("report"), results.getTimestamp("reported")));				
			}
			return uaList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addUserActivity(UserActivity ua) {
		try {
			PreparedStatement stmt = conn.prepareStatement(addUserActivity);
			stmt.setInt(1, ua.getUserId());
			stmt.setString(2, ua.getReport());
			boolean success = stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
