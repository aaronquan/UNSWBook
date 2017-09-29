package datastructures;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String userCreateStmt = "INSERT into UNSWBOOKUSER (username, pwd, name, email) values (?, ?, ?, ?)";
	private String validateStmt = "SELECT ID FROM UNSWBOOKUSER WHERE username=? AND pwd=?";
	private String lookupStmt = "SELECT username, pwd, name, email FROM UNSWBOOKUSER WHERE id=?";
	private String findStmt = "SELECT * FROM UNSWBOOKUSER WHERE username like '%?%'";

	
	
	public UserDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	@Override
	public boolean addUser(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(userCreateStmt);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());	
			stmt.setString(4, user.getEmailAddress());	
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			return success;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> findUsers(String name) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UNSWBOOKUSER WHERE username like '%" + name + "%'");
			//stmt.setString(1, name);
			boolean success = stmt.execute();
			ResultSet results =  stmt.getResultSet();
			while(results.next()) {
				int id = results.getInt(1);
				String username = results.getString(2);
				String password = results.getString(3);
				String rname = results.getString(4);
				String email = results.getString(5);
				User u = new User(username,password,email,rname);
				u.setId(id);
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
	@Override
	public User lookupId(Integer id) {
		try {
			PreparedStatement stmt = conn.prepareStatement(lookupStmt);
			stmt.setInt(1, id);
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			results.next();
			User u = new User(results.getString("username"), results.getString("pwd"), results.getString("email"), results.getString("name"));
			return u;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
