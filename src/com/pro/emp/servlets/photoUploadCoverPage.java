package com.pro.emp.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import com.pro.emp.util.SquareThumb;

/**
 * Servlet implementation class photoUpload
 */
public class photoUploadCoverPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public photoUploadCoverPage() {
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
		String aId=null;
		Session con= CassandraDB.getCassConnection();
		try {
		EmpInfo info = EmpInfoDAO.getEmpById( empid);
		
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
		EmpBasicInfo infos= CacheRecords.getInstance().getCacheData(empid);
		info.setCoverPath(uploadPhoto(request,empid,aId,con));
		EmpInfoDAO.updateCoverPath(con, info,infos.getCreationdate());
		Session_control.setSession_(request, empid);
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
		String thumFileName="";
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
					//String finalimage = id+"_"+buffer.toString()+"9_"+r+"9_9"+domainName;
					// thumFileName = id+"_"+buffer.toString()+"9_"+r+"9_9icon"+domainName;
					
					Calendar cc= Calendar.getInstance();
					String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
					d=""+Calendar.getInstance().getTime().getTime();
					String finalimage = String.valueOf(id)+"/"+aId+"/"+"a_"+r+d+domainName;
					String finalimageDb = String.valueOf(id)+"\\"+aId+"\\"+"a_"+r+d+domainName;
					String finalimageThumb = String.valueOf(id)+"\\"+aId+"\\"+"b_"+r+d+domainName;
					System.out.println(" here is the path "+PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+finalimageDb);
					boolean success= (new File(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+String.valueOf(id)+"\\"+aId).mkdirs());
					File savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+finalimageDb);
					filepath = finalimage;
					item.write(savedFile);
					double[] dataa = Util.resize_image(savedFile,20,20,800,800,500,500);
					int wid =(int)(dataa[1]);
					int hei = (int)(dataa[0]);
					
					// now create thumbnail for this photo
					Util.CreateThumbNail(savedFile, PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+finalimageThumb,wid,hei);
					
					String finalimageUploDS = String.valueOf(id)+"\\"+aId+"\\"+"a_"+r+d+domainName;
					//String thumFileName1 = String.valueOf(id)+"\\"+aId+"\\"+"1_"+buffer.toString()+"_"+r+d+domainName;
					String thumFileName2 = String.valueOf(id)+"\\"+aId+"\\"+"b_"+r+d+domainName;
					String thumFileName3 = String.valueOf(id)+"\\"+aId+"\\"+"c_"+r+d+domainName;
					String thumFileName4 = String.valueOf(id)+"\\"+aId+"\\"+"d_"+r+d+domainName;
					String thumFileName5 = String.valueOf(id)+"\\"+aId+"\\"+"e_"+r+d+domainName;
					String thumFileName6 = String.valueOf(id)+"\\"+aId+"\\"+"f_"+r+d+domainName;
					String thumFileName7 = String.valueOf(id)+"\\"+aId+"\\"+"g_"+d+domainName;
					com.pro.emp.util.ImageToolThumb imageTool = new com.pro.emp.util.ImageToolThumb();
					 imageTool.load( PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					
					// imageTool.thumbnail(700);
				   //     imageTool.writeResult(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1, "PNG");
//				        
//						Thumbnails.of(savedFile).size(800,800).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
//						Thumbnails.of(savedFile).size(600,600).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
//						Thumbnails.of(savedFile).size(400,450).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
//						Thumbnails.of(savedFile).size(300,300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
//					
					 
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
						
						 PhotoInfo pInfo=new PhotoInfo();
						 pInfo.setHeight(hei);
						 pInfo.setWidth(wid);
						 pInfo.setIdPhotoAlbum(aId);
						 pInfo.setOwnerId(id);
						 pInfo.setPhotoPath(filepath);
						 
						 filepath=	PhotoInfoDAO.createPhotoInfo(pInfo, con);
					 //File SmallFile=new File(PropertyReader.getValue("photoSavepath1")+"Photo\\"+id+"_thumbNail_Icon"+domainName);
					//Thumbnails.of(savedFile).size(50,50).toFile(SmallFile);
					//filepath=createAlbumnPhoto(id,request,buffer.toString(),r,savedFile,aId);
					//finalimage = String.valueOf(id)+"\\"+aId+"\\"+"0_"+buffer.toString()+"_"+r+d+domainName;
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		System.out.println(" Cover path before crop " + filepath);
		return filepath;
	}
	

}
