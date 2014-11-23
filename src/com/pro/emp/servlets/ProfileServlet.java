package com.pro.emp.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.EmpAddInfoDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.RoleDAO;
import com.pro.emp.dao.RoleTypesDAO;
import com.pro.emp.domain.EmpAdditionalInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.RoleTypes;
import com.pro.emp.util.Constant;
import com.pro.emp.util.PasswordService;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer= response.getWriter();
		
		String empId="";
		String empName="";
		String dob="";
		String department="";
		String designation="";
		String doj="";
		String password="";
		String emailid="";
		String loginName="";
		String url="";
		String mobile="";
		String gender="";
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
		
		if(request.getParameter("txtEmailId")!=null)
			emailid=request.getParameter("txtEmailId").toString();
		
		if(request.getParameter("txtLoginName")!=null)
			loginName=request.getParameter("txtLoginName").toString();

		if(request.getParameter("txtMobile")!=null)
			mobile=request.getParameter("txtMobile").toString();
		if(request.getParameter("txtGender")!=null)
			gender=request.getParameter("txtGender").toString();

		// for additional info section
		/*String address="";
		String city="";
		String state="";
		String telephone="";
		String mobile="";
		String pincode="";
		String refContact="";
		String country="";
		
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
			refContact =request.getParameter("refContact").toString().trim();*/
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Session con =CassandraDB.getCassConnection();
		//EmpAdditionalInfo empAdd=new EmpAdditionalInfo();
		EmpInfo emp=new EmpInfo();
		try {
			
			
			emp.setDepartment(department);
			emp.setDesignation(designation);
			//System.out.println(" emp id " + empId);
			emp.setEmpid(empId);
			//emp.setEmpName(empName);
			PasswordService ps=new PasswordService();
			emp.setPassWord(ps.encrypt(password));
			emp.setGender(gender);
			emp.setMobile(mobile);
			emp.setEmpName(empName);
			//emp.setEmailid(emailid);
			emp.setLoginName(loginName);
			
			// creating additional info
			
			/*empAdd.setAddress(address);
			empAdd.setState(state);
			empAdd.setCity(city);
			empAdd.setCountry(country);
			empAdd.setPincode(pincode);
			empAdd.setMobile(mobile);
			empAdd.setTelephone(telephone);
			empAdd.setEmpId(empId);
			empAdd.setRefContact(refContact);*/
		}catch(Exception e){
			e.printStackTrace();
		}
		 if(EmpInfoDAO.isLoginNameExists(con, loginName)){
			url="/empidExist.jsp?empid="+empId+"&message=Login name already exists.";
			HttpSession session = request.getSession(true);
			session.setAttribute("createEmpInfo", emp);
			writer.println("Login name already exists.");
		//	session.setAttribute("createEmpAddInfo", empAdd);
		} else{
			try {
				department="Data Entry";
//				Date theDate = (Date) dateFormat.parse(dob); 
//				emp.setDob(theDate);
//				theDate = (Date) dateFormat.parse(doj); 
//				emp.setDoj(theDate);
				EmpInfoDAO.createNewEmpInfo(emp, con);
				List<RoleTypes> roleTypes= RoleTypesDAO.getAllRoleTypesByDepartment(con, "Data entry");
				
				HashMap<String, Boolean> rolesMap=new HashMap<String, Boolean>();
				
				//System.out.println(" roleTypes " + roleTypes );
				//System.out.println(" roleTypes " + roleTypes.size() );
				if(department.equalsIgnoreCase(Constant.DEP_DATAENTRY)){
					for(RoleTypes r:roleTypes){
						
						
						
						if(r.getName().equalsIgnoreCase(Constant.CALENDAR_VIEW))
							rolesMap.put(r.getName(), true);
						else if(r.getName().equalsIgnoreCase(Constant.PROFILE_VIEW))
							rolesMap.put(r.getName(), true);
						else if(r.getName().equalsIgnoreCase(Constant.PROFILE_EDIT))
							rolesMap.put(r.getName(), true);
						else if(r.getName().equalsIgnoreCase(Constant.CALENDAR_EDIT))
							rolesMap.put(r.getName(), true);
						else if(r.getName().equalsIgnoreCase(Constant.DEP_ADMIN))
							rolesMap.put(r.getName(), false);
						else if(r.getName().equalsIgnoreCase(Constant.PHOTO_VIEW))
							rolesMap.put(r.getName(),true);
						else if(r.getName().equalsIgnoreCase(Constant.PHOTO_EDIT))
							rolesMap.put(r.getName(),true);
						else
							rolesMap.put(r.getName(),false);			
						
					}
				}
				
				RoleDAO.createRoles(emp, con, rolesMap);
				
				
				//EmpAddInfoDAO.createEmpAdditionalInfo(empAdd, empId, con);
				//url="/loginContinue.jsp?empid="+empId;
				writer.println("<span style=\"color:green; \">New employee information successfully created.</span>");
			}
			 catch(Exception e){
				System.out.println(" exception " + e.toString());
				e.printStackTrace();
			}
		}
		 CassandraDB.freeConnection(con);
		//RequestDispatcher rd =request.getServletContext().getRequestDispatcher(url);
		//rd.forward(request, response);
		writer.flush();
		writer.close();
	}
	
	
	

}
