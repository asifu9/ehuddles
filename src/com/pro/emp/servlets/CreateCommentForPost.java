package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
import com.pro.emp.EmployeeInfo;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateCommentForPost
 */
@WebServlet("/CreateCommentForPost")
public class CreateCommentForPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCommentForPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String postId="";
		String commentDesc="";
		String commentedById="";
		String resp="";
		String flow="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("postId")!=null)
			postId=request.getParameter("postId");

		if(request.getParameter("commentDesc")!=null)
			commentDesc=request.getParameter("commentDesc");
		
		if(request.getParameter("commentedById")!=null)
			commentedById=request.getParameter("commentedById");
		
		
		if(request.getParameter("flow")!=null)
			flow=request.getParameter("flow");
		
		String de =commentDesc.replaceAll("'", "''");
		HashMap<String,PostComments> ob=new HashMap<String, PostComments>();
		Session con = CassandraDB.getCassConnection();
		try{
			PostComments info=new PostComments();
			info.setCommentDesc(de);
			info.setCommmentUserId( commentedById);
			info.setPostId( postId);
			
			PostComments p=PostCommentsDAO.createPostComments(info, con);
			
			DomainNotify dn=new DomainNotify();
			
			dn.setType("Comment");
			
			ObjectMapper mapper2 = new ObjectMapper();
			Writer strWriter2 = new StringWriter();
			mapper2.writeValue(strWriter2, p);
			String obj2 = strWriter2.toString();
			while(obj2.contains("\\\\\\\\")){
				int ss = obj2.indexOf("\\\\\\\\");
				StringBuffer b=new StringBuffer(obj2);
				b=b.replace(ss, ss+1, "");
				obj2=b.toString();
			}
			dn.setJson(obj2);
			System.out.println(" output is " + obj2);
			NotificationGroupList.getInstance().sendNotificationToAll(dn);
			// create notification
			CommentNotification com=new CommentNotification();
			com.setMessage(info.getCommentDesc());
			com.setCommentId(p.getKey());
			com.setPostedByUserId(PostDAO.getUserIdByPostId(con, postId));
			com.setPostCommentedByUserId(p.getCommmentUserId());
			com.setPostId( postId);
			com.setFlow(Integer.parseInt(flow));
			com.setStatus(1);
			com.setCommentIdDate(p.getCommentDate());
			HashMap<String, String> ss=new HashMap<String, String>();
			if(!com.getPostedByUserId().toString().equalsIgnoreCase(commentedById)){
				CommentNotificationsDAO.createNotificationComment(com, con);
				ss.put(com.getPostedByUserId(), "test");
			}
			
		
    		
			List<PostComments> comm = PostCommentsDAO.getCommentsForPostId( postId, con);
			for(PostComments c:comm){
				System.out.println(" cc " + c.getCommentUserName() + " : " + c.getCommmentUserId());
				if(!c.getCommmentUserId().toString().equalsIgnoreCase(p.getCommmentUserId().toString()))
					ob.put(c.getCommmentUserId(), c);
			}
			
			PostInfo postInfo = PostDAO.getPostDetailByPostId(con, postId);
			Set<String> set = ob.keySet();
			for(String s:set){
				PostComments c=ob.get(s);
				if(!ss.containsKey(c.getCommmentUserId())){
				CommentNotification ccd=new CommentNotification();
				ccd.setCommentId(p.getKey());
				ccd.setPostedByUserId(c.getCommmentUserId());
				ccd.setPostCommentedByUserId(info.getCommmentUserId());
				ccd.setPostId( postId);
				ccd.setFlow(Integer.parseInt(flow));
				ccd.setStatus(1);
				String person="";
				if(postInfo.getPostedById().toString().equalsIgnoreCase(commentedById))
					person= " his ";
				else
					person = postInfo.getPostedByUserInfo().getEmpName(); 
				String des=commentDesc;
				if(des.length()>20)
					des=des.substring(0,20);
				EmpBasicInfo e= CacheRecords.getInstance().getCacheData( ccd.getPostCommentedByUserId());
				ccd.setMessage(e.getEmpName() +" commented on " + person + " post : " + des );
				
				
				
				if(ccd.getPostCommentedByUserId().toString().equalsIgnoreCase(commentedById)){
					
						CommentNotificationsDAO.createNotificationComment(ccd, con);
					System.out.println(" created " + c.getCommmentUserId());
				}
				}
			}
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, p);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String postId="";
		String commentDesc="";
		String commentedById="";
		String resp="";
		String flow="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("postId")!=null)
			postId=request.getParameter("postId");

		if(request.getParameter("commentDesc")!=null)
			commentDesc=request.getParameter("commentDesc");
		
		if(request.getParameter("commentedById")!=null)
			commentedById=request.getParameter("commentedById");
		
		
		if(request.getParameter("flow")!=null)
			flow=request.getParameter("flow");
		
		String de =commentDesc.replaceAll("'", "''");
		HashMap<String,PostComments> ob=new HashMap<String, PostComments>();
		Session con = CassandraDB.getCassConnection();
		try{
			PostComments info=new PostComments();
			info.setCommentDesc(de);
			info.setCommmentUserId( commentedById);
			info.setPostId( postId);
			
			PostComments p=PostCommentsDAO.createPostComments(info, con);
			
			// create notification
			CommentNotification com=new CommentNotification();
			com.setCommentId(p.getKey());
			com.setPostedByUserId(PostDAO.getUserIdByPostId(con, postId));
			com.setPostCommentedByUserId(p.getCommmentUserId());
			com.setPostId( postId);
			com.setFlow(Integer.parseInt(flow));
			com.setStatus(1);
			com.setCommentIdDate(p.getCommentDate());
			System.out.println(" commentIddate " + com.getCommentIdDate());
			System.out.println(" getPostedByUserId " + com.getPostedByUserId());
			System.out.println(" commentedById " + commentedById);
			HashMap<String, String> ss=new HashMap<String, String>();
			if(!com.getPostedByUserId().toString().equalsIgnoreCase(commentedById)){
				CommentNotificationsDAO.createNotificationComment(com, con);
				ss.put(com.getPostedByUserId(), "test");
			}
			
		
    		
			List<PostComments> comm = PostCommentsDAO.getCommentsForPostId( postId, con);
			for(PostComments c:comm){
				System.out.println(" cc " + c.getCommentUserName() + " : " + c.getCommmentUserId());
				if(!c.getCommmentUserId().toString().equalsIgnoreCase(p.getCommmentUserId().toString()))
					ob.put(c.getCommmentUserId(), c);
			}
			
			PostInfo postInfo = PostDAO.getPostDetailByPostId(con, postId);
			Set<String> set = ob.keySet();
			for(String s:set){
				PostComments c=ob.get(s);
				if(!ss.containsKey(c.getCommmentUserId())){
				System.out.println(" C.getComment id " + c.getCommmentUserId());
				CommentNotification ccd=new CommentNotification();
				ccd.setCommentId(p.getKey());
				ccd.setPostedByUserId(c.getCommmentUserId());
				ccd.setPostCommentedByUserId(info.getCommmentUserId());
				ccd.setPostId( postId);
				ccd.setFlow(Integer.parseInt(flow));
				ccd.setStatus(1);
				String person="";
				if(postInfo.getPostedById().toString().equalsIgnoreCase(commentedById))
					person= " his ";
				else
					person = postInfo.getPostedByUserInfo().getEmpName(); 
				String des=commentDesc;
				if(des.length()>20)
					des=des.substring(0,20);
				EmpBasicInfo e= CacheRecords.getInstance().getCacheData( ccd.getPostCommentedByUserId());
				ccd.setMessage(e.getEmpName() +" commented on " + person + " post : " + des );
				
				
				
				if(ccd.getPostCommentedByUserId().toString().equalsIgnoreCase(commentedById)){
					
						CommentNotificationsDAO.createNotificationComment(ccd, con);
					System.out.println(" created " + c.getCommmentUserId());
				}
				}
			}
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, p);
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
