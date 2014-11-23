//package com.pro.emp;
//
//import java.sql.Session;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.datastax.driver.core.Session;
//import com.pro.emp.dao.AttendanceDAO;
//import com.pro.emp.dao.CommentsDAO;
//import com.pro.emp.dao.EmpInfoDAO;
//import com.pro.emp.dao.LeaveManagementDAO;
//import com.pro.emp.domain.Attendance;
//import com.pro.emp.domain.CalBreakdownInfo;
//import com.pro.emp.domain.CalSplit;
//import com.pro.emp.domain.Comments;
//import com.pro.emp.domain.EmpInfo;
//import com.pro.emp.domain.LeavesManagement;
//
//public class UtilAttendance {
//
//	public static boolean createAttendance(String empid,int month, int year){
//		
//		Calendar calendar = Calendar.getInstance();
//		Attendance attt=new Attendance();
//		attt.setEmpid(empid);
//		attt.setYear(year);
//		attt.setMonth(month);
//		
//		int date = 1;
//		calendar.set(year, month, date);
//		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//		  for(int i=1;i<=days;i++){
//			  
//			  switch (i) {
//			  
//				case 1:
//					attt.setDay1(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 2:
//					attt.setDay2(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 3:
//					attt.setDay3(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 4:
//					attt.setDay4(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 5:
//					attt.setDay5(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 6:
//					attt.setDay6(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 7:
//					attt.setDay7(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 8:
//					attt.setDay8(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 9:
//					attt.setDay9(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 10:
//					attt.setDay10(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 11:
//					attt.setDay11(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 12:
//					attt.setDay12(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 13:
//					attt.setDay13(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 14:
//					attt.setDay14(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 15:
//					attt.setDay15(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 16:
//					attt.setDay16(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 17:
//					attt.setDay17(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 18:
//					attt.setDay18(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 19:
//					attt.setDay19(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 20:
//					attt.setDay20(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 21:
//					attt.setDay21(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 22:
//					attt.setDay22(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 23:
//					attt.setDay23(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 24:
//					attt.setDay24(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 25:
//					attt.setDay25(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 26:
//					attt.setDay26(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 27:
//					attt.setDay27(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 28:
//					attt.setDay28(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 29:
//					attt.setDay29(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 30:
//					attt.setDay30(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//				case 31:
//					attt.setDay31(String.valueOf(i)+"-"+getDayOfWeek(year, month, i)+"-none");
//					break;
//
//					
//			  }
//		  }
//			//Session con =CassandraDB.getCassConnection();
//		//AttendanceDAO.createAttendance(attt, con);
//		
//		//CassandraDB.freeConnection(con);
//
//		
//		return true;
//		
//	}
//	
//	
//	public static String getDayOfWeek(int year, int month,int date){
//		
//		Calendar c = new GregorianCalendar(year, month, date);
//		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); 
//		String dayofWeek="";
//		
//		switch (dayOfWeek) {
//		case 1:
//			dayofWeek="sun";
//			break;
//			
//		case 2:
//			dayofWeek="mon";
//			break;
//		case 3:
//			dayofWeek="tue";
//			break;
//		case 4:
//			dayofWeek="wed";
//			break;
//		case 5:
//			dayofWeek="thu";
//			break;
//		case 6:
//			dayofWeek="fri";
//			break;
//		case 7:
//			dayofWeek="sat";
//			break;
//		}
//		
//		return dayofWeek;
//	}
//	
//	public static Attendance getCurrentCalendar(String empid,String month_,String year_){
//		Attendance cal=null;
//		
//	//	Session con =CassandraDB.getCassConnection();
//		int year =0;
//		int month =0;
//		try {
//			if(month_!=null && !month_.equals("") && year_ !=null && !year_.equals("")){
//			 year =Integer.parseInt(year_);
//			 month = Integer.parseInt(month_);
//			// System.out.println(" Month is upper " + month + " : " + year);
//			}else{
//				Calendar cale = Calendar.getInstance();
//				month = cale.get(Calendar.MONTH)+1;
//				year = cale.get(Calendar.YEAR);
//				//System.out.println(" Month is lower " + month + " : " + year);
//			}
//			
//			//cal =AttendanceDAO.getCalendar(empid, year,month-1, con);
//		}
//		catch(Exception e){
//		}
//		finally{
//			//CassandraDB.freeConnection(con);
//		}
//		return cal;
//	}
//	
//	public static Set<Integer> getCommentsForMonth(String empid,int month,int year){
//		Set<Integer> set =new HashSet<Integer>();
//	//	Session con =CassandraDB.getCassConnection();
//		
//		try {
//			
//		//	Set<Comments> comm=CommentsDAO.getCommentsByMonth(empid, year, month, con);
//			
//		//	for(Comments com:comm){
//		//		if(!set.contains(com.getDay())){
//		//			set.add(com.getDay());
//		//		}
//		//	}
//			
//		}catch(Exception ex ){
//			ex.printStackTrace();
//		}finally {
//	//		CassandraDB.freeConnection(con);
//		}
//		return set;
//	}
//	public static String getJSPScriptForCalendarView(Attendance attt, boolean isReadOnly){
//
//		String code="";
//		code="<table cellpadding=\"0\" align=\"right\" cellspacing=\"0\" id=\"caltable\" style=\"border-color:#EEEEEE;border-style:ridge;border-width:1px;width:70%; \" border=\"0\"><tr>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Sun</td>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Mon</td>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Tue</td>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Wed</td>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Thu</td>"+
//				"<td style=\"border-right-color:#EEEEEE;border-right-style:ridge;border-right-width:1px;border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Fri</td>"+
//				"<td style=\"border-bottom-color:#EEEEEE;border-bottom-style:ridge;border-bottom-width:1px;background-color:white;color:gray;font-weight:bold;\" width=\"20px\" align=\"center\">Sat</td>"+
//				"</tr>";
//		//E5E5E5
//		Set<Integer> commentList = getCommentsForMonth(attt.getEmpid(), attt.getMonth(), attt.getYear());
//
//		int j=0;
//		int row=0;
//		int col=0;
//		CalBreakdownInfo calBreak = new CalBreakdownInfo();
//		for(int i=1;i<=31;i++){
//			  CalSplit cal=null;
//			  
//			  switch (i) {
//			  
//				case 1:
//
//					cal= getCalSplitObject(attt.getDay1());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					code+=getFirstTd(cal.getDayWeek());
//					code+= getAddedStringView( cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					row=0;
//					
//					break;
//				case 2:
//					
//					cal= getCalSplitObject(attt.getDay2());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 3:
//					cal= getCalSplitObject(attt.getDay3());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 4:
//					cal= getCalSplitObject(attt.getDay4());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 5:
//					cal= getCalSplitObject(attt.getDay5());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(),cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 6:
//					cal= getCalSplitObject(attt.getDay6());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 7:
//					cal= getCalSplitObject(attt.getDay7());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 8:
//					cal= getCalSplitObject(attt.getDay8());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 9:
//					cal= getCalSplitObject(attt.getDay9());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 10:
//					cal= getCalSplitObject(attt.getDay10());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 11:
//					cal= getCalSplitObject(attt.getDay11());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 12:
//					cal= getCalSplitObject(attt.getDay12());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 13:
//					cal= getCalSplitObject(attt.getDay13());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 14:
//					cal= getCalSplitObject(attt.getDay14());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 15:
//					cal= getCalSplitObject(attt.getDay15());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 16:
//					cal= getCalSplitObject(attt.getDay16());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 17:
//					cal= getCalSplitObject(attt.getDay17());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 18:
//					cal= getCalSplitObject(attt.getDay18());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 19:
//					cal= getCalSplitObject(attt.getDay19());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 20:
//					cal= getCalSplitObject(attt.getDay20());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 21:
//					cal= getCalSplitObject(attt.getDay21());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 22:
//					cal= getCalSplitObject(attt.getDay22());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 23:
//					cal= getCalSplitObject(attt.getDay23());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 24:
//					cal= getCalSplitObject(attt.getDay24());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 25:
//					cal= getCalSplitObject(attt.getDay25());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 26:
//					cal= getCalSplitObject(attt.getDay26());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 27:
//					cal= getCalSplitObject(attt.getDay27());
//					{
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					j = getCount(cal.getDayWeek());
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 28:
//					cal= getCalSplitObject(attt.getDay28());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 29:
//					cal= getCalSplitObject(attt.getDay29());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 30:
//					cal= getCalSplitObject(attt.getDay30());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 31:
//					cal= getCalSplitObject(attt.getDay31());
//					if(cal!=null){
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					j = getCount(cal.getDayWeek());
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedStringView(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//
//			  }    
//		
//		}
//		//System.out.println("\n\n\n\n " + code + "\n\n");
//		code=code+"</tr></table>";
//		return code;
//	
//	}
//	public static String getJSPScriptForCalendar(Attendance attt,boolean isReadOnly){
//		String code="";
//		code="<table cellpadding=\"0\" cellspacing=\"0\" id=\"caltable\" style=\"border-color:black;border-style:solid;border-width:1px;width:100%; \" border=\"0\"><tr>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Sun</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Mon</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Tue</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Wed</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Thu</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Fri</td>"+
//				"<td style=\"border-color:black;border-style:solid;border-width:1px;background-color:#6F5B7B;color:white;font-weight:bold;\" width=\"70px\" align=\"center\">Sat</td>"+
//				"</tr>";
//		Set<Integer> commentList = getCommentsForMonth(attt.getEmpid(), attt.getMonth(), attt.getYear());
//		//System.out.println(" attt " + attt);
//		int j=0;
//		int row=0;
//		int col=0;
//		CalBreakdownInfo calBreak = new CalBreakdownInfo();
//		for(int i=1;i<=31;i++){
//			  CalSplit cal=null;
//			  
//			  switch (i) {
//			  
//				case 1:
//
//					cal= getCalSplitObject(attt.getDay1());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					code+=getFirstTd(cal.getDayWeek());
//					code+= getAddedString( cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					row=0;
//					
//					break;
//				case 2:
//					
//					cal= getCalSplitObject(attt.getDay2());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 3:
//					cal= getCalSplitObject(attt.getDay3());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 4:
//					cal= getCalSplitObject(attt.getDay4());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 5:
//					cal= getCalSplitObject(attt.getDay5());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(),cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 6:
//					cal= getCalSplitObject(attt.getDay6());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 7:
//					cal= getCalSplitObject(attt.getDay7());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 8:
//					cal= getCalSplitObject(attt.getDay8());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 9:
//					cal= getCalSplitObject(attt.getDay9());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 10:
//					cal= getCalSplitObject(attt.getDay10());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 11:
//					cal= getCalSplitObject(attt.getDay11());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 12:
//					cal= getCalSplitObject(attt.getDay12());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 13:
//					cal= getCalSplitObject(attt.getDay13());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 14:
//					cal= getCalSplitObject(attt.getDay14());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 15:
//					cal= getCalSplitObject(attt.getDay15());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 16:
//					cal= getCalSplitObject(attt.getDay16());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 17:
//					cal= getCalSplitObject(attt.getDay17());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 18:
//					cal= getCalSplitObject(attt.getDay18());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 19:
//					cal= getCalSplitObject(attt.getDay19());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 20:
//					cal= getCalSplitObject(attt.getDay20());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 21:
//					cal= getCalSplitObject(attt.getDay21());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 22:
//					cal= getCalSplitObject(attt.getDay22());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 23:
//					cal= getCalSplitObject(attt.getDay23());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 24:
//					cal= getCalSplitObject(attt.getDay24());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 25:
//					cal= getCalSplitObject(attt.getDay25());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 26:
//					cal= getCalSplitObject(attt.getDay26());
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					break;
//				case 27:
//					cal= getCalSplitObject(attt.getDay27());
//					{
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					j = getCount(cal.getDayWeek());
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 28:
//					cal= getCalSplitObject(attt.getDay28());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 29:
//					cal= getCalSplitObject(attt.getDay29());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 30:
//					cal= getCalSplitObject(attt.getDay30());
//					if(cal!=null){
//					j = getCount(cal.getDayWeek());
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//				case 31:
//					cal= getCalSplitObject(attt.getDay31());
//					if(cal!=null){
//					calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//					j = getCount(cal.getDayWeek());
//					col=j;
//					if(j==1) {
//						code=code+"</tr><tr>";
//						row=row+1;
//					}
//					code+=getAddedString(cal.getDay(), cal.getStatus(),row,col,attt.getMonth(),attt.getYear(),commentList,isReadOnly);
//					}
//					break;
//
//			  }    
//		
//		}
//
//		return code;
//	}
//		
//		
//		public static String getFirstTd(String day){
//			String text="";
//			if(day.equalsIgnoreCase("sun")){
//				text="";
//			}else if(day.equalsIgnoreCase("mon")){
//				text="<tr><td></td>";
//			}else if(day.equalsIgnoreCase("tue")){
//				text="<tr><td></td><td></td>";
//			}else if(day.equalsIgnoreCase("wed")){
//				text="<tr><td></td><td></td><td></td>";
//			}else if(day.equalsIgnoreCase("thu")){
//				text="<tr><td></td><td></td><td></td><td></td>";
//			}else if(day.equalsIgnoreCase("fri")){
//				text="<tr><td></td><td></td><td></td><td></td><td></td>";
//			}else if(day.equalsIgnoreCase("sat")){
//				text="<tr><td></td><td></td><td></td><td></td><td></td><td></td>";
//			}
//			return text;
//		}
//		
//		public static int getCount(String day ){
//			int count=0;
//			if(day.equalsIgnoreCase("sun")){
//				count=1;
//			}else if(day.equalsIgnoreCase("mon")){
//				count=2;
//			}else if(day.equalsIgnoreCase("tue")){
//				count=3;
//			}else if(day.equalsIgnoreCase("wed")){
//				count=4;
//			}else if(day.equalsIgnoreCase("thu")){
//				count=5;
//			}else if(day.equalsIgnoreCase("fri")){
//				count=6;
//			}else if(day.equalsIgnoreCase("sat")){
//				count=7;
//			}
//			return count;
//		}
//		
//		
//	   public static	CalSplit getCalSplitObject(String str){
//			CalSplit cal=null;
//			if(str!=null && !str.trim().equals("")){
//				String[] split = str.split("-");
//				if(split.length ==3){
//					cal=new CalSplit();
//					cal.setDay(Integer.parseInt(split[0]));
//					cal.setDayWeek(split[1]);
//					cal.setStatus(split[2]);
//				}
//			}
//			return cal;
//		}
//	   public static String getAddedStringView(int day,String status,int row,int col,int month,int year,Set<Integer> commentList,boolean isReadOnly){
//
//			row+=1;
//			col-=1;
//			
//
//		
//			String code=" <td  style=\"border-color:"+getColor(status)+";border-style:ridge;border-width:1px;background-color:"+getColor(status)+"; \">" +
//					"<span style=\"font-weight:bold;color:black;font-size:10px;\"> "+day + "</span>" ;
//		
//			code+="</td>";
//				
//			return code;
//		
//	   
//	   }
//		public static String getAddedString(int day,String status,int row,int col,int month,int year,Set<Integer> commentList,boolean isReadOnly){
//			row+=1;
//			col-=1;
//			String imagePath="";
//
//			if(!commentList.contains(day))
//				imagePath="<div  style=\"height:8px;\" ><img src=\"images/images3.jpg\"  width=\"10px\" height=\"10px\" border=\"0\"></div>";
//			else
//				imagePath="<div  id=\"divComment\" class=\"divCommentStyle\" style=\"height:8px;\" > <img src=\"images/images5.jpg\" width=\"10px\" height=\"10px\" border=\"0\"></div>";
//			
//			String code=" <td height=\"20px\" style=\"border-color:black;border-style:solid;border-width:1px;background-color:"+getColor(status)+"; \"> <div align=\"right\">" +
//					"<a href=\"#\" onclick=\"commentPopup("+row+","+col+","+ day+","+year+","+month+")\">" +
//					""+imagePath+"</a></div><br><div align=\"right\">" +
//					"<span style=\"font-weight:bold;\"> "+day + "</span> &nbsp;" ;
//			if(!isReadOnly) {
//				code+="<select style=\"width:20px;backgroundimage:url('../images/dropdownImage.jpg');\" onchange=\"colorCell(this.value,"+row+","+col+")\" name=\"select"+day+"\"> ";
//				
//				if(status.equalsIgnoreCase("none"))
//					code+="<option value=\"none\" selected>None</option> ";
//				else	
//					code+="<option value=\"none\" >None</option> ";
//				
//				if(status.equalsIgnoreCase("present"))
//					code+="<option value=\"present\" selected>Present</option> ";
//				else	
//					code+="<option value=\"present\" >Present</option> ";
//				
//				if(status.equalsIgnoreCase("paidLeave"))
//					code+="<option value=\"paidLeave\" selected>Paid Leave</option> ";
//				else
//					code+="<option value=\"paidLeave\">Paid Leave</option> ";
//				
//				if(status.equalsIgnoreCase("weeklyOff"))
//					code+="<option value=\"weeklyOff\" selected>weekly Off</option> ";
//				else
//					code+="<option value=\"weeklyOff\">weekly Off</option> ";
//				
//				if(status.equalsIgnoreCase("unpaidLeave"))
//					code+="<option value=\"unpaidLeave\" selected>Unpaid Leave</option> ";
//				else
//					code+="<option value=\"unpaidLeave\">Unpaid Leave</option> ";
//				
//				if(status.equalsIgnoreCase("SickLeave"))
//					code+="<option value=\"SickLeave\" selected>Sick Leave</option> ";
//				else
//					code+="<option value=\"SickLeave\">Sick Leave</option> ";
//				
//				if(status.equalsIgnoreCase("unauthorizedLeave"))
//					code+="<option value=\"unauthorizedLeave\" selected>Unauthorized Leave</option> ";
//				else
//					code+="<option value=\"unauthorizedLeave\">Unauthorized Leave</option> ";
//			 
//				code+="</select></div>";
//				
//			}
//			code+="</td>";
//				
//			return code;
//		}
//		
//
//		public static String getColor(String status ){
//			
//			String color="";
//			
//			if(status.equalsIgnoreCase("none")){
//				color = "white";
//			}else if(status.equalsIgnoreCase("present")){
//				color = "green";
//			}else if(status.equalsIgnoreCase("paidLeave")){
//				//color= "#F3EDB4";
//				color= "yellow";
//			}else if(status.equalsIgnoreCase("weeklyOff")){
//				color= "gray";
//			}else if(status.equalsIgnoreCase("unpaidLeave")){
//				color= "#F3EDB4";
//			}else if(status.equalsIgnoreCase("SickLeave")){
//				color= "#96E2DB";
//			}else if(status.equalsIgnoreCase("unauthorizedLeave")){
//				color= "red";
//			}
//			return color;
//			
//		}
//		
//		public static Set<Comments> getAllCommentsByDay(String empid,String day,String month,String year){
//			Set<Comments> comm=new HashSet<Comments>();
//			
//			//Session con =CassandraDB.getCassConnection();
//			
//			try {
//
//			int day_ = Integer.parseInt(day);
//			int month_ = Integer.parseInt(month);
//			int year_ = Integer.parseInt(year);
//			//comm=CommentsDAO.getCommentsByDay(empid, year_, month_, day_, con);
//			
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			finally {
//				//CassandraDB.freeConnection(con);
//			}
//			return comm;
//		}
//		
//		public static EmpInfo getEmployeeById(String empid){
//			EmpInfo info=null;
//			//ConnectionPool c= ConnectionPool.getInstance();
//			Session con =CassandraDB.getCassConnection();
//			try {
//
//
//			info=EmpInfoDAO.getEmpById(con, empid);
//			
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			finally {
//				CassandraDB.freeConnection(con);
//			}
//			return info;
//		}
//		
//		public static String getMonthsScript(String month){
//
//			/*String[] str= {"","","","","","","","","","","",""};
//			if(month!=""){
//				for(int i=0;i<=11;i++){
//					if(String.valueOf(i).equalsIgnoreCase(month)){
//						str[i-1]="selected";
//					}
//				}
//			}else {
//				for(int i=0;i<=11;i++){
//					if(Calendar.getInstance().get(Calendar.MONTH)==i){
//						str[i]="selected";
//					}
//				}
//			}*/
//			String[] str= {"","","","","","","","","","","",""};
//			if(month!=""){
//				for(int i=1;i<=12;i++){
//					if(String.valueOf(i).equalsIgnoreCase(month)){
//						str[i-1]="selected";
//					}
//				}
//			}else {
//				for(int i=0;i<=11;i++){
//					if(Calendar.getInstance().get(Calendar.MONTH)==i){
//						str[i]="selected";
//					}
//				}
//			}
//			String code="";
//			code=code+="<option class=\"comboBoxItems\" value=\"1\" "+str[0]+">January</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2\" "+str[1]+">February</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"3\" "+str[2]+">March</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"4\" "+str[3]+">April</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"5\" "+str[4]+">May</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"6\" "+str[5]+">June</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"7\" "+str[6]+">July</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"8\" "+str[7]+">August</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"9\" "+str[8]+">September</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"10\" "+str[9]+">October</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"11\" "+str[10]+">November</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"12\" "+str[11]+">December</option>";
//			
//			//System.out.println(" cod eis " + code);
//			return code;
//			
//		}
//		
//		public static String getYearsScript(String year){
//
//			String[] str= {"","","","","","","","","",""};
//			int j=0;
//			if(year!=""){
//				for(int i=2012;i<=2020;i++){
//					
//					if(String.valueOf(i).equalsIgnoreCase(year)){
//						str[j]="selected";
//					}
//					j+=1;
//				}
//			}else {
//				for(int i=0;i<=9;i++){
//					if(Calendar.getInstance().get(Calendar.YEAR)==i){
//						str[j]="selected";
//					}
//					j+=1;
//				}
//			}
//			String code="";
//			code=code+="<option class=\"comboBoxItems\" value=\"2011\" "+str[0]+">2011</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2012\""+str[1]+">2012</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2013\""+str[2]+">2013</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2014\""+str[3]+">2014</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2015\""+str[4]+">2015</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2016\""+str[5]+">2016</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2017\""+str[6]+">2017</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2018\""+str[7]+">2018</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2019\""+str[8]+">2019</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2020\""+str[9]+">2020</option>";
//
//			
//			return code;
//		}
//		public static String getYearsScriptView(String year){
//
//			String[] str= {"","","","","","","","",""};
//			int j=0;
//			if(year!=""){
//				for(int i=2012;i<=2020;i++){
//					
//					if(String.valueOf(i).equalsIgnoreCase(year)){
//						str[j]="selected";
//					}
//					j+=1;
//				}
//			}else {
//				for(int i=2012;i<=2020;i++){
//					if(Calendar.getInstance().get(Calendar.YEAR)==i){
//						str[j]="selected";
//					}
//					j+=1;
//				}
//			}
//			String code="";
//			//code=code+="<option class=\"comboBoxItems\" value=\"2011\" "+str[0]+">2011</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2012\" "+str[0]+">2012</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2013\" "+str[1]+">2013</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2014\" "+str[2]+">2014</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2015\" "+str[3]+">2015</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2016\" "+str[4]+">2016</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2017\" "+str[5]+">2017</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2018\" "+str[6]+">2018</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2019\" "+str[7]+">2019</option>";
//			code=code+="<option class=\"comboBoxItems\" value=\"2020\" "+str[8]+">2020</option>";
//
//			
//			return code;
//		}
//		
//		public static CalBreakdownInfo getCalBreakdownInfo(String value,CalBreakdownInfo cal){
//			
//			
//			if(value.equalsIgnoreCase("none")){
//				cal.setNoOfNA(cal.getNoOfNA()+1);
//			}else if(value.equalsIgnoreCase("present")){
//				cal.setNoOfWorkingDays(cal.getNoOfWorkingDays()+1);
//			}else if(value.equalsIgnoreCase("paidLeave")){
//				cal.setNoOfPaidLeaves(cal.getNoOfPaidLeaves()+1);
//			}else if(value.equalsIgnoreCase("weeklyOff")){
//				cal.setNoOfWeeklyOff(cal.getNoOfWeeklyOff()+1);
//			}else if(value.equalsIgnoreCase("unpaidLeave")){
//				cal.setNoOfUnpaidLeaves(cal.getNoOfUnpaidLeaves()+1);
//			}else if(value.equalsIgnoreCase("SickLeave")){
//				cal.setNoOfSickDayLeaves(cal.getNoOfSickDayLeaves()+1);
//			}else if(value.equalsIgnoreCase("unauthorizedLeave")){
//				cal.setNoOfUnauthorizedLeaves(cal.getNoOfUnauthorizedLeaves()+1);
//			}else if(value.equalsIgnoreCase("NA")){
//				cal.setNoOfNA(cal.getNoOfNA()+1);
//			}
//			
//			
//			return cal;
//		}
//		
//		public static CalBreakdownInfo getCalBreakdownWorkingDays(String value,CalBreakdownInfo cal){
//			
//			if(value.equalsIgnoreCase("sat")){
//				
//			}else if(value.equalsIgnoreCase("sun")){
//				
//			}else {
//				cal.setTotalWorkingDays(cal.getTotalWorkingDays()+1);
//			}
//			
//			
//			return cal;
//		}
//
//		public static CalBreakdownInfo getCalendarBreakups(Attendance attt){
//
//			CalBreakdownInfo calBreak = new CalBreakdownInfo();
//			for(int i=1;i<=31;i++){
//				  CalSplit cal=null;
//				  
//				  switch (i) {
//				  
//					case 1:
//						cal= getCalSplitObject(attt.getDay1());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 2:
//						cal= getCalSplitObject(attt.getDay2());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 3:
//						cal= getCalSplitObject(attt.getDay3());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 4:
//						cal= getCalSplitObject(attt.getDay4());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 5:
//						cal= getCalSplitObject(attt.getDay5());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 6:
//						cal= getCalSplitObject(attt.getDay6());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 7:
//						cal= getCalSplitObject(attt.getDay7());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 8:
//						cal= getCalSplitObject(attt.getDay8());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 9:
//						cal= getCalSplitObject(attt.getDay9());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 10:
//						cal= getCalSplitObject(attt.getDay10());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 11:
//						cal= getCalSplitObject(attt.getDay11());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 12:
//						cal= getCalSplitObject(attt.getDay12());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 13:
//						cal= getCalSplitObject(attt.getDay13());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 14:
//						cal= getCalSplitObject(attt.getDay14());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 15:
//						cal= getCalSplitObject(attt.getDay15());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 16:
//						cal= getCalSplitObject(attt.getDay16());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 17:
//						cal= getCalSplitObject(attt.getDay17());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 18:
//						cal= getCalSplitObject(attt.getDay18());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 19:
//						cal= getCalSplitObject(attt.getDay19());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 20:
//						cal= getCalSplitObject(attt.getDay20());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 21:
//						cal= getCalSplitObject(attt.getDay21());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 22:
//						cal= getCalSplitObject(attt.getDay22());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 23:
//						cal= getCalSplitObject(attt.getDay23());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 24:
//						cal= getCalSplitObject(attt.getDay24());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 25:
//						cal= getCalSplitObject(attt.getDay25());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 26:
//						cal= getCalSplitObject(attt.getDay26());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 27:
//						cal= getCalSplitObject(attt.getDay27());
//						calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//						calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						break;
//					case 28:
//						cal= getCalSplitObject(attt.getDay28());
//						if(cal !=null) {
//							calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//							calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						}
//						break;
//					case 29:
//						cal= getCalSplitObject(attt.getDay29());
//						if(cal !=null) {
//							calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//							calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						}
//						break;
//					case 30:
//						cal= getCalSplitObject(attt.getDay30());
//						if(cal !=null) {
//							calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//							calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						}
//						break;
//					case 31:
//						cal= getCalSplitObject(attt.getDay31());
//						if(cal !=null) {
//							calBreak = getCalBreakdownInfo(cal.getStatus(), calBreak);
//							calBreak = getCalBreakdownWorkingDays(cal.getDayWeek(), calBreak);
//						}
//						break;
//
//				  }    
//			
//			}
//
//			return calBreak;
//		}
//		
//		public static float getAccAnnualLeavesByYear(String empid,Date doj,int year){
//			float count=0;
//			// take the doj date and calculate the 
//			Calendar startcal=Calendar.getInstance();
//			startcal.setTime(doj);
//			Calendar current = Calendar.getInstance();
//			
//			
//			// check whether its a first year one
//			if(year==startcal.get(Calendar.YEAR) ){
//				// get total days for from joining day to till date
//				float noOfDays= (float)( (current.getTime().getTime() - startcal.getTime().getTime())/ (1000 * 60 * 60 * 24));
//				//System.out.println("same year number of days is " + noOfDays);
//				//count = noOfDays/Integer.parseInt(PropertyReader.getValue("accGap"));
//				//System.out.println(" acc leavess" + count);
//			}else if(year+1== startcal.get(Calendar.YEAR) ) {
//			// its the same year like 2011-2012 within march.
//				// get total days for from joining day to till date
//				float noOfDays= (float)( (current.getTime().getTime() - startcal.getTime().getTime())/ (1000 * 60 * 60 * 24));
//				//System.out.println("same year number of days is " + noOfDays);
//				//count = noOfDays/Integer.parseInt(PropertyReader.getValue("accGap"));
//				//System.out.println(" acc leavess" + count);
//			
//			
//			// else it will be next further years which starts from April
//			}else{
//
//				startcal.set(year, 0, 1);
//				float noOfDays= (float)( (current.getTime().getTime() - startcal.getTime().getTime())/ (1000 * 60 * 60 * 24));
//				//count = noOfDays/Integer.parseInt(PropertyReader.getValue("accGap"));
//			}
//		return count;
//		}
//		
//		public static int getAnnualLeavesTakenByYear(String empid,Date doj,int year){
//			int count=0;
//			int startMonth=0;
//			// take the doj date and calculate the 
//			Calendar startcal=Calendar.getInstance();
//			startcal.setTime(doj);
//			
//			// check whether its a first year one
//			if(year==startcal.get(Calendar.YEAR)){
//				startMonth=startcal.get(Calendar.MONTH);
//				for(int i=0;i<=startMonth;i++){
//					count=count + getTotalAnnualLeavesTakenByMonth(empid, year,i);
//				}
//				
//			}// else it will be next further years which starts from April
//			else{
//				
//				for(int i=startMonth;i<=11;i++){
//					count=count + getTotalAnnualLeavesTakenByMonth(empid, year,i);
//				}
//				
//				
//			}
//
//			return count;
//		}
//		
////		private static int getTotalAnnualLeavesTakenByMonth(String empid,int year,int month){
////			int count=0;
////			count = getLeavesByMonth(empid, String.valueOf(month),String.valueOf(year), "paidLeave");
////			return count;
////		}
//		
////		private static int getTotalSickLeavesTakenByMonth(String empid,int year,int month){
////			int count=0;
////			count = getLeavesByMonth(empid, String.valueOf(month), String.valueOf(year), "SickLeave");
////			return count;
////		}
//		
////		private static int getLeavesByMonth(String empId,String month,String year,String leaveType){
////			Session con =CassandraDB.getCassConnection();
////			int count=0;
////			try {
////				Attendance att= AttendanceDAO.getCalendar(empId, Integer.parseInt(year),Integer.parseInt(month), con);
////			
////				if(att!=null){
////					if(att.getDay1().contains(leaveType)) count+=1;
////					if(att.getDay2().contains(leaveType)) count+=1;
////					if(att.getDay3().contains(leaveType)) count+=1;
////					if(att.getDay4().contains(leaveType)) count+=1;
////					if(att.getDay5().contains(leaveType)) count+=1;
////					if(att.getDay6().contains(leaveType)) count+=1;
////					if(att.getDay7().contains(leaveType)) count+=1;
////					if(att.getDay8().contains(leaveType)) count+=1;
////					if(att.getDay9().contains(leaveType)) count+=1;
////					if(att.getDay10().contains(leaveType)) count+=1;
////					if(att.getDay11().contains(leaveType)) count+=1;
////					if(att.getDay12().contains(leaveType)) count+=1;
////					if(att.getDay13().contains(leaveType)) count+=1;
////					if(att.getDay14().contains(leaveType)) count+=1;
////					if(att.getDay15().contains(leaveType)) count+=1;
////					if(att.getDay16().contains(leaveType)) count+=1;
////					if(att.getDay17().contains(leaveType)) count+=1;
////					if(att.getDay18().contains(leaveType)) count+=1;
////					if(att.getDay19().contains(leaveType)) count+=1;
////					if(att.getDay20().contains(leaveType)) count+=1;
////					if(att.getDay21().contains(leaveType)) count+=1;
////					if(att.getDay22().contains(leaveType)) count+=1;
////					if(att.getDay23().contains(leaveType)) count+=1;
////					if(att.getDay24().contains(leaveType)) count+=1;
////					if(att.getDay25().contains(leaveType)) count+=1;
////					if(att.getDay26().contains(leaveType)) count+=1;
////					if(att.getDay27().contains(leaveType)) count+=1;
////					if(att.getDay28().contains(leaveType)) count+=1;
////					
////					if(att.getDay29()!=null && att.getDay29().contains(leaveType)) count+=1;
////					if(att.getDay30()!=null && att.getDay30().contains(leaveType)) count+=1;
////					if(att.getDay31()!=null && att.getDay31().contains(leaveType)) count+=1;
////				}
////			}
////			catch(Exception e){
////				e.printStackTrace();
////			}
////			finally{
////				CassandraDB.freeConnection(con);
////			}
////			return count;
////		}
////		
//	
////		public static int getSickLeavesTakenByYear(String empid,Date doj,int year){
////			int count=0;
////			int startMonth=0;
////			// take the doj date and calculate the 
////			Calendar startcal=Calendar.getInstance();
////			startcal.setTime(doj);
////
////
////			
////			// check whether its a first year one
////			if(year==startcal.get(Calendar.YEAR)){
////				startMonth=startcal.get(Calendar.MONTH);
////				for(int i=0;i<=startMonth;i++){
////					count=count + getTotalSickLeavesTakenByMonth(empid, year,i);
////				}
////				
////			}// else it will be next further years which starts from April
////			else{
////				
////				for(int i=startMonth;i<=11;i++){
////					count=count + getTotalAnnualLeavesTakenByMonth(empid, year,i);
////				}
////				
////				
////			}
////
////			return count;
////		}
//		
////		
////		public static double getCarryoverAnnualLeaves(String year_,String empid){
////			double count=0.0;
////			
////			if(!(year_==null || year_.equals(""))){
////				
////				Session con =CassandraDB.getCassConnection();
////				int year= Integer.parseInt(year_);
////				year=year-1;
////				try {
////					count=LeaveManagementDAO.getLastYearCarryOverLeaves(con, empid, year);
////				}
////				catch(Exception e){
////					
////				}
////				finally {
////					CassandraDB.freeConnection(con);
////				}
////				
////			}
////			
////			return count;
////		}
//		
////		public static double getTotalAnnualLeaveForYear(String empid,String year){
////			
////			double count=0.0;
////			//ConnectionPool c= ConnectionPool.getInstance();
////			Session con =CassandraDB.getCassConnection();
////			Calendar currentDate= Calendar.getInstance();
////			try {
////				EmpInfo info=EmpInfoDAO.getEmpById(con, empid);
////				if(!(year==null) && !(year.equals(""))){
////					Calendar doj= Calendar.getInstance();
////					doj.setTime(info.getDoj());
////					int currentYear = Integer.parseInt(year);
////					if(doj.get(Calendar.YEAR)==currentYear && doj.get(Calendar.MONTH)>0){
////						// calculate for whole year--> April-March
////					}else if(doj.get(Calendar.YEAR)<currentYear 
////							&& currentDate.get(Calendar.MONTH)<=0
////							&& doj.get(Calendar.YEAR)+1==currentDate.get(Calendar.YEAR)){
////						// calculate for next whole year 
////					}else if(doj.get(Calendar.YEAR)<currentYear 
////							&& doj.get(Calendar.MONTH) <=0 
////							&& currentDate.get(Calendar.MONTH)<=0
////							){
////						// calculate for next whole year from april t0 march
////					}
////				}
////					
////				
////			}catch(Exception e){
////				
////			}
////			finally {
////				CassandraDB.freeConnection(con);
////			}
////			
////			
////			return count;
////		}
//		
////		public static LeavesManagement getLeaveManagement(Attendance att){
////			
////
////			LeavesManagement lm= null;
////			Session con =CassandraDB.getCassConnection();
////			try {
////				lm=LeaveManagementDAO.getLeaveManByYear(att.getEmpid(), att.getYear(), con);
////			}catch (Exception e) {
////				// TODO: handle exception
////			}
////			finally{
////				CassandraDB.freeConnection(con);
////			}
////			return lm;
////		}
//		
////		public static LeavesManagement updateLeaveManagement(LeavesManagement lm,int year,String empid,String leaveType,Session con,int type){
////			int count=getLeavesByMonth(empid,"0",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"1",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"2",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"3",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"4",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"5",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"6",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"7",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"8",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"9",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"10",String.valueOf(year),leaveType);
////			count+=getLeavesByMonth(empid,"11",String.valueOf(year),leaveType);
////		
////		
////		
////			if(type==1)
////				lm.setPaidLeavesTaken(count);
////			else
////				lm.setSickLeavesTaken(count);
////			if(type==1)
////				lm.setCarryOverLeaves(lm.getTotalPaidLeaves()-count);
////			LeaveManagementDAO.update(con, lm);
////			return lm;
////		}
//}
// 