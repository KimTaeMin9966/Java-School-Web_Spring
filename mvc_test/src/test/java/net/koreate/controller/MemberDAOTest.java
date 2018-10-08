package net.koreate.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.koreate.dao.MemberDao;
import net.koreate.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MemberDAOTest {

	@Inject
	private MemberDao dao;

	@Test
	public void testInsertMember() throws Exception {
		System.out.println("dao : " + dao);
		MemberVo VO = new MemberVo();
		VO.setUserid("user01");
		VO.setUserpw("user01");
		VO.setUsername("USER01");
		VO.setEmail("user00@aaa.com");

		dao.insertMember(VO);
		System.out.println("입력완료 " + VO);
	}

}
