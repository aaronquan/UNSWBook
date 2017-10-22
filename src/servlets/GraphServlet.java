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
import datastructures.Graph;
import datastructures.GraphDAOImpl;
import datastructures.User;
import datastructures.UserDAO;
import datastructures.UserDAOImpl;

/**
 * Servlet implementation class GraphServlet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphServlet() {
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
			UserDAO usd = new UserDAOImpl();
			User u = usd.lookupId(userId);
			request.setAttribute("user", u);
			request.setAttribute("uid", (String) Integer.toString(userId));
			request.setAttribute("name", (String) u.getName());
			GraphDAOImpl gi = new GraphDAOImpl();
			List<Graph> nodes = gi.getNodeList();
			List<Graph> newNodes = new ArrayList<Graph>();
			List<Graph> edges = gi.getConnectionList();
			
			String graphSearch = (String) request.getParameter("graphSearch");
			if (graphSearch == null || graphSearch.equals("")) {
				request.setAttribute("nodes", nodes);
				request.setAttribute("edges", edges);
				System.out.println("HELLLLLLLLLL");
			} else {
				System.out.println("asiodjfoaisjdfoaisdj");
				for (Graph n: nodes) {
					if (n.getTitle().matches(".*" + graphSearch + ".*")) {
						newNodes.add(n);
					}
				}
				request.setAttribute("nodes", newNodes);
				request.setAttribute("edges", edges);
			}
			
			

			request.getRequestDispatcher("graph.jsp").forward(request, response);
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
