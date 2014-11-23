package com.pro.post.domain;

import java.sql.Timestamp;

import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;

public class CommentNotificationJson {

	private String key;
	private PostInfo postId;
	private EmpInfo postedByUserId;
	private EmpInfo postCommentedByUserId;
	
	private PostComments commentId;
	private Timestamp commentTime;
	private int status;
	private int flow;
	private String commetnTimeStr;
	private String commentTimeStr2;
	private String message;
	
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCommentTimeStr2() {
		return commentTimeStr2;
	}
	public void setCommentTimeStr2(String commentTimeStr2) {
		this.commentTimeStr2 = commentTimeStr2;
	}
	public String getCommetnTimeStr() {
		return commetnTimeStr;
	}
	public void setCommetnTimeStr(String commetnTimeStr) {
		this.commetnTimeStr = commetnTimeStr;
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
	public EmpInfo getPostCommentedByUserId() {
		return postCommentedByUserId;
	}
	public void setPostCommentedByUserId(EmpInfo postCommentedByUserId) {
		this.postCommentedByUserId = postCommentedByUserId;
	}

	
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
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
	public PostComments getCommentId() {
		return commentId;
	}
	public void setCommentId(PostComments commentId) {
		this.commentId = commentId;
	}
	
	
	
	
	
}
