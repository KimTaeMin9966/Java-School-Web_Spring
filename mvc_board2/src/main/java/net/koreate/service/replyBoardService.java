package net.koreate.service;

import java.util.List;

import net.koreate.vo.replyBoardVo;

public interface replyBoardService {

	void regist(replyBoardVo VO) throws Exception;
	public List<replyBoardVo> listAllShow() throws Exception;
	public replyBoardVo read(int bno) throws Exception;
	public void remove(int bno) throws Exception;
	void modify(replyBoardVo VO) throws Exception;

}
