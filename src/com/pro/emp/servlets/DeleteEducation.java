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

/**
 * Servlet implementation class DeleteEducation
 */
@WebServlet("/DeleteEducation")
public class DeleteEducation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEducation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key="";
		String empId="";
		
		if(request.getParameter("empId")!=null){
			empId= request.getParameter("empId");
		}
		if(request.getParameter("key")!=null){
			key = request.getParameter("key");
		}
		String url="editdetailInfo.jsp?"+empId;
		if(key!=null && key.length()>0){
			Session con= CassandraDB.getCassConnection();
			try {
				EducationDAO.deleteEducation(con, key);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key="";
		String empId="";
		
		if(request.getParameter("empId")!=null){
			empId= request.getParameter("empId");
		}
		if(request.getParameter("key")!=null){
			key = request.getParameter("key");
		}
		String url="editdetailInfo.jsp?"+empId;
		if(key!=null && key.length()>0){
			Session con= CassandraDB.getCassConnection();
			try {
				EducationDAO.deleteEducation(con, key);
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
