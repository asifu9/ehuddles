package com.pro.emp.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.PropertyReader;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.PhotoInfo;

/**
 * Servlet implementation class RotateImage
 */
@WebServlet("/RotateImage")
public class RotateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RotateImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imageId="";
		if(request.getParameter("imageId")!=null)
			imageId=request.getParameter("imageId");
		
		PhotoInfo info=null;
		
		Session con= CassandraDB.getCassConnection();
		try {
			
			//info = PhotoInfoDAO.getPhotoByIdDynamic(imageId, con);
			String path1=info.getPhotoPath();
			String path2= path1.replaceFirst("0_","1_");
			String path3= path1.replaceFirst("0_","2_");
			String path4= path1.replaceFirst("0_","3_");
			String path5= path1.replaceFirst("0_","4_");
			String path6= path1.replaceFirst("0_","5_");
			String path7= path1.replaceFirst("0_","6_");
			System.out.println(" PropertyReader.getValue(photoSavepath)" +PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path1);
			//File f=new File(PropertyReader.getValue("photoSavepath",request)+""+path1);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path1, PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path1);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path2,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path2);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path3,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path3);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path4,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path4);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path5,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path5);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path6,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path6);
			Util.rotatePhoto(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path7,PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path7);
			//Thumbnails.of(f).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path1);
			
			//File f1=new File(PropertyReader.getValue("photoSavepath",request)+""+path2);
			//Thumbnails.of(f1).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path2);
			
			//File f2=new File(PropertyReader.getValue("photoSavepath",request)+""+path3);
			//Thumbnails.of(f2).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path3);
			
			//File f3=new File(PropertyReader.getValue("photoSavepath",request)+""+path4);
			//Thumbnails.of(f3).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path4);
			
			//File f4=new File(PropertyReader.getValue("photoSavepath",request)+""+path5);
			//Thumbnails.of(f4).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path5);
			
			//File f5=new File(PropertyReader.getValue("photoSavepath",request)+""+path6);
			//Thumbnails.of(f5).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path6);
			
			//File f6=new File(PropertyReader.getValue("photoSavepath",request)+""+path7);
			//Thumbnails.of(f6).rotate(90);//.toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+path7);
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
