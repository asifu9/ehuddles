package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
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
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.dao.LikeTableDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.LikeTable;
import com.pro.emp.domain.TickerInfo;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.notification.DomainNotify;
import com.pro.emp.notification.NotificationGroupList;

/**
 * Servlet implementation class CreateLike
 */
@WebServlet("/CreateLike")
public class CreateLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String data="";
		
		if(request.getParameter("inputQueryString")!=null){
			data=request.getParameter("inputQueryString");
		}
		String newId="";
		String photoId="";
		String empId="";
		int result=0;
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		boolean isDelete=false;
		try {
		if(!data.trim().equalsIgnoreCase("")){
			photoId= data.split("~@~")[0];
			empId= data.split("~@~")[1];
			String type = data.split("~@~")[2];
			LikeTable l=new LikeTable();
			l.setItemId(photoId);
			l.setLikedUserId(empId);
			//System.out.println(" photo id " + photoId + " : " + empId + " : " + type);
			if(type.equalsIgnoreCase("Unlike")){
				isDelete=true;
			result=	LikeTableDAO.deleteLike(con, data.split("~@~")[3]);
			}else{
			newId=LikeTableDAO.createLikeTable(l, con);
			System.out.println(" new ticker id " + newId);
			
			TickerInfo i=new TickerInfo();
			i.setCommentDesc("");
			i.setLikeId(newId);
			i.setPhotoId(photoId);
			i.setTargetUserId(empId);
			i.setCommentId(null);
			i.setUserId(PhotoInfoDAO.getBasicPhotoInfoById(photoId, con).getOwnerId());
			i.setType("like");
			TickerInfo sss=TickersInfoDAO.createoTickerInf(i, con);
			
			TickerInfoDomain dd= TickersInfoDAO.getTickerInfoDomainById(sss.getKey());
			DomainNotify d=new DomainNotify();
			System.out.println(" dd " + dd + " dd key " + dd.getKey());
			d.setType("LikeTicker");
			ObjectMapper mapper2 = new ObjectMapper();
			Writer strWriter2 = new StringWriter();
			mapper2.writeValue(strWriter2, dd);
			String obj2 = strWriter2.toString();
			while(obj2.contains("\\\\\\\\")){
				int ss = obj2.indexOf("\\\\\\\\");
				StringBuffer b=new StringBuffer(obj2);
				b=b.replace(ss, ss+1, "");
				obj2=b.toString();
			}
			d.setJson(obj2);
			NotificationGroupList.getInstance().sendNotificationToAll(d);
			
			
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		//c.freeConnection(con);
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		String dd="";
		if(!newId.equalsIgnoreCase("")){
			dd="Unlike";
		}else{
		if(result==1){
			dd="Unlike";
		}else if(result==3){
			dd="Like";
		}else{
			dd="failure";
		}
		}
		
		writer.println("[{\"likeStatus\":\""+dd+"\",\"likeKey\":\""+newId+"\"}]");
        writer.flush();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data="";
		
		if(request.getParameter("inputQueryString")!=null){
			data=request.getParameter("inputQueryString");
		}
		String newId="";
		String photoId="";
		String empId="";
		int result=0;
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		boolean isDelete=false;
		try {
		if(!data.trim().equalsIgnoreCase("")){
			photoId= data.split("~@~")[0];
			empId= data.split("~@~")[1];
			String type = data.split("~@~")[2];
			LikeTable l=new LikeTable();
			l.setItemId(photoId);
			l.setLikedUserId(empId);
			//System.out.println(" photo id " + photoId + " : " + empId + " : " + type);
			if(type.equalsIgnoreCase("Unlike")){
				isDelete=true;
			result=	LikeTableDAO.deleteLike(con, data.split("~@~")[3]);
			}else{
			newId=LikeTableDAO.createLikeTable(l, con);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		String dd="";
		if(result==1){
			dd="Unlike";
		}else if(result==3){
			dd="Like";
		}else{
			dd="failure";
		}
		
		writer.println("[{\"likeStatus\":\""+dd+"\",\"likeKey\":\""+newId+"\"}]");
        writer.flush();
	}

}
