package com.pro.emp.servlets;

import java.io.IOException;
import java.sql.Connection;

import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.dao.AttendanceDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.LeaveManagementDAO;
import com.pro.emp.domain.Attendance;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.LeavesManagement;

/**
 * Servlet implementation class CalendarServlet
 */
public class CalendarServlet extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CalendarServlet() {
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
		
		String[] cal = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
		String status="";
		int month=0;
		int year=0;
		String empid="";
		String key="";
		String LMKey="";
		
		if(request.getParameter("_month")!=null)
			month = Integer.parseInt(request.getParameter("_month").toString().trim())-1;
		
		if(request.getParameter("_year")!=null)
			year = Integer.parseInt(request.getParameter("_year").toString().trim());
		
		if(request.getParameter("empid")!=null)
			empid = request.getParameter("empid").toString().trim();
		
		if(request.getParameter("key")!=null)
			key = request.getParameter("key");
		
		if(request.getParameter("LMKey")!=null)
			LMKey = request.getParameter("LMKey");
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		for(int i=0;i<days;i++){
			int index=i+1;
			if(request.getParameter("select"+index)!=null){
				status = request.getParameter("select" + index);
			}
			cal[i]=index+"-"+UtilAttendance.getDayOfWeek(year,month,index)+"-"+status;
		}

		Attendance att=new Attendance();
		att.setId(key);
		att.setEmpid(empid);
		att.setYear(year);
		att.setMonth(month);
		att.setDay1(cal[0]);
		att.setDay2(cal[1]);
		att.setDay3(cal[2]);
		att.setDay4(cal[3]);
		att.setDay5(cal[4]);
		att.setDay6(cal[5]);
		att.setDay7(cal[6]);
		att.setDay8(cal[7]);
		att.setDay9(cal[8]);
		att.setDay10(cal[9]);
		att.setDay11(cal[10]);
		att.setDay12(cal[11]);
		att.setDay13(cal[12]);
		att.setDay14(cal[13]);
		att.setDay15(cal[14]);
		att.setDay16(cal[15]);
		att.setDay17(cal[16]);
		att.setDay18(cal[17]);
		att.setDay19(cal[18]);
		att.setDay20(cal[19]);
		att.setDay21(cal[20]);
		att.setDay22(cal[21]);
		att.setDay23(cal[22]);
		att.setDay24(cal[23]);
		att.setDay25(cal[24]);
		att.setDay26(cal[25]);
		att.setDay27(cal[26]);
		att.setDay28(cal[27]);
		if(days >28)
		att.setDay29(cal[28]);
		if(days >29)
		att.setDay30(cal[29]);
		if(days >30)
		att.setDay31(cal[30]);

		//System.out.println(" att is " + cal + " more specific " + cal[5] + " : " + cal[10]);
		Connection con =CassandraDB.getCassConnection();
		try {
			AttendanceDAO.updateAttendance(att, con);
			EmpInfo info=EmpInfoDAO.getEmpById(con, empid);

			int paidLeavesTaken=UtilAttendance.getAnnualLeavesTakenByYear(empid, info.getDoj(), year);
			int sickLeavesTaken = UtilAttendance.getSickLeavesTakenByYear(empid, info.getDoj(), year);
			LeavesManagement lm=new LeavesManagement();
			lm.setEmpid(empid);
			lm.setCarryOverLeaves(UtilAttendance.getAccAnnualLeavesByYear(empid, info.getDoj(), year));
			lm.setPaidLeavesTaken(paidLeavesTaken);
			lm.setSickLeavesTaken(sickLeavesTaken);
			lm.setYear(String.valueOf(year));
			lm.setId(LMKey);
			LeaveManagementDAO.update(con, lm);
			LeavesManagement lms = LeaveManagementDAO.getLeaveManByYear(empid, year, con);
			lms=UtilAttendance.updateLeaveManagement(lms,year,empid,"paidLeave",con,1);
			lms=UtilAttendance.updateLeaveManagement(lms, year, empid, "SickLeave", con,2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CassandraDB.freeConnection(con);
		}
		
	}

*/}
