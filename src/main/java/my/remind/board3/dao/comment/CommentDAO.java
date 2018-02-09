package my.remind.board3.dao.comment;

import java.util.ArrayList;

import my.remind.board3.vo.CommentVO;

public interface CommentDAO {

	/** Getters **/
	public ArrayList<CommentVO> getComments(int contentId, int page, int count);
	public int getNextComments(int contentId, int lastCommentId, int count) ;
	public int getPrevComments(int contentId, int firstCommentId, int count) ;
	
	/** Setters **/
	public int insertComment(CommentVO comment) ;
	public int updateComment(CommentVO comment) ;
	public int deleteComemnt(int commentId) ;
}
