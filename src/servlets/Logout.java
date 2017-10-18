package servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.UserActivity;
import datastructures.UserActivityDAOImpl;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public Logout() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");  
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user");
        UserActivityDAOImpl uai = new UserActivityDAOImpl();
		UserActivity ua = UserActivity.createActivity(userId, "Logged out", new Timestamp(System.currentTimeMillis()));
		uai.addUserActivity(ua);
        session.invalidate();
        response.sendRedirect("Login");
	}
}