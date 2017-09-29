package datastructures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDAOImpl implements PostDAO {

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String createTextPostStmt = "INSERT into UNSWBOOKPOST (user, text, posted) values (?, ?, ?)";
	private String getPostStmt = "SELECT (user,text, posted) from UNSWBOOKPOST where id = ?";

	public PostDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	
	public boolean createTextPost(Post post) {
		try {
			PreparedStatement stmt = conn.prepareStatement(createTextPostStmt);
			stmt.setInt(1, post.getUserId());
			stmt.setString(2, post.getPostText());
			stmt.setTimestamp(3, post.getPostedBy());
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
	public Post getPost(Integer postId) {
		try {
			PreparedStatement stmt = conn.prepareStatement(getPostStmt);
			stmt.setInt(1, postId);
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			results.next();
			Post p = Post.createTextPost(results.getString("postText"), results.getInt("user"), results.getTimestamp("posted"));
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
