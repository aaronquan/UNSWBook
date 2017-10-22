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
				<form action="GraphServlet" method="POST">
					<div class="row">
						<div class="col-md-2 col-md-offset-8">
							<div class="input-group">
							  <input type="text" class="form-control" id="graphSearch" name="graphSearch" placeholder="Search graph nodes...">
							</div>
						</div>
						<div class="col-md-2">
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</div>	
				</form>				
				
				<div id="mynetwork" style="height: 500px; border: 1px solid lightgray;"></div>				
				<br>
				<button onclick="goBack()" class="btn btn-danger">Go Back</button>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		var nodes = <%=jNodes%>;
		var edges = <%=jEdges%>;

		// create nodes dataset
		var graphNodes = new vis.DataSet([]);
		nodes.forEach(function(n) {
			graphNodes.add([{id: n.id.trim(), label: n.title.trim(), shape: n.shape.trim()}]);
		});
		
		// create edges dataset
		var graphEdges = new vis.DataSet([]);
		edges.forEach(function(e) {
			if (e.id.trim() === 'E1') {
				graphEdges.add([{label: e.title.trim(), from: e.from.trim(), to: e.to.trim(), font: {align: 'middle'}}]);
			} else {
				graphEdges.add([{label: e.title.trim(), from: e.from.trim(), to: e.to.trim(), font: {align: 'middle'}, arrows: 'to'}]);
			}
		});
		
		// create a network
		var container = document.getElementById('mynetwork');
		var data = {
			nodes: graphNodes,
			edges: graphEdges
		};
		var options = {};
		var network = new vis.Network(container, data, options);
	</script>
</html>