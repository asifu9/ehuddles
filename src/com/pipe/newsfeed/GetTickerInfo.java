package com.pipe.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.util.Collections;
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
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoCommentsDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoComments;
import com.pro.emp.domain.TickerInfo;
import com.pro.emp.domain.TickerInfoAll;
import com.pro.emp.domain.TickerInfoDomain;

/**
 * Servlet implementation class GetTickerInfo
 */
@WebServlet("/GetTickerInfo")
public class GetTickerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTickerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key=null;
		if(request.getParameter("key")!=null){
			key =request.getParameter("key");
		}
		
		String resp = "";
		response.setCharacterEncoding("UTF-8");
	
		PrintWriter writer = response.getWriter();
		Session con= CassandraDB.getCassConnection();
		try {
		if(key!=null){
			
			TickerInfoAll ob=new TickerInfoAll();
			TickerInfo info=TickersInfoDAO.getTickersById(con,key);
			ob.setTickerType(info.getType());
			ob.setKey(key);
			ob.setPhoto(PhotoInfoDAO.getPhotoByIdDynamic(info.getUserId(),info.getPhotoId(), con,false));
			List<PhotoComments> photoCom= PhotoCommentsDAO.getCommentsForId(info.getPhotoId(), con);
		
			//SortComments osb=new SortComments("PhotoComments");
			//Collections.sort(photoCom,osb);
			
			ob.setPhotoComm(photoCom);
			ob.setTargetUserId(EmpInfoDAO.getBasicEmpById(con, info.getTargetUserId()));
			ob.setUserId(EmpInfoDAO.getBasicEmpById(con, info.getUserId()));
			ObjectMapper mapper = new ObjectMapper();
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, ob);
			 resp = strWriter.toString();
			//System.out.println(" Ticker ssec tion " + resp);
		}
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
