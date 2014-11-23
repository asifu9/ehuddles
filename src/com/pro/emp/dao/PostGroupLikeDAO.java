package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import com.pro.cache.CacheRecords;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;
import com.pro.emp.domain.PostGroupComments;
import com.pro.emp.domain.PostGroupLikeTable;
import com.pro.emp.domain.PostLikeTable;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class PostGroupLikeDAO  extends EmpCommanDAO{/*


	public static PostGroupLikeTable createPostLike( PostGroupLikeTable like,Connection con) 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
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
	    String inssql="INSERT INTO likeGroupPost(" +
	    						"key,"+
	                            "postId," +
	                            "likedUserId,likedDate,indicator "+
	                            ") values(\'"+id+"\',\'"+like.getPostId()+"\',\'"+like.getLikedUserId()+"\',\'"+ss+"\',1)";
	    try
	    {
	    	
    		
	    	ps=con.prepareStatement(inssql);
	        
	        
	        ps.executeUpdate();
	        //rs=ps.getGeneratedKeys();
	        
	       like.setKey(""+id);
	       
	       // con.commit();
	    	result=1;
	    }
	    catch(SQLException se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(con);
	        se.printStackTrace();
	    }
	    
	    return like;
	  }
	
	public static java.util.List<PostGroupLikeTable> getLikesForPostId(Connection con,String postId){
		PreparedStatement ps=null;
		ResultSet rs=null;
		PostGroupLikeTable lk=null;
		java.util.List<PostGroupLikeTable> likes=new ArrayList<PostGroupLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM likeGroupPost WHERE postId =\'"+postId+"\' and indicator=1";
		try{
			
    		
			ps = con.prepareStatement(_sql);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		lk=new PostGroupLikeTable();
	    		lk.setKey(rs.getString(1));
	    		lk.setPostId(rs.getString(2));
	    		lk.setLikedUserId(rs.getString(3));
	    		lk.setLikedDate(rs.getDate(4));
	    		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( rs.getString(3));
	    		if(i.getImagePath()!=null)
	    			lk.setUserPhotoPath(i.getImagePath().getPhotoPath());
	    		lk.setUserName(i.getEmpName());
	    		likes.add(lk);
	    	}
    	
		}
		catch(SQLException se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	public static boolean getLikeFOrPostandUser(Connection con,String postId,String userId){
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean found=false;
		String _sql="select key,postId,likedUserId,likedDate FROM likeGroupPost WHERE postId =\'"+postId+"\' and likedUserId='"+userId+"' and indicator=1";
		try{
			
    		
			ps = con.prepareStatement(_sql);
	    	rs=ps.executeQuery();
	    	if(rs.next()){
	    	found=true;
	    	}
    	
		}
		catch(SQLException se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return found;
	}
	
	public static java.util.List<PostGroupLikeTable> getLikesForPostIdTODelete(Connection con,String postId,HashMap<String, String> map,HashMap<String, String> map2){
		PreparedStatement ps=null;
		ResultSet rs=null;
		PostGroupLikeTable lk=null;
		java.util.List<PostGroupLikeTable> likes=new ArrayList<PostGroupLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM likeGroupPost WHERE postId =\'"+postId+"\' and indicator=1";
		try{
			
    		
			ps = con.prepareStatement(_sql);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		lk=new PostGroupLikeTable();
	    		lk.setKey(rs.getString(1));
	    		lk.setPostId(rs.getString(2));
	    		lk.setLikedUserId(rs.getString(3));
	    		lk.setLikedDate(rs.getDate(4));
	    		lk.setUserPhotoPath(map2.get(rs.getString(3)));
	    		lk.setUserName(map.get(rs.getString(3)));
	    		likes.add(lk);
	    	}
    	
		}
		catch(SQLException se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	public static java.util.List<PostGroupLikeTable> getLikesForPostIdAndUserId(Connection con,String postId,String userId){
		PreparedStatement ps=null;
		ResultSet rs=null;
		PostGroupLikeTable lk=null;
		java.util.List<PostGroupLikeTable> likes=new ArrayList<PostGroupLikeTable>();
		String _sql="select key,postId,likedUserId,likedDate FROM likeGroupPost WHERE postId =\'"+postId+"\' and likedUserId=\'"+userId+"\' and indicator=1";
		try{
			
			ps = con.prepareStatement(_sql);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		lk=new PostGroupLikeTable();
	    		lk.setKey(rs.getString(1));
	    		lk.setPostId(rs.getString(2));
	    		lk.setLikedUserId(rs.getString(3));
	    		lk.setLikedDate(rs.getDate(4));
	    		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( rs.getString(3));
	    		if(i.getImagePath()!=null)
	    			lk.setUserPhotoPath(i.getImagePath().getPhotoPath());
	    		lk.setUserName(i.getEmpName());
	    		likes.add(lk);
	    	}
    	
		}
		catch(SQLException se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	public static int deleteLike(Connection con, String id)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String _sql="DELETE FROM likeGroupPost WHERE key =\'"+id+"\'";
	//	System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	ps = con.prepareStatement(_sql);
	    	ps.executeUpdate();
	    	_result=1;
	    }
	    catch(SQLException se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
*/}
