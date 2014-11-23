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
import com.pro.emp.domain.Group;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.UserGroups;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class UserGroupDAO extends EmpCommanDAO{/*


	
		
	public  Group addUserToGroup(String groupId,String userId,Connection con){
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
	        String inssql="INSERT INTO userGroups(" +
	        						"key,"+
	        						"groupId ," +
	                                "userId," +
	                                "joinedOn, "+
	                                "indicator " +
	                                ") values (" +
	                                "'"+id
	                                +"','"+groupId
	                                +"','"+userId
	                                +"','"+ ss
	                                +"',1"
	                              
	                                +")";
	       // System.out.println(" sql " + inssql);
            ps=con.prepareStatement(inssql);
             
            ps.executeUpdate();
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
		return null;
	}
	
	public static int deleteUserFromGroup(Connection con, String groupId,String userId){
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		String _sql0="select key from userGroups where groupId='"+groupId+"' and userId='"+userId+"' and indicator=1";
		
		
	
		//System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	ps = con.prepareStatement(_sql0);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		//System.out.println(" deleting key LIke " + rs.getString(1));
	    		PostLikeDAO.deleteLike(con,rs.getString(1));
	    		String _sql="DELETE FROM userGroups WHERE key =\'"+rs.getString(1)+"\'";
	    		PreparedStatement sss =con.prepareStatement(_sql);
	    		sss.executeUpdate();
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
	
	
	
	public  List<UserGroups> getUsersForGroup(Connection con,String groupId){
		List<UserGroups> list=new ArrayList<UserGroups>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
    		            
	        String inssql="select key,groupId ,userId,joinedOn from userGroups where indicator=1 AND groupId='"+groupId+"'";
	        //System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	while(rs.next())
        	{
        		UserGroups info=new UserGroups();
        		info.setKey(rs.getString(1));
        		info.setGroupId(rs.getString(2));
        		info.setUserId(rs.getString(3));
        		info.setJoinedOnStr(Util.getProperTimeINGroup(rs.getTimestamp(4)));//(EmpInfoDAO.getBasicEmpById_(con, rs.getString(3)));
        		info.setJoinedOn(rs.getTimestamp(4));
        		info.setUserIdInfo(CacheRecords.getInstance().getCacheData( rs.getString(3)));
        		
        		list.add(info);
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public  int isUserBelongsToGroup(Connection con,String groupId,String userId){
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	int result=0;
    	try
        {
    		            
	        String inssql="select key,groupId ,userId,joinedOn from userGroups where indicator=1 AND groupId='"+groupId+"' and userId='"+userId+"'";
	        //System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
        	if(rs.next())
        	{
        		result=1;
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
//	public static Group getGroupById(Connection con,String groupId){
//		Group g=null;
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1 AND key='"+groupId+"'";
//	        System.out.println(" SSSSSSSSSSSSSSss " + inssql);
//            ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	if(rs.next())
//        	{
//        		 g=new Group();
//        		g.setKey(rs.getString(1));
//        		g.setName(rs.getString(2));
//        		g.setOwnerId(rs.getString(3));
//        		g.setOwnerIdInfo(EmpInfoDAO.getBasicEmpById_(con, rs.getString(3)));
//        		g.setDescription(rs.getString(4));
//        		g.setCreatedOn(rs.getTimestamp(5));
//        		g.setCreatedOnStr(Util.getPhotoTimeFull(g.getCreatedOn()));
//        		g.setPhotoId(rs.getString(6));
//        		g.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));
//
//        		
//        		
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return g;
//		
//	}
	
//	public static List<Group> getAllGroups(Connection con){
//		List<Group> list=new ArrayList<Group>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1";// AND ownerId='"+ownerId+"'";
//	        System.out.println(" SSSSSSSSSSSSSSss " + inssql);
//            ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		Group info=new Group();
//        		info.setKey(rs.getString(1));
//        		info.setName(rs.getString(2));
//        		info.setOwnerId(rs.getString(3));
//        		info.setOwnerIdInfo(EmpInfoDAO.getBasicEmpById_(con, rs.getString(3)));
//        		info.setDescription(rs.getString(4));
//        		info.setCreatedOn(rs.getTimestamp(5));
//        		info.setCreatedOnStr(Util.getPhotoTimeFull(info.getCreatedOn()));
//        		info.setPhotoId(rs.getString(6));
//        		info.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));
//
//        		list.add(info);
//        		
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//		
//	}
	

*/}
