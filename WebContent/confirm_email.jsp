<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Email</title>
</head>
<body>

<%

String username = request.getParameter("username");
System.out.println(username);
String firstName = request.getParameter("firstname");
System.out.println(firstName);
String surname= request.getParameter("surname");
System.out.println(surname);
String email= request.getParameter("email");
System.out.println(email);
String password= request.getParameter("password");
System.out.println(password);

//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
//dispatcher.forward(request, response);
%>

<form name="confirmForm" method="post" action="ConfirmEmailServlet?username=<%=username %>&firstname=<%=firstName%>&surname=<%=surname%>&email=<%=email%>&password=<%=password%>">
     <input type="submit" value="Confirm Email" />
</form>
</body>
</html>