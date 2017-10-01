package datastructures;

public class Like {
   private Integer postId;
   private Integer userId;
   
   public Like(Integer postId, Integer userId){
	   this.postId = postId;
	   this.userId = userId;
   }

	public Integer getPostId() {
		return postId;
	}
	
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
