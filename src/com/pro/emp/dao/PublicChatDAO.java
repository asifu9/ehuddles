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
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.domain.PublicChat;

public class PublicChatDAO {


	public  PublicChat createChat( PublicChat chat,Session session) 
	{
		int result=0;
		UUID id=UUID.randomUUID();
		Date currentDate = Calendar.getInstance().getTime();
	    String inssql="INSERT INTO  empInfoDB.publicChat(" +
	    						"key,"+
	                            "postedFromId," +
	                            "postedInfo,indicator,postedTime "+
	                            ") values(?,?,?,?,?)";// +
	                            //"(\'"+id+"\',\'"+chat.getPostedFromId()+"\',\'"+chat.getPostedInfo()+"\',1,\'"+ss+"\')";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id.toString(),chat.getPostedFromId(),chat.getPostedInfo(),1,currentDate));
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // session.commit();
	    	chat.setPostedTime(Util.convertDateToTimestamp(currentDate));
	    	chat.setPostedFromId(chat.getPostedFromId());
	    	chat.setKey(id.toString());
	    	
	    	result=1;
	    }
	    catch(Exception se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(session);
	        se.printStackTrace();
	    }
	    
	    return chat;
	  }
	
	public static void main(String args[]){
		new PublicChatDAO().getAllChats(CassandraDB.getCassConnection());
	}
	public  java.util.List<PublicChat> getAllChats(Session session){
		PublicChat lk=null;
		java.util.List<PublicChat> likes=new ArrayList<PublicChat>();
		String _sql="select key,postedFromId,postedInfo,postedTime FROM  empInfoDB.publicChat WHERE indicator =1";
		try{
			
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	        for(Row rs:rss){
	    		lk=new PublicChat();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostedFromId(rs.getString(1));
	    		lk.setPostedInfo(rs.getString(2));
	    		lk.setPostedTime(Util.convertDateToTimestamp(rs.getDate(3)));
	    		lk.setInfo(CacheRecords.getInstance().getCacheData(rs.getString(1)));
	    		lk.setPostedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(3))));
	    		likes.add(lk);
	    		System.out.println(" here i am " );
	    	}
    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return likes;
	}
	
	public  PublicChat getChatById(String key,String fromId,Date d){
		PublicChat lk=null;
		PublicChat likes=null;
		String _sql="select key,postedFromId,postedInfo,postedTime FROM  empInfoDB.publicChat WHERE indicator =1 and key=? and postedFromId=? and postedTime=?";
		try{
			Session session=CassandraDB.getCassConnection();
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key,fromId,d));
	        for(Row rs:rss){
	    		lk=new PublicChat();
	    		lk.setKey(rs.getString(0));
	    		lk.setPostedFromId(rs.getString(1));
	    		lk.setPostedInfo(rs.getString(2));
	    		lk.setPostedTime(Util.convertDateToTimestamp(rs.getDate(3)));
	    		lk.setInfo(CacheRecords.getInstance().getCacheData(rs.getString(1)));
	    		lk.setPostedTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(3))));
	    		likes=lk;
	    		System.out.println(" here i am " );
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
