package com.svctop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class HistoryDao {
	@Autowired
    private SqlSession sqlSession;
    
    public List<HashMap> selectHistoryBySeq(String seq){
        return this.sqlSession.selectList("select",seq);
    }

	public void inputReply(HistoryDao rvo)throws Exception {
		sqlSession.insert("write", rvo);
		
	}
	
	public void inputReply1(List<Map<String, Object>> body)throws Exception {
		sqlSession.insert("write", body);
		
	}
	
	public void modifyReply(HistoryDao rvo) {
		// TODO Auto-generated method stub
		
		sqlSession.update("modify", rvo);
		
	}

	public void inputReply(ReplyVO rvo) {
		// TODO Auto-generated method stub
		
	}

	public ResponseEntity<String> modifyReply(int blogId, String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(int blogId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void inputReply2(List<Map<String,Object>> rvo) {
		
		for(int i=0; i<rvo.size(); i++) {  
			sqlSession.insert("write", rvo.get(i));	    
		}
		
		
	}



}
