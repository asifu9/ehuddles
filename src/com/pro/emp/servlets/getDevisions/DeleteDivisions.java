package com.pro.emp.servlets.getDevisions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.DepartmentDAO;
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.domain.Department;
import com.pro.emp.domain.Designation;

/**
 * Servlet implementation class DeleteDivisions
 */
@WebServlet("/DeleteDivisions")
public class DeleteDivisions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDivisions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type="";
		String resp="";
		String key="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("key")!=null){
			key= request.getParameter("key");
		}
		
		if(request.getParameter("type")!=null){
			type= request.getParameter("type");
		}
		
		System.out.println(" photoId " + key);
		System.out.println(" postDesc " + type);
		Session con = CassandraDB.getCassConnection();
		try{
			if(type.equalsIgnoreCase("department")){
				
				DepartmentDAO dao=new DepartmentDAO();
				int result=dao.deleteDepartment(key.trim());
				if(result==1)
					resp="[{\"result\":\"SUCCESS\"}]";
				else
					resp="[{\"result\":\"FAILURE\"}]";
				System.out.println(" test ----- " + resp);
			}else if(type.equalsIgnoreCase("designation")){
				
				DesignationDAO dao=new DesignationDAO();
				int result=dao.deleteDesignation(key.trim());
				if(result==1)
					resp="[{\"result\":\"SUCCESS\"}]";
				else
					resp="[{\"result\":\"FAILURE\"}]";
				System.out.println(" test ----- " + resp);
			}
			
		
			
			
			
			
		}catch(Exception e){
			resp="[{\"result\":\"FAILURE\"}]";
		e.printStackTrace();	
		resp="";
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(resp);
        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
