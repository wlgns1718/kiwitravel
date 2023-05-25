package com.ssafy.kiwi.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kiwi.board.model.ReplyDto;

@Mapper
public interface ReplyMapper {

	List<ReplyDto> getReply(int boardno) throws Exception;

	boolean writeReply(ReplyDto replyDto) throws Exception;

	boolean deleteReply(int replyno) throws Exception;

	boolean updateReply(ReplyDto replyDto) throws Exception;

}
