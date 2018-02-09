package my.remind.board3.dao.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import my.remind.board3.vo.ContentVO;

@Repository
public class ContentDAOImpl implements ContentDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "my.remind.board3.mapper.ContentMapper.";
	
	@Override
	public ArrayList<ContentVO> getContents(int page, int count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1)*count);
		map.put("count", count);
		return (ArrayList) sqlSession.selectList(NAMESPACE+"getContents", map);
	}

	@Override
	public ContentVO getContent(int contentId) {
		return sqlSession.selectOne(NAMESPACE+"getContent", contentId);
	}

	@Override
	public int getNextContents(int lastContentId, int count) {
		int nextContents = sqlSession.selectOne(NAMESPACE+"getNextContents", lastContentId);
		
		System.out.println("[last]" + lastContentId + "/" + nextContents);
		return pages(nextContents, count);
	}

	@Override
	public int getPrevContents(int firstContentId, int count) {
		int prevContents = sqlSession.selectOne(NAMESPACE+"getPrevContents", firstContentId);
		System.out.println("[first]" + firstContentId + "/" + prevContents);
		return pages(prevContents, count);
	}

	@Override
	public int getLastContentId() {
		return sqlSession.selectOne(NAMESPACE+"getLastContentId");
	}

	@Override
	public int setContentAvailable(int contentId, int available) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", contentId);
		map.put("boardAvailable", available);
		return sqlSession.update(NAMESPACE+"setContentAvailable", map);
	}

	@Override
	public int updateContent(ContentVO content) {
		return sqlSession.update(NAMESPACE+"updateContent", content);
	}

	@Override
	public int insertContent(ContentVO content) {
		return sqlSession.insert(NAMESPACE+"insertContent", content);
	}

	@Override
	public int deleteContent(int contentId) {
		return sqlSession.delete(NAMESPACE+"deleteContent", contentId);
	}
	
	
	public int pages(int contents, int count) {
		int pages = Integer.valueOf(contents / count);
		if( contents % count != 0 ) { pages += 1; }
		return pages;
	}

}
