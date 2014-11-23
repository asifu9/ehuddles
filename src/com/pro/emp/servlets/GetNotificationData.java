package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Session_control;
import com.pro.emp.SortComments;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.dao.PublicChatDAO;
import com.pro.emp.domain.Domain;
import com.pro.emp.domain.DomainMix;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.MessageLinks;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.PublicChat;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.message.dao.MessageLinksDAO;
import com.pro.emp.util.PostUtil;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotification;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class GetMoreData
 */
@WebServlet("/GetNotificationData")
public class GetNotificationData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetNotificationData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);


	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		final ExecutorService exec = Executors.newSingleThreadExecutor();
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		// set the timeout
		EmpInfo info=Session_control.getSession(request);
		//System.out.println(" emp in get notification count is " + info);
		//System.out.println(" emp id in get notification count is  " + info.getId());
		Temp t=new Temp();

		try {

			List<LikeNotificationJson> p= LikeNotificationsDAO.getLikeNotificationsLive(info.getId());
			//SortComments s=new SortComments("commentTime");
			//Collections.sort(p,s);
			List<MessageLinks> links= MessageLinksDAO.getMessageLinksForUserId(CassandraDB.getCassConnection(),info.getId());
			int cccc=0;
			HashMap<String,Integer> cc=new HashMap<String, Integer>();
			for(MessageLinks l:links){
				if(l.getStatus()==0){
					if(cc.containsKey(l.getFromId())){
						int val=cc.get(l.getFromId());
						val+=1;
						cc.put(l.getFromId(), val);
						cccc+=1;
					}else{
					cccc=cccc+1;
					cc.put(l.getFromId(),cccc);
					}
				}
			}
			t.setMessageCount(cccc);
			FollowDAO fdao=new FollowDAO();
			List<Follow> f= fdao.getAllFollowers(info.getId());
			if(f!=null)
				t.setFollowCount(f.size());
			int commCount=getCommentNotifications(info.getId());

			t.setCommCount(commCount);
			t.setLikeCount(p.size());
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(" first one 2 " + Calendar.getInstance().getTime());
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, t);
		String resp = strWriter.toString();
		//System.out.println(exec.isTerminated() + " Notification response :  " + resp);
		writer.println( resp);
		writer.flush();


	}
	private int getCommentNotifications(String userId) {
		int i=0;
		i= CommentNotificationsDAO.getCommentNotificationsCount(userId);
		//SortComments s=new SortComments("commentTime");
		//Collections.sort(p,s);

		return i;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}





class Temp{
	private int likeCount;
	private int commCount;
	private int followCount;
	private int messageCount;
	
	

	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCommCount() {
		return commCount;
	}
	public void setCommCount(int commCount) {
		this.commCount = commCount;
	}


}

