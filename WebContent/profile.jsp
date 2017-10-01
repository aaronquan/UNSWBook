<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="datastructures.*, java.util.*"%>
<% 
	List<WallPost> allPosts = (List<WallPost>) request.getAttribute("allPosts");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | ${profileUser.getName()}</title>
	    <%@include file="links.html" %>
	</head>

	<body>
		<jsp:include page="navbar.jsp"/>
		<div class="container-fluid">
			<h1>${profileUser.getName()}</h1>
			Show user details and a list of posts sorted by date
			<br><jsp:include page="createPost.jsp"/><br>
			<% if (allPosts != null) {
				for (WallPost p : allPosts) { %>
				<p>Author: <%=p.getAuthor()%></p>
				<p>Date: <%=p.getDate()%></p>
				<p>Content: <%=p.getContent()%></p>
				<p>Number of likes: <%=p.getLikedBy().size()%></p>
				<% }
			} %>
			<br><jsp:include page="post.jsp"/>
		</div>
	</body>
</html>