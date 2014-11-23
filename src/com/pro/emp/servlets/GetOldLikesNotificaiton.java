package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.LikeNotification;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class GetOldLikesNotificaiton
 */
@WebServlet("/GetOldLikesNotificaiton")
public class GetOldLikesNotificaiton extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOldLikesNotificaiton() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String time="";
		String userId="";
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
		if(request.getParameter("userId")!=null){
			userId=request.getParameter("userId");
		}
		
		String resp="";
		//System.out.println(" time is " + time);
		Timestamp ts =null;
		if(time!=null && !time.trim().equals("") && !time.equals("null")){
		ts=Timestamp.valueOf(time);	
		}
		//System.out.println(" ttt " + ts);
		if(ts==null)
			ts=new Timestamp(Calendar.getInstance().getTimeInMillis());
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Session con= CassandraDB.getCassConnection();
		List<LikeNotificationJson> data=null;
		List<LikeNotificationJson> dataTotal=new ArrayList<LikeNotificationJson>();
		try {
			int i=0;
			while(i<50){
				System.out.println(" i " + i );
				i+=1;
				boolean flag=true;
				data= LikeNotificationsDAO.getLikeNotificationsOld(con, userId, ts);
			//	SortComments sort=new SortComments("likeTime2");
			//	Collections.sort(data,sort);
				boolean result=false;
				for(LikeNotificationJson jss:data){
					result=true;
					dataTotal.add(jss);
					
					//if(flag==true){
					//	flag=false;
						ts=jss.getLikedTime();
					//}
						
				}
				if(result==false){
					Calendar cals=Calendar.getInstance();
					if(ts==null){
					ts=	new Timestamp(Calendar.getInstance().getTimeInMillis());
					cals.setTimeInMillis(ts.getTime());
					cals.add(Calendar.HOUR,-10);
					}else{
						cals.setTimeInMillis(ts.getTime());
						cals.add(Calendar.HOUR,-10);
					}
					ts= new Timestamp(cals.getTimeInMillis());
				}
				if(dataTotal.size()>8){
					break;
				}
			}
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, dataTotal);
			resp = strWriter.toString();
			//System.out.println(" resp " + resp);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
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
