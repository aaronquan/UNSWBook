package servlets;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastructures.*;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("loginMessage", "");
		String username = request.getParameter("username");
		String firstname = request.getParameter("first_name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		if (username == null || password == null || email == null || firstname == null || surname == null || confirm_password == null 
			|| username.equals("") || password.equals("") || email.equals("") || firstname.equals("") || surname.equals("") || confirm_password.equals("")){
			request.getRequestDispatcher("registerServlet").forward(request, response); // FIXME: implement proper error handling for missing fields
		} else if (! confirm_password.equals(password)) {
			request.getRequestDispatcher("registerServlet").forward(request, response);
		}else{
			User newUser = new User(username,password, email, firstname.concat(" ").concat(surname));
			UserDAOImpl udi = new UserDAOImpl();
			boolean created = udi.addUser(newUser);
			// If user not created display some sort of error message
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

		}
	}

	private Predicate<? super String> isNull() {
		return null;
	}

}
