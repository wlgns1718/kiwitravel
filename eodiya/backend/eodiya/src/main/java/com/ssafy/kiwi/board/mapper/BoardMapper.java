package com.ssafy.kiwi.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kiwi.board.model.BoardDto;
import com.ssafy.kiwi.board.model.BoardViewDto;
import com.ssafy.kiwi.board.model.LikesDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> getListWhenNoUserInfo() throws Exception;

    List<BoardDto> getListWhenUserInfoExistAndNotFollowSelect(int userno) throws Exception;

    List<BoardDto> getListWhenUserInfoExistAndFollowSelect(int userno) throws Exception;

    void updateHit(int boardno) throws Exception;

    BoardDto getArticle(BoardViewDto boardViewDto) throws Exception;

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
