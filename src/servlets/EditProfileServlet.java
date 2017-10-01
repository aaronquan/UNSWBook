package servlets;

import java.io.IOException;
import java.util.function.Predicate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.*;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseConnection dbc = (DatabaseConnection) request.getSession().getAttribute("dbc");
		if(dbc == null) {
			String dbURL = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
			dbc = new DatabaseConnection(dbURL);
			dbc.createConnection();
			request.getSession().setAttribute("dbc", dbc);
		}
		assert (dbc != null);
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null){
			Integer userId = (Integer) session.getAttribute("user");
			UserDAO usd = new UserDAOImpl();
			User u = usd.lookupId(userId);
			System.out.println(u);
			request.setAttribute("user", u);
			request.getRequestDispatcher("editProfile.jsp").forward(request, response);
		}else{
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String uid = request.getParameter("uid");
		
		Integer userId = (Integer) request.getSession(false).getAttribute("user");
		UserDAO usd = new UserDAOImpl();
		User user = usd.lookupId(userId);
		request.setAttribute("user", user);
		
		if (password.equals(confirm_password)) {
			UserDAO udao = new UserDAOImpl();
			boolean successfulUpdate =  udao.updateUser(userId, name, email, gender, age, confirm_password);
			request.setAttribute("updateSuccess", successfulUpdate);
			System.out.println(successfulUpdate);
			request.getRequestDispatcher("Profile").forward(request, response);
		} else {
			request.setAttribute("updateSuccess", false);
			request.getRequestDispatcher("registerServlet").forward(request, response);
		}
	}

}
