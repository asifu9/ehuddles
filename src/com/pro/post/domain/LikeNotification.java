package com.pro.post.domain;

import java.util.UUID;

public class LikeNotification {

	private String key;
	private String postId;
	private String postedByUserId;
	private String postLikedByUserId;
	private String likeId;
	private String likedTime;
	private int status;
	private int flow;
	private String message;
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
	public String getPostedByUserId() {
		return postedByUserId;
	}
	public void setPostedByUserId(String postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	public String getPostLikedByUserId() {
		return postLikedByUserId;
	}
	public void setPostLikedByUserId(String postLikedByUserId) {
		this.postLikedByUserId = postLikedByUserId;
	}
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}
	public String getLikedTime() {
		return likedTime;
	}
	public void setLikedTime(String likedTime) {
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
}
