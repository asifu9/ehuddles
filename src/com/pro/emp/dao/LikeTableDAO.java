package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;

public class LikeTableDAO  extends EmpCommanDAO{


	public static String createLikeTable( LikeTable like,Session session) 
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
	    String inssql="INSERT INTO empInfoDB.liketable(" +
	    						"key,"+
	                            "itemId," +
	                            "likedUserId,likedDate,indicator "+
	                            ") values(?,?,?,?,?)";// +
	                           // "" +
	                           // "(\'"+id+"\',\'"+like.getItemId()+"\',\'"+like.getLikedUserId()+"\',\'"+ss+"\')";
	    try
	    {
	    	
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    		id.toString(),
	    		like.getItemId(),
	    		like.getLikedUserId(),
	    		Calendar.getInstance().getTime(),1));
	    	
	    	
	        
	       
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
	
	public static java.util.List<LikeTable> getLikesForId(Session session,String itemId){
		LikeTable lk=null;
		java.util.List<LikeTable> likes=new ArrayList<LikeTable>();
		String _sql="select key,itemId,likedUserId,likedDate FROM empInfoDB.liketable WHERE  itemId =?";
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
	    		EmpBasicInfo i = CacheRecords.getInstance().getCacheData( rs.getString(2));
	    		if(i.getImagePath()!=null)
	    			lk.setUserPhotoPath(i.getImagePath().getPhotoPath());
	    		lk.setUserName(i.getEmpName());
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
	
	public static int deleteLike(Session session, String id)
	{
		
		String _sql="DELETE FROM empInfoDB.liketable WHERE key =? and indicator=1";
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	    	_result=1;
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
	    	//transactionRollback(con);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
}
