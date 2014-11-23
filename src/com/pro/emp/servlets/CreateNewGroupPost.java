package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.CassandraDB;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PostGroupDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.GroupDiscussionPost;
import com.pro.emp.domain.PostInfo;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateNewPost
 */
@WebServlet("/CreateNewGroupPost")
public class CreateNewGroupPost extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateNewGroupPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postedById="";
		String des="";
		String resp="";
		String photoId="";
		String groupId="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("photoId")!=null){
			photoId= request.getParameter("photoId");
		}
		
		if(request.getParameter("groupId")!=null){
			groupId=request.getParameter("groupId");
		}
		
		if(request.getParameter("postedById")!=null){
			postedById= request.getParameter("postedById");
		}
		if(request.getParameter("desc")!=null){
			des= request.getParameter("desc");
		}
		
		Connection con = CassandraDB.getCassConnection();
		try{
			GroupDiscussionPost info=new GroupDiscussionPost();
			info.setPostedById(postedById);
			info.setGroupId(groupId);
			info.setDesc(des.replace("\n", "<br/>"));
			info.setPostedPhotoId(photoId);
			PostGroupDAO ob=new PostGroupDAO();
			info=ob.createGroupPost(info, con);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, info);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
			
//			CommentNotification c=new CommentNotification();
//			c.setCommentId(info.getKey());
//			c.setPostCommentedByUserId(userId);
//			c.setPostedByUserId(targetId);
//			
//			String ddd= info.getPostedDesc();
//			if(ddd.length()>20){
//				ddd = ddd.substring(0,20);
//				ddd=ddd+"...";
//				
//			}
//			System.out.println(" flow is " + flow);
//			EmpBasicInfo in = EmpInfoDAO.getBasicEmpById_(con,info.getPostedById());
//			c.setMessage(in.getEmpName()+ " written on your wall :" + ddd);
//			c.setPostId(info.getKey());
//			c.setFlow(1);
//			if(flow!=null && Integer.parseInt(flow)==0)
//				CommentNotificationsDAO.createNotificationComment(c, con);
			
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
		doGet(request,response);
	}

*/}
