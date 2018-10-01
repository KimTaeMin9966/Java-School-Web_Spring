package net.koreate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.CommentDao;
import net.koreate.vo.CommentVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	CommentDao dao;
	
	@Override
	public void addComment(CommentVo VO) throws Exception {
		dao.create(VO);
	}

	@Override
	public List<CommentVo> listComment(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void modifyComment(CommentVo VO) throws Exception {
		dao.update(VO);
	}

	@Override
	public void removeComment(int cno) throws Exception {
		dao.delete(cno);
	}

	@Override
	public PageMaker getPageMaker(int page, int bno) throws Exception {
		Criteria cri = new Criteria(); cri.setPage(page);
		int cnt = dao.count(bno);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(cnt);
		
		return pageMaker;
	}

	@Override
	public List<CommentVo> listCommentPage(int bno, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno); map.put("cri", cri);
		return dao.listPage(map);
	}

}
