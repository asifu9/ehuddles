package com.pipe.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
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
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.TickerInfo;
import com.pro.emp.domain.TickerInfoAll;

/**
 * Servlet implementation class GetUpcomingBirthDays
 */
@WebServlet("/GetUpcomingBirthDays")
public class GetUpcomingBirthDays extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUpcomingBirthDays() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resp = "";
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		List<EmpInfo> list=new ArrayList<EmpInfo>();
		Session con= CassandraDB.getCassConnection();
		try {
	
			
			list= EmpInfoDAO.getUpcomingBirthdays(con);
			SortComments osb=new SortComments("date");
			Collections.sort(list,osb);
			
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, list);
			 resp = strWriter.toString();
			//System.out.println(" Birthday ssec tion " + resp);
		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			//CassandraDB.freeConnection(con);
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
