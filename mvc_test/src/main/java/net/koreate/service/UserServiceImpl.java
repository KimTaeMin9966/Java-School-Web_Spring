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
	public UserVo login(LoginDto dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public UserVo getUser(String uid) throws Exception {
		return dao.getUser(uid);
	}

}
