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
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class PostDAO extends EmpCommanDAO{

/*	public static void main(String arg[]){
		//getPosts(CassandraDB.getCassConnection(),Util.convertDateToTimestamp(Calendar.getInstance().getTime()));
		//String postId="a21ab01e-5870-4f45-924c-8daa52bf5ec3";
		///getUserIdByPostId(CassandraDB.getCassConnection(),postId);
		//deletePost(CassandraDB.getCassConnection(),postId);
		getPosts(CassandraDB.getCassConnection(),Calendar.getInstance().getTime());
	}
	*/
		
	public static PostInfo createPost(PostInfo post,Session session){
    	//ResultSet rs=null;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
			Calendar currentDate=Calendar.getInstance();
			 SimpleDateFormat formatter= 
	        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
	        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
	        	  dt.setTime(currentDate.getTime().getTime());
	        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
	        	  String ss=""+tm;
	        	  ss=ss.substring(0,19);
    		String de=post.getPostedDesc().replaceAll("'","''");
	        String inssql="INSERT INTO  empInfoDB.post(" +
	        						"key,"+
	        						"postedById ," +
	                                "postedDesc," +
	                                "postedPhotoId, "+
	                                "postedVideoId ," +
	                                "postedToId,"+
	                                "postedTime,indicator,privatestatus,postType,photoCount"+
	                                ") values (?,?,?,?,?,?,?,?,?,?,?)";// +
//	                                "'"+id
//	                                +"','"+post.getPostedById()
//	                                +"','"+de
//	                                +"','"+post.getPostedPhotoId()
//	                                +"','"+ post.getPostedVideoId()
//	                                +"','"+ post.getPostedToId()
//	                                +"','"+ ss+"',1,"+post.getPrivatestatus()+","+post.getPostType()+","+post.getPhotoCount()+")";
	        System.out.println(" id " + id);
	        System.out.println(" post.getPostedById() " + post.getPostedById());
	        System.out.println(" de " + de);
	        System.out.println(" post.getPostedPhotoId() " + post.getPostedPhotoId());
	        System.out.println(" post.getPostedVideoId() " + post.getPostedVideoId());
	        System.out.println(" post.getPostedToId() " + post.getPostedToId());
	        System.out.println(" post.getPrivatestatus() " + post.getPrivatestatus());
	        System.out.println(" post.getPostType() " + post.getPostType());
	        System.out.println(" post.getPhotoCount() " + post.getPhotoCount());
	        Date ssss=Calendar.getInstance().getTime();
	        
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rs=session.execute(boundStatement.bind(
	    			id.toString(),
	    			post.getPostedById(),
	    			de,
	    			post.getPostedPhotoId(),
	    			post.getPostedVideoId(),
	    			post.getPostedToId(),
	    			ssss,
	    			1,
	    			post.getPrivatestatus(),
	    			post.getPostType(),
	    			post.getPhotoCount()));
             
           post.setPostedTime(Util.convertDateToTimestamp(ssss));
            post.setKey(id.toString());
            post.setPostedTimeStr(Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
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
		return post;
	}

	public static int deletePost(Session session, String postId,String deleteTime){
	
	
		System.out.println(" post id = " + postId + "  delete time " +  deleteTime);
		String _sql="delete from  empInfoDB.post  WHERE   indicator=1 and postedTime =? ";
		System.out.println(" postedTime " + _sql);
		Date d= Util.convertStringToDate(deleteTime);
		//System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	
	    	
	    	//deleteAllLikesOfPost(session,key) ;
	    	//deleteAllCOmmentsOfPost(session, key);
	    	ResultSet rs=session.execute(boundStatement.bind(
	    			
	    			
	    			d
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
	
	public static int deleteAllCOmmentsOfPost(Session session, String key){
		String sssl = " select key,commentDate from postComments where postId= ?";
		
		
		//System.out.println(" sql " + sssl);
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(sssl);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key));
	    	
	    	for(Row rs: rss){
	    		//System.out.println(" deleting key comments " + rs.getString(1));
	    		PostCommentsDAO.deleteById(rs.getString(0), session,rs.getDate(1));
	    	}
	    	
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	
	public static int deleteAllLikesOfPost(Session session, String key){
		String sssl = " select key,likedDate from  likePost where postId= ?";
		
		
	//	System.out.println(" sql " + sssl);
		int _result=0;
	    try
	    {
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(sssl);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key));
	    	
	    	
	    	for(Row rs:rss){
	    		//System.out.println(" deleting key LIke " + rs.getString(1));
	    		PostLikeDAO.deleteLike(session,rs.getString(0));
	    	}
	    	
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	
	public static void main(String args[]){
		/*List<PostInfo> lll=getPosts(CassandraDB.getCassConnection(),Calendar.getInstance().getTime(),"1");
		for(PostInfo info:lll){
			System.out.println(" info. __________ " + info.getPostedDesc());
			System.out.println(" " + info.getPostedPhotoId());
			System.out.println(" posted " + info.getPostedByPhotoInfo());
			System.out.println(" __________________________________________________ " + info.getFlow() + " : " + info.getPostType());
		}*/
		PostInfo info= getPostDetailByPostId(CassandraDB.getCassConnection(),"984f3c9c-89c7-4ce7-ac9d-27b70bdeb834");
		System.out.println(" info. __________ " + info.getPostedDesc());
		System.out.println(" " + info.getPostedPhotoId());
		System.out.println(" posted " + info.getPostedByPhotoInfo());
		System.out.println(" __________________________________________________ " + info.getFlow() + " : " + info.getPostType());
	
		
	}
		public static List<PostInfo> getPosts(Session session,Date start,String flow){
			List<PostInfo> list=new ArrayList<PostInfo>();
			 String inssql="";
			// flow="1";
    	try
        {
    			inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount,flow from   empInfoDB.post where indicator=1 AND privatestatus=0 AND postedTime <= ?    LIMIT 10 ALLOW FILTERING";//AND  
	        System.out.println(" SSSSSSSSSSSSSSss ---------------------------------------- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			start
	    					));
        	for(Row rs:rss)
        	{
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		System.out.println(" **************** " + info.getKey() + " *********************");
        		info.setPostedById(rs.getString(1));
        		System.out.println(" desc -- " + rs.getString(2) + " : " + rs.getInt(7));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
        		try{
        		info.setPostType(rs.getInt(7));
        		}catch(Exception ex){info.setPostType(0);}
        		if(info.getPostType()==1 && info.getPostedPhotoId()!=null){
        			info.setPhotoCount(rs.getInt(8));
        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), session));
        		}
        		
        		//System.out.println( "post type " + info.getPostType() + " : " + info.getPostedVideoId());
        		if(info.getPostType()==3){
        			if(info.getPostedVideoId()==null ){
        				info.setPostType(3);
        			}else{
        				info.setVideoInfo(VideoInfoDAO.getVideoByIdDynamic(info.getPostedVideoId(), session));
        			}
        		}else if(info.getPostType()==2){
        			info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(info.getPostedById(), info.getPostedPhotoId(), session));
        		}
        		
        		
        		info.setFlow(rs.getInt(9));

	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(Util.convertDateToTimestamp(rs.getDate(6)));
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
	    		info.setPostedTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    		
//        		System.out.println( " phtot id  " + rs.getString(4));
        		if(rs.getString(3)!=null){
        			System.out.println(" PHoto i d  is not e mpty " + rs.getString(3));
        			info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoByIdDynamicName(rs.getString(3), CassandraDB.getCassConnection()));
        		}else{
        			System.out.println(" its empty");
        			info.setPostedByPhotoInfo(null);
        		}
//        		//Calendar c= Calendar.getInstance();
//        		//info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
//        		info.setLikes(PostLikeDAO.getLikesForPostId(con, info.getKey()));
//        		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(info.getKey(), con);
//        		SortComments s=new SortComments("PostComments");
//				Collections.sort(commList,s);
//        		info.setComments(commList);
//        		System.out.println("  info.getPostedById() " +  info.getPostedById());
        		if(info.getPostedById()!=null){
        			System.out.println(" getting posted by user id info " + info.getPostedById());
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData(info.getPostedById()));
        		System.out.println(" after getting data is " + info.getPostedByUserInfo());
        		
        		}
        		if(info.getPostedToId()!=null){
        			info.setPostedToUserInfo(CacheRecords.getInstance().getCacheData(info.getPostedToId()));
        		}
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static List<PostInfo> getPostsById(String id){
		List<PostInfo> list=new ArrayList<PostInfo>();
    	HashMap<UUID,String> map=new HashMap<UUID, String>();
    	try
        {
    		Session session =CassandraDB.getCassConnection();
	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from   empInfoDB.post where indicator=1 and  postedById=? AND privatestatus=1 LIMIT 50";
           // System.out.println(" inssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id,id));
	        
        	for(Row rs:rss)
        	{
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		info.setPostedById(rs.getString(1));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
        		try{
        		info.setPostType(rs.getInt(7));
        		}catch(Exception exxx){
        			info.setPostType(0);
        		}
        		if(info.getPostType()==1){
        			info.setPhotoCount(rs.getInt(8));
        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), session));
        		}
        		Calendar cal=Calendar.getInstance();
	    		cal.setTime(Util.convertDateToTimestamp(rs.getDate(6)));
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
	    		info.setPostedTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
	    	
        		if(info.getPostedById()!=null){
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		//map.put(info.getKey(), info.getKey());
        		list.add(info);
        		}
        	}
        	
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static List<PostInfo> getPostsById(String id,Date t1){
		List<PostInfo> list=new ArrayList<PostInfo>();
    	HashMap<String,String> map=new HashMap<String, String>();
    	try
        {
    		Session session=CassandraDB.getCassConnection();
	        //String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from  post where indicator=1 and  postedById=? OR postedToId=? and postedTime <= ?  LIMIT 10 ALLOW FILTERING";
    		 String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from   empInfoDB.post where  postedById=?   LIMIT 10 ALLOW FILTERING";
    		 String inssql2="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from   empInfoDB.post where   postedToId=?   LIMIT 10 ALLOW FILTERING";
           System.out.println(" inssql " + inssql);
           System.out.println(" id  " + id + " time = " + t1);

	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	    	Set<String> li=new HashSet<String>();
	    	
	        for(Row rs:rss)
        	{
	        	
	        	 
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		info.setPostedById(rs.getString(1));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		if(info.getPostedToId()!=null)
        			info.setPostedToUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedToId()));
        		System.out.println("****** posted by " + info.getPostedById()  + " posted to "  + info.getPostedToId());
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
        		System.out.println(" message 1nd part : " + info.getPostedDesc());
        		try{
        		info.setPostType(rs.getInt(7));
        		}catch(Exception exxx){
        			info.setPostType(0);
        		}
        		if(info.getPostType()==1 && info.getPostedPhotoId()!=null){
        			info.setPhotoCount(rs.getInt(8));
        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), session));
        		}
        		if(info.getPostedById()!=null){
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		//map.put(info.getKey(), info.getKey());
        		list.add(info);
        		li.add(info.getKey());
        		}
        	}
        	
	        com.datastax.driver.core.PreparedStatement statement2 =  session.prepare(inssql2);
	    	BoundStatement boundStatement2 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement2);
	    	ResultSet rss2=session.execute(boundStatement2.bind(id));
	        for(Row rs:rss2)
        	{
	        	System.out.println(" ((((((((((((((((((");
	        	if(!li.contains(rs.getString(0))){
	        		PostInfo info=new PostInfo();
	        		info.setKey(rs.getString(0));
	        		info.setPostedById(rs.getString(1));
	        		info.setPostedDesc(rs.getString(2));
	        		info.setPostedPhotoId(rs.getString(3));
	        		info.setPostedVideoId(rs.getString(4));
	        		info.setPostedToId(rs.getString(5));
	        		if(info.getPostedToId()!=null)
	        			info.setPostedToUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedToId()));
	        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
	        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
	        		try{
	        		info.setPostType(rs.getInt(7));
	        		}catch(Exception exxx){
	        			info.setPostType(0);
	        		}
	        		if(info.getPostType()==1 && info.getPostedPhotoId()!=null){
	        			info.setPhotoCount(rs.getInt(8));
	        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), session));
	        		}
	        		if(info.getPostedById()!=null){
	        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
	        		//map.put(info.getKey(), info.getKey());
	        		System.out.println(" message 2nd part : " + info.getPostedDesc());
	        		list.add(info);
	        		}
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static List<PostInfo> getPostsJSON(Session session){
		List<PostInfo> list=new ArrayList<PostInfo>();
    	try
        {
    		
    		
	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime from   empInfoDB.post where indicator=1  LIMIT 50";

	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	        
	        for(Row rs:rss)
        	{
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		info.setPostedById(rs.getString(1));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
//        		System.out.println( " phtot id  " + rs.getString(4));
        		if(rs.getString(3)!=null){
//        			System.out.println(" PHoto i d  is not e mpty " + rs.getString(4));
        			info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(rs.getString(1), rs.getString(3), session));
        		}else{
//        			System.out.println(" its empty");
        			info.setPostedByPhotoInfo(null);
     		}
//        		//Calendar c= Calendar.getInstance();
//        		//info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
        		info.setLikes(PostLikeDAO.getLikesForPostId(session, info.getKey()));
        		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(info.getKey(), session);
        	//	SortComments s=new SortComments("PostComments");
			//	Collections.sort(commList,s);
        		info.setComments(commList);
//        		System.out.println("  info.getPostedById() " +  info.getPostedById());
        		if(info.getPostedById()!=null){
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		
        		list.add(info);
        		}
        	
        		
        }
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	

	public static String getUserIdByPostId(Session session,String postId){
    	String userId=null;;
    	try
        {
    		
	        String inssql="select KEY,postedTime,postedById from   empInfoDB.post where indicator=1 and  postedTime <= ?   and  key=?    ALLOW FILTERING";//and postedTime <= ?
            System.out.println(" inssql " + inssql);
            System.out.println(" oist ud = " + postId) ;
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			Calendar.getInstance().getTime()
	    			,
	    			//Calendar.getInstance().getTime(),
	    			//Calendar.getInstance().getTime(),
	    			postId
	    			));
	    	
        	for(Row rs:rss)
        	{
        		 userId=rs.getString(2);
        		 System.out.println(" user id = "+ userId);
        	}
        	
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	//  System.out.println(" inssql " + userId);
    	return userId;
		
	}
	
	

	public static PostInfo getPostDetailByPostId(Session session,String postId){
		PostInfo list=new PostInfo();
    	try
        {
    		
	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from   empInfoDB.post where indicator=1 and key=?";
          //  System.out.println(" inssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId));
	        
        	for(Row rs:rss)
        	{
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		info.setPostedById(rs.getString(1));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));

        		if(info.getPostedById()!=null){
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		
        		}
        		list=info;
        	}
        	
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static PostInfo getFullPostDetailByPostId(Session session,String postId){
		PostInfo list=new PostInfo();
    	try
        {
    		
    			
	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from   empInfoDB.post where indicator=1 and key=?";
          //  System.out.println(" inssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(postId));
	        
        	for(Row rs:rss)
        	{
        		PostInfo info=new PostInfo();
        		info.setKey(rs.getString(0));
        		info.setPostedById(rs.getString(1));
        		info.setPostedDesc(rs.getString(2));
        		info.setPostedPhotoId(rs.getString(3));
        		info.setPostedVideoId(rs.getString(4));
        		info.setPostedToId(rs.getString(5));
        		info.setPostedTime(Util.convertDateToTimestamp(rs.getDate(6)));
        		try{
        			info.setPostType(rs.getInt(7));
        		}catch(Exception e){info.setPostType(0);}
        		
        		if(info.getPostedPhotoId()!=null){
        			if(info.getPostType()==0)
        				info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(info.getPostedById(), info.getPostedPhotoId(), session));
        		}
        		if(info.getPostType()==1 && info.getPostedPhotoId()!=null){
        			info.setPhotoCount(rs.getInt(8));
        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), session));
        		}
        		info.setPostedTimeStr(Util.getCommentTime(Util.convertDateToTimestamp(rs.getDate(6))));
        		List<PostComments> list1=PostCommentsDAO.getCommentsForPostId(postId, session);
        	//	SortComments ob=new SortComments("PostComments");
        	//	Collections.sort(list1,ob);
        		info.setComments(list1);
        		info.setLikes(PostLikeDAO.getLikesForPostId(session, postId));
        		if(info.getPostedById()!=null){
        		info.setPostedByUserInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		
        		}
        		list=info;
        	}
        	
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
}
