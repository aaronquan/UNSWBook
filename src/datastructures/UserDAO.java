package datastructures;

import java.util.List;

public interface UserDAO {

	void addUser(User user);
	
	List<User> findUsers(String name);
	
	List<User> getAllUsers();
}
