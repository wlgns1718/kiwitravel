package com.ssafy.kiwi.file.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.file.model.FileDto;


public interface FileService {
	

	void insertFile(List<MultipartFile> files, int lastno) throws Exception;

	List<FileDto> getBoardImages(int boardno) throws Exception;
	
}
