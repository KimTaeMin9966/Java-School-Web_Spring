package net.koreate.dao;

import java.util.List;

import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

public interface BoardDao {

	void registerReply(ReplyBoardVo VO) throws Exception;
	void updateOrigin(int bno) throws Exception;
	List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception;
	int listReplyCount(SearchCriteria cri) throws Exception;
	void updateCnt(int bno) throws Exception;
	ReplyBoardVo readReply(int bno) throws Exception;
	void updateReply(ReplyBoardVo VO) throws Exception;
	void replyRegister(ReplyBoardVo VO) throws Exception;

}
