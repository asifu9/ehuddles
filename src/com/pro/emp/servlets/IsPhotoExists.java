package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortPhotos;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.PhotoInfo;

/**
 * Servlet implementation class IsPhotoExists
 */
@WebServlet("/IsPhotoExists")
public class IsPhotoExists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsPhotoExists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumId="";
		String path="";
		String resp="{\"result\":\"FAIL\"}";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		if(request.getParameter("albumId")!=null){
			albumId=request.getParameter("albumId");
		}
		if(request.getParameter("path")!=null){
			path=request.getParameter("path");
		}
		System.out.println(" albumId " + albumId + " path " + path);
		boolean found=false;
		Session con= CassandraDB.getCassConnection();
		try{
		List<PhotoInfo> list= PhotoInfoDAO.getPhotoByAlbumId(albumId, con);
		SortPhotos s=new SortPhotos("PhotoAlbum");
		Collections.sort(list,s);
		for(PhotoInfo l:list){
			if(l.getPhotoPath().equalsIgnoreCase(path)){
				found=true;
				break;
			}
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		if(found)
			resp="{\"result\":\"SUCCESS\"}";
		
		System.out.println(" resp : " + resp);
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
