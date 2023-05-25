package com.ssafy.kiwi.board.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.kiwi.file.model.FileDto;

@Component
public class BoardDto {

	private int boardno;
	private String title;
	private String content;
	private String createdate;
	private int hit;
	private int scope;
	private int userno;
	private String nickname;
	private int cntLike;
	private int cntReply;
	private int islike;
	private int cntimages;
	
	
	private FileDto attachFile;          // 첨부 파일
	private List<FileDto> imageFiles;    // 첨부 이미지
	
	
	
	public FileDto getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(FileDto attachFile) {
		this.attachFile = attachFile;
	}

	public List<FileDto> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<FileDto> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public int getCntLike() {
		return cntLike;
	}

	public void setCntLike(int cntLike) {
		this.cntLike = cntLike;
	}

	public int getCntReply() {
		return cntReply;
	}

	public void setCntReply(int cntReply) {
		this.cntReply = cntReply;
	}

	public int getIslike() {
		return islike;
	}

	public void setIslike(int islike) {
		this.islike = islike;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCntimages() {
		return cntimages;
	}

	public void setCntimages(int cntimages) {
		this.cntimages = cntimages;
	}

	@Override
	public String toString() {
		return "BoardDto [boardno=" + boardno + ", title=" + title + ", content=" + content + ", createdate="
				+ createdate + ", hit=" + hit + ", scope=" + scope + ", userno=" + userno + ", nickname=" + nickname
				+ ", cntLike=" + cntLike + ", cntReply=" + cntReply + ", islike=" + islike + ", cntimages=" + cntimages
				+ ", attachFile=" + attachFile + ", imageFiles=" + imageFiles + "]";
	}
		

	
}
