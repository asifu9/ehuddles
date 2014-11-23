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
import com.pro.emp.dao.PhotoAlbumDAO;

/**
 * Servlet implementation class DeleteAlbum
 */
@WebServlet("/DeleteAlbum")
public class DeleteAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAlbum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String albumId="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String resp="{\"result\":\"FAIL\"}";
		int res=0;
		if(request.getParameter("AlbumId")!=null){
			albumId =request.getParameter("AlbumId");
			Session con= CassandraDB.getCassConnection(); 
			try{
				res=PhotoAlbumDAO.deleteAlbumById(albumId, con);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				
			}
			
			if(res==1)
				resp="[{\"result\":\"SUCCESS\"}]";
			
				
		}else{
			//System.out.println(" data is null");
		}
		//System.out.println(" resp " + resp);
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
