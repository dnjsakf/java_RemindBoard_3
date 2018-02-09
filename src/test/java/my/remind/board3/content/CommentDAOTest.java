package my.remind.board3.content;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.remind.board3.dao.comment.CommentDAO;
import my.remind.board3.vo.CommentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDAOTest {
	
	@Inject
	CommentDAO dao;
	
	@Test
	public void getComments() throws Exception{
		ArrayList<CommentVO> comments = dao.getComments(3, 1, 10);
		
		System.out.println("[comments]" + comments.size());
	}
	
	@Test
	public void getNextComments() throws Exception{
		System.out.println("[NextComments]" + dao.getNextComments(3, 20, 3));
	}
	
	@Test
	public void getPrevComments() throws Exception{
		System.out.println("[PrevComments]" + dao.getPrevComments(3, 20, 3));
	}
	

	@Test
	public void updateComment() throws Exception{
		DateFormat df = DateFormat.getDateTimeInstance();
		CommentVO comment = new CommentVO(
					"허정운",
					df.format(new Date()),
					"1234",
					"테스트로 입력해봅니다!!!",
					30
				);
		comment.setNo(62);
		System.out.println("[update]" + dao.updateComment(comment));
	}
	/*
	@Test
	public void insertComment() throws Exception{
		DateFormat df = DateFormat.getDateTimeInstance();
		CommentVO comment = new CommentVO(
					"허정운",
					df.format(new Date()),
					"1234",
					"테스트로 입력해봅니다!!!",
					4
				);
		System.out.println("[insert]" + dao.insertComment(comment));
	}
	*/
}
