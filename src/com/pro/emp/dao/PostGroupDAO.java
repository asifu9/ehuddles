package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.pro.cache.CacheRecords;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.SortComments;
import com.pro.emp.SortPhotos;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.GroupDiscussionPost;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class PostGroupDAO extends EmpCommanDAO{/*


	
		
	public  GroupDiscussionPost createGroupPost(GroupDiscussionPost post,Connection con){
		PreparedStatement ps=null;
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
    		String de=post.getDesc().replaceAll("'","''");
	        String inssql="INSERT INTO GroupDiscussionPost (" +
	        						"key,"+
	        						"groupId,"+
	        						"postedById ," +
	                                "desc," +
	                                "postedPhotoId, "+
	                                "postedVideoId ," +
	                                "postedTime,indicator"+
	                                ") values (" +
	                                "'"+id
	                                +"','"+post.getGroupId()
	                                +"','"+post.getPostedById()
	                                +"','"+de
	                                +"','"+post.getPostedPhotoId()
	                                +"','"+ post.getPostedVideoId()
	                                +"','"+ ss+"',1)";//,"+post.getPrivatestatus()+","+post.getPostType()+","+post.getPhotoCount()+")";
	        
	       //System.out.println(" ID education " + inssql + " : " + de);
            ps=con.prepareStatement(inssql);
             
            ps.executeUpdate();
            post.setKey(""+id);
            post.setPostedTimeStr(Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
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
		return post;
	}
	
	public static int deletePost(Connection con, String key){
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		String _sql="DELETE FROM GroupDiscussionPost  WHERE key =\'"+key+"\'";
		
	
		//System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	//deleteAllLikesOfPost(con,key) ;
	    	//deleteAllCOmmentsOfPost(con, key);
	    	ps = con.prepareStatement(_sql);
	    	ps.executeUpdate();
	    	_result=1;
	    }
	    catch(SQLException se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	
	public static int deleteAllCOmmentsOfPost(Connection con, String key){
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sssl = " select key from postComments where postId= '"+key+"'";
		
		
		//System.out.println(" sql " + sssl);
		int _result=0;
	    try
	    {
	    	ps = con.prepareStatement(sssl);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		//System.out.println(" deleting key comments " + rs.getString(1));
	    		PostCommentsDAO.deleteById(rs.getString(1), con);
	    	}
	    	
	    }
	    catch(SQLException se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	
	public static int deleteAllLikesOfPost(Connection con, String key){
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sssl = " select key from  likePost where postId= '"+key+"'";
		
		
	//	System.out.println(" sql " + sssl);
		int _result=0;
	    try
	    {
	    	ps = con.prepareStatement(sssl);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		//System.out.println(" deleting key LIke " + rs.getString(1));
	    		PostLikeDAO.deleteLike(con,rs.getString(1));
	    	}
	    	
	    }
	    catch(SQLException se)
	    {
	    	//transactionRollback(con);
	    	_result=2;
	        se.printStackTrace();
	    }
	   
	    return _result;
	}
	
	public static List<GroupDiscussionPost> getGroupPosts(Connection con,Timestamp start,Timestamp end,String groupId){
		List<GroupDiscussionPost> list=new ArrayList<GroupDiscussionPost>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
    		String startStr=(""+start).substring(0,19);
    		String endStr=(""+end).substring(0,19);
	        String inssql="select key,groupId,postedById ,desc,postedPhotoId,postedVideoId ,postedTime from  GroupDiscussionPost  where indicator=1 AND postedTime < '"+startStr+"' and postedTime > '"+endStr+"' AND groupId='"+groupId+"' LIMIT 10";
	        System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	while(rs.next())
        	{
        		GroupDiscussionPost info=new GroupDiscussionPost();
        		info.setKey(rs.getString(1));
        		info.setGroupId(rs.getString(2));
        		info.setPostedById(rs.getString(3));
        		info.setPostedByIdInfo(CacheRecords.getInstance().getCacheData( rs.getString(3)));
        		info.setDesc(rs.getString(4));
        		info.setPostedPhotoId(rs.getString(5));
        		if(rs.getString(5)!=null){
        			info.setPostedPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(5), con));
        		}
        		info.setPostedVideoId(rs.getString(6));
        		info.setPostedTime(rs.getTimestamp(7));
        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));

	    		Calendar cal=Calendar.getInstance();
	    		cal.setTime(rs.getTimestamp(7));
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
	   
        		
        		list.add(info);
        		}
        	
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
//	public static List<PostInfo> getPostsById(Connection con,String id){
//		List<PostInfo> list=new ArrayList<PostInfo>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	HashMap<String,String> map=new HashMap<String, String>();
//    	try
//        {
//    		
//	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from  post where indicator=1 and postedById='"+id+"'  LIMIT 50";
//           // System.out.println(" inssql " + inssql);
//	        ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
//        		try{
//        		info.setPostType(rs.getInt(8));
//        		}catch(Exception exxx){
//        			info.setPostType(0);
//        		}
//        		if(info.getPostType()==1){
//        			info.setPhotoCount(rs.getInt(9));
//        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), con,info.getPostedTime()));
//        		}
//        		Calendar cal=Calendar.getInstance();
//	    		cal.setTime(rs.getTimestamp(7));
//	    		String month="";
//	    		
//	    		if(cal.get(Calendar.MONTH)<=8)
//	    			month="0"+(cal.get(Calendar.MONTH)+1);
//	    		else
//	    			month=""+(cal.get(Calendar.MONTH)+1);
//	    		String day="";
//	    		if(cal.get(Calendar.DATE)<=9)
//	    			day="0"+cal.get(Calendar.DATE);
//	    		else
//	    			day=""+cal.get(Calendar.DATE);
//	    		String hour="";
//	    		if(cal.get(Calendar.HOUR_OF_DAY)<=9)
//	    			hour="0"+cal.get(Calendar.HOUR_OF_DAY);
//	    		else
//	    			hour=""+cal.get(Calendar.HOUR_OF_DAY);
//	    		String min="";
//	    		if(cal.get(Calendar.MINUTE)<=9)
//	    			min="0"+cal.get(Calendar.MINUTE);
//	    		else
//	    			min=""+cal.get(Calendar.MINUTE);
//	    		String sec="";
//	    		if(cal.get(Calendar.SECOND)<=9)
//	    			sec="0"+cal.get(Calendar.SECOND);
//	    		else
//	    			sec=""+cal.get(Calendar.SECOND);
//	    		info.setPostedTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
//	    	
//        		if(info.getPostedById()!=null){
//        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//        		map.put(info.getKey(), info.getKey());
//        		list.add(info);
//        		}
//        	}
//        	
//        	//second query
//        	String inssql2="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from  post where indicator=1 and postedToId='"+id+"' AND privatestatus=1  LIMIT 50";
//          //  System.out.println(" inssql " + inssql2);
//	        ps=con.prepareStatement(inssql2);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
//        		try {
//        			info.setPostType(rs.getInt(8));
//        		}catch(Exception e){info.setPostType(0);}
//        		Calendar cal=Calendar.getInstance();
//	    		cal.setTime(rs.getTimestamp(7));
//	    		String month="";
//	    		
//	    		if(cal.get(Calendar.MONTH)<=8)
//	    			month="0"+(cal.get(Calendar.MONTH)+1);
//	    		else
//	    			month=""+(cal.get(Calendar.MONTH)+1);
//	    		String day="";
//	    		if(cal.get(Calendar.DATE)<=9)
//	    			day="0"+cal.get(Calendar.DATE);
//	    		else
//	    			day=""+cal.get(Calendar.DATE);
//	    		String hour="";
//	    		if(cal.get(Calendar.HOUR_OF_DAY)<=9)
//	    			hour="0"+cal.get(Calendar.HOUR_OF_DAY);
//	    		else
//	    			hour=""+cal.get(Calendar.HOUR_OF_DAY);
//	    		String min="";
//	    		if(cal.get(Calendar.MINUTE)<=9)
//	    			min="0"+cal.get(Calendar.MINUTE);
//	    		else
//	    			min=""+cal.get(Calendar.MINUTE);
//	    		String sec="";
//	    		if(cal.get(Calendar.SECOND)<=9)
//	    			sec="0"+cal.get(Calendar.SECOND);
//	    		else
//	    			sec=""+cal.get(Calendar.SECOND);
//	    		info.setPostedTimeStr2(""+cal.get(Calendar.YEAR)+"-"+month+"-"+day+" "+hour+":"+min+":"+sec+".0");
//	    	
//        		if(info.getPostType()==1){
//        			info.setPhotoCount(rs.getInt(9));
//        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), con,info.getPostedTime()));
//        		}
//        		if(info.getPostedById()!=null){
//        			if(!map.containsKey(info.getKey())){
//		        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//		        		
//		        		list.add(info);
//        			}
//        		}
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
	
//	public static List<PostInfo> getPostsById(Connection con,String id,Timestamp t1,Timestamp t2){
//		List<PostInfo> list=new ArrayList<PostInfo>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	String startStr=(""+t1).substring(0,19);
//		String endStr=(""+t2).substring(0,19);
//    	HashMap<String,String> map=new HashMap<String, String>();
//    	try
//        {
//    		
//	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from  post where indicator=1 and postedById='"+id+"' and postedTime < '"+startStr+"' and postedTime > '"+endStr+"'  LIMIT 10";
//           // System.out.println(" inssql " + inssql);
//	        ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
//        		try{
//        		info.setPostType(rs.getInt(8));
//        		}catch(Exception exxx){
//        			info.setPostType(0);
//        		}
//        		if(info.getPostType()==1){
//        			info.setPhotoCount(rs.getInt(9));
//        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), con,info.getPostedTime()));
//        		}
//        		if(info.getPostedById()!=null){
//        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//        		map.put(info.getKey(), info.getKey());
//        		list.add(info);
//        		}
//        	}
//        	
//        	//second query
//        	String inssql2="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType,photoCount from  post where indicator=1 and postedToId='"+id+"' AND privatestatus=1 and postedTime < '"+startStr+"' and postedTime > '"+endStr+"'  LIMIT 10";
//          //  System.out.println(" inssql " + inssql2);
//	        ps=con.prepareStatement(inssql2);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
//        		try {
//        			info.setPostType(rs.getInt(8));
//        		}catch(Exception e){info.setPostType(0);}
//        		
//        		if(info.getPostType()==1){
//        			info.setPhotoCount(rs.getInt(9));
//        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), con,info.getPostedTime()));
//        		}
//        		if(info.getPostedById()!=null){
//        			if(!map.containsKey(info.getKey())){
//		        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//		        		
//		        		list.add(info);
//        			}
//        		}
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
	
//	public static List<PostInfo> getPostsJSON(Connection con){
//		List<PostInfo> list=new ArrayList<PostInfo>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//    		java.util.List<EmpInfo> empList= EmployeeInfo.getAllEmployees();
//    		HashMap<String, String> map=new HashMap<String,String>(); 
//    		HashMap<String, String> map2=new HashMap<String,String>(); 
//    		for(EmpInfo d:empList){
//    			String name=d.getEmpName();
//    			String path =d.getImagePath();
//    			map.put(String.valueOf(d.getId()), name);
//    			map2.put(String.valueOf(d.getId()), path);
//    			
//    		}
//    		
//	        String inssql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime from  post where indicator=1  LIMIT 50";
//            ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
////        		System.out.println( " phtot id  " + rs.getString(4));
//        		if(rs.getString(4)!=null){
////        			System.out.println(" PHoto i d  is not e mpty " + rs.getString(4));
//        			info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(rs.getString(4), con,map,map2));
//        		}else{
////        			System.out.println(" its empty");
//        			info.setPostedByPhotoInfo(null);
//     		}
////        		//Calendar c= Calendar.getInstance();
////        		//info.setTimeInStr(Util.getCommentTime(info.getCommentDate()));
//        		info.setLikes(PostLikeDAO.getLikesForPostId(con, info.getKey(),map,map2));
//        		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(info.getKey(), con,map,map2);
//        		SortComments s=new SortComments("PostComments");
//				Collections.sort(commList,s);
//        		info.setComments(commList);
////        		System.out.println("  info.getPostedById() " +  info.getPostedById());
//        		if(info.getPostedById()!=null){
//        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//        		
//        		list.add(info);
//        		}
//        	
//        		
//        }
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
	

//	public static String getUserIdByPostId(Connection con,String postId){
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	String userId="";
//    	try
//        {
//    		
//	        String inssql="select key,postedById from  post where indicator=1 and key='"+postId+"'";
//           // System.out.println(" inssql " + inssql);
//	        ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	if(rs.next())
//        	{
//        		 userId=rs.getString(2);
//        	}
//        	
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	//  System.out.println(" inssql " + userId);
//    	return userId;
//		
//	}
	
	

	public static GroupDiscussionPost getGrouupPostDetailByPostId(Connection con,String postId){
		GroupDiscussionPost list=new GroupDiscussionPost();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
    		
	        String inssql="select key,groupId,postedById ,desc,postedPhotoId,postedVideoId ,postedTime from  GroupDiscussionPost  where indicator=1 and key='"+postId+"'";
          //  System.out.println(" inssql " + inssql);
	        ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	if(rs.next())
        	{
        		GroupDiscussionPost info=new GroupDiscussionPost();
        		info.setKey(rs.getString(1));
        		info.setGroupId(rs.getString(2));
        		info.setPostedById(rs.getString(3));
        		info.setDesc(rs.getString(4));
        		info.setPostedPhotoId(rs.getString(5));
        		if(rs.getString(5)!=null)
        			info.setPostedPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(5), con));
        		info.setPostedVideoId(rs.getString(6));
        		info.setPostedTime(rs.getTimestamp(7));
        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));

        		if(info.getPostedById()!=null){
        		info.setPostedByIdInfo(CacheRecords.getInstance().getCacheData( info.getPostedById()));
        		
        		}
        		list=info;
        	}
        	
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
//	public static GroupDiscussionPost getFullPostDetailByPostId(Connection con,String postId){
//		GroupDiscussionPost list=new GroupDiscussionPost();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//    	
//    		 String inssql="select key,groupId,postedById ,desc,postedPhotoId,postedVideoId ,postedTime from  GroupDiscussionPost  where indicator=1 and key='"+postId+"'";
//          //  System.out.println(" inssql " + inssql);
//	        ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	if(rs.next())
//        	{
//        		PostInfo info=new PostInfo();
//        		info.setKey(rs.getString(1));
//        		info.setPostedById(rs.getString(2));
//        		info.setPostedDesc(rs.getString(3));
//        		info.setPostedPhotoId(rs.getString(4));
//        		info.setPostedVideoId(rs.getString(5));
//        		info.setPostedToId(rs.getString(6));
//        		info.setPostedTime(rs.getTimestamp(7));
//        		try{
//        			info.setPostType(rs.getInt(8));
//        		}catch(Exception e){info.setPostType(0);}
//        		
//        		if(info.getPostedPhotoId()!=null){
//        			if(info.getPostType()==0)
//        				info.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(info.getPostedPhotoId(), con, map, map2));
//        		}
//        		if(info.getPostType()==1){
//        			info.setPhotoCount(rs.getInt(9));
//        			info.setPhotoList(PhotoInfoDAO.getPhotoByAlbumIdForPostDisplay(info.getPostedPhotoId(), con,info.getPostedTime()));
//        		}
//        		info.setPostedTimeStr(Util.getCommentTime(rs.getTimestamp(7)));
//        		List<PostComments> list1=PostCommentsDAO.getCommentsForPostId(postId, con, map, map2);
//        		SortComments ob=new SortComments("PostComments");
//        		Collections.sort(list1,ob);
//        		info.setComments(list1);
//        		info.setLikes(PostLikeDAO.getLikesForPostId(con, postId, map, map2));
//        		if(info.getPostedById()!=null){
//        		info.setPostedByUserInfo(EmpInfoDAO.getEmpById(con, info.getPostedById()));
//        		
//        		}
//        		list=info;
//        	}
//        	
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
*/}
