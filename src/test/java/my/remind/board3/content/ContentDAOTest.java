package my.remind.board3.content;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.remind.board3.dao.content.ContentDAO;
import my.remind.board3.vo.ContentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ContentDAOTest {
	@Inject
	ContentDAO dao;
	
	@Test
	public void getContent() throws Exception {
		System.out.println("[getContent] " + dao.getContent(2));
	}
	
	@Test
	public void getContents() throws Exception {
		ArrayList<ContentVO> contents = dao.getContents(1,10);
		
		System.out.println("[getContents]" + contents.size());
	}
	
	@Test
	public void getNextContents() throws Exception {
		System.out.println("[getNextContents]" + dao.getNextContents(10, 10));
	}
	
	@Test
	public void getPrevContents() throws Exception {
		System.out.println("[getPrevContents]" + dao.getPrevContents(1, 10));
	}
	
	@Test
	public void setContentAvailable() throws Exception{
		System.out.println("[setAvailable]" + dao.setContentAvailable(1, 0));
	}
	
	/*
	@Test
	public void insertContent() throws Exception{
		int id = dao.getLastContentId();
		DateFormat df = DateFormat.getDateTimeInstance();
		ContentVO content = new ContentVO(
									id + 1,
									"테스트 입력",
									"관리자",
									df.format(new Date()),
									"내용내용",
									1
								);
		System.out.println("[insert]" + dao.insertContent(content));
	}
	*/
	
}
