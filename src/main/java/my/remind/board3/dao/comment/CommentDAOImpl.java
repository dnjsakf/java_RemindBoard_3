package my.remind.board3.dao.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import my.remind.board3.vo.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "my.remind.board3.mapper.CommentMapper.";

	@Override
	public ArrayList<CommentVO> getComments(int contentId, int page, int count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("contentId", contentId);
		map.put("start", (page-1)*count);
		map.put("count", count);
		return (ArrayList) sqlSession.selectList(NAMESPACE+"getComments",map);
	}

	@Override
	public int getNextComments(int contentId, int lastCommentId, int count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("contentId", contentId);
		map.put("lastCommentId", lastCommentId);
		int nextComments = sqlSession.selectOne(NAMESPACE+"getNextComments", map );
		
		System.out.println("[next]" + nextComments);
		return pages( nextComments, count );
	}

	@Override
	public int getPrevComments(int contentId, int firstCommentId, int count) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("contentId", contentId);
		map.put("firstCommentId", firstCommentId);
		int prevComments = sqlSession.selectOne(NAMESPACE+"getPrevComments", map );

		System.out.println("[prev]" + prevComments);
		return pages( prevComments, count );
	}

	@Override
	public int insertComment(CommentVO comment) {
		return sqlSession.insert(NAMESPACE+"insertComment", comment);
	}

	@Override
	public int updateComment(CommentVO comment) {
		return sqlSession.update(NAMESPACE+"updateComment", comment);
	}

	@Override
	public int deleteComemnt(int commentId) {
		return sqlSession.delete(NAMESPACE+"deleteComment", commentId);
	}

	
	// Service
	public int pages(int comments, int count) {
		int pages = Integer.valueOf(comments / count);
		if( comments % count != 0 ) { pages += 1; }
		return pages;
	}
}
