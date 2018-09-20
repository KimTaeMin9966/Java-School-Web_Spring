package net.koreate.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.koreate.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "net.koreate.app.mappers.MemberMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVo memberVo) {
		sqlSession.insert(namespace + ".insertMember", memberVo);
	}

	@Override
	public MemberVo readMember(String userid) throws Exception {
		return (MemberVo) sqlSession.selectOne(namespace + ".readMember", userid);
	}

	@Override
	public MemberVo readWithPass(String userid, String pass) throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", pass);
		return sqlSession.selectOne(namespace + ".readWithPass", paramMap);
	}

}
