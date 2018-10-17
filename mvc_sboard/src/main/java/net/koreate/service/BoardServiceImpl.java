package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.koreate.dao.BoardDao;
import net.koreate.vo.PageMaker;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDao dao;

	@Override
	@Transactional
	public void registReply(ReplyBoardVo VO) throws Exception {
		dao.registerReply(VO);
		
		dao.updateOrigin();
		
		String[] files = VO.getFiles();
		if(files != null) for(String fullName : files) dao.addAttach(fullName);
	}

	@Override
	public List<ReplyBoardVo> listReplyCriteria(SearchCriteria cri) throws Exception {
		List<ReplyBoardVo> list = dao.listReplyCriteria(cri);
		
		for(ReplyBoardVo vo : list) vo.setCommentCnt(dao.getCommentCnt(vo.getBno()));
		return list;
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
		ReplyBoardVo VO = dao.readReply(bno);
		VO.setCommentCnt(dao.getCommentCnt(bno));
		return VO;
	}

	@Override
	public void replyRegister(ReplyBoardVo VO) throws Exception {
		int origin = VO.getOrigin(); // 그룹 번호
		int depth = VO.getDepth(); // 그룹 수준 번호
		int seq = VO.getSeq(); // 정렬 수준
		
		dao.updateReply(VO);
		
		VO.setOrigin(origin);
		VO.setDepth(depth + 1);
		VO.setSeq(seq + 1);
		
		dao.replyRegister(VO);
	}

	@Override
	public List<String> getAttach(int bno) throws Exception {
		return dao.getAttach(bno);
	}

	@Override
	@Transactional
	public void modify(ReplyBoardVo VO) throws Exception {
		/*ReplyBoardVo modifyVO = dao.readReply(VO.getBno());
		dao.modifyFiles(modifyVO);


		String[] files = VO.getFiles();
		if(files != null) for(String fullName : files) dao.updateFiles(fullName, VO);*/
		System.out.println("modify : " + VO);
		dao.update(VO);
		
		int bno = VO.getBno();
		dao.deleteAttach(bno);
		
		String[] files = VO.getFiles();
		
		if(files == null) return;
		for(String fullName : files) {
			Map<String,Object> map = new HashMap<>();
			map.put("bno", bno);
			map.put("fullName", fullName);
			dao.replaceAttach(map);
		}
	}

	@Override
	@Transactional
	public void remove(int bno) throws Exception {
		/*dao.deleteComments(bno);
		dao.deleteFiles(bno);*/
		dao.deleteAttach(bno);
		dao.delete(bno);
	}
	
}
