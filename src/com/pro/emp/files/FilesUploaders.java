package com.pro.emp.files;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import com.pro.emp.Thumbnail;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.emp.util.SquareThumb;

/**
 * Servlet implementation class FilesUploaders
 */
public class FilesUploaders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilesUploaders() {
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
		RequestDispatcher rd=null;
		List<photoTemp>  data=uploadPhoto(request);
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		String albumId="";
		String empId=Session_control.getSession(request).getId();
		int count=0;
		try {
			count=data.size();

			for(photoTemp t:data){

				PhotoInfo info=new PhotoInfo();
				Timestamp st= new Timestamp(Calendar.getInstance().getTime().getTime());
				info.setCreaedOn(st);
				info.setPhotoPath(t.getPath());
				info.setIdPhotoAlbum(t.getIdPhotoAlbum());
				info.setHeight(t.getHeight());
				info.setWidth(t.getWidth());
				info.setPhotoCreationDate(t.getTime());
				albumId=t.getIdPhotoAlbum().toString();

				empId =t.getEmpId().toString();
				info.setOwnerId(empId);
				info.setDescription("");
				PhotoInfoDAO.createPhotoInfo(info, con);
			}
			EmpBasicInfo ii = CacheRecords.getInstance().getCacheData(empId);
			PhotoAlbum ab= PhotoAlbumDAO.getAlbumById(albumId, con);
			if(ab.getPrivacy()==1){
				PostInfo info=new PostInfo();
				info.setPostedById(empId);
				info.setPostedPhotoId(albumId);
				info.setPostType(2);
				info.setPrivatestatus(0);
				info.setPhotoCount(count);
				System.out.println( " ii.getEmpName() " +ii);
				System.out.println(" :" + ii.getEmpName());
				System.out.println(" ab.getName() " +ab );
				System.out.println(" : " +  ab.getName());
				info.setPostedDesc(ii.getEmpName() +  " added new photo to his album <a class=\"albumNameInPostStyle\" href=\"photo_all.jsp?aid="+albumId+"\">" + ab.getName()+ "</a>");
				PostInfo i=PostDAO.createPost(info, con);
				i = PostDAO.getPostDetailByPostId(CassandraDB.getCassConnection(), i.getKey());

				//Notification 
				DomainNotify d=new DomainNotify();
				d.setType("post");
				ObjectMapper mapper2 = new ObjectMapper();
				Writer strWriter2 = new StringWriter();
				mapper2.writeValue(strWriter2, i);
				String obj2 = strWriter2.toString();
				while(obj2.contains("\\\\\\\\")){
					int ss = obj2.indexOf("\\\\\\\\");
					StringBuffer b=new StringBuffer(obj2);
					b=b.replace(ss, ss+1, "");
					obj2=b.toString();
				}
				d.setJson(obj2);
				NotificationGroupList.getInstance().sendNotificationToAll(d);

			}

			//con.commit();
			rd =request.getRequestDispatcher("/fileUploadPopup.jsp?message=Successfully Uploaded");

			//fileUploader.jsp?albumId=66&eed=62
		}
		catch(Exception ex){
			ex.printStackTrace();
			rd =request.getRequestDispatcher("/fileUploadPopup.jsp?message=Error while upload");
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		rd.forward(request, response);
	}

	public List<photoTemp>  uploadPhoto(HttpServletRequest request){
		FileItemFactory factory = new DiskFileItemFactory();
		String empId=null;
		String albumId=null;
		List<photoTemp> temp=new ArrayList<FilesUploaders.photoTemp>();
		ServletFileUpload upload = new ServletFileUpload(factory);



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

			if (item.isFormField()){
				@SuppressWarnings("unused")
				String name = item.getFieldName();
				/*	if(name.equalsIgnoreCase("empId")){
					try {
					empId=item.getString();


					}
					catch(Exception e){
						empId=null;
					}
				}*/
				if(name.equalsIgnoreCase("albumId")){
					try {

						albumId=item.getString().trim();

					}
					catch(Exception e){
						System.out.println(" e "+ e.getMessage());
					}
				}

				empId= Session_control.getSession(request).getId();

				@SuppressWarnings("unused")
				String value = item.getString();
				if(empId!=null && albumId!=null){
					String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+empId+"/"+albumId;
					//System.out.println(" str " +str);
					boolean success= (new File(str).mkdirs());
					//System.out.println(" result " + success);
				}

			} else {
				try {
					//System.out.println(" directory " + empId + " : " + albumId);
					if(empId!=null && albumId!=null){
						String str=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+empId+"/"+albumId;
						//System.out.println(" str " +str);
						boolean success= (new File(str).mkdirs());
						//System.out.println(" result " + success);
					}
					String itemName = item.getName();

					Random generator = new Random();
					int r = Math.abs(generator.nextInt());
					Calendar cc= Calendar.getInstance();
					String d=""+ cc.get(Calendar.YEAR)+cc.get(Calendar.MONTH)+cc.get(Calendar.DATE)+"-"+cc.get(Calendar.HOUR)+cc.get(Calendar.MINUTE)+cc.get(Calendar.SECOND);
					d=""+Calendar.getInstance().getTime().getTime();
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
					System.out.println("  buffere is " + buffer.toString() + " r " + r + " d " + d + " domain " + domainName);
					String finalimage = String.valueOf(empId)+"\\"+albumId+"\\"+"a_"+""+r+d+domainName;
					String thumFileName1 = String.valueOf(empId)+"\\"+albumId+"\\"+"b_"+""+r+d+domainName;
					String thumFileName2 = String.valueOf(empId)+"\\"+albumId+"\\"+"c_"+""+r+d+domainName;
					String thumFileName3 = String.valueOf(empId)+"\\"+albumId+"\\"+"d_"+""+r+d+domainName;
					String thumFileName4 = String.valueOf(empId)+"\\"+albumId+"\\"+"e_"+"_"+r+d+domainName;
					String thumFileName5 = String.valueOf(empId)+"\\"+albumId+"\\"+"f_"+""+r+d+domainName;
					String thumFileName6 = String.valueOf(empId)+"\\"+albumId+"\\"+"g_"+""+r+d+domainName;
					String thumFileName7 = String.valueOf(empId)+"\\"+albumId+"\\"+"h_"+""+r+d+domainName;

					String large_thumFileName = finalimage;
					System.out.println("finalimage " + finalimage);
					System.out.println("thumFileName1 " + thumFileName1);
					System.out.println("thumFileName2 " + thumFileName2);
					System.out.println("thumFileName3 " + thumFileName3);
					System.out.println("thumFileName4 " + thumFileName4);
					System.out.println("thumFileName5 " + thumFileName5);
					System.out.println("thumFileName6 " + thumFileName6);
					System.out.println("thumFileName7 " + thumFileName7);

					//System.out.println(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					File savedFile = new File(PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage);
					// uploaded original photo

					String sourcePath=PropertyReader.getValue("photoSavepath",request)+"userUploads/"+finalimage;
					com.pro.emp.util.ImageToolThumb imageTool = new com.pro.emp.util.ImageToolThumb();




					filepath = String.valueOf(empId)+"/"+albumId+"/"+"a_"+""+r+d+domainName;
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
					//System.out.println(" in hiehg more option");
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
					//					  Thumbnails.of(savedFile).size(1000,1000).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1);
					//						Thumbnails.of(savedFile).size(800,800).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
					//						Thumbnails.of(savedFile).size(600,600).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
					//						Thumbnails.of(savedFile).size(400,450).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
					//						Thumbnails.of(savedFile).size(300,300).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
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

					SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6,200,200,"png");
					SquareThumb.createSquareImage(savedFile,PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7,150,150,"png");






					//						 Thumbnails.of(savedFile).size(1000,1000).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName1);
					//						Thumbnails.of(savedFile).size(800,800).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName2);
					//						Thumbnails.of(savedFile).size(600,600).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName3);
					//						Thumbnails.of(savedFile).size(500,500).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName4);
					//						Thumbnails.of(savedFile).size(400,400).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName5);
					//						if( hh > ww){ 
					//							Thumbnails.of(savedFile).width(200).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6);
					//						}else{
					//							Thumbnails.of(savedFile).width(350).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName6);
					//						}
					//						
					//						Thumbnails.of(savedFile).size(200,200).toFile(PropertyReader.getValue("photoSavepath",request)+"userUploads\\"+thumFileName7);
					////							 
					////						 
					//						 
					//						 }
					//						 
					//						 
					//						 
					//					 }


					// now create thumbnail for this photo
					//					HashMap<String,Integer> info=Util.createFullImage(savedFile, PropertyReader.getValue("photoSavepath")+"userUploads\\"+thumFileName1);
					//					savedFile = new File(PropertyReader.getValue("photoSavepath")+"userUploads\\"+thumFileName1);
					//					
					//					// other thumb nails 
					//					//2
					//					// photo albumn thumb nail
					double[] dataaaa = Util.resize_image(savedFile,20,20,800,800,500,500);




					String finalimagedddd = String.valueOf(empId)+"/"+albumId+"/"+"a_"+r+d+domainName;
					photoTemp t= new photoTemp();
					t.setIdPhotoAlbum(albumId);
					t.setPath(finalimagedddd);

					t.setTime(Util.getPhotoCreationDate(savedFile));
					t.setEmpId(empId);
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
		String time;



		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
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

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}
		public String getIdPhotoAlbum() {
			return idPhotoAlbum;
		}
		public void setIdPhotoAlbum(String idPhotoAlbum) {
			this.idPhotoAlbum = idPhotoAlbum;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
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
