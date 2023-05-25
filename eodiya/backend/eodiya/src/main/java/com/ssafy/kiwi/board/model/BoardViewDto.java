package com.ssafy.kiwi.board.model;

import org.springframework.stereotype.Component;

@Component
public class BoardViewDto {

	private int userno;
	private int boardno;
	private int isFollowSelect;

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getIsFollowSelect() {
		return isFollowSelect;
	}

	public void setIsFollowSelect(int isFollowSelect) {
		this.isFollowSelect = isFollowSelect;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	@Override
	public String toString() {
		return "BoardViewDto [userno=" + userno + ", boardno=" + boardno + ", isFollowSelect=" + isFollowSelect + "]";
	}

}
