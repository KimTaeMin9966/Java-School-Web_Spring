package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.vo.PageMaker;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

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

	@Override
	public List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception {
		return dao.listReplyCriteria(cri);
	}

	@Override
	public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int cnt = dao.listReplyCount(cri);
		pageMaker.setTotalCount(cnt);
		return pageMaker;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		dao.updateCnt(bno);
	}

	@Override
	public ReplyBoardVo readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}

	@Override
	public void replyRegister(ReplyBoardVo VO) throws Exception {
		int origin = VO.getOrigin(); // 그룹 번호
		int depth = VO.getDepth(); // 그룹 수준 번호
		int seq = VO.getSeq(); // 정렬 수준
		
		dao.updateReply(VO);
		
		VO.setOrigin(origin);
		VO.setDepth(depth);
		VO.setSeq(seq);
		
		dao.replyRegister(VO);
		
		
		
		
	}
}
