package com.ssafy.kiwi.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.board.model.ReplyDto;
import com.ssafy.kiwi.board.service.ReplyService;

@RestController
@RequestMapping("/reply")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReplyController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private ReplyService replyService;

	public ReplyController(ReplyService replyService) {
		super();
		this.replyService = replyService;
	}

	@GetMapping("/{boardno}")
	public ResponseEntity<List<ReplyDto>> getReply(@PathVariable("boardno") int boardno) throws Exception {
		logger.info("getReply - 호출");
		return new ResponseEntity<List<ReplyDto>>(replyService.getReply(boardno), HttpStatus.OK);
	}

	@PostMapping("/write")
	public ResponseEntity<String> writeReply(@RequestBody ReplyDto replyDto) throws Exception {
		logger.info("writeReply - 호출");
		if (replyService.writeReply(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{replyno}")
	public ResponseEntity<String> deleteReply(@PathVariable("replyno") int replyno) throws Exception {
		logger.info("deleteReply - 호출");
		if (replyService.deleteReply(replyno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateReply(@RequestBody ReplyDto replyDto) throws Exception {
		logger.info("updateReply - 호출");
		if (replyService.updateReply(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
