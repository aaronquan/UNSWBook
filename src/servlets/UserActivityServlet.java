package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datastructures.UserActivity;
import datastructures.UserActivityDAOImpl;

/**
 * Servlet implementation class UserActivityServlet
 */
@WebServlet("/UserActivityServlet")
public class UserActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("user"));
		int userId = Integer.parseInt(request.getParameter("user"));
		UserActivityDAOImpl uai = new UserActivityDAOImpl();
		List<UserActivity> ua = uai.getUserActivity(userId);
		request.setAttribute("userActivity", ua);
		request.getRequestDispatcher("user_activity.jsp").forward(request, response);
	}

}
