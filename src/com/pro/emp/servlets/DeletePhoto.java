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
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.dao.PhotoInfoDAO;

/**
 * Servlet implementation class DeletePhoto
 */
@WebServlet("/DeletePhoto")
public class DeletePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String photoId="";
		String createdOn="";
		if(request.getParameter("photoId")!=null){
			photoId=request.getParameter("photoId");
		}
		if(request.getParameter("createdOn")!=null){
			createdOn=request.getParameter("createdOn");
		}
		//System.out.println(" Comment id : " + commentId);
		Date d= Util.convertStringToDate(createdOn);
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		String res="";
		try {
			Calendar currentDate = Calendar.getInstance();
			Timestamp t=new Timestamp(currentDate.getTime().getTime());
	       // System.out.println(" date is " + t);  
			boolean result=PhotoInfoDAO.deleteById(photoId, con,d);
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
