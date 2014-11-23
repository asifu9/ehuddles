package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpInfo;

/**
 * Servlet implementation class UpdateRole
 */
public class UpdateRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRole() {
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
		String value="";
		String empid="";
		if(request.getParameter("roleValue")!=null)
			value=request.getParameter("roleValue").toString();
		
		if(request.getParameter("empid")!=null)
			empid=request.getParameter("empid").toString();
		
		Session con =CassandraDB.getCassConnection();
		EmpInfo emp=new EmpInfo();
		emp.setEmpid(empid);
		emp.setRole(value);
		try {
			//EmpInfoDAO.updateRole(con, emp);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
	}

}
