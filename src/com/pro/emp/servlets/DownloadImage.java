package com.pro.emp.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.PropertyReader;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;

/**
 * Servlet implementation class DownloadImage
 */
@WebServlet("/DownloadImage")
public class DownloadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/octet-stream");
		String createdOn="";
		// get the image path
		String imageId ="";
		if(request.getParameter("imageId")!=null){
			imageId=request.getParameter("imageId");
			if(request.getParameter("createdOn")!=null){
				createdOn=request.getParameter("createdOn");
			}
		
			Session con= CassandraDB.getCassConnection();
		try {
			Date ddd= Util.convertStringToDate(createdOn);
			PhotoInfoDomain info = PhotoInfoDAO.getPhotoDescById(imageId, con);
			String updateName= info.getPath().replace("thumbbig_", "");
			String path = PropertyReader.getValue("photoSavepath",request)+PropertyReader.getValue("photoAlbumPath",request)+updateName;
			//System.out.println(" path is " + path);
			response.setHeader("Content-Disposition","attachment;filename="+updateName);
			
			File file = new File(path);
			FileInputStream fileIn = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			 
			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(fileIn.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			fileIn.close();
			out.flush();
			out.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/octet-stream");
		String createdOn="";
		// get the image path
		String imageId ="";
		if(request.getParameter("imageId")!=null){
			imageId=request.getParameter("imageId");
			if(request.getParameter("createdOn")!=null){
				createdOn=request.getParameter("createdOn");
			}
		
			Session con= CassandraDB.getCassConnection();
		try {
			Date ddd= Util.convertStringToDate(createdOn);
			PhotoInfoDomain info = PhotoInfoDAO.getPhotoDescById(imageId, con);
			String updateName= info.getPath().replace("thumbbig_", "");
			String path = PropertyReader.getValue("photoSavepath",request)+PropertyReader.getValue("photoAlbumPath",request)+updateName;
			//System.out.println(" path is " + path);
			response.setHeader("Content-Disposition","attachment;filename="+updateName);
			
			File file = new File(path);
			FileInputStream fileIn = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			 
			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(fileIn.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			fileIn.close();
			out.flush();
			out.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		}
		
	}

}
