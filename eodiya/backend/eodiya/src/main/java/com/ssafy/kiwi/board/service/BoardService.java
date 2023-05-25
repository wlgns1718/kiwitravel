package com.ssafy.kiwi.board.service;

import java.util.List;

import com.ssafy.kiwi.board.model.BoardDto;
import com.ssafy.kiwi.board.model.BoardViewDto;
import com.ssafy.kiwi.board.model.LikesDto;

public interface BoardService {

	List<BoardDto> getList(BoardViewDto boardviewDto) throws Exception;

	void updateHit(int boardno) throws Exception;

	BoardDto getArticle(BoardViewDto boardviewDto) throws Exception;

	boolean modifyArticle(BoardDto boardDto) throws Exception;

	boolean deleteArticle(int boardno) throws Exception;

	int writeArticle(BoardDto boardDto) throws Exception;

	boolean addLikes(LikesDto likesDto) throws Exception;

	boolean deleteLikes(LikesDto likesDto) throws Exception;

	List<BoardDto> getBoardListwithUserInfo(int userno) throws Exception;

	List<BoardDto> getBoardListwithLike(int userno) throws Exception;

	List<BoardDto> getBestList() throws Exception;

	List<BoardDto> getNoticeList() throws Exception;

	int getcntImage(int boardno) throws Exception;
}
