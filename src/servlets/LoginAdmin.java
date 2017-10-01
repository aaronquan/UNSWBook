package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datastructures.*;

/**
 * Servlet implementation class LoginAdmin
 */
@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String loginMessage = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = (User) request.getSession().getAttribute("user");
		DatabaseConnection dbc = (DatabaseConnection) request.getSession().getAttribute("dbc");
		if(dbc == null) {
			String dbURL = "jdbc:derby://localhost:1527/UNSWDatabase;create=true;user=user;password=user";
			dbc = new DatabaseConnection(dbURL);
			dbc.createConnection();
			request.getSession().setAttribute("dbc", dbc);
		}
		request.setAttribute("loginMessage", this.loginMessage);
		request.setAttribute("registrationMessage", "");
		this.loginMessage = "";
		request.getRequestDispatcher("loginadmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String pwd  = request.getParameter("password");
		
		if (user == null || pwd == null || user.equals("") || pwd.equals("")) {
			this.loginMessage = "Please enter your username and password";
			doGet(request, response);
		}else{
			UserDAO usd = new UserDAOImpl();
			Integer id = usd.validateAdmin(user, pwd);
			if (id == null){
				this.loginMessage = "Invalid username or password";
				doGet(request, response);
			}else if(id == -1){
				this.loginMessage = "You have been BANNED. >=[";
				doGet(request, response);
			}else{
				this.loginMessage = "";
				HttpSession login = request.getSession(true);
				login.setAttribute("user", id);
				Integer expiry = 15 * 60; 
				login.setMaxInactiveInterval(expiry);
				response.sendRedirect("Profile");
			}
		}
	}

}
