package tests;

import java.util.ArrayList;
import java.util.List;

import datastructures.*;

public class UsersTest {

	public static void main(String[] args) {
		// Test inserting user
		User u = new User("Jake", "abc", null, null);
		UserDAOImpl udi = new UserDAOImpl();
		
		// Check Database before
		List<User> users = udi.getAllUsers();
		System.out.println("Users before:");
		for(User use : users) {
			System.out.println(use.getName());			
		}
		System.out.println("Inserting User");
		udi.addUser(u);
		u = new User("Bob", "abc", null, null);
		udi.addUser(u);
		users = udi.getAllUsers();
		System.out.println("Users After:");
		for(User use : users) {
			System.out.println(use.getName());			
		}
	}

}
