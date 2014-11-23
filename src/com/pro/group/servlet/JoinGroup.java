package com.pro.group.servlet;

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
import com.pro.emp.activity.ActivityDAO;
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.UserGroupDAO;
import com.pro.emp.domain.Activity;
import com.pro.emp.domain.Group;
import com.pro.emp.util.ActivityTypes;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/JoinGroup")
public class JoinGroup extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public JoinGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId="";
		String groupId="";
		String resp="";
		PrintWriter writer= response.getWriter();
		Group g=new Group();
		 response.setContentType("application/json");
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		if(request.getParameter("groupId")!=null){
			groupId=request.getParameter("groupId");
		}
		
		Connection con = CassandraDB.getCassConnection();
		try {
			 UserGroupDAO ob=new UserGroupDAO();
			 
			 ob.addUserToGroup(groupId, userId, con);
			 Activity act=new Activity();
	            act.setActivityType(ActivityTypes.ACT_GROUP_JOINED.getValue());
	            act.setFromId(userId);
	            act.setGroupId(groupId);
	            act.setStatus(1);
	            act.setFlow(1);
	            ActivityDAO.createActivity(act, con);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, g);
		resp= strWriter.toString();
		System.out.println(" resp for post " + resp);
		writer.print(resp);
		writer.flush();
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

*/}
