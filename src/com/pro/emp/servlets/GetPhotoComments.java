package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.domain.PhotoComments;

/**
 * Servlet implementation class GetPhotoComments
 */
@WebServlet("/GetPhotoComments")
public class GetPhotoComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPhotoComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuilder b=new StringBuilder();
		response.setContentType("application/json");
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		String id="";
		if(request.getParameter("inputQueryString")!=null){
			id=request.getParameter("inputQueryString");
		}

		response.setCharacterEncoding("UTF-8");
		//ConnectionPool c= ConnectionPool.getInstance();
		Session con =CassandraDB.getCassConnection();
		List<PhotoComments> comm=new ArrayList<PhotoComments>();
		try {
			comm = PhotoCommentsDAO.getCommentsForId(id, con);
			
			ObjectComparator comparator = new ObjectComparator();
			Collections.sort(comm, comparator);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, comm);
		
		
		String jsonString= strWriter.toString();
		//System.out.println(" JSON " + jsonString);
		if(jsonString.contains("\b")){
			//System.out.println(" \\b present 1");
		}
		if(jsonString.contains("\r")){
			//System.out.println(" \\r present 2");
			jsonString=jsonString.replaceAll("\r","");
		}
		if(jsonString.contains("\t")){
			//System.out.println(" \\t present 3");
		}
		if(jsonString.contains("\n")){
			//System.out.println(" \\n present 4");
		}
		if(jsonString.contains("\b")){
			//System.out.println(" \\b present 5");
		}
		out.print(jsonString);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

 class ObjectComparator implements Comparator<PhotoComments> {

    public int compare(PhotoComments obj1, PhotoComments obj2) {
        return obj1.getCommentDate().compareTo(obj2.getCommentDate());
    }

}
