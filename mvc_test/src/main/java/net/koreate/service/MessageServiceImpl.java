package net.koreate.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.koreate.dao.MessageDao;
import net.koreate.dao.PointDao;
import net.koreate.vo.MessageVo;

@Service
public class MessageServiceImpl implements MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Inject
	MessageDao msgDao;
	
	@Inject
	PointDao pDao;

	@Override
	public void addMessage(MessageVo vo) throws Exception {
		logger.info("addMessage Called!!!"); logger.info("VO : " + vo);
		
		msgDao.create(vo);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", vo.getSender());
		paramMap.put("point", 10);
		
		pDao.updatePoint(paramMap);
		
		logger.info("addMessage finished!!!");
	}

	@Override
	public MessageVo readMessage(String uid, int mno) throws Exception {
		logger.info("readMessage Called!!!"); logger.info("uid : " + uid); logger.info("mno : " + mno);
		
		msgDao.update(mno);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("point", 5);
		
		pDao.updatePoint(paramMap);
		
		logger.info("readMessage finished!!!");
		return msgDao.readMessage(mno);
	}
	
}
