package com.ssafy.kiwi.util;

import java.io.File;
import org.slf4j.Logger;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.board.controller.BoardController;
import com.ssafy.kiwi.file.model.FileDto;

@Component
public class Util {

	private final String rootPath = System.getProperty("user.dir");
	private final String fileDir = rootPath + File.separator+"resources"+File.separator;
	private final Logger logger = LoggerFactory.getLogger(Util.class);

	// 비밀번호 해싱
	public String Hashing(String password, String salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시함수를 사용

		// key-stretching
		for (int i = 0; i < 10000; i++) {
			String newPassword = password + salt; // 패스워드와 Salt 를 합쳐 새로운 문자열 생성
			md.update(newPassword.getBytes()); // temp 의 문자열을 해싱하여 md 에 저장해둔다
			byte[] temp = md.digest(); // md 객체의 다이제스트를 얻어 password 를 갱신한다
			password = Byte_to_String(temp);
		}
		return password;
	}

	// 바이트 값을 16진수로 변경해준다
	public String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}

	// 임의의 salt 값 생성
	public String getSalt() {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[16];
		rnd.nextBytes(temp);
		return Byte_to_String(temp);
	}

//	public static String getFullPath(String filename) {
//		System.out.println("여깁니다!!!!!!!"+rootPath);
//		String folder = "";
//		LocalDate now = LocalDate.now();
////		System.out.println(fileDir+"images\\"+now+File.separator+filename);
//		
//		return fileDir+"images"+File.separator+now+File.separator+filename;
//	}

	public FileDto storeFile(MultipartFile multipartFile,int lastno) throws IOException{
		if(multipartFile.isEmpty()) {
            return null;
        }
		logger.info("storeFile - 호출!!!!!!!! {}");
		String originalFilename = new String(multipartFile.getOriginalFilename().getBytes("8859_1"), "UTF-8");
//		System.out.println(originalFilename);
		// 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
        // 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로
		String pos = extractExt(originalFilename);
		if(pos.equals("error")) {
			throw new IOException("확장자 오류");
		}
		String storeFilename = UUID.randomUUID() + "." + pos;
		 // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장
//		System.out.println("여기로 오나");
		LocalDate now = LocalDate.now();
		DateTimeFormatter simpledateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String today = now.format(simpledateFormat);
//		System.out.println(today+"여긴가");
		File savefolder = new File(fileDir+"images"+File.separator+today);
		logger.info("saveFolder - 생성 {}", savefolder);
		if (!savefolder.exists()) {
			savefolder.mkdirs();
		}
		
        multipartFile.transferTo(new File(savefolder,storeFilename));
        FileDto fileDto = new FileDto();
        fileDto.setBoardno(lastno);
        fileDto.setSaveFolder(today);
        fileDto.setStoreFilename(storeFilename);
        fileDto.setUploadFilename(originalFilename);
        return fileDto;
	}

	// 파일이 여러개 들어왔을 때 처리해주는 부분
	public List<FileDto> storeFiles(List<MultipartFile> multipartFiles, int lastno) throws IOException {
		List<FileDto> storeFileResult = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				storeFileResult.add(storeFile(multipartFile, lastno));
			}
		}
		return storeFileResult;
	}

	// 확장자 추출
	private String extractExt(String originalFilename) {
		int pos_temp = originalFilename.lastIndexOf(".");
		String pos = originalFilename.substring(pos_temp + 1);
		if(pos.equals("jpg")||pos.equals("png")||pos.equals("jpeg")) {
			return pos;
		}
		else {
			return "error";
		}
		
	}
}
