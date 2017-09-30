package datastructures;

import java.sql.Timestamp;

public class WallPost {
	
	public String author;
	public String content;
	public Timestamp date;
	
	public WallPost(String author, String content, Timestamp date){
		this.author = author;
		this.content = content;
		this.date = date;
	}

}
