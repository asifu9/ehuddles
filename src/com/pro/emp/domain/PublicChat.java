package com.pro.emp.domain;

import java.sql.Timestamp;

public class PublicChat extends Domain{

	private String key;
	private String postedFromId;
	private String postedInfo;
	private Timestamp postedTime;
	private String postedTimeStr;
	private EmpBasicInfo info;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPostedFromId() {
		return postedFromId;
	}
	public void setPostedFromId(String postedFromId) {
		this.postedFromId = postedFromId;
	}
	public String getPostedInfo() {
		return postedInfo;
	}
	public void setPostedInfo(String postedInfo) {
		this.postedInfo = postedInfo;
	}
	public Timestamp getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(Timestamp postedTime) {
		this.postedTime = postedTime;
	}
	public String getPostedTimeStr() {
		return postedTimeStr;
	}
	public void setPostedTimeStr(String postedTimeStr) {
		this.postedTimeStr = postedTimeStr;
	}
	public EmpBasicInfo getInfo() {
		return info;
	}
	public void setInfo(EmpBasicInfo info) {
		this.info = info;
	}
	
	
	
}
