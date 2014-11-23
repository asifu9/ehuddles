package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoInfoDAO;

/**
 * Servlet implementation class PhotoAddDesc
 */
@WebServlet("/PhotoAddDesc")
public class PhotoAddDesc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoAddDesc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String key="";
String desc="";
String createdOn="";
		if(request.getParameter("key")!=null){
			key=request.getParameter("key");
		}
		if(request.getParameter("desc")!=null){
			desc=request.getParameter("desc");
		}
		if(request.getParameter("createdOn")!=null){
			createdOn=request.getParameter("createdOn");
		}
		System.out.println(" created on " + createdOn);
		Date dd= Util.convertStringToDate(createdOn);
		String value1=desc.replaceAll("\n","<br/>");
		//System.out.println(" value si " + value1);
		if(value1.contains("\b")){
			//System.out.println(" \\b present 1");
		}
		if(value1.contains("\r")){
			//System.out.println(" \\r present 2");
			value1=value1.replaceAll("\r","");
		}
		if(value1.contains("\t")){
			//System.out.println(" \\t present 3");
		}
		if(value1.contains("\n")){
			//System.out.println(" \\n present 4");
		}
		if(value1.contains("\b")){
			//System.out.println(" \\b present 5");
		}
		
		//System.out.println(" Comment id : " + commentId);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		String res="";
		try {
			Calendar currentDate = Calendar.getInstance();
			Timestamp t=new Timestamp(currentDate.getTime().getTime());
	       // System.out.println(" date is " + t);  
			boolean result=PhotoInfoDAO.updateDescriptoin(key, con, value1,dd);
			if(result==true){
				res="{\"result\":\"SUCCESS\"}";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			res="{\"result\":\"FAILURE\"}";
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(res);
        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
