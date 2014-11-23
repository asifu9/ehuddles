package com.pro.post.domain;

import java.sql.Timestamp;

import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostInfo;

public class LikeNotificationJson {

	private String key;
	private PostInfo postId;
	private EmpInfo postedByUserId;
	private EmpInfo postLikedByUserId;
	
	private String likeId;
	private Timestamp likedTime;
	private String likedTimeStr2;
	private int status;
	private int flow;
	private String likedTimeStr;
	private String message;
	private PhotoInfo photoInfo;
	
	
	public PhotoInfo getPhotoInfo() {
		return photoInfo;
	}
	public void setPhotoInfo(PhotoInfo photoInfo) {
		this.photoInfo = photoInfo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLikedTimeStr2() {
		return likedTimeStr2;
	}
	public void setLikedTimeStr2(String likedTimeStr2) {
		this.likedTimeStr2 = likedTimeStr2;
	}
	public String getLikedTimeStr() {
		return likedTimeStr;
	}
	public void setLikedTimeStr(String likedTimeStr) {
		this.likedTimeStr = likedTimeStr;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public PostInfo getPostId() {
		return postId;
	}
	public void setPostId(PostInfo postId) {
		this.postId = postId;
	}
	public EmpInfo getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(EmpInfo postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	public EmpInfo getPostLikedByUserId() {
		return postLikedByUserId;
	}
	public void setPostLikedByUserId(EmpInfo postLikedByUserId) {
		this.postLikedByUserId = postLikedByUserId;
	}
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}
	 
	public Timestamp getLikedTime() {
		return likedTime;
	}
	public void setLikedTime(Timestamp likedTime) {
		this.likedTime = likedTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	
	
	
}
