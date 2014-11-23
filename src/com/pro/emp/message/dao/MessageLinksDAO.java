package com.pro.emp.message.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.MessageLinks;
import com.pro.post.domain.LikeNotificationJson;

public class MessageLinksDAO extends EmpCommanDAO{

	

	public static MessageLinks createMessageLinks( MessageLinks message,Session session) 
	{
		Calendar currentDate = Calendar.getInstance();
		
		 int result=-1;
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO empInfoDB.mailLinks(key," +
	    						"mailId,"+
	                            "fromId,toId," +
	                            "status,indicator,mailTime "+
	                            ") values(?,?,?,?,?,?,?)";
	                         /*   "" +
	                            "(\'"+id+"\',\'"+message.getMailId()+"\',\'"+message.getFromId()+"\'," +
	                            		"\'"+message.getToId()+"\',0,1,\'"+ss+"\')";*/
	    try
	    {
	    	
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id.toString(),message.getMailId(),message.getFromId(),message.getToId(),0,1,Calendar.getInstance().getTime()));
	        //rs=ps.getGeneratedKeys();
	        message.setKey(id.toString());
	       
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
	    
	    return message;
	  }
	
	public static java.util.List<MessageLinks> getMessageLinksForUserId(Session session,String userId){
		MessageLinks lk=null;
		java.util.List<MessageLinks> links=new ArrayList<MessageLinks>();
		//System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,mailId,fromId,toId,status,mailTime from empInfoDB.mailLinks where indicator=1 and toId=?  ALLOW FILTERING";
		try{
			
			HashMap<String,Integer> ma=new HashMap<String, Integer>();
			//System.out.println(" sss " + _sql);
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	        for(Row rs:rss){
	    
	    		//System.out.println(" ******************************* ");
	    		lk=new MessageLinks();
	    		lk.setKey(rs.getString(0));
	    		lk.setFromId(rs.getString(2));
	    		lk.setStatus(rs.getInt(4));
	    		lk.setToId(rs.getString(3));
	    		lk.setMailId(rs.getString(1));
	    		lk.setMailTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		lk.setMailTimeStr(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(5))));
	    		lk.setNewMessageCount(getMessageCount(session,userId,lk.getFromId()));
	    		lk.setFromIdInfo(CacheRecords.getInstance().getCacheData(lk.getFromId()));
	    		lk.setToIdInfo(CacheRecords.getInstance().getCacheData(lk.getToId()));
	    		lk.setMailIdInfo(MessageDetailsDAO.getMessagesById(session,lk.getMailId()));
	    		
	    		//for time
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
	    		lk.setMailTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+"");
	    		//posted by user info
	    		//posted by user info
	    		
	    		links.add(lk);
	    	}
	    			}catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
		
	  System.out.println(" here is count " + links.size() + " " + links);
	    return links;
	}
	
	public static java.util.List<MessageLinks> getSentMessageLinksForUserId(Session session,String userId){
		MessageLinks lk=null;
		java.util.List<MessageLinks> links=new ArrayList<MessageLinks>();
		//System.out.println("postedByUserId - userId " + userId);
		String _sql="select key ,mailId,fromId,toId,status,mailTime from empInfoDB.mailLinks where indicator=1 and fromId=?  ALLOW FILTERING";
		try{
			HashMap<String,Integer> ma=new HashMap<String, Integer>();
			//System.out.println(" sss " + _sql);
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId));
	        for(Row rs:rss){
	    		//System.out.println(" =----------------- ");
	        	lk=new MessageLinks();
	    		lk.setKey(rs.getString(0));
	    		lk.setFromId(rs.getString(2));
	    		lk.setStatus(rs.getInt(4));
	    		lk.setToId(rs.getString(3));
	    		lk.setMailId(rs.getString(1));
	    		lk.setMailTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		lk.setMailTimeStr(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(5))));
	    		lk.setNewMessageCount(getMessageCount(session,userId,lk.getFromId()));
	    		lk.setFromIdInfo(CacheRecords.getInstance().getCacheData(lk.getFromId()));
	    		lk.setToIdInfo(CacheRecords.getInstance().getCacheData(lk.getToId()));
	    		lk.setMailIdInfo(MessageDetailsDAO.getMessagesById(session,lk.getMailId()));
	    		
	    		//for time
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
	    		lk.setMailTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+"");
	    		//posted by user info
	    	
	    		links.add(lk);
	    	}
	    	
		}catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  //System.out.println(" here is count " + links.size() + " " + links);
	    return links;
	}
	
	
	public static java.util.List<MessageLinks> getMessageLinksByandFromUserId(Session session,String userId,String mailedUserId,String flow){
		MessageLinks lk=null;
		java.util.List<MessageLinks> links=new ArrayList<MessageLinks>();
		//System.out.println("postedByUserId - userId " + userId); 
		String _sql="select key ,mailId,fromId,toId,status,mailTime from empInfoDB.mailLinks where indicator=1 and toId=? and fromId=?  ALLOW FILTERING";
		String _sql2="select key ,mailId,fromId,toId,status,mailTime from empInfoDB.mailLinks where indicator=1 and toId=? and fromId= ?  ALLOW FILTERING";
		try{
			//System.out.println(" sss " + _sql);
			if(!flow.equalsIgnoreCase("sent")){
				com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(userId,mailedUserId));
		        for(Row rs:rss)
	    	{
	    		//System.out.println(" =----------------- ");
	    		lk=new MessageLinks();
	    		lk.setKey(rs.getString(0));
	    		lk.setFromId(rs.getString(2));
	    		lk.setToId(rs.getString(3));
	    		lk.setStatus(rs.getInt(4));
	    		lk.setMailId(rs.getString(1));
	    		lk.setMailTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		lk.setMailTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		
	    		lk.setFromIdInfo(CacheRecords.getInstance().getCacheData(lk.getFromId()));
	    		lk.setToIdInfo(CacheRecords.getInstance().getCacheData(lk.getToId()));
	    		lk.setMailIdInfo(MessageDetailsDAO.getMessagesById(session,lk.getMailId()));
	    		//posted by user info
	    		
	    		links.add(lk);
	    	}
			}
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql2);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(mailedUserId,userId));
	        for(Row rs:rss){
	    		//System.out.println(" =----------------- ");
	    		lk=new MessageLinks();
	    		lk.setKey(rs.getString(0));
	    		lk.setFromId(rs.getString(2));
	    		lk.setToId(rs.getString(3));
	    		lk.setStatus(rs.getInt(4));
	    		lk.setMailId(rs.getString(1));
	    		lk.setMailTime(Util.convertDateToTimestamp(rs.getDate(5)));
	    		lk.setMailTimeStr(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(5))));
	    		
	    		lk.setFromIdInfo(CacheRecords.getInstance().getCacheData(lk.getFromId()));
	    		lk.setToIdInfo(CacheRecords.getInstance().getCacheData(lk.getToId()));
	    		lk.setMailIdInfo(MessageDetailsDAO.getMessagesById(session,lk.getMailId()));
	    		//posted by user info
	    		
	    		links.add(lk);
	    	}
	    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  
	    return links;
	}
	public static void main(String args[]){
		getMessageCount(CassandraDB.getCassConnection(),"abf5faab-ac39-406b-9ddf-3baa8e78873e","abf5faab-ac39-406b-9ddf-3baa8e78873e");
	}
	public static int getMessageCount(Session session,String userId,String mailedUserId){
		BigInteger count=new BigInteger("0");
		System.out.println("userId  " + userId + " : " + mailedUserId);
		String _sql="select COUNT(*) from empInfoDB.mailLinks where indicator=1 and  status=0 and toId=? and fromId=?  ALLOW FILTERING";
		try{
			//System.out.println(" sss 1 : " + _sql);
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(userId,mailedUserId));
	        for(Row rs:rss){
	    		try{
	    		count=rs.getVarint(0);
	    		}catch(Exception ex){
	    			
	    		}
	    	}
	    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  return Integer.parseInt(String.valueOf(count));
	}
	
	public static int updateMessageStatus(Session session,String from,String to){
		int count=0;
		//System.out.println("postedByUserId - userId " + userId);
		String _sql="select key from empInfoDB.mailLinks where indicator=1 and  status=0 and toId=? and fromId=?  ALLOW FILTERING";
		try{
			//System.out.println(" sss 1 : " + _sql);
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(to,from));
	        for(Row rs:rss){
	    		
	    		String sss= " update empInfoDB.mailLinks set status=1 where key=? and indicator=1";
	    		//System.out.println(" ssss " + sss);
	    		com.datastax.driver.core.PreparedStatement statement2 =  session.prepare(sss);
		    	BoundStatement boundStatement2 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement2);
		    	session.execute(boundStatement2.bind(rs.getString(0)));
		        
	    	}
	    	
		}
		catch(Exception se)
	    {
	    	//transactionRollback(session);
	        se.printStackTrace();
	    }
	  return count;
	}
}
