package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;

/**
 * Servlet implementation class DeletePostComment
 */
@WebServlet("/DeletePostComment")
public class DeletePostComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String key="";
		String res="";
		String commentDate="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		if(request.getParameter("key")!=null){
			key=request.getParameter("key");
		}
		if(request.getParameter("commentDate")!=null){
			commentDate=request.getParameter("commentDate");
		}
		
		System.out.println(" commentDate " + commentDate);
		Session con = CassandraDB.getCassConnection();
		try{
			int result=PostCommentsDAO.deleteById(key, con,Util.convertStringToDate(commentDate));
			
			
			System.out.println(" test ----- " + res);
			if(result==1){
				//resp = "SUCCESS";
				res="{\"result\":\"SUCCESS\"}";
			}
			else{
				//resp = "FAIL";
				res="{\"result\":\"FAILURE\"}";
			}
		}catch(Exception e){
		e.printStackTrace();	
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
