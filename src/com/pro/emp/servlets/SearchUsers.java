package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpInfo;

/**
 * Servlet implementation class SearchUsers
 */
public class SearchUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str="";
		String name="";
		String type="";
		
		if(request.getParameter("queryString")!=null)
			str= request.getParameter("queryString").trim();
		Session con =CassandraDB.getCassConnection();
		
		//response.setStatus(200);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		StringBuffer sb=new StringBuffer();
		try {
			
			if(!str.equalsIgnoreCase("") && str.contains("~")){
				name = str.split("~")[0];
				type= str.split("~")[1];
				List<EmpInfo> empList=EmpInfoDAO.getEmpByName(con, name);
				sb=new StringBuffer();
				sb.append("");
//				if(empList.size()>=5){
					sb.append("<div id=\"searchresultsMessage\" style=\"width=250px;\">");
//				}else if(empList.size()==4){
//					sb.append("<p id=\"searchresults\" style=\" height:240px; width=200px;\">");
//				}else if(empList.size()==3){
//					sb.append("<p id=\"searchresults\" style=\" height:100px; width=200px;\">");
//				}else if(empList.size()==2){
//					sb.append("<p id=\"searchresults\" style=\" height:120px; width=200px;\">");
//				}else if(empList.size()==1){
//					sb.append("<p id=\"searchresults\" style=\" height:60px; width=200px;\">");
//				}else{
//					sb.append("<p id=\"searchresults\" style=\" height:0px; width=200px;\">");
//				}
				if(empList.size()>0){
					int i=1;
					for(EmpInfo emp:empList){
						String imagePath ="images/person.jpg";
						if(emp.getImage()!=null){
							imagePath=PropertyReader.getValue("photoAlbumPath",request)+ emp.getImage().getPhotoPath().replaceFirst("0_","7_");
						}
						
						
								sb.append("<a style=\"border-bottom-color: #6F5B7B;border-bottom-style: solid;border-bottom-width: 1px;\" onclick=\"addMetoList('"+emp.getId()+"','"+imagePath+"','"+emp.getEmpName()+"')\">");
							

						System.out.println(" image path " + imagePath);
	         			sb.append("<table class=\"tablestyle1\"><tr><td class=\"tablestyle1C1\" width=\"40px\"><img border\"0\" width=\"30px\" height=\"30px\" src=\""+imagePath+"\" alt=\"\" /></td><td class=\"tablestyle1C2\" align=\"left\">");
	         			sb.append("<span class=\"searchheading\" >"+emp.getEmpName()+"</span></a></td></tr></table>");
	         			
	         		//	sb.append("<span>Emp Id : "+emp.getId()+" &nbsp; | &nbsp; Designation :  "+emp.getDesignation()+"</span></a>");
	         			
	         			
	         			//	sb.append("<hr>");
	         			//pagelet(out,sb.toString());
	         			i+=1;
						}
					sb.append("</div>");
					System.out.println(" response - " + sb.toString());
					out.println(sb.toString());
					out.flush();
					
				}
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
			
		}
		//System.out.println(" here is the data " + sb.toString());
		//out.println(sb.toString());
	}

	
/*	 private void pagelet(PrintWriter writer,String content) {
	        if (writer.checkError()){ 
	        	System.out.println(" checksum error");
	        	return;
	        }
	        writer.println("<script>" +
	                "arrived(\"" + content + "\");" +
	                "</script>\n");
	      writer.println(content);
			
	        writer.flush();
	        System.out.println(" flushed ");
	    }*/
}
