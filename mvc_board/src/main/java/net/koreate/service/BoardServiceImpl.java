package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.BoardDao;
import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDao dao;

	@Override
	public void regist(BoardVo board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVo boardVo) throws Exception {
		dao.modify(boardVo);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.remove(bno);
	}

	@Override
	public List<BoardVo> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public int listCountCriteria() throws Exception {
		return dao.listCountCriteria();
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		dao.updateViewCnt(bno);
	}

	@Override
	public List<BoardVo> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

}
