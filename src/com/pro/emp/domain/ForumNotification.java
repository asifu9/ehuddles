package com.pro.emp.domain;

import java.sql.Timestamp;

public class ForumNotification {

	/**
	 * CREATE TABLE forumNotification ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "forumId text,"+
	    		   "postedByUserId text,"+
	    		   "postedTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int"+
	 * 
	 */
	
	private String key;
	private String forumId;
	private Forum forumIdInfo;
	private String postedByUserId;
	private EmpBasicInfo postedByUserIdInfo;
	private Timestamp postedTime;
	private String postedTimeStr;
	private int status;
	private String notifyingUserId;
	private EmpBasicInfo notifyingUserIdInfo;
	
	
	
	/**
	 * @return the notifyingUserId
	 */
	public String getNotifyingUserId() {
		return notifyingUserId;
	}
	/**
	 * @param notifyingUserId the notifyingUserId to set
	 */
	public void setNotifyingUserId(String notifyingUserId) {
		this.notifyingUserId = notifyingUserId;
	}
	/**
	 * @return the notifyingUserIdInfo
	 */
	public EmpBasicInfo getNotifyingUserIdInfo() {
		return notifyingUserIdInfo;
	}
	/**
	 * @param notifyingUserIdInfo the notifyingUserIdInfo to set
	 */
	public void setNotifyingUserIdInfo(EmpBasicInfo notifyingUserIdInfo) {
		this.notifyingUserIdInfo = notifyingUserIdInfo;
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
	 * @return the forumId
	 */
	public String getForumId() {
		return forumId;
	}
	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(String forumId) {
		this.forumId = forumId;
	}
	/**
	 * @return the forumIdInfo
	 */
	public Forum getForumIdInfo() {
		return forumIdInfo;
	}
	/**
	 * @param forumIdInfo the forumIdInfo to set
	 */
	public void setForumIdInfo(Forum forumIdInfo) {
		this.forumIdInfo = forumIdInfo;
	}
	/**
	 * @return the postedByUserId
	 */
	public String getPostedByUserId() {
		return postedByUserId;
	}
	/**
	 * @param postedByUserId the postedByUserId to set
	 */
	public void setPostedByUserId(String postedByUserId) {
		this.postedByUserId = postedByUserId;
	}
	/**
	 * @return the postedByUserIdInfo
	 */
	public EmpBasicInfo getPostedByUserIdInfo() {
		return postedByUserIdInfo;
	}
	/**
	 * @param postedByUserIdInfo the postedByUserIdInfo to set
	 */
	public void setPostedByUserIdInfo(EmpBasicInfo postedByUserIdInfo) {
		this.postedByUserIdInfo = postedByUserIdInfo;
	}
	/**
	 * @return the postedTime
	 */
	public Timestamp getPostedTime() {
		return postedTime;
	}
	/**
	 * @param postedTime the postedTime to set
	 */
	public void setPostedTime(Timestamp postedTime) {
		this.postedTime = postedTime;
	}
	/**
	 * @return the postedTimeStr
	 */
	public String getPostedTimeStr() {
		return postedTimeStr;
	}
	/**
	 * @param postedTimeStr the postedTimeStr to set
	 */
	public void setPostedTimeStr(String postedTimeStr) {
		this.postedTimeStr = postedTimeStr;
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
	
	
	
}
