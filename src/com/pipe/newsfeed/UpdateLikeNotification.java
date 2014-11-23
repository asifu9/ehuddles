package com.pipe.newsfeed;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.post.dao.LikeNotificationsDAO;

/**
 * Servlet implementation class UpdateLikeNotification
 */
@WebServlet("/UpdateLikeNotification")
public class UpdateLikeNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLikeNotification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key="";
		if(request.getParameter("key")!=null){
			key=request.getParameter("key");
		}
		System.out.println("updated key--------- " + key);
		try {
		LikeNotificationsDAO.updateNOtificaitonLIke( key);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			//CassandraDB.freeConnection(con);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
