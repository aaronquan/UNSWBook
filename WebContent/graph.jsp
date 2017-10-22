<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="datastructures.*, java.util.*, com.google.gson.Gson"%>
<% 
	List<Graph> nodes = (List<Graph>)request.getAttribute("nodes");
	List<Graph> edges = (List<Graph>)request.getAttribute("edges");
	String jNodes = new Gson().toJson(nodes);
	String jEdges = new Gson().toJson(edges);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>UNSWBook | Graph</title>
	    <%@include file="links.html" %>
	</head>
	<body>
		<jsp:include page="navbar.jsp"/>
		<div class="container-fluid">
			<div class="container">
				<h1>UNSWBOOK Usage Graph</h1>
				<br>
				<div id="mynetwork" style="height: 500px; border: 1px solid lightgray;"></div>				
				<br>
				<button onclick="goBack()" class="btn btn-danger">Go Back</button>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		console.log("gelloooooo");
		
		// create an array with nodes
		var graphNodes = new vis.DataSet([
			{id: 1, label: 'jsamir'},
			{id: 2, label: 'Who likes hamish and andy', shape: 'box'},
			{id: 3, label: 'Hi Im Jerome, Im new!', shape: 'box'},
			{id: 4, label: 'timc'},
		]);
		
		// create an array with edges
		var edges = [
		    {from: 1, to: 2, label: 'Liked', font: {align: 'middle'}, arrows:'to'},
		    {from: 1, to: 3, label: 'Posted', font: {align: 'middle'}, arrows:'to'},
		    {from: 4, to: 2, label: 'Posted', font: {align: 'middle'}, arrows:'to'},
		    {from: 1, to: 4, label: 'FriendsWith', font: {align: 'middle'}},
		]
		
		// create a network
		var container = document.getElementById('mynetwork');
		var data = {
			nodes: graphNodes,
			edges: edges
		};
		var options = {};
		var network = new vis.Network(container, data, options);
	</script>
</html>