package com.pro.emp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManageCalSession
 */
public class ManageCalSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCalSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(true);
		 String empid="";
			//System.out.println(" in Mangae cal Session ");
			if(request.getParameter("empid")!=null)
				empid=request.getParameter("empid").toString().trim();
		if(empid!=null)
		{
			session.setAttribute("calendarEmpId", empid);
			//System.out.println(" in Mangae cal Session data  " + empid);
		}
		RequestDispatcher rd= request.getRequestDispatcher("/listUsers.jsp?view=popup");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(true);
		 String empid="";
		 //System.out.println(" in Mangae cal Session ");
			if(request.getParameter("empid")!=null)
				empid=request.getParameter("empid").toString().trim();
		if(empid!=null)
		{
			session.setAttribute("calendarEmpId", empid);
		}
		RequestDispatcher rd= request.getRequestDispatcher("/listUsers.jsp?view=popup");
		rd.forward(request, response);
	}

}
