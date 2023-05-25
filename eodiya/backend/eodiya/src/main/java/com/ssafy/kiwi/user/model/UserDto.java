package com.ssafy.kiwi.user.model;

import com.ssafy.kiwi.file.model.FileDto;

public class UserDto {
	private int userno;
	private String id;
	private String password;
	private String salt;
	private String email;
	private String nickname;
	private int age;
	private String createdate;
	private String logintime;
	private int gender;
	private int isRestrict;
	
	//프로필 사진 model
	private FileDto attachImage;// 첨부 파일
	

	
	public FileDto getAttachImage() {
		return attachImage;
	}

	public void setAttachImage(FileDto attachImage) {
		this.attachImage = attachImage;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getIsRestrict() {
		return isRestrict;
	}

	public void setIsRestrict(int isRestrict) {
		this.isRestrict = isRestrict;
	}

	@Override
	public String toString() {
		return "UserDto [userno=" + userno + ", id=" + id + ", password=" + password + ", salt=" + salt + ", email="
				+ email + ", nickname=" + nickname + ", age=" + age + ", createdate=" + createdate + ", logintime="
				+ logintime + ", gender=" + gender + ", isRestrict=" + isRestrict + "]";
	}

}
