package servlets;

import java.io.IOException;
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
@WebServlet("/BanUserServlet")
public class BanUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String banned = request.getParameter("banned"); 	 
			if (banned == null){
				doGet(request, response);
			}else{
				UserDAOImpl udi = new UserDAOImpl();
				boolean success = udi.ban(Integer.parseInt(banned));
				// If user not created display some sort of error message
				response.sendRedirect("Profile");
//				dispatcher.forward(request, response);
			} 
	}

}
