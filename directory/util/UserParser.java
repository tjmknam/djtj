import model.User;
import .UserType;

public class UserParser {
	
	private static final String SEP = ",";
	
	public static String toString(User user) {
		UserType userType = user.getUserType();
		String userTypeName = UserType.toLine(userType);
		
		StringBuilder sb = new StringBuilder();
		sb.append(user.getUserID());
		sb.append(SEP).append(user.getPassword());
		sb.append(SEP).append(user.getFirstName());
		sb.append(SEP).append(user.getLastName());
		sb.append(SEP).append(user.getEmail());
		sb.append(SEP).append(userTypeName);
		return sb.toString();
	}
	
	public static User toUser(String line) {
		String[] values = line.split(",");
		

		UserType userType = UserType.fromName(values[5]);
		
		User user = new User();
		user.setUserID(values[0]);
		user.setPassword(values[1]);
		user.setFirstName(values[2]);
		user.setLastName(values[3]);
		user.setEmail(values[4]);
		user.setUserType(userType);
		
		return user;
	}

}
