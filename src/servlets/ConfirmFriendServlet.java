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
@WebServlet("/ConfirmFriendServlet")
public class ConfirmFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("confirm_friend.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String from = request.getParameter("from"); 	 
		 String to = request.getParameter("to"); 		 
			if (from == null || to == null ){
				doGet(request, response);
			}else{
				UserDAOImpl udi = new UserDAOImpl();
				boolean created = udi.confirmFriendReq(Integer.parseInt(from), Integer.parseInt(to));
				// If user not created display some sort of error message
				response.sendRedirect("Login");
//				dispatcher.forward(request, response);
			} 
	}

}
