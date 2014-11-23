package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class MessageLinks {
	private String key;
	private String mailId;
	private String fromId;
	private String toId;
	private int status;
	private Timestamp mailTime;
	private String mailTimeStr;
	private EmpBasicInfo fromIdInfo;
	private EmpBasicInfo toIdInfo;
	private MessageDetails mailIdInfo;
	private int newMessageCount;
	private String mailTimeStr2;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
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
	public Timestamp getMailTime() {
		return mailTime;
	}
	public void setMailTime(Timestamp mailTime) {
		this.mailTime = mailTime;
	}
	public String getMailTimeStr() {
		return mailTimeStr;
	}
	public void setMailTimeStr(String mailTimeStr) {
		this.mailTimeStr = mailTimeStr;
	}
	public EmpBasicInfo getFromIdInfo() {
		return fromIdInfo;
	}
	public void setFromIdInfo(EmpBasicInfo fromIdInfo) {
		this.fromIdInfo = fromIdInfo;
	}
	public EmpBasicInfo getToIdInfo() {
		return toIdInfo;
	}
	public void setToIdInfo(EmpBasicInfo toIdInfo) {
		this.toIdInfo = toIdInfo;
	}
	public MessageDetails getMailIdInfo() {
		return mailIdInfo;
	}
	public void setMailIdInfo(MessageDetails mailIdInfo) {
		this.mailIdInfo = mailIdInfo;
	}
	public int getNewMessageCount() {
		return newMessageCount;
	}
	public void setNewMessageCount(int newMessageCount) {
		this.newMessageCount = newMessageCount;
	}
	public String getMailTimeStr2() {
		return mailTimeStr2;
	}
	public void setMailTimeStr2(String mailTimeStr2) {
		this.mailTimeStr2 = mailTimeStr2;
	}
	
	
	
}
