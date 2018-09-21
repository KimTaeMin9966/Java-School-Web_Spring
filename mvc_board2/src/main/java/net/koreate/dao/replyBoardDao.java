package net.koreate.dao;

import java.util.List;

import net.koreate.vo.replyBoardVo;

public interface replyBoardDao {

	void create(replyBoardVo VO) throws Exception;
	public List<replyBoardVo> listAllShow() throws Exception;
	public replyBoardVo read(int bno) throws Exception;
	public void remove(int bno) throws Exception;
	void modify(replyBoardVo VO) throws Exception;

}
