package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.message.dao.MessageLinksDAO;

/**
 * Servlet implementation class UpdateMailStatus
 */
@WebServlet("/UpdateMailStatus")
public class UpdateMailStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMailStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromId="";
		String toId="";
		
		if(request.getParameter("fromId")!=null){
			fromId=request.getParameter("fromId");
		}
		if(request.getParameter("toId")!=null){
			toId=request.getParameter("toId");
		}
		System.out.println(" here i am ");
		Session con= CassandraDB.getCassConnection();
		try{
			MessageLinksDAO.updateMessageStatus(con,fromId,toId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
