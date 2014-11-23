package com.pro.emp.domain;

import java.util.Date;

public class PostGroupLikeTable {

	private String key;
	private String postId;
	private String likedUserId;
	private String userPhotoPath;
	private Date likedDate;
	private String userName;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getLikedUserId() {
		return likedUserId;
	}
	public void setLikedUserId(String likedUserId) {
		this.likedUserId = likedUserId;
	}
	public String getUserPhotoPath() {
		return userPhotoPath;
	}
	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}
	public Date getLikedDate() {
		return likedDate;
	}
	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
