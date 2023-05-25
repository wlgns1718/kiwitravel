package com.ssafy.kiwi.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.kiwi.user.model.UserDto;

@Mapper
public interface UserMapper {

	UserDto login(String id, String pw) throws Exception;

	String findSalt(String id) throws Exception;

	UserDto userInfo(String id) throws Exception;

	void saveRefreshToken(Map<String, String> map) throws Exception;

	Object getRefreshToken(String userid) throws Exception;

	void deleteRefreshToken(Map<String, String> map) throws Exception;

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
