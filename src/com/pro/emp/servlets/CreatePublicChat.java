package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PublicChatDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.PublicChat;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class CreateCommentForPost
 */
@WebServlet("/CreatePublicChat")
public class CreatePublicChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePublicChat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId="";
		String commentDesc="";
		
		String resp="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("userId")!=null)
			userId=request.getParameter("userId");

		if(request.getParameter("commentDesc")!=null)
			commentDesc=request.getParameter("commentDesc");
		
	
		String de =commentDesc.replaceAll("'", "''");
		Session con = CassandraDB.getCassConnection();
		try{
			PublicChat chat=new PublicChat();
			chat.setPostedFromId(userId);
			chat.setPostedInfo(de);
			PublicChatDAO dao=new PublicChatDAO();
			chat =dao.createChat(chat, con);
			Calendar cal= Calendar.getInstance();
			cal.setTimeInMillis(chat.getPostedTime().getTime());
			Date t =  cal.getTime();
			PublicChat cc= dao.getChatById(chat.getKey(),chat.getPostedFromId(),t);
			System.out.println(" created suuccessfully chat");
			
			DomainNotify d=new DomainNotify();
			
			d.setType("Chat");
			ObjectMapper mapper2 = new ObjectMapper();
			Writer strWriter2 = new StringWriter();
			mapper2.writeValue(strWriter2, cc);
			String obj2 = strWriter2.toString();
			while(obj2.contains("\\\\\\\\")){
				int ss = obj2.indexOf("\\\\\\\\");
				StringBuffer b=new StringBuffer(obj2);
				b=b.replace(ss, ss+1, "");
				obj2=b.toString();
			}
			d.setJson(obj2);
			NotificationGroupList.getInstance().sendNotificationToAll(d);
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, chat);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
