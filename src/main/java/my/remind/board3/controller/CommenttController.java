package my.remind.board3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.remind.board3.dao.comment.CommentDAO;
import my.remind.board3.vo.CommentVO;

@Controller
@RequestMapping("/board/comment/*")
public class CommenttController {
	
	@Inject
	private CommentDAO dao;
	
	private int page = 1;
	private int viewCount = 3;
	
	// AJAX
	/**
	 * Page parameter를 받아와서, 해당 페이지 내에 있는 댓글 목록을 가져와서 보내줌
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCommentList( HttpServletRequest request ){
		if( request.getParameter("id") == null ) {
			return null;
		}
		if( request.getParameter("page") != null ) {
			this.page = Integer.valueOf(request.getParameter("page"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int contentId = Integer.valueOf(request.getParameter("id"));
		
		ArrayList<CommentVO> comments = dao.getComments(contentId, this.page, this.viewCount);
		int firstCommentId = 0;
		int lastCommentId = 0;
		if( comments.size() > 0 ) {
			firstCommentId = comments.get(0).getNo();
			lastCommentId = comments.get( comments.size() - 1 ).getNo();
		}
		
		map.put("comments", comments);
		map.put("activePage", this.page);
		map.put("nextPages", dao.getNextComments(contentId, lastCommentId, this.viewCount));
		map.put("prevPages", dao.getPrevComments(contentId, firstCommentId, this.viewCount));
		
		return map;
	}
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertComment(@RequestBody Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if( params.get("writer") == null ||
			params.get("date") == null ||
			params.get("password") == null ||
			params.get("comment") == null ||
			params.get("boardNo") == null) {
			map.put("result", "parameter is null");
			return map;
		}
		CommentVO comment = new CommentVO(
									(String) params.get("writer"),
									(String) params.get("date"),
									(String) params.get("password"),
									(String) params.get("comment"),
									Integer.parseInt( (String)params.get("boardNo") )
									);
		map.put("result", dao.insertComment(comment));
		return map;
	}
	
}