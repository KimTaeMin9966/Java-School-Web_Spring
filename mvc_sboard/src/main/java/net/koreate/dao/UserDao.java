package net.koreate.dao;

import net.koreate.dto.LoginDto;
import net.koreate.vo.UserVo;

public interface UserDao {

	void signUp(UserVo vo) throws Exception;
	UserVo getUserByID(String uid) throws Exception;
	UserVo signIn(LoginDto dto) throws Exception;
	UserVo getUserByUNO(int uno) throws Exception;
	
}
