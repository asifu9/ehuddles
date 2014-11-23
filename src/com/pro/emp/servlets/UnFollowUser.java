package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.TickerInfo;

/**
 * Servlet implementation class UnFollowUser
 */
@WebServlet("/UnFollowUser")
public class UnFollowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnFollowUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String followBy="";
		String followTo="";
		String followKey="";
		String resp="";
		int i=-1;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Follow ff=new Follow();
		try {
			if(request.getParameter("followBy")!=null)
				followBy=request.getParameter("followBy");
			if(request.getParameter("followTo")!=null)
				followTo=request.getParameter("followTo");
			if(request.getParameter("followKey")!=null)
				followKey=request.getParameter("followKey");
			
			if(!(followBy.equals("") && followTo.equals(""))){
				FollowDAO dao=new FollowDAO();
				i=dao.unfollow(CassandraDB.getCassConnection(), followBy, followTo,followKey);
			}
			resp="{\"result\":\"SUCCESS\"}";
		}catch(Exception ex){
			resp="{\"result\":\"FAILURE\"}";
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
