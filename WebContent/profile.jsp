<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="datastructures.*, java.util.*"%>
<% 
	List<WallPost> allPosts = (List<WallPost>) request.getAttribute("allPosts");
	String name = (String) request.getAttribute("name");
	String uid = (String) request.getAttribute("uid");
	String pid = (String) request.getAttribute("pid");
	String isFriend = (String) request.getAttribute("isFriend");
	Boolean isAdmin = (Boolean) request.getAttribute("admin");
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
			<div class="container">
				<h1>${profileUser.getName()} (${profileUser.getUsername()})</h1>
				<h4>Email: ${profileUser.getEmailAddress()}</h4>
				<h4>Age: ${profileUser.getAge()}</h4>
				<h4>Gender: ${profileUser.getGender()}</h4>
				<% if (! uid.equals(pid) && (isFriend.equals("false")))  {%>
					<form action="friendReqServlet" method="POST">
						<button type="submit" id="add_friend" class="btn btn-primary">Add friend</button>
						<input type="hidden" name="from" value="${sessionScope.user}">
						<input type="hidden" name="to" value="${pid}">
					</form>
				<% } %> 
			</div>
			<br>

			<% if (uid.equals(pid))  {%>
				<jsp:include page="createPost.jsp"/><br>
			<% } %>
			
			<% if (isAdmin) { %>
			    <form name="confirmForm" method="post" action="BanUserServlet?banned=<%=pid %>">
                <input type="submit" value="Ban" />
                 </form>
			<% } %>

			<% if (allPosts != null) {
				for (WallPost p : allPosts) { %>
				<div class="container">
					<div class="col-md-12">
				        <div class="panel panel-default">
				            <div class="panel-body">
				               <section class="post-heading">
				                    <div class="row">
				                        <div class="col-md-12">
				                            <div class="media">
				                              <div class="media-body">
				                                <form action="Profile" id="<%=p.getIdOfAuthor()%>_author" method="POST">
				                                	<a href="javascript:{}" onclick="document.getElementById('<%=p.getIdOfAuthor()%>_author').submit();" class="anchor-username"><h4 class="media-heading"><%=p.getAuthor()%></h4></a>
				                                	 <input type="hidden" value="<%=p.getIdOfAuthor()%>" name="user">
												</form>
				                                <p class="anchor-time"><%=p.getDate()%></p>
				                              </div>
				                            </div>
				                        </div>
				                    </div>             
				               </section>
				               <br>
				               <section class="post-body">
				                   <p><%=p.getContent()%></p>
				               </section>
				               <section class="post-footer">
				                   <hr>
				                   <% if (p.getLikedBy().contains(name)) { %>
				                	   <form action="UnlikePostServlet" method="POST" id="<%=p.getId()%>_post">
						                   <div class="post-footer-option container">
						                        <ul class="list-unstyled">
						                            <li><a href="javascript:{}" onclick="document.getElementById('<%=p.getId()%>_post').submit();"><i class="glyphicon glyphicon-thumbs-up"></i>Unlike (<%=p.getLikedBy().size()%>)</a></li>
						                        </ul>
						                   </div>
						                   <input type="hidden" name="postId" value="<%=p.getId()%>">
						                   <input type="hidden" name="userId" value="${sessionScope.user}">
					                   </form>
				                   <% } else { %>
					                   <form action="LikePostServlet" method="POST" id="<%=p.getId()%>_post">
						                   <div class="post-footer-option container">
						                        <ul class="list-unstyled">
						                            <li><a href="javascript:{}" onclick="document.getElementById('<%=p.getId()%>_post').submit();"><i class="glyphicon glyphicon-thumbs-up"></i>Like (<%=p.getLikedBy().size()%>)</a></li>
						                        </ul>
						                   </div>
						                   <input type="hidden" name="postId" value="<%=p.getId()%>">
						                   <input type="hidden" name="userId" value="${sessionScope.user}">
					                   </form>
				                   <% } %>
				               </section>
				            </div>
				        </div>   
					</div>
				</div>		
				<% }
			} %>
		</div>
	</body>
</html>