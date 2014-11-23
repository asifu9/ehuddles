package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.List;

public class PostGroupComments {

	private String key;
	private String postId;
	private String commmentUserId;
	private String commentUserName;
	private String commentUserPhoto;
	private String commentDesc;
	private Timestamp commentDate;
	private String timeInStr;
	private List<PostLikeTable> likes;
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
	public String getCommmentUserId() {
		return commmentUserId;
	}
	public void setCommmentUserId(String commmentUserId) {
		this.commmentUserId = commmentUserId;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentUserPhoto() {
		return commentUserPhoto;
	}
	public void setCommentUserPhoto(String commentUserPhoto) {
		this.commentUserPhoto = commentUserPhoto;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public String getTimeInStr() {
		return timeInStr;
	}
	public void setTimeInStr(String timeInStr) {
		this.timeInStr = timeInStr;
	}
	public List<PostLikeTable> getLikes() {
		return likes;
	}
	public void setLikes(List<PostLikeTable> likes) {
		this.likes = likes;
	}
	
		
	
	
	
	
}
