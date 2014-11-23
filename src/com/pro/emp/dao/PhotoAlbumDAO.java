package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.SortPhotos;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoInfo;

public class PhotoAlbumDAO extends EmpCommanDAO {
	
public static int createPhotoAlbum(PhotoAlbum album,Session session){
		

    	int result=0;
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
    		 UUID id= java.util.UUID.randomUUID();
	        String inssql="INSERT INTO empInfoDB.photoAlbum(" +
	        						"key,"+
	                                "name, "+
	                                "coverScreenPath," +
	                                "createdOn,updatedOn,id,privacy,description,indicator,albumType) values(?,?,?,?,?,?,?,?,?,?) ";// +
//	                                "(\'"+id+"\'" +
//	                                ",\'"+album.getName()+"\'"+
//	                                ",\'"+album.getCoverScreenPath()+"\'"+
//	                                ",\'"+ss+"\'"+
//	                                ",\'"+ss+"\'"+
//	                                ",\'"+album.getUserId()+"\'"+
//	                                ","+album.getPrivacy()+",\'"+album.getDesc()+"\',1,"+album.getAlbumType()+
	                                
	                                
	                                
	                           //     ")";
	       //System.out.println(" SQL is " + inssql);
             	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
    	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
    	    	ResultSet rss=session.execute(boundStatement.bind(
    	    			id.toString(),
    	    			album.getName(),
    	    			album.getCoverScreenPath(),
    	    			Calendar.getInstance().getTime(),
    	    			Calendar.getInstance().getTime(),
    	    			album.getUserId(),
    	    			album.getPrivacy(),
    	    			album.getDesc(),
    	    			1,
    	    			album.getAlbumType()
    	    			));
 
          
            //session.commit();
            result=1;
    		
        }
        catch(Exception se)
        {
        	
        	//transactionRollback(session);
            System.out.println("Insertion Error");
            se.printStackTrace();
            result=0;
        }

		return result;
	}

	public static List<PhotoAlbum> getAlbumByUserId(String id){
		List<PhotoAlbum> list=new ArrayList<PhotoAlbum>();
    	int result=0;
    	try
        {
    		
    		Session session= CassandraDB.getCassConnection();
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where id=? and indicator=1  ALLOW FILTERING";
            //System.out.println(" sql is --- " + inssql);
	     	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
        	for(Row rs:rss)
        	{
        		PhotoAlbum info=new PhotoAlbum();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		if(rs.getString(2)!=null && !rs.getString(2).equalsIgnoreCase("null")){
        			info.setCoverScreenPath(rs.getString(2));
        			PhotoInfo ooo=PhotoInfoDAO.getPhotoByAlbumIdSize(info.getIdPhotoAlbum(), session, info.getCoverScreenPath());
        			if(ooo!=null){
        				info.setWidth(ooo.getWidth());
        				info.setHeight(ooo.getHeight());
        				info.setCoverScreenPath(ooo.getPhotoPath());
        			}
        				
        		}else{
        			List<PhotoInfo> infoss = PhotoInfoDAO.getPhotoByAlbumId(info.getIdPhotoAlbum(), session);
        			SortPhotos s=new SortPhotos("PhotoAlbum");
    				Collections.sort(infoss,s);
        			if(infoss.size()>0){
        				info.setCoverScreenPath(infoss.get(0).getPhotoPath());
        				info.setWidth(infoss.get(0).getWidth());
        				info.setHeight(infoss.get(0).getHeight());
        			}
        		}
        		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(rs.getString(0), session));
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setStrCreatedOn(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(3))));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" " + info.getIdPhotoAlbum() + " : " + info.getName() + " : " + info.getCoverScreenPath() + " : " +
        		//		info.getCreatedOn() + " : " + info.getUpdatedOn() + " : " + info.getUserId() + " : " + info.getPrivacy());
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static List<PhotoAlbum> getAlbumUserId(Session session,String id){
		List<PhotoAlbum> list=new ArrayList<PhotoAlbum>();
    	int result=0;
    	try
        {
    		
    		
	        String inssql="select UserId from  empInfoDB.photoAlbumInfo where id=? and indicator=1";
            //System.out.println(" sql is --- " + inssql);
	     	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		PhotoAlbum info=new PhotoAlbum();
        		info.setUserId(rs.getString(0));
        		
        		if(rs.getString(0)!=null){
        			info.setUserId(rs.getString(0));
        		}
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	
	public static List<PhotoAlbumInfo> getAllAblumnsWithLatest(Session session){
		List<PhotoAlbumInfo> list=new ArrayList<PhotoAlbumInfo>();
    	int result=0;
    	try
        {
    	
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where  indicator=? LIMIT 10  ALLOW FILTERING";
            //System.out.println(" sql is --- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(1));
	        for(Row rs:rss)
        	{
        		PhotoAlbumInfo info=new PhotoAlbumInfo();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		info.setCoverScreenPath(rs.getString(2));
        		//System.out.println(" cover path in photo album is " + rs.getString(3));
        			if(rs.getString(2)==null || rs.getString(2).equalsIgnoreCase("null"))
        			{
        				List<PhotoInfo> sss= PhotoInfoDAO.getPhotoByAlbumId(info.getIdPhotoAlbum(), session);
        				SortPhotos s=new SortPhotos("PhotoAlbum");
        				Collections.sort(sss,s);
        				if(sss.size()>0)
        					info.setCoverScreenPath(sss.get(0).getPhotoPath());
        				//System.out.println("after--------------- cover path in photo album is " + rs.getString(3));
        			}
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setTimeInStr(Util.getPhotoCreationTime(info.getUpdatedOn()));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" Map data " + map );
        		if(info.getUserId()!=null){
        		//System.out.println(" id is  " + info.getUserId() + " : " + info.getIdPhotoAlbum() + " : " + info.getName());
        		EmpBasicInfo i = CacheRecords.getInstance().getCacheData( info.getUserId());
        		info.setUserName(i.getEmpName());
        		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(info.getIdPhotoAlbum(), session));
        		list.add(info);
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static List<PhotoAlbum> getAlbumByUserIdPublic(Session session,String id){
		List<PhotoAlbum> list=new ArrayList<PhotoAlbum>();
    	int result=0;
    	try
        {
    		
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where id=? and indicator=1 and privacy=1  ALLOW FILTERING";
            //System.out.println(" sql is --- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		PhotoAlbum info=new PhotoAlbum();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		info.setCoverScreenPath(rs.getString(2));
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setStrCreatedOn(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(3))));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" " + info.getIdPhotoAlbum() + " : " + info.getName() + " : " + info.getCoverScreenPath() + " : " +
        		//		info.getCreatedOn() + " : " + info.getUpdatedOn() + " : " + info.getUserId() + " : " + info.getPrivacy());
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static List<PhotoAlbum> getAlbumByUserIdPublic(String id,Session session){
		List<PhotoAlbum> list=new ArrayList<PhotoAlbum>();
    	int result=0;
    	try
        {
    		
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where id=? and indicator=1 and privacy=1  ALLOW FILTERING";
            //System.out.println(" sql is --- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		PhotoAlbum info=new PhotoAlbum();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		info.setCoverScreenPath(rs.getString(2));
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setStrCreatedOn(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(3))));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" " + info.getIdPhotoAlbum() + " : " + info.getName() + " : " + info.getCoverScreenPath() + " : " +
        		//		info.getCreatedOn() + " : " + info.getUpdatedOn() + " : " + info.getUserId() + " : " + info.getPrivacy());
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static PhotoAlbum getUserProfileAlbumByUserId(String id,Session session){
		PhotoAlbum list=null;
    	int result=0;
    	try
        {
    		
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where id=? and indicator=1 and privacy=1 and albumType=2  ALLOW FILTERING";
            //System.out.println(" sql is --- " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		PhotoAlbum info=new PhotoAlbum();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		info.setCoverScreenPath(rs.getString(2));
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setStrCreatedOn(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(3))));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" " + info.getIdPhotoAlbum() + " : " + info.getName() + " : " + info.getCoverScreenPath() + " : " +
        		//		info.getCreatedOn() + " : " + info.getUpdatedOn() + " : " + info.getUserId() + " : " + info.getPrivacy());
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static List<PhotoAlbumInfo> getAllAlbums(Session session,String empId){
		List<PhotoAlbumInfo> list=new ArrayList<PhotoAlbumInfo>();
    	try
        {
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where privacy=1 and indicator=1  ALLOW FILTERING";

	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	        for(Row rs:rss)
        	{
        		PhotoAlbumInfo info=new PhotoAlbumInfo();
        		info.setIdPhotoAlbum(rs.getString(0));
        		info.setName(rs.getString(1));
        		info.setCoverScreenPath(rs.getString(2));
        		if(rs.getString(2)!=null && !rs.getString(2).equalsIgnoreCase("") && !rs.getString(2).equalsIgnoreCase("null")){
        			info.setCoverScreenPath(rs.getString(2));
        			PhotoInfo ooo=PhotoInfoDAO.getPhotoByAlbumIdSize(info.getIdPhotoAlbum(), session, info.getCoverScreenPath());
        			if(ooo!=null){
        				info.setWidth(ooo.getWidth());
        				info.setHeight(ooo.getHeight());
        				info.setCoverScreenPath(ooo.getPhotoPath());
        			}
        				
        		}else{
        			//System.out.println(" cover path is null ");
        			List<PhotoInfo> infoss = PhotoInfoDAO.getPhotoByAlbumId(info.getIdPhotoAlbum(), session);
        			SortPhotos s=new SortPhotos("PhotoAlbum");
    				Collections.sort(infoss,s);
        			if(infoss.size()>0){
        				info.setCoverScreenPath(infoss.get(0).getPhotoPath());
        				info.setWidth(infoss.get(0).getWidth());
        				info.setHeight(infoss.get(0).getHeight());
        			}
        		}
        		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setTimeInStr(Util.getPhotoCreationTime(info.getUpdatedOn()));
        		info.setUserId(rs.getString(5));
        		info.setPrivacy(rs.getInt(6));
        		info.setDesc(rs.getString(7));
        		//System.out.println(" Map data " + map );
        		//System.out.println(" id is  " + info.getUserId());
        		EmpBasicInfo i = CacheRecords.getInstance().getCacheData( info.getUserId());
        		info.setUserName(i.getEmpName());
        		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(info.getIdPhotoAlbum(), session));
        		list.add(info);
        	}
        	
        	 inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  empInfoDB.photoAlbum where privacy=2 and id=? and indicator=1  ALLOW FILTERING";
        	 com.datastax.driver.core.PreparedStatement statement1 =  session.prepare(inssql);
 	    	BoundStatement boundStatement1 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement1);
 	    	ResultSet rss2=session.execute(boundStatement1.bind(empId));
 	        for(Row rs:rss2)
         	{
         		PhotoAlbumInfo info=new PhotoAlbumInfo();
         		info.setIdPhotoAlbum(rs.getString(0));
         		info.setName(rs.getString(1));
         		info.setCoverScreenPath(rs.getString(2));
         		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
         		info.setTimeInStr(Util.getPhotoCreationTime(info.getUpdatedOn()));
         		info.setUserId(rs.getString(5));
         		info.setPrivacy(rs.getInt(6));
         		//System.out.println(" Map data " + map );
         		//System.out.println(" id is  " + info.getUserId());
         		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( info.getUserId());
         		info.setUserName(i.getEmpName());
         		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(info.getIdPhotoAlbum(), session));
         		list.add(info);
         	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static List<PhotoAlbumInfo> getAllAlbums1(Session session){
		List<PhotoAlbumInfo> list=new ArrayList<PhotoAlbumInfo>();
    	try
        {
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	        for(Row rs:rss)
        	{
        		PhotoAlbumInfo info=new PhotoAlbumInfo();
         		info.setIdPhotoAlbum(rs.getString(0));
         		info.setName(rs.getString(1));
         		info.setCoverScreenPath(rs.getString(2));
         		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
         		info.setTimeInStr(Util.getPhotoCreationTime(info.getUpdatedOn()));
         		info.setUserId(rs.getString(5));
         		info.setPrivacy(rs.getInt(6));
         		//System.out.println(" Map data " + map );
         		System.out.println(" id is  " + info.getUserId());
         		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( info.getUserId());
         		System.out.println(" EmbPbasic info = " + i);
         		info.setUserName(i.getEmpName());
         		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(info.getIdPhotoAlbum(), session));
         		list.add(info);
        	}
        	
        
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static PhotoAlbumInfo getAlbumInfoById(String id,Session session){
		PhotoAlbumInfo list=null;
    	try
        {
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where key=? and privacy=1 and indicator=1  ALLOW FILTERING";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
	        	PhotoAlbumInfo info=new PhotoAlbumInfo();
         		info.setIdPhotoAlbum(rs.getString(0));
         		info.setName(rs.getString(1));
         		info.setCoverScreenPath(rs.getString(2));
         		info.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
         		info.setTimeInStr(Util.getPhotoCreationTime(info.getUpdatedOn()));
         		info.setUserId(rs.getString(5));
         		info.setPrivacy(rs.getInt(6));
         		//System.out.println(" Map data " + map );
         		//System.out.println(" id is  " + info.getUserId());
         		EmpBasicInfo i =CacheRecords.getInstance().getCacheData( info.getUserId());
         		info.setUserName(i.getEmpName());
         		info.setCount(PhotoInfoDAO.getPhotoCountByAlbumId(info.getIdPhotoAlbum(), session));
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    		list=null;
    	}
    	return list;
		
	}
	public static boolean updateCoverPage(String id,Session session,String coverPagePath){
		boolean result=false;
    	try
        {
    		
	        String inssql="update empInfoDB.photoAlbum set coverScreenPath= ? where key=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(coverPagePath,id));
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	public static void main(String args[]){
		
		// id 5899e7d9-43e6-4457-a20d-5c1968357f3b : name ssf privacy Public desc sdfsdffffffff
		updateOtherInfo("81b2d900-38be-491a-bb99-87617e47fe0d",CassandraDB.getCassConnection(),"ssf01","Public","sfffff001");
	}
	public static boolean updateOtherInfo(String id,Session session,String name,String privacy,String desc){
		boolean result=false;
		System.out.println(" id " + id + " : name " + name + " privacy " + privacy + " desc " + desc);
		int pv=2;
    	try
        {
    		if(privacy.trim().equalsIgnoreCase("public"))
    			pv=1;
    		
	        String inssql="update empInfoDB.photoAlbum set  name= ?,description=?,privacy=? where  KEY=?";
	        System.out.println(" inssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(
	    		
	    			name,
	    			desc,
	    			pv,
	    		
	    			id
	    			
	    			));
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}

	public static PhotoAlbum getAlbumById(String id,Session session){
		PhotoAlbum album=new PhotoAlbum();
    	try
        {
    		
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where indicator=1 and key=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		album.setIdPhotoAlbum(rs.getString(0));
        		album.setName(rs.getString(1));
        		album.setCoverScreenPath(rs.getString(2));
        		album.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		album.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		album.setPrivacy(rs.getInt(6));
        		album.setUserId(rs.getString(5));
        		album.setDesc(rs.getString(7));
        	}
            
        }
    	catch(Exception e){
    		album=null;
    		e.printStackTrace();
    		
    	}
    	return album;
		
	}
	
	public static PhotoAlbum getAlbumByIdForPostDisplay(String id,Session session){
		PhotoAlbum album=new PhotoAlbum();
    	try
        {
    		
    		
	        String inssql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,description from  empInfoDB.photoAlbum where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
	        	album.setIdPhotoAlbum(rs.getString(0));
        		album.setName(rs.getString(1));
        		album.setCoverScreenPath(rs.getString(2));
        		album.setCreatedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		album.setUpdatedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		album.setPrivacy(rs.getInt(6));
        		album.setUserId(rs.getString(5));
        		album.setDesc(rs.getString(7));
        	}
            
        }
    	catch(Exception e){
    		album=null;
    		e.printStackTrace();
    		
    	}
    	return album;
		
	}
	public static int deleteAlbumById(String id,Session session){
    	int result=0;
    	try
        {
    		
    		
	        String inssql="delete from  empInfoDB.photoAlbum where key=?";
	       // System.out.println(" inssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id));
        	result=1;
            
        }
    	catch(Exception e){
    		result=0;
    		e.printStackTrace();
    		
    	}
    	  //System.out.println(" inssql  res" + result);
    	return result;
		
	}
}
