package datastructures;

import java.util.List;

public interface UserDAO {

	boolean addUser(User user);

	boolean updateUser(Integer id, String name, String email, String gender, String age, String password);
	
	List<User> findUsers(String name);
	
	List<User> findUsersAdvanced(String uname, String firstName, String surname, String age, String gender);
	
	List<User> getAllUsers();

	Integer validate(String user, String pwd);

	User lookupId(Integer id);
	
	boolean ban(Integer id);
	
	boolean isFriend(Integer user, Integer user2);
	
	public Integer validateAdmin(String user, String pwd);
	
	public Boolean isAdmin(Integer userId);
	
}
