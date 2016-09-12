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

import exception.UserStorageException;
import model.User;
import model.UserType;
import util.UserParser;

public class FileUserStorage implements UserStorage {
	
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

public String getData() {
	return null;
	// TODO Auto-generated method stub
	
}

public void getDataLDAP() throws IOException {
	// TODO Auto-generated method stub
	
}

}

