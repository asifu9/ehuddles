package com.pro.emp.domain;

import java.sql.Timestamp;

public class Forum {

	private String key;
	private String ownerId;
	private EmpBasicInfo ownerIdInfo;
	private String subject;
	private int forumType;
	private String body;
	private Timestamp time;
	private String timeStr;
	private int status;
	
	
	
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
	/**
	 * @return the ownerIdInfo
	 */
	public EmpBasicInfo getOwnerIdInfo() {
		return ownerIdInfo;
	}
	/**
	 * @param ownerIdInfo the ownerIdInfo to set
	 */
	public void setOwnerIdInfo(EmpBasicInfo ownerIdInfo) {
		this.ownerIdInfo = ownerIdInfo;
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
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the forumType
	 */
	public int getForumType() {
		return forumType;
	}
	/**
	 * @param forumType the forumType to set
	 */
	public void setForumType(int forumType) {
		this.forumType = forumType;
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
	
	
}
