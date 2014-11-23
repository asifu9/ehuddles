package com.pro.emp.servlets.getDevisions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.DepartmentDAO;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.Department;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.PostInfo;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateDepartment
 */
@WebServlet("/CreateDepartment")
public class CreateDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depName="";
		String resp="";
		String orderDep="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("orderDep")!=null){
			orderDep= request.getParameter("orderDep");
		}
		
		if(request.getParameter("depName")!=null){
			depName= request.getParameter("depName");
		}
		
		System.out.println(" photoId " + orderDep);
		System.out.println(" postDesc " + depName);
		Session con = CassandraDB.getCassConnection();
		try{
			Department d=new Department();
			d.setName(depName);
			d.setOrderDep(Integer.parseInt(orderDep));
			DepartmentDAO dao=new DepartmentDAO();
			String id=dao.createDepartment(d);
			d.setKey(id);
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, d);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
			
			
			
		}catch(Exception e){
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
