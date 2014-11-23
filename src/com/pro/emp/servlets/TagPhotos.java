package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.Session_control;
import com.pro.emp.dao.TagDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Tag;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.CommentNotification;
import com.pro.post.domain.LikeNotification;

/**
 * Servlet implementation class TagPhotos
 */
@WebServlet("/TagPhotos")
public class TagPhotos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TagPhotos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp="";
		String userList="";
		String photoId="";

		if(request.getParameter("userList")!=null)
			userList=request.getParameter("userList");

		if(request.getParameter("photoId")!=null)
			photoId=request.getParameter("photoId");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		System.out.println(" user list " + userList);
		try{
			EmpInfo info = Session_control.getSession(request);
			List<Tag> tagList=new ArrayList<Tag>();
			String[] split = userList.split(",");
			for(String str:split){
				if(!str.trim().equals("")){
					Tag t=new Tag();
					t.setFollow(1);
					t.setOwnerId(info.getId());
					t.setPhotoId(photoId);
					t.setDesc("");
					t.setTaggedUserId(str.replace("selected_",""));
					TagDAO dao=new TagDAO();
					t=dao.createTag(t);
					t.setTaggerUserIdInfo(CacheRecords.getInstance().getCacheData(str.replace("selected_","")));
					tagList.add(t);
					
					LikeNotification like=new LikeNotification();
					like.setFlow(1);
					like.setMessage(info.getEmpName() + " has tagged a photo of you");
					like.setPostedByUserId(str.replace("selected_",""));
					like.setPostLikedByUserId(info.getId());
					like.setStatus(1);
					like.setLikeId("tag");
					like.setPostId(photoId);
					LikeNotificationsDAO.createNotificationLink(like, CassandraDB.getCassConnection());
				}
			}

			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, tagList);
			resp= strWriter.toString();
			System.out.println(" response tag data " + resp);
		}
		catch(Exception ex){

		}
		out.print(resp);
		out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
