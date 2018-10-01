package net.koreate.dao;

import java.util.List;

import net.koreate.vo.CommentVo;

public interface CommentDao {

	void create(CommentVo VO) throws Exception;
	List<CommentVo> list(int bno) throws Exception;
	void update(CommentVo vo)throws Exception;
	void delete(int cno)throws Exception;

}
