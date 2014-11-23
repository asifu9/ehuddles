package com.pipe.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.util.PostUtil;

/**
 * Servlet implementation class GetTickers
 */
@WebServlet("/GetTickers")
public class GetTickers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTickers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Session con = CassandraDB.getCassConnection();
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		try {
		List<TickerInfoDomain> list= PostUtil.getAllTickers();
		
	
		
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, list);
		String resp = strWriter.toString();
		//System.out.println(" JQUERY IS " + resp);
		writer.println( resp);
		writer.flush();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			//CassandraDB.freeConnection(con);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
