<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		</div>
	</body>
</html>