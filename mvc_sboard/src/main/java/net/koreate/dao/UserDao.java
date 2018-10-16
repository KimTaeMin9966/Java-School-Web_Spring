package net.koreate.dao;

import net.koreate.vo.UserVo;

public interface UserDao {

	void signUp(UserVo vo) throws Exception;
	
}
