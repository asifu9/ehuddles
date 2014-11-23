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
import com.pro.emp.activity.ActivityDAO;
import com.pro.emp.domain.Activity;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.util.ActivityTypes;
import com.sun.org.apache.regexp.internal.CharacterIterator;
import com.sun.org.apache.regexp.internal.StringCharacterIterator;

public class GroupDAO extends EmpCommanDAO{/*


	
		
	public  Group createGroup(Group group,Connection con){
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
    		String de=group.getDescription().replaceAll("'","''");
	        String inssql="INSERT INTO group(" +
	        						"key,"+
	        						"name ," +
	                                "ownerId," +
	                                "description, "+
	                                "indicator ," +
	                                "createdOn"+
	                                ") values (" +
	                                "'"+id
	                                +"','"+group.getName()
	                                +"','"+group.getOwnerId()
	                                +"','"+de
	                                +"',1"
	                                +",'"+ ss
	                                +"')";
	        
	      // System.out.println(" Group sql " + inssql + " : " + de);
            ps=con.prepareStatement(inssql);
             
            ps.executeUpdate();
            group.setKey(""+id);
            group.setCreatedOnStr(Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
            
            Activity act=new Activity();
            act.setActivityType(ActivityTypes.ACT_GROUP_CREATE.getValue());
            act.setFromId(group.getOwnerId());
            act.setGroupId(""+id);
            act.setStatus(1);
            act.setFlow(1);
            ActivityDAO.createActivity(act, con);
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
		return group;
	}
	
	
	public  void updatePhotoId(String photoId,String groupId,Connection con){
		PreparedStatement ps=null;
    	//ResultSet rs=null;
    	try
        {
	        String inssql="Update group set photoId='"+photoId+"' where key='"+groupId+"'";// INTO group(" +
	      // System.out.println(" Group sql " + inssql + " : ");
            ps=con.prepareStatement(inssql);
             
            ps.executeUpdate();
            
            Activity act=new Activity();
            act.setActivityType(ActivityTypes.ACT_GROUP_PHOTO_UPDATE.getValue());
            act.setGroupId(""+groupId);
            act.setStatus(1);
            act.setFlow(1);
            ActivityDAO.createActivity(act, con);
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
	
	
	public  int deleteGroup(Connection con, String key){
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		String _sql="DELETE FROM group WHERE key =\'"+key+"\'";
		
	
		//System.out.println(" sql " + _sql);
		int _result=0;
	    try
	    {
	    	//TODO also delete group references of user
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
	
	public  int deleteAllCOmmentsOfPost(Connection con, String key){
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
	
	public  int deleteAllLikesOfPost(Connection con, String key){
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
	
	public  List<Group> getGroupsForOwner(Connection con,String ownerId){
		List<Group> list=new ArrayList<Group>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1 AND ownerId='"+ownerId+"'";
	        //System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
            UserGroupDAO ugDAO=new UserGroupDAO();
        	while(rs.next())
        	{
        		Group info=new Group();
        		info.setKey(rs.getString(1));
        		info.setName(rs.getString(2));
        		info.setOwnerId(rs.getString(3));
        		info.setOwnerIdInfo(CacheRecords.getInstance().getCacheData( rs.getString(3)));
        		info.setDescription(rs.getString(4));
        		info.setCreatedOn(rs.getTimestamp(5));
        		info.setCreatedOnStr(Util.getPhotoTimeFull(info.getCreatedOn()));
        		info.setPhotoId(rs.getString(6));
        		if(rs.getString(6)!=null)
        			info.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));
        		info.setMemberCount(ugDAO.getUsersForGroup(con, info.getKey()).size());
        		list.add(info);
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public  Group getGroupById(Connection con,String groupId){
		Group g=null;
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1 AND key='"+groupId+"'";
	      //  System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            UserGroupDAO ugDAO=new UserGroupDAO();
            rs = ps.executeQuery();
        	if(rs.next())
        	{
        		 g=new Group();
        		g.setKey(rs.getString(1));
        		g.setName(rs.getString(2));
        		g.setOwnerId(rs.getString(3));
        		g.setOwnerIdInfo(CacheRecords.getInstance().getCacheData( rs.getString(3)));
        		g.setDescription(rs.getString(4));
        		g.setCreatedOn(rs.getTimestamp(5));
        		g.setCreatedOnStr(Util.getPhotoTimeFull(g.getCreatedOn()));
        		g.setPhotoId(rs.getString(6));
        		if(rs.getString(6)!=null)
        			g.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));

        		g.setMemberCount(ugDAO.getUsersForGroup(con,g.getKey()).size());
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return g;
		
	}
	
	public  List<Group> getAllGroups(Connection con){
		List<Group> list=new ArrayList<Group>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1";// AND ownerId='"+ownerId+"'";
	        //System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            //ps.setInt(1,id);
            rs = ps.executeQuery();
            UserGroupDAO ugDAO=new UserGroupDAO();
        	while(rs.next())
        	{
        		Group info=new Group();
        		info.setKey(rs.getString(1));
        		info.setName(rs.getString(2));
        		info.setOwnerId(rs.getString(3));
        		info.setOwnerIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(3)));
        		info.setDescription(rs.getString(4));
        		info.setCreatedOn(rs.getTimestamp(5));
        		info.setCreatedOnStr(Util.getPhotoTimeFull(info.getCreatedOn()));
        		info.setPhotoId(rs.getString(6));
        		if(rs.getString(6)!=null)
        			info.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));
        		
        		info.setMemberCount(ugDAO.getUsersForGroup(con, info.getKey()).size());
        		list.add(info);
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
		
	}
	
	public  List<Group> getAllGroups(Connection con,String userId){
		List<Group> list=new ArrayList<Group>();
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	try
        {
	        String inssql="select key,name ,ownerId,description,createdOn,photoId from  group where indicator=1";// AND ownerId='"+ownerId+"'";
	       // System.out.println(" SSSSSSSSSSSSSSss " + inssql);
            ps=con.prepareStatement(inssql);
            rs = ps.executeQuery();
            UserGroupDAO ugDAO=new UserGroupDAO();
        	while(rs.next())
        	{
        		//System.out.println(" name " + rs.getString(2));
        		if(!rs.getString(3).equalsIgnoreCase(userId)){
        		Group info=new Group();
        		info.setKey(rs.getString(1));
        		info.setName(rs.getString(2));
        		info.setOwnerId(rs.getString(3));
        		info.setOwnerIdInfo(CacheRecords.getInstance().getCacheData(rs.getString(3)));
        		info.setDescription(rs.getString(4));
        		info.setCreatedOn(rs.getTimestamp(5));
        		info.setCreatedOnStr(Util.getPhotoTimeFull(info.getCreatedOn()));
        		info.setPhotoId(rs.getString(6));
        		if(rs.getString(6)!=null)
        			info.setPhotoIdInfo(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(6), con));
        		
        		info.setIsMember(ugDAO.isUserBelongsToGroup(con, info.getKey(), userId));
        		info.setMemberCount(ugDAO.getUsersForGroup(con, info.getKey()).size());
        		list.add(info);
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
		
	}
*/}
