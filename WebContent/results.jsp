<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="datastructures.*, java.util.*"%>
<% 
	List<User> foundUsers = (List<User>) request.getAttribute("results");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | Results</title>
	    <%@include file="links.html" %>
	</head>
	<body>
		<jsp:include page="navbar.jsp"/>
		<h1>Results</h1><br>
		<% for(User u : foundUsers) { %>
		<form method="post" action="">
			<h3><%= u.getUsername() + " (" + u.getName() + ")" %></h3> <button>Add Friend</button>
			<input type="hidden" value="<%= u.getId() %>" name="friend">
		</form>
		<% } %>
	</body>
</html>