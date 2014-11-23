package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class MessageDetails {

	private String key;
	private String subject;
	private String message;
	private Timestamp time;
	private String timeStr;
	private int indicator;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public int getIndicator() {
		return indicator;
	}
	public void setIndicator(int indicator) {
		this.indicator = indicator;
	}
	
	
}
