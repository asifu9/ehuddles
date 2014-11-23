package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.pro.emp.Util;
import com.pro.emp.domain.Comments;


public class CommentsDAO extends EmpCommanDAO{
	
	public static void createComment(Comments comment,Connection con){
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		UUID id= java.util.UUID.randomUUID();
		Calendar currentDate=Calendar.getInstance();
		 SimpleDateFormat formatter= 
       	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 String inssql="INSERT INTO empInfoDB.comments(key," +
         "empid," +
         "day, "+
         "year, "+
         "month, "+
         "description, "+
         "time, "+
         "commentedBy "+
         ") values('"+id+"','"+comment.getEmpid()+"',"+comment.getDay()+"," +
         		""+comment.getYear()+","+comment.getMonth()+",'"+comment.getDescription()+"'" +
         				",'"+ss+"','"+comment.getCommentebBy()+"')";
		  try
		    {
			  //System.out.println(" SQL IS " +inssql);
		    	ps=con.prepareStatement(inssql);
		       
		        ps.executeUpdate();
		        
		       
		       // con.commit();
       
		    }
		    catch(SQLException se)
		    {
		    	//transactionRollback(con);
		        System.out.println("Insertion Error");
		        se.printStackTrace();
		    }
		    finally
		    {
		    	
		    	//CloseConnections(ps, rs);
		    }

	}
	
/*public static Set<Comments> getCommentsByDay(String empId,int year,int month,int day,Connection con){
		
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	Set<Comments> comments=new HashSet<Comments>();
    	HashMap<UUID, String> empMap = Util.getAllEmployeeInternalIdByName();
    	//System.out.println(" emp Map is " + empMap);
    	Comments com=null;
    	String _sql="select " + 
    	 			 "key," +
			    	 "empid," +
			         "year, "+
			         "month, "+
			         "day, "+
			         "description, "+
			         "time, "+
			         "commentedBy "+
			         " from comments where empid='"+empId.trim()+"' and year= "+year+" and month="+month+" and day="+day;
    	  try{
    		//  System.out.println(" SQL IN " + _sql);
             	ps = con.prepareStatement(_sql);
             	rs = ps.executeQuery();
          	while(rs.next())
          	{
          		com=new Comments();
          		com.setId(rs.getString(1));		
          		com.setEmpid(rs.getString(2));
          		com.setYear(rs.getInt(3));
          		com.setMonth(rs.getInt(4));
          		com.setDay(rs.getInt(5));
          		com.setDescription(rs.getString(6));
          		com.setTime(rs.getTimestamp(7));
          		//System.out.println(" rs.getString(8) " + rs.getString(8));
          		com.setCommentebBy(empMap.get(rs.getString(8)));
          		comments.add(com);

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
          return comments;
  	}*/
/*
public static Set<Comments> getCommentsByMonth(String empId,int year,int month,Connection con){
	
	PreparedStatement ps=null;
	ResultSet rs=null;
	Set<Comments> comments=new HashSet<Comments>();
	Comments com=null;
	String _sql="select " + 
	 			 "key," +
		    	 "empid," +
		         "year, "+
		         "month, "+
		         "day, "+
		         "description, "+
		         "time, "+
		         "commentedBy "+
		         " from comments where empid='"+empId.trim()+"' and year= "+year+" and month="+month;
	  try{
		  //System.out.println(" SQL IS " + _sql);
         	ps = con.prepareStatement(_sql);
      	rs = ps.executeQuery();
      	while(rs.next())
      	{
      		com=new Comments();
      		com.setId(rs.getString(1));		
      		com.setEmpid(rs.getString(2));
      		com.setYear(rs.getInt(3));
      		com.setMonth(rs.getInt(4));
      		com.setDay(rs.getInt(5));
      		com.setDescription(rs.getString(6));
      		com.setTime(rs.getTimestamp(7));
      		com.setCommentebBy(rs.getString(8));
      		comments.add(com);

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
      return comments;
	}
*/
	public static boolean deleteComment(Connection con, String id)
	{
		PreparedStatement ps=null;
		
		String _sql="DELETE FROM comments WHERE key = '"+id+"'";
		int _result=0;
	    try
	    {
	    	ps = con.prepareStatement(_sql);
	    	 ps.executeUpdate();
	    	 _result=1;
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
	    if(_result==0)
	    {
	    	return false;
	    }
	    else
	    {
	    	return true;
	    }
	}
}
