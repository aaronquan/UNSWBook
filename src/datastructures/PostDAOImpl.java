package datastructures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {

	private String dbUrl = "jdbc:derby://localhost:1527/UNSWdatabase;create=true;user=user;password=user";
	private Connection conn;
	private String createTextPostStmt = "INSERT into UNSWBOOKPOST (userid, onwall, post, posted) values (?, ?, ?)";
	private String getPostStmt = "SELECT (userid, onwall, post, posted) from UNSWBOOKPOST where id = ?";
	private String getWallStmt = "SELECT (name, post, posted) from UNSWBOOKPOST "
			+ "INNER JOIN UNSWBOOKUSER WHERE onwall=? and userid=UNSWBOOKUSER.id ORDER BY posted DESC";

	public PostDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	
	public boolean createTextPost(Post post) {
		try {
			PreparedStatement stmt = conn.prepareStatement(createTextPostStmt);
			stmt.setInt(1, post.getUserId());
			stmt.setInt(2, post.getUserWallId());
			stmt.setString(3, post.getPostText());
			stmt.setTimestamp(4, post.getPostedBy());
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
			Post p = Post.createTextPost(results.getString("post"), results.getInt("user"), results.getInt("onwall"), results.getTimestamp("posted"));
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<WallPost> getWall(Integer userId) {
		try {
			PreparedStatement stmt = conn.prepareStatement(getWallStmt);
			stmt.setInt(1, userId);
			boolean success = stmt.execute();
			if (!success) return null;
			ResultSet results = stmt.getResultSet();
			List<WallPost> xs = new ArrayList<WallPost>();
			while(results.next()){
				xs.add(new WallPost(results.getString("name"), results.getString("post"), results.getTimestamp("posted")));
			}
			return xs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
