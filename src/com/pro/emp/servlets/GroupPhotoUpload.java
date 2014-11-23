package com.pro.emp.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.files.FilesUploaders;
import com.pro.emp.util.SquareThumb;

/**
 * Servlet implementation class photoUpload
 */
public class GroupPhotoUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupPhotoUpload() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {/*

		HttpSession session = request.getSession(true);
		String empid=session.getAttribute("calendarEmpId").toString();
		String aId="";
		Session con= CassandraDB.getCassConnection();
		//String groupId=getGroupId(request);
		String ppId="";
		try {
			

			 GroupDAO obc=new GroupDAO();
			List<photoTemp>  data=uploadPhoto(request);
		
			String groupId="";
			
			for(photoTemp t:data){
				
				 GroupDAO ob=new GroupDAO();
				Group g=ob.getGroupById(con, t.getEmpId());
				groupId=t.getEmpId();
				List<PhotoAlbum> al= PhotoAlbumDAO.getAlbumByUserIdPublic(con,g.getOwnerId());
				if(al.size()==0){
					PhotoAlbum a=new PhotoAlbum();
					a.setDesc("Group photo");
					a.setName("Group photo");
					a.setUserId(empid);
					a.setPrivacy(1);
					PhotoAlbumDAO.createPhotoAlbum(a, con);
					List<PhotoAlbum> all= PhotoAlbumDAO.getAlbumByUserId(empid, con);
					aId=all.get(0).getIdPhotoAlbum();
					}else{
						aId=al.get(0).getIdPhotoAlbum();
					}
				
				
				PhotoInfo info=new PhotoInfo();
				info.setOwnerId(g.getOwnerId());
				Timestamp st= new Timestamp(Calendar.getInstance().getTime().getTime());
				info.setCreaedOn(st);
				info.setPhotoPath(t.getPath());
				info.setIdPhotoAlbum(aId);
				info.setHeight(t.getHeight());
				info.setWidth(t.getWidth());
				String aalbumId=t.getIdPhotoAlbum();
				
				//String eeempId =t.getEmpId();
				info.setOwnerId(g.getOwnerId());
				info.setDescription("");
				ppId=PhotoInfoDAO.createPhotoInfo(info, con);
				}
				

		//String photoIdNew=uploadPhoto(request,empid,aId);
		obc.updatePhotoId(ppId, groupId,con);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		RequestDispatcher rd =request.getRequestDispatcher("/groupPhotosuccessfull.jsp");
		rd.forward(request, response);
		
	*/}
	
	
	
	public List<photoTemp>  uploadPhoto(HttpServletRequest request){
		FileItemFactory factory = new DiskFileItemFactory();
		List<photoTemp> temp=new ArrayList<GroupPhotoUpload.photoTemp>();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String groupId="";
		
		System.out.println(" Request has come here to check ");
		@SuppressWarnings("rawtypes")
		List items = null;
		String filepath="";
		String filepath_big="";
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			System.out.println(" Looking for item "  + item.getFieldName() );
			
			if (item.isFormField()){
				@SuppressWarnings("unused")
				String name = item.getFieldName();
				if(name.equalsIgnoreCase("groupId")){
					try {
					groupId=item.getString();
					
					
					}
					catch(Exception e){
						groupId=null;
					}
				}
			
				
				@SuppressWarnings("unused")
				String value = item.getString();
				if(groupId!=null){
					String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+groupId;
					System.out.println(" str " +str);
					boolean success= (new File(str).mkdirs());
					System.out.println(" result " + success);
				}

			} else {
				try {
					if(groupId!=null){
						String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+groupId;
						System.out.println(" str " +str);
						boolean success= (new File(str).mkdirs());
						System.out.println(" result " + success);
					}
					String itemName = item.getName();
					
					Random generator = new Random();
					int r = Math.abs(generator.nextInt());
					Calendar cc= Calendar.getInstance();
					String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
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
					domainName=".JPG";
					String finalimage = String.valueOf(groupId)+"\\"+"0_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName1 = String.valueOf(groupId)+"\\"+"1_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName2 = String.valueOf(groupId)+"\\"+"2_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName3 = String.valueOf(groupId)+"\\"+"3_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName4 = String.valueOf(groupId)+"\\"+"4_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName5 = String.valueOf(groupId)+"\\"+"5_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName6 = String.valueOf(groupId)+"\\"+"6_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName7 = String.valueOf(groupId)+"\\"+"7_"+buffer.toString()+"_"+r+d+domainName;
					
					String large_thumFileName = finalimage;
					System.out.println(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					File savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					// uploaded original photo
					
					String sourcePath=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage;
						com.pro.emp.util.ImageToolThumb imageTool = new com.pro.emp.util.ImageToolThumb();
			       
					
					
			
					filepath = String.valueOf(groupId)+"/"+"0_"+buffer.toString()+"_"+r+d+domainName;
					filepath_big = finalimage;
					item.write(savedFile); // original finle written to disk
					
					
					//savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					double[] dataaaa2 = Util.resize_image(savedFile,20,20,800,800,500,500);
					int wid =(int)(dataaaa2[1]);
					int hie = (int)(dataaaa2[0]);
					
					 // end of convert
					 imageTool.load( PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
//					 if( iimg.getHeight() > iimg.getWidth()){ 
					 BufferedImage iimg = ImageIO.read(savedFile);
					  System.out.println(" in hiehg more option");
					// create first thumbail
//					Thumbnails.of(savedFile).size(1000,1000).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1);
//					Thumbnails.of(savedFile).size(600,600).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
//					Thumbnails.of(savedFile).size(500,500).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
//					Thumbnails.of(savedFile).size(400,450).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
//					Thumbnails.of(savedFile).size(300,300).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
//					Thumbnails.of(savedFile).width(200).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6);
//					Thumbnails.of(savedFile).size(200,200).rotate(90).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7);
//					 }
//					 else{
				       // imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5, "PNG");
				        
				        imageTool.thumbnail(1000);
				        imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1, "PNG");
				        
				        imageTool.thumbnail(800);
				        imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2, "PNG");
				        
				        imageTool.thumbnail(600);
				        imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3, "PNG");
				        
				        imageTool.thumbnail(500);
				        imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4, "PNG");
				        
				        imageTool.thumbnail(400);
				        imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5, "PNG");
			      
				        
//				        Thumbnails.of(savedFile).size(1000,1000).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1);
//						Thumbnails.of(savedFile).size(800,800).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
//						Thumbnails.of(savedFile).size(600,600).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
//						Thumbnails.of(savedFile).size(400,450).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
//						Thumbnails.of(savedFile).size(300,300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
//					
						  SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6,200,200,"png");
					    	SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7,150,150,"png");
				       
				        
						double[] dataaaa = Util.resize_image(savedFile,20,20,800,800,500,500);

					
				
					
					String finalimagedddd = String.valueOf(groupId)+"/"+"0_"+buffer.toString()+"_"+r+d+domainName;
					photoTemp t= new photoTemp();
					t.setPath(finalimagedddd);
					t.setEmpId(groupId);
					t.setWidth(wid);
					t.setHeight(hie);
					temp.add(t);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return temp;
	}
	
	class photoTemp {
		int id;
		String path;
		String idPhotoAlbum;
		String empId;
		int width;
		int height;
		
		
		
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		/**
		 * @return the empId
		 */
		public String getEmpId() {
			return empId;
		}
		/**
		 * @param empId the empId to set
		 */
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		/**
		 * @return the idPhotoAlbum
		 */
		public String getIdPhotoAlbum() {
			return idPhotoAlbum;
		}
		/**
		 * @param idPhotoAlbum the idPhotoAlbum to set
		 */
		public void setIdPhotoAlbum(String idPhotoAlbum) {
			this.idPhotoAlbum = idPhotoAlbum;
		}
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}
		/**
		 * @return the path
		 */
		public String getPath() {
			return path;
		}
		/**
		 * @param path the path to set
		 */
		public void setPath(String path) {
			this.path = path;
		}
		
		
		
		
	}
	

}
