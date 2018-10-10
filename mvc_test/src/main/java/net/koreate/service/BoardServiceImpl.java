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
	public void create(BoardVo vo) throws Exception {
		dao.create(vo);
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

	// 2018/10/10
	@Override
	public List<BoardVo> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void update(BoardVo vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

}
