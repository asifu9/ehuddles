package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.MessageDetails;
import com.pro.emp.domain.MessageLinks;
import com.pro.emp.message.dao.MessageDetailsDAO;
import com.pro.emp.message.dao.MessageLinksDAO;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;

/**
 * Servlet implementation class CreateMessage
 */
@WebServlet("/CreateMessage")
public class CreateMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String resp="";
		 String idlist="";
		 String message="";
		 String fromId="";
		 List<MessageLinks> li = new ArrayList<MessageLinks>();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
		 if(request.getParameter("idlist")!=null){
			 idlist=request.getParameter("idlist");
		 }
		 Set<String>  users=new HashSet<String>();
		 if(request.getParameter("fromId")!=null){
			 fromId=request.getParameter("fromId");
		 }
		 if(request.getParameter("message")!=null){
			 message=request.getParameter("message");
		 }
		 Session con = CassandraDB.getCassConnection();
		 try {
			 MessageDetails det=new MessageDetails();
			 det.setMessage(message);
			 String messageId=MessageDetailsDAO.createMessageDetails(det, con);
			 // message created now get the message id
			 
			 String[] l= idlist.split("~~~");
			 for(String s:l){
				 if( s==null || s.trim().equalsIgnoreCase("")){
				 }else{
					 MessageLinks links=new MessageLinks();
					 links.setFromId( fromId);
					 links.setMailId( messageId);
					 links.setStatus(0);
					 links.setToId( s);
					 links=MessageLinksDAO.createMessageLinks(links, con);
					 links.setFromIdInfo(CacheRecords.getInstance().getCacheData( fromId));
					 links.setToIdInfo(CacheRecords.getInstance().getCacheData( s));
					 users.add(links.getToId());
					 li.add(links);
				 }
			 }
			 	DomainNotify d=new DomainNotify();
				d.setType("message");
				d.setJson("");
				NotificationGroupList.getInstance().sendNotificationToUsers(users, d);
			 
				ObjectMapper mapper = new ObjectMapper();
				Writer strWriter = new StringWriter();
				mapper.writeValue(strWriter, li);
				resp = strWriter.toString();
				System.out.println(" resp in comment ------- " + resp);
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
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
