package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.LikeTableDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LikeTable;
import com.pro.emp.domain.PhotoInfoDomain;

/**
 * Servlet implementation class GetPhotoDescription
 */
@WebServlet("/GetPhotoDescription")
public class GetPhotoDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPhotoDescription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String result="";
		StringBuilder b=new StringBuilder();
		String ownerId="";
		response.setContentType("application/json; charset=UTF-8");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
	String id="";
		
		if(request.getParameter("inputQueryString")!=null){
			id=request.getParameter("inputQueryString");
		}
		
		if(request.getParameter("ownerId")!=null){
			ownerId=request.getParameter("ownerId");
		}
		String pid="";

		String ownerName="";
		pid = id;
		//response.setContentType("text/html");
		//PrintWriter writer = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		List<LikeTable> t=new ArrayList<LikeTable>();
		PhotoInfoDomain domain=null;
		String desc="";
		HashMap<String, String> map=new HashMap<String,String>(); 
		HashMap<String, String> map2=new HashMap<String,String>(); 
		try {
			
			if(id!=null && !id.trim().equals("")){
				domain=PhotoInfoDAO.getPhotoDescById(pid, con);
				
				t= LikeTableDAO.getLikesForId(con, pid);
				//System.out.println(" length is  " + t.size() + "  for id " + pid);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, t);
			System.out.println(" domain is " + domain);
			b.append("[{\"id\":\""+domain.getId()+"\",\"description\":\""+domain.getDescription()+"\",\"owner\":\""+domain.getOwner()+"\",\"date\":\""+domain.getDate()+"\",\"fullTime\":\""+domain.getFullTime()+"\",\"likedList\":");
			
			String jsonString= strWriter.toString();
			
			b.append(jsonString);
			
			
			b.append("}]");
			result=b.toString();
			//System.out.println(" contains " + result.indexOf("\n"));
			
			//System.out.println(" here is json get method 1: " +result);
			//System.out.println(" JSON " + jsonString);
			if(result.contains("\b")){
			//	System.out.println(" \\b present 1");
				result=result.replaceAll("\r","");
			}
			if(jsonString.contains("\r")){
			//	System.out.println(" \\r present 2");
				result=result.replaceAll("\r","");
			}
			if(result.contains("\t")){
			//	System.out.println(" \\t present 3");
			}
			if(jsonString.contains("\n")){
			//	System.out.println(" \\n present 4");
			}
			if(jsonString.contains("\b")){
			//	System.out.println(" \\b present 5");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
		CassandraDB.freeConnection(con);
		}
		
		out.print(result);
		out.flush();
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
