package net.koreate.dao;

import net.koreate.vo.MessageVo;

public interface MessageDao {

	public void create(MessageVo vo) throws Exception;
	public MessageVo readMessage(int mno) throws Exception;
	public void update(int mno) throws Exception;
	
}
