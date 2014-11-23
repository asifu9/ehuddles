package com.pro.emp;

import java.util.Calendar;
import java.util.Comparator;

import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.ForumDiscussion;
import com.pro.emp.domain.ForumNotification;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.MessageLinks;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostGroupComments;
import com.pro.emp.domain.PublicChat;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotificationJson;

public class SortComments implements Comparator<Object>{

	private String type="";
	
	public SortComments(String typ){
		this.type=typ;
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {
		int result=0;
		if(type.equalsIgnoreCase("PostGroupComment")){
			PostGroupComments p = (PostGroupComments)arg0;
			PostGroupComments p2= (PostGroupComments)arg1;
			result=p.getCommentDate().compareTo(p2.getCommentDate());
		
		
	}else if(type.equalsIgnoreCase("SortThreadForum")){
		ForumDiscussion p = (ForumDiscussion)arg0;
		ForumDiscussion p2= (ForumDiscussion)arg1;
		
		
		Calendar c1= Calendar.getInstance();
		c1.setTimeInMillis(p.getTime().getTime());
		
		Calendar c2= Calendar.getInstance();
		c2.setTimeInMillis(p2.getTime().getTime());
		
		result=c1.compareTo(c2);
	}else if(type.equalsIgnoreCase("PostComments")){
		PostComments p = (PostComments)arg0;
		PostComments p2= (PostComments)arg1;
		
		
		Calendar c1= Calendar.getInstance();
		c1.setTimeInMillis(p.getCommentDate().getTime());
		
		Calendar c2= Calendar.getInstance();
		c2.setTimeInMillis(p2.getCommentDate().getTime());
		
		result=c1.compareTo(c2);
	}
		
		
		else if(type.equalsIgnoreCase("GroupSort")){
		Group p = (Group)arg0;
		Group p2= (Group)arg1;
		
		
		Calendar c1= Calendar.getInstance();
		c1.setTimeInMillis(p.getCreatedOn().getTime());
		
		Calendar c2= Calendar.getInstance();
		c2.setTimeInMillis(p2.getCreatedOn().getTime());
		
		result=c2.compareTo(c1);
	}else if(type.equalsIgnoreCase("chatSort")){
			PublicChat p= (PublicChat)arg0;
			PublicChat p2= (PublicChat)arg1;
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(p.getPostedTime().getTime());
			Calendar c2=Calendar.getInstance();
			c2.setTimeInMillis(p2.getPostedTime().getTime());
			
			result=c2.compareTo(c);
		}else if(type.equalsIgnoreCase("chatSort")){
			PublicChat p= (PublicChat)arg0;
			PublicChat p2= (PublicChat)arg1;
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(p.getPostedTime().getTime());
			Calendar c2=Calendar.getInstance();
			c2.setTimeInMillis(p2.getPostedTime().getTime());
			
			result=c2.compareTo(c);
		}else if(type.equalsIgnoreCase("PhotoComments")){
			PhotoComments p = (PhotoComments)arg0;
			PhotoComments p2= (PhotoComments)arg1;
			result=p2.getCommentDate().compareTo(p.getCommentDate());
		}else if(type.equalsIgnoreCase("date")){
			EmpInfo p = (EmpInfo)arg0;
			EmpInfo p2= (EmpInfo)arg1;
			Calendar c= Calendar.getInstance();
			c.setTimeInMillis(p.getDob().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getDob().getTime());
			if(c.get(Calendar.MONTH)<c2.get(Calendar.MONTH)){
				
				result=0;
			}else result=1;
			
		}else if(type.equalsIgnoreCase("dateBirth")){
			EmpInfo p = (EmpInfo)arg0;
			EmpInfo p2= (EmpInfo)arg1;
			//System.out.println(" p " + p + " : " + p2);
			result=p.getDobForSort().compareTo(p2.getDobForSort());
		}else if(type.equalsIgnoreCase("ticker")){
			
			TickerInfoDomain p = (TickerInfoDomain)arg0;
			TickerInfoDomain p2= (TickerInfoDomain)arg1;
			result=p2.getDate().compareTo(p.getDate());
		}else if(type.equalsIgnoreCase("likeTime")){
			LikeNotificationJson p = (LikeNotificationJson)arg0;
			LikeNotificationJson p2= (LikeNotificationJson)arg1;
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(p.getLikedTime().getTime());
			Calendar c2=Calendar.getInstance();
			c2.setTimeInMillis(p2.getLikedTime().getTime());
			
			result=c.compareTo(c2);
		}else if(type.equalsIgnoreCase("likeTime2")){
			LikeNotificationJson p = (LikeNotificationJson)arg0;
			LikeNotificationJson p2= (LikeNotificationJson)arg1;
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(p.getLikedTime().getTime());
			Calendar c2=Calendar.getInstance();
			c2.setTimeInMillis(p2.getLikedTime().getTime());
			
			result=c2.compareTo(c);
		}else if(type.equalsIgnoreCase("PhotoPostDisplay")){
			PhotoInfo p = (PhotoInfo)arg0;
			PhotoInfo p2= (PhotoInfo)arg1;
			Calendar c=Calendar.getInstance();
			c.setTimeInMillis(p.getCreaedOn().getTime());
			Calendar c2=Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreaedOn().getTime());
			
			result=c2.compareTo(c);
			
		}else if(type.equalsIgnoreCase("commentTime")){
			CommentNotificationJson p = (CommentNotificationJson)arg0;
			CommentNotificationJson p2= (CommentNotificationJson)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCommentTime().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCommentTime().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("commentTime2")){
			CommentNotificationJson p = (CommentNotificationJson)arg0;
			CommentNotificationJson p2= (CommentNotificationJson)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCommentTime().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCommentTime().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("mailSort")){
			MessageLinks p = (MessageLinks)arg0;
			MessageLinks p2= (MessageLinks)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getMailTime().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getMailTime().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("mailSortSent")){
			MessageLinks p = (MessageLinks)arg0;
			MessageLinks p2= (MessageLinks)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getMailTime().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getMailTime().getTime());
			
			result=c1.compareTo(c2);
		}else if(type.equalsIgnoreCase("PhotoAlbumInfo")){
			PhotoAlbumInfo p = (PhotoAlbumInfo)arg0;
			PhotoAlbumInfo p2= (PhotoAlbumInfo)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCreatedOn().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreatedOn().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("PhotoDisplaySort")){
			PhotoAlbumInfo p = (PhotoAlbumInfo)arg0;
			PhotoAlbumInfo p2= (PhotoAlbumInfo)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCreatedOn().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreatedOn().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("PhotoAlbumDisplaySort")){
			PhotoAlbumInfo p = (PhotoAlbumInfo)arg0;
			PhotoAlbumInfo p2= (PhotoAlbumInfo)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCreatedOn().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreatedOn().getTime());
			
			result=c2.compareTo(c1);
		}else if(type.equalsIgnoreCase("PhotoAlbumDisplaySort1")){
			PhotoAlbum p = (PhotoAlbum)arg0;
			PhotoAlbum p2= (PhotoAlbum)arg1;
			
			
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCreatedOn().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreatedOn().getTime());
			
			result=c2.compareTo(c1);
		}
		
		
		
		return result;
	}

}
