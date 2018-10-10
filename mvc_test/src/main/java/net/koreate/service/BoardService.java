package net.koreate.service;

import java.util.List;

import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

public interface BoardService {

	void create(BoardVo VO) throws Exception;
	List<BoardVo> listCriteria(Criteria cri) throws Exception;
	PageMaker getPageMaker(Criteria cri) throws Exception;

}
