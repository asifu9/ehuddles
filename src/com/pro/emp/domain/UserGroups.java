package com.pro.emp.domain;

import java.sql.Timestamp;

public class UserGroups {

	
	/**
	 * "CREATE TABLE userGroups ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "groupId text,"+
	    		   "userId text,joinedOn timestamp,"+
	    		   "indicator int) ";
	 */
	
	private String key;
	private String groupId;
	private String userId;
	private Timestamp joinedOn;
	private Group groupIdInfo;
	private String joinedOnStr;
	private EmpBasicInfo userIdInfo;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getJoinedOn() {
		return joinedOn;
	}
	public void setJoinedOn(Timestamp joinedOn) {
		this.joinedOn = joinedOn;
	}
	public Group getGroupIdInfo() {
		return groupIdInfo;
	}
	public void setGroupIdInfo(Group groupIdInfo) {
		this.groupIdInfo = groupIdInfo;
	}
	public String getJoinedOnStr() {
		return joinedOnStr;
	}
	public void setJoinedOnStr(String joinedOnStr) {
		this.joinedOnStr = joinedOnStr;
	}
	public EmpBasicInfo getUserIdInfo() {
		return userIdInfo;
	}
	public void setUserIdInfo(EmpBasicInfo userIdInfo) {
		this.userIdInfo = userIdInfo;
	}
	
	
}
