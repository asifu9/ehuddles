package com.pro.emp.domain;

import java.util.Date;

public class LeavesManagement {

	private String id;
	private String empid;
	private String year;
	private int paidLeavesTaken;
	private int sickLeavesTaken;
	private Date doj;
	private float carryOverLeaves;
	private String endyear;
	private String month;
	private String endMonth;
	private float totalPaidLeaves;
	
	
	
	public float getTotalPaidLeaves() {
		return totalPaidLeaves;
	}
	public void setTotalPaidLeaves(float totalPaidLeaves) {
		this.totalPaidLeaves = totalPaidLeaves;
	}
	public String getEndyear() {
		return endyear;
	}
	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getPaidLeavesTaken() {
		return paidLeavesTaken;
	}
	public void setPaidLeavesTaken(int paidLeavesTaken) {
		this.paidLeavesTaken = paidLeavesTaken;
	}
	public int getSickLeavesTaken() {
		return sickLeavesTaken;
	}
	public void setSickLeavesTaken(int sickLeavesTaken) {
		this.sickLeavesTaken = sickLeavesTaken;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public float getCarryOverLeaves() {
		return carryOverLeaves;
	}
	public void setCarryOverLeaves(float carryOverLeaves) {
		this.carryOverLeaves = carryOverLeaves;
	}
	
	
}
