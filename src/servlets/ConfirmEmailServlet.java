package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastructures.User;
import datastructures.UserDAOImpl;

/**
 * Servlet implementation class confirmEmailServlet
 */
@WebServlet("/ConfirmEmailServlet")
public class ConfirmEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("confirm_email.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("username"); 	 
		 String firstName = request.getParameter("firstname"); 	 
		 String surname = request.getParameter("surname");
		 String email = request.getParameter("email"); 	 
		 String password = request.getParameter("password"); 	 
		 
			if (username == null || password == null || email == null || firstName == null || surname == null ){
				request.getRequestDispatcher("registerServlet").forward(request, response); // FIXME: implement proper error handling for missing fields
			}else{
				User newUser = new User(username,password, email, firstName.concat(" ").concat(surname));
				UserDAOImpl udi = new UserDAOImpl();
				boolean created = udi.addUser(newUser);
				// If user not created display some sort of error message
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			} 
	}

}
