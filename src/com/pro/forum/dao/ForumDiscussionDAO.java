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

import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.ForumDiscussion;

public class ForumDiscussionDAO {/*



	public static String createNewDiscussion( ForumDiscussion d,Connection con) 
	{
		PreparedStatement ps=null;
		Calendar currentDate = Calendar.getInstance();
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
		 *//**
			 * private String key;
	private String forumId;
	private String replierId;
	private EmpInfo replierIdInfo;
	private String body;
	private Timestamp time;
	private String timeStr;
			 *//*
		 String sss= d.getBody().replace("'", "''");
	    String inssql="INSERT INTO forumDiscussion(key," +
	    						"forumId,"+
	                            "replierId," +
	                            "body,time,indicator"+
	                            ") values(\'"+id+"\',\'"+d.getForumId()+"\',\'"+d.getReplierId()+"\',\'"+sss+"\',\'"+ss+"\',1)";
	    try
	    {
	    	
    		System.out.println(" sql is "+ inssql);
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
	
	 public static List<ForumDiscussion> getAllDiscussionForForumsId(String forumId,Connection con){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	List<ForumDiscussion> list=new ArrayList<ForumDiscussion>();
	    	ForumDiscussion f=null;
	    	*//**
			 * private String key;
	private String forumId;
	private String replierId;
	private EmpInfo replierIdInfo;
	private String body;
	private Timestamp time;
	private String timeStr;
			 *//*
	    	String _sql="select " + 
	    				"key," +
	                    "forumId,replierId, "+
	                    "body,time" +
	    				" from forumDiscussion where forumId='"+forumId+"' and indicator = 1";
	        try{
	        	System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		f=new ForumDiscussion();
	        		f.setKey(rs.getString(1));
	        		f.setForumId(rs.getString(2));
	        		f.setReplierId(rs.getString(3));
	        		if(f.getReplierId()!=null)
	        			f.setReplierIdInfo(EmpInfoDAO.getEmpById(con, rs.getString(3)));
	        		f.setBody(rs.getString(4));
	        		f.setTime(rs.getTimestamp(5));
	        		f.setTimeStr(Util.getPhotoTimeFull(rs.getTimestamp(5)));
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
	 
	 
	 public static void deleteThread(Connection con,String key){
			PreparedStatement ps=null;
	    	String str="delete from forumDiscussion where key='"+key+"'";
	    	
	    	 try{
		        	System.out.println(" sql in empById " + str);
		           	ps = con.prepareStatement(str);
		        	ps.executeUpdate();
		        }
		        catch(SQLException se)
		        {
		        	//transactionRollback(con);
		            se.printStackTrace();
		        }
	 }
	 
*/}