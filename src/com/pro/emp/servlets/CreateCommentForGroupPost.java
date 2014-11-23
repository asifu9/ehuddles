package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PostGroupCommentsDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostGroupComments;
import com.pro.emp.domain.PostInfo;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateCommentForPost
 */
@WebServlet("/CreateCommentForGroupPost")
public class CreateCommentForGroupPost extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateCommentForGroupPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String postId="";
		String commentDesc="";
		String commentedById="";
		String resp="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("postId")!=null)
			postId=request.getParameter("postId");

		if(request.getParameter("commentDesc")!=null)
			commentDesc=request.getParameter("commentDesc");
		
		if(request.getParameter("commentedById")!=null)
			commentedById=request.getParameter("commentedById");
		
		String de =commentDesc.replaceAll("'", "''");
		HashMap<String,PostGroupComments> ob=new HashMap<String, PostGroupComments>();
		Connection con = CassandraDB.getCassConnection();
		try{
			PostGroupComments info=new PostGroupComments();
			info.setCommentDesc(de);
			info.setCommmentUserId(commentedById);
			info.setPostId(postId);
			
			PostGroupComments p=PostGroupCommentsDAO.createPostComments(info, con);
			
			// create notification
//			CommentNotification com=new CommentNotification();
//			com.setCommentId(p.getKey());
//			com.setPostedByUserId(PostDAO.getUserIdByPostId(con, postId));
//			com.setPostCommentedByUserId(p.getCommmentUserId());
//			com.setPostId(postId);
//			com.setFlow(Integer.parseInt(flow));
//			com.setStatus(1);
//			
//			System.out.println(" getPostedByUserId " + com.getPostedByUserId());
//			System.out.println(" commentedById " + commentedById);
//			HashMap<String, String> ss=new HashMap<String, String>();
//			if(!com.getPostedByUserId().equalsIgnoreCase(commentedById)){
//				CommentNotificationsDAO.createNotificationComment(com, con);
//				ss.put(com.getPostedByUserId(), "test");
//			}
			
	
    		
//			List<PostComments> comm = PostCommentsDAO.getCommentsForPostId(postId, con, map, map2);
//			for(PostComments c:comm){
//				System.out.println(" cc " + c.getCommentUserName() + " : " + c.getCommmentUserId());
//				if(!c.getCommmentUserId().equalsIgnoreCase(p.getCommmentUserId()))
//					ob.put(c.getCommmentUserId(), c);
//			}
//			
//			PostInfo postInfo = PostDAO.getPostDetailByPostId(con, postId);
//			Set<String> set = ob.keySet();
//			for(String s:set){
//				PostComments c=ob.get(s);
//				if(!ss.containsKey(c.getCommmentUserId())){
//				System.out.println(" C.getComment id " + c.getCommmentUserId());
//				CommentNotification ccd=new CommentNotification();
//				ccd.setCommentId(p.getKey());
//				ccd.setPostedByUserId(c.getCommmentUserId());
//				ccd.setPostCommentedByUserId(info.getCommmentUserId());
//				ccd.setPostId(postId);
//				ccd.setFlow(Integer.parseInt(flow));
//				ccd.setStatus(1);
//				String person="";
//				if(postInfo.getPostedById().equalsIgnoreCase(commentedById))
//					person= " his ";
//				else
//					person = postInfo.getPostedByUserInfo().getEmpName(); 
//				String des=commentDesc;
//				if(des.length()>20)
//					des=des.substring(0,20);
//				ccd.setMessage(map.get(commentedById) +" commented on " + person + " post : " + des );
//				
//				if(ccd.getPostCommentedByUserId().equalsIgnoreCase(commentedById)){
//					
//						CommentNotificationsDAO.createNotificationComment(ccd, con);
//					System.out.println(" created " + c.getCommmentUserId());
//				}
//				}
//			}
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, p);
			resp = strWriter.toString();
			//System.out.println(" test ----- " + resp);
			
		}catch(Exception e){
		e.printStackTrace();	
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(resp);
        writer.flush();
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String postId="";
		String commentDesc="";
		String commentedById="";
		String resp="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("postId")!=null)
			postId=request.getParameter("postId");

		if(request.getParameter("commentDesc")!=null)
			commentDesc=request.getParameter("commentDesc");
		
		if(request.getParameter("commentedById")!=null)
			commentedById=request.getParameter("commentedById");
		
		Connection con = CassandraDB.getCassConnection();
		try{
			PostComments info=new PostComments();
			info.setCommentDesc(commentDesc);
			info.setCommmentUserId(commentedById);
			info.setPostId(postId);
			
			PostComments p=PostCommentsDAO.createPostComments(info, con);
			
			
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

*/}
