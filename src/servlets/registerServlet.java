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
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	public static String USERNAME = "unswbookconfirmation@gmail.com";
	public static String PASSWORD = "jhgf1234";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstname = request.getParameter("first_name");
		String surname = request.getParameter("surname");
		String userPassword = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		if (username == null || password == null || email == null || firstname == null || surname == null || confirm_password == null 
				|| username.equals("") || password.equals("") || email.equals("") || firstname.equals("") || surname.equals("") || confirm_password.equals("")){
				request.getRequestDispatcher("registerServlet").forward(request, response); // FIXME: implement proper error handling for missing fields
			} else if (! confirm_password.equals(password)) {
				request.getRequestDispatcher("registerServlet").forward(request, response);
			}else{
				Email(email, firstname, surname, username, userPassword);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login");
				dispatcher.forward(request, response);
			}
		/*
		 */
		
	}

	private Predicate<? super String> isNull() {
		return null;
	}
	
	public static void Email(String email, String firstName, String surname, String username, String password){
			
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
				message.setSubject("Account Confirmation");
				message.setText("Dear " + firstName + " " + surname + "," +
						"\n\n Click to confirm your email!" +
						"\n\n http://localhost:8080/UNSWBook/confirm_email.jsp?username=" + username + "&firstname=" + firstName + "&surname=" + surname + "&email=" + email + "&password=" + password);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	}

}
