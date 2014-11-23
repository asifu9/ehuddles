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
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.emp.domain.MessageLinks;
import com.pro.emp.message.dao.MessageLinksDAO;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotificationJson;

/**
 * Servlet implementation class GetMessages
 */
@WebServlet("/GetSentMessages")
public class GetSentMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSentMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId="";
		
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		String resp="";
		
		Timestamp ts =null;
		System.out.println(" ttt comment" + ts);
		 
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Session con= CassandraDB.getCassConnection();
		java.util.List<MessageLinks> data=null;
		HashMap<String, MessageLinks> obb=new HashMap<String, MessageLinks>();
		
		try {
			
				
				data=MessageLinksDAO.getSentMessageLinksForUserId(con,userId);
				SortComments sort=new SortComments("mailSortSent");
				Collections.sort(data, sort);
			//	SortComments sort=new SortComments("mailSortSent");
			//	Collections.sort(data,sort);
				for(MessageLinks s:data){
					if(!obb.containsKey(s))
						obb.put(s.getToId(),s);
				}
				
			Set<String> set= obb.keySet();
			data=new ArrayList<MessageLinks>();
			for(String ss:set)
				data.add(obb.get(ss));
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, data);
			resp = strWriter.toString();
			System.out.println(" resp in send item" + resp);
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
