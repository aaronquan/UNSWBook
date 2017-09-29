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
		<div class="container-fluid">
			<h1>Results</h1>
			<br>		  
			<div class="list-group">
				<% for(User u : foundUsers) { %>
					<a href="Profile?method=POST&user=<%=u.getId()%>" class="list-group-item"><b><%= u.getName()%></b><%=" (" + u.getUsername() + ")" %></a>
				<% } %>
			</div>
		</div>
	</body>
</html>