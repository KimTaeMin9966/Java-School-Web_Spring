package net.koreate.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.koreate.dao.CommentDao;
import net.koreate.vo.CommentVo;

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

}
