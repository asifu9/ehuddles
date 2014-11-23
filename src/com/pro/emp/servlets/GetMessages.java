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
@WebServlet("/GetMessages")
public class GetMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessages() {
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
		//System.out.println(" ttt comment" + ts);
		 
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Session con= CassandraDB.getCassConnection();
		java.util.List<MessageLinks> data=null;
		
		try {
				data=MessageLinksDAO.getMessageLinksForUserId(con,userId);
				
			//	SortComments sort=new SortComments("mailSort");
			//	Collections.sort(data,sort);
				
				
			
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, data);
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
