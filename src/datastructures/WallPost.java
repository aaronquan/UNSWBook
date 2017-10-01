package datastructures;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WallPost {
	
	public String author;
	public String content;
	public Timestamp date;
	public List<String> likedBy;
	public Integer id;
	
	public WallPost(Integer id, String author, String content, Timestamp date){
		this.id = id;
		this.author = author;
		this.content = content;
		this.date = date;
		this.likedBy = new ArrayList<String>();
	}

}
