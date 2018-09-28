package net.koreate.dao;

import net.koreate.vo.ReplyBoardVo;

public interface BoardDao {

	void registerReply(ReplyBoardVo VO) throws Exception;
	void updateOrigin(int bno) throws Exception;

}
