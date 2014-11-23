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
import java.util.List;
import java.util.UUID;

import com.pro.cache.CacheRecords;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.activity.ActivityDAO;
import com.pro.emp.domain.Activity;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostGroupComments;
import com.pro.emp.util.ActivityTypes;

public class PostGroupCommentsDAO extends EmpCommanDAO{/*

	
	public static PostGroupComments createPostComments(PostGroupComments comm,Connection con){
		PreparedStatement ps=null;
		String result="";
		UUID id= java.util.UUID.randomUUID();
		try
	    {
			
			 String timeStamp = ""+ comm.getCommentDate();
			 //System.out.println(" t stamp " + timeStamp);
				Calendar currentDate=Calendar.getInstance();
				 SimpleDateFormat formatter= 
		        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
		        	  dt.setTime(currentDate.getTime().getTime());
		        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
		        	  String ss=""+tm;
		        	  ss=ss.substring(0,19);
	        String inssql="INSERT INTO postGroupComments(" +
	        						"key,"+
	                                "postId,"+
	                                "commentUserId," +
	                                "commentDesc," +
	                                "indicator,"+
	                                "commentDate" +
	                                ") values (\'"+id+"\',\'"+comm.getPostId()+"\'," +
	                                		"\'"+comm.getCommmentUserId()+"\'," +
	                                		"\'"+comm.getCommentDesc()+"\',1,\'"+ss+"\')";
	      //  System.out.println(" sql is " + inssql);
	        ps=con.prepareStatement(inssql);
	        
			comm.setKey(""+id);           
			comm.setTimeInStr( Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
	        ps.executeUpdate();
	        EmpInfo info = EmpInfoDAO.getEmpById(con, comm.getCommmentUserId());
	        comm.setCommentUserName(info.getEmpName());
	        if(info.getImage()!=null)
	        	comm.setCommentUserPhoto(info.getImage().getPhotoPath());
	       // rs=ps.getGeneratedKeys();
	       // con.commit();
			
			result=""+id;
			   Activity act=new Activity();
	            act.setActivityType(ActivityTypes.ACT_GROUP_POST_COMMENT.getValue());
	            act.setFromId(comm.getCommmentUserId());
	            act.setPostId(comm.getPostId());
	            act.setStatus(1);
	            act.setFlow(1);
	            ActivityDAO.createActivity(act, con);
	    }
	    catch(SQLException se)
	    {
	    	//transactionRollback(con);
	        System.out.println("Insertion Error");
	        se.printStackTrace();
	        comm=null;
	    }
		return comm;
	}
	
	public static List<PostGroupComments> getCommentsForPostId(String postId,Connection con){
		List<PostGroupComments> list=new ArrayList<PostGroupComments>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
    	
    		
	        String inssql="select key,postId,commentUserId,commentDesc,commentDate from  postGroupComments  where postId=\'"+postId+"\' and indicator=1";
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	while(rs.next())
        	{
        		
        		PostGroupComments info=new PostGroupComments();
        		info.setKey(rs.getString(1));
        		info.setPostId(rs.getString(2));
        		info.setCommmentUserId(rs.getString(3));
        		EmpBasicInfo i=CacheRecords.getInstance().getCacheData( info.getCommmentUserId());
        		info.setCommentUserName(i.getEmpName());
        		if(i.getImagePath()!=null)
        			info.setCommentUserPhoto(i.getImagePath().getPhotoPath());
        		info.setCommentDesc(rs.getString(4));
        		info.setCommentDate(rs.getTimestamp(5));
        		Calendar c= Calendar.getInstance();
        		info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
        		info.setLikes(PostLikeDAO.getLikesForPostId(con, info.getKey()));
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

	public static int deleteById(String id,Connection con){
		int result=0;
		PreparedStatement ps=null;
    	try
        {
    		
	        String inssql="delete from  postGroupComments where key=\'"+id+"\'";
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            ps.executeUpdate();
        	result=1;
        }
    	catch(Exception e){
    		result=2;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	

	public static PostGroupComments getCommentById(String commentId,Connection con){
		PostGroupComments list=null;
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
    	
    		
	        String inssql="select key,postId,commentUserId,commentDesc,commentDate from  postGroupComments  where key=\'"+commentId+"\' and indicator=1";
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	if(rs.next())
        	{
        		
        		PostGroupComments info=new PostGroupComments();
        		info.setKey(rs.getString(1));
        		info.setPostId(rs.getString(2));
        		info.setCommmentUserId(rs.getString(3));
        		info.setCommentDesc(rs.getString(4));
        		info.setCommentDate(rs.getTimestamp(5));
        		Calendar c= Calendar.getInstance();
        		info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
        		list=(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	
*/}
