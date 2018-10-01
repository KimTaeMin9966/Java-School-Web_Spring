package net.koreate.service;

import java.util.List;

import net.koreate.vo.CommentVo;

public interface CommentService {

	void addComment(CommentVo VO) throws Exception;
	List<CommentVo> listComment(int bno) throws Exception;
	void modifyComment(CommentVo VO) throws Exception;
	void removeComment(int cno)throws Exception;

}
