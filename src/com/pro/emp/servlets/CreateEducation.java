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
import com.pro.emp.domain.Education;

/**
 * Servlet implementation class CreateEducation
 */
@WebServlet("/CreateEducation")
public class CreateEducation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEducation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String collegeName="";		
		 String courseName="";
		 String startDate="";
		 String endDate="";
		 String city="";
		 String empId="";
		 response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
		if(request.getParameter("collegeName")!=null){
			collegeName= request.getParameter("collegeName");
		}
		if(request.getParameter("course")!=null){
			courseName= request.getParameter("course");
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
		//System.out.println(" data is " + empId + " : " + collegeName + " : " + courseName + " : " + startDate + " : " + endDate );
		
		Session con =  CassandraDB.getCassConnection();
		try {
			Education edu=new Education();
			edu.setCity(city);
			edu.setCollegeName(collegeName);
			edu.setCourseName(courseName);
			edu.setEmpId(empId);
			Date theDate = Util.getFormatedDate(startDate); 
			edu.setFrom(theDate);
			Date theDate1 = Util.getFormatedDate(endDate); 
			edu.setTo(theDate1);
			
			
			String dd=EducationDAO.createEducationInfo(edu, con);
			edu.setEmpId(dd);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, edu);
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
		// TODO Auto-generated method stub
	}

}
