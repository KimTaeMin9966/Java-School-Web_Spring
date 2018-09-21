package net.koreate.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import net.koreate.vo.replyBoardVo;

@Service
public class replyBoardDaoImpl implements replyBoardDao {
	
	@Inject
	private SqlSession session;

	private static String namespace = "net.koreate.mappers.reBoardMapper";
	
	@Override
	public void create(replyBoardVo VO) throws Exception {
		session.insert(namespace + ".create", VO);
	}
	
	@Override
	public List<replyBoardVo> listAllShow() throws Exception {
		return session.selectList(namespace + ".listAllShow");
	}

	@Override
	public replyBoardVo read(int bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void remove(int bno) throws Exception {
		session.delete(namespace + ".remove", bno);
	}

	@Override
	public void modify(replyBoardVo VO) throws Exception {
		session.update(namespace + ".update", VO);
	}

}
