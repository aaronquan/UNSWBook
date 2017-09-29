<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | Home</title>
	    <%@include file="links.html" %>
	</head>
	<body>
		<jsp:include page="navbar.jsp">
			<jsp:param name="user" value="${user.getName()}"/>
		</jsp:include>
		Hello ${sessionScope.user}
		Hello ${user.getName()}
		<p>This should contain a collection of posts ordered by latest timestamp of all your friends</p>
	</body>
<body>

</body>
</html>