package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.EducationDAO;
import com.pro.emp.dao.ProffesionalDAO;
import com.pro.emp.domain.Education;
import com.pro.emp.domain.Proffesional;

/**
 * Servlet implementation class CreateProffession
 */
@WebServlet("/CreateProffession")
public class CreateProffession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProffession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String company="";		
		 String designation="";
		 String startDate="";
		 String endDate="";
		 String city="";
		 String empId="";
		 response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			//System.out.println(" ok here i am ");
		if(request.getParameter("company")!=null){
			company= request.getParameter("company");
		}
		if(request.getParameter("designation")!=null){
			designation= request.getParameter("designation");
		}
		if(request.getParameter("startDate")!=null){
			startDate= request.getParameter("startDate");
		}
		if(request.getParameter("endDate")!=null){
			endDate= request.getParameter("endDate");
		}
		if(request.getParameter("city")!=null){
			city= request.getParameter("city");
		}
		if(request.getParameter("empId")!=null){
			empId= request.getParameter("empId");
		}
		String result="";
		//System.out.println(" data is " + empId + " : " + company + " : " + designation + " : " + startDate + " : " + endDate );
		
		Session con =  CassandraDB.getCassConnection();
		try {
			Proffesional pro=new Proffesional();
			pro.setCity(city);
			pro.setCompanyName(company);
			pro.setDesignation(designation);
			pro.setEmpId(empId);
			Date theDate = Util.getFormatedDate(startDate); 
			pro.setWorkedFrom(theDate);
			Date theDate1 = Util.getFormatedDate(endDate); 
			pro.setWorkedTo(theDate1);
			
			
			String dd=ProffesionalDAO.createEducationInfo(pro, con);
			pro.setEmpId(dd);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, pro);
			result = strWriter.toString();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(result);
        writer.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String company="";		
		 String designation="";
		 String startDate="";
		 String endDate="";
		 String city="";
		 String empId="";
		 response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			//System.out.println(" ok here i am ");
		if(request.getParameter("company")!=null){
			company= request.getParameter("company");
		}
		if(request.getParameter("designation")!=null){
			designation= request.getParameter("designation");
		}
		if(request.getParameter("startDate")!=null){
			startDate= request.getParameter("startDate");
		}
		if(request.getParameter("endDate")!=null){
			endDate= request.getParameter("endDate");
		}
		if(request.getParameter("city")!=null){
			city= request.getParameter("city");
		}
		if(request.getParameter("empId")!=null){
			empId= request.getParameter("empId");
		}
		String result="";
		//System.out.println(" data is " + empId + " : " + company + " : " + designation + " : " + startDate + " : " + endDate + " : " + city );
		
		Session con =  CassandraDB.getCassConnection();
		try {
			Proffesional pro=new Proffesional();
			pro.setCity(city);
			pro.setCompanyName(company);
			pro.setDesignation(designation);
			pro.setEmpId(empId);
			Date theDate = Util.getFormatedDate(startDate); 
			pro.setWorkedFrom(theDate);
			Date theDate1 = Util.getFormatedDate(endDate); 
			pro.setWorkedTo(theDate1);
			
			
			String dd=ProffesionalDAO.createEducationInfo(pro, con);
			pro.setEmpId(dd);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, pro);
			result = strWriter.toString();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(result);
        writer.flush();
	}

}
