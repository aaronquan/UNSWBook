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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<String> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<String> likedBy) {
		this.likedBy = likedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
