<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="datastructures.*, java.util.*"%>
<% 
	List<UserActivity> activity = (List<UserActivity>)request.getAttribute("userActivity");
%> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | User activity</title>
	    <%@include file="links.html" %>
	</head>
	<body>
		<jsp:include page="navbar.jsp"/>
		<div class="container-fluid">
			<div class="container">
				<table data-toggle="table" data-search="true" data-pagination="true">
					<thead style="background-color: #d9d9d9;">
						<tr>
							<th data-field="activity" data-sortable="true">User activity</th>
							<th data-field="timestamp" data-sortable="true">Timestamp</th>
						</tr>
					</thead>
					<tbody style="background-color: #f2f2f2;">
						<% for (UserActivity ua: activity) {%>
							<tr><td><%= ua.getReport()%></td><td><%=ua.getTime()%></td></tr>
						<%} %>
					</tbody>
				</table>
				<br>
				<button onclick="goBack()" class="btn btn-danger">Go Back</button>
			</div>
		</div>
	</body>
</html>