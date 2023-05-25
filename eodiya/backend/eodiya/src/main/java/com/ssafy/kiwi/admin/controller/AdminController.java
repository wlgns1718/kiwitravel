package com.ssafy.kiwi.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.kiwi.tour.controller.TourController;
import com.ssafy.kiwi.user.model.UserDto;
import com.ssafy.kiwi.user.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

//	private final Logger logger = LoggerFactory.getLogger(TourController.class);
//
//	private UserService userService;
//
//	@Autowired
//	public AdminController(UserService userService) {
//		super();
//		this.userService = userService;
//	}

//	@GetMapping("/user")
//	public ResponseEntity<?> userList() {
//		logger.debug("userList call");
//		try {
//			List<UserDto> list = userService.listMember(null);
//			if (list != null && !list.isEmpty()) {
//				return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//
//	}

//	@GetMapping(value = "/user/{userid}")
//	public ResponseEntity<?> userInfo(@PathVariable("userid") String userId) {
//		logger.debug("userInfo userid : {}", userId);
//		try {
//			UserDto UserDto = memberService.getMember(userId);
//			if (UserDto != null)
//				return new ResponseEntity<UserDto>(UserDto, HttpStatus.OK);
//			else
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@PostMapping(value = "/user")
//	public ResponseEntity<?> userRegister(@RequestBody UserDto UserDto) {
//		logger.debug("userRegister UserDto : {}", UserDto);
//		try {
//			memberService.joinMember(UserDto);
//			List<UserDto> list = memberService.listMember(null);
//			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
////			return new ResponseEntity<Integer>(cnt, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//
//	}
//
//	@PutMapping(value = "/user")
//	public ResponseEntity<?> userModify(@RequestBody UserDto UserDto) {
//		logger.debug("userModify UserDto : {}", UserDto);
//		try {
//			memberService.updateMember(UserDto);
//			List<UserDto> list = memberService.listMember(null);
//			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
//	@DeleteMapping(value = "/user/{userid}")
//	public ResponseEntity<?> userDelete(@PathVariable("userid") String userId) {
//		logger.debug("userDelete userid : {}", userId);
//		try {
//			memberService.deleteMember(userId);
//			List<UserDto> list = memberService.listMember(null);
//			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
//
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
