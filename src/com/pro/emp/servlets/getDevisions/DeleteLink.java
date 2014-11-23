package com.pro.emp.servlets.getDevisions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.DepartmentDAO;
import com.pro.emp.dao.DesignationDAO;
import com.pro.emp.dao.LinkInfoDAO;

/**
 * Servlet implementation class DeleteLink
 */
@WebServlet("/DeleteLink")
public class DeleteLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLink() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resp="";
		String key="";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		if(request.getParameter("key")!=null){
			key= request.getParameter("key");
		}
		
		System.out.println(" photoId " + key);
		Session con = CassandraDB.getCassConnection();
		try{
				
				LinkInfoDAO dao=new LinkInfoDAO();
				int result=dao.deleteLink(key.trim());
				if(result==1)
					resp="[{\"result\":\"SUCCESS\"}]";
				else
					resp="[{\"result\":\"FAILURE\"}]";
				System.out.println(" test ----- " + resp);
			
		}catch(Exception e){
			resp="[{\"result\":\"FAILURE\"}]";
		e.printStackTrace();	
		resp="";
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
