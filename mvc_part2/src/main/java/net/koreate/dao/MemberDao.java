package net.koreate.dao;

import net.koreate.vo.MemberVo;

public interface MemberDao {

	public String getTime();

	public void insertMember(MemberVo memberVo);

	public MemberVo readMember(String userid) throws Exception;

	public MemberVo readWithPass(String userid, String pass) throws Exception;

}
