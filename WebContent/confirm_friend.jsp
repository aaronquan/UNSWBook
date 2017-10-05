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

String from = request.getParameter("from");
String to = request.getParameter("to");


//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
//dispatcher.forward(request, response);
%>

<form name="confirmForm" method="post" action="ConfirmFriendServlet?from=<%=from %>&to=<%=to%>">
     <input type="submit" value="Confirm Friend" />
</form>
</body>
</html>