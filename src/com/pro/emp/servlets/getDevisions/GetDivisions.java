package com.pro.emp.servlets.getDevisions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.DepartmentDAO;
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.domain.Department;
import com.pro.emp.domain.Designation;

/**
 * Servlet implementation class ManageDepartment
 */
@WebServlet("/ManageDepartment")
public class GetDivisions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDivisions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String type="";
		String resp1 ="";
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		if(type.equalsIgnoreCase("department")){
			Set<Department> depSet=new HashSet<Department>();
			Session con =CassandraDB.getCassConnection();
			try {
				depSet= new DepartmentDAO().getAllDepartment();
				ObjectMapper mapper = new ObjectMapper();
				Writer strWriter = new StringWriter();
				mapper.writeValue(strWriter, depSet);
				resp1 = strWriter.toString();
				System.out.println(" notification resp " + resp1);
					
				
			}catch(Exception ex){
				
			}
		}else if(type.equalsIgnoreCase("designation")){
			Set<Designation> depSet=new HashSet<Designation>();
			try {
				DesignationDAO dao=new DesignationDAO();
				depSet= dao.getAllDesg();
				ObjectMapper mapper = new ObjectMapper();
				Writer strWriter = new StringWriter();
				mapper.writeValue(strWriter, depSet);
				resp1 = strWriter.toString();
				System.out.println(" notification resp " + resp1);
					
				
			}catch(Exception ex){
				
			}
		}
		writer.println(resp1);
        writer.flush();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
