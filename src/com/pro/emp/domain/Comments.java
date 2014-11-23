package com.pro.emp.domain;

import java.sql.Timestamp;



public class Comments {
	private String id;
	private String description;
	private Timestamp time;
	private String commentebBy;
	private int day;
	private int year;
	private String empid;
	private int month;
	
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getCommentebBy() {
		return commentebBy;
	}
	public void setCommentebBy(String commentebBy) {
		this.commentebBy = commentebBy;
	}
	
}
