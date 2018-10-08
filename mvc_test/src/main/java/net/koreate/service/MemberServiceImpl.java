package net.koreate.service;

import javax.inject.Inject;

import net.koreate.dao.MemberDao;
import net.koreate.vo.MemberVo;

public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDao dao;

	@Override
	public void insert(MemberVo VO) throws Exception {
		dao.insertMember(VO);
	}

}
