package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.Session_control;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpAddInfoDAO;
import com.pro.emp.dao.EmpCompanyInfoDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.CompanyInfo;
import com.pro.emp.domain.EmpAdditionalInfo;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.util.PasswordService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class ProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer =response.getWriter();
		String empId="";
		String empName="";
		String dob="";
		String department="";
		String designation="";
		String doj="";
		String password="";
		String loginName="";
		String emailId="";
		String processPart="";
		String creationDate="";
		
		if(request.getParameter("creationDate")!=null)
			creationDate=request.getParameter("creationDate").toString();
		

		
		if(request.getParameter("txtEmpName")!=null)
			empName=request.getParameter("txtEmpName").toString();
		
		if(request.getParameter("txtEmpId")!=null)
			empId=request.getParameter("txtEmpId").toString();
		
		if(request.getParameter("txtDOB")!=null)
			dob=request.getParameter("txtDOB").toString();
		
		if(request.getParameter("txtDepartment")!=null)
			department=request.getParameter("txtDepartment").toString();
		
		if(request.getParameter("txtDesignation")!=null)
			designation=request.getParameter("txtDesignation").toString();
		
		if(request.getParameter("txtDOJ")!=null)
			doj=request.getParameter("txtDOJ").toString();
		
		if(request.getParameter("txtPassword")!=null)
			password=request.getParameter("txtPassword").toString();

		if(request.getParameter("txtLoginName")!=null)
			loginName=request.getParameter("txtLoginName").toString();
		
		if(request.getParameter("txtEmail")!=null)
			emailId=request.getParameter("txtEmail").toString();
		
		if(request.getParameter("processPart")!=null)
			processPart=request.getParameter("processPart").toString();
		
		// for additional info section
		String address="";
		String city="";
		String state="";
		String telephone="";
		String mobile="";
		String pincode="";
		String refContact="";
		String country="";
		String key="";
		if(request.getParameter("key")!=null)
			key= request.getParameter("key").toString().trim();
		
		if(request.getParameter("txtAddress")!=null)
			address= request.getParameter("txtAddress").toString().trim();
		
		if(request.getParameter("txtCity")!=null)
			city= request.getParameter("txtCity").toString().trim();
		
		if(request.getParameter("txtState")!=null)
			state= request.getParameter("txtState").toString().trim();
		
		if(request.getParameter("txtCountry")!=null)
			country= request.getParameter("txtCountry").toString().trim();
		
		if(request.getParameter("txtPincode")!=null)
			pincode= request.getParameter("txtPincode").toString().trim();
		
		if(request.getParameter("txtTelephone")!=null)
			telephone= request.getParameter("txtTelephone").toString().trim();
		
		if(request.getParameter("txtMobile")!=null)
			mobile= request.getParameter("txtMobile").toString().trim();
		
		if(request.getParameter("refContact")!=null)
			refContact =request.getParameter("refContact").toString().trim();

		Date ss= Util.convertStringToDate(creationDate);
		Timestamp ts=Util.convertDateToTimestamp(ss);
		RequestDispatcher rd=null;
		Session con =CassandraDB.getCassConnection();
		String url="";
		try {
			
			
			if(processPart.equalsIgnoreCase("empinfo")){
				EmpInfo emp=new EmpInfo();
			//	emp.setDepartment(Integer.parseInt(department));
			//	emp.setDesignation(Integer.parseInt(designation));
				emp.setEmpid(empId);
				emp.setEmpName(empName);
				PasswordService ps=new PasswordService();
                emp.setDepartment(department);
                emp.setDesignation(designation);
				emp.setPassWord(ps.encrypt(password));
				emp.setLoginName(loginName);
				emp.setEmailid(emailId);
				emp.setId(key);
				Date theDate = Util.getFormatedDate(dob); 
				emp.setDob(theDate);
				theDate = Util.getFormatedDate(doj); 
				emp.setDoj(theDate);
				emp.setCreationDate(ts);
				
				if(EmpInfoDAO.isLoginNameExistsInUpdate(con, loginName,key)){
					//System.out.println(" already exist ");
					writer.println("<span style=\"color:red;font-weight:bold; \" >Login name already exists.</span>");
				//	session.setAttribute("createEmpAddInfo", empAdd);
				} else {
					//System.out.println(" already exist - going to update");
				// code here			
					EmpInfoDAO.update(con, emp);
					EmpInfo emps= EmpInfoDAO.getEmpById( emp.getId());
					//System.out.println(" values are " + rairCom + ":"+acsCom+":"+allfaxCom+":"+qualcommCom);
				//	EmpCompanyInfoDAO.deleteAllCompanyForId(con, emps.getId());
					
					
				//	EmpCompanyInfo d=new EmpCompanyInfo();
			//		Set<CompanyInfo> comList= EmpCompanyInfoDAO.getAllCompanyInfo(con);
				//	HashMap<String, String> mapCom =new HashMap<String, String>();
//					Iterator<CompanyInfo> itr= comList.iterator();
//					while(itr.hasNext()){
//						CompanyInfo ddd=itr.next();
//						mapCom.put(ddd.getCompanyName(),ddd.getKey());
//					}
//					if(rairCom.equals("true")){
//						d.setCompanyId(mapCom.get("RAIR"));
//						d.setEmpId(emps.getId());
//						EmpCompanyInfoDAO.createCompanyInfo(d, emps.getId(), con);
//					}
//					if(acsCom.equals("true")){
//						d.setCompanyId(mapCom.get("ACS"));
//						d.setEmpId(emps.getId());
//						EmpCompanyInfoDAO.createCompanyInfo(d, emps.getId(), con);
//					}
//					if(qualcommCom.equals("true")){
//						d.setCompanyId(mapCom.get("Qualcomm"));
//						d.setEmpId(emps.getId());
//						EmpCompanyInfoDAO.createCompanyInfo(d, emps.getId(), con);
//					}
//					if(allfaxCom.equals("true")){
//						d.setCompanyId(mapCom.get("AllFax"));
//						d.setEmpId(emps.getId());
//						EmpCompanyInfoDAO.createCompanyInfo(d, emps.getId(), con);
//					}
					
					//Session_control.setProfileUpdate(request);
					writer.println("<span style=\"color:green;font-weight:bold; \" >Successfully Updated.</span>");
				}
				
				EmpInfo ee=Session_control.getSession(request);
				if(ee.getId().toString().equalsIgnoreCase(key)){
					Session_control.setSession_(request,  key);
				}
			}else if(processPart.equalsIgnoreCase("address")){
			// creating additional info
				// check whether any address exists or not
				
				System.out.println(" address part ");
				//System.out.println("In Address update empId in edit servet " + key);
				EmpAdditionalInfo empAddInfo=EmpAddInfoDAO.getEmpAdditionaDetailsById(con, key);
				//System.out.println(" EMp idsss " + empAddInfo.getEmpId());
				EmpAdditionalInfo empAdd=new EmpAdditionalInfo();
				empAdd.setAddress(address);
				empAdd.setState(state);
				empAdd.setCity(city);
				empAdd.setCountry(country);
				empAdd.setPincode(pincode);
				empAdd.setMobile(mobile);
				empAdd.setTelephone(telephone);
				empAdd.setId(key);
				empAdd.setEmpId(empId);
				empAdd.setRefContact(refContact);
				
				//if(empAddInfo!=null)
				//	EmpAddInfoDAO.update(con, empAdd);
				//else
					EmpAddInfoDAO.createEmpAdditionalInfo(empAdd, key, con);
				writer.println("<span style=\"color:green;font-weight:bold; \" >Successfully Updated.</span>");
				EmpInfo ee=Session_control.getSession(request);
				if(ee.getEmpid().equalsIgnoreCase(empId)){
					Session_control.setSession_(request,  key);
				}
			}
			
			//TODO dfdsfdsf
			//EmpIdProofDAO.update(con, idProofStrings)(idProofStrings, empId, con);
			//url="/editProfile.jsp?editEmpId="+empId+"&message=Successfully Updated";
			writer.flush();
			writer.close();
			
		}catch(Exception e){
			//System.out.println(" exception " + e.toString());
			//url="/editProfile.jsp?editEmpId="+empId+"&message=Failed to update";
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		
		//rd =request.getServletContext().getRequestDispatcher(url);
		//rd.forward(request, response);
	}
	

}
