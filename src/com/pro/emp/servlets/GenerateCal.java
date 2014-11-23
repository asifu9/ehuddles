package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.dao.AttendanceDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.LeaveManagementDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LeavesManagement;

/**
 * Servlet implementation class GenerateCal
 */
public class GenerateCal extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public GenerateCal() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String year="";
		String empid="";
		Connection con =CassandraDB.getCassConnection();

		PrintWriter out=response.getWriter();
		try {
			if(request.getParameter("year")!=null && request.getParameter("empid")!=null){
				
				empid= request.getParameter("empid").toString().trim();
				year = request.getParameter("year").toString().trim();
				
				if(!AttendanceDAO.isCalendarExists(Integer.parseInt(year), empid, con)) {
					for(int i=0;i<=11;i++){
						UtilAttendance.createAttendance(empid,i, Integer.parseInt(year));
					}
					
					
					out.println("<Profile><![CDATA[TRUE]]></Profile>"); 
				
				EmpInfo empp= EmpInfoDAO.getEmpById(con, empid);
				LeavesManagement lm=new LeavesManagement();
				// calculate total leaves for this person
				int nextYear= Integer.parseInt(year)+1;
				Calendar doj= Calendar.getInstance();
				doj.setTime(empp.getDoj());
				if(doj.get(Calendar.YEAR)<=Integer.parseInt(year)){
					// calculate leave for whole year
				//	lm.setTotalPaidLeaves(Integer.parseInt(PropertyReader.getValue("totalAnnualLeaves")));
					lm.setMonth("1");
					lm.setYear(year);
					lm.setEndyear(String.valueOf(Integer.parseInt(year)+1));
				}
				else if(doj.get(Calendar.YEAR)==Integer.parseInt(year)){
					// joined in this year but before april
					Calendar end_year= Calendar.getInstance();
					end_year.set(Integer.parseInt(year), 2, 31);
					float total = (float)((int)((end_year.getTime().getTime()-doj.getTime().getTime())/(float)(1000 * 60 * 60 * 24)))/Integer.parseInt(PropertyReader.getValue("accGap"));
					
					DecimalFormat dec = new DecimalFormat("###.##");
					lm.setTotalPaidLeaves(Float.parseFloat(dec.format(total)));
					lm.setYear(String.valueOf(year));
					lm.setMonth(String.valueOf(doj.get(Calendar.MONTH)));
					lm.setEndyear(year);
					
					
					
				}	
				else{
				
					Calendar end_year= Calendar.getInstance();
					end_year.set(Integer.parseInt(year), 12, 31);
					
					
					int noOfDays= (int)( (end_year.getTime().getTime() - doj.getTime().getTime())/ (1000 * 60 * 60 * 24));
					
				//	float total = (float)((int)((end_year.getTime().getTime()-doj.getTime().getTime())/(float)(1000 * 60 * 60 * 24)))/Integer.parseInt(PropertyReader.getValue("accGap"));
					//total =total + ((int)((end_year.getTime().getTime()-doj.getTime().getTime())/(1000 * 60 * 60 * 24)))%Integer.parseInt(PropertyReader.getValue("accGap"));
					
					//if(total > 15)
					//	total = 15.00f;
					DecimalFormat dec = new DecimalFormat("###.##");
					//lm.setTotalPaidLeaves(Float.parseFloat(dec.format(total)));
					
					lm.setYear(String.valueOf(year));
					lm.setMonth(String.valueOf(doj.get(Calendar.MONTH)));
					lm.setEndyear(String.valueOf(Integer.parseInt(year)));
				}
				lm.setEmpid(empid);
				lm.setDoj(empp.getDoj());
				lm.setEndMonth("12");
				lm.setCarryOverLeaves(0);
				lm.setPaidLeavesTaken(0);
				lm.setSickLeavesTaken(0);
				LeaveManagementDAO.create(lm, con);
				}else{
					out.println("<Profile><![CDATA[FALSE]]></Profile>"); 
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
	}

*/}
