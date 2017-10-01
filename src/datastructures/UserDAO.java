package datastructures;

import java.util.List;

public interface UserDAO {

	boolean addUser(User user);

	boolean updateUser(String uid, String name, String email, String gender, String age, String password);
	
	List<User> findUsers(String name);
	
	List<User> findUsersAdvanced(String uname, String firstName, String surname, String age, String gender);
	
	List<User> getAllUsers();

	Integer validate(String user, String pwd);

	User lookupId(Integer id);
	
	boolean ban(String email);
	
	boolean isFriend(Integer user, Integer user2);
	
}
