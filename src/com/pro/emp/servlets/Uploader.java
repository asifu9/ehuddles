package com.pro.emp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Uploader
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Uploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String albumId="";
		String eed="";
		if(request.getParameter("eed")!=null)
			eed=request.getParameter("eed");
		if(request.getParameter("albumId")!=null)
			albumId=request.getParameter("albumId");
		
		HttpSession session=request.getSession();
		session.setAttribute("albumId", albumId);
		session.setAttribute("eed", eed);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String albumId="";
		String eed="";
		String aId="";
		if(request.getParameter("eed")!=null)
			eed=request.getParameter("eed");
		if(request.getParameter("albumId")!=null)
			albumId=request.getParameter("albumId");
		if(request.getParameter("aId")!=null)
			aId=request.getParameter("aId");
		HttpSession session=request.getSession();
		session.setAttribute("albumId", albumId);
		session.setAttribute("eed", eed);
		session.setAttribute("aId",aId);
	}

}
