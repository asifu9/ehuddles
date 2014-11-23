package com.pro.friends.domain;

import java.sql.Timestamp;

public class Friends {

	private String key;
	private String fromId;
	private String toId;
	private int status;
	private Timestamp requestedTime;
	private Timestamp responsedTime;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getRequestedTime() {
		return requestedTime;
	}
	public void setRequestedTime(Timestamp requestedTime) {
		this.requestedTime = requestedTime;
	}
	public Timestamp getResponsedTime() {
		return responsedTime;
	}
	public void setResponsedTime(Timestamp responsedTime) {
		this.responsedTime = responsedTime;
	}
	
	
	
}
