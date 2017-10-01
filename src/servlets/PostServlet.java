package servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.DatabaseConnection;
import datastructures.Post;
import datastructures.PostDAOImpl;
import datastructures.User;
import datastructures.UserDAO;
import datastructures.UserDAOImpl;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
		if (session == null || session.getAttribute("user") != null) {
			Post newPost;
			Integer userId = (Integer) session.getAttribute("user");
			UserDAO usd = new UserDAOImpl();
			User u = usd.lookupId(userId); 
			request.setAttribute("user", u);
			String profileUser = request.getParameter("user");
			String content = request.getParameter("postContent");

			request.setAttribute("profileUser", u);
			newPost = Post.createTextPost(content, userId, userId, new Timestamp(System.currentTimeMillis()));
			System.out.println("posting to self!: " + content + " " + userId + " " + userId);
			
			if (content != null && (! content.equals(""))) {
				PostDAOImpl pdi = new PostDAOImpl();
				pdi.createTextPost(newPost);
			}
			
			response.sendRedirect("Profile");
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
