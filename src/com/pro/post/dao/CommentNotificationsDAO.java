package com.pro.post.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpCommanDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;
import com.pro.post.domain.CommentNotification;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotification;
import com.pro.post.domain.LikeNotificationJson;

public class CommentNotificationsDAO extends EmpCommanDAO {




	public static String createNotificationComment( CommentNotification comm,Session session) 
	{
		//PreparedStatement ps=null;
		ResultSet rs=null;
		int result=0;
		Calendar currentDate = Calendar.getInstance();
		 SimpleDateFormat formatter= 
       	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		 System.out.println(" posted by user id " + comm.getPostedByUserId());
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
       	  Date d= Calendar.getInstance().getTime();
       	  
       	  
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO empInfoDB.commentNotification(" +
	    						"key,"+
	                            "postId,postedByUserId," +
	                            "postcommentedByUserId,commentId,commentTime,status,indicator,flow,message,commentIdDate"+
	                            ")values(?,?,?,?,?,?,?,?,?,?,?);";
	                           // ") values(\'"+id+"\',\'"+comm.getPostId()+"\',\'"+comm.getPostedByUserId()+"\',
	    //\'"+comm.getPostCommentedByUserId()+"\',\'"+comm.getCommentId()+"\',\'"+ss+"\',1,1,"+comm.getFlow()+",\'"+comm.getMessage()+"\')";
	    try
	    {
	    	System.out.println(" commnetIdDate " + comm.getCommentIdDate());
	    	System.out.println(" message " + comm.getMessage());
	    	System.out.println(" getPostCommentedByUserId " + comm.getPostCommentedByUserId());
	    	System.out.println(" getCommentId " + comm.getCommentId());
	    	System.out.println(" getPostId " + comm.getPostId());
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(
	    				id.toString(),
	    		      comm.getPostId(),
	    		      comm.getPostedByUserId(),
	    		      comm.getPostCommentedByUserId(),
	    		      comm.getCommentId(),
	    		      Calendar.getInstance().getTime(),1,1,
	    		      comm.getFlow(),
	    		      comm.getMessage(),comm.getCommentIdDate()));
	    	
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // con.commit();
	    	result=1;
	    }
	    catch(Exception se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(con);
	        se.printStackTrace();
	    }
	    
	    return ""+id;
	  }
	
	
	public static void main(String args[]){
		getCommentNotificationsTest();
	}
	public static java.util.List<CommentNotificationJson> getCommentNotificationsTest(){
		CommentNotificationJson lk=null;
		java.util.List<CommentNotificationJson> likes=new ArrayList<CommentNotificationJson>();
		String _sql="select key ,postId,postedByUserId,postcommentedByUserId,commentId,commentTime,status,indicator,flow,message,commentIdDate from empInfoDB.commentNotification ";
		try{
			Session session=CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	    			//.bind("f85b8030-fb77-42b0-a018-20f1edbc0696")
	    	System.out.println("sql " + _sql);
	    	for (Row rs : rss) {
	    		
	    		lk=new CommentNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		System.out.println("rs.getDate(5) " + rs.getDate(10) + " : " + rs.getString(9));
	    		lk.setCommentTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		//if(rs.getString(4)!=null)
	    		//	lk.setCommentId(  PostCommentsDAO.getCommentById(rs.getString(4),rs.getDate(10), session) );
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		EmpBasicInfo i =CacheRecords.getInstance().getCacheData(  rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		EmpBasicInfo ii = CacheRecords.getInstance().getCacheData(  rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostCommentedByUserId(postLikedByUserId);
	    		lk.setCommetnTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		String month="";
	    		
	    		if(cal.get(Calendar.MONTH)<=8)
	    			month="0"+(cal.get(Calendar.MONTH)+1);
	    		else
	    			month=""+(cal.get(Calendar.MONTH)+1);
	    		String day="";
	    		if(cal.get(Calendar.DATE)<=9)
	    			day="0"+cal.get(Calendar.DATE);
	    		else
	    			day=""+cal.get(Calendar.DATE);
	    		String hour="";
	    		if(cal.get(Calendar.HOUR_OF_DAY)<=9)
	    			hour="0"+cal.get(Calendar.HOUR_OF_DAY);
	    		else
	    			hour=""+cal.get(Calendar.HOUR_OF_DAY);
	    		String min="";
	    		if(cal.get(Calendar.MINUTE)<=9)
	    			min="0"+cal.get(Calendar.MINUTE);
	    		else
	    			min=""+cal.get(Calendar.MINUTE);
	    		String sec="";
	    		if(cal.get(Calendar.SECOND)<=9)
	    			sec="0"+cal.get(Calendar.SECOND);
	    		else
	    			sec=""+cal.get(Calendar.SECOND);
	    		lk.setCommentTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setMessage(rs.getString(9));
	    		lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	public static java.util.List<CommentNotificationJson> getCommentNotifications(String userId){
		CommentNotificationJson lk=null;
		java.util.List<CommentNotificationJson> likes=new ArrayList<CommentNotificationJson>();
		System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,postId,postedByUserId,postcommentedByUserId,commentId,commentTime,status,indicator,flow,message,commentIdDate from empInfoDB.commentNotification where flow=1 and indicator=1  and postedByUserId=? ALLOW FILTERING";
		try{
			Session session=CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	    			//.bind("f85b8030-fb77-42b0-a018-20f1edbc0696")
	    	for (Row rs : rss) {
	    		
	    		lk=new CommentNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		System.out.println("rs.getDate(5) " + rs.getDate(5) + " : " + rs.getString(9));
	    		lk.setCommentTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		//if(rs.getString(4)!=null)
	    		//	lk.setCommentId(  PostCommentsDAO.getCommentById(rs.getString(4),rs.getDate(10), session) );
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		EmpBasicInfo i =CacheRecords.getInstance().getCacheData(  rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		EmpBasicInfo ii = CacheRecords.getInstance().getCacheData(  rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostCommentedByUserId(postLikedByUserId);
	    		lk.setCommetnTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		String month="";
	    		
	    		if(cal.get(Calendar.MONTH)<=8)
	    			month="0"+(cal.get(Calendar.MONTH)+1);
	    		else
	    			month=""+(cal.get(Calendar.MONTH)+1);
	    		String day="";
	    		if(cal.get(Calendar.DATE)<=9)
	    			day="0"+cal.get(Calendar.DATE);
	    		else
	    			day=""+cal.get(Calendar.DATE);
	    		String hour="";
	    		if(cal.get(Calendar.HOUR_OF_DAY)<=9)
	    			hour="0"+cal.get(Calendar.HOUR_OF_DAY);
	    		else
	    			hour=""+cal.get(Calendar.HOUR_OF_DAY);
	    		String min="";
	    		if(cal.get(Calendar.MINUTE)<=9)
	    			min="0"+cal.get(Calendar.MINUTE);
	    		else
	    			min=""+cal.get(Calendar.MINUTE);
	    		String sec="";
	    		if(cal.get(Calendar.SECOND)<=9)
	    			sec="0"+cal.get(Calendar.SECOND);
	    		else
	    			sec=""+cal.get(Calendar.SECOND);
	    		lk.setCommentTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setMessage(rs.getString(9));
	    		lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	public static int getCommentNotificationsCount(String userId){
		int count=0;
		java.util.List<CommentNotificationJson> likes=new ArrayList<CommentNotificationJson>();
		System.out.println("getCommentNotificationsCount - userId " + userId);
		String _sql="select key ,postId,postedByUserId,postcommentedByUserId,commentId,commentTime,status,indicator,flow,message from empInfoDB.commentNotification where flow=1 and indicator=1 and status=1 and postedByUserId=? ALLOW FILTERING";
		try{
			Session session=CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	    			//.bind("f85b8030-fb77-42b0-a018-20f1edbc0696")
	    	for (Row rs : rss) {
	    		
	    		count+=1;
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return count;
	}
	
	/*public static void main(String arg[]){
		Date d=Calendar.getInstance().getTime();
		Timestamp t=new Timestamp(d.getTime());
		getOldCommentNotifications(CassandraDB.getCassConnection(),"abf5faab-ac39-406b-9ddf-3baa8e78873e",t);
	}*/
	
	public static java.util.List<CommentNotificationJson> getOldCommentNotifications(Session session,String userId,Timestamp starttime){
		//PreparedStatement ps=null;
		CommentNotificationJson lk=null;
		java.util.List<CommentNotificationJson> likes=new ArrayList<CommentNotificationJson>();
		if(starttime==null)
			starttime=new Timestamp(Calendar.getInstance().getTimeInMillis());
		Calendar cals=Calendar.getInstance();
		cals.setTimeInMillis(starttime.getTime());
		Date dddddd= cals.getTime();
		System.out.println(" timestamp in old comment notification is " + dddddd + " : " + userId);
		
		
	
		//System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,postId,postedByUserId,postcommentedByUserId,commentId,commentTime,status,indicator,flow,message from empInfoDB.commentNotification where commentTime < ?  and  postedByUserId=? and flow=1 and indicator=1 LIMIT 10  ALLOW FILTERING";
		//String _sql="select key ,postId,postedByUserId,postcommentedByUserId,commentId,commentTime,status,indicator,flow,message,commentIdDate from commentNotification LIMIT 10  ALLOW FILTERING";
		try{
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(dddddd,userId));
	    	
			System.out.println(" sss " + _sql);
	    	for(Row rs: rss){
	    		System.out.println(" ok got it " + rs.getDate(10) + " : " + rs.getString(9));
	    		lk=new CommentNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
	    		lk.setFlow(rs.getInt(8));
	    		lk.setCommentTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		String month="";
	    		
	    		if(cal.get(Calendar.MONTH)<=8)
	    			month="0"+(cal.get(Calendar.MONTH)+1);
	    		else
	    			month=""+(cal.get(Calendar.MONTH)+1);
	    		String day="";
	    		if(cal.get(Calendar.DATE)<=9)
	    			day="0"+cal.get(Calendar.DATE);
	    		else
	    			day=""+cal.get(Calendar.DATE);
	    		String hour="";
	    		if(cal.get(Calendar.HOUR_OF_DAY)<=9)
	    			hour="0"+cal.get(Calendar.HOUR_OF_DAY);
	    		else
	    			hour=""+cal.get(Calendar.HOUR_OF_DAY);
	    		String min="";
	    		if(cal.get(Calendar.MINUTE)<=9)
	    			min="0"+cal.get(Calendar.MINUTE);
	    		else
	    			min=""+cal.get(Calendar.MINUTE);
	    		String sec="";
	    		if(cal.get(Calendar.SECOND)<=9)
	    			sec="0"+cal.get(Calendar.SECOND);
	    		else
	    			sec=""+cal.get(Calendar.SECOND);
	    		lk.setCommentTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		
	    		if(rs.getDate(5)!=null){
	    			lk.setCommentId( PostCommentsDAO.getCommentById(rs.getString(4),rs.getDate(10), session) );
	    			//System.out.println(" sssssssssss " + lk.getCommentId().getCommmentUserId());
	    		}
	    		//posted by user info
	    		EmpInfo a1 = EmpInfoDAO.getBasicEmpById(session, lk.getPostId().getPostedById());
	    		EmpInfo postedByUserId=new EmpInfo();
	    	
	    		postedByUserId.setEmpName(a1.getEmpName());
	    		if(a1.getImage()!=null)
	    		postedByUserId.setImagePath(a1.getImage().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(7));
	    		// post liked user id
	    		EmpInfo a2 = EmpInfoDAO.getBasicEmpById(session, lk.getCommentId().getCommmentUserId());
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		postLikedByUserId.setEmpName(a2.getEmpName());
	    		if(a2.getImage()!=null)
	    		postLikedByUserId.setImagePath(a2.getImage().getPhotoPath());
	    		lk.setPostCommentedByUserId(postLikedByUserId);
	    		lk.setCommetnTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		lk.setMessage(rs.getString(9));
	    		
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	   System.out.println(" comments size " + likes.size());
	   
	    return likes;
	}
	public static int updateNOtificaitonComment(Session session, String id,String userId)
	{
		java.util.List<CommentNotificationJson> list=getBasicKeyForUserId(session,userId);
		
		String _sql="update empInfoDB.commentNotification set status=0 WHERE key =? and commentTime=? and indicator=1";
		
		int _result=0;
	    try
	    {
			for(CommentNotificationJson l:list){
				
				if(id.equalsIgnoreCase(l.getKey())){
					Date dd=Calendar.getInstance().getInstance().getTime();
					dd.setTime(l.getCommentTime().getTime());
					com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
			    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
			    	ResultSet rs=session.execute(boundStatement.bind(l.getKey(),dd));
					_result=1;
				}
			}
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	   
	    if(_result==0)
	    {
	    	return 0;
	    }
	    else
	    {
	    	return 3;
	    }
	}
	
	public static java.util.List<CommentNotificationJson> getBasicKeyForUserId(Session session,String userId){
		CommentNotificationJson lk=null;
		java.util.List<CommentNotificationJson> likes=new ArrayList<CommentNotificationJson>();
		String _sql="select key ,postId,postedByUserId,commentTime from empInfoDB.commentNotification where indicator=1 and status=1 and postedByUserId=? and flow=1  ALLOW FILTERING";
		try{
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	    	
	    	for(Row rs:rss){
	    		lk=new CommentNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setCommentTime(Util.convertDateToTimestamp(rs.getDate(3)));
	    		likes.add(lk);
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	

}
