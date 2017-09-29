package datastructures;

public interface PostDAO {
	
	Post getPost(Integer postId);

	boolean createTextPost(Post post);

}
