package datastructures;

import java.util.List;

public interface UserActivityDAO {

		public List<UserActivity> getUserActivity(int userID);
		
		public void addUserActivity(UserActivity ua);
}
