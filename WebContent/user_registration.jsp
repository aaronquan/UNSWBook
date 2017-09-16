<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/UserRegistration" method="post">
	  <p>UserName:        
      	<input type="text" name="name" />
      </p>
      
      <p>Password:        
      	<input type="text" name="password" />
      </p>
      
      <p>Email:        
      	<input type="text" name="email" />
      </p>
      
	<input type="submit" name="submit" value="submit" />
	</form>
</body>
</html>