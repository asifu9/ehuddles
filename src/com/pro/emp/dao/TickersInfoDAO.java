package com.pro.emp.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PhotoInfoDomain;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.TickerInfo;
import com.pro.emp.domain.TickerInfoDomain;

public class TickersInfoDAO extends EmpCommanDAO{

	/*public static void main(String args[]){
		TickerInfo info=new TickerInfo();
		info.setCommentDesc("ciomment");
		info.setCommentId(UUID.randomUUID());
		info.setKey(UUID.randomUUID());
		info.setLikeId(UUID.randomUUID());
		info.setPhotoId(UUID.randomUUID());
		info.setTargetUserId(UUID.randomUUID());
		info.setTimestamp(Util.convertDateToTimestamp(Calendar.getInstance().getTime()));
		info.setType("10");
		info.setUserId(UUID.randomUUID());
		System.out.println("like id " + info.getLikeId().toString());
		System.out.println(" setCommentId user id " + info.getCommentId().toString());
		System.out.println(" key " + info.getKey().toString());
		System.out.println(" photo id " + info.getPhotoId().toString());
		System.out.println(" setTargetUserId " + info.getTargetUserId().toString());
		System.out.println(" user Id " + info.getUserId());
		
		createoTickerInf(info,CassandraDB.getCassConnection());
		System.out.println(" Done ");
		
	//	079a2b9e-5d47-4fa7-8009-108df8cca475:69d5b363-684c-4c15-918b-8c4086b15c13:0f013118-0a7e-4791-b8b3-f8603ceca956:a9c6dcbb-1ecb-42b6-85c3-39c1513c51ea:5f609f2a-f0e1-4c03-8dd1-7691e640167a:3ee25498-e6a8-493e-bdd9-7c9dfce478d3:ciomment:10
	//	 Done 
		
	 Set<TickerInfoDomain> list=	getFirstTickers(CassandraDB.getCassConnection());
	 for(TickerInfoDomain t:list){
		 System.out.println(" " + t.getKey() + " : " + t.getDate());
	 }
	}*/
	
	public static TickerInfo createoTickerInf(TickerInfo tic,Session session){
		String result="";
		UUID id= java.util.UUID.randomUUID();
		try
	    {
			
			// String timeStamp = ""+ tic.getCommentDate();
			 //System.out.println(" t stamp " + timeStamp);
			Calendar currentDate=Calendar.getInstance();
			 SimpleDateFormat formatter= 
	        	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
	        	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
	        	  dt.setTime(currentDate.getTime().getTime());
	        	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
	        	  String ss=""+tm;
	        	  ss=ss.substring(0,19);
	        String inssql="INSERT INTO  empInfoDB.tickerinfo (" +
	        						"KEY,"+
	                                "userId," +
	                                "targetUserId,"+
	                                "photoId," +
	                                "commentId," +
	                                "likeId,"+
	                                "commentDesc," +
	                                "tickerType," +
	                                "indicator," +
	                                "tickertime" +
	                                ") values(?,?,?,?,?,?,?,?,?,?)";// +
	        System.out.println(" sql " + inssql);
	        System.out.println(id+":"+tic.getUserId()+":"+tic.getTargetUserId()+":"+tic.getPhotoId()+":"+tic.getCommentId()+":"+tic.getLikeId()+":"+tic.getCommentDesc()+":"+tic.getType());
			tic.setKey(id.toString());           
			tic.setTimeInStr( Util.getCommentTime(new Timestamp(Calendar.getInstance().getTimeInMillis())));
			com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(
	    			id.toString(),
	    			tic.getUserId(),
	    			tic.getTargetUserId(),
	    			tic.getPhotoId(),
	    			tic.getCommentId(),
	    			tic.getLikeId(),
	    			tic.getCommentDesc(),
	    			tic.getType(),
	    			1,
	    			Util.convertDateToTimestamp(Calendar.getInstance().getTime())));
	    	
//	    	boundStatement.setUUID(0, id);
//	    	boundStatement.setUUID(1,tic.getUserId());
//	    	boundStatement.setInt(2,1);
//	    	boundStatement.setDate(3, Calendar.getInstance().getTime());
//	    			
//	    			session.execute(boundStatement);
	       // rs=ps.getGeneratedKeys();
	       // session.commit();
			
			result=""+id;
			tic.setKey(result);
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	        System.out.println("Insertion Error");
	        se.printStackTrace();
	        tic=null;
	    }
		return tic;
	}
	
/*	public static void main(String args[]){
		getFirstTickers(CassandraDB.getCassConnection());
	}
	*/
	

	public static Set<TickerInfoDomain> getFirstTickers(Session session){
		Set<TickerInfoDomain> list=new HashSet<TickerInfoDomain>();
    	try
        {
    		
	        String inssql="select key,userId,targetUserId,photoId,likeId,commentId,tickerType,tickertime,commentDesc from  empInfoDB.tickerInfo  where indicator=? LIMIT 10";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(1));
	        for(Row rs:rss)
        	{
        		System.out.println(" ------------------  comment id = " + rs.getString(3) + " : " + rs.getString(4) + " : like id = " + rs.getString(6));
        		TickerInfoDomain info=new TickerInfoDomain();
        		info.setUserId(CacheRecords.getInstance().getCacheData( rs.getString(1)));
        		info.setDate(Util.convertDateToTimestamp(rs.getDate(7)));
        		info.setDateTime(Util.convertDateToTimestamp(rs.getDate(7)).toString());
        		info.setTime(Util.getCommentTime(new Timestamp(rs.getDate(7).getTime())));
        		info.setKey(rs.getString(0));
        		if(rs.getString(1)!=null){
        			info.setTargetUserId(CacheRecords.getInstance().getCacheData(rs.getString(2)));
        		}else{
        			info.setTargetUserId(null);
        		}
        		
        		
        		if(rs.getString(6).equalsIgnoreCase("like")){
        			
        			if(rs.getString(4)!=null){
            			info.setMessage(" likes");
            		}
        			
        			if(rs.getString(1)!=null){
        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
        					if(info.getUserId().getGender().equalsIgnoreCase("male")){
        						info.setMessage(info.getMessage() + " his own photo.");
        					}else{
        						info.setMessage(info.getMessage() + " her own photo.");
        					}
        				}else{
        					info.setMessage(info.getMessage()+ " " + info.getUserId().getEmpName() + " photo.");
        				}
        			}else{
        			info.setMessage( info.getMessage()+ " "+info.getTargetUserId().getEmpName()+" photo");
        			}
        		}else if(rs.getString(6).equalsIgnoreCase("comment")){
        			//c omment
        			PhotoComments comm =null;
            		
            		if(rs.getString(5)!=null){
            			info.setMessage("commented");
            			comm= PhotoCommentsDAO.getCommentsById(rs.getString(5), session);
            		}
            		if(rs.getString(3)!=null){
            			
            			
            			
    	        			if(rs.getString(1)!=null){
    	        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
    	        					if(comm!=null){
    	        						if(info.getUserId().getGender().equalsIgnoreCase("male")){
    	        							info.setMessage(info.getMessage() + " on his own photo. : " + comm.getCommentDesc());
    	        						}else{
    	        							info.setMessage(info.getMessage() + " on her own photo. : " + comm.getCommentDesc());
    	        						}
    	        						
    	        					}
    	        				}else{
    	        					if(comm!=null)
    	        						info.setMessage(info.getMessage() + " on "+info.getUserId().getEmpName() + " photo. : " + comm.getCommentDesc());
    	        				}
    	        			}else{
    	        				if(comm!=null)
    	        					info.setMessage( info.getMessage()+ " on "+info.getTargetUserId().getEmpName()+" photo. : "  + ""+comm.getCommentDesc());
    	        			}
            			
            		}
        		}
        		
        		
        		
        		
        		list.add(info);
        	}
	        
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	public static void main(String ar[]){
		System.out.println(getTickerInfoDomainById("037ef91b-e48c-45ae-acc3-d49137c4cc9a"));
	}
	public static TickerInfoDomain getTickerInfoDomainById(String key){
		TickerInfoDomain list=null;
    	try
        {
    		Session session= CassandraDB.getCassConnection();
	        String inssql="select key,userId,targetUserId,photoId,likeId,commentId,tickerType,tickertime,commentDesc from  empInfoDB.tickerInfo  where indicator=? and key=? LIMIT 10";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(1,key));
	        for(Row rs:rss)
        	{
        		System.out.println(" ------------------  comment id = " + rs.getString(3) + " : " + rs.getString(4) + " : like id = " + rs.getString(6));
        		TickerInfoDomain info=new TickerInfoDomain();
        		info.setUserId(CacheRecords.getInstance().getCacheData( rs.getString(1)));
        		info.setDate(Util.convertDateToTimestamp(rs.getDate(7)));
        		info.setDateTime(Util.convertDateToTimestamp(rs.getDate(7)).toString());
        		info.setTime(Util.getCommentTime(new Timestamp(rs.getDate(7).getTime())));
        		info.setKey(rs.getString(0));
        		if(rs.getString(1)!=null){
        			info.setTargetUserId(CacheRecords.getInstance().getCacheData(rs.getString(2)));
        		}else{
        			info.setTargetUserId(null);
        		}
        		
        		
        		if(rs.getString(6).equalsIgnoreCase("like")){
        			
        			if(rs.getString(4)!=null){
            			info.setMessage(" likes");
            		}
        			
        			if(rs.getString(1)!=null){
        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
        					if(info.getUserId().getGender().equalsIgnoreCase("male")){
        						info.setMessage(info.getMessage() + " his own photo.");
        					}else{
        						info.setMessage(info.getMessage() + " her own photo.");
        					}
        				}else{
        					info.setMessage(info.getMessage()+ " " + info.getUserId().getEmpName() + " photo.");
        				}
        			}else{
        			info.setMessage( info.getMessage()+ " "+info.getTargetUserId().getEmpName()+" photo");
        			}
        		}else if(rs.getString(6).equalsIgnoreCase("comment")){
        			//c omment
        			PhotoComments comm =null;
            		
            		if(rs.getString(5)!=null){
            			info.setMessage("commented");
            			comm= PhotoCommentsDAO.getCommentsById(rs.getString(5), session);
            		}
            		if(rs.getString(3)!=null){
            			
            			
            			
    	        			if(rs.getString(1)!=null){
    	        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
    	        					if(comm!=null){
    	        						if(info.getUserId().getGender().equalsIgnoreCase("male")){
    	        							info.setMessage(info.getMessage() + " on his own photo. : " + comm.getCommentDesc());
    	        						}else{
    	        							info.setMessage(info.getMessage() + " on her own photo. : " + comm.getCommentDesc());
    	        						}
    	        						
    	        					}
    	        				}else{
    	        					if(comm!=null)
    	        						info.setMessage(info.getMessage() + " on "+info.getUserId().getEmpName() + " photo. : " + comm.getCommentDesc());
    	        				}
    	        			}else{
    	        				if(comm!=null)
    	        					info.setMessage( info.getMessage()+ " on "+info.getTargetUserId().getEmpName()+" photo. : "  + ""+comm.getCommentDesc());
    	        			}
            			
            		}
        		}
        		
        		
        		
        		
        		list=info;
        	}
	        
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}
	/*public static void main(String args[]){
		
		getAllTickersByLatest(CassandraDB.getCassConnection(),Util.convertStringToDate("2014-05-24 15:11:20.753"));
	}*/
	public static List<TickerInfoDomain> getAllTickersByLatest(Session session,Date t){
		List<TickerInfoDomain> list=new ArrayList<TickerInfoDomain>();
    	try
        {
    	
    		
	        String inssql="select key,userId,targetUserId,photoId,likeId,commentId,tickerType,tickertime from   empInfoDB.tickerinfo   where indicator=1 and tickertime > ?  LIMIT 10";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(t));
	        for(Row rs:rss)
        	{
        		
        		TickerInfoDomain info=new TickerInfoDomain();
        		info.setUserId(CacheRecords.getInstance().getCacheData( rs.getString(1)));
        		info.setDate(Util.convertDateToTimestamp(rs.getDate(7)));
        		info.setDateTime(Util.convertDateToTimestamp(rs.getDate(7)).toString());
        		info.setTime(Util.getCommentTime(new Timestamp(rs.getDate(7).getTime())));
        		info.setKey(rs.getString(0));
        		if(rs.getString(1)!=null){
        			info.setTargetUserId(CacheRecords.getInstance().getCacheData(rs.getString(2)));
        		}else{
        			info.setTargetUserId(null);
        		}
        		
        		
        		if(rs.getString(6).equalsIgnoreCase("like")){
        			
        			if(rs.getString(4)!=null){
            			info.setMessage(" likes ");
            		}
        			
        			if(rs.getString(1)!=null){
        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
        					info.setMessage(info.getMessage() + " their own photo.");
        				}else{
        					info.setMessage(info.getMessage() + info.getUserId().getEmpName() + " photo.");
        				}
        			}else{
        			info.setMessage( info.getMessage()+ "  "+info.getTargetUserId().getEmpName()+" photo");
        			}
        		}else{
        			//c omment
        			PhotoComments comm =null;
            		
            		if(rs.getString(5)!=null && !rs.getString(5).equalsIgnoreCase("null")){
            			info.setMessage(" Commented ");
            			//System.out.println(" COmment id in ticker " + rs.getString(6));
            			comm= PhotoCommentsDAO.getCommentsById(rs.getString(5), session);
            		}
            		if(rs.getString(3)!=null){
            			
            			
            			
    	        			if(rs.getString(1)!=null){
    	        				if(info.getUserId().getId().equalsIgnoreCase(info.getTargetUserId().getId())){
    	        					info.setMessage(info.getMessage() + " their own photo. : " + comm.getCommentDesc());
    	        				}else{
    	        					info.setMessage(info.getMessage() + info.getUserId().getEmpName() + " photo. : " + comm.getCommentDesc());
    	        				}
    	        			}else{
    	        			info.setMessage( info.getMessage()+ " on "+info.getTargetUserId().getEmpName()+" photo. : "  + ""+comm.getCommentDesc());
    	        			}
            			
            		}
        		}
        		
        		
        		System.out.println(";got" + info.getMessage() );
        		
        		list.add(info);
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
		
	}

	public static TickerInfo getTickersById(Session session,String key){
		TickerInfo info=null;
    	try
        {
    		//2014-03-23 19:31:45.67
    	//	System.out.println(" date is " + date);
    		/**
    		 *  Mon Mar 31 15:48:59 IST 2014 275
 date Sun Mar 23 19:31:45 IST 2014
 date 1395583305067
 Sun Mar 23 19:31:45 IST 2014 67
 d = Sun Mar 23 19:31:45 IST 2014 key is ac43d701-01be-4eaf-b952-bde08d10c2ef
    		 */
    		//java.util.Date dd= Util.convertStringToDate("2014-03-31 15:48:59.275");
    		//eaeb966c-f873-448d-9c7d-d99d6ae589fc
    		//and tickertime=?
    		//System.out.println(" d = " + dd  + " key is " + key);
	        String inssql="select key,userId,targetUserId,photoId,likeId,commentId,tickerType,tickertime from   empInfoDB.tickerinfo   where indicator=1   and KEY=?";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key));
	        for(Row rs:rss)
        	{
        		
        		 info=new TickerInfo();
        		 System.out.println(" id = " + rs.getString(0) + " : " + rs.getDate(7));
        		info.setKey(rs.getString(0));
        		info.setUserId(rs.getString(1));
        		info.setTargetUserId(rs.getString(2));
        		info.setPhotoId(rs.getString(3));
        		info.setLikeId(rs.getString(4));
        		info.setCommentId(rs.getString(5));
        		info.setType(rs.getString(6));
        	}
            
        }
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return info;
		
	}
	public static int deleteById(String id,Session session){
		int result=0;
    	try
        {
    		
	        String inssql="delete from  postComments where key=? and indicator=1";
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id));
        	result=1;
        }
    	catch(Exception e){
    		result=2;
    		e.printStackTrace();
    	}
    	return result;
		
	}
	
	
	
	
	
}
