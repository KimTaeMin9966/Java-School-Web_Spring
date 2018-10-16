package net.koreate.service;

import net.koreate.dto.LoginDto;
import net.koreate.vo.UserVo;

public interface UserService {

	void signUp(UserVo vo) throws Exception;
	UserVo getUserByID(String uid) throws Exception;
	UserVo signIn(LoginDto dto) throws Exception;
	UserVo getUserByUNO(int uno) throws Exception;
	
}
