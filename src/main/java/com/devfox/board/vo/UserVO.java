package com.devfox.board.vo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int userNo;
	
	@NotBlank
	@Email
	private String userId;
	
	@NotBlank
	private String userPw;
	
	private int userRl;
	private int userMc;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public int getUserRl() {
		return userRl;
	}
	public void setUserRl(int userRl) {
		this.userRl = userRl;
	}
	public int getUserMc() {
		return userMc;
	}
	public void setUserMc(int userMc) {
		this.userMc = userMc;
	}
	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userRl=" + userRl
				+ ", userMc=" + userMc + "]";
	}
}
