import storage.*;

public class deleteUserSQL {

	public static void main(String[] args) {
		DBConnectStorage connect = new DBConnectStorage();
		connect.deleteUser();

	}

}
