package net.koreate.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.UserDao;
import net.koreate.dto.LoginDto;
import net.koreate.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	UserDao dao;

	@Override
	public void signUp(UserVo vo) throws Exception {
		dao.signUp(vo);
	}

	@Override
	public UserVo getUserByID(String uid) throws Exception {
		return dao.getUserByID(uid);
	}

	@Override
	public UserVo signIn(LoginDto dto) throws Exception {
		return dao.signIn(dto);
	}

	@Override
	public UserVo getUserByUNO(int uno) throws Exception {
		return dao.getUserByUNO(uno);
	}
	
}
