package my.remind.board3.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.remind.board3.dao.comment.CommentDAO;
import my.remind.board3.dao.content.ContentDAO;
import my.remind.board3.vo.CommentVO;
import my.remind.board3.vo.ContentVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private ContentDAO dao;
	
	@Inject
	private CommentDAO commentDAO;
	
	private int page = 1;
	private int viewCount = 10;
	
	private final String REDIRECT_PAGE = "redirect:board/list";
	private final String VIEW_APP_PAGE = "board/boardApp";
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getBoardList(Model model, HttpServletRequest request) {
		
		if( request.getParameter("page") != null ) {
			this.page = Integer.valueOf(request.getParameter("page"));
		}

		ArrayList<ContentVO> contents = dao.getContents(this.page,  this.viewCount);
		int firstContentId = contents.get(0).getBoardNo();
		int lastContentId = contents.get(contents.size()-1).getBoardNo();

		model.addAttribute("mode", "list");
		model.addAttribute("contents", contents);
		model.addAttribute("nextPages", dao.getNextContents(lastContentId, this.viewCount));
		model.addAttribute("prevPages", dao.getPrevContents(firstContentId, this.viewCount));
		
		return VIEW_APP_PAGE;
	}
	
	// 여기서 댓글 관련된 최초 데이터를 먼저 보내주면, 다음에 생성하기가 수월하지
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String getBoardContent(Model model, HttpServletRequest request) {
		if( request.getParameter("id") == null ) {
			return REDIRECT_PAGE+"?page="+this.page;
		}

		int contentId = Integer.valueOf(request.getParameter("id"));
		
		// Check page
		if( request.getParameter("page") != null ) {
			this.page = Integer.valueOf(request.getParameter("page"));
		}
		
		// Content Detail
		model.addAttribute("mode", "view");
		model.addAttribute("content", dao.getContent(contentId));

		// Comments
		int commentCount = 3;
		ArrayList<CommentVO> comments = commentDAO.getComments(contentId, 1, commentCount);
		int firstCommentId = 0;
		int lastCommentId = 0;
		if( comments.size() > 0 ) {
			firstCommentId = comments.get(0).getNo();
			lastCommentId = comments.get( comments.size() - 1 ).getNo();
		}
		model.addAttribute("nextPages", commentDAO.getNextComments(contentId, lastCommentId, commentCount));
		model.addAttribute("prevPages", commentDAO.getPrevComments(contentId, firstCommentId, commentCount));
		
		return VIEW_APP_PAGE;
	}
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String getBoardUpdate(Model model, HttpServletRequest request) {
		if( request.getParameter("id") == null ) {
			return REDIRECT_PAGE+"?page="+this.page;
		}
		int contentId = Integer.valueOf(request.getParameter("id"));

		model.addAttribute("mode","update");
		model.addAttribute("content", dao.getContent(contentId));
		
		return VIEW_APP_PAGE;
	}

	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String getBoardWrite(Model model, HttpServletRequest request) {
		
		model.addAttribute("mode","write");
		
		return VIEW_APP_PAGE;
	}
	
}
