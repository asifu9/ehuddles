package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.RoleDAO;
import com.pro.emp.domain.EmpInfo;

/**
 * Servlet implementation class RoleManageUpdateServlet
 */
@WebServlet("/RoleManageUpdateServlet")
public class RoleManageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManageUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key="";
		String value="";
		
		if(request.getParameter("id")!=null){
			key= request.getParameter("id");
		}
	
		if(request.getParameter("value")!=null){
			value= request.getParameter("value");
		}
		//System.out.println(" here i am ---------------------" + key + " : " + role + " : " + value);
		Session con = CassandraDB.getCassConnection();
		try {
				boolean isActive = (value.equalsIgnoreCase("1")?true:false);
				RoleDAO.updateRoleById(key, isActive,con);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
