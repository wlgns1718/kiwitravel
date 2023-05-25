package com.ssafy.kiwi.file.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kiwi.file.model.FileDto;

@Mapper
public interface FileMapper {

	void insertFile(FileDto fileDto) throws Exception;
	
	List<FileDto> getBoardImages(int boardno) throws Exception;
	
}
