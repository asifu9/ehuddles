package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;

public class PhotoCommentsDAO extends EmpCommanDAO{

	
	public static PhotoComments createPhotoComments(PhotoComments comm,Session session){
		String result="";
		UUID id= java.util.UUID.randomUUID();
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
			 //System.out.println(" t stamp " + timeStamp);
			String desc=comm.getCommentDesc().replace("'", "''");
	        String inssql="INSERT INTO  empInfoDB.photoComments (" +
	        						"key,"+
	                                "photoId,"+
	                                "commentUserId," +
	                                "commentUserName," +
	                                "commentUserPhoto," +
	                                "commentDesc," +
	                                "commentDate,indicator" +
	                                ") values (?,?,?,?,?,?,?,?)";// +
//	                                "(\'"+id+"\',\'"+comm.getPhotoId()+"\'," +
//	                                		"\'"+comm.getCommmentUserId()+"\'," +
//	                                		"\'"+comm.getCommentUserName()+"\'," +
//	                                		"\'"+comm.getCommentUserPhoto()+"\'," +
//	                                		"\'"+desc+"\',\'"+ss+"\',1)";
	      // System.out.println(" sql is " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			comm.getPhotoId(),
	    			comm.getCommmentUserId(),
	    			comm.getCommentUserName(),
	    			comm.getCommentUserPhoto(),
	    			desc,
	    			Calendar.getInstance().getTime(),
	    			1
	    			));
	       
			comm.setId(id.toString());           
	       // rs=ps.getGeneratedKeys();
	       // session.commit();
			
			result=""+id;
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	        System.out.println("Insertion Error");
	        se.printStackTrace();
	        comm=null;
	    }
		return comm;
	}
	
	public static List<PhotoComments> getCommentsForId(String photoId,Session session){
		List<PhotoComments> list=new ArrayList<PhotoComments>();
    	try
        {
    		
	        String inssql="select key,photoId,commentUserId,commentUserName,commentUserPhoto,commentDesc,commentDate from   empInfoDB.photoComments   where photoId=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(photoId));
	        for(Row rs:rss)
        	{
        		PhotoComments info=new PhotoComments();
        		info.setId(rs.getString(0));
        		info.setPhotoId(rs.getString(1));
        		info.setCommmentUserId(rs.getString(2));
        		EmpInfo infsss = EmpInfoDAO.getEmpById( info.getCommmentUserId());
        		info.setCommentUserName(rs.getString(3));
        		if(infsss.getImage()!=null)
        			info.setCommentUserPhoto(infsss.getImage().getPhotoPath());
        		info.setCommentDesc(rs.getString(5));
        		info.setCommentDate(Util.convertDateToTimestamp(rs.getDate(6)));
        		Calendar c= Calendar.getInstance();
        		info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
        		info.setLikes(LikeTableDAO.getLikesForId(session, info.getId()));
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

	public static boolean deleteById(String id,Session session){
		boolean result=false;
    	try
        {
    		
	        String inssql="delete from   empInfoDB.photoComments  where key=?";
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
    		
	        String inssql="update photoinfo set description=? where key=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(description,id));
            //ps.setString(1,description);
           // ps.setInt(2,id);
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
	        String inssql="select description,createdOn,idPhotoAlbum,key from  photoinfo where key=? where indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		
        		description=new PhotoInfoDomain();
        		description.setId(rs.getString(3));
        		description.setDate(rs.getString(1));
        		description.setDescription((rs.getString(0)==null?"":rs.getString(0)));
        		albumId=rs.getString(2);
        		
        	}
        	 String ssl="select id from photoAlbum where key=? where indicator=1";
        	 com.datastax.driver.core.PreparedStatement statement1 =  session.prepare(ssl);
 	    	BoundStatement boundStatement1 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement1);
 	    	ResultSet rss1=session.execute(boundStatement1.bind(albumId));
 	        for(Row rs:rss1){
        		 description.setOwner(rs.getString(0));
        	 }
        	 
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return description;
		
	}
	public static void main( String[] arg){
		getCommentsById("fee84422-511d-4ffd-b539-325bebcda9f0",CassandraDB.getCassConnection());
	}
	public static PhotoComments getCommentsById(String commentId,Session session){
		PhotoComments list=null;;
    	try
        {
    		System.out.println(" comment Id = " + commentId);
	        String inssql="select key,photoId,commentUserId,commentDesc from   empInfoDB.photoComments   where key=? and indicator=1";
           // System.out.println(" comment id  " + commentId);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(commentId));
	        for(Row rs:rss)
        	{
	        	System.out.println(" k got record" + rs.getString(0));
        		PhotoComments info=new PhotoComments();
        		info.setId(rs.getString(0));
        		info.setPhotoId(rs.getString(1));
        		info.setCommmentUserId(rs.getString(2));
        		info.setCommentDesc(rs.getString(3));
        		
        		
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	System.out.println(" list " + list);
    	return list;
		
	}
	
}
