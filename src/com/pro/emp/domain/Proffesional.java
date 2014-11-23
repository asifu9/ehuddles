package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Proffesional {

	private String key;
	private String empId;
	private String companyName;
	private String designation;
	private Date workedFrom;
	private Date workedTo;
	private String city;
	private String strWorkedFrom;
	private String strWorkedTo;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStrWorkedFrom() {
		return strWorkedFrom;
	}
	public void setStrWorkedFrom(String strWorkedFrom) {
		this.strWorkedFrom = strWorkedFrom;
	}
	public String getStrWorkedTo() {
		return strWorkedTo;
	}
	public void setStrWorkedTo(String strWorkedTo) {
		this.strWorkedTo = strWorkedTo;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getWorkedFrom() {
		return workedFrom;
	}
	public void setWorkedFrom(Date workedFrom) {
		this.workedFrom = workedFrom;
	}
	public Date getWorkedTo() {
		return workedTo;
	}
	public void setWorkedTo(Date workedTo) {
		this.workedTo = workedTo;
	}
	
	
}
