package net.koreate.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.vo.ReplyBoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDao dao;

	@Override
	public void registReply(ReplyBoardVo VO) throws Exception {
		dao.registerReply(VO);
		
		int bno = VO.getBno();
		dao.updateOrigin(bno);
	}
}
