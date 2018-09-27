package net.koreate.dao;

import java.util.List;

import net.koreate.vo.Search;
import net.koreate.vo.replyBoardVo;

public interface replyBoardDao {

	public void create(replyBoardVo VO) throws Exception;
	public List<replyBoardVo> listAllShow() throws Exception;
	public replyBoardVo read(int bno) throws Exception;
	public void remove(int bno) throws Exception;
	public void modify(replyBoardVo VO) throws Exception;
	public void updateViewCnt(int bno) throws Exception;
	public List<replyBoardVo> listSearch(Search cri) throws Exception;
	public void reply(replyBoardVo VO) throws Exception;

}
