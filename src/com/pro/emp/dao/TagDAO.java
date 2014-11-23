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
import com.pro.emp.domain.Tag;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class TagDAO extends EmpCommanDAO{

/*	public static void main(String arg[]){
		//getPosts(CassandraDB.getCassConnection(),Util.convertDateToTimestamp(Calendar.getInstance().getTime()));
		//String postId="a21ab01e-5870-4f45-924c-8daa52bf5ec3";
		///getUserIdByPostId(CassandraDB.getCassConnection(),postId);
		//deletePost(CassandraDB.getCassConnection(),postId);
		getPosts(CassandraDB.getCassConnection(),Calendar.getInstance().getTime());
	}
	*/
		
	public  Tag createTag(Tag tag){
    	//ResultSet rs=null;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
    		Session session = CassandraDB.getCassConnection();
			Calendar currentDate=Calendar.getInstance();
	        String inssql="INSERT INTO empInfoDB.tag (" +
	        						"KEY ,"+
	        						"photoId  ," +
	                                "ownerId,taggedUserId,description,tagTime,follow ," +
	                                "indicator " +
	                                ") values (?,?,?,?,?,?,?,?)";// +
//	                                "'"+id
//	                                +"','"+post.getPostedById()
//	                                +"','"+de
//	                                +"','"+post.getPostedPhotoId()
//	                                +"','"+ post.getPostedVideoId()
//	                                +"','"+ post.getPostedToId()
//	                                +"','"+ ss+"',1,"+post.getPrivatestatus()+","+post.getPostType()+","+post.getPhotoCount()+")";
	        Date ssss=Calendar.getInstance().getTime();
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rs=session.execute(boundStatement.bind(
	    			id.toString(),
	    			tag.getPhotoId(),
	    			tag.getOwnerId(),
	    			tag.getTaggedUserId(),
	    			tag.getDesc(),
	    			ssss,1,
	    			1));
             tag.setKey(id.toString());
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
		return tag;
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
	


	
		public  List<Tag> getTaggerUserByPhotoId(String photoId){
			List<Tag> list=new ArrayList<Tag>();
    	try
        {
    		/**
    		 * 	        						"KEY ,"+
	        						"photoId  ," +
	                                "ownerId,taggedUserId,desc,tagTime,follow ," +
	                                "indicator " +

    		 */
    		System.out.println(" photo " + photoId);
    		Session session =  CassandraDB.getCassConnection();
	        String inssql="select KEY,taggedUserId,photoid,description,follow,indicator from empInfoDB.tag where indicator=1 and photoId=? ALLOW FILTERING";//AND  
	        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			photoId
	    					));
        	for(Row rs:rss)
        	{
        		System.out.println(" got result");
        		Tag t=new Tag();
        		t.setKey(rs.getString(0));
        		t.setTaggerUserIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(1)));
        		
        		list.add(t);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
		
		public  List<Tag> getTaggerPhotoIdByTaggedUser(String userId){
			List<Tag> list=new ArrayList<Tag>();
    	try
        {
    		/**
    		 * 	        						"KEY ,"+
	        						"photoId  ," +
	                                "ownerId,taggedUserId,desc,tagTime,follow ," +
	                                "indicator " +

    		 */
    		System.out.println(" photo " + userId);
    		Session session =  CassandraDB.getCassConnection();
	        String inssql="select KEY,taggedUserId,photoid,description,follow,indicator from empInfoDB.tag where indicator=1 and taggedUserId=? ALLOW FILTERING";//AND  
	        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			userId
	    					));
        	for(Row rs:rss)
        	{
        		System.out.println(" got result");
        		Tag t=new Tag();
        		t.setKey(rs.getString(0));
        		t.setPhotoIdInfo(PhotoInfoDAO.getBasicPhotoInfoById(rs.getString(2),CassandraDB.getCassConnection()));
        		list.add(t);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
		public  void deleteTag(String key){
    	try
        {
    		/**
    		 * 	        						"KEY ,"+
	        						"photoId  ," +
	                                "ownerId,taggedUserId,desc,tagTime,follow ," +
	                                "indicator " +

    		 */
    		System.out.println(" photo " + key);
    		Session session =  CassandraDB.getCassConnection();
	        String inssql="delete from empInfoDB.tag where indicator=1 and key=?";//AND  
	        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			key
	    					));
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
		
	}
		
		public  void unfollowTaggedPhoto(String key){
	    	try
	        {
	    		/**
	    		 * 	        						"KEY ,"+
		        						"photoId  ," +
		                                "ownerId,taggedUserId,desc,tagTime,follow ," +
		                                "indicator " +

	    		 */
	    		System.out.println(" photo " + key);
	    		Session session =  CassandraDB.getCassConnection();
		        String inssql="update empInfoDB.tag set follow=0 where indicator=1 and key=?";//AND  
		        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
		        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(
		    			key
		    					));
	            
	        }
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
			
		}
}
