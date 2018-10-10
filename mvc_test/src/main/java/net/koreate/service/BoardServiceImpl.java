package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDao dao;

	@Override
	public void create(BoardVo VO) throws Exception {
		dao.create(VO);
	}

	@Override
	public List<BoardVo> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int cnt = dao.listCountCriteria(cri);
		pageMaker.setTotalCount(cnt);
		return pageMaker;
	}

}
