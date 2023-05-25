package com.ssafy.kiwi.board.model;

import org.springframework.stereotype.Component;

@Component
public class LikesDto {

	private int userno;
	private int boardno;

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	
	

	@Override
	public String toString() {
		return "LikesDto [userno=" + userno + ", boardno=" + boardno + "]";
	}

}
