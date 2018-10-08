package net.koreate.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MybatisTest {
	
	@Inject
	SqlSessionFactory ssf;
	
	@Test
	public void sqlSessionFactoryConntectTest() {
		
		try {
			SqlSession session = ssf.openSession();
			System.out.println("SQL SESSION 생성완료 SESSION" + session);
		} catch (Exception e) { e.printStackTrace(); System.out.println("연결실패"); }
		
	}
	
}