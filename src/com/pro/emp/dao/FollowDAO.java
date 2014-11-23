package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.print.DocFlavor.STRING;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.SortComments;
import com.pro.emp.SortPhotos;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class FollowDAO extends EmpCommanDAO{

/*	public static void main(String arg[]){
		//getPosts(CassandraDB.getCassConnection(),Util.convertDateToTimestamp(Calendar.getInstance().getTime()));
		//String postId="a21ab01e-5870-4f45-924c-8daa52bf5ec3";
		///getUserIdByPostId(CassandraDB.getCassConnection(),postId);
		//deletePost(CassandraDB.getCassConnection(),postId);
		getPosts(CassandraDB.getCassConnection(),Calendar.getInstance().getTime());
	}
	*/
		
	public  Follow createFollow(Follow follow){
    	//ResultSet rs=null;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
    		Session session = CassandraDB.getCassConnection();
			Calendar currentDate=Calendar.getInstance();
			 SimpleDateFormat formatter= 
	        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
	        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
	        	  dt.setTime(currentDate.getTime().getTime());
	        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
	        	  String ss=""+tm;
	        	  ss=ss.substring(0,19);
	        	  
	        String inssql="INSERT INTO empInfoDB.follow (" +
	        						"KEY ,"+
	        						"followBy  ," +
	                                "followTo ," +
	                                "followStartTime , "+
	                                "indicator " +
	                                ") values (?,?,?,?,?)";// +
//	                                "'"+id
//	                                +"','"+post.getPostedById()
//	                                +"','"+de
//	                                +"','"+post.getPostedPhotoId()
//	                                +"','"+ post.getPostedVideoId()
//	                                +"','"+ post.getPostedToId()
//	                                +"','"+ ss+"',1,"+post.getPrivatestatus()+","+post.getPostType()+","+post.getPhotoCount()+")";
	        Date ssss=Calendar.getInstance().getTime();
	        follow.setFollowStartTime(Util.convertDateToTimestamp(ssss));
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rs=session.execute(boundStatement.bind(
	    			id.toString(),
	    			follow.getFollowBy(),
	    			follow.getFollowTo(),
	    			ssss,
	    			1));
             follow.setKey(id.toString());
        }
        catch(Exception se)
        {
        	//transactionRollback(con);
            System.out.println("Insertion Error");
            se.printStackTrace();
        }
        finally
        {
        	//CloseConnections(ps, rs);
        }
		return follow;
	}

	public static void main(String args[]){
		 FollowDAO ss= new FollowDAO();
		 FollowDAO d=new FollowDAO();
		List<Follow> list=	d.getAllFollowers("5d642094-fb8f-4ca3-90de-7fe57d8b194f");
		for(Follow l:list){
			System.out.println(" follow By: " + l.getFollowBy() + " | followTo : " + l.getFollowTo() + " |  key = " + l.getKey());
		}
		 ss.unfollow(CassandraDB.getCassConnection(), 
				 "27cedc9c-3c92-4d21-b9e4-94e9c689844a", 
				 "5d642094-fb8f-4ca3-90de-7fe57d8b194f", "963f82ed-5898-4e58-b314-2918349e9fa9");
		 
		 
	}
	public  int unfollow(Session session, String followBy,String followTo,String key){
	
	
		String _sql="delete from empInfoDB.follow  WHERE  followTo =? and key= ?";
		System.out.println(" postedTime " + _sql);
		System.out.println(" followBy " + followBy);
		System.out.println(" followTo " + followTo);
		System.out.println(" key " + key);
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rs=session.execute(boundStatement.bind(
	    			followTo,key
	    			//postId
	    			));
	    	_result=1;
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	


	
		public  List<Follow> getAllFollowers(String followTo){
			List<Follow> list=new ArrayList<Follow>();
    	try
        {
    		System.out.println(" followTo " + followTo);
    		Session session =  CassandraDB.getCassConnection();
	        String inssql="select KEY,followBy  ,followTo ,followStartTime from empInfoDB.follow where indicator=1 and followTo=?   LIMIT 10 ALLOW FILTERING";//AND  
	        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			followTo
	    					));
        	for(Row rs:rss)
        	{
        		System.out.println(" got result");
        		Follow info=new Follow();
        		info.setKey(rs.getString(0));
        		info.setFollowBy(rs.getString(1));
        		info.setFollowTo(rs.getString(2));
        		info.setFollowByUserInfo(CacheRecords.getInstance().getCacheData(info.getFollowBy()));
        		System.out.println(" info. " + info.getFollowByUserInfo().getEmpName());
        		info.setFollowToUserInfo(CacheRecords.getInstance().getCacheData(info.getFollowTo()));
        		System.out.println(" info. " + info.getFollowToUserInfo().getEmpName());
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	
}
