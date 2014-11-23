package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.Designation;

/**
 * Servlet implementation class DepartmentChanged
 */
@WebServlet("/DepartmentChanged")
public class DepartmentChanged extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public DepartmentChanged() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id="";
		if(request.getParameter("id")!=null){
			id=request.getParameter("id");
		}
		StringBuffer buff= new StringBuffer();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		Session con =CassandraDB.getCassConnection();
		Set<Designation> set=new HashSet<Designation>();
		try {
			if(id!=null && !id.trim().equals("")){
				
				if(id.contains("~~~")){
					String[] split= id.split("~~~");
					set= DesignationDAO.getAllDesgByDept(con, Integer.parseInt(split[0]));
					for(Designation desg:set){
						if(Integer.parseInt(split[1])==desg.getId())
							buff.append("<option value=\""+desg.getId()+"\" selected>"+desg.getName() + " </option>");
						else
							buff.append("<option value=\""+desg.getId()+"\">"+desg.getName() + " </option>");
					}
				}else{
				
					set= DesignationDAO.getAllDesgByDept(con, Integer.parseInt(id));
					for(Designation desg:set){
						buff.append("<option value=\""+desg.getId()+"\">"+desg.getName() + " </option>");
					}
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		//System.out.println(" AJAX is : "+buff.toString());
		writer.println(buff.toString());
        writer.flush();
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id="";
		if(request.getParameter("id")!=null){
			id=request.getParameter("id");
		}
		
		StringBuffer buff= new StringBuffer();
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		Session con =CassandraDB.getCassConnection();
		Set<Designation> set=new HashSet<Designation>();
		try {
			if(id!=null && !id.trim().equals("")){
				if(id.contains("~~~")){
					String[] split= id.split("~~~");
					set= DesignationDAO.getAllDesgByDept(con, Integer.parseInt(split[0]));
					for(Designation desg:set){
						if(Integer.parseInt(split[1])==desg.getId())
							buff.append("<option value=\""+desg.getId()+"\" selected>"+desg.getName() + " </option>");
						else
							buff.append("<option value=\""+desg.getId()+"\">"+desg.getName() + " </option>");
					}
				}else{
					set= DesignationDAO.getAllDesgByDept(con, Integer.parseInt(id));
					for(Designation desg:set){
						buff.append("<option value=\""+desg.getId()+"\">"+desg.getName() + " </option>");
					}
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(buff.toString());
        writer.flush();
	}

*/}
 