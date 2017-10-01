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
			<h1>${profileUser.getName()} (${profileUser.getUsername()})</h1>
			<br><jsp:include page="createPost.jsp"/><br>
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
				                                <form action="Profile" id="<%=p.getIdOfAuthor()%>_result" method="POST">
				                                	<a href="javascript:{}" onclick="document.getElementById('<%=p.getIdOfAuthor()%>_result').submit();" class="anchor-username"><h4 class="media-heading"><%=p.getAuthor()%></h4></a>
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
				                   <div class="post-footer-option container">
				                        <ul class="list-unstyled">
				                            <li><a href="LikePostServlet"><i class="glyphicon glyphicon-thumbs-up"></i>Like (<%=p.getLikedBy().size()%>)</a></li>
				                        </ul>
				                   </div>
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