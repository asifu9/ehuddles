package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.CommentsDAO;
import com.pro.emp.domain.Comments;

/**
 * Servlet implementation class CommentsUpdate
 */
public class CommentsUpdate extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CommentsUpdate() {
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
		 
		 String desc ="";
		 String day="";
		 String month="";
		 String year="";
		 String empid="";
		 String commentedBy="";
		 String deleteId="";
		 
		 if(request.getParameter("commentDesc")!=null)
		 		desc=request.getParameter("commentDesc").toString().trim();
		 
		 if(request.getParameter("day")!=null)
			 day=request.getParameter("day").toString().trim();
		 
		 if(request.getParameter("month")!=null)
			 month=request.getParameter("month").toString().trim();
		 
		 if(request.getParameter("year")!=null)
			 year=request.getParameter("year").toString().trim();
		 
		 if(request.getParameter("empid")!=null)
			 empid=request.getParameter("empid").toString().trim();
		 
		 if(request.getParameter("commentedBy")!=null)
			 commentedBy=request.getParameter("commentedBy").toString().trim();
		 
		 if(request.getParameter("id")!=null)
			 deleteId=request.getParameter("id").toString().trim();
		 
		 
			
		 
			Connection con =CassandraDB.getCassConnection();
			
		try {
			if(request.getParameter("delete")!=null){
				// to delete the comment
				CommentsDAO.deleteComment(con, deleteId);
			}else {
				// for creating new comment
				Comments com=new Comments();
				com.setDay(Integer.parseInt(day));
				com.setMonth(Integer.parseInt(month));
				com.setYear(Integer.parseInt(year));
				com.setEmpid(empid);
				com.setDescription(desc);
				com.setCommentebBy(commentedBy);
				com.setTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
				CommentsDAO.createComment(com, con);

			}
		}catch(Exception e){
			System.out.println(" exception " + e.toString());
			e.printStackTrace();
			
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		 
	}

*/}
