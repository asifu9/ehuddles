package com.pro.iframeDyna;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.Session_control;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PostInfo;

/**
 * Servlet implementation class PhotoShow
 */
@javax.servlet.annotation.WebServlet(urlPatterns = { "/PhotoShow" })
public class PhotoShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoShow() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key="";
		String resp="";
		PrintWriter writer= response.getWriter();
		List<PhotoAlbum> info=null;
		 response.setContentType("application/json");
		if(request.getParameter("userId")!=null){
			key=request.getParameter("userId");
		}
		
		try{
			
		 info= PhotoAlbumDAO.getAlbumByUserId(key);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, info);
		resp= strWriter.toString();
		System.out.println(" resp for post " + resp);
		writer.print(resp);
		writer.flush();
		
	
    
  }

}
