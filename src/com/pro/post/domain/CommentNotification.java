package com.pro.post.domain;

import java.util.Date;
import java.util.UUID;

public class CommentNotification {

	private String key;
	private String postId;
	private String postedByUserId;
	private String postCommentedByUserId;
	private String commentId;
	private String commentTime;
	private Date commentIdDate;
	
	
	public Date getCommentIdDate() {
		return commentIdDate;
	}
	public void setCommentIdDate(Date commentIdDate) {
		this.commentIdDate = commentIdDate;
	}
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
	public String getPostCommentedByUserId() {
		return postCommentedByUserId;
	}
	public void setPostCommentedByUserId(String postCommentedByUserId) {
		this.postCommentedByUserId = postCommentedByUserId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
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
