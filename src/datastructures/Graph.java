package datastructures;

public class Graph {
	private String id;
	private String title;
	private String from;
	private String to;
	private String shape;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Graph createNode(String id, String title) {
		Graph node = new Graph();
		node.id = id;
		if (id.matches(".*P.*")) {
			node.shape = "box";
		} else {
			node.shape = "ellipse";
		}
		node.title = title;
		return node;
	}
	
	public static Graph createEdge(String id, String from, String to) {
		Graph edge = new Graph();
		edge.id = id;
		if (id.matches(".*E1.*")) {
			edge.title = "FriendsWith";
		} else if (id.matches(".*E2.*")) {
			edge.title = "Posted";
		} else if (id.matches(".*E3.*")) {
			edge.title = "Liked";
		} else {
			edge.title = "";
		}
		edge.to = to;
		edge.from = from;
		return edge;
	}
}
