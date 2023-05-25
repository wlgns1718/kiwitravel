package com.ssafy.kiwi.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.board.model.BoardDto;
import com.ssafy.kiwi.board.model.BoardViewDto;
import com.ssafy.kiwi.board.model.LikesDto;
import com.ssafy.kiwi.board.service.BoardService;
import com.ssafy.kiwi.file.service.FileService;
import com.ssafy.kiwi.user.model.UserDto;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private BoardService boardService;
	private FileService fileService;

	public BoardController(BoardService boardService, FileService fileService) {
		super();
		this.boardService = boardService;
		this.fileService = fileService;
	}

	@PostMapping
	public ResponseEntity<List<BoardDto>> listArticle(@RequestBody BoardViewDto boardviewDto) throws Exception {
		logger.info("listArticle - 호출");
		System.out.println(boardviewDto);

		return new ResponseEntity<List<BoardDto>>(boardService.getList(boardviewDto), HttpStatus.OK);
	}

	@PostMapping(value = "/write", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> writeArticle(BoardDto boardDto,
			@RequestParam(value = "files", required = false) List<MultipartFile> files) throws Exception {

		System.out.println(boardDto);
		logger.info("writeArticle - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			boardService.writeArticle(boardDto);
			int lastno = boardDto.getBoardno();
			System.out.println(files);
			if (files != null) {
				logger.info("파일 등록 호출");
				fileService.insertFile(files, lastno);
			}
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			logger.error("게시글 등록 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/detail")
	public ResponseEntity<BoardDto> getArticle(@RequestBody BoardViewDto boardviewDto) throws Exception {
		logger.info("getArticle - 호출 : " + boardviewDto);
		System.out.println(boardviewDto);
		boardService.updateHit(boardviewDto.getBoardno());
		System.out.println(boardviewDto.getBoardno());
		return new ResponseEntity<BoardDto>(boardService.getArticle(boardviewDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", boardDto);

		if (boardService.modifyArticle(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@DeleteMapping("/{boardno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("boardno") int boardno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticle(boardno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/addlikes")
	public ResponseEntity<String> addLikes(@RequestBody LikesDto likesDto) throws Exception {
		logger.info("addLikes - 호출");
		if (boardService.addLikes(likesDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/deletelikes")
	public ResponseEntity<String> deleteLikes(@RequestBody LikesDto likesDto) throws Exception {
		logger.info("deleteLikes - 호출");
		System.out.println(likesDto);
		if (boardService.deleteLikes(likesDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/mypage/{userno}")
	public ResponseEntity<List<BoardDto>> getBoardListwithUserInfo(@PathVariable("userno") int userno)
			throws Exception {
		logger.info("getBoardListwithUserInfo - 호출 : " + userno);
		System.out.println(userno);
		return new ResponseEntity<List<BoardDto>>(boardService.getBoardListwithUserInfo(userno), HttpStatus.OK);
	}

	@GetMapping("/liked/{userno}")
	public ResponseEntity<List<BoardDto>> getBoardListwithLike(@PathVariable("userno") int userno) throws Exception {
		logger.info("getBoardListwithLike - 호출 : ");
		return new ResponseEntity<List<BoardDto>>(boardService.getBoardListwithLike(userno), HttpStatus.OK);
	}

	@GetMapping("/best")
	public ResponseEntity<List<BoardDto>> bestArticle() throws Exception {
		logger.info("bestArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.getBestList(), HttpStatus.OK);
	}

	@GetMapping("/notice")
	public ResponseEntity<List<BoardDto>> noticeArticle() throws Exception {
		logger.info("noticeArticle - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.getNoticeList(), HttpStatus.OK);
	}

	@GetMapping("/cntimage/{boardno}")
	public ResponseEntity<?> getcntImage(@PathVariable("boardno") int boardno) {
		logger.info("getcntImage - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int cntimage = boardService.getcntImage(boardno);
			resultMap.put("cntImage", cntimage);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("게시글 사진 개수 가져오기 실패!! : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
}