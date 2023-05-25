package com.ssafy.kiwi.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.kiwi.file.model.FileDto;
import com.ssafy.kiwi.file.service.FileService;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {
	
	private final Logger logger = LoggerFactory.getLogger(FileController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private FileService fileService;

	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}
	
	
	@GetMapping("boardimages/{boardno}")
	public ResponseEntity<?> getBoardImages(@PathVariable("boardno") int boardno)
			throws Exception {
		logger.info("getBoardImages - 호출 : " + boardno);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(boardno);
		try {
			List<FileDto> fileDtos = fileService.getBoardImages(boardno);
			resultMap.put("images",fileDtos);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("이미지 불러오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@GetMapping("/showimage")
	public ResponseEntity<Resource> showImage(@RequestParam Map<String,String> param) throws IOException{
		String imageRoot= "";
		String saveFolder = param.get("saveFolder");
		String saveFile = param.get("storeFilename");
		imageRoot = new File("").getAbsolutePath() + "/resources/images/"+saveFolder+"/"+saveFile;
		
		
		 // Resorce를 사용해서 로컬 서버에 저장된 이미지 경로 및 파일 명을 지정
        Resource resource = new FileSystemResource(imageRoot);
        
     // 로컬 서버에 저장된 이미지 파일이 없을 경우
        if(!resource.exists()){
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 리턴 결과 반환 404
        }
        
     // 로컬 서버에 저장된 이미지가 있는 경우 로직 처리
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(imageRoot);
            // 인풋으로 들어온 파일명 .png / .jpg 에 맞게 헤더 타입 설정
            header.add("Content-Type", Files.probeContentType(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //http://localhost:9999/image/showimage?saveFolder=C:/SSAFY2/eodiya/backend/eodiya/resources/images/2023-05-25&storeFilename=46ec12bf-2069-4f02-b27c-e84c620e81cb.png
        // 이미지 리턴 실시 [브라우저에서 get 주소 확인 가능]
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
	
	
	
	
	
	
	
}
