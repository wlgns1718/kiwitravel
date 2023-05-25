package com.ssafy.kiwi.board.service;

import java.util.List;

import com.ssafy.kiwi.board.model.ReplyDto;

public interface ReplyService {

	List<ReplyDto> getReply(int boardno) throws Exception;

	boolean writeReply(ReplyDto replyDto) throws Exception;

	boolean deleteReply(int replyno) throws Exception;

	boolean updateReply(ReplyDto replyDto) throws Exception;


}
