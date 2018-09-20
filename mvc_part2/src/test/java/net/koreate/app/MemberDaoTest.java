package net.koreate.app;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.MemberDao;
import net.koreate.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDaoTest {
	
	@Inject
	MemberDao dao;
	
	@Test
	public void testTime() {
		System.out.println(dao.getTime());
	}
	
	@Test
	public void testInsertMEmber() {
		MemberVo memberVo = new MemberVo();
		memberVo.setUserid("id003");
		memberVo.setUserpw("pass1");
		memberVo.setUsername("홍길동");
		memberVo.setEmail("email");
		dao.insertMember(memberVo);
	}
	

}
