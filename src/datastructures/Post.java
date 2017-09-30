package datastructures;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
	
	private Integer postId;
	private String postText;
	private String imageFileName;
	private Integer userWallId;
	private Integer userId;
	private Timestamp posted;
	
	private PostType type;
	
	
	public static Post createTextPost(String postText, Integer userId, Integer userWallId, Timestamp postedBy){
		Post p = new Post();
		p.posted = postedBy;
		p.userId = userId;
		p.postText = postText;
		p.userWallId = userWallId;
		p.type = PostType.Text;
		return p;
	}
	
	
	public Post() {
	}

	public static Post createImagePost(String imageFileName, Integer userId, Timestamp postedBy){
		Post p = new Post();
		p.posted = postedBy;
		p.userId = userId;
		p.imageFileName = imageFileName;
		p.type = PostType.Text;
		return p;
	}
	
	enum PostType{
		Image, Text
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getPostedBy() {
		return posted;
	}

	public void setPostedBy(Timestamp postedBy) {
		this.posted = postedBy;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public Timestamp getPostDate() {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer getUserWallId() {
		return userWallId;
	}


	public void setUserWallId(Integer userWallId) {
		this.userWallId = userWallId;
	}

}
