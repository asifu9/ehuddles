package com.pro.emp.domain;

import com.pro.emp.PropertyReader;

public class CalBreakdownInfo {

	private int noOfWorkingDays;
	private int noOfSickDayLeaves;
	private int noOfWeeklyOff;
	private int noOfPaidLeaves;
	private int noOfUnpaidLeaves;
	private int noOfUnauthorizedLeaves;
	private int noOfNA;
	private int totalWorkingDays;
	private int totalAnnualLeavesTaken;
	private int totalSickLeavesTaken;
	
	
	public int getTotalAnnualLeavesTaken() {
		return totalAnnualLeavesTaken;
	}
	public void setTotalAnnualLeavesTaken(int totalAnnualLeavesTaken) {
		this.totalAnnualLeavesTaken = totalAnnualLeavesTaken;
	}
	public int getTotalSickLeavesTaken() {
		return totalSickLeavesTaken;
	}
	public void setTotalSickLeavesTaken(int totalSickLeavesTaken) {
		this.totalSickLeavesTaken = totalSickLeavesTaken;
	}
	public int getTotalAnnualLeaves() {
		return 0;// Integer.parseInt(PropertyReader.getValue("totalAnnualLeaves"));
	}

	public int getTotalSickLeaves() {
		return 0;// Integer.parseInt(PropertyReader.getValue("totalSickLeaves"));
	}

	public int getTotalWorkingDays() {
		return totalWorkingDays;
	}
	public void setTotalWorkingDays(int totalWorkingDays) {
		this.totalWorkingDays = totalWorkingDays;
	}
	public int getNoOfNA() {
		return noOfNA;
	}
	public void setNoOfNA(int noOfNA) {
		this.noOfNA = noOfNA;
	}
	public int getNoOfWorkingDays() {
		return noOfWorkingDays;
	}
	public void setNoOfWorkingDays(int noOfWorkingDays) {
		this.noOfWorkingDays = noOfWorkingDays;
	}
	public int getNoOfSickDayLeaves() {
		return noOfSickDayLeaves;
	}
	public void setNoOfSickDayLeaves(int noOfSickDayLeaves) {
		this.noOfSickDayLeaves = noOfSickDayLeaves;
	}
	public int getNoOfWeeklyOff() {
		return noOfWeeklyOff;
	}
	public void setNoOfWeeklyOff(int noOfWeeklyOff) {
		this.noOfWeeklyOff = noOfWeeklyOff;
	}
	public int getNoOfPaidLeaves() {
		return noOfPaidLeaves;
	}
	public void setNoOfPaidLeaves(int noOfPaidLeaves) {
		this.noOfPaidLeaves = noOfPaidLeaves;
	}
	public int getNoOfUnpaidLeaves() {
		return noOfUnpaidLeaves;
	}
	public void setNoOfUnpaidLeaves(int noOfUnpaidLeaves) {
		this.noOfUnpaidLeaves = noOfUnpaidLeaves;
	}
	public int getNoOfUnauthorizedLeaves() {
		return noOfUnauthorizedLeaves;
	}
	public void setNoOfUnauthorizedLeaves(int noOfUnauthorizedLeaves) {
		this.noOfUnauthorizedLeaves = noOfUnauthorizedLeaves;
	}
	
	
}
