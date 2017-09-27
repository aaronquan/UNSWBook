package datastructures;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	
	public UserDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	@Override
	public void addUser(User user) {
			int id = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select count(*) from UNSWBOOKUSER");
			while(results.next()) {
				id = results.getInt(1);
			}
			
			String query = "insert into UNSWBOOKUSER values (" + id + ",'" + user.getName() + "','" + user.getPassword() + "')";
			System.out.println(query);
			boolean success = stmt.execute(query);
			
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

}
