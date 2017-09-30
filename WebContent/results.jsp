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
			<% if (foundUsers.size() < 1) { %>
				<h3>No users found.</h3>
			<% } %>
			<div class="list-group" style="border: 0 none;">
				<% for(User u : foundUsers) { %>
					<form action="Profile" id="<%=u.getId()%>_result" method="POST">
						<a href="javascript:{}" onclick="document.getElementById('<%=u.getId()%>_result').submit();" class="list-group-item"><b><%= u.getName()%></b><%=" (" + u.getUsername() + ")" %></a>
						<input type="hidden" value="<%=u.getId()%>" name="user">
					</form>
				<% } %>
			</div>
		</div>
	</body>
</html>