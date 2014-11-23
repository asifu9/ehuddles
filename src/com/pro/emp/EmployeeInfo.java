package com.pro.emp;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Session;
import com.pro.emp.dao.EducationDAO;
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.dao.EmpAddInfoDAO;
import com.pro.emp.dao.EmpCompanyInfoDAO;
import com.pro.emp.dao.EmpIdProofDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.ProffesionalDAO;
import com.pro.emp.dao.RoleDAO;
import com.pro.emp.domain.CompanyInfo;
import com.pro.emp.domain.Education;
import com.pro.emp.domain.EmpActivityInfo;
import com.pro.emp.domain.EmpAdditionalInfo;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpIdproofInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.EmployeeDomain;
import com.pro.emp.domain.Proffesional;
import com.pro.emp.domain.Role;

public class EmployeeInfo {
	
	
	public static List<EmpInfo> getAllEmployees(){
		Session con =CassandraDB.getCassConnection();
		List<EmpInfo> ls= EmpInfoDAO.getAllEmployeesInfo(con);
		//CassandraDB.freeConnection(con);
		return ls;
	}
//	public static List<EmpBasicInfo> getAllColleageEmployeesInfo(){
//		Session con =CassandraDB.getCassConnection();
//		List<EmpBasicInfo> ls= EmpInfoDAO.getAllColleageEmployeesInfo(con);
//		//CassandraDB.freeConnection(con);
//		return ls;
//	}
//	public static List<EmpInfo> getAllEmployeesCal(String year){
//		Session con =CassandraDB.getCassConnection();
//		List<EmpInfo> ls= EmpInfoDAO.getAllEmployeesInfoCal(year,con);
//		//CassandraDB.freeConnection(con);
//		return ls;
//	}
	
//	public static HashMap<String, String> getAllCompanyInfo(){
//		Session c= CassandraDB.getCassConnection();
//		Set<CompanyInfo> set= EmpCompanyInfoDAO.getAllCompanyInfo(c);
//		//System.out.println(" Set size " + set.size());
//		//CassandraDB.freeConnection(c);
//		HashMap<String, String> mapCom =new HashMap<String, String>();
//		Iterator<CompanyInfo> itr= set.iterator();
//		while(itr.hasNext()){
//			CompanyInfo ddd=itr.next();
//			mapCom.put(ddd.getCompanyName(),ddd.getKey());
//		}
//		//System.out.println(" data is " + mapCom);
//		return mapCom;
//	}
	
	public static EmpInfo getEmployeeById(String id){
		
		
		EmpInfo emp= null;
		
			emp= EmpInfoDAO.getEmpById( id);
		
		
		return emp;
	}
	
	public static HashMap<String,Boolean> getRolesForEmpId(String id){
		Session con= CassandraDB.getCassConnection();
	//	List<Role> list= RoleDAO.getAllRoleTypesByDepartment(con, id);
		HashMap<String,Boolean> role=new HashMap<String, Boolean>();
	//	for(Role r:list){
	//		role.put(r.getRole(), r.isActive());
	///	}
	//	//CassandraDB.freeConnection(con);
		return role;
		
	}
	
	public static HashMap<String,Boolean> getRolesForEmpIdFull(String id){
		Session con= CassandraDB.getCassConnection();
		List<Role> list= RoleDAO.getAllRoleTypesByDepartmentFull(con, id);
		HashMap<String,Boolean> role=new HashMap<String, Boolean>();
		for(Role r:list){
			role.put(r.getRole(), r.isActive());
		}
		//CassandraDB.freeConnection(con);
		return role;
		
	}
	public static HashMap<String, Object> getRolesForEmpIdKeyFuuul(String id){
		Session con= CassandraDB.getCassConnection();
		HashMap<String, Object> obj=new HashMap<String, Object>();
		List<Role> list= RoleDAO.getAllRoleTypesByDepartmentFull(con, id);
		HashMap<String,String> role=new HashMap<String, String>();
		HashMap<String,Boolean> roleMap=new HashMap<String, Boolean>();
		for(Role r:list){
			role.put(r.getRole(),r.getRoleId());
		}
		for(Role r:list){
			roleMap.put(r.getRole(),r.isActive());
		}
		obj.put("IDINFO",role);
		obj.put("IDBOOL",roleMap);
		CassandraDB.freeConnection(con);
		return obj;
		
	}
	public static HashMap<String,String> getRolesForEmpIdKey(String id){
		Session con= CassandraDB.getCassConnection();
		List<Role> list= RoleDAO.getAllRoleTypesByDepartment(con, id);
		HashMap<String,String> role=new HashMap<String, String>();
		for(Role r:list){
			role.put(r.getRole(),r.getRoleId());
		}
		CassandraDB.freeConnection(con);
		return role;
		
	}
//	public static Set<EmpActivityInfo> getEmployeeActivityInfo(String id,Session con){
//		Set<EmpActivityInfo> activities=new HashSet<EmpActivityInfo>();
//		if(con!=null){
//			activities= EmpActInfoDAO.getEmpActivityInfoById(con, id);
//		}else{
//			con =CassandraDB.getCassConnection();
//			activities= EmpActInfoDAO.getEmpActivityInfoById(con, id);
//			CassandraDB.freeConnection(con);
//		}
//		
//		return activities;
//	
//	}
	
//	public static EmpAdditionalInfo getEmployeeAdditionalInfo(String id,Session con){
//		
//		 EmpAdditionalInfo emp= null;
//		 if(con!=null){
//			 emp= EmpAddInfoDAO.getEmpAdditionaDetailsById(con,id);
//		 }else{
//			 con =CassandraDB.getCassConnection();
//			 emp= EmpAddInfoDAO.getEmpAdditionaDetailsById(con,id);
//			 CassandraDB.freeConnection(con);
//		 }
//		
//		return emp;
//	
//	}
	
//	public static Set<EmpIdproofInfo> getEmployeeIdProofInfo(String id,Session con){
//		
//		Set<EmpIdproofInfo> set=new HashSet<EmpIdproofInfo>();
//		if(con!=null){
//			set= EmpIdProofDAO.getEmpIdproofInfoById(con, id);
//		}else{
//			 con =CassandraDB.getCassConnection();
//			set= EmpIdProofDAO.getEmpIdproofInfoById(con, id);
//			CassandraDB.freeConnection(con);
//		}
//		
//		return set;
//	
//	}
	
//	public static Set<EmpCompanyInfo> getEmpCompanyInfo(String id,Session con){
//		
//		Set<EmpCompanyInfo> set= new HashSet<EmpCompanyInfo>();
//		if(con!=null){
//			set= EmpCompanyInfoDAO.getEmpCompanyInfo(con, id);
//		}else{
//			con =CassandraDB.getCassConnection();
//			set= EmpCompanyInfoDAO.getEmpCompanyInfo(con, id);
//			CassandraDB.freeConnection(con);
//		}
//		return set;
//	
//	}
	
	public static EmployeeDomain getCompleteEmpInfo(String id){
		Session con =CassandraDB.getCassConnection();
		EmployeeDomain empDomain=new EmployeeDomain();
	//	empDomain.setEmpActInfo(getEmployeeActivityInfo(id,con));
	//	empDomain.setEmpAddInfo(getEmployeeAdditionalInfo(id,con));
	//	empDomain.setEmpIdProofs(getEmployeeIdProofInfo(id,con));
		empDomain.setEmpInfo(getEmployeeById(id));
	//	empDomain.setEmpComInfo(getEmpCompanyInfo(empDomain.getEmpInfo().getId(),con));
		empDomain.setEducation(getEducationDetails(id, con));
		empDomain.setProffesional(getProffesionalDetails(id, con));
		CassandraDB.freeConnection(con);
		return empDomain;
		
	
	}
	
	public static Set<Proffesional> getProffesionalDetails(String id, Session con){
		return ProffesionalDAO.getAllProffesionalByEmpId(con, id);
	}
	
	
	public static Set<Education> getEducationDetails(String id, Session con){
		return EducationDAO.getEmpIdproofInfoById(con, id);
	}
	
	public static void setToSession(String empid,HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("editUser", empid);
	}
	

	public static String getImagePath(String empid,HttpServletRequest reqeust){
		EmpInfo emp=getEmployeeById(empid);
		if(emp==null){
			
			// try to get it from session
			HttpSession session = reqeust.getSession(true);
			if(session.getAttribute("editUser")!=null) {
				empid=session.getAttribute("editUser").toString();
			}else{
				//System.out.println(" session is null");
			}
		}
		
		 emp=getEmployeeById(empid);
		
		return emp.getImagePath();
	}
}
