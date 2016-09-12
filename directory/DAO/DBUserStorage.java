import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import exception.UserStorageException;
import model.User;
import model.UserType;

public class DBUserStorage implements UserStorage{
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	public DBUserStorage() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/directory","root","helloworld");
			st = (Statement) con.createStatement();
			
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void addUser(User user) {
		
		String userID = user.getUserID();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		UserType userType = user.getUserType();
		String userTypeName = UserType.toLine(userType);
			
		String query = "INSERT INTO user VALUE ('" + userID + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + email + "', '" + userTypeName + "')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	public void removeUsers(String userID) {

			
			String query = "DELETE FROM user WHERE userID = '" + userID + "'";
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		
	}


	public boolean doesUserExist(String userID) throws UserStorageException {
		return false;
	}


	public User[] listUser() throws UserStorageException {
		return null;
	}


	public User authenticateUser(String userID, String password) throws UserStorageException {
		return null;
	}





public String getData() {
	String line = null;
	try {
		String query = "select * from user";
		rs = st.executeQuery(query);
		System.out.println("Records from Database");
		while (rs.next()) {
			String userID = rs.getString("userID");
			String password = rs.getString("password");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String userType = rs.getString("user_type");
			line = "UserID:" + userID + " Password:" + password + " First Name:" + firstName + " Last Name:" + lastName + " Email:" + email + " User Type:" + userType;
			return line;
		}
	}
	
	catch (Exception e) {
		System.out.println(e);
	}
	return line;
}


public void getDataLDAP() throws IOException {
	// TODO Auto-generated method stub
	
}
}

