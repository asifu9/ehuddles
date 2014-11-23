package com.pro.post.dao;

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
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpCommanDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;
import com.pro.post.domain.LikeNotification;
import com.pro.post.domain.LikeNotificationJson;

public class LikeNotificationsDAO extends EmpCommanDAO {




	public static String createNotificationLink( LikeNotification like,Session session) 
	{
		int result=0;
		Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= 
        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
        	  dt.setTime(currentDate.getTime().getTime());
        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
        	  String ss=""+tm;
        	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO empInfoDB.likeNotification(" +
	    						"key,"+
	                            "postId,postedByUserId," +
	                            "postLikedByUserId,likeId,likedTime,status,indicator,flow,message"+
	                            ") values(?,?,?,?,?,?,?,?,?,?)";// +
	            /*                "" +
	                            "(\'"+id+"\',\'"+like.getPostId()+"\',\'"+like.getPostedByUserId()+"\'," +
	                            		"\'"+like.getPostLikedByUserId()+"\',\'"+like.getLikeId()+"\'," +
	                            				"\'"+ss+"\',1,1,"+like.getFlow()+",\'"+like.getMessage()+"\')";*/
	    try
	    {
	    	System.out.println(" sql i s " + inssql);
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    				like.getPostId(),
	    				like.getPostedByUserId(),
	    				like.getPostLikedByUserId(),
	    				like.getLikeId(),
	    				Calendar.getInstance().getTime(),
	    				1,
	    				1,
	    				like.getFlow(),
	    				like.getMessage()));
	        //rs=ps.getGeneratedKeys();
	        
	       
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
	    
	    return ""+id;
	  }
	public static void main(String args[]){
		java.util.List<LikeNotificationJson> list=getLikeNotificationsLive("abf5faab-ac39-406b-9ddf-3baa8e78873e");
		System.out.println(" count is " + list.size());
	}
	public static java.util.List<LikeNotificationJson> getLikeNotificationsLive(String userId){
		LikeNotificationJson lk=null;
		java.util.List<LikeNotificationJson> likes=new ArrayList<LikeNotificationJson>();
		System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,postId,postedByUserId,postLikedByUserId,likeId,likedTime,status,indicator,flow,message from empInfoDB.likeNotification where  postedByUserId=? and status=1 and flow=1 and indicator=1   ALLOW FILTERING";
		try{
			System.out.println(" sss " + _sql);
			Session session= CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	        for(Row rs:rss){
	        	//abf5faab-ac39-406b-9ddf-3baa8e78873e
	        	System.out.println("key = " + rs.getString(0)+" postedByUserId " + rs.getString(2) + " : postLikedByUserId " + rs.getString(3) + " : " + rs.getInt(6));
	    		lk=new LikeNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		lk.setLikedTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(rs.getDate(5));
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
	    		lk.setLikedTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setLikeId(rs.getString(4));
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		CacheRecords r= CacheRecords.getInstance();
	    		System.out.println(" rs.getString(2) " + rs.getString(2));
	    		EmpBasicInfo i= r.getCacheData( rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		lk.setMessage(rs.getString(9));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		System.out.println(" rs.getString(3) " + rs.getString(3));
	    		EmpBasicInfo ii= r.getCacheData( rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostLikedByUserId(postLikedByUserId);
	    		lk.setLikedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		if(!(rs.getString(4).equalsIgnoreCase("tag")))
	    			lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
	    		else
	    			lk.setPhotoInfo(PhotoInfoDAO.getPhotoByIdDynamicName(rs.getString(1), CassandraDB.getCassConnection()));
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
	public static java.util.List<LikeNotificationJson> getLikeNotifications(String userId){
		LikeNotificationJson lk=null;
		java.util.List<LikeNotificationJson> likes=new ArrayList<LikeNotificationJson>();
		System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,postId,postedByUserId,postLikedByUserId,likeId,likedTime,status,indicator,flow,message from empInfoDB.likeNotification where  postedByUserId=?  and flow=1 and indicator=1   ALLOW FILTERING";
		try{
			System.out.println(" sss " + _sql);
			Session session= CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	        for(Row rs:rss){
	        	//abf5faab-ac39-406b-9ddf-3baa8e78873e
	        	System.out.println("key = " + rs.getString(0)+" postedByUserId " + rs.getString(2) + " : postLikedByUserId " + rs.getString(3) + " : " + rs.getInt(6));
	    		lk=new LikeNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		lk.setLikedTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(rs.getDate(5));
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
	    		lk.setLikedTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setLikeId(rs.getString(4));
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		CacheRecords r= CacheRecords.getInstance();
	    		System.out.println(" rs.getString(2) " + rs.getString(2));
	    		EmpBasicInfo i= r.getCacheData( rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		lk.setMessage(rs.getString(9));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		System.out.println(" rs.getString(3) " + rs.getString(3));
	    		EmpBasicInfo ii= r.getCacheData( rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostLikedByUserId(postLikedByUserId);
	    		lk.setLikedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		if(!(rs.getString(4).equalsIgnoreCase("tag")))
	    			lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
	    		else
	    			lk.setPhotoInfo(PhotoInfoDAO.getPhotoByIdDynamicName(rs.getString(1), CassandraDB.getCassConnection()));
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
	public static java.util.List<LikeNotificationJson> getLIkeDetails(String id){
		LikeNotificationJson lk=null;
		java.util.List<LikeNotificationJson> likes=new ArrayList<LikeNotificationJson>();
		String _sql="select key ,postId,postedByUserId,postLikedByUserId,likeId,likedTime,status,indicator,flow,message from empInfoDB.likeNotification where  key='f300e4d2-424a-4dd9-8432-1fa68afdac08'   ALLOW FILTERING";
		try{
			System.out.println(" sss " + _sql);
			Session session= CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	        for(Row rs:rss){
	        	//abf5faab-ac39-406b-9ddf-3baa8e78873e
	        	System.out.println("key = " + rs.getString(0)+" postedByUserId " + rs.getString(2) + " : postLikedByUserId " + rs.getString(3));
	    		lk=new LikeNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		System.out.println("rs.getDate(5) " + rs.getDate(5));
	    		lk.setLikedTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(rs.getDate(5));
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
	    		lk.setLikedTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setLikeId(rs.getString(4));
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		CacheRecords r= CacheRecords.getInstance();
	    		System.out.println(" rs.getString(2) " + rs.getString(2));
	    		EmpBasicInfo i= r.getCacheData( rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		lk.setMessage(rs.getString(9));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		System.out.println(" rs.getString(3) " + rs.getString(3));
	    		EmpBasicInfo ii= r.getCacheData( rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostLikedByUserId(postLikedByUserId);
	    		lk.setLikedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		
	    		lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
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
	public static java.util.List<LikeNotificationJson> getLikeNotificationsOld(Session session,String userId,Timestamp starttime){
		LikeNotificationJson lk=null;
		java.util.List<LikeNotificationJson> likes=new ArrayList<LikeNotificationJson>();
		Date c=Calendar.getInstance().getTime();
		c.setTime(starttime.getTime());
		String _sql="select key ,postId,postedByUserId,postLikedByUserId,likeId,likedTime,status,indicator,flow,message from empInfoDB.likeNotification where likedTime <? and  indicator=1 and postedByUserId=? and flow=1  LIMIT 10   ALLOW FILTERING";
		try{
			System.out.println(" sss " + _sql);
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(c,userId));
	        for(Row rs:rss){
	    		lk=new LikeNotificationJson();
	    		lk.setKey(rs.getString(0));
	    		lk.setFlow(rs.getInt(8));
	    		lk.setLikedTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(rs.getDate(5));
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
	    		lk.setLikedTimeStr2(cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		lk.setLikeId(rs.getString(4));
	    		//posted by user info
	    		EmpInfo postedByUserId=new EmpInfo();
	    		CacheRecords r= CacheRecords.getInstance();
	    		
	    		EmpBasicInfo i= r.getCacheData( rs.getString(2));
	    		postedByUserId.setEmpName(i.getEmpName());
	    		if(i.getImagePath()!=null)
	    			postedByUserId.setImagePath(i.getImagePath().getPhotoPath());
	    		lk.setPostedByUserId(postedByUserId);
	    		lk.setStatus(rs.getInt(6));
	    		lk.setMessage(rs.getString(9));
	    		// post liked user id
	    		EmpInfo postLikedByUserId=new EmpInfo();
	    		EmpBasicInfo ii= r.getCacheData( rs.getString(3));
	    		postLikedByUserId.setEmpName(ii.getEmpName());
	    		if(ii.getImagePath()!=null)
	    			postLikedByUserId.setImagePath(ii.getImagePath().getPhotoPath());
	    		lk.setPostLikedByUserId(postLikedByUserId);
	    		lk.setLikedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		
	    		lk.setPostId(PostDAO.getPostDetailByPostId(session, rs.getString(1)));
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
	
	public static int updateNOtificaitonLIke(String id)
	{
		//java.util.List<LikeNotificationJson> list=getBasicKeyForUserId(id);
		//71ae1a6e-5b67-4173-b2c0-3f07d2c7dc69
		Session session = CassandraDB.getCassConnection();
		System.out.println(" key notification to update " + id);
		int _result=0;
	    try
	    {  // postedByUserId abf5faab-ac39-406b-9ddf-3baa8e78873e : postLikedByUserId 5d642094-fb8f-4ca3-90de-7fe57d8b194f
			//for(LikeNotificationJson l:list){
				String _sql="update likeNotification set status=0 WHERE indicator=1 and key =?";
				com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	session.execute(boundStatement.bind(id));
				_result=1;
	    }
	    //}
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
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
	
	
	public static java.util.List<LikeNotificationJson> getBasicKeyForUserId(String userId){
		LikeNotificationJson lk=null;
		Session session=CassandraDB.getCassConnection();
		java.util.List<LikeNotificationJson> likes=new ArrayList<LikeNotificationJson>();
		String _sql="select key ,postId,postedByUserId from empInfoDB.likeNotification where postedByUserId=? and flow=1 and indicator=1   ALLOW FILTERING";
		try{
			
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	        for(Row rs:rss){
	    		lk=new LikeNotificationJson();
	    		lk.setKey(rs.getString(0));
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
	
	public static java.util.List<LikeTable> getLikesForIdDynamic(Session session,String itemId){
		LikeTable lk=null;
		java.util.List<LikeTable> likes=new ArrayList<LikeTable>();
		String _sql="select key,itemId,likedUserId,likedDate FROM empInfoDB.liketable WHERE itemId =?";
		try{
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(itemId));
	        for(Row rs:rss){
	    		lk=new LikeTable();
	    		lk.setId(rs.getString(0));
	    		lk.setItemId(rs.getString(1));
	    		lk.setLikedUserId(rs.getString(2));
	    		lk.setLikedDate(rs.getDate(3));
	    		EmpInfo ff= EmpInfoDAO.getBasicEmpById(session, rs.getString(2));
	    		lk.setUserPhotoPath(ff.getImagePath());
	    		lk.setUserName(ff.getEmpName());
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

}
