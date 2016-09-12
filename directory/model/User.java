public class User {
    
	private String userID;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private UserType userType;
	
	public User() {
		super();
	}
	

	
	public User(String userID, String password, String firstName, String lastName, String email, UserType userType) {
		super();
		this.userID = userID;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userType = userType;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void greet() {
		switch (userType) {
		case ADMIN:
				System.out.println("Hello Admin!");
				break;
		
		case REGULAR:
				System.out.println("Hello Regular!");
				break;
		}
	}
	

}
