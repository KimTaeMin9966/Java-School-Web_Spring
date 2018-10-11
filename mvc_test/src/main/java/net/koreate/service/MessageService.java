package net.koreate.service;

import net.koreate.vo.MessageVo;

public interface MessageService {

	void addMessage(MessageVo vo) throws Exception;
	MessageVo readMessage(String uid, int mno) throws Exception;
	
}
