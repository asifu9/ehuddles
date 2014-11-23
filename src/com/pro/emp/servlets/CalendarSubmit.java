package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.dao.AttendanceDAO;

/**
 * Servlet implementation class CalendarSubmit
 */
@WebServlet("/CalendarSubmit")
public class CalendarSubmit extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CalendarSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] cal = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
		String status="";
		String empid="";
		String key="";
		String value="";
		
		if(request.getParameter("empid")!=null)
			empid = request.getParameter("empid").toString().trim();
		
		if(request.getParameter("key")!=null)
			key = request.getParameter("key");
		
		if(request.getParameter("value")!=null)
			value=request.getParameter("value");
		
		Calendar calendar = Calendar.getInstance();

		Connection con =CassandraDB.getCassConnection();
		try {
			AttendanceDAO.updateAttendanceSubmit(key, con, Integer.parseInt(value));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
	}

*/}
