package net.koreate.dao;

import net.koreate.dto.LoginDto;
import net.koreate.vo.UserVo;

public interface UserDao {

	UserVo login(LoginDto dto) throws Exception;
	UserVo getUser(String uid) throws Exception;

}
