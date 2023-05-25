package com.ssafy.kiwi.board.model;

import org.springframework.stereotype.Component;

@Component
public class ReplyDto {

	private int replyno;
	private String content;
	private String createdate;
	private int boardno;
	private int userno;
	private String nickname;

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "ReplyDto [replyno=" + replyno + ", content=" + content + ", createdate=" + createdate + ", boardno="
				+ boardno + ", userno=" + userno + ", nickname=" + nickname + "]";
	}

}
