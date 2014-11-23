package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PostGroupLikeDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostGroupLikeTable;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.PostLikeTable;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.LikeNotification;

/**
 * Servlet implementation class CreateLikeForPost
 */
@WebServlet("/CreateLikeForGroupPost")
public class CreateLikeForGroupPost extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateLikeForGroupPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId="";
		String postId="";
		String resp="";
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("userId")!=null)
			userId=request.getParameter("userId");
		
		if(request.getParameter("postId")!=null)
			postId=request.getParameter("postId");

		Connection con= CassandraDB.getCassConnection();
		try {
			PostGroupLikeTable pl=new PostGroupLikeTable();
			pl.setLikedUserId(userId);
			pl.setPostId(postId);
			
			// check whether if already exist
			boolean found=PostGroupLikeDAO.getLikeFOrPostandUser(con, postId, userId);
			
			if(found==false){
				PostGroupLikeTable pltable= PostGroupLikeDAO.createPostLike(pl, con);
//			LikeNotification not=new LikeNotification();
//			not.setLikeId(pltable.getKey());
//			not.setPostedByUserId(PostDAO.getUserIdByPostId(con, postId));
//			not.setPostLikedByUserId(pl.getLikedUserId());
//			not.setLikeId(pltable.getKey());
//			not.setStatus(0);
//			not.setFlow(Integer.parseInt(flow));
//			not.setPostId(postId);
//			not.setMessage("");
//			HashMap<String, String> ss=new HashMap<String, String>();
//			ss.put(not.getPostedByUserId(), "");
//			System.out.println("not.getPostedByUserId() " + not.getPostedByUserId());
//			System.out.println(" userId "+userId);
//			if(!not.getPostedByUserId().equalsIgnoreCase(userId))
//				LikeNotificationsDAO.createNotificationLink(not, con);
//			

//    		PostInfo p = PostDAO.getPostDetailByPostId(con, postId);
			List<PostGroupLikeTable > list = PostGroupLikeDAO.getLikesForPostId(con, postId);
//			for(PostLikeTable t:list){
//				if(!t.getLikedUserId().equalsIgnoreCase(not.getPostedByUserId())){
//					if(!ss.containsKey(t.getLikedUserId()) && !userId.equalsIgnoreCase(t.getLikedUserId())){
//					LikeNotification a=new LikeNotification();
//					a.setLikeId(t.getKey());
//					a.setPostedByUserId(t.getLikedUserId());
//					a.setPostLikedByUserId(userId);
//					a.setLikeId(t.getKey());
//					a.setStatus(0);
//					a.setFlow(Integer.parseInt(flow));
//					a.setPostId(postId);
//					String tt="";
//					if(p.getPostedPhotoId()!=null)
//						tt="Photo";
//					a.setMessage( map.get(userId) + " Likes " + p.getPostedByUserInfo().getEmpName() + "  wall post "+tt);
//					LikeNotificationsDAO.createNotificationLink(a, con);
//					}
//				}
//			}
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, list);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
			}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
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
		// TODO Auto-generated method stub
	}

*/}
