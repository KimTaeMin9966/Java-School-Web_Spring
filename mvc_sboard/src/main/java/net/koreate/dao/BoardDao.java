package net.koreate.dao;

import java.util.List;
import java.util.Map;

import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

public interface BoardDao {

	void registerReply(ReplyBoardVo VO) throws Exception;
	void updateOrigin() throws Exception;
	List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception;
	int listReplyCount(SearchCriteria cri) throws Exception;
	void updateCnt(int bno) throws Exception;
	ReplyBoardVo readReply(int bno) throws Exception;
	void updateReply(ReplyBoardVo VO) throws Exception;
	void replyRegister(ReplyBoardVo VO) throws Exception;
	void addAttach(String fullName) throws Exception;
	List<String> getAttach(int bno) throws Exception;
	void update(ReplyBoardVo VO) throws Exception;
	void deleteAttach(int bno) throws Exception;
	void replaceAttach(Map<String, Object> map) throws Exception;
	void delete(int bno) throws Exception;

}
