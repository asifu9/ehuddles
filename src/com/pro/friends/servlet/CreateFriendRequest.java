package com.pro.friends.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.friends.domain.Friends;
import com.pro.friends.domain.dao.FriendsDAO;

/**
 * Servlet implementation class CreateFriendRequest
 */
@WebServlet("/CreateFriendRequest")
public class CreateFriendRequest extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CreateFriendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fromId="";
		String toId="";
		
		if(request.getParameter("fromId")!=null){
			fromId=request.getParameter("fromId");
		}
		if(request.getParameter("toId")!=null){
			toId=request.getParameter("toId");
		}
		
		Connection con = CassandraDB.getCassConnection();
		try {
			Friends f=new Friends();
			f.setFromId(fromId);
			f.setToId(toId);
			f.setStatus(0);
			FriendsDAO.createFriend(f, con);
			
		}catch(Exception ex){
			ex.printStackTrace();
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
