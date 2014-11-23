package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.TickerInfo;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;

/**
 * Servlet implementation class CreateComment
 */
@WebServlet("/CreateComment")
public class CreateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String photoId="";
		String commentedUserId="";
		String text="";
		
		if(request.getParameter("photoId")!=null){
			photoId=request.getParameter("photoId");
		}
		if(request.getParameter("commentUserId")!=null){
			commentedUserId=request.getParameter("commentUserId");
		}
		if(request.getParameter("text")!=null){
			text=request.getParameter("text");
		}
		
		//System.out.println(" photo id : " + photoId + " : " + commentedUserId + " : " + text);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		Session con =CassandraDB.getCassConnection();
		String resp="";
		try {
			Calendar currentDate = Calendar.getInstance();
			Timestamp t=new Timestamp(currentDate.getTime().getTime());
	       // System.out.println(" date is " + t);  
			EmpInfo info= EmpInfoDAO.getEmpById(commentedUserId);
			PhotoComments p=new PhotoComments();
			p.setCommentDesc(text.replaceAll("\n", "<br/>"));
			p.setCommentUserName(info.getEmpName());
			p.setCommentUserPhoto((info.getImage()==null?"":info.getImage().getPhotoPath()));
			p.setCommmentUserId(commentedUserId);
			p.setCommentDate(t);
			p.setPhotoId(photoId);
			p=PhotoCommentsDAO.createPhotoComments( p,con);
			
			// create ticker info
			System.out.println(" comment id si  " + p.getId());
			System.out.println(" photo id " + p.getPhotoId());
			TickerInfo i=new TickerInfo();
			i.setCommentDesc(text.replaceAll("\n", "<br/>"));
			i.setCommentId(p.getId());
			i.setPhotoId(p.getPhotoId());
			i.setTargetUserId(commentedUserId);
			i.setUserId(PhotoInfoDAO.getBasicPhotoInfoById(photoId, con).getOwnerId());
			i.setType("comment");
			
			TickerInfo sss= TickersInfoDAO.createoTickerInf(i, con);
			TickerInfoDomain ddd= TickersInfoDAO.getTickerInfoDomainById(sss.getKey());
			DomainNotify dn=new DomainNotify();
			dn.setType("Ticker");
			
			ObjectMapper mapper2 = new ObjectMapper();
			Writer strWriter2 = new StringWriter();
			mapper2.writeValue(strWriter2, ddd);
			String obj2 = strWriter2.toString();
			while(obj2.contains("\\\\\\\\")){
				int ss = obj2.indexOf("\\\\\\\\");
				StringBuffer b=new StringBuffer(obj2);
				b=b.replace(ss, ss+1, "");
				obj2=b.toString();
			}
			dn.setJson(obj2);
			System.out.println(" output is " + obj2);
			NotificationGroupList.getInstance().sendNotificationToAll(dn);
			
			p.setTimeInStr(Util.getCommentTime(t));
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, p);
			resp = strWriter.toString();
			//System.out.println(" test " + resp);
		}catch(Exception ex){
			ex.printStackTrace();
			resp = "";
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		writer.println(resp);
        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
