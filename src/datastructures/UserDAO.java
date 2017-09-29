package datastructures;

import java.util.List;

public interface UserDAO {

	boolean addUser(User user);
	
	List<User> findUsers(String name);
	
	List<User> getAllUsers();

	Integer validate(String user, String pwd);

	User lookupId(Integer id);
}
