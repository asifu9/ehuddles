package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Util;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.PostComments;

/**
 * Servlet implementation class DeletePost
 */
@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String key="";
			String res="";
			String deleteDate="";
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			if(request.getParameter("key")!=null){
				key=request.getParameter("key");
			}
			if(request.getParameter("deleteDate")!=null){
				deleteDate=request.getParameter("deleteDate");
			}
			
			
			Session con = CassandraDB.getCassConnection();
			try{
				int result=PostDAO.deletePost(con, key,Util.getTimeToDelete(Util.convertStringToDate(deleteDate)));
				
				System.out.println(" test ----- " + res);
				if(result==1){
					//resp = "SUCCESS";
					res="{\"result\":\"SUCCESS\"}";
				}
				else{
					//resp = "FAIL";
					res="{\"result\":\"SUCCESS\"}";
				}
			}catch(Exception e){
			e.printStackTrace();	
			}
			finally{
				CassandraDB.freeConnection(con);
			}
			writer.println(res);
	        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
