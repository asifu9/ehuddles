package com.pro.emp.domain;

import java.util.Date;

public class Education {

	private String key;
	private String empId;
	private String collegeName;
	private String courseName;
	private Date from;
	private Date to;
	private String city;
	private String strFrom;
	private String strTo;
	
	
	public String getStrFrom() {
		return strFrom;
	}
	public void setStrFrom(String strFrom) {
		this.strFrom = strFrom;
	}
	public String getStrTo() {
		return strTo;
	}
	public void setStrTo(String strTo) {
		this.strTo = strTo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
	
}
