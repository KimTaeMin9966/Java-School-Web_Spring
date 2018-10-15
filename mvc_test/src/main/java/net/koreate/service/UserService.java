package net.koreate.service;

import net.koreate.dto.LoginDto;
import net.koreate.vo.UserVo;

public interface UserService {

	UserVo login(LoginDto dto) throws Exception;
	UserVo getUser(String uid) throws Exception;

}
