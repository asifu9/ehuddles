package com.pro.friends.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import com.pro.emp.domain.MessageDetails;
import com.pro.friends.domain.Friends;

public class FriendsDAO {

	public static String createFriend( Friends f,Connection con) 
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		Calendar currentDate = Calendar.getInstance();
		 int result=-1;
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO friends(key," +
	    						"fromId,"+
	                            "toId," +
	                            "status,requestedTime,indicator"+
	                            ") values(\'"+id+"\',\'"+f.getFromId()+"\',\'"+f.getToId()+"\',0,\'"+ss+"\',1)";
	    try
	    {
	    	
    		
	    	ps=con.prepareStatement(inssql);
	        
	        
	        ps.executeUpdate();
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // con.commit();
	    	result=1;
	    }
	    catch(SQLException se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(con);
	        se.printStackTrace();
	    }
	    
	    return ""+id;
	  }
	
	
	public static Friends getFriendStatus(String fromId, String toId,Connection con){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	Friends f=null;

	    	String _sql="select " + 
	    				"key," +
	                    "fromId, "+
	                    "toId,status,requestedTime,responsedTime" +
	    				" from friends where  	fromId = '"+fromId+"' AND toId='"+toId+"'";
	        try{
	        	System.out.println(" sql in friend request " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	if(rs.next())
	        	{
	        		f=new Friends();
	        		f.setKey(rs.getString(1));
	        		f.setFromId(rs.getString(2));
	        		f.setToId(rs.getString(3));
	        		f.setStatus(rs.getInt(4));
	        		f.setRequestedTime(rs.getTimestamp(5));
	        		f.setResponsedTime(rs.getTimestamp(5));
	        		
	        	}
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	        	f=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return f;
	    
	}
}
