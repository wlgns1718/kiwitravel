package com.ssafy.kiwi.user.service;

import java.util.List;

import com.ssafy.kiwi.user.model.UserDto;

public interface UserService {

	UserDto login(String id, String pw) throws Exception;

	UserDto userInfo(String id) throws Exception;

	void saveRefreshToken(String userid, String refreshToken) throws Exception;

	Object getRefreshToken(String userid) throws Exception;

	void deleRefreshToken(String userid) throws Exception;

	String idCheck(String id) throws Exception;

	String nickCheck(String nickname) throws Exception;

	List<Integer> getFollower(int userno) throws Exception;

	List<Integer> getFollowee(int userno) throws Exception;

	void regist(UserDto userDto) throws Exception;

	void modifyInfo(UserDto userDto) throws Exception;

	UserDto getOtherUser(int userno) throws Exception;

	int addFollow(int userno, int followee) throws Exception;

	int deleteFollow(int userno, int followee) throws Exception;

}
