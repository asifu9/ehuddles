package com.pro.emp.domain;

import java.sql.Timestamp;

public class ForumDiscussion {

	private String key;
	private String forumId;
	private String replierId;
	private EmpInfo replierIdInfo;
	private String body;
	private Timestamp time;
	private String timeStr;
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
	 * @return the replierId
	 */
	public String getReplierId() {
		return replierId;
	}
	/**
	 * @param replierId the replierId to set
	 */
	public void setReplierId(String replierId) {
		this.replierId = replierId;
	}
	/**
	 * @return the replierIdInfo
	 */
	public EmpInfo getReplierIdInfo() {
		return replierIdInfo;
	}
	/**
	 * @param replierIdInfo the replierIdInfo to set
	 */
	public void setReplierIdInfo(EmpInfo replierIdInfo) {
		this.replierIdInfo = replierIdInfo;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}
	/**
	 * @return the timeStr
	 */
	public String getTimeStr() {
		return timeStr;
	}
	/**
	 * @param timeStr the timeStr to set
	 */
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
	
	
	
}
