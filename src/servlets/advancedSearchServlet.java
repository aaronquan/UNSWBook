package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.DatabaseConnection;
import datastructures.User;
import datastructures.UserDAO;
import datastructures.UserDAOImpl;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/advancedSearchServlet")
public class advancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public advancedSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") != null){
			Integer userId = (Integer) session.getAttribute("user");
			UserDAO usd = new UserDAOImpl();
			User u = usd.lookupId(userId);
			request.setAttribute("user", u);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}else{
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = (Integer) request.getSession(false).getAttribute("user");
		UserDAO usd = new UserDAOImpl();
		User u = usd.lookupId(userId);
		request.setAttribute("user", u);
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String name = request.getParameter("first_name").concat(" ").concat(request.getParameter("surname"));
		System.out.println(gender == "any");
		if (gender.equals("any")) gender = "";
		
		UserDAO udao = new UserDAOImpl();
		List<User> users =  udao.findUsersAdvanced(username, name, age, gender);
		request.setAttribute("results", users);
		request.getRequestDispatcher("/results.jsp").forward(request, response);
	}

}
