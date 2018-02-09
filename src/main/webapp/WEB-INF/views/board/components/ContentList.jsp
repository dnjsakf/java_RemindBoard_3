<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="my.remind.board3.vo.ContentVO" %>
<%
	String ctxPath = "";

	ContentVO content = null;
	ArrayList<ContentVO> contents = null;
	
	int nextPages = 0;
	int prevPages = 0;
	int activePage = 1;

	ctxPath = request.getContextPath();
	
	if( request.getParameter("page") != null ){
		activePage = Integer.valueOf(request.getParameter("page"));
	}
	
	if( request.getAttribute("contents") != null ){
		contents = (ArrayList<ContentVO>) request.getAttribute("contents");
	}
	
	if( request.getAttribute("nextPages") != null ){
		nextPages = (Integer) request.getAttribute("nextPages");
	}
	
	if( request.getAttribute("prevPages") != null ){
		prevPages = (Integer) request.getAttribute("prevPages");
	}
	
	if( prevPages > 2 ){ prevPages = 2; }
	if( nextPages > 2 ){ nextPages = 2; }
	
	System.out.println("[variable]"+nextPages+"/"+prevPages);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
	.active {
		background-color: rgba(255, 0, 255, 0.4);
	}

</style>
</head>
<body>
	<% if( contents != null ) {	%>
	<table class="boared">
		<thead>
			<tr>
				<td class="content-no">no</td>
				<td class="content-title">title</td>
				<td class="content-writer">writer</td>
				<td class="content-date">date</td>
			</tr>
		</thead>
		<tbody>
		<% for(int i = 0; i < contents.size(); i++){ %>
			<% content = contents.get(i); %>
			<tr class="cotnent-item" onClick="javascript:location.href='content?page=<%= activePage %>&id=<%= content.getBoardNo()  %>'">
				<td class="content-no"><%= content.getBoardNo() %></td>
				<td class="content-title"><%= content.getBoardTitle() %></td>
				<td class="content-writer"><%= content.getBoardWriter() %></td>
				<td class="content-date"><%= content.getBoardDate() %></td>
			</tr>
		<% } %>
		</tbody>
	</table>
	<ul class="pagination">
		<!-- 이전 페이지 -->
		<% if( activePage > 1 ){ %>
		<li>
			<a id="page-prev-btn" href="?page=<%= activePage-1 %>">
				<i class="material-icons">chevron_left</i>
			</a>
		</li>
		<% } %>
		<!-- Active Page를 중심으로 왼쪽 페이지 : 최대 5개? -->
		<% for(int i = (activePage - prevPages); i < activePage; i++ ) { %>
		<li><a class="page-number-btn" page="<%= i %>" href="?page=<%=i%>"><%= i %></a></li>
		<% } %>
		<!-- Active Page -->
		<li><a class="page-number-btn active" href="?page=<%=activePage%>"><%= activePage %></a></li>
		<!-- Active Page를 중심으로 오른쪽 페이지 : 최대 5개 ?-->
		<% for(int i = (activePage + 1); i <= (activePage+nextPages); i++ ) { %>
		<li><a class="page-number-btn" page="<%= i %>" href="?page=<%=i%>"><%= i %></a></li>
		<% } %>
		<!-- 다음 페이지 -->
		<% if( activePage < (activePage + nextPages)) {%>
		<li>
			<a id="page-next-btn" href="?page=<%= activePage+1 %>">
				<i class="material-icons">chevron_right</i>
			</a>
		</li>
		<% } %>
	</ul>
	<% } else { out.print("No Content DATA"); } %>
</body>
</html>