package com.svctop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svctop.dao.ReplyVO;


public interface ReplySvc {
	public void inputReply(ReplyVO rvo) throws Exception;
}
