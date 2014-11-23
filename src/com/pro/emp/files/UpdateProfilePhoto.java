package com.pro.emp.files;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.PhotoInfo;

/**
 * Servlet implementation class UpdateProfilePhoto
 */
@WebServlet("/UpdateProfilePhoto")
public class UpdateProfilePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfilePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String photoProfileId="";
		String empId="";
		String createdOn="";
		if(request.getParameter("profileIdHidden")!=null){
			photoProfileId= request.getParameter("profileIdHidden");
			
		}
		if(request.getParameter("empIdPhoto")!=null){
			empId= request.getParameter("empIdPhoto");
			
		}
		if(request.getParameter("createdOn")!=null){
			createdOn=request.getParameter("createdOn");
		}
		
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		
		try {
			Date dd=Util.convertStringToDate(createdOn);
			List<PhotoInfo> list= Util.getAllPhotoByAlbumId(photoProfileId);
			String coverPageId="";
			if(request.getParameter("coverPage")!=null){
				coverPageId=(request.getParameter("coverPage"));
			}
			
			for(PhotoInfo info:list){
				
				String value1="";
				String delete="";
				if(request.getParameter("del_"+info.getIdPhotoInfo())!=null){
					delete = request.getParameter("del_"+info.getIdPhotoInfo());
				}
				
				if(delete.equalsIgnoreCase("on")){
					// delete this file
					PhotoInfoDAO.deleteById(info.getIdPhotoInfo(), con,info.getCreaedOn());
				}else {
				
					if(request.getParameter("desc_"+info.getIdPhotoInfo())!=null){
						value1=request.getParameter("desc_"+info.getIdPhotoInfo());
					}
					if(value1.equalsIgnoreCase("Write a description"))
						value1="";
					//p.setCommentDesc(text.replace("\n", "<br/>"));
					value1=value1.replaceAll("\n","<br/>");
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
					
					PhotoInfoDAO.updateDescriptoin(info.getIdPhotoInfo(), con, value1,new Date(info.getCreaedOn().getTime()));
					//System.out.println(" cover pid id " + coverPageId + " : " + info.getIdPhotoInfo());
					if(coverPageId.equalsIgnoreCase(info.getIdPhotoInfo())){
						PhotoAlbumDAO.updateCoverPage(photoProfileId,con,info.getPhotoPath());
					}
				}
				
				//con.commit();
				
			}
			RequestDispatcher rd= request.getRequestDispatcher("/photo.jsp?userId="+empId);
			rd.forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
			}
		finally{
			CassandraDB.freeConnection(con);
		}
	}
	

}
