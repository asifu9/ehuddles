package com.pro.emp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import sun.awt.image.BufferedImageGraphicsConfig;



import com.datastax.driver.core.Session;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.pro.emp.dao.DepartmentDAO;
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.LikeTableDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.UserGroupDAO;
import com.pro.emp.dao.VideoInfoDAO;
import com.pro.emp.domain.Department;
import com.pro.emp.domain.Designation;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.LikeTable;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;
import com.pro.emp.domain.UserGroups;
import com.pro.emp.domain.VideoInfo;

public class Util {
	

	public static String getFormat(String str){
		if(str==null)
			return "";
		else 
			return str;
	}
	
	public static String getFormat(Date date){
		if(date==null)
			return "";
		else
			return date.toString();
	}

	public static Timestamp convertDateToTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	public static String getPhotoCreationDate(File jpgFile){
		String dateReturn="";	 
		boolean isFound=false;
		try
		        {
		            Metadata metadata = ImageMetadataReader.readMetadata( jpgFile );
		           
		            for (Directory directory : metadata.getDirectories()) {
		                for (Tag tag : directory.getTags()) {
		                	//System.out.println( tag.getTagName()+" : " + tag.getDescription());
		                	if(tag.getTagName().equalsIgnoreCase("Date/Time Original")){
		                		
		                      dateReturn =  tag.getDescription();
		                      String[] spl = dateReturn.split(":");
		                     // System.out.println( "  size " + spl.length);
		                      if(spl.length==4){
		                    	  DateFormat df = 
		                              new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		                          Date d = df.parse(dateReturn);
		                          Calendar i = Calendar.getInstance();
		                          i.setTimeInMillis(d.getTime());
		                          //System.out.println(i.get());
		                          //2013:06:11 13:04:35
		                          Timestamp tm=new Timestamp(d.getTime());
		        	        	  String ss=""+tm.toString();
		        	        	  ss=ss.substring(0,19);
		        	        	  String ssss= ss.substring(0,10);
		        	        	  System.out.println(" sss " + ssss);
		        	        	
		        	        	  String ssss2= ss.substring(11,19);
		        	        	  ssss2=ssss2.replaceAll(" ","0");
		        	        	  System.out.println(" sss2 " + ssss2);
		        	        	  ssss=ssss.replaceAll(" ","0");
		        	        	  dateReturn=ssss+" "+ssss2;
		                      //    dateReturn=ss;//""+ i.get(Calendar.YEAR)+":" + i.get(Calendar.MONTH)+":"+i.get(Calendar.DATE)+ " " + i.get(Calendar.HOUR) + ":"+i.get(Calendar.MINUTE)+":"+i.get(Calendar.SECOND);
		                          dateReturn = dateReturn.replaceAll("-", "-");
		                         // dateReturn = dateReturn.replaceAll(" ", "0");
		                    	  break;
		                      }else{
		                    	  String ssss= dateReturn.substring(0,10);
		        	        	  System.out.println(" sss " + ssss);
		        	        	
		        	        	  String ssss2= dateReturn.substring(11,19);
		        	        	  System.out.println(" sss2 " + ssss2);
		        	        	  ssss=ssss.replaceAll(" ","0");
		        	        	  ssss2=ssss2.replaceAll(" ","0");
		        	        	  dateReturn=ssss+" "+ssss2;
		                    	  dateReturn = dateReturn.replaceFirst(":", "-");
		                    	  dateReturn = dateReturn.replaceFirst(":", "-");
		                    	  break;
		                      }
		                	}
		                }
		               
		            }

		        }
		        catch( Exception e )
		        {
		            e.printStackTrace();
		        }
		     return   dateReturn;
	}
	public static Date getFormatedDate(String dt){
		Date date=null;
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = (Date) dateFormat1.parse(dt); 
		} catch(Exception e){
			
			try {
				date = (Date) dateFormat2.parse(dt); 
			}
			catch(Exception ex){
				return null;
			}
		}
		return date;
	}
	public static void main(String args[]){
		//convertStringToDate("sS");
	}
	public static Date convertStringToDate(String date){
		//date="2014-03-15 20:17:26.192";
		Date d=null;
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:s.S");
		try{
			d= (Date)dateFormat1.parse(date);
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		System.out.println(" date " + d);
		System.out.println( " date " + d.getTime());
		Calendar cc = Calendar.getInstance();
		cc.setTime(d);
		System.out.println(" " + cc.getTime() + " " + cc.get(Calendar.MILLISECOND));
		
		return d;
	}
	public static  HashMap<String, String> getAllEmployeeIdByName(){
		
		HashMap<String,String> map=new HashMap<String, String>();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		try {
			List<EmpInfo> info= EmpInfoDAO.getAllEmployeesInfo(con);
			for(EmpInfo i:info){
				map.put(i.getEmpid(), i.getEmpName());
			}
		
		}catch(Exception ex){
			
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		return map;
	}
	
	public static  HashMap<String, String> getAllEmployeeInternalIdByName(){
		
		HashMap<String,String> map=new HashMap<String, String>();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		try {
			List<EmpInfo> info= EmpInfoDAO.getAllEmployeesInfo(con);
			for(EmpInfo i:info){
				map.put(i.getId(), i.getEmpName());
			}
		
		}catch(Exception ex){
			
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		return map;
	}
	
	  public static void CreateThumbNail(File file,String thumbUrl,int width,int height) {
	    try{
		    BufferedImage originalImage = ImageIO.read(file);
		   
		    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		          int  IMG_HEIGHT = height;
		          int IMG_WIDTH =  width;
		    BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_HEIGHT,IMG_WIDTH);
		    ImageIO.write(resizeImageJpg, "jpg", new File(thumbUrl));
		            originalImage.flush();
		            resizeImageJpg.flush();
		            System.gc();

	    }catch(IOException e){
	    	System.out.println(e.getMessage());
	        System.out.println("Not Created:"+thumbUrl);
	    }
	 }
	  public static void CreateThumbNailMediumn(File file,String thumbUrl) {
		    try{
			    BufferedImage originalImage = ImageIO.read(file);
			   
			    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			          int  IMG_HEIGHT =  Integer.parseInt("200");
			          int IMG_WIDTH =  Integer.parseInt("200");
			    BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_HEIGHT,IMG_WIDTH);
			    ImageIO.write(resizeImageJpg, "jpg", new File(thumbUrl));
			            originalImage.flush();
			            resizeImageJpg.flush();
			            System.gc();

		    }catch(IOException e){
		    	System.out.println(e.getMessage());
		        System.out.println("Not Created:"+thumbUrl);
		    }
		 }

	  public static void CreateBigThumbNail(File file,String thumbUrl) {
		    try{
			    BufferedImage originalImage = ImageIO.read(file);
			    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			          int  IMG_HEIGHT =  Integer.parseInt("200");
			          int IMG_WIDTH =  Integer.parseInt("200");
			    BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_HEIGHT,IMG_WIDTH);
			    ImageIO.write(resizeImageJpg, "jpg", new File(thumbUrl));
			            originalImage.flush();
			            resizeImageJpg.flush();
			            System.gc();

		    }catch(IOException e){
		    	System.out.println(e.getMessage());
		        System.out.println("Not Created:"+thumbUrl);
		    }
		 }
	  
	  private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_HEIGHT,int IMG_WIDTH){
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	    System.gc();
		return resizedImage;
	}
	  
	  public static  List<PhotoAlbum> getAllPhotoAlbulmByUser(String id){
			
		  List<PhotoAlbum> info = new ArrayList<PhotoAlbum>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoAlbumDAO.getAlbumByUserId(id);
				//SortComments sort=new SortComments("PhotoAlbumDisplaySort1");
				///SortComments sort=new SortComments("PhotoAlbumDisplaySort1");

				//	Collections.sort(info,sort);
	
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
		}
	  public static List<PhotoInfo> getPhotosByAlbumId(String id){
		  List<PhotoInfo> info = new ArrayList<PhotoInfo>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				
				info= PhotoInfoDAO.getPhotoByAlbumId(id, con);
				SortPhotos s=new SortPhotos("PhotoAlbum");
				Collections.sort(info,s);
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
	  }
	  public static List<PhotoAlbumInfo> getAllPhotoAlbumns(String empId){
		  List<PhotoAlbumInfo> info = new ArrayList<PhotoAlbumInfo>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoAlbumDAO.getAllAlbums(con, empId);
				  /*for (Iterator<PhotoAlbumInfo> i = info.iterator(); i.hasNext(); )
				    {
						System.out.println(i.next());

				    }*/
			//	SortComments sort=new SortComments("PhotoAlbumDisplaySort");
			//		Collections.sort(info,sort);
//
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
	  }
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> getAlbumUserId( ){
		  List<PhotoAlbumInfo> info = new ArrayList<PhotoAlbumInfo>();
		  List<String> info1 = new ArrayList<String>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoAlbumDAO.getAllAlbums1(con);
				  /*for (Iterator<PhotoAlbumInfo> i = info.iterator(); i.hasNext(); )
				    {
						System.out.println(i.next());

				    }*/
			//	SortComments sort=new SortComments("PhotoAlbumDisplaySort");
			//		Collections.sort(info,sort);
				
				//Map=EmpInfoDAO.getEmpDetails(con);
				/* for(int i=0;i<info.size();i++) {
					    HashMap hMap=new HashMap();
					    hMap.put("Data", info);
					   
					}*/
				for(PhotoAlbumInfo pa:info)
				{
					if(!info1.contains(pa.getUserId()))
					{
					info1.add(pa.getUserId());
						
					}
					System.out.println("Emp Id:"+pa.getUserId()+"and Emp Name:"+ pa.getUserName());
				}
				return info1;

			}catch(Exception ex){
				
			}
			

			finally{
				CassandraDB.freeConnection(con);
			}
			return null;
			
			
			
			
	  }
	  public static List<String> getUserAlbums( ){
		  List<PhotoAlbumInfo> info = new ArrayList<PhotoAlbumInfo>();
		  List<String> info1 = new ArrayList<String>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoAlbumDAO.getAllAblumnsWithLatest(con);
				  /*for (Iterator<PhotoAlbumInfo> i = info.iterator(); i.hasNext(); )
				    {
						System.out.println(i.next());

				    }*/
			//	SortComments sort=new SortComments("PhotoAlbumDisplaySort1");
				///SortComments sort=new SortComments("PhotoAlbumDisplaySort1");

				//	Collections.sort(info,sort);
				
				//Map=EmpInfoDAO.getEmpDetails(con);
				/* for(int i=0;i<info.size();i++) {
					    HashMap hMap=new HashMap();
					    hMap.put("Data", info);
					   
					}*/
				for(PhotoAlbumInfo pa:info)
				{
					if(!info1.contains(pa.getIdPhotoAlbum()))
					{
					info1.add(pa.getIdPhotoAlbum());
						
					}
					System.out.println("AlbumId:"+pa.getIdPhotoAlbum());				}
				return info1;

			}catch(Exception ex){
				
			}
			

			finally{
				CassandraDB.freeConnection(con);
			}
			return null;
			
			
			
			
	  }
	  
	  
	  
	  
	  public static PhotoAlbumInfo getPhotoAlbumnsInfoById(String id){
		  PhotoAlbumInfo info = null;
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoAlbumDAO.getAlbumInfoById(id,con);
				
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
	  }
	  
	  public static Set<Department> getAllDept(){
		  Set<Department> info = new HashSet<Department>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= new DepartmentDAO().getAllDepartment();
				
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
	  }
	  public static Set<Designation> getAllDesignation(){
		  Set<Designation> info = new HashSet<Designation>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= new DesignationDAO().getAllDesg();
				
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
	  }
	  
	  public static  List<PhotoInfo> getAllPhotoByAlbumId(String id){
			
		  List<PhotoInfo> info = new ArrayList<PhotoInfo>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= PhotoInfoDAO.getPhotoByAlbumId(id, con);
				
				SortPhotos s=new SortPhotos("PhotoAlbum");
				Collections.sort(info,s);
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
		}
	  @SuppressWarnings("unchecked")

	  public static PhotoAlbumInfo getPhotoAlbumById(String id){
		  
		  PhotoAlbumInfo al=null;
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				al= PhotoAlbumDAO.getAlbumInfoById(id, con);
				
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return al;
	  }
// public static List<Group> getAllGroupsByUserId(String userId){
//		  
//		  List<Group> gr=null;
//			//ConnectionPool c= ConnectionPool.getInstance();
//			Session con =CassandraDB.getCassConnection();
//			try {
//				GroupDAO ob=new GroupDAO();
//				gr= ob.getGroupsForOwner(con, userId);
//				
//			
//			}catch(Exception ex){
//				
//			}
//			finally{
//				CassandraDB.freeConnection(con);
//			}
//			return gr;
//	  }
// public static int getMemberCountForGroupId(String groupId){
//	  
//	  List<UserGroups> gr=null;
//		//ConnectionPool c= ConnectionPool.getInstance();
//		Session con =CassandraDB.getCassConnection();
//		try {
//			UserGroupDAO ob=new UserGroupDAO();
//			gr= ob.getUsersForGroup(con, groupId);//(con, userId);
//			
//		
//		}catch(Exception ex){
//			
//		}
//		finally{
//			CassandraDB.freeConnection(con);
//		}
//		return gr.size();
// }
// public static Group getGroupById(String groupId){
//	  
//	  Group gr=null;
//		//ConnectionPool c= ConnectionPool.getInstance();
//		Session con =CassandraDB.getCassConnection();
//		try {
//			GroupDAO ob=new GroupDAO();
//			gr= ob.getGroupById(con, groupId);
//			
//		
//		}catch(Exception ex){
//			
//		}
//		finally{
//			CassandraDB.freeConnection(con);
//		}
//		return gr;
// }
// 
// public static List<UserGroups> getListOfUsersForGroup(String groupId){
//	  
//	  List<UserGroups> gr=null;
//		//ConnectionPool c= ConnectionPool.getInstance();
//		Session con =CassandraDB.getCassConnection();
//		try {
//			UserGroupDAO ob=new UserGroupDAO();
//			gr= ob.getUsersForGroup(con, groupId);//(con, groupId);
//			
//		
//		}catch(Exception ex){
//			
//		}
//		finally{
//			CassandraDB.freeConnection(con);
//		}
//		return gr;
//}
// 
 
	  public static PhotoAlbum getPhotoAlbumById_(String id){
		  
		  PhotoAlbum al=null;
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				al= PhotoAlbumDAO.getAlbumById(id, con); // getAlbumInfoById(id, con);
				
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return al;
	  }
	  
		public static  EmpInfo getEmployeeById(String id){
			
			EmpInfo info=null;
			//ConnectionPool c= ConnectionPool.getInstance();
			try {
				info= EmpInfoDAO.getEmpById( id);
			
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return info;
		}
		
		/**
		 * Impoertant funcgtion to rezie the photo as per aspect ratio
		 * @param file
		 * @param newht
		 * @param newwt
		 * @throws IOException
		 */
		public static double[] resize_image(File file,int newht,int newwt,
				int maxht,int maxwt,int minht,int minwt) throws IOException{
			
			BufferedImage iimg = ImageIO.read(file);
			  double _newdim[]={iimg.getHeight(),iimg.getWidth()};
			int height = iimg.getHeight();
			   int width = iimg.getWidth();
			  // System.out.println(" original " + height + " : " + width);
			   
			   if (height > maxht || width > maxwt) {
				   
				      // Use Ratios to constraint proportions.
				   double old_ratio = (double)height /(double) width;
				     // int old_ratio1 = height % width;
				    //  System.out.println(" ddd " + old_ratio1);
				     // old_ratio=old_ratio+old_ratio1;
				      double min_ratio = (double)minht / (double)minwt;
				      // If it can scale perfectly.
				     // System.out.println(" newhiehgt" + newht + " : " + newwt);
				     // System.out.println(" ra : " + old_ratio + " : " + min_ratio);
				      if (old_ratio == min_ratio) {
				      //  System.out.println(" ext " + minht + " : " + minwt);
				      } 
				      else {
				    	  
				      
				        //var newdim = [img.height, img.width];
				        _newdim[0]=minht;
				        //newdim[0] = minht;  // Sort out the height first
				        // ratio = ht / wt => wt = ht / ratio.
				        _newdim[1]=_newdim[0]/ old_ratio;
				        //newdim[1] = newdim[0] / old_ratio;
				        // Do we still have to sort out the width?
				      //  System.out.println(" h 1 " + _newdim[1] + " : h 0 " + _newdim[0]);
				        if(_newdim[1]>maxwt){
				        	_newdim[1]=minwt;
				        	_newdim[0]=_newdim[1]*old_ratio;
				        }
				       
				      
				        
				      }
			   }
			   return _newdim;
		}
		
	public static HashMap<String, Integer> createFullImage(File data,String destinationPath) throws FileNotFoundException, IOException{
		
		boolean isDone=false;
		BufferedImage bi = null;

		bi = ImageIO.read(data);

		Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) iter.next();

		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

		// reduced quality.
		iwp.setCompressionQuality(0.9f);

		File file = new File(destinationPath);

		FileImageOutputStream output = null;

		output = new FileImageOutputStream(file);

		writer.setOutput(output);

		IIOImage image = new IIOImage(bi, null, null);
		writer.write(null, image, iwp);
		//
		//File f = new File("E:/Vinod/Docs/Pics/krishna_01.jpg");
		   //BufferedImage image = ImageIO.read(file);
		BufferedImage iimg = ImageIO.read(file);
		   int height = iimg.getHeight();
		   int width = iimg.getWidth();
		   int maxWidth=800;
			int maxHieght=600;
		   if(width>800){
			
				
			//	System.out.println("www width " + (width*maxWidth)/height);
				width = (width*maxWidth)/height;
				if(width>maxWidth)
					width = (width*maxWidth)/height;
				if(width>maxWidth)
					width =maxWidth;
					
		   }
		   if(height>600){
			  // System.out.println(" hhh height " + ( height*maxHieght)/width);
			  height=( height*maxHieght)/width;
			  if(height>maxHieght)
				  height=( height*maxHieght)/width;
			  
			  if(height> maxHieght)
				  height=maxHieght;
		   }
		   System.out.println("Height : "+ height);
		   System.out.println("Width : "+ width);
		   //
		   
		writer.dispose();
		isDone=true;
		
		HashMap<String,Integer> d= new HashMap<String, Integer>();
		d.put("width",width);
		d.put("height", height);
		
		return d;
	}
	
	
public static  List<LikeTable> getAllLikesForId(String itemId){
		
		List<LikeTable> likes=null;
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		try {
			likes= LikeTableDAO.getLikesForId(con, itemId);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		return likes;
	}
	
//public static String getCurrentTimestamp(){
//	Calendar currentDate = Calendar.getInstance();
//	Timestamp t=new Timestamp(currentDate.getTime().getTime());
//	String dd=""+t;
//	//System.out.println(" time stamp before update " + dd);
//	String	str= dd.substring(0,dd.indexOf("."));
//	//System.out.println(" time stamp after update " + str);
//	return str;
//}
public static Date getCurrentTimestamp(){
	Calendar currentDate = Calendar.getInstance();
	
	return currentDate.getTime();
}
public static String getCurrentTimestamp(Date tt){
	if(tt==null)
		return "";
	Calendar currentDate = Calendar.getInstance();
	currentDate.setTime(tt);
	Timestamp t=new Timestamp(currentDate.getTime().getTime());
	String dd=""+t;
	//System.out.println(" time stamp before update " + dd);
	String	str= dd.substring(0,dd.indexOf("."));
	//System.out.println(" time stamp after update " + str);
	return str;
}
public static String getCurrentTimestamp10Digit(Date tt){
	if(tt==null)
		return "";
	Calendar currentDate = Calendar.getInstance();
	currentDate.setTime(tt);
	Timestamp t=new Timestamp(currentDate.getTime().getTime());
	String dd=""+t;
	//System.out.println(" time stamp before update " + dd);
	String	str= dd.substring(0,dd.indexOf("."));
	str=str.substring(0,10);
	//System.out.println(" time stamp after update " + str);
	return str;
}
public static String getCommentTime(Timestamp s){
	Calendar c= Calendar.getInstance();
	Timestamp curre= new Timestamp(c.getTime().getTime());
	if(s!=null){
	int days= (int)(curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
	int hours= (int)(curre.getTime()-s.getTime())/(60 * 60 * 1000);
	int min= (int)(curre.getTime()-s.getTime())/(60*1000);
	int sec = (int)(curre.getTime()-s.getTime())/(10*60*1000);
	if(days!=0){
		return ""+days+ " Days ago"; 
	}else if(hours !=0){
		return ""+hours + " Hours ago";
		
	}else if(min !=0){
		return ""+min + " Minutes ago";
	}else
		return "" + 1 + " Minute ago";
	
	}else{
		return "";
	}
}

public static String getPhotoTime(Timestamp s){
	Calendar c= Calendar.getInstance();
	Timestamp curre= new Timestamp(c.getTime().getTime());
	int days= (int)(curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
	int hours= (int)(curre.getTime()-s.getTime())/(60 * 60 * 1000);
	int min= (int)(curre.getTime()-s.getTime())/(60*1000);
	Date d= new Date(s.getTime());
	c.setTime(d);
	String month="";
	switch(c.get(Calendar.MONTH)){
		case 0: month="January";break;
		case 1: month="Febraury";break;
		case 2: month="March";break;
		case 3: month="April";break;
		case 4: month="May";break;
		case 5: month="June";break;
		case 6: month="July";break;
		case 7: month="August";break;
		case 8: month="September";break;
		case 9: month="October";break;
		case 10: month="November";break;
		case 11: month="December";break;
	}
	return month+ " "+ c.get(Calendar.DATE);
}

public static String getTimeToDelete(Date s){
	Calendar c= Calendar.getInstance();
	Date d= new Date(s.getTime());
	String time="";
	c.setTime(d);
	String month="";
	String day="";
	
	//2014-03-13 21:51:32.237
	time=""+c.get(Calendar.YEAR)+"-"+getMonth(c.get(Calendar.MONTH)+1)+"-"+getMonth(c.get(Calendar.DATE))+ " "+getMonth(c.get(Calendar.HOUR_OF_DAY))+":"+getMonth(c.get(Calendar.MINUTE))+":"+getMonth(c.get(Calendar.SECOND)) + "."+getMonth(c.get(Calendar.MILLISECOND));
	return time;
	
}

public static String getMonth(int i){
	
		switch(i){
		case 1: return "01";
		case 2: return "02";
		case 3: return "03";
		case 4: return "04";
		case 5: return "05";
		case 6: return "06";
		case 7: return "07";
		case 8: return "08";
		case 9: return "09";
		default: return String.valueOf(i);
	}
}
	public static String getPhotoTimeFull(Timestamp s){
		Calendar c= Calendar.getInstance();
		Date d= new Date(s.getTime());
		c.setTime(d);
		String month="";
		String day="";
		switch(c.get(Calendar.MONTH)){
			case 0: month="January";break;
			case 1: month="Febraury";break;
			case 2: month="March";break;
			case 3: month="April";break;
			case 4: month="May";break;
			case 5: month="June";break;
			case 6: month="July";break;
			case 7: month="August";break;
			case 8: month="September";break;
			case 9: month="October";break;
			case 10: month="November";break;
			case 11: month="December";break;
		}
		//System.out.println(" c.get(Calendar.DAY_OF_WEEK "+c.get(Calendar.DAY_OF_WEEK));
		switch(c.get(Calendar.DAY_OF_WEEK)){
		case 1: day="Sunday";break;
		case 2: day="Monday";break;
		case 3: day="Tuesday";break;
		case 4: day="Wednesday";break;
		case 5: day="Thursday";break;
		case 6: day="Friday";break;
		case 7: day="Saturday";break;
		}

	
	return day+", "+month+" "+c.get(Calendar.DATE)+ ", "+ c.get(Calendar.YEAR)+ " at "+ c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
}
	
	public static String getProperTimeINGroup(Timestamp s){
		Calendar c= Calendar.getInstance();
		Date d= new Date(s.getTime());
		c.setTime(d);
		String month="";
		String day="";
		switch(c.get(Calendar.MONTH)){
			case 0: month="January";break;
			case 1: month="Febraury";break;
			case 2: month="March";break;
			case 3: month="April";break;
			case 4: month="May";break;
			case 5: month="June";break;
			case 6: month="July";break;
			case 7: month="August";break;
			case 8: month="September";break;
			case 9: month="October";break;
			case 10: month="November";break;
			case 11: month="December";break;
		}
		//System.out.println(" c.get(Calendar.DAY_OF_WEEK "+c.get(Calendar.DAY_OF_WEEK));
		switch(c.get(Calendar.DAY_OF_WEEK)){
		case 1: day="Sunday";break;
		case 2: day="Monday";break;
		case 3: day="Tuesday";break;
		case 4: day="Wednesday";break;
		case 5: day="Thursday";break;
		case 6: day="Friday";break;
		case 7: day="Saturday";break;
		}

	
	return month+" "+c.get(Calendar.DATE)+ ", "+ c.get(Calendar.YEAR)+ " at "+ c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);
}
	public static String getBirthday(Timestamp s){
		Calendar c= Calendar.getInstance();
		Date d= new Date(s.getTime());
		c.setTime(d);
		String month="";
		String day="";
		switch(c.get(Calendar.MONTH)){
			case 0: month="January";break;
			case 1: month="Febraury";break;
			case 2: month="March";break;
			case 3: month="April";break;
			case 4: month="May";break;
			case 5: month="June";break;
			case 6: month="July";break;
			case 7: month="August";break;
			case 8: month="September";break;
			case 9: month="October";break;
			case 10: month="November";break;
			case 11: month="December";break;
		}
		
	
	return month+", "+c.get(Calendar.DATE);
}

public static String getPhotoCreationTime(Timestamp s){
	Calendar c= Calendar.getInstance();
	Timestamp curre= new Timestamp(c.getTime().getTime());
	
	Calendar cc= Calendar.getInstance();
	cc.setTimeInMillis(s.getTime());
	int monthInt = cc.get(Calendar.MONTH);
	//System.out.println(" Montdh is " + monthInt);
	String month="";
	switch(monthInt){
		case 0: month="January";
			break;
		case 1: month="Febraury";
		break;
		case 2: month="March";
		break;
		case 3: month="April";
		break;
		case 4: month="May";
		break;
		case 5: month="June";
		break;
		
		case 6: month="July";
		break;
		case 7: month="August";
		break;
		case 8: month="September";
		break;
		case 9: month="october";
		break;
		case 10: month="November";
		break;
		case 11: month="December";
		break;
	}
	int days= cc.get(Calendar.DATE);
	int year = cc.get(Calendar.YEAR);
	
	
	return ""+month + "-"+days + "-"+year;
}

public static void rotatePhoto( String inputFileLocation,String outputFileLocation )throws IOException {
	double angleOfRotation=90.0;
	 
	
	System.out.println("Reading Original File : "+inputFileLocation);
	 
	BufferedImage originalImage=readImage(inputFileLocation);
	 
	System.out.println("...Done\n");
	 
	System.out.println("Rotating the original Image By: "+angleOfRotation+" degrees");
	BufferedImage processedImage=rotateMyImage(originalImage, angleOfRotation);
	System.out.println("...Done\n");
	
	 
	System.out.println("Writing the rotated image to: "+outputFileLocation);
	writeImage(processedImage, outputFileLocation, "jpg");
	System.out.println("...Done");
	}

public static BufferedImage readImage(String fileLocation) {
	BufferedImage img = null;
	try {
	img = ImageIO.read(new File(fileLocation));
	} catch (IOException e) {
	e.printStackTrace();
	}
	return img;
	}
public static void writeImage(BufferedImage img, String fileLocation,
		String extension) {
		try {
		BufferedImage bi = img;
		File outputfile = new File(fileLocation);
		ImageIO.write(bi, extension, outputfile);
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
 
	public static BufferedImage rotateMyImage(BufferedImage img, double angle) {
	int w = img.getWidth();
	int h = img.getHeight();
	BufferedImage dimg =new BufferedImage(w, h, img.getType());
	Graphics2D g = dimg.createGraphics();
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
	RenderingHints.VALUE_ANTIALIAS_ON);
	g.rotate(Math.toRadians(angle), w/2, h/2);
	 
	g.drawImage(img, null, 0, 0);
	return dimg;
	}
	public static  List<VideoInfo> getAllVideoByvideoId(String id){
		
		  List<VideoInfo> info = new ArrayList<VideoInfo>();
			//ConnectionPool c= ConnectionPool.getInstance();
			Session con =CassandraDB.getCassConnection();
			try {
				info= VideoInfoDAO.getVideoByVideoId(id, con);
				
				SortVideos s=new SortVideos("PhotoAlbum");
				Collections.sort(info,s);
			
			}catch(Exception ex){
				
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			return info;
		
	 

}
public static List<VideoInfo> getAllVideos(String empId){
	System.out.println("control comes in");
	  List<VideoInfo> info = new ArrayList<VideoInfo>();
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		try {
			System.out.println("control in to try");
			info= VideoInfoDAO. getAllUserVideo(con, empId);
			  /*for (Iterator<PhotoAlbumInfo> i = info.iterator(); i.hasNext(); )
			    {
					System.out.println(i.next());

			    }*/
			//SortComments sort=new SortComments("VideoSort");
			//	Collections.sort(info,sort);
				System.out.println( " list " + info.size());
		
		}catch(Exception ex){
			
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		return info;
}
}
