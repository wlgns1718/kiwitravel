package com.ssafy.kiwi.file.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.file.mapper.FileMapper;
import com.ssafy.kiwi.file.model.FileDto;
import com.ssafy.kiwi.util.Util;

@Service
public class FileServiceImpl implements FileService {
	
	private FileMapper fileMapper;
	private Util util;
	
	public FileServiceImpl(FileMapper fileMapper, Util util) {
		super();
		this.fileMapper = fileMapper;
		this.util = util;
	}

	@Override
	public void insertFile(List<MultipartFile> files, int lastno) throws Exception {
		//fileDto 생성
		List<FileDto> filedto = util.storeFiles(files,lastno);
		for(FileDto file : filedto) {
			fileMapper.insertFile(file);
		}
		
	}

	@Override
	public List<FileDto> getBoardImages(int boardno) throws Exception {
		List<FileDto> list = fileMapper.getBoardImages(boardno);
		return list;
	}

}
