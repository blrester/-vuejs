package com.svctop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svctop.dao.HistoryDao;
import com.svctop.dao.ReplyVO;
import com.svctop.service.ReplySvc;

@RestController
@RequestMapping("/mybatis-mapping")
public class MybatisMapping {
	@Autowired
	private HistoryDao historyDao;

	@Autowired
	private ReplySvc rsvc;

	@RequestMapping(value = "/selectAll/{bid}", method = RequestMethod.GET)
	public List<HashMap> mybatisMapping1(@PathVariable("bid") String bid) {
		return historyDao.selectHistoryBySeq(bid);
	}

	@RequestMapping(value = "/select/{bid}", method = RequestMethod.GET)
	public ResponseEntity<List<HashMap>> list(@PathVariable("bid") String bid) {

		ResponseEntity<List<HashMap>> resEntity = null;

		try {
			resEntity = new ResponseEntity<>(historyDao.selectHistoryBySeq(bid), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

//	@PostMapping("/blog")
//	public ResponseEntity<String> created(@RequestBody Map<String, String> rvo){
//		
//		ResponseEntity<String> resEntity = null;
//		try {
//			historyDao.inputReply1(rvo);
//			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resEntity = new ResponseEntity<String>(e.getMessage(),
//					HttpStatus.BAD_REQUEST);
//		}
//		
//		return resEntity;
//	}
//	

	// 데이터 입력 확인 단일 제이쓴을 보낼때
	@PostMapping("/blog")
	public ResponseEntity<String> create(@RequestBody List<Map<String, Object>> body) {

		ResponseEntity<String> resEntity = null;
		try {
			historyDao.inputReply1(body);
			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

//  데이터 입력 확인  리스트로 제이슨을 보낼때 
	@PostMapping("/blog1")
	public Object testList(HttpServletRequest request, @RequestBody List<Map<String, Object>> list) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();

		ResponseEntity<String> resEntity = null;

		try {
			historyDao.inputReply2(list);
			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	@PutMapping("/blog/{id}")
	public ResponseEntity<String> update(@PathVariable String id, @RequestBody Map<String, String> body) {
		int blogId = Integer.parseInt(id);
		String title = body.get("title");
		String content = body.get("content");
		return historyDao.modifyReply(blogId, title, content);
	}

	@DeleteMapping("blog/{id}")
	public boolean delete(@PathVariable String id) {
		int blogId = Integer.parseInt(id);
		return historyDao.delete(blogId);
	}

	@RequestMapping(value = "/{rebid}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@PathVariable("rebid") Integer rebid, HistoryDao rvo) {

		ResponseEntity<String> resEntity = null;

		try {
			// rvo.setRebid(rebid);
			historyDao.modifyReply(rvo);

			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;

	}

}
