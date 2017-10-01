package servlets;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastructures.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/friendReqServlet")
public class friendReqServlet extends HttpServlet {
	public static String USERNAME = "unswbookconfirmation@gmail.com";
	public static String PASSWORD = "jhgf1234";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public friendReqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		if (from == null || to == null || from.equals("") || to.equals("")){
			request.getRequestDispatcher("friendReqServlet").forward(request, response);
		}else{
		   UserDAOImpl ud = new UserDAOImpl();
		   Integer toId = Integer.parseInt(to);
           User toUser = ud.lookupId(toId);
           ud.createFriendReq(Integer.parseInt(from), toId);
		   Email(toUser.getEmailAddress(), from, to );
		   response.sendRedirect("Login");	   
		}
	}

	
	public static void Email(String email, String from, String to){
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME,PASSWORD);
					}
				});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from@no-spam.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
				message.setSubject("Friend Request!");
				message.setText("Hello" +
						"\n\n Click to get a new Friend!" +
						"\n\n http://localhost:8080/UNSWBook/confirm_friend.jsp?from=" + from + "&to=" + to);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}
}
