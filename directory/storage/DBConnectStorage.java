import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

public class DBConnectStorage implements UserStorage {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	public DBConnectStorage() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/directory","root","helloworld");
			st = (Statement) con.createStatement();
			
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void getData() {
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
				System.out.println("UserID:" + userID + " Password:" + password + " First Name:" + firstName + " Last Name:" + lastName + " Email:" + email + " User Type:" + userType);
			}
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void addUser() {
			Scanner a = null;
		
		try {
			
		a = new Scanner(System.in);

		
		System.out.println("Please enter your User ID : ");
		String userID = a.next();
		
		System.out.println("Please enter your password : ");
		String password = a.next();
		
		System.out.println("Please enter your First Name : ");
		String firstName = a.next();
		
		System.out.println("Please enter your Last Name : ");
		String lastName = a.next();
		
		System.out.println("Please enter your Email : ");
		String email = a.next();
		
		System.out.println("Are you Admin or Regular user? (Type A for Admin or R for Regular)");
		String userType = a.next();
		
		String query = "INSERT INTO user VALUE ('" + userID + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + email + "', '" + userType + "');";
		st.executeUpdate(query);
		
		
	}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deleteUser() {
		Scanner a = null;
		try {
			a = new Scanner(System.in);
			System.out.println("Please enter userID you would like to delete : ");
			String userID = a.next();
			
			String query = "DELETE FROM user where userID = '" + userID + "'";
			st.executeUpdate(query);
			
		}
		catch (Exception e) {
			System.out.println(e);			
		}
		
	}
	
	public boolean doesUserExist(String userID) throws UserStorageException {	
		
		try {
			File file = new File("<YOUR_LOCATION>/UserList.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
		
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		
			String line;
		
			while ((line = br.readLine()) != null) {
				//System.out.println("TEST: " + line);
				User user2 = UserParser.toUser(line);
				//System.out.println("COMP: " + userID + " =? " + user2.getUserID());
				if (userID.equals(user2.getUserID())) {
					return true;
				}	
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
///////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////
	
	
	public void addUser(User user) throws UserStorageException {
		try {
		
		File file = new File("<YOUR_LOCATION>/UserList.txt");
		
		
		FileWriter fw = new FileWriter(file, true); 
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		user.setPassword(user.getPassword());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setEmail(user.getEmail());
		user.setUserType(user.getUserType());
		
		
		
		bw.write(UserParser.toString(user) + "\n");
		
		bw.close();	
		}
		
		catch (IOException e) {
					e.printStackTrace();
		}
	}
		
//////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////
	

	public User[] listUser() throws UserStorageException {
		User[] user = new User[100];
		try {
			
		File file = new File("<YOUR_LOCATION>/UserList.txt");
		
		if (!file.exists()) {
			throw new UserStorageException("The file does not exist!");
		}
		
		FileReader fileReader = new FileReader(file);    //read the original file
		BufferedReader bufferedReader = new BufferedReader(fileReader);  //read the characters in the file
		String line = null;
		
		int i = 0;
		
			while ((line = bufferedReader.readLine()) != null) {
					user[i] = UserParser.toUser(line);
					i++;
							
			}
			fileReader.close();				
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}	
		return user;
	}
	
/////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
		
	public void removeUsers(String userID) {
			try {
			
			File file = new File("<YOUR_LOCATION>/UserList.txt");
			
			File temp = File.createTempFile("file",  ".txt", file.getParentFile()); 
			//create temporary file
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			//open the file for reading
		
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp)));
			//open the temp file for writing
			
	
			
				for (String line; (line = reader.readLine()) != null;) {
					String[] values = line.split(",");
					if (values[0].equals(userID)) {
						continue;
					}
					writer.println(line);
					
				}
				 //check each line with the for loop and replace it with a blank
			
				reader.close();
				writer.close();
				file.delete();
			
				temp.renameTo(file);
			}
		
			catch (IOException e) {
				e.printStackTrace();
			}
		}
//////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
	
public User authenticateUser(String userID, String password) throws UserStorageException {
	User user = null;
	try {
		File file = new File("<YOUR_LOCATION>/UserList.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);	
		String line;
		
		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			if (values[0].equals(userID)) {
				if (values[1].equals(password)) {
					user = UserParser.toUser(line);
					if (values[5].equals("admin")) {
						UserType userType = UserType.fromName("admin");
						user.setUserType(userType);
					}
					else {
						UserType userType = UserType.fromName("regular");
						user.setUserType(userType);
					}
				}
			}
		}
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	return user;
	}

}
