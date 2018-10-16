package net.koreate.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.UserDao;
import net.koreate.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	UserDao dao;

	@Override
	public void signUp(UserVo vo) throws Exception {
		dao.signUp(vo);
	}
}
