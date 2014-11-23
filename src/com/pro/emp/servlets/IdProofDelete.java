package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.EmpIdProofDAO;

/**
 * Servlet implementation class IdProofDelete
 */
public class IdProofDelete extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public IdProofDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id="";
		String empid="";
		String flow="";
		
		if(request.getParameter("proofId")!=null)
			id=request.getParameter("proofId").toString().trim();
		
		if(request.getParameter("empid")!=null)
			empid=request.getParameter("empid").toString().trim();
		
		if(request.getParameter("flow")!=null)
			flow=request.getParameter("flow").toString().trim();
		
		Connection con =CassandraDB.getCassConnection();
		EmpIdProofDAO.deleteEmpIdproofInfo(con, id);
		CassandraDB.freeConnection(con);
		
		String url="/editOtherInfo.jsp?empId="+empid;
		if(flow.equals("creation")){
			url="/createOtherInfo.jsp?empId="+empid;
		}
		
		RequestDispatcher rd =request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

*/}
