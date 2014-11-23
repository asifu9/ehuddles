package com.pro.forum.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.domain.Forum;
import com.pro.forum.dao.ForumDAO;

/**
 * Servlet implementation class CreateNewForum
 */


@javax.servlet.annotation.WebServlet(urlPatterns = { "/CreateNewForum" }, asyncSupported = true)
public class CreateNewForum extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateNewForum() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userId="";
		 String postDesc="";
		 String sub="";
		 String id="";
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		if(request.getParameter("hiddenValue")!=null){
			postDesc=request.getParameter("hiddenValue");
		}
		if(request.getParameter("sub")!=null){
			sub=request.getParameter("sub");
		}
		System.out.println("post Desc " + postDesc);
		Forum f=new Forum();
		f.setBody(postDesc);
		f.setForumType(1);
		f.setOwnerId(userId);
		f.setStatus(1);
		f.setSubject(sub);
		Connection con = CassandraDB.getCassConnection();
		try {
			id=ForumDAO.createNewForum(f, con);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		RequestDispatcher rd =request.getServletContext().getRequestDispatcher("/ShowForum?forumId="+id);
		rd.forward(request, response);
	}

*/}
