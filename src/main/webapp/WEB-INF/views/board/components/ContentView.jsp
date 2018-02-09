<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="my.remind.board3.vo.ContentVO" %>
<%!
	String ctxPath;
	ContentVO content = null;
%>
<%
	ctxPath = request.getContextPath();
	if( request.getAttribute("content") != null ){
		content = (ContentVO) request.getAttribute("content");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Board > content </title>
</head>
<body>
	<!-- Content-Detail -->
	<div id="content-detail" class="row">
	<% if( content != null ) { %>
		<div class="row">
			<div class="input-field col s1">
				<input id="content-no" type="text" value="<%= content.getBoardNo() %>" readonly>
				<label for="content-no" class="active">글번호</label>
			</div>
			<div class="input-field col s11">
				<input id="content-title" type="text" value="<%= content.getBoardTitle() %>" readonly>
				<label for="content-title" class="active">제목</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<input id="content-writer" type="text" value="<%= content.getBoardWriter() %>" readonly>
				<label for="content-writer" class="active">작성자</label>
			</div>
			<div class="input-field col s6">
				<input id="content-date" type="text" value="<%= content.getBoardDate() %>" readonly>
				<label for="content-date" class="active">작성일</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<textarea id="content-date" class="materialize-textarea" readonly><%= content.getBoardContent() %></textarea>
				<label for="content-date" class="active">내용</label>
			</div>
		</div>
	<% } %>
	</div>
	<!-- Comment -->
	<jsp:include page="CommentList.jsp"/>
</body>
</html>