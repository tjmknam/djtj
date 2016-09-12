public enum UserType {
	ADMIN, REGULAR;
	
	public static String toLine(UserType userType) {
		if (userType.equals(ADMIN)) {
			return "admin";
		}
		else if (userType.equals(REGULAR)) {
			return "regular";
		}
		else {
			return null;
		}
	}
	
	
	public static UserType fromName(String name) {
		if ("admin".equals(name)) {
			return ADMIN;
		} else if ("regular".equals(name)) {
			return REGULAR;
		} else {
			return null;
		}
	}
}
