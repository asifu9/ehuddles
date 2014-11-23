package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.dao.TagDAO;
import com.pro.emp.domain.Tag;

/**
 * Servlet implementation class TagGetByPhoto
 */
@WebServlet("/TagGetByPhoto")
public class TagGetByPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagGetByPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp="";
		String photoId="";
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		if(request.getParameter("photoId")!=null)
			photoId=request.getParameter("photoId");
		try{
		TagDAO dao=new TagDAO();
		List<Tag> list= dao.getTaggerUserByPhotoId(photoId);
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, list);
		resp= strWriter.toString();
		System.out.println(" response tag data " + resp);
	}
	catch(Exception ex){

	}
	out.print(resp);
	out.flush();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
