package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.TickerInfo;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.domain.CommentNotification;

/**
 * Servlet implementation class FollowUser
 */
@WebServlet("/FollowUser")
public class FollowUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String followBy="";
		String followTo="";
		String resp="";
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Follow ff=new Follow();
		try {
			if(request.getParameter("followBy")!=null)
				followBy=request.getParameter("followBy");
			if(request.getParameter("followTo")!=null)
				followTo=request.getParameter("followTo");
		
			if(!(followBy.equals("") && followTo.equals(""))){
				Follow f=new Follow();
				f.setFollowBy(followBy);
				f.setFollowTo(followTo);
				FollowDAO dao=new FollowDAO();
				ff=dao.createFollow(f);
				
				//create notification
				//CommentNotification cn=new CommentNotification();
				
				
				//TickerInfo tic=new TickerInfo();
				//tic.setFollowId(ff.getKey());
				//tic.setUserId(f.getFollowBy());
				//EmpBasicInfo i = CacheRecords.getInstance().getCacheData(f.getFollowBy());
				//EmpBasicInfo j = CacheRecords.getInstance().getCacheData(f.getFollowTo());
				//cn.setMessage(i.getEmpName() + " started following you");
				//cn.setPostCommentedByUserId(f.getFollowBy());
				//cn.setFlow(3);
				//cn.setStatus(1);
				 //CommentNotificationsDAO.createNotificationComment(cn, CassandraDB.getCassConnection());
				//tic.setTargetUserId(f.getFollowBy());
				//tic.setCommentDesc("  : following <span class=\'tickerNameFollowStyle\'>" + j.getEmpName() + " </span>");
				//tic.setType("follow");
				//TickersInfoDAO.createoTickerInf(tic, CassandraDB.getCassConnection());
			}
			resp="{\"result\":\"SUCCESS\",\"key\":\""+ff.getKey()+"\"}";
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
