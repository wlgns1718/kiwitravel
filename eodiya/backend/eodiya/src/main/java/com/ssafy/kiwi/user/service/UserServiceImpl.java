package com.ssafy.kiwi.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kiwi.user.mapper.UserMapper;
import com.ssafy.kiwi.user.model.UserDto;
import com.ssafy.kiwi.util.Util;

@Service
public class UserServiceImpl implements UserService {

	private Util util;
	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper, Util util) {
		super();
		this.userMapper = userMapper;
		this.util = util;
	}

	@Override
	public UserDto login(String id, String pw) throws Exception {
		String salt = userMapper.findSalt(id);
		String npw = util.Hashing(pw, salt);
		return userMapper.login(id, npw);
	}

	@Override
	public UserDto userInfo(String id) throws Exception {
		return userMapper.userInfo(id);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userMapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

	@Override
	public String idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	public String nickCheck(String nickname) throws Exception {
		return userMapper.nickCheck(nickname);
	}

	@Override
	public List<Integer> getFollower(int userno) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getFollower(userno);
		
	}

	@Override
	public List<Integer> getFollowee(int userno) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.getFollowee(userno);
	}

	@Override
	public void regist(UserDto userDto) throws Exception {
		String salt = util.getSalt();
		userDto.setSalt(salt);
		String npw = util.Hashing(userDto.getPassword(), salt);
		userDto.setPassword(npw);
		userMapper.regist(userDto);
		
	}

	@Override
	public void modifyInfo(UserDto userDto) throws Exception {
		// TODO Auto-generated method stub
		userMapper.modifyInfo(userDto);
		
	}

	@Override
	public UserDto getOtherUser(int userno) throws Exception {
		
		return userMapper.getOtherUser(userno);
	}

	@Override
	public int addFollow(int userno, int followee) throws Exception {
		return userMapper.addFollow(userno, followee);
	}

	@Override
	public int deleteFollow(int userno, int followee) throws Exception {
		return userMapper.deleteFollow(userno, followee);
	}

}
