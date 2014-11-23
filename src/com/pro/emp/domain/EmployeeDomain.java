package com.pro.emp.domain;
import java.util.Set;


public class EmployeeDomain {

	private EmpInfo empInfo;
	private EmpAdditionalInfo empAddInfo;
	private Set<EmpActivityInfo> empActInfo;
	private Set<EmpIdproofInfo> empIdProofs;
	private Set<EmpCompanyInfo> empComInfo;
	private Set<Education> education;
	private Set<Proffesional> proffesional;
	
	
	
	public Set<Proffesional> getProffesional() {
		return proffesional;
	}
	public void setProffesional(Set<Proffesional> proffesional) {
		this.proffesional = proffesional;
	}
	public Set<Education> getEducation() {
		return education;
	}
	public void setEducation(Set<Education> education) {
		this.education = education;
	}
	/**
	 * @return the empComInfo
	 */
	public Set<EmpCompanyInfo> getEmpComInfo() {
		return empComInfo;
	}
	/**
	 * @param empComInfo the empComInfo to set
	 */
	public void setEmpComInfo(Set<EmpCompanyInfo> empComInfo) {
		this.empComInfo = empComInfo;
	}
	public EmpInfo getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	public EmpAdditionalInfo getEmpAddInfo() {
		return empAddInfo;
	}
	public void setEmpAddInfo(EmpAdditionalInfo empAddInfo) {
		this.empAddInfo = empAddInfo;
	}
	public Set<EmpActivityInfo> getEmpActInfo() {
		return empActInfo;
	}
	public void setEmpActInfo(Set<EmpActivityInfo> empActInfo) {
		this.empActInfo = empActInfo;
	}
	public Set<EmpIdproofInfo> getEmpIdProofs() {
		return empIdProofs;
	}
	public void setEmpIdProofs(Set<EmpIdproofInfo> empIdProofs) {
		this.empIdProofs = empIdProofs;
	}
	
	
}
