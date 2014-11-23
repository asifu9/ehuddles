package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;

/**
 * Servlet implementation class UpdateAlbumInfo
 */
@WebServlet("/UpdateAlbumInfo")
public class UpdateAlbumInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAlbumInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key="";
		String name="";
		String privacy="";
		String desc="";
		if(request.getParameter("key")!=null){
			key=request.getParameter("key");
		}
		if(request.getParameter("name")!=null){
			name=request.getParameter("name");
		}
		if(request.getParameter("pv")!=null){
			privacy=request.getParameter("pv");
		}
		if(request.getParameter("desc")!=null){
			desc=request.getParameter("desc");
		}
		System.out.println(" Key " + key  + " : " + name  + " : " + privacy);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		String res="";
		try {
		
	       // System.out.println(" date is " + t);  
			boolean result=PhotoAlbumDAO.updateOtherInfo(key, con, name,privacy,desc);
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
