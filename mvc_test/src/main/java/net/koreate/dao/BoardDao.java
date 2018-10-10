package net.koreate.dao;

import java.util.List;

import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;

public interface BoardDao {

	void create(BoardVo vo) throws Exception;
	List<BoardVo> listCriteria(Criteria cri) throws Exception;
	int listCountCriteria(Criteria cri) throws Exception;
	
	// 2018/10/10
	List<BoardVo> listAll() throws Exception;
	BoardVo read(int bno) throws Exception;
	void update(BoardVo vo) throws Exception;
	void delete(int bno) throws Exception;

}