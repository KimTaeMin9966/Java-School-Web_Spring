package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.replyBoardDao;
import net.koreate.vo.Search;
import net.koreate.vo.replyBoardVo;

@Service
public class replyBoardServiceImpl implements replyBoardService {

	@Inject
	private replyBoardDao dao;
	
	@Override
	public void regist(replyBoardVo VO) throws Exception {
		dao.create(VO);
	}

	@Override
	public List<replyBoardVo> listAllShow() throws Exception {
		return dao.listAllShow();
	}

	@Override
	public replyBoardVo read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.remove(bno);
	}

	@Override
	public void modify(replyBoardVo VO) throws Exception {
		dao.modify(VO);
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		dao.updateViewCnt(bno);
	}

	@Override
	public List<replyBoardVo> listSearch(Search cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public void reply(replyBoardVo VO) throws Exception {
		dao.reply(VO);
	}

}
