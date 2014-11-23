package com.pro.emp.domain;

import java.util.Date;

public class LinkDomain {
/**
 *   KEY text,\n"
		        + "    empId text,\n"
		        + "    url text,\n"
		        + "    userName text,\n"
		        + "    password text,\n"
		        + "    orderNum int,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY )\n"
		        
00 */
	private String name;
	private String key;
	private String empId;
	private String url;
	private String userName;
	private String password;
	private int orderNum;
	private Date creationDate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
