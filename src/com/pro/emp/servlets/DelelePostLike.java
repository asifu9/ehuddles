package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
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
import com.pro.emp.EmployeeInfo;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostLikeTable;

/**
 * Servlet implementation class DelelePostLike
 */
@WebServlet("/DelelePostLike")
public class DelelePostLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelelePostLike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String postId="";
		int result=0;
		String resp="";
		String userId="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("postId")!=null){
			postId= request.getParameter("postId");
		}
		if(request.getParameter("userId")!=null){
			userId= request.getParameter("userId");
		}
		Session con =CassandraDB.getCassConnection();
		try {
			List<PostLikeTable> lis= PostLikeDAO.getLikesForPostIdAndUserId(con,postId,userId);
			
			if(lis!=null && lis.size()>0 )
			{
				for(PostLikeTable p:lis)
				result=PostLikeDAO.deleteLike(con, p.getKey());
				
				List<PostLikeTable> list= PostLikeDAO.getLikesForPostId(con,postId);
				ObjectMapper mapper = new ObjectMapper();
				Writer strWriter = new StringWriter();
				mapper.writeValue(strWriter, list);
				resp = strWriter.toString();
				System.out.println(" test ----- " + resp);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
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
