package com.pro.emp.activity;

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
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.Activity;
import com.pro.emp.domain.MessageDetails;

public class ActivityDAO {/*

	
	*//**
	 * CREATE TABLE activity ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "activityType text,"+
	    		   "fromId text,toId text,"+
	    		   "postId text,"+
	    		   "groupId text,"+
	    		   "activityTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "flow int"+
	 * 
	 *//*

	public static String createActivity( Activity act,Connection con) 
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
	    String inssql="INSERT INTO activity(key," +
		   "activityType,"+
		   "fromId,"+
		   "toId,"+
		   "postId,"+
		   "groupId,"+
		   "activityTime,"+
		   "status,"+
		   "indicator,commentId,"+
		   "flow"+
	       ") values(\'"+id+"\',"+
	       act.getActivityType()+",\'"+
	       act.getFromId()+"\',\'"+
	       act.getToId()+"\',\'"+
	       act.getPostId()+"\',\'"+
	       act.getGroupId()+"\',\'"+
	       ss+"\',"+
	       act.getStatus()+","+
	       "1"+",'"+
	       act.getCommentId()+"\',"+
	       act.getFlow()+")";
	    try
	    {
	    	
    		//System.out.println(" activity query " + inssql);
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
	    return null;
	}
	    public static List<Activity> getActivities(Connection con){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	Activity act=null;
	    	List<Activity> list=new ArrayList<Activity>();
	    	String _sql="select " + 
	    	"KEY,"+
 		   "activityType,"+
 		   "fromId,toId,"+
 		   "postId,"+
 		   "groupId,"+
 		   "activityTime,"+
 		   "status,"+
 		   "flow,commentId from activity";
	        try{
	        	//System.out.println(" sql in empById " + _sql);
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		act=new Activity();
	        		act.setKey(rs.getString(1));
	        		act.setActivityType(rs.getInt(2));
	        		act.setFromId(rs.getString(3));
	        		if(act.getFromId()!=null)
	        			act.setFromIdInfo(CacheRecords.getInstance().getCacheData(act.getFromId()));
	        		act.setToId(rs.getString(4));
	        		if(act.getToId()!=null)
	        			act.setToIdInfo(CacheRecords.getInstance().getCacheData(act.getToId()));
	        		act.setPostId(rs.getString(5));
	        		if(act.getPostId()!=null)
	        			act.setPostIdInfo(PostDAO.getPostDetailByPostId(con, act.getPostId()));
	        		act.setGroupId(rs.getString(6));
	        		if(act.getGroupId()!=null){
	        			GroupDAO ff=new GroupDAO();
	        			act.setGroupIdInfo(ff.getGroupById(con, act.getGroupId()));
	        		}
	        		if(act.getCommentId()!=null){
	        			act.setCommentIdInfo(PostCommentsDAO.getCommentById(act.getCommentId(), con));
	        		}
	        		act.setActivityTime(rs.getTimestamp(7));
	        		act.setStatus(rs.getInt(8));
	        		act.setFlow(rs.getInt(9));
	        		list.add(act);
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

	
	
*/}
