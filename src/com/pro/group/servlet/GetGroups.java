package com.pro.group.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.CassandraDB;
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.domain.Group;

/**
 * Servlet implementation class CreateGroup
 */
@WebServlet("/GetGroups")
public class GetGroups extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public GetGroups() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId="";
		
		String resp="";
		PrintWriter writer= response.getWriter();
		List<Group>  g=new ArrayList<Group>();
		 response.setContentType("application/json");
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		String flow="";
		if(request.getParameter("flow")!=null){
			flow=request.getParameter("flow");
		}
		Connection con = CassandraDB.getCassConnection();
		try {
			GroupDAO dao=new GroupDAO();
			if(!flow.equalsIgnoreCase("1")){
				g=dao.getAllGroups(con,userId);
			}else{
				g=dao.getGroupsForOwner(con, userId);
			}
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
