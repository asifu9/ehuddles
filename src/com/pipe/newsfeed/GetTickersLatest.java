package com.pipe.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import com.pro.emp.Util;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.TickerInfoDomain;

/**
 * Servlet implementation class GetTickersLatest
 */
@WebServlet("/GetTickersLatest")
public class GetTickersLatest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTickersLatest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session con = CassandraDB.getCassConnection();
		Date d=Calendar.getInstance().getTime();
		if(request.getParameter("datee")!=null){
			try{
			d= Util.convertStringToDate(request.getParameter("datee"));
			}catch(Exception e){
				d=Calendar.getInstance().getTime();
			}
		}
		//System.out.println(" t = " + t);
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		try {
			//System.out.println(" dddd = " + d);
		List<TickerInfoDomain> list= TickersInfoDAO.getAllTickersByLatest(con,d);
		
		//SortComments osb=new SortComments("ticker");
		//Collections.sort(list,osb);
		
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
