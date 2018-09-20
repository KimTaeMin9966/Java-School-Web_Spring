package net.koreate.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Inject
	private SqlSession session;

	private static String namespace = "net.koreate.mappers.BoardMapper";

	@Override
	public void create(BoardVo boardVo) throws Exception {
		session.insert(namespace + ".create", boardVo);
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void modify(BoardVo boardVo) throws Exception {
		session.update(namespace + ".update", boardVo);
	}

	@Override
	public void remove(int bno) throws Exception {
		session.delete(namespace + ".remove", bno);

	}

	@Override
	public List<BoardVo> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCri", cri);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public int listCountCriteria() throws Exception {
		return session.selectOne(namespace + ".listCount");
	}

	@Override
	public void updateViewCnt(int bno) throws Exception {
		session.update(namespace + ".updateViewCnt", bno);
	}

}
