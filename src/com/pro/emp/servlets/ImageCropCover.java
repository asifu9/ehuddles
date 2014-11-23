package com.pro.emp.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import net.coobird.thumbnailator.Thumbnails;

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
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;

/**
 * Servlet implementation class ImageCrop
 */
public class ImageCropCover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageCropCover() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(true);
		String empid="";
		if(session.getAttribute("editUser")!=null)
			empid = session.getAttribute("editUser").toString();
		Session con = CassandraDB.getCassConnection();
		@SuppressWarnings("unused")
		String imageName= req.getParameter("imageName").toString();
		int t=Integer.parseInt(req.getParameter("t"));
		int l=Integer.parseInt(req.getParameter("l"));
		int w=Integer.parseInt(req.getParameter("w"));
		int h=Integer.parseInt(req.getParameter("h"));
		
		String aId=null;
		List<PhotoAlbum> al= PhotoAlbumDAO.getAlbumByUserIdPublic(empid, con);
		if(al.size()==0){
			PhotoAlbum a=new PhotoAlbum();
			a.setDesc("Profile Picture");
			a.setName("Profile Album");
			a.setUserId(empid);
			a.setPrivacy(1);
			PhotoAlbumDAO.createPhotoAlbum(a, con);
			List<PhotoAlbum> all= PhotoAlbumDAO.getAlbumByUserId(empid);
			aId=all.get(0).getIdPhotoAlbum();
		}else{
			aId=al.get(0).getIdPhotoAlbum();
		}
		System.out.println(" image name is " + imageName);
		int i=imageName.lastIndexOf("/");
		String aaa= imageName.substring(i+3,imageName.length());
		String acutlaImage=req.getParameter("i");
		String imagePath=PropertyReader.getValue("photoSavepath",req)+"userUploads/"+req.getParameter("i");
		System.out.println(" image path in servlet " + imagePath);
		BufferedImage outImage=ImageIO.read(new File(imagePath));
		BufferedImage cropped=outImage.getSubimage(l, t, w, h);
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ImageIO.write(cropped,req.getParameter("f"), out);
		System.out.println("imagePath " + imagePath);
		File ob=new File(imagePath);
		
		Random r=new Random();
		int aa=r.nextInt();
		String str=PropertyReader.getValue("photoSavepath",req)+"userUploads/"+empid+"/"+aId;
		//String ffff=PropertyReader.getValue("photoDisplayPath",req)+
		System.out.println(" str " +str);
		boolean success= (new File(str).mkdirs());
		
		
		String finalimage = String.valueOf(empid)+"\\"+aId+"\\"+"3_"+aaa;
		String finalimageDb = String.valueOf(empid)+"/"+aId+"/"+"3_"+aa;
		System.out.println(" finalimage " + finalimage  + " : finalimageDb " + finalimageDb );
		System.out.println(" PropertyReader.getValue(photoAlbumPath)+finalimage "+PropertyReader.getValue("photoAlbumPath")+finalimage);
		File nFile=new File(PropertyReader.getValue("photoAlbumPath")+finalimage);
		
		ImageIO.write(cropped,req.getParameter("f"), 
				ob); // save the file with crop dimensions
	
		createAlbumnPhoto(ob,empid,req,aId.toString(),aaa,aa);
		
		//File SmallFile=new File(PropertyReader.getValue("photoSavepath1")+"Photo\\"+empid+"_thumbNail_Icon."+req.getParameter("f"));
		//Thumbnails.of(nFile).size(50,50).toFile(SmallFile);
		
		//String path =  empid + "_thumbNail."+req.getParameter("f");
		//String iconPath = empid + "_thumbNail_icon."+req.getParameter("f");
		// save the path in db

		
		try {
	//	EmpInfo info = EmpInfoDAO.getEmpById(con, empid);
		//.info.setImagePath("Photo/"+path);
		//System.out.println(" ok here i am see the difference ----------------------- " +"Photo/"+path);
		//EmpInfo info=new EmpInfo();
		//info.setCoverPath(finalimageDb);
		//info.setId(empid);
		//EmpInfoDAO.updateCoverPath(con, info);//(con, empid,"Photo/"+path);
		//Session_control.setSession_(req,empid);
		//double[] dataa = Util.resize_image(nFile,20,20,400,400,200,200);
		//int wid =(int)(dataa[1]);
		//int hei = (int)(dataa[0]);
		//Util.CreateThumbNail(nFile, PropertyReader.getValue("photoSavepath")+"Photo\\"+iconPath,wid,hei);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		// end of save path in db
		//res.setContentType("image/jpg");
//		ServletOutputStream wrt=res.getOutputStream();
//		wrt.write(out.toByteArray());
//		wrt.flush();
//		wrt.close();
		
		String url="/successfull.jsp";
	
	
	RequestDispatcher rd =req.getRequestDispatcher(url);
	rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	public void createAlbumnPhoto(File savedFile,String empId,HttpServletRequest request,String aId,String buffer,int r) throws IOException{
		String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+empId+"/"+aId;
		
		String sss="";
		System.out.println(" str " +str);
		boolean success= (new File(str).mkdirs());
		System.out.println(" result " + success);
		Calendar cc=Calendar.getInstance();
		String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
		String domainName=".JPG";
		//String finalimage = String.valueOf(empId)+"\\"+aId+"\\"+"0_"+buffer.toString()+"_"+r+d+domainName;
		//String thumFileName4 = String.valueOf(empId)+"\\"+aId+"\\"+"4_"+buffer.toString()+"_"+r+domainName;
		String thumFileName3 = String.valueOf(empId)+"\\"+aId+"\\"+"3_"+buffer.toString();//+"_"+domainName;
		String thumFileName4 = String.valueOf(empId)+"\\"+aId+"\\"+"4_"+buffer.toString();//+"_"+domainName;
		String thumFileName5 = String.valueOf(empId)+"\\"+aId+"\\"+"5_"+buffer.toString();//+"_"+domainName;
		String thumFileName6 = String.valueOf(empId)+"\\"+aId+"\\"+"6_"+buffer.toString();//+"_"+domainName;
		String thumFileName7 = String.valueOf(empId)+"\\"+aId+"\\"+"7_"+buffer.toString();//+"_"+domainName;
		
		sss=String.valueOf(empId)+"/"+aId+"/"+"1_"+buffer.toString()+"_"+r+d+domainName;

		//String filepath = String.valueOf(empId)+"/"+empId+"/"+"0_"+buffer.toString()+"_"+r+d+domainName;
		
		
	
		BufferedImage iimg = ImageIO.read(savedFile);
		 if( iimg.getHeight() > iimg.getWidth()){ 
		  
		  
		// create first thumbail
			 Thumbnails.of(savedFile).width(400).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
		Thumbnails.of(savedFile).width(350).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
		Thumbnails.of(savedFile).width(300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
		Thumbnails.of(savedFile).width(250).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6);
		Thumbnails.of(savedFile).size(200,200).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7);
		System.out.println(" photo creation done 0");
		 }
		 else{
			 Thumbnails.of(savedFile).height(400).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
			Thumbnails.of(savedFile).height(350).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
			Thumbnails.of(savedFile).height(300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
			Thumbnails.of(savedFile).height(250).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6);
			Thumbnails.of(savedFile).size(200,200).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7);
				 
			 
			System.out.println(" photo creation done 1 ");
			 
			 
			 
		 }
		 
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
		double[] dataaaa2 = Util.resize_image(savedFile,20,20,800,800,500,500);
		int wid =(int)(dataaaa2[1]);
		int hie = (int)(dataaaa2[0]);
		
		
	
		
	
		
		String finalimagedddd = String.valueOf(empId)+"/"+aId+"/"+"0_"+buffer.toString();//+"_"+r+d+domainName;
		photoTemp t= new photoTemp();
		t.setIdPhotoAlbum(aId);
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
		info.setOwnerId(empId);
		info.setDescription("");
		Session conn= CassandraDB.getCassConnection();
		try{
			System.out.println(" photo creation done 45");
		String iiid=PhotoInfoDAO.createPhotoInfo(info, conn);
		System.out.println(" photo creation done " + iiid);
		PostInfo info1=new PostInfo();
		info1.setPostedById(empId);
		EmpBasicInfo b= CacheRecords.getInstance().getCacheData(empId);
		 
		info1.setPostedDesc(b.getEmpName()+ " has changed his Cover photo.");
		info1.setPostedPhotoId(iiid);
		info1.setPrivatestatus(0);
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
			StringBuffer bs=new StringBuffer(obj2);
			bs=bs.replace(ss, ss+1, "");
			obj2=bs.toString();
		}
		dd.setJson(obj2);
		NotificationGroupList.getInstance().sendNotificationToAll(dd);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(conn);
		}
		
		//return sss;
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
