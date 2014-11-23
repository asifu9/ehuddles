package com.pro.emp.servlets;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.VideoInfoDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.VideoInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;

/**
 * Servlet implementation class photoUpload
 */
@WebServlet("/UploadVideo")
public class UploadVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadVideo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println( " here i am yaar ");
//		HttpSession session = request.getSession(true);
//		String empid=session.getAttribute("calendarEmpId").toString();
//
//		ConnectionPool c=ConnectionPool.getInstance();
//		Connection con= c.getConnection();
//		try {
//		EmpInfo info = EmpInfoDAO.getEmpById(con, empid);
//		info.setImagePath(uploadPhoto(request));
//		EmpInfoDAO.updateImagePath(con, info);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		finally {
//			c.freeConnection(con);
//		}
//		RequestDispatcher rd =request.getServletContext().getRequestDispatcher("/photoUpload.jsp?msg=Successfully Uploaded..");
//		rd.forward(request, response);
		//uploadVideo(request);
		/*System.out.println("Control inn");
		String path=uploadVideo(request);
		VideoInfo info=new VideoInfo();
		EmpInfo emp = Session_control.getSession(request);
		System.out.println("employee:"+emp);
		info.setDescription("");
		info.setOwnerId(emp.getId());
		System.out.println("owner id:"+emp.getId());
		info.setVideoPath(path);
		Connection con =CassandraDB.getCassConnection();
		
		try{
			System.out.println("control in");
			String videoId=VideoInfoDAO.createVideoInfo(info, con);
			PostInfo post=new PostInfo();
			post.setPostedDesc(emp.getEmpName() + " uploaded new video ");
			post.setPostedById(emp.getId());
			post.setPostedVideoId(videoId);
			post.setPostType(3);
			PostDAO.createPost(post, con);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}*/
		
	
		RequestDispatcher rd=null;
		VideoTemp t=uploadVideo(request);
		System.out.println("empid"+t.getEmpId());
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		
		try {
				VideoInfo info=new VideoInfo();
				info.setIdVideoInfo(t.empId);
				info.setVideoPath(t.filepath);
				String empId = t.getEmpId();
				System.out.println("empiiiid:"+empId);
				info.setOwnerId(empId);
				info.setDescription("");
				String VideoId=VideoInfoDAO.createVideo(info, con);
				
				EmpBasicInfo ii = CacheRecords.getInstance().getCacheData(empId);
				PostInfo post=new PostInfo();
				post.setPostedById(empId);
				post.setPostType(3);
				post.setPostedDesc(ii.getEmpName() + " added new video");
				post.setPostedVideoId(VideoId);
				post=PostDAO.createPost(post, con);
				
				post = PostDAO.getFullPostDetailByPostId(CassandraDB.getCassConnection(), post.getKey());
				//Notification 
				DomainNotify dd=new DomainNotify();
				dd.setType("post");
				ObjectMapper mapper2 = new ObjectMapper();
				Writer strWriter2 = new StringWriter();
				mapper2.writeValue(strWriter2, post);
				String obj2 = strWriter2.toString();
				while(obj2.contains("\\\\\\\\")){
					int ss = obj2.indexOf("\\\\\\\\");
					StringBuffer b=new StringBuffer(obj2);
					b=b.replace(ss, ss+1, "");
					obj2=b.toString();
				}
				dd.setJson(obj2);
				NotificationGroupList.getInstance().sendNotificationToAll(dd);
						
		//con.commit();
		rd =request.getRequestDispatcher("/fileVideoPopup.jsp?message=Successfully Uploaded");
		
		//fileUploader.jsp?albumId=66&eed=62
		}
		catch(Exception ex){
			ex.printStackTrace();
			rd =request.getRequestDispatcher("/fileVideoPopup.jsp?message=Error while upload");
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		rd.forward(request, response);
	}

	public VideoTemp uploadVideo(HttpServletRequest request){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		@SuppressWarnings("rawtypes")
		List items = null;
		String filepath ="";
		String empId = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		Iterator itr = items.iterator();
	
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()){
				@SuppressWarnings("unused")
				String name = item.getFieldName();
				if(name.equalsIgnoreCase("empId")){
					try {
					empId=item.getString();
					
					
					}
					catch(Exception e){
						empId=null;
					}
				}
				@SuppressWarnings("unused")
				String value = item.getString();

			} else {
				try {
					String itemName = item.getName();
					Random generator = new Random();
					int r = Math.abs(generator.nextInt());

					String reg = "[.*]";
					String replacingtext = "";
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(itemName);
					StringBuffer buffer = new StringBuffer();

					while (matcher.find()) {
						matcher.appendReplacement(buffer, replacingtext);
					}
					int IndexOf = itemName.indexOf("."); 
					String domainName = itemName.substring(IndexOf);
					String finalimage = buffer.toString()+"_"+r+domainName;
					File savedFile1 = new File(PropertyReader.getValue("photoSavepath",request));
					System.out.println("save1:"+savedFile1);
					File savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"video\\"+finalimage);
					System.out.println("Save file:"+savedFile);
			
					filepath = "video/"+ finalimage;
					item.write(savedFile);
					
					// now create thumbnail for this photo
					//Util.CreateThumbNail(savedFile, PropertyReader.getValue("photoSavepath")+"Photo\\"+thumFileName);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
		
		VideoTemp t= new VideoTemp();
		t.setFilepath(filepath);
		t.setEmpId(empId);
	return t;	
	}
	//String finalimagedddd = String.valueOf(empId)+"/"+"0_"+buffer.toString()+"_"+r+d+domainName;
	

class   VideoTemp {
	String filepath;
	String empId;
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	

}
}

