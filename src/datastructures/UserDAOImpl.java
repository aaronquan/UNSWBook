package datastructures;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String userCreateStmt = "INSERT into UNSWBOOKUSER (username, pwd, name, email) values (?, ?, ?, ?)";
	private String validateStmt = "SELECT ID FROM UNSWBOOKUSER WHERE username=? AND pwd=?";

	
	
	public UserDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	@Override
	public void addUser(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(userCreateStmt);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());	
			stmt.setString(4, user.getEmailAddress());	
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> findUsers(String name) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from UNSWBOOKUSER where name like " + name;
			ResultSet results =  stmt.executeQuery(query);
			while(results.next()) {
				int id = results.getInt(1);
				String username = results.getString(2);
				String password = results.getString(3);
				User u = new User();
				u.setId(id);
				u.setName(username);
				u.setPassword(password);
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public List<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from UNSWBOOKUSER ";
			ResultSet results =  stmt.executeQuery(query);
			while(results.next()) {
				int id = results.getInt(1);
				String username = results.getString(2);
				String password = results.getString(3);
				User u = new User();
				u.setId(id);
				u.setName(username);
				u.setPassword(password);
				users.add(u);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	@Override
	public Integer validate(String user, String pwd) {
		try {
			PreparedStatement stmt = conn.prepareStatement(validateStmt);
			stmt.setString(1, user);
			stmt.setString(2, pwd);
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			results.next();
			return results.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
