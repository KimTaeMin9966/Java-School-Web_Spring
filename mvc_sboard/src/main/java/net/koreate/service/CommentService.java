package net.koreate.service;

import java.util.List;

import net.koreate.vo.CommentVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

public interface CommentService {

	void addComment(CommentVo VO) throws Exception;
	List<CommentVo> listComment(int bno) throws Exception;
	void modifyComment(CommentVo VO) throws Exception;
	void removeComment(int cno)throws Exception;
	PageMaker getPageMaker(int page, int bno) throws Exception;
	List<CommentVo> listCommentPage(int bno, Criteria cri) throws Exception;

}
