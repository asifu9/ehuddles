package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class Follow {

	private String key;
	private String followBy ;
	private String followTo ;
	private Timestamp followStartTime ;
	private String timeStr;
	private EmpBasicInfo followByUserInfo;
	private EmpBasicInfo followToUserInfo;
	
	public EmpBasicInfo getFollowByUserInfo() {
		return followByUserInfo;
	}
	public void setFollowByUserInfo(EmpBasicInfo followByUserInfo) {
		this.followByUserInfo = followByUserInfo;
	}
	public EmpBasicInfo getFollowToUserInfo() {
		return followToUserInfo;
	}
	public void setFollowToUserInfo(EmpBasicInfo followToUserInfo) {
		this.followToUserInfo = followToUserInfo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFollowBy() {
		return followBy;
	}
	public void setFollowBy(String followBy) {
		this.followBy = followBy;
	}
	public String getFollowTo() {
		return followTo;
	}
	public void setFollowTo(String followTo) {
		this.followTo = followTo;
	}
	public Timestamp getFollowStartTime() {
		return followStartTime;
	}
	public void setFollowStartTime(Timestamp followStartTime) {
		this.followStartTime = followStartTime;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
	
	
}
