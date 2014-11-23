package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.PhotoCommentsDAO;

/**
 * Servlet implementation class CreateComment
 */
@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commentId="";
		String commentDate="";
		if(request.getParameter("commentId")!=null){
			commentId=request.getParameter("commentId");
		}
		
		if(request.getParameter("commentDate")!=null){
			commentDate=request.getParameter("commentDate");
		}
		System.out.println(" Comment date : " + commentDate);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		String res="";
		try {
			Calendar currentDate = Calendar.getInstance();
			Timestamp t=new Timestamp(currentDate.getTime().getTime());
	       // System.out.println(" date is " + t);  
			boolean result=PhotoCommentsDAO.deleteById(commentId, con);
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
