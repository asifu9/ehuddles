package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;
import com.pro.emp.domain.PostLikeTable;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class PostLikeDAO  extends EmpCommanDAO{


	public static PostLikeTable createPostLike( PostLikeTable like,Session session) 
	{
		int result=0;
		Calendar currentDate=Calendar.getInstance();
		 SimpleDateFormat formatter= 
       	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
       
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO  empInfoDB.likePost(" +
	    						"key,"+
	                            "postId," +
	                            "likedUserId,likedDate,indicator "+
	                            ") values(?,?,?,?,?)";
	                            //"(\'"+id+"\',\'"+like.getPostId()+"\',\'"+like.getLikedUserId()+"\',\'"+ss+"\',1)";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			like.getPostId(),
	    			like.getLikedUserId(),
	    			Calendar.getInstance().getTime(),
	    			1
	    			));
	        
	       like.setKey(id.toString());
	       
	       // session.commit();
	    	result=1;
	    }
	    catch(Exception se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(session);
	        se.printStackTrace();
	    }
	    
	    return like;
	  }
	
	public static java.util.List<PostLikeTable> getLikesForPostId(Session session,String postId){
		PostLikeTable lk=null;
		java.util.List<PostLikeTable> likes=new ArrayList<PostLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM  empInfoDB.likePost WHERE postId =? and indicator=1  ALLOW FILTERING";
		try{
			
    		
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId));
	        for(Row rs:rss){
	    		lk=new PostLikeTable();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostId(rs.getString(1));
	    		lk.setLikedUserId(rs.getString(2));
	    		EmpBasicInfo i = CacheRecords.getInstance().getCacheData( rs.getString(2));
	    		if(i.getImagePath()!=null)
	    			lk.setUserPhotoPath(i.getImagePath().getPhotoPath());
	    		lk.setUserName(i.getEmpName());
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}

	public static boolean getLikeFOrPostandUser(Session session,String postId,String userId){
		boolean found=false;
		String _sql="select key,postId,likedUserId,likedDate FROM  empInfoDB.likePost WHERE postId =? and likedUserId=? and indicator=1 ALLOW FILTERING";
		try{
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId,userId));
	        for(Row rs:rss){
	        	found=true;
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return found;
	}
	
	public static java.util.List<PostLikeTable> getLikesForPostIdTODelete(Session session,String postId,HashMap<String, String> map,HashMap<String, String> map2){
		PostLikeTable lk=null;
		java.util.List<PostLikeTable> likes=new ArrayList<PostLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM  empInfoDB.likePost WHERE postId =? and indicator=1  ALLOW FILTERING";
		try{
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId));
	        for(Row rs:rss){
	    		lk=new PostLikeTable();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostId(rs.getString(1));
	    		lk.setLikedUserId(rs.getString(2));
	    		lk.setLikedDate(Util.convertDateToTimestamp(rs.getDate(3)));
	    		lk.setUserPhotoPath(map2.get(rs.getString(2)));
	    		lk.setUserName(map.get(rs.getString(2)));
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	public static java.util.List<PostLikeTable> getLikesForPostIdAndUserId(Session session,String postId,String userId){
		PostLikeTable lk=null;
		java.util.List<PostLikeTable> likes=new ArrayList<PostLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM  empInfoDB.likePost WHERE postId =? and likedUserId=? and indicator=1 ALLOW FILTERING";
		try{
		
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId,userId));
	        for(Row rs:rss){
	    		lk=new PostLikeTable();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostId(rs.getString(1));
	    		lk.setLikedUserId(rs.getString(2));
	    		lk.setLikedDate(Util.convertDateToTimestamp(rs.getDate(3)));
	    		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( rs.getString(2));
	    		if(i.getImagePath()!=null)
	    			lk.setUserPhotoPath(i.getImagePath().getPhotoPath());
	    		lk.setUserName(i.getEmpName());
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	public static int deleteLike(Session session, String id)
	
	{
		String _sql="DELETE FROM  empInfoDB.likePost WHERE indicator=1  and key =?";
	//	System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id));
	    	_result=1;
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
}
