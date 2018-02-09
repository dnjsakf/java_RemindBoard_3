package my.remind.board3.dao.content;

import java.util.ArrayList;

import my.remind.board3.vo.ContentVO;

public interface ContentDAO {
	/************** Getters **************/
	/* TODO: getContents(int page, int count) : return ArrayList<ContentVO> */
	public ArrayList<ContentVO> getContents(int page, int count);
	
	/* TODO: getContent(int contentId) : return ContentVO */
	public ContentVO getContent(int contentId);
	
	/* TODO: getNextContents(int lastContentId) : return int */
	public int getNextContents(int lastContentId, int count);
	
	/* TODO: getPrevContents(int firstContentId) : return int */
	public int getPrevContents(int firstContentId, int count);
	
	/* TODO: getLastContentId() : return int */
	public int getLastContentId();
	
	/************** Setters **************/
	/* TODO: setContentAvailable(int contentId, int available) : return int */
	public int setContentAvailable(int contentId, int available);
	
	/* TODO: updateContent(ContentVO content) : return int */
	public int updateContent(ContentVO content);
	
	/* TODO: insertContent(ContentVO content) : return int */
	public int insertContent(ContentVO content);
	
	/* TODO: deleteContent(int contentId) : return int */
	public int deleteContent(int contentId);
	
}
