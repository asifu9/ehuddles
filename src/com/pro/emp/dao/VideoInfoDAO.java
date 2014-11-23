package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.domain.PhotoInfoDomain;
import com.pro.emp.domain.VideoInfo;

public class VideoInfoDAO extends EmpCommanDAO{

	public static String createVideo(VideoInfo info,Session session){
		 UUID id= java.util.UUID.randomUUID();
		int result=0;
		try
	    {
			Calendar currentDate=Calendar.getInstance();
			 SimpleDateFormat formatter= 
	        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
	        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
	        	  dt.setTime(currentDate.getTime().getTime());
	        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
	        	  String ss=""+tm;
	        	  ss=ss.substring(0,19);
		
	        String inssql="INSERT INTO empInfoDB.videoinfo(" +
	        						"key,"+
	                                "videoPath," +
	                                "description,createdOn,ownerId,indicator) values(?,?,?,?,?,?)";// +
	                               // " (\'"+id+"\',\'"+info.getVideoPath()+"\',\'"+info.getDescription()+"\',
	        						//\'"+ss+"\',\'"+info.getOwnerId()+"\',1)";
	       // System.out.println(" sql is " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    						info.getVideoPath(),
	    						info.getDescription(),
	    						ss,
	    						info.getOwnerId(),1));
	       
	       // rs=ps.getGeneratedKeys();
	       // session.commit();
			
			result=1;
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	        System.out.println("Insertion Error");
	        se.printStackTrace();
	        result=3;
	    }
		return ""+id;
	}
	
	public static List<VideoInfo> getVideoByVideoId(String id,Session session){
		List<VideoInfo> list=new ArrayList<VideoInfo>();
    	try
        {
    		
	        String inssql="select key,VideoPath,description,createdOn from  empInfoDB.videoinfo where idVideoInfo=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setVideoPath(rs.getString(1));
        		info.setDescription(rs.getString(2));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setLikeInfo(LikeTableDAO.getLikesForId(session, info.getIdVideoInfo()));
        		
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static VideoInfo getVideoByVideoIdSize(String id,Session session,String path){
		VideoInfo list=null;//new ArrayList<PhotoInfo>();
    	try
        {
    		
	        String inssql="select key,videoPath,description,createdOn, from  empInfoDB.videoinfo where idvidepInfo=? and videoPath=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id,path));
	        for(Row rs:rss)
        	{
        		VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setVideoPath(rs.getString(1));
        		info.setDescription(rs.getString(2));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		
        		list=info;
        		break;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

//	public static List<PhotoInfo> getPhotoByAlbumId(String id,Session session){
//		List<PhotoInfo> list=new ArrayList<PhotoInfo>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//    		
//	        String inssql="select key,idPhotoAlbum,photoPath from  photoinfo where idPhotoAlbum=\'"+id+"\' and indicator=1";
//            ps=session.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PhotoInfo info=new PhotoInfo();
//        		info.setIdPhotoInfo(rs.getString(1));
//        		info.setIdPhotoAlbum(rs.getString(2));
//        		info.setPhotoPath(rs.getString(3));
//        		list.add(info);
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
	public static List<VideoInfo> getVideoByAlbumIdForPostDisplay(String id,Session session,Timestamp t){
		List<VideoInfo> list=new ArrayList<VideoInfo>();
    	String st=""+t;
    	System.out.println(" st "+t);
    	st=st.substring(0, 19);
    	try
        {
    		
	        String inssql="select key,idVideoInfo,videoPath,createdOn,ownerId from  empInfoDB.videoinfo where idvideoInfo=? and indicator=1 and createdOn <= ?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id,st));
	        for(Row rs:rss)
        	{
	        	VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setVideoPath(rs.getString(1));
        		info.setDescription(rs.getString(2));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	@SuppressWarnings("unchecked")
	
	public static boolean deleteById(String id,Session session){
		boolean result=false;
    	try
        {
    		
	        String inssql="delete from  empInfoDB.videoinfo where key=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id));
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	public static boolean updateDescriptoin(String id,Session session,String description){
		boolean result=false;
    	try
        {
    		
	        String inssql="update empInfoDB.videoinfo set description=? where key=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(description,id));
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	public static PhotoInfoDomain getPhotoDescById(String id,Session session){
		PhotoInfoDomain description=null;
    	try
        {
    		String albumId="";
	        String inssql="select description,createdOn,idPhotoAlbum,key,photoPath from  empInfoDB.photoinfo where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
            
            
       
        	{
        		
        		description=new PhotoInfoDomain();
        		description.setId(rs.getString(3));
        		description.setDate(Util.getPhotoTime(Util.convertDateToTimestamp(rs.getDate(1))));
        		description.setPath(rs.getString(4));
        		//System.out.println(" fulld date "+ Util.getPhotoTimeFull(rs.getTimestamp(2)));
        		description.setFullTime(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(1))));
        		description.setDescription((rs.getString(0)==null?"":rs.getString(0)));
        		albumId=rs.getString(2);
        		description.setPath(rs.getString(4));
        		
        	}
        	 String ssl="select id from empInfoDB.photoAlbum where key=\'"+albumId+"\'";
        	 com.datastax.driver.core.PreparedStatement statement2 =  session.prepare(inssql);
 	    	BoundStatement boundStatement2 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement2);
 	    	ResultSet rss2=session.execute(boundStatement2.bind(albumId));
 	        for(Row rs:rss2)
        	{
        		 description.setOwner(rs.getString(0));
        	 }
        	 
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return description;
		
	}
	
	
	public static  int getvideoCountByVideoId(String id,Session session){
		List<VideoInfo> list=new ArrayList<VideoInfo>();
    	int count=0;
    	try
        {
    		
	        String inssql="select count(*) from  empInfoDB.videoinfo where idvideoInfo=? and indicator=1";
	        System.out.println(" here is the count man " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		count=rs.getInt(0);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return count;
		
	}
	
	public static VideoInfo getBasicVideoInfoById(String id,Session session){
		VideoInfo list=null;
    	try
        {
    		
	        String inssql="select key,ownerId from  empInfoDB.VideoInfo where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setOwnerId(rs.getString(1));
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static VideoInfo getVideoByIdDynamic(String id,Session session){
		VideoInfo list=null;
    	try
        {
    		
	        String inssql="select key,videoPath,description,createdOn,ownerId from  empInfoDB.videoinfo where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setVideoPath(rs.getString(1));
        		info.setDescription(rs.getString(2));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setLikeInfo(LikeTableDAO.getLikesForIdDynamic(session, info.getIdVideoInfo()));
        		
        		info.setOwnerId(rs.getString(4));
        		list=info;
        	}     
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static VideoInfo getVideoByIdSimple(String id,Session session){
		VideoInfo list=null;
    	try
        {
    		
	        String inssql="select key,ownerId,videoPath from  empInfoDB.videoinfo where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		VideoInfo info=new VideoInfo();
        		info.setIdVideoInfo(rs.getString(0));
        		info.setOwnerId(rs.getString(1));
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
	}
	public static List<VideoInfo> getAllUserVideo(Session session,String empId){
		System.out.println("sessiontrol in to method all video");
		List<VideoInfo> list=new ArrayList<VideoInfo>();
		try
	    {
	       
	    	  String inssql="select key,videoPath,createdOn,Description,ownerId from  empInfoDB.videoinfo where ownerId=? and indicator=1";
	    	  com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(empId));
		        for(Row rs:rss)
	     	{
	     		System.out.println("sessiontrol in to the loop" + rs.getString(2));
	     		VideoInfo info=new VideoInfo();
	     		info.setIdVideoInfo(rs.getString(0));
	     		info.setVideoPath(rs.getString(1));
	     		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(2)));
	     		info.setDescription("");
	     		info.setOwnerId(rs.getString(3));
		     	list.add(info);
		     	System.out.println("list data :"+list.size());
	     	}
	        
	    }
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
}
