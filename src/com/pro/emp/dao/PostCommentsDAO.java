package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;
import com.pro.emp.domain.PostComments;

public class PostCommentsDAO extends EmpCommanDAO{

	
	public static PostComments createPostComments(PostComments comm,Session session){
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
	        String inssql="INSERT INTO  empInfoDB.postComments (" +
	        						"key,"+
	                                "postId,"+
	                                "commentUserId," +
	                                "commentDesc," +
	                                "indicator,"+
	                                "commentDate" +
	                                ") values(?,?,?,?,?,?)";// +
//	                                " (\'"+id+"\',\'"+comm.getPostId()+"\'," +
//	                                		"\'"+comm.getCommmentUserId()+"\'," +
//	                                		"\'"+comm.getCommentDesc()+"\',1,\'"+ss+"\')";
	      //  System.out.println(" sql is " + inssql);

	        
			comm.setKey(id.toString());           
			comm.setTimeInStr( Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
			Date dddd=Calendar.getInstance().getTime();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			comm.getPostId(),
	    			comm.getCommmentUserId(),
	    			comm.getCommentDesc(),
	    			1,
	    			dddd
	    			
	    			));
			comm.setCommentDate(dddd);
			EmpInfo info = EmpInfoDAO.getEmpById( comm.getCommmentUserId());
	        comm.setCommentUserName(info.getEmpName());
	        if(info.getImage()!=null)
	        	comm.setCommentUserPhoto(info.getImage().getPhotoPath());
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
	
	public static List<PostComments> getCommentsForPostId(String postId,Session session){
		List<PostComments> list=new ArrayList<PostComments>();
    	try
        {
    	
    		
	        String inssql="select key,postId,commentUserId,commentDesc,commentDate from   empInfoDB.postComments   where postId=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId));
	        for(Row rs:rss)
        	{
        		
        		PostComments info=new PostComments();
        		info.setKey(rs.getString(0));
        		info.setPostId(rs.getString(1));
        		info.setCommmentUserId(rs.getString(2));
        		EmpBasicInfo i = CacheRecords.getInstance().getCacheData( info.getCommmentUserId());
        		info.setCommentUserName(i.getEmpName());
        		if(i.getImagePath()!=null)
        			info.setCommentUserPhoto(i.getImagePath().getPhotoPath());
        		
        		info.setCommentDesc(rs.getString(3));
        		System.out.println(" ************************* " + rs.getString(3));
        		String ss=""+rs.getDate(4);
        		info.setTimeInStrToDelete(Util.getTimeToDelete(rs.getDate(4)));
        		//System.out.println(" Util.convertDateToTimestamp(rs.getDate(4)) " + Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setCommentDate( Util.convertDateToTimestamp(rs.getDate(4)));
        		Calendar c= Calendar.getInstance();
        		info.setTimeInStr(Util.getCommentTime(Util.convertDateToTimestamp(info.getCommentDate())));
        		info.setLikes(PostLikeDAO.getLikesForPostId(session, info.getKey()));
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

	public static int deleteById(String id,Session session,Date date){
		int result=0;
    	try
        {
    		
	        String inssql="delete from   empInfoDB.postComments  where key=? and indicator=1 and commentdate = ?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id,date));
        	result=1;
        }
    	catch(Exception e){
    		result=2;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	public static void main(String args[]){
		getCommentById("abf5faab-ac39-406b-9ddf-3baa8e78873e",Calendar.getInstance().getTime(),CassandraDB.getCassConnection());
	}

	public static PostComments getCommentById(String commentId,Date commentIdDate,Session session){
		PostComments list=null;
    	try
        {
    		
    		System.out.println(" commentId " + commentId + " commentIdDate " + commentIdDate);
	        String inssql="select key,postId,commentUserId,commentDesc,commentDate from   empInfoDB.postComments   where  commentDate=? and key=?  and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(commentIdDate,commentId));
	        for(Row rs:rss)
        	{
        		
        		PostComments info=new PostComments();
        		info.setKey(rs.getString(0));
        		info.setPostId(rs.getString(1));
        		info.setCommmentUserId(rs.getString(2));
        		info.setCommentDesc(rs.getString(3));
        		info.setCommentDate(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setTimeInStr(Util.getCommentTime(Util.convertDateToTimestamp(info.getCommentDate())));
        		list=(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	
}
