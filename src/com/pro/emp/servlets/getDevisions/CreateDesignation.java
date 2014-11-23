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
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.domain.Designation;

/**
 * Servlet implementation class CreateDesignation
 */
@WebServlet("/CreateDesignation")
public class CreateDesignation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDesignation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String desName="";
		String resp="";
		String orderNum="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("orderNum")!=null){
			orderNum= request.getParameter("orderNum");
		}
		
		if(request.getParameter("desName")!=null){
			desName= request.getParameter("desName");
		}
		
		System.out.println(" photoId " + orderNum);
		System.out.println(" postDesc " + desName);
		Session con = CassandraDB.getCassConnection();
		try{
			Designation d=new Designation();
			d.setName(desName);
			d.setOrderNum(Integer.parseInt(orderNum));
			DesignationDAO dao=new DesignationDAO();
			String id=dao.createDesignation(d);
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
