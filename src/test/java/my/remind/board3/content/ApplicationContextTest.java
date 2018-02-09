package my.remind.board3.content;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.remind.board3.dao.comment.CommentDAO;
import my.remind.board3.dao.content.ContentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ApplicationContextTest {
	
	@Inject
	ApplicationContext ctx;
	
	@Inject
	SqlSessionFactory factory;
	
	@Inject
	SqlSession sqlSession;
	
	@Inject
	ContentDAO cnt;
	
	@Inject
	CommentDAO cmt;
	
	@Test
	public void test() throws Exception {
		System.out.println("[context] " + ctx);
	}
	
	@Test
	public void factory() throws Exception{
		System.out.println("[factory] "+ factory);
	}
	
	@Test
	public void session() throws Exception{
		System.out.println("[session] "+ sqlSession);
	}
	
	@Test
	public void contentDAO() throws Exception{
		System.out.println("[contentDAO]" + cnt);
	}
	
	@Test
	public void commentDAO() throws Exception {
		System.out.println("[commentDAO" + cmt);
	}
}
