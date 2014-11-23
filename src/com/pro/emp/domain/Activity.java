package com.pro.emp.domain;

import java.sql.Timestamp;

public class Activity {

	/**
	 * CREATE TABLE activity ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "activityType text,"+
	    		   "fromId text,toId text,"+
	    		   "postId text,"+
	    		   "groupId text,"+
	    		   "activityTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "flow int"+
	 * 
	 */
	private String key;
	private int activityType;
	private String fromId;
	private String commentId;
	private PostComments commentIdInfo;
	private EmpBasicInfo fromIdInfo;
	private String toId;
	private EmpBasicInfo toIdInfo;
	private String postId;
	private PostInfo postIdInfo;
	private String groupId;
	private Group groupIdInfo;
	private Timestamp activityTime;
	private String activityTimeStr;
	private int status;
	private int flow;
	
	
	
	
	/**
	 * @return the commentIdInfo
	 */
	public PostComments getCommentIdInfo() {
		return commentIdInfo;
	}
	/**
	 * @param commentIdInfo the commentIdInfo to set
	 */
	public void setCommentIdInfo(PostComments commentIdInfo) {
		this.commentIdInfo = commentIdInfo;
	}
	/**
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the activityType
	 */
	public int getActivityType() {
		return activityType;
	}
	/**
	 * @param activityType the activityType to set
	 */
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	/**
	 * @return the fromId
	 */
	public String getFromId() {
		return fromId;
	}
	/**
	 * @param fromId the fromId to set
	 */
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	/**
	 * @return the fromIdInfo
	 */
	public EmpBasicInfo getFromIdInfo() {
		return fromIdInfo;
	}
	/**
	 * @param fromIdInfo the fromIdInfo to set
	 */
	public void setFromIdInfo(EmpBasicInfo fromIdInfo) {
		this.fromIdInfo = fromIdInfo;
	}
	/**
	 * @return the toId
	 */
	public String getToId() {
		return toId;
	}
	/**
	 * @param toId the toId to set
	 */
	public void setToId(String toId) {
		this.toId = toId;
	}
	/**
	 * @return the toIdInfo
	 */
	public EmpBasicInfo getToIdInfo() {
		return toIdInfo;
	}
	/**
	 * @param toIdInfo the toIdInfo to set
	 */
	public void setToIdInfo(EmpBasicInfo toIdInfo) {
		this.toIdInfo = toIdInfo;
	}
	/**
	 * @return the postId
	 */
	public String getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
	/**
	 * @return the postIdInfo
	 */
	public PostInfo getPostIdInfo() {
		return postIdInfo;
	}
	/**
	 * @param postIdInfo the postIdInfo to set
	 */
	public void setPostIdInfo(PostInfo postIdInfo) {
		this.postIdInfo = postIdInfo;
	}
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupIdInfo
	 */
	public Group getGroupIdInfo() {
		return groupIdInfo;
	}
	/**
	 * @param groupIdInfo the groupIdInfo to set
	 */
	public void setGroupIdInfo(Group groupIdInfo) {
		this.groupIdInfo = groupIdInfo;
	}
	/**
	 * @return the activityTime
	 */
	public Timestamp getActivityTime() {
		return activityTime;
	}
	/**
	 * @param activityTime the activityTime to set
	 */
	public void setActivityTime(Timestamp activityTime) {
		this.activityTime = activityTime;
	}
	/**
	 * @return the activityTimeStr
	 */
	public String getActivityTimeStr() {
		return activityTimeStr;
	}
	/**
	 * @param activityTimeStr the activityTimeStr to set
	 */
	public void setActivityTimeStr(String activityTimeStr) {
		this.activityTimeStr = activityTimeStr;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the flow
	 */
	public int getFlow() {
		return flow;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	
}
