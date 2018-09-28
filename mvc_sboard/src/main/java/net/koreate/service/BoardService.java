package net.koreate.service;

import net.koreate.vo.ReplyBoardVo;

public interface BoardService {

	// 게시물 작성
	void registReply(ReplyBoardVo VO) throws Exception;

}
