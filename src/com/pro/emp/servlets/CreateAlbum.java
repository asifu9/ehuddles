package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.domain.PhotoAlbum;

/**
 * Servlet implementation class CreateAlbum
 */
public class CreateAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAlbum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid="";
		String name="";
		String desc="";
		String privacy="";
		System.out.println("in get method fo create album");
		if(request.getParameter("userId")!=null){
			userid=request.getParameter("userId");
		}
		if(request.getParameter("name")!=null){
			name=request.getParameter("name");
		}
		if(request.getParameter("desc")!=null){
			desc=request.getParameter("desc");
		}
		if(request.getParameter("pv")!=null){
			privacy=request.getParameter("pv");
		}
		int result=0;
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		Session con =CassandraDB.getCassConnection();
		try {
			PhotoAlbum al=new PhotoAlbum();
			al.setName(name);
			al.setUserId(userid);
			al.setDesc(desc);
			int i=0;
			if(privacy.equalsIgnoreCase("public"))
				i=1;
			al.setPrivacy(i);
			al.setCoverScreenPath("");
			al.setAlbumType(1);
			Timestamp ts= new Timestamp(Calendar.getInstance().getTime().getTime());
			al.setCreatedOn(ts);
			result=PhotoAlbumDAO.createPhotoAlbum(al, con);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		String msg="Error occured";
		if(result!=0){
			msg="[{\"result\":\"SUCCESS\"}]";
		}
		writer.println(msg);
        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid="";
		String albumName="";
		String privacy="";
		System.out.println("in postS method fo create album");
		if(request.getParameter("inputQueryString")!=null){
			String str=request.getParameter("inputQueryString");
			userid = str.split("~~~#")[2];
			albumName =str.split("~~~#")[0];
			privacy = str.split("~~~#")[1];
			int result=0;
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			
			Session con =CassandraDB.getCassConnection();
			try {
				PhotoAlbum al=new PhotoAlbum();
				al.setName(albumName);
				al.setUserId(userid);
				al.setPrivacy(Integer.parseInt(privacy));
				al.setCoverScreenPath("");
				Timestamp ts= new Timestamp(Calendar.getInstance().getTime().getTime());
				al.setCreatedOn(ts);
				result=PhotoAlbumDAO.createPhotoAlbum(al, con);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			String msg="Error occured";
			if(result!=0){
				msg="Album Successfully created";
			}
			writer.println(msg);
	        writer.flush();
		}
	}

}
