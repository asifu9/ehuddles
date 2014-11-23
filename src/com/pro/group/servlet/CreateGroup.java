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
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.UserGroupDAO;
import com.pro.emp.domain.Group;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId="";
		String name="";
		String desc="";
		String resp="";
		PrintWriter writer= response.getWriter();
		Group g=new Group();
		 response.setContentType("application/json");
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		if(request.getParameter("name")!=null){
			name=request.getParameter("name");
		}
		if(request.getParameter("desc")!=null){
			desc=request.getParameter("desc");
		}
		System.out.println(" userId " + userId + " name " + name + " : desc " +desc);
		Connection con = CassandraDB.getCassConnection();
		try {
			GroupDAO dao=new GroupDAO();
			
			g.setDescription(desc);
			g.setName(name);
			g.setOwnerId(userId);
			g=dao.createGroup(g, con);
			
			UserGroupDAO ugDao=new UserGroupDAO();
			ugDao.addUserToGroup(g.getKey(), userId, con);
			g=dao.getGroupById(con ,g.getKey());
			
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
