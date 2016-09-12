import java.io.IOException;

import org.forgerock.opendj.ldap.LdapException;

import exception.UserStorageException;
import model.User;

public interface UserStorage {
	public boolean doesUserExist(String userID) throws UserStorageException;
	public void addUser(User user) throws UserStorageException;
	public User[] listUser() throws UserStorageException;
	public void removeUsers(String userID) throws UserStorageException;
	public User authenticateUser(String userID, String password) throws UserStorageException;
	public String getData();
	public void getDataLDAP() throws IOException;
}
