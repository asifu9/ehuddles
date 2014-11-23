package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class TickerInfoDomain extends Domain {


	
	 private String key;
	 private EmpBasicInfo userId;
	 private EmpBasicInfo targetUserId;
	 private String message;
	 private Timestamp date;
	 private String dateTime;
	 private String time;
	 
	 
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public EmpBasicInfo getUserId() {
		return userId;
	}
	public void setUserId(EmpBasicInfo userId) {
		this.userId = userId;
	}
	public EmpBasicInfo getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(EmpBasicInfo targetUserId) {
		this.targetUserId = targetUserId;
	}

	 
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int code=0;
//		code+=	this.key.hashCode()+this.userId.hashCode()+this.targetUserId.hashCode();
//			code+=this.message.hashCode()+this.date.hashCode();
//		 
		return code;
	}
	 
	@Override
	 public boolean equals(Object obj) { 
         if (obj != null && getClass().equals(obj.getClass())) { 
        	 	TickerInfoDomain dd= (TickerInfoDomain)obj;
        	 return dd.getDate().toString().equals(this.getDate().toString()) && dd.getKey().equals(this.getKey()) && dd.getMessage().equals(this.getMessage())&& dd.getTargetUserId().equals(this.getTargetUserId()) && dd.getUserId().equals(this.getUserId()); 

        	 
          } else { return false; }
   }
	 
}
