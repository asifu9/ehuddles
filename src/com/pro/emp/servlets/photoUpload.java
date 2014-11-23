package com.pro.emp.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.emp.util.SquareThumb;

/**
 * Servlet implementation class photoUpload
 */
public class photoUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public photoUpload() {
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

		HttpSession session = request.getSession(true);
		String empid=session.getAttribute("calendarEmpId").toString();
		String aId="";
		Session con= CassandraDB.getCassConnection();
		try {
		EmpInfo info = EmpInfoDAO.getEmpById( empid);
		
		PhotoAlbum al= PhotoAlbumDAO.getUserProfileAlbumByUserId( empid, con);
		if(al==null){
			PhotoAlbum a=new PhotoAlbum();
			a.setDesc("Profile Picture");
			a.setName("Profile Album");
			a.setUserId( empid);
			a.setPrivacy(1);
			a.setAlbumType(2);
			PhotoAlbumDAO.createPhotoAlbum(a, con);
			al= PhotoAlbumDAO.getUserProfileAlbumByUserId( empid, con);
		}
		aId= al.getIdPhotoAlbum().toString();
		info.setImagePath(uploadPhoto(request,empid,aId,con));
		EmpInfoDAO.updateImagePath(con, info);
		Session_control.setSession_(request,  empid);
		EmpBasicInfo ob=new EmpBasicInfo();
		ob.setEmpName(info.getEmpName());
		ob.setId(empid);
		ob.setImagePath(PhotoInfoDAO.getPhotoById(empid,info.getId(), con));
		CacheRecords.getInstance().setCacheData( empid, ob);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		RequestDispatcher rd =request.getRequestDispatcher("/successfull.jsp");
		rd.forward(request, response);
	}
	
	public String uploadPhoto(HttpServletRequest request,String id,String aId,Session con){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		@SuppressWarnings("rawtypes")
		List items = null;
		String filepath="";
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
					String finalimage = id+"_"+buffer.toString()+"_"+r+domainName;
					String thumFileName = id+"_"+buffer.toString()+"_"+r+"_icon"+domainName;
					Calendar cc=Calendar.getInstance();
					String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
					d=""+Calendar.getInstance().getTime().getTime();
					String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+id+"/"+aId;
					System.out.println(" str " +str);
					boolean success= (new File(str).mkdirs());
					System.out.println(" result " + success);
					String finalimage1 = String.valueOf(id)+"\\"+aId+"\\"+"a_"+r+d+domainName;
					System.out.println(" file saved in path " + finalimage1);
					File savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+finalimage1);
					System.out.println(" dddddd d = " + d);
					//filepath = "Photo/"+ thumFileName;
					item.write(savedFile);
					double[] dataa = Util.resize_image(savedFile,20,20,800,800,500,500);
					int wid =(int)(dataa[1]);
					int hei = (int)(dataa[0]);
					// now create thumbnail for this photo
					//Util.CreateThumbNail(savedFile, PropertyReader.getValue("photoSavepath",request)+"Photo\\"+thumFileName,wid,hei);
					//File SmallFile=new File(PropertyReader.getValue("photoSavepath1")+"Photo\\"+id+"_thumbNail_Icon"+domainName);
					//Thumbnails.of(savedFile).height(300).toFile(SmallFile);
					filepath=createAlbumnPhoto(d,id,request,buffer.toString(),r,savedFile,aId,domainName);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return filepath;
	}
	
	public String createAlbumnPhoto(String d,String empId,HttpServletRequest request,String buffer,int r,File savedFile,String aId,String domainName) throws IOException{
		String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+empId+"/"+aId;
		String iiid="";
		
		System.out.println(" str " +str);
		boolean success= (new File(str).mkdirs());
		System.out.println(" result " + success + "d = " + d);
		 
		Calendar cc=Calendar.getInstance();
		//String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
		//d=""+Calendar.getInstance().getTime().getTime();
		String finalimage = String.valueOf(empId)+"\\"+aId+"\\"+"a_"+r+d+domainName;
		String thumFileName1 = String.valueOf(empId)+"\\"+aId+"\\"+"b_"+r+d+domainName;
		String thumFileName2 = String.valueOf(empId)+"\\"+aId+"\\"+"c_"+r+d+domainName;
		String thumFileName3 = String.valueOf(empId)+"\\"+aId+"\\"+"d_"+r+d+domainName;
		String thumFileName4 = String.valueOf(empId)+"\\"+aId+"\\"+"e_"+r+d+domainName;
		String thumFileName5 = String.valueOf(empId)+"\\"+aId+"\\"+"f_"+r+d+domainName;
		String thumFileName6 = String.valueOf(empId)+"\\"+aId+"\\"+"g_"+r+d+domainName;
		String thumFileName7 = String.valueOf(empId)+"\\"+aId+"\\"+"h_"+r+d+domainName;
		
		double[] dataaaa2 = Util.resize_image(savedFile,20,20,800,800,500,500);
		int wid =(int)(dataaaa2[1]);
		int hie = (int)(dataaaa2[0]);
//060b47a3-50d0-461b-8d26-1270b153abcf\47d08c6f-6c27-4e61-886f-385e8e3ecc6f\a_16007939211395081275722.jpg
//060b47a3-50d0-461b-8d26-1270b153abcf\47d08c6f-6c27-4e61-886f-385e8e3ecc6f\a_16007939211395081275784.jpg
		String filepath = String.valueOf(empId)+"/"+empId+"/"+"a_"+r+d+domainName;
		String filepathThumb = String.valueOf(empId)+"/"+aId+"/"+"a_"+r+d+domainName;
		com.pro.emp.util.ImageToolThumb imageTool = new com.pro.emp.util.ImageToolThumb();
		
		System.out.println(" search path = " + PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
		 imageTool.load( PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
		
	
//		  Thumbnails.of(savedFile).size(1000,1000).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1);
//			Thumbnails.of(savedFile).size(800,800).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
//			Thumbnails.of(savedFile).size(600,600).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
//			Thumbnails.of(savedFile).size(400,450).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
//			Thumbnails.of(savedFile).size(300,300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
//			
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
   
	    	SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6,200,200,"jpg");
	    	SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7,150,150,"jpg");
			 
			
		 
		System.out.println(" photo creation done ");
		
		// now create thumbnail for this photo
//		HashMap<String,Integer> info=Util.createFullImage(savedFile, PropertyReader.getValue("photoSavepath")+"userUploads\\"+thumFileName1);
//		savedFile = new File(PropertyReader.getValue("photoSavepath")+"userUploads\\"+thumFileName1);
//		
//		// other thumb nails 
//		//2
//		// photo albumn thumb nail
		double[] dataaaa = Util.resize_image(savedFile,20,20,800,800,500,500);
//		int widdd =(int)(dataaaa[1]);
//		int heiii = (int)(dataaaa[0]);
//		Util.CreateThumbNail(savedFile, PropertyReader.getValue("photoSavepath")+"userUploads\\"+thumFileName2,widdd,heiii);
//		//Util.CreateBigThumbNail(savedFile, PropertyReader.getValue("photoSavepath")+"userUploads\\"+large_thumFileName);
//		
		
		// other thumb nails 
		//3
		// photo albumn thumb nail
	
		
		
	
		
	
		
		String finalimagedddd = String.valueOf(empId)+"/"+aId+"/"+"a_"+r+d+domainName;
		photoTemp t= new photoTemp();
		t.setIdPhotoAlbum( aId);
		t.setPath(finalimagedddd);
		t.setEmpId(empId);
		t.setWidth(wid);
		t.setHeight(hie);
		System.out.println(" photo creation done 3 ");
		PhotoInfo info=new PhotoInfo();
		Timestamp st= new Timestamp(Calendar.getInstance().getTime().getTime());
		info.setCreaedOn(st);
		info.setPhotoPath(t.getPath());
		info.setIdPhotoAlbum(t.getIdPhotoAlbum());
		info.setHeight(t.getHeight());
		info.setWidth(t.getWidth());
		
		
		empId =t.getEmpId();
		info.setOwnerId( empId);
		info.setDescription("");
		Session conn= CassandraDB.getCassConnection();
		try{
			System.out.println(" photo creation done 45");
		iiid=PhotoInfoDAO.createPhotoInfo(info, conn);
		System.out.println(" photo creation done " + iiid);
		PostInfo info1=new PostInfo();
		info1.setPostedById( empId);
		EmpBasicInfo b= CacheRecords.getInstance().getCacheData(  empId);
		info1.setPostedDesc(b.getEmpName()+ " has changed his profile photo.");
		info1.setPostedPhotoId( iiid);
		info1.setPrivatestatus(0);
		info1.setPostType(3);
		info1=PostDAO.createPost(info1, conn);
		
		info1 = PostDAO.getFullPostDetailByPostId(CassandraDB.getCassConnection(), info1.getKey());
		//Notification 
		DomainNotify dd=new DomainNotify();
		dd.setType("post");
		ObjectMapper mapper2 = new ObjectMapper();
		Writer strWriter2 = new StringWriter();
		mapper2.writeValue(strWriter2, info1);
		String obj2 = strWriter2.toString();
		while(obj2.contains("\\\\\\\\")){
			int ss = obj2.indexOf("\\\\\\\\");
			StringBuffer bb=new StringBuffer(obj2);
			bb=bb.replace(ss, ss+1, "");
			obj2=bb.toString();
		}
		dd.setJson(obj2);
		NotificationGroupList.getInstance().sendNotificationToAll(dd);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(conn);
		}
		return iiid;
	}

	
	class photoTemp {
		String id;
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
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
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
