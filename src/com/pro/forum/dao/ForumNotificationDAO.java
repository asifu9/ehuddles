package com.pro.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.pro.cache.CacheRecords;
import com.pro.emp.Util;
import com.pro.emp.domain.ForumNotification;

public class ForumNotificationDAO {/*


	public static String createNewForum( ForumNotification f,Connection con) 
	{
		PreparedStatement ps=null;
		Calendar currentDate = Calendar.getInstance();
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
		 
	    String inssql="INSERT INTO forumNotification(key," +
	    						"forumId,postedByUserId,"+
	                            "time,indicator,status,notifyingUserId"+
	                            ") values(\'"+id+"\',\'"+f.getForumId()+"\',\'"+f.getPostedByUserId()+"\',\'"+ss+"\',1,1,\'"+f.getNotifyingUserId()+"\')";
	    try
	    {
	    	
    		
	    	ps=con.prepareStatement(inssql);
	        
	        
	        ps.executeUpdate();
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // con.commit();
	    }
	    catch(SQLException se)
	    {
	        System.out.println("Insertion Error");
	        //transactionRollback(con);
	        se.printStackTrace();
	    }
	    
	    return ""+id;
	  }
	
	 public static List<ForumNotification> getActiveForumNotification(Connection con,String userId){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	List<ForumNotification> list=new ArrayList<ForumNotification>();
	    	ForumNotification f=null;
	    	*//**
			  * 
			  * CREATE TABLE forumNotification ("+
		    		   "KEY uuid PRIMARY KEY,"+
		    		   "forumId text,"+
		    		   "postedByUserId text,"+
		    		   "postedTime timestamp,"+
		    		   "status int,"+
		    		   "indicator int"+
			  *//*
	    	String _sql="select " + 
	    				"key," +
	                    "forumId, "+
	                    "postedByUserId,time,status" +
	    				" from forumNotification where indicator = 1 and status=1 and notifyingUserId='"+userId+"'";
	        try{
	        	//System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		f=new ForumNotification();
	        		f.setKey(rs.getString(1));
	        		f.setForumId(rs.getString(2));
	        		if(rs.getString(2)!=null)
	        			f.setForumIdInfo(ForumDAO.getForumByForumId(con, rs.getString(2)));
	        		f.setPostedByUserId(rs.getString(3));
	        		if(rs.getString(3)!=null)
	        			f.setPostedByUserIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(3)));
	        		
	        		f.setPostedTime(rs.getTimestamp(4));
	        		f.setPostedTimeStr(Util.getPhotoTimeFull(rs.getTimestamp(4)));
	        		f.setStatus(rs.getInt(5));
	        		list.add(f);
	        	}
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return list;
	    }
	 
	 

	 public static void udateStatus(Connection con,String forumId){

	    	PreparedStatement ps=null;
	    	String _sql="update forumNotification set status=0 where key '" +forumId + "'"; 
	    			
	        try{
	        	//System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	 ps.executeUpdate();
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	    }

*/}
