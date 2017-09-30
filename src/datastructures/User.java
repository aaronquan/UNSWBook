package datastructures;

import java.sql.Date;

public class User {
	private int id;
	private String username;
	private String password;
	private String emailAddress;
	private String name;
	private Integer age;
	private String gender;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public User() {}
	
	public User (String username, String password, String email, String name) {
		this.username = username;
		this.password = password;
		this.emailAddress = email;
		this.name = name;
	}
	
}
