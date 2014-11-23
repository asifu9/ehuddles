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
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.domain.EmpActivityInfo;

/**
 * Servlet implementation class ActivityAdd
 */
public class ActivityAdd extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public ActivityAdd() {
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
		// TODO Auto-generated method stub
		
		// for Extra activity
		String typeOfActivity="";
		String Comments="";
		String empid="";
		String flow="";
		if(request.getParameter("txtTypeOfActivity")!=null)
			typeOfActivity=request.getParameter("txtTypeOfActivity").toString().trim();
		
		if(request.getParameter("ActivityComment")!=null)
			Comments=request.getParameter("ActivityComment").toString().trim();
		
		if(request.getParameter("empid")!=null)
			empid=request.getParameter("empid").toString().trim();
		
		if(request.getParameter("flow")!=null)
			flow=request.getParameter("flow").toString().trim();
		
		EmpActivityInfo empAct= new EmpActivityInfo();
		empAct.setActivityType(typeOfActivity);
		empAct.setComments(Comments);
		empAct.setEmpId(empid);
		
		Session con =CassandraDB.getCassConnection();
		int res=EmpActInfoDAO.crateEmpActivityInfo(empAct, empid, con);
		String urlApp="";
		if(res==1){
			urlApp= "&message1=Successfully saved..&message2=";
		}else if(res==2){
			urlApp ="&message2=&message1=This activity type already exists..";
		}else if(res==3){
			urlApp ="&message2=&message1=Exception while saving to DB..";
		}
		CassandraDB.freeConnection(con);
		
		String url="/editOtherInfo.jsp?empId="+empid+urlApp;
		if(flow.equals("creation")){
			url="/createOtherInfo.jsp?empId="+empid+urlApp;
		}
		RequestDispatcher rd =request.getRequestDispatcher(url);
		rd.forward(request, response);
		
		
	}

*/}
