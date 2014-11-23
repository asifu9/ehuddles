package com.pro.forum.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.forum.dao.ForumDiscussionDAO;

/**
 * Servlet implementation class DeleteMyThread
 */
@WebServlet("/DeleteMyThread")
public class DeleteMyThread extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public DeleteMyThread() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String thread="";
		
		if(request.getParameter("thread")!=null)
			thread= request.getParameter("thread");
		
		Connection con = CassandraDB.getCassConnection();
		try{
			ForumDiscussionDAO.deleteThread(con, thread);
		}catch(Exception ex){
			
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

*/}
