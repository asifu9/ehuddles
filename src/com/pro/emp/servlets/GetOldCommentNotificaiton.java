package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotification;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class GetOldCommentNotificaiton
 */
@WebServlet("/GetOldCommentNotificaiton")
public class GetOldCommentNotificaiton extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOldCommentNotificaiton() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String time="";
		String userId="";
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
	
		String resp="";
		//System.out.println(" time is comment" + time);
		Timestamp ts =null;
		if(time!=null && !time.trim().equals("") && !time.equals("null")){
		ts=Timestamp.valueOf(time);	
		}
		//System.out.println(" ttt comment" + ts);
		 
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Session con= CassandraDB.getCassConnection();
		List<CommentNotificationJson> data=null;
		List<CommentNotificationJson> dataTotal=new ArrayList<CommentNotificationJson>();
		try {
			int i=0;
		
				data=CommentNotificationsDAO.getOldCommentNotifications(con, userId,ts);
				
			//	SortComments sort=new SortComments("commentTime2");
			//	Collections.sort(data,sort);
				boolean result=false;
				for(CommentNotificationJson jss:data){
					result=true;
					dataTotal.add(jss);
					
					//if(flag==true){
					//	flag=false;
						ts=jss.getCommentTime();
					//}
						
				}
				//System.out.println(" result is "+result + " time : " +ts);
			
			
			
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, dataTotal);
			resp = strWriter.toString();
			//System.out.println(" resp in comment " + resp);
		}
		catch(Exception ex){
			
		}
		finally{
			CassandraDB.freeConnection(con);
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
