package com.ssafy.kiwi.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.kiwi.board.mapper.ReplyMapper;
import com.ssafy.kiwi.board.model.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService {

	private ReplyMapper replyMapper;

	public ReplyServiceImpl(ReplyMapper replyMapper) {
		super();
		this.replyMapper = replyMapper;
	}

	@Override
	public List<ReplyDto> getReply(int boardno) throws Exception {
		return replyMapper.getReply(boardno);
	}

	@Override
	public boolean writeReply(ReplyDto replyDto) throws Exception {
		return replyMapper.writeReply(replyDto);
	}

	@Override
	public boolean deleteReply(int replyno) throws Exception {
		return replyMapper.deleteReply(replyno);
	}

	@Override
	public boolean updateReply(ReplyDto replyDto) throws Exception {
		return replyMapper.updateReply(replyDto);
	}

}
