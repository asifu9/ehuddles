package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.Session_control;
import com.pro.emp.dao.LinkInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LinkDomain;

/**
 * Servlet implementation class GetLinks
 */
@WebServlet("/GetLinks")
public class GetLinks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLinks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resp="";
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		EmpInfo info= Session_control.getSession(request);
		Set<LinkDomain> ls=new HashSet<LinkDomain>();
		try{
			ls = new LinkInfoDAO().getLinksByEmpId(info.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		 ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ls);
			resp = strWriter.toString();
			System.out.println(" resp " + resp);
			out.println( resp);
			out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
