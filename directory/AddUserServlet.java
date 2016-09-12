
import java.io.IOException;

import com.indigo.storage.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userID = (String) request.getParameter("userID");
		String password = (String) request.getParameter("password");
		String firstName = (String) request.getParameter("firstName");
		String lastName = (String) request.getParameter("lastName");
		String email = (String) request.getParameter("email");
		String userType2 = (String) request.getParameter("userType");
		
		UserType userType = UserType.fromName(userType2);
	
		
		User user = new User(userID, password, firstName, lastName, email, userType);
		UserStorage uStorage = new DBConnectStorage();
		try {
			uStorage.addUser(user);
		} catch (UserStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.getRequestDispatcher("/list").forward(request, response);
		response.sendRedirect("/list");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

}
