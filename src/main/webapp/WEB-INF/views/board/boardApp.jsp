<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	String ctxPath = request.getContextPath();
	String mode = "INIT";
	
	if( request.getAttribute("mode") != null) {
		mode = (String) request.getAttribute("mode");
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>게시판</title>
	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	<!--  Materialize-Icon -->          
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
	<!-- JQuery Script -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<header class="board-header">
		<h4>Board</h4>
	</header>
	<section class="board-section">
		<% if(mode.equals("list")) { %>
			<jsp:include page="components/ContentList.jsp"/>
		<% } else if(mode.equals("view")) { %>
			<jsp:include page="components/ContentView.jsp"/>
		<% } else if(mode.equals("write")) { %>
			<jsp:include page="components/ContentWrite.jsp"/>
		<% } else if(mode.equals("update")) { %>
			<jsp:include page="components/ContentUpdate.jsp"/>
		<% } else { %>
			<h1>ERROR PAGE</h1>
		<% } %>
	</section>
	<footer class="board-footer">
		<h4>footer</h4>
	</footer>
	
	<!-- Compiled and minified JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
</body>
</html>