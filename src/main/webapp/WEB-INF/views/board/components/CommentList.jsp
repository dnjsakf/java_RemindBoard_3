<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="my.remind.board3.vo.CommentVO" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%!
	String ctxPath;
	DateFormat df = DateFormat.getDateInstance();
	
	ArrayList<CommentVO> comments = null;
	CommentVO comment = null;
	
	int contentId = -1;
	
	// Comments Page
	int activePage = 1;
	int nextPages = 0;
	int prevPages = 0;
%>
<%
	ctxPath = request.getContextPath();
	if( request.getParameter("id") != null ){
		contentId = Integer.valueOf(request.getParameter("id"));
	}

	if( request.getAttribute("comments") != null ){
		comments = (ArrayList) request.getAttribute("comments");
	}
	if( request.getAttribute("nextPages") != null ){
		nextPages = (Integer) request.getAttribute("nextPages");
	}
	if( request.getAttribute("prevPages") != null ){
		prevPages = (Integer) request.getAttribute("prevPages");
	}
	System.out.println("[comment first]" + ( comments == null  ? null : comments.get(0) ) );
	System.out.println("[comment last ]" + ( comments == null  ? null : comments.get( comments.size() -1 ) ) );
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Board > content </title>
	<link rel="stylesheet" type="text/css" href="<%= ctxPath %>/resources/css/board.css"/>
	
	<style>
		ul.pagination a.active {
			background-color: rgba(255, 0, 255, 0.4);
		}
	</style>
</head>
<body>
	<!-- Comment -->
	<div id="comment-containner">
		<!-- ajax data -->
		<div id="comment-list"></div>
		<!-- 댓글 Pagination -->
		<div id="comment-pagination">
			<ul id="pagination" class="pagination">
				
			</ul>
		</div>
			
		<!-- 댓글작성: AJAX로 처리하자 -->
		<div id="comment-write" class="comment-item">
			<div class="row">
				<div class="input-field col s4">
					<input id="comment-input-writer" type="text" class="comment-input writer" name="writer">
					<label for="comment-input-writer">작성자</label>
				</div>
				<div class="input-field col s4">
					<input id="comment-input-password" type="password" class="comment-input password" name="password">
					<label for="comment-input-password">비밀번호</label>
				</div>
				<div class="input-field col s4">
					<input id="comment-input-date" type="text" class="comment-input date" name="date" value="<%= df.format(new Date()) %>" readonly>
					<label for="comment-input-date" class="active">작성일</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<textarea id="comment-input-comment" class="comment-input comment materialize-textarea" name="comment"></textarea>
					<label for="comment-input-comment">내용</label>
				</div>
			</div>
			<div class="row" style="text-align: right; padding-right: 30px;">
				<a href="#" onClick="saveComment()">저장</a>
			</div>
		</div>
	</div>
	<script src="<%=ctxPath%>/resources/js/comment/CommentController.js"></script>
</body>
</html>