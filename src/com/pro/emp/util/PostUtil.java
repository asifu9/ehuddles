package com.pro.emp.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.TickerInfoDomain;

public class PostUtil {
public static int count=0;
	public static List<PostInfo> getAllPosts(Date t,String flow){
		List<PostInfo> posts= new ArrayList<PostInfo>();
		List<PostInfo> postsTotal= new ArrayList<PostInfo>();
		Session con =	CassandraDB.getCassConnection();
		System.out.println(" t is " +t);
		try {
				posts= PostDAO.getPosts(con,t,flow);
				count+=1;
				for(PostInfo p:posts){
					postsTotal.add(p);
					System.out.println(" p id = " + p.getKey()   + "  time is " + p.getPostedTime());
				}
//				System.out.println(" size is " + postsTotal.size() + "  count " + count);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		return postsTotal;
	}
	
	/*public static HashMap<Timestamp,List<GroupDiscussionPost>> getAllGroupPosts(Timestamp t,String groupId){
		List<GroupDiscussionPost> posts= new ArrayList<GroupDiscussionPost>();
		List<GroupDiscussionPost> postsTotal= new ArrayList<GroupDiscussionPost>();
		Session con =	CassandraDB.getCassConnection();
		System.out.println(" t is " +t);
		try {
			Calendar end= Calendar.getInstance();
			end.setTimeInMillis(t.getTime());
				end.add((Calendar.HOUR_OF_DAY), -40);
				Timestamp t2=new Timestamp(end.getTime().getTime());
				posts=PostGroupDAO.getGroupPosts(con, t, t2, groupId);
				for(GroupDiscussionPost p:posts){
					postsTotal.add(p);
				}
				
			
			
			SortPosts s=new SortPosts("Postsss");
			Collections.sort(postsTotal,s);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		HashMap<Timestamp,List<GroupDiscussionPost>> obb=new HashMap<Timestamp, List<GroupDiscussionPost>>();
		obb.put(t,postsTotal);
		return obb;
	}*/
	
	public static List<TickerInfoDomain> getAllTickers(){
		Set<TickerInfoDomain> postsTotal= new HashSet<TickerInfoDomain>();
		Session con =	CassandraDB.getCassConnection();
		List<TickerInfoDomain> list =new ArrayList<TickerInfoDomain>();
		try {
			postsTotal= TickersInfoDAO.getFirstTickers(con);
			for(TickerInfoDomain ii:postsTotal){
				list.add(ii);
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		return list;
	}
	
	public static void main(String rag[]){
		getAllPostsById("abf5faab-ac39-406b-9ddf-3baa8e78873e",Calendar.getInstance().getTime());
	}
	public static List<PostInfo>  getAllPostsById(String id,Date t){
		List<PostInfo> posts= new ArrayList<PostInfo>();
		List<PostInfo> postsTotal= new ArrayList<PostInfo>();
	//	com.datastax.driver.core.Session con =	CassandraDB.getCassConnection();
		try {
			Calendar end= Calendar.getInstance();
			end.setTimeInMillis(t.getTime());
			posts= PostDAO.getPostsById(id,end.getTime());
			for(PostInfo p:posts){
				postsTotal.add(p);
				System.out.println(" go red");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	
		return postsTotal;
	}
	
	public static List<PostInfo> getAllPostsJSON(){
		List<PostInfo> posts= new ArrayList<PostInfo>();
		com.datastax.driver.core.Session con =	CassandraDB.getCassConnection();
		try {
			posts= PostDAO.getPostsJSON(con);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		return posts;
	}
}
