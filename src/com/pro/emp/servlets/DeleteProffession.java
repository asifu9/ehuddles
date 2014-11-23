package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.EducationDAO;
import com.pro.emp.dao.ProffesionalDAO;

/**
 * Servlet implementation class DeleteProffession
 */
@WebServlet("/DeleteProffession")
public class DeleteProffession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProffession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key="";
		String empId="";
		
		if(request.getParameter("empid")!=null){
			empId= request.getParameter("empid");
		}
		if(request.getParameter("key")!=null){
			key = request.getParameter("key");
		}
		//System.out.println(" delete profession 2" + empId + " : " + key);
		String url="editdetailInfo.jsp?empId="+empId;
		if(key!=null && key.length()>0){
			Session con= CassandraDB.getCassConnection();
			try {
				ProffesionalDAO.deleteProffession(con, key);
				//System.out.println(" delelte profession");
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				CassandraDB.freeConnection(con);
			}
		}
		//System.out.println(" delelte profession " + url);
		RequestDispatcher rd =request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key="";
		String empId="";
		
		if(request.getParameter("empid")!=null){
			empId= request.getParameter("empid");
		}
		if(request.getParameter("key")!=null){
			key = request.getParameter("key");
		}
		//System.out.println(" delete profession " + empId + " : " + key);
		String url="editdetailInfo.jsp?"+empId;
		if(key!=null && key.length()>0){
			Session con= CassandraDB.getCassConnection();
			try {
				ProffesionalDAO.deleteProffession(con, key);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				CassandraDB.freeConnection(con);
			}
		}
		
		RequestDispatcher rd =request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
