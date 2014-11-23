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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.util.PostUtil;

/**
 * Servlet implementation class GetPost
 */
@WebServlet("/GetPost")
public class GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//System.out.println(" hi how are you in do get ---------------------------------------");
		Session con = CassandraDB.getCassConnection();
		List<PostInfo> total=new ArrayList<PostInfo>();
		response.setCharacterEncoding("UTF-8");
		String time="";
		String flow="1";
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
		String userId="";
		if(request.getParameter("key")!=null){
			userId=request.getParameter("key");
		}
		if(request.getParameter("flow")!=null)
			flow=request.getParameter("flow");
		//System.out.println(" time " + time);
		
		List<PostInfo> ob=new ArrayList<PostInfo>();
		PrintWriter writer = response.getWriter();
		try {
			Timestamp tmsp=new Timestamp(Calendar.getInstance().getTime().getTime());	
			Date lastTime=Calendar.getInstance().getTime();
			
			
			if(time.trim().equals(""))
				lastTime=new Date(tmsp.getTime());
			else
				lastTime=Util.convertStringToDate(time);
			//System.out.println(" user Id = " + userId + " teim " + lastTime);
			if(userId.equalsIgnoreCase("") || userId==null){
			ob= PostUtil.getAllPosts(lastTime,flow);
			}else{
				ob=PostUtil.getAllPostsById(userId, lastTime);
			}
			List<PostInfo> p  = ob;
			
				//ob= PostUtil.getAllPosts(lastTime);
				//p  = ob.get(lastTime);
				
    			for(PostInfo list:p){
    				//System.out.println(" list. " + list.getPostType() + " ------------------------ " + list.getPostedPhotoId());
    				if(list.getPostedPhotoId()!=null){
    					if(list.getPostType()==3){
    						list.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(null,list.getPostedPhotoId() , con));
    					}
    				}else{
    					list.setPostedByPhotoInfo(null);
    				}
    			//	System.out.println(" desc is " + list.getPostedDesc());
            		list.setLikes(PostLikeDAO.getLikesForPostId(con, list.getKey()));
            		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(list.getKey(), con);
            		SortComments s=new SortComments("PostComments");
    				Collections.sort(commList,s);
            		list.setComments(commList);
            	//	for(PostComments pp:commList){
            	//		System.out.println(" pp time " + pp.getCommentDate());
            	//	}
            		lastTime = list.getPostedTime();
//            		System.out.println("  info.getPostedById() " +  info.getPostedById());
    				String value1 = list.getPostedDesc();
    				//value1=value1.replaceAll("\"","\\\\\"");
    				if(value1!=null){
    					value1=value1.replaceAll("\"","\\\\\"");
        			value1=value1.replaceAll("\n","<br/>");
        			value1=value1.replaceAll("\\n","<br/>");
            		if(value1.contains("\b")){
        			}
        			if(value1.contains("\r")){
        				value1=value1.replaceAll("\r","");
        			}
//        			if(value1.contains(",")){
//        				System.out.println(" \\r present 2 111");
//        				value1=value1.replaceAll(",","");
//        			}
        			if(value1.contains("\t")){
        				//System.out.println(" \\t present 3");
        			}
        			if(value1.contains("\n")){
        				//System.out.println(" \\n present 4");
        			}
        			if(value1.contains("\b")){
        				//System.out.println(" \\b present 5");
        			}
        			if(value1.contains("\'")){
        				value1 =value1.replaceAll("\'","\\'");
        			}
    				list.setDateToDelete(Util.getTimeToDelete(Util.convertDateToTimestamp(list.getPostedTime())));
    						
    				list.setPostedDesc(value1);
    				}
    			//	System.out.println(" ---------------- ADDED NEW RECORD---------------- " + list.getDateToDelete());
    				total.add(list);
		
		
    			}
    			//System.out.println(" here is list " + total);
    			for(PostInfo i:total){
    				if(i.getComments()!=null){
    					for(PostComments ps:i.getComments()){
    						//System.out.println(" time = " + ps.getCommentDate());
    					}
    				}
    			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, total);
		String resp = strWriter.toString();
		//System.out.println(" JQUERY IS ******* " + resp);
		writer.println( resp);
		writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//System.out.println(" hi how are you in do post ");
	}

}
