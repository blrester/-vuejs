package com.svctop.service;

import org.springframework.stereotype.Service;

import com.svctop.dao.HistoryDao;
import com.svctop.dao.ReplyVO;

@Service
public class ReplySvcImpl implements ReplySvc{
	
	private HistoryDao historyDao;

	@Override
	public void inputReply(ReplyVO rvo) throws Exception {
		historyDao.inputReply(rvo);
		
	}
}
