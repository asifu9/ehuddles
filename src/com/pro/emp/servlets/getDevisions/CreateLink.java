package com.pro.emp.servlets.getDevisions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

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
 * Servlet implementation class CreateLink
 */
@WebServlet("/CreateLink")
public class CreateLink extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLink() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * var linkName = document.getElementById("linkName").value;
	var linkURL = document.getElementById("linkURL").value;
	var empId = document.getElementById("empId").value;
	var userName = document.getElementById("userName").value;
	var passwordU = document.getElementById("passwordU").value;
		 */
		System.out.println(" here in servlet ");
		 String linkName="";
		 String orderNum="";
		 String userName="";
		 String passwordU="";
		 String linkURL="";
		 String resp="";
		
		 if(request.getParameter("linkName")!=null)
			 linkName=request.getParameter("linkName");
		 if(request.getParameter("orderNum")!=null)
			 orderNum=request.getParameter("orderNum");

		 if(request.getParameter("userName")!=null)
			 userName=request.getParameter("userName");
		 if(request.getParameter("passwordU")!=null)
			 passwordU=request.getParameter("passwordU");
		 if(request.getParameter("linkURL")!=null)
			 linkURL=request.getParameter("linkURL");
		 response.setCharacterEncoding("UTF-8");
		 System.out.println(" orderNum " + orderNum);
			PrintWriter writer = response.getWriter();
		EmpInfo info= Session_control.getSession(request);
		 try{
			 LinkDomain ld=new LinkDomain();
			 ld.setEmpId(info.getId());
			 ld.setOrderNum(Integer.parseInt(orderNum));
			 ld.setPassword(passwordU);
			 ld.setName(linkName);
			 ld.setUrl(linkURL);
			 ld.setUserName(userName);
			 LinkInfoDAO dao=new LinkInfoDAO();
			String id= dao.createLink(ld);
			ld.setKey(id);
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ld);
			resp = strWriter.toString();
			System.out.println(" test ----- " + resp);
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 writer.println(resp);
	        writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
