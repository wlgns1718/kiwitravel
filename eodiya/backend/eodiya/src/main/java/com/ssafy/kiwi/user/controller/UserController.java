package com.ssafy.kiwi.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.kiwi.file.service.FileService;
import com.ssafy.kiwi.jwt.service.JwtServiceImpl;
import com.ssafy.kiwi.user.model.UserDto;
import com.ssafy.kiwi.user.service.UserService;
import com.ssafy.kiwi.util.Util;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String SERVERFAIL = "server error";

	@Autowired
	private UserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private FileService fileService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto.getId(), userDto.getPassword());
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());
				userService.saveRefreshToken(loginUser.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", SERVERFAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> checkNick(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			String nickName = userService.nickCheck(userDto.getNickname());
			if (nickName == null) {
				resultMap.put("isVaild", "true");
			} else {
				resultMap.put("isVaild", "false");
			}
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/register/{userid}")
	public ResponseEntity<Map<String, Object>> checkId(@PathVariable("userid") String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			String userId = userService.idCheck(id);
			if (userId == null) {
				resultMap.put("isVaild", "true");
			} else {
				resultMap.put("isVaild", "false");
			}
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;

		} catch (Exception e) {
			logger.error("정보조회 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String id, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
			try {
				UserDto userDto = userService.userInfo(id);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
//			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/getfollower/{userno}")
	public ResponseEntity<?> getFollower(@PathVariable("userno") int userno) {
		logger.info("Follower List - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<Integer> follwerList = userService.getFollower(userno);
			resultMap.put("followerList", follwerList);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로워 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@GetMapping("/getfollowee/{userno}")
	public ResponseEntity<?> getFollowee(@PathVariable("userno") int userno) {
		logger.info("Followee List - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<Integer> followeeList = userService.getFollowee(userno);
			resultMap.put("followeeList", followeeList);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로잉 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			userService.regist(userDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("회원등록 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/modifyinfo")
	public ResponseEntity<Map<String, Object>> modifyInfo(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		try {
			userService.modifyInfo(userDto);
//			System.out.println(userDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			resultMap.put("userInfo", userDto);

		} catch (Exception e) {
			logger.error("회원등록 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/otherinfo/{userno}")
	public ResponseEntity<?> getOtherUser(@PathVariable("userno") int userno) {
		logger.info("getOtherUser - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto userDto = userService.getOtherUser(userno);
			resultMap.put("userInfo", userDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로잉 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping(value = "/image", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> getImages(UserDto userDto, List<MultipartFile> files) {
		logger.info("getImages - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(userDto);
//		System.out.println(files);
		try {

			resultMap.put("userInfo", userDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("팔로잉 정보 가져오기 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/follow/{userno}/{followee}")
	public ResponseEntity<?> addFollow(@PathVariable("userno") int userno, @PathVariable("followee") int followee) {
		logger.info("addFollow - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int result = userService.addFollow(userno, followee);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("add Follow 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/follow/{userno}/{followee}")
	public ResponseEntity<?> deleteFollow(@PathVariable("userno") int userno, @PathVariable("followee") int followee) {
		logger.info("deleteFollow - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			int result = userService.deleteFollow(userno, followee);
			if (result == 1) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("add Follow 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
