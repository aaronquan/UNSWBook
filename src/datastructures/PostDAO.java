package datastructures;

import java.util.List;

public interface PostDAO {
	
	Post getPost(Integer postId);

	boolean createTextPost(Post post);
	
	List<WallPost> getWall(Integer userId);

	boolean likePost(Like like);

}
