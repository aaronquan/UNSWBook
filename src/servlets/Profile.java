package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.DatabaseConnection;
import datastructures.Post;
import datastructures.PostDAO;
import datastructures.PostDAOImpl;
import datastructures.WallPost;
import datastructures.User;
import datastructures.UserDAO;
import datastructures.UserDAOImpl;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		if (session == null || session.getAttribute("user") != null){
			Integer userId = (Integer) session.getAttribute("user");
			String profileUser = request.getParameter("user");
			PostDAO pdao = new PostDAOImpl();
			List<WallPost> allPosts;
			UserDAO usd = new UserDAOImpl();
			User u = usd.lookupId(userId);
			request.setAttribute("user", u);
			if (profileUser != null && profileUser.matches("[0-9]+")) {
				UserDAO pUsd = new UserDAOImpl();
				User pu = pUsd.lookupId(Integer.parseInt(profileUser));
				request.setAttribute("profileUser", pu);
				allPosts =  pdao.getWall(Integer.parseInt(profileUser));
			} else {
				request.setAttribute("profileUser", u);
				allPosts =  pdao.getWall(userId);
			}
			
			// TO REMOVE vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
			if (allPosts != null) {
				for (WallPost p : allPosts) {
					System.out.println(p.getContent());
				}
			}
			// TO REMOVE ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			
			request.setAttribute("allPosts", allPosts);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}else{
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
