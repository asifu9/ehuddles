package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;

public class PhotoInfoDAO extends EmpCommanDAO{


	public static String createPhotoInfo(PhotoInfo info,Session session){
		 UUID id= java.util.UUID.randomUUID();
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
		
	        String inssql="INSERT INTO  empInfoDB.photoinfo(" +
	        						"key,"+
	                                "idPhotoAlbum, "+
	                                "photoPath," +
	                                "description,createdOn,width,height,ownerId,indicator) values " +
	                                "(?,?,?,?,?,?,?,?,?)";
	                              //  "" +
	                              /*  "(\'"+id+"\',\'"+info.getIdPhotoAlbum()+"\',\'"+info.getPhotoPath()+"\',
	                               * \'"+info.getDescription()+"\',\'"+ss+"\',"+info.getWidth()+",
	                               * "+info.getHeight()+",\'"+info.getOwnerId()+"\',1,
	                               * \'"+info.getPhotoCreationDate()+"\',\'"+info.getPhotoCreationDate()+"\')";*/
	       // System.out.println(" sql is " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			info.getIdPhotoAlbum(),
	    			info.getPhotoPath(),
	    			info.getDescription(),
	    			Calendar.getInstance().getTime(),
	    			info.getWidth(),
	    			info.getHeight(),
	    			info.getOwnerId(),
	    			1
	    			));
	        
	        // rs=ps.getGeneratedKeys();
	       // con.commit();
			
			result=1;
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(con);
	        System.out.println("Insertion Error");
	        se.printStackTrace();
	        result=3;
	    }
		return ""+id;
	}
	
	public static List<PhotoInfo> getPhotoByAlbumId(String id,Session session){
		List<PhotoInfo> list=new ArrayList<PhotoInfo>();
    	try
        {
    		
	        String inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where idPhotoAlbum=? and indicator=1";
	       // System.out.println(" insssql " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        
        	for(Row rs:rss)
        	{
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setIdPhotoAlbum(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		info.setDescription(rs.getString(3));
        		//System.out.println(" rs.getTimestamp(8) 1  " + rs.getString(8));
        		//System.out.println(" rs.getTimestamp(8) 2  " + rs.getTimestamp(8));
        		if(rs.getDate(4)!=null)
        			info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		System.out.println(" creatd date is " + info.getCreaedOn() + " for id= " + rs.getString(0));
        		info.setLikeInfo(LikeTableDAO.getLikesForId(session, info.getIdPhotoInfo()));
        		info.setWidth(rs.getInt(5));
        		info.setHeight(rs.getInt(6));
        		info.setOwnerId(rs.getString(7));
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static PhotoInfo getPhotoByAlbumIdSize(String id,Session session,String path){
		PhotoInfo list=null;//new ArrayList<PhotoInfo>();
    	try
        {
    		
	        String inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height from   empInfoDB.photoinfo where idPhotoAlbum=? and indicator=1";

	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	    	
	        for(Row rs:rss)
        	{
        		if(rs.getString(2).equalsIgnoreCase(path)){
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setIdPhotoAlbum(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		info.setDescription(rs.getString(3));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setWidth(rs.getInt(5));
        		info.setHeight(rs.getInt(6));
        		list=info;
        		break;
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

//	public static List<PhotoInfo> getPhotoByAlbumId(String id,Connection con){
//		List<PhotoInfo> list=new ArrayList<PhotoInfo>();
//		PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	try
//        {
//    		
//	        String inssql="select key,idPhotoAlbum,photoPath from   empInfoDB.photoinfo where idPhotoAlbum=\'"+id+"\' and indicator=1";
//            ps=con.prepareStatement(inssql);
//            //ps.setInt(1,id);
//            rs = ps.executeQuery();
//        	while(rs.next())
//        	{
//        		PhotoInfo info=new PhotoInfo();
//        		info.setIdPhotoInfo(rs.getString(1));
//        		info.setIdPhotoAlbum(rs.getString(2));
//        		info.setPhotoPath(rs.getString(3));
//        		list.add(info);
//        	}
//            
//        }
//    	catch(Exception e){
//    		e.printStackTrace();
//    	}
//    	return list;
//		
//	}
	public static List<PhotoInfo> getPhotoByAlbumIdForPostDisplay(String id,Session session){
		List<PhotoInfo> list=new ArrayList<PhotoInfo>();
    	//System.out.println(" st "+t);
    	try
        {
    		
	        String inssql="select key,idPhotoAlbum,photoPath,createdOn,width,height,ownerId from   empInfoDB.photoinfo where idPhotoAlbum=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        System.out.println(" sqqql is ----- " + inssql + " : id = " + id);
	        for(Row rs:rss)
        	{
	        	System.out.println(" got result ");
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setIdPhotoAlbum(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(3)));
        		info.setWidth(rs.getInt(4));
        		info.setHeight(rs.getInt(5));
        		info.setOwnerId(rs.getString(6));
        		list.add(info);
        	}
            
        	//SortComments com = new SortComments("PhotoPostDisplay");
        	//Collections.sort(list,com);
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static PhotoInfo getPhotoById(String empid,String photoId,Session session){
		PhotoInfo list=null;
		boolean isEmpFlow=true;
		String inssql="";
    	try
        {
    		if(empid==null)
    			isEmpFlow=false;
    		if(isEmpFlow)
    			inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where ownerId=? AND indicator=1 and createdOn <=?";
    		else
    			inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where indicator=1 and createdOn <=?";
           // System.out.println(" sssssql " + inssql);
            //ps.setInt(1,id);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=null;
	    	if(isEmpFlow)
	    		rss=session.execute(boundStatement.bind(empid,Calendar.getInstance().getTime()));
	    	else
	    		rss=session.execute(boundStatement.bind(Calendar.getInstance().getTime()));
	        
	        for(Row rs:rss)
        	{
	        	System.out.println(" ---------------------- here we are looping ----------------------");
	        	if(rs.getString(0).equalsIgnoreCase(photoId)){
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setIdPhotoAlbum(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		info.setDescription(rs.getString(3));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setLikeInfo(LikeTableDAO.getLikesForId(session, info.getIdPhotoInfo()));
        		info.setWidth(rs.getInt(5));
        		info.setHeight(rs.getInt(6));
        		info.setOwnerId(rs.getString(7));
        		list=info;
        		break;
	        	}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static boolean deleteById(String id,Session session,Date createdOn){
		boolean result=false;
    	try
        {
    		
	        String inssql="delete from   empInfoDB.photoinfo where key=? and indicator=1 and createdOn=?";
	        
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id,createdOn));
	    	
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	
	
	public static boolean updateDescriptoin(String id,Session session,String description,Date createdOn){
		boolean result=false;
    	try
        {
    		System.out.println(" id : " + id);
	        String inssql="update  empInfoDB.photoinfo set description=? where indicator=1 and key=? and createdOn = ?";
            com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(description,id,createdOn));
        	result=true;
        }
    	catch(Exception e){
    		result=false;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	/*public static void main(String args[]){
		Date dd= Util.convertStringToDate("2014-03-22 20:18:00.03");
		getPhotoDescById("81840c78-a48e-437c-ad1b-29a1bdedfc94",CassandraDB.getCassConnection(),dd);
	}*/
	public static PhotoInfoDomain getPhotoDescById(String id,Session session){
		PhotoInfoDomain description=null;
    	try
        {
    		
	        String inssql="select description,createdOn,idPhotoAlbum,key,photoPath from   empInfoDB.photoinfo";// where  indicator=1 and createdOn=? and key=? ";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);//.bind(d,id));
            System.out.println(" here i am " + rss);
            String albumId="";
        	for(Row rs:rss)
        	{
        		System.out.println( "  got record ----------- " + rs.getString(3));
        		description=new PhotoInfoDomain();
        		description.setId(rs.getString(3));
        		System.out.println( " Util.convertDateToTimestamp(rs.getDate(1)) " + Util.convertDateToTimestamp(rs.getDate(1)));
        		description.setDate(Util.getPhotoTime(Util.convertDateToTimestamp(rs.getDate(1))));
        		description.setPath(rs.getString(4));
        		//System.out.println(" fulld date "+ Util.getPhotoTimeFull(rs.getTimestamp(2)));
        		description.setFullTime(Util.getPhotoTimeFull(Util.convertDateToTimestamp(rs.getDate(1))));
        		System.out.println("setFullTime  " + description.getFullTime());
        		description.setDescription((rs.getString(0)==null?"":rs.getString(0)));
        		albumId=rs.getString(2);
        		description.setPath(rs.getString(4));
        		
        	}
        	System.out.println(" ----------- DONE " + albumId);
        	 String ssl="select id from photoAlbum where key=?";
        	  com.datastax.driver.core.PreparedStatement statement2 =  session.prepare(ssl);
  	    	BoundStatement boundStatement2 = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement2);
  	    	ResultSet rss2=session.execute(boundStatement2.bind(albumId));
              
        	 for(Row rs:rss2){
        		 description.setOwner(rs.getString(0));
        	 }
        	 
            
        }
    	catch(Exception e){
    		System.out.println(" -------------- ERROR --------------------");
    		e.printStackTrace();
    	}
    	return description;
		
	}
	
	
	public static  int getPhotoCountByAlbumId(String id,Session session ){
    	int count=0;
    	try
        {
    		
	        String inssql="select count(*) from   empInfoDB.photoinfo where indicator=1 and idPhotoAlbum=?";
	        System.out.println(" here is the count man " + inssql + " jid " + id) ;
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
        	for(Row rs:rss)
        	{
        		count=(int) rs.getLong(0);
        	}
            
        }
    	catch(Exception e){
    		System.out.println(" Error in count of albumns ");
    		e.printStackTrace();
    	}
    	return count;
		
	}
	
	public static PhotoInfo getBasicPhotoInfoById(String id,Session session){
		PhotoInfo list=null;
    	try
        {
    		
	        String inssql="select key,ownerId,photoPath from   empInfoDB.photoinfo where indicator=1";
	        
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	    	
        	for(Row rs:rss)
        	{
        		if(rs.getString(0).equalsIgnoreCase(id) && rs.getString(1)!=null){
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setOwnerId(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		list=info;
        		
        		System.out.println(" ddd");
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static void main(String args[]){
		PhotoInfo in = getPhotoByIdDynamicName("1083187f-ee61-4ebd-affa-45d57687a2a4", CassandraDB.getCassConnection());
		System.out.println(" in " + in.getOwnerId());
	}
	
	public static PhotoInfo getPhotoByIdDynamicName(String id,Session  session){
		PhotoInfo list=null;
    	if(id==null )
    		return null;
    	try
        {
    		System.out.println(" ---- here is the photo id " + id);
	        String inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	    	
        	for(Row rs:rss)
        	{
        		PhotoInfo info=new PhotoInfo();
        		if(rs.getString(0).equalsIgnoreCase(id)){
	        		info.setIdPhotoInfo(rs.getString(0));
	        		try{
	        		info.setIdPhotoAlbum(rs.getString(1));
	        		info.setPhotoPath(rs.getString(2));
	        		info.setDescription(rs.getString(3));
	        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
	        		info.setLikeInfo(LikeTableDAO.getLikesForIdDynamic(session, info.getIdPhotoInfo()));
	        		info.setWidth(rs.getInt(5));
	        		info.setHeight(rs.getInt(6));
	        		info.setOwnerId(rs.getString(7));
	        		list=info;
	        		} catch(Exception ex){list=null; } 
        		}
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

	public static PhotoInfo getPhotoByIdDynamic(String id,String photoId,Session  session,boolean isFromEmp){
		PhotoInfo list=null;
    	if(id==null )
    		return null;
    	try
        {
    		System.out.println(" id " + id + " photo Id = " + photoId);
    		
	        String inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where indicator=1 and ownerId=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id
	    			//,
	    			//Calendar.getInstance().getTime()
	    			));
	    	
        	for(Row rs:rss)
        	{
        		if(rs.getString(0).equalsIgnoreCase(photoId)){
	        		PhotoInfo info=new PhotoInfo();
	        		info.setIdPhotoInfo(rs.getString(0));
	        		try{
	        		info.setIdPhotoAlbum(rs.getString(1));
	        		info.setPhotoPath(rs.getString(2));
	        		info.setDescription(rs.getString(3));
	        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
	        		if(isFromEmp==false)
	        			info.setLikeInfo(LikeTableDAO.getLikesForIdDynamic(session, info.getIdPhotoInfo()));
	        		info.setWidth(rs.getInt(5));
	        		info.setHeight(rs.getInt(6));
	        		info.setOwnerId(rs.getString(7));
	        		list=info;
	        		} catch(Exception ex){list=null; } 
	        	}
        		
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	
	public static PhotoInfo getPhotoByIdSimple(String key,Session session){
		PhotoInfo list=null;
    	try
        {
    		
	        String inssql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,ownerId from   empInfoDB.photoinfo where key=? and indicator=1";

	        
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key));
	    	//System.out.println(" sssssql " + inssql);
            //ps.setInt(1,id);
        	for(Row rs:rss)
        	{
        		PhotoInfo info=new PhotoInfo();
        		info.setIdPhotoInfo(rs.getString(0));
        		info.setIdPhotoAlbum(rs.getString(1));
        		info.setPhotoPath(rs.getString(2));
        		info.setDescription(rs.getString(3));
        		info.setCreaedOn(Util.convertDateToTimestamp(rs.getDate(4)));
        		info.setWidth(rs.getInt(5));
        		info.setHeight(rs.getInt(6));
        		info.setOwnerId(rs.getString(7));
        		list=info;
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
	}
}
