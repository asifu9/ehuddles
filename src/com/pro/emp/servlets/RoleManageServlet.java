package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.RoleDAO;

/**
 * Servlet implementation class RoleManageServlet
 */
@WebServlet("/RoleManageServlet")
public class RoleManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type="";
		boolean option=false;
		int empId=0;
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		if(request.getParameter("option")!=null){
			option=Boolean.parseBoolean(request.getParameter("option"));
		}
		if(request.getParameter("employeeId")!=null){
			empId=Integer.parseInt(request.getParameter("employeeId"));
		}
		
		//System.out.println(" type = "+type + " option = "+option);
	
		//ConnectionPool c=ConnectionPool.getInstance();
		Session con= CassandraDB.getCassConnection();
		try {
			RoleDAO.updateUIRoles(type, option, con);
			
		}
		catch(Exception ex){
			
			
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		RequestDispatcher rd= request.getRequestDispatcher("/RoleManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String type="";
		boolean option=false;
		int empId=0;
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		if(request.getParameter("option")!=null){
			option=Boolean.parseBoolean(request.getParameter("option"));
		}
		if(request.getParameter("employeeId")!=null){
			empId=Integer.parseInt(request.getParameter("employeeId"));
		}
		
		//System.out.println(" type = "+type + " option = "+option);
	
		Session con= CassandraDB.getCassConnection();
		try {
			RoleDAO.updateUIRoles(type, option, con);
			
		}
		catch(Exception ex){
			
			
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		RequestDispatcher rd= request.getRequestDispatcher("/RoleManage.jsp");
		rd.forward(request, response);
	}

}
