package datastructures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GraphDAOImpl implements GraphDAO {
	
	private String dbUrl = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
	private Connection conn;
	private String searchForConenctions = "SELECT subject, predicate, object from GRAPHTRIPLESTORE";
	private String searchForNodes = "select subject, object from entitystore where subject in (select distinct subject from entitystore where object = 'Post' or object = 'User') and predicate = 'Title'";
	
	public GraphDAOImpl() {
		DatabaseConnection dbc = new DatabaseConnection(dbUrl);
		conn = dbc.createConnection();
	}
	
	@Override
	public List<Graph> getNodeList() {
		try {
			PreparedStatement stmt = conn.prepareStatement(searchForNodes);
			boolean success = stmt.execute();
			ResultSet results = stmt.getResultSet();
			List<Graph> nodes = new ArrayList<Graph>();
			while (results.next()) {
				nodes.add(Graph.createNode(results.getString("subject"), results.getString("object")));				
			}
			return nodes;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Graph> getConnectionList() {
		try {
			PreparedStatement stmt = conn.prepareStatement(searchForConenctions);
			boolean success = stmt.execute();
			ResultSet results = stmt.getResultSet();
			List<Graph> edges = new ArrayList<Graph>();
			while (results.next()) {
				edges.add(Graph.createEdge(results.getString("predicate"), results.getString("subject"), results.getString("object")));				
			}
			return edges;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
