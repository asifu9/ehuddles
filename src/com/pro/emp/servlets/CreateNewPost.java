package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateNewPost
 */
@WebServlet("/CreateNewPost")
public class CreateNewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId="";
		String postDesc="";
		String resp="";
		String photoId="";
		String targetId="";
		String privacy="";
		String flow="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("photoId")!=null){
			photoId= request.getParameter("photoId");
		}
		
		if(request.getParameter("flow")!=null){
			flow=request.getParameter("flow");
		}
		
		if(request.getParameter("userId")!=null){
			userId= request.getParameter("userId");
		}
		if(request.getParameter("postDesc")!=null){
			postDesc= request.getParameter("postDesc");
		}
		if(request.getParameter("targetId")!=null){
			targetId= request.getParameter("targetId");
		}
		if(request.getParameter("privacy")!=null){
			privacy= request.getParameter("privacy");
		}
		System.out.println(" photoId " + photoId);
		System.out.println(" flow " + flow);
		System.out.println(" userId " + userId);
		System.out.println(" postDesc " + postDesc);
		System.out.println(" targetId " + targetId);
		System.out.println(" privacy " + privacy);
		Session con = CassandraDB.getCassConnection();
		try{
			PostInfo info=new PostInfo();
			info.setPostedById( userId);
			if(!(targetId==null || targetId.trim().equals(""))){
				info.setPostedToId( targetId);
			}
			info.setPostedDesc(postDesc.replace("\n", "<br/>"));
			if(!(photoId==null || photoId.trim().equals(""))){
				info.setPostedPhotoId( photoId);
			}
			int sss=0;
			try{
				sss=Integer.parseInt(flow);
			}catch(Exception e){}
			System.out.println("  sssss ------_________________ " + sss);
			info.setPostType(sss);
			info.setPrivatestatus(Integer.parseInt(privacy));
			info=PostDAO.createPost(info, con);
			info = PostDAO.getPostDetailByPostId(CassandraDB.getCassConnection(), info.getKey());//(info.getKey());
			// notify code
			DomainNotify d=new DomainNotify();
			d.setType("post");
			ObjectMapper mapper2 = new ObjectMapper();
			Writer strWriter2 = new StringWriter();
			mapper2.writeValue(strWriter2, info);
			String obj2 = strWriter2.toString();
			while(obj2.contains("\\\\\\\\")){
				int ss = obj2.indexOf("\\\\\\\\");
				StringBuffer b=new StringBuffer(obj2);
				b=b.replace(ss, ss+1, "");
				obj2=b.toString();
			}
			d.setJson(obj2);
			NotificationGroupList.getInstance().sendNotificationToAll(d);
			//end of notify code
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, info);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
			
			CommentNotification c=new CommentNotification();
			c.setCommentId(info.getKey());
			if(!(userId==null || userId.trim().equals(""))){
				c.setPostCommentedByUserId( userId);
			}
			if(!(targetId==null || targetId.trim().equals(""))){
				c.setPostedByUserId( targetId);
			}
			
			String ddd= info.getPostedDesc();
			if(ddd.length()>20){
				ddd = ddd.substring(0,20);
				ddd=ddd+"...";
				
			}
			System.out.println(" flow is " + flow);
			EmpBasicInfo in =CacheRecords.getInstance().getCacheData(info.getPostedById());
			c.setMessage(in.getEmpName()+ " written on your wall :" + ddd);
			c.setPostId(info.getKey());
			c.setFlow(1);
			if(flow!=null && Integer.parseInt(flow)==0){
				c.setCommentIdDate(info.getPostedTime());
				CommentNotificationsDAO.createNotificationComment(c, con);
				
			}
			FollowDAO ff=new FollowDAO();
			List<Follow> f=ff.getAllFollowers(userId);
			if(f!=null){
				for(Follow followers:f){
					if(flow.equals("0"))
						c.setMessage(in.getEmpName()+ " written on their private wall :" + ddd);
					else
						c.setMessage(in.getEmpName()+ " written on public wall :" + ddd);
					c.setPostedByUserId( followers.getFollowBy());
					c.setCommentIdDate(info.getPostedTime());
					CommentNotificationsDAO.createNotificationComment(c, con);
				}
			}
			
		}catch(Exception e){
		e.printStackTrace();	
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(resp);
        writer.flush();
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId="";
		String postDesc="";
		String resp="";
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("userId")!=null){
			userId= request.getParameter("userId");
		}
		if(request.getParameter("postDesc")!=null){
			postDesc= request.getParameter("postDesc");
		}
		Session con = CassandraDB.getCassConnection();
		try{
			PostInfo info=new PostInfo();
			info.setPostedById( userId);
			info.setPostedDesc(postDesc);
			
			info=PostDAO.createPost(info, con);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, info);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
			
		}catch(Exception e){
		e.printStackTrace();	
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(resp);
        writer.flush();
	}

}
