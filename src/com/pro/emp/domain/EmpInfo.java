package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;






public class EmpInfo extends Domain{

	private String id;
	
	private HashMap<Integer, String> calStatus;
	
	
	
	
	public HashMap<Integer, String> getCalStatus() {
		return calStatus;
	}
	public void setCalStatus(HashMap<Integer, String> calStatus) {
		this.calStatus = calStatus;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmpName() {
		if(empName==null)
			return "";
		else			
			return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date theDate) {
		this.dob = theDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	private String empid;
	private String empName;
	private String passWord;
	private Date dob;
	private String department;
	private String designation;
	private Date doj;
	private String role;
	private String imagePath;
	private String loginName;
	private String emailid;
	private String strDepartment;
	private String strDesignation;
	private String mobile;
	private String gender;
	private String dobStr;
	private Date dobForSort;
	private boolean isTodayBirth;
	private PhotoInfo image;
	private Timestamp creationDate;
	
	
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	private PhotoInfo coverImage;
	private String coverPath;
	

	public PhotoInfo getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(PhotoInfo coverImage) {
		this.coverImage = coverImage;
	}
	public PhotoInfo getImage() {
		return image;
	}
	public void setImage(PhotoInfo image) {
		this.image = image;
	}
	public String getCoverPath() {
		return coverPath;
	}
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	public boolean isTodayBirth() {
		return isTodayBirth;
	}
	public void setTodayBirth(boolean isTodayBirth) {
		this.isTodayBirth = isTodayBirth;
	}
	public Date getDobForSort() {
		return dobForSort;
	}
	public void setDobForSort(Date dobForSort) {
		this.dobForSort = dobForSort;
	}
	public String getDobStr() {
		return dobStr;
	}
	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the strDepartment
	 */
	public String getStrDepartment() {
		return strDepartment;
	}
	/**
	 * @param strDepartment the strDepartment to set
	 */
	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}
	/**
	 * @return the strDesignation
	 */
	public String getStrDesignation() {
		return strDesignation;
	}
	/**
	 * @param strDesignation the strDesignation to set
	 */
	public void setStrDesignation(String strDesignation) {
		this.strDesignation = strDesignation;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
}
