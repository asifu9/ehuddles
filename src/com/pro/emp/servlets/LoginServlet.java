package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.Session_control;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.notification.NotificationGroupList;
import com.pro.emp.util.PasswordService;

/**
 * Servlet implementation class LoginServlet
 */
@javax.servlet.annotation.WebServlet(urlPatterns = { "/LoginServlet" }, asyncSupported = true)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginName="";
		String password="";
		if(request.getParameter("txtLoginName")!=null)
			loginName=request.getParameter("txtLoginName").toString();

		if(request.getParameter("txtPassword")!=null)
			password=request.getParameter("txtPassword").toString();
		
		PasswordService ps= new PasswordService();
		
		Session con= CassandraDB.getCassConnection();
		EmpInfo emp=null;
		try {
			emp = EmpInfoDAO.isEmpExists(con, loginName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CassandraDB.freeConnection(con);
		
		if(emp!=null){
			
			//System.out.println(" user id in login Servlet " + emp.getId());
				Session_control.setSession_(request,emp.getId());
				RequestDispatcher rd=null;
				
				//rd= request.getRequestDispatcher("/Feeds");
				
				//String server = request.getServerName();
				//String server2 = request.getServletPath();
				//System.out.println(" server " + server + " : " + server2 + " : "+ request.getServletContext().getContextPath());
				//request.getServletContext();
				//response.sendRedirect(request.getServletContext().getContextPath()+"/Feeds");
				//rd.forward(request, response);
				RequestDispatcher rds=null;
				
				 rds= request.getRequestDispatcher("/feeds.jsp");
				rds.forward(request, response);
			
		}else{
			RequestDispatcher rd=null;
			
			 rd= request.getRequestDispatcher("/login.jsp?msg=Invalid login name or Password, Try again!");
			
			rd.forward(request, response);
		}
		
		
	}

}
