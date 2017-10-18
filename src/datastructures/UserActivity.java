package datastructures;
import java.sql.Timestamp;

public class UserActivity {
	
	private int userId;
	private String report;
	private Timestamp time;
	
	public static UserActivity createActivity(int userId, String report, Timestamp time) {
		UserActivity ua = new UserActivity();
		ua.userId = userId;
		ua.report = report;
		ua.time = time;
		return ua;
	}
	public int getUserId() {
		return userId;
	}
	public void setId(int userId) {
		this.userId = userId;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
