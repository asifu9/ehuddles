package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
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
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class GetLikeNotificationJson
 */
@WebServlet("/GetLikeNotificationJson")
public class GetLikeNotificationJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLikeNotificationJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String id="";
		if(request.getParameter("empId")!=null)
			id = request.getParameter("empId");
		
		List<LikeNotificationJson> p= LikeNotificationsDAO.getLikeNotifications( id);
		SortComments s=new SortComments("likeTime");
		Collections.sort(p,s);
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, p);
		String resp1 = strWriter.toString();
		System.out.println(" notification resp " + resp1);
			
		writer.println(resp1);
        writer.flush();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
