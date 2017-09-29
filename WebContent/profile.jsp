<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | ${user.getName()}</title>
	    <%@include file="links.html" %>
	</head>

	<body>
		<jsp:include page="navbar.jsp"/>
		<h1>${user.getName()} PROFILE PAGE</h1>
		email: ${user.getEmailAddress()}
	</body>
</html>