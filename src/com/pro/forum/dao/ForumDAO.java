package com.pro.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.pro.cache.CacheRecords;
import com.pro.emp.Util;
import com.pro.emp.domain.Forum;
import com.pro.emp.domain.ForumDiscussion;

public class ForumDAO {/*


	public static String createNewForum( Forum f,Connection con) 
	{
		PreparedStatement ps=null;
		Calendar currentDate = Calendar.getInstance();
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO forum(key," +
	    						"ownerId,body,"+
	                            "subject," +
	                            "forumType,time,indicator,status"+
	                            ") values(\'"+id+"\',\'"+f.getOwnerId()+"\',\'"+f.getBody()+"\',\'"+f.getSubject()+"\',"+f.getForumType()+",\'"+ss+"\',1,1)";
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
	
	 public static List<Forum> getAllForums(Connection con){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	List<Forum> list=new ArrayList<Forum>();
	    	Forum f=null;
	    	*//**
			  * ="CREATE TABLE forum ("+
		    		   "KEY uuid PRIMARY KEY,"+
		    		   "ownerId text,"+
		    		   "subject text,"+
		    		   "forumType int,"+
		    		   "time timestamp,"+
		    		   "status int,"+
		    		   "indicator int"+
			  *//*
	    	String _sql="select " + 
	    				"key," +
	                    "ownerId, "+
	                    "subject,formType,time,status" +
	    				" from forum where indicator = 1";
	        try{
	        	//System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		f=new Forum();
	        		f.setKey(rs.getString(1));
	        		f.setOwnerId(rs.getString(2));
	        		f.setOwnerIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(2)));
	        		f.setSubject(rs.getString(3));
	        		f.setForumType(rs.getInt(4));
	        		f.setTime(rs.getTimestamp(5));
	        		f.setStatus(rs.getInt(6));
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
	 
	 

	 public static Forum getForumByForumId(Connection con,String forumId){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	Forum forum=null;
	    	*//**
			  * ="CREATE TABLE forum ("+
		    		   "KEY uuid PRIMARY KEY,"+
		    		   "ownerId text,"+
		    		   "subject text,"+
		    		   "forumType int,"+
		    		   "time timestamp,"+
		    		   "status int,"+
		    		   "indicator int"+
			  *//*
	    	String _sql="select " + 
	    				"key," +
	                    "ownerId, "+
	                    "subject,formType,time,status,body" +
	    				" from forum where indicator = 1 and key='"+forumId + "'";
	        try{
	        	System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	if(rs.next())
	        	{
	        		forum=new Forum();
	        		forum.setKey(rs.getString(1));
	        		forum.setOwnerId(rs.getString(2));
	        		forum.setOwnerIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(2)));
	        		forum.setSubject(rs.getString(3));
	        		forum.setForumType(rs.getInt(4));
	        		forum.setTime(rs.getTimestamp(5));
	        		forum.setTimeStr(Util.getPhotoTimeFull(rs.getTimestamp(5)));
	        		forum.setStatus(rs.getInt(6));
	        		forum.setBody(rs.getString(7));
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
	        return forum;
	    }

	 
	 public static Set<String> getAllUsersForForumId(Connection con,String forumId,String ownerId){

	    	Map<String,String> m=new HashMap<String, String>();
	    	List<ForumDiscussion> dis= ForumDiscussionDAO.getAllDiscussionForForumsId(forumId, con);
	    	m.put(ownerId,"");
	    	for(ForumDiscussion d:dis)
	    		m.put(d.getReplierId(),"");
	    	
	    	return m.keySet();
	    	
	    }
*/}
