package datastructures;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String userCreateStmt = "INSERT into UNSWBOOKUSER (username, pwd, name, email) values (?, ?, ?, ?)";
	private String validateStmt = "SELECT ID, BANNED FROM UNSWBOOKUSER WHERE username=? AND pwd=? AND isadmin=?";
	private String lookupStmt = "SELECT username, pwd, name, email, gender, age FROM UNSWBOOKUSER WHERE id=?";
	private String findStmt = "SELECT * FROM UNSWBOOKUSER WHERE username like '%?%'";
	private String banStmt = "UPDATE UNSWBOOKUSER SET banned=true where id=?";
	private String isFriendStmt = "SELECT 1 FROM UNSWBOOKFRIENDS where (person_a=? and person_b=? and confirmed=true) "
			+ "or (person_a=? and person_b=? and confirmed=true)";
	private String isAdminStmt = "SELECT 1 from UNSWBOOKUSER where id=? and isadmin=true";
	private String createFriendStmt  = "INSERT into UNSWBOOKFRIENDS (person_a, person_b) values (?,?)";
	private String confirmFriendStmt = "UPDATE UNSWBOOKFRIENDS set confirmed=true where person_a=? and person_b=?";
	
	
	
	public UserDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	
	public boolean createFriendReq(Integer from, Integer to){
		try {
			PreparedStatement stmt = conn.prepareStatement(createFriendStmt);
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			boolean success = stmt.execute();
			return success;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean confirmFriendReq(Integer from, Integer to){
		try {
			PreparedStatement stmt = conn.prepareStatement(confirmFriendStmt);
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			boolean success = stmt.executeUpdate() == 0;
			
			stmt = conn.prepareStatement("INSERT into GRAPHTRIPLESTORE values ('U' || cast(? as CHAR(3)), 'E2', 'U' || cast(? as CHAR(3)))");
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			System.out.println(stmt.execute());
			
			return success;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
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
			
			stmt = conn.prepareStatement("INSERT into ENTITYSTORE values"
					+ " ('U' || cast((SELECT MAX(ID) from UNSWBOOKUSER) as CHAR(3)), 'Type', 'User'),"
					+ " ('U' || cast((SELECT MAX(ID) from UNSWBOOKUSER) as CHAR(3)), 'Class', 'entityNode'),"
					+ " ('U' || cast((SELECT MAX(ID) from UNSWBOOKUSER) as CHAR(3)), 'Title', ?)");
			stmt.setString(1, user.getUsername());
			System.out.println(stmt.execute());
			
			return success;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateUser(Integer uid, String name, String email, String gender, String age, String password) {
		try {
			String set_addition = "SET ";
			if (! name.equals("")) {
				name = set_addition + "NAME = '" + name + "'";
				set_addition = ", ";
			}
			if (! email.equals("")) {
				email = set_addition + "EMAIL = '" + email + "'";
				set_addition = ", ";
			}
			if (gender.matches("^(Male|Female|Other)$")) {
				gender = set_addition + "GENDER = '" + gender + "'";
				set_addition = ", ";
			} else {
				gender = set_addition + "GENDER = NULL";
				set_addition = ", ";
			}
			if (age.matches("^[0-9]+$")) {
				age = set_addition + "Age = " + age;
				set_addition = ", ";
			} else {
				age = set_addition + "AGE = NULL";
				set_addition = ", ";
			}
			if (! password.equals("")) {
				password = set_addition + "PWD = '" + password + "'";
				set_addition = ", ";
			}
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE UNSWBOOKUSER " + name + email + age + gender + password + " WHERE ID = " + uid);
			System.out.println("UPDATE UNSWBOOKUSER " + name + email + age + gender + password + " WHERE ID = " + uid);
			boolean success = stmt.execute();
			System.out.println(success);
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
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UNSWBOOKUSER WHERE UPPER(USERNAME) LIKE UPPER('%" + name + "%') OR UPPER(NAME) LIKE UPPER('%" + name + "%')");
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
	
	
	public List<User> findUsersAdvanced(String uname, String firstName, String surname, String age, String gender) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		try {
			String where_addition = "WHERE ";
			if (! uname.equals("")) {
				uname = where_addition + "UPPER(USERNAME) LIKE UPPER('%" + uname + "%')";
				where_addition = " AND ";
			}
			if (! firstName.equals("")) {
				firstName = where_addition + "UPPER(NAME) LIKE UPPER('%" + firstName + "%')";
				where_addition = " AND ";
			}
			if (! surname.equals("")) {
				surname = where_addition + "UPPER(NAME) LIKE UPPER('%" + surname + "%')";
				where_addition = " AND ";
			}
			if (! age.equals("")) {
				age = where_addition + "AGE = " + age;
				where_addition = " AND ";
			}
			if (! gender.equals("")) {
				gender = where_addition + "GENDER = '" + gender + "'";
				where_addition = " AND ";
			}
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM UNSWBOOKUSER " + uname + firstName + surname + age + gender);
			
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
			stmt.setBoolean(3, false);
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			if (!results.next()) return null;
			if (results.getBoolean("BANNED")){
				return -1;
			}else{
				return results.getInt("ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public Integer validateAdmin(String user, String pwd) {
		try {
			PreparedStatement stmt = conn.prepareStatement(validateStmt);
			stmt.setString(1, user);
			stmt.setString(2, pwd);
			stmt.setBoolean(3, true);
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			if (!results.next()) return null;
			if (results.getBoolean("BANNED")){
				return -1;
			}else{
				return results.getInt("ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
			u.setAge(results.getInt("age"));
			u.setGender(results.getString("gender"));
			return u;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean ban(Integer id) {
		try {
			PreparedStatement stmt = conn.prepareStatement(banStmt);
			stmt.setInt(1, id);
			System.out.println(stmt.toString());
			return stmt.executeUpdate()==0 ? false : true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean isFriend(Integer user, Integer user2) {
		try {
			PreparedStatement stmt = conn.prepareStatement(isFriendStmt);
			stmt.setInt(1, user);
			stmt.setInt(2, user2);
			stmt.setInt(3, user2);
			stmt.setInt(4, user);
			System.out.println(stmt.toString());
			boolean success = stmt.execute();
			return(stmt.getResultSet().next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean isAdmin(Integer userId) {
		try {
			PreparedStatement stmt = conn.prepareStatement(isAdminStmt);
			stmt.setInt(1, userId);
			stmt.execute();
			return (stmt.getResultSet().next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
