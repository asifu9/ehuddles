package com.pro.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.Session_control;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Forum;
import com.pro.emp.domain.ForumDiscussion;
import com.pro.emp.domain.ForumNotification;
import com.pro.forum.dao.ForumDAO;
import com.pro.forum.dao.ForumDiscussionDAO;
import com.pro.forum.dao.ForumNotificationDAO;

/**
 * Servlet implementation class ForumReply
 */
@WebServlet("/ForumReply")
public class ForumReply extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public ForumReply() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId="";
		String forumId="";
		String desc="";
		String result="";
		String id="";
		EmpInfo emp = Session_control.getSession(request);
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("userId")!=null)
			userId= request.getParameter("userId");
		
		if(request.getParameter("forumId")!=null)
			forumId= request.getParameter("forumId");
		
		if(request.getParameter("desc")!=null)
			desc= request.getParameter("desc");
		
		ForumDiscussion dis=new ForumDiscussion();
		dis.setBody(desc);
		dis.setForumId(forumId);
		dis.setReplierId(userId);
		Connection con = CassandraDB.getCassConnection();
		try {
			id=ForumDiscussionDAO.createNewDiscussion(dis, con);
			Forum owner = ForumDAO.getForumByForumId(con, forumId);
			// get all users for this forum
			Set<String> users=ForumDAO.getAllUsersForForumId(con, forumId, owner.getOwnerId());
			users.remove(userId);
			System.out.println( "users to crate notificaiton " + users);
			for(String s:users)
			{
					ForumNotification n=new ForumNotification();
					n.setForumId(forumId);
					n.setNotifyingUserId(s);
					n.setPostedByUserId(owner.getOwnerId());
					n.setStatus(1);
					ForumNotificationDAO.createNewForum(n, con);
			}
			result="{\"result\":\""+id+"\"}";
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
		writer.println(result);
        writer.flush();

	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

*/}
