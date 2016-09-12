import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.indigo.storage.*;
import com.indigo.executable.*;

public class ListUsersServlet extends HttpServlet {
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
			
			//User user = (User) request.getAttribute("user");
			
			doGet(request,response);
			

			//FileUserStorage storageList = new FileUserStorage();
			//sotrageList.listUsers();
			
			//PrintWriter out = response.getWriter();
			//out.println ("<html>\n" + "<head>\n" + "<title>List of Users<\title>\n" + "<\head>\n" + "<body>\n" + "<table cellspacing="5" cellpadding="5" border="1">\n" + "<tr>\n" + "<td align="right">User ID:</td>\n" + "<td>" + user.getUserID() + "</td>\n" + "</tr>\n" + "<tr>\n" + "<td align=right>Password:</td>\n" + "<td>" + user.getPassword() + "</td>\n" + "<tr>\n" + "<tr>\n" + "<td align=right>First Name:</td>\n" + "<td>" + user.getFirstName() + "</td>\n" + "<tr>\n" + "<tr>\n" + "<td align=right>Last Name:</td>\n" + "<td>" + user.getLastName() + "</td>\n" + "<tr>\n" + "<tr>\n" + "<td align=right>Email:</td>\n" + "<td>" + user.getEmail() + "</td>\n" + "<tr>\n" + "tr>\n" + "<td align=right>User Type:</td>\n" + "<td>" + user.getUserType() + "</td>\n" + "<tr>\n" + "<\table>");
		}
	
	protected void doGet (
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		UserStorage uStorage = new DBConnectStorage();
		User[] users = new User[100];
		try {
			users = uStorage.listUser();
	
		} catch (UserStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("Users", users);
		request.getRequestDispatcher("listUsers.jsp").forward(request, response);
		}
}
