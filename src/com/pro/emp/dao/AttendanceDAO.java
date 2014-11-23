package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pro.emp.domain.Attendance;

public class AttendanceDAO extends EmpCommanDAO {
	
	public static String createAttendance(Attendance att,Connection con){
		
		PreparedStatement ps=null;
		UUID id= java.util.UUID.randomUUID();
		int recordID=-1;
		 String inssql="INSERT INTO attendence(key," +
         "empid," +
         "year, "+
         "month, "+
         "day1, "+
         "day2, "+
         "day3, "+
         "day4, "+
         "day5, "+
         "day6, "+
         "day7, "+
         "day8, "+
         "day9, "+
         "day10, "+
         "day11, "+
         "day12, "+
         "day13, "+
         "day14, "+
         "day15, "+
         "day16, "+
         "day17, "+
         "day18, "+
         "day19, "+
         "day20, "+
         "day21, "+
         "day22, "+
         "day23, "+
         "day24, "+
         "day25, "+
         "day26, "+
         "day27, "+
         "day28, "+
         "day29, "+
         "day30, "+
         "day31, "+
         "isSubmitted"+
         ") values('"+id
         +"','"+att.getEmpid()
         +"',"+att.getYear()
         +","+att.getMonth()
         +",'"+att.getDay1()
         +"','"+att.getDay2()
         +"','"+att.getDay3()
         +"','"+att.getDay4()
         +"','"+att.getDay5()
         +"','"+att.getDay6()
         +"','"+att.getDay7()
         +"','"+att.getDay8()
         +"','"+att.getDay9()
         +"','"+att.getDay10()
         +"','"+att.getDay11()
         +"','"+att.getDay12()
         +"','"+att.getDay13()
         +"','"+att.getDay14()
         +"','"+att.getDay15()
         +"','"+att.getDay16()
         +"','"+att.getDay17()
         +"','"+att.getDay18()
         +"','"+att.getDay19()
         +"','"+att.getDay20()
         +"','"+att.getDay21()
         +"','"+att.getDay22()
         +"','"+att.getDay23()
         +"','"+att.getDay24()
         +"','"+att.getDay25()
         +"','"+att.getDay26()
         +"','"+att.getDay27()
         +"','"+att.getDay28()
         +"','"+att.getDay29()
         +"','"+att.getDay30()
         +"','"+att.getDay31()
         +"',0)";
		    try
		    {
		    	ps=con.prepareStatement(inssql);
		        ps.executeUpdate();
		        
		       recordID=1;
		    }
		    catch(SQLException se)
		    {
		    	//transactionRollback(con);
		        System.out.println("Insertion Error");
		        se.printStackTrace();
		    }
		    finally
		    {
		    	
		    	//CloseConnections(ps, rs);
		    }
		    if(recordID==1)
		    	return ""+id;
		    else
		    	return "";
		
	}
	
	public static Attendance getCalendar(String empId,int year,int month,Connection con){
		
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	Attendance cal=null;

    	String _sql="select " + 
    	 			 "key," +
			    	 "empid," +
			         "year, "+
			         "month, "+
			         "day1, "+
			         "day2, "+
			         "day3, "+
			         "day4, "+
			         "day5, "+
			         "day6, "+
			         "day7, "+
			         "day8, "+
			         "day9, "+
			         "day10, "+
			         "day11, "+
			         "day12, "+
			         "day13, "+
			         "day14, "+
			         "day15, "+
			         "day16, "+
			         "day17, "+
			         "day18, "+
			         "day19, "+
			         "day20, "+
			         "day21, "+
			         "day22, "+
			         "day23, "+
			         "day24, "+
			         "day25, "+
			         "day26, "+
			         "day27, "+
			         "day28, "+
			         "day29, "+
			         "day30, "+
			         "day31, "+
			         "isSubmitted "+
    				" from attendence where empid = '"+empId+"' and year = "+year+" and month ="+month;
        try{
        	//System.out.println(" SQL IS " + _sql);
           	ps = con.prepareStatement(_sql);
        	rs = ps.executeQuery();
        	if(rs.next())
        	{
        		cal=new Attendance();
        		cal.setId(rs.getString(1));		
        		cal.setEmpid(rs.getString(2));
        		cal.setYear(rs.getInt(3));
        		cal.setMonth(rs.getInt(4));
        		cal.setDay1(rs.getString(5));
        		cal.setDay2(rs.getString(6));
        		cal.setDay3(rs.getString(7));
        		cal.setDay4(rs.getString(8));
        		cal.setDay5(rs.getString(9));
        		cal.setDay6(rs.getString(10));
        		cal.setDay7(rs.getString(11));
        		cal.setDay8(rs.getString(12));
        		cal.setDay9(rs.getString(13));
        		cal.setDay10(rs.getString(14));
        		cal.setDay11(rs.getString(15));
        		cal.setDay12(rs.getString(16));
        		cal.setDay13(rs.getString(17));
        		cal.setDay14(rs.getString(18));
        		cal.setDay15(rs.getString(19));
        		cal.setDay16(rs.getString(20));
        		cal.setDay17(rs.getString(21));
        		cal.setDay18(rs.getString(22));
        		cal.setDay19(rs.getString(23));
        		cal.setDay20(rs.getString(24));
        		cal.setDay21(rs.getString(25));
        		cal.setDay22(rs.getString(26));
        		cal.setDay23(rs.getString(27));
        		cal.setDay24(rs.getString(28));
        		cal.setDay25(rs.getString(29));
        		cal.setDay26(rs.getString(30));
        		cal.setDay27(rs.getString(31));
        		cal.setDay28(rs.getString(32));
        		cal.setDay29(rs.getString(33));
        		cal.setDay30(rs.getString(34));
        		cal.setDay31(rs.getString(35));
        		cal.setIsSubmitted(rs.getInt(36));

        	}
        }
        catch(SQLException se)
        {
        	//transactionRollback(con);
        	cal=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseConnections(ps, rs);
        }
        return cal;
	}
	
	public static boolean updateAttendance(Attendance att,Connection con){
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		 String inssql="Update attendence set " +
         "day1='"+att.getDay1()+"', "+
         "day2='"+att.getDay2()+"', "+
         "day3='"+att.getDay3()+"', "+
         "day4='"+att.getDay4()+"', "+
         "day5='"+att.getDay5()+"', "+
         "day6='"+att.getDay6()+"', "+
         "day7='"+att.getDay7()+"', "+
         "day8='"+att.getDay8()+"', "+
         "day9='"+att.getDay9()+"', "+
         "day10='"+att.getDay10()+"', "+
         "day11='"+att.getDay11()+"', "+
         "day12='"+att.getDay12()+"', "+
         "day13='"+att.getDay13()+"', "+
         "day14='"+att.getDay14()+"', "+
         "day15='"+att.getDay15()+"', "+
         "day16='"+att.getDay16()+"', "+
         "day17='"+att.getDay17()+"', "+
         "day18='"+att.getDay18()+"', "+
         "day19='"+att.getDay19()+"', "+
         "day20='"+att.getDay20()+"', "+
         "day21='"+att.getDay21()+"', "+
         "day22='"+att.getDay22()+"', "+
         "day23='"+att.getDay23()+"', "+
         "day24='"+att.getDay24()+"', "+
         "day25='"+att.getDay25()+"', "+
         "day26='"+att.getDay26()+"', "+
         "day27='"+att.getDay27()+"', "+
         "day28='"+att.getDay28()+"', "+
         "day29='"+att.getDay29()+"', "+
         "day30='"+att.getDay30()+"', "+
         "day31='"+att.getDay31()+"' where key='"+att.getId()+"'";// empId='"+att.getEmpid()+"' and month="+att.getMonth()+" and year="+att.getYear();
        
		 int _result=0;
		    try
		    {
		    	//System.out.println(" Att cal " + inssql);
		    	ps=con.prepareStatement(inssql);

		       
		           
		        ps.executeUpdate();
		        _result=1;
		       //con.commit();
		    }
		    catch(SQLException se)
	        {
		    	//transactionRollback(con);
	             se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        if(_result==0)
	        {
	        	return false;
	        }
	        else
	        {
	        	return true;
	        }
		
	}
	
	public static boolean isCalendarExists(int year,String empid,Connection con){
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean result=false;
		String _sql="select " + 
		 "key," +
		 "empid," +
		 "year, "+
		 "month from attendence where empid='"+empid+"' and  year= "+year;
		try{
			//System.out.println(" IS Cal exist " + _sql);
		 	ps = con.prepareStatement(_sql);
	    	rs = ps.executeQuery();
	    	int i=0;
	    	while(rs.next())
	    	{
	    		i+=1;
	    	} 
	    	//System.out.println(" count is " + i);
	    	if(i==12)
	    		result=true;
		}
    	catch(SQLException se)
        {
    		//transactionRollback(con);
             se.printStackTrace();
        }
        finally
        {
        	//CloseConnections(ps, rs);
        }
      //  System.out.println(" result is " + result);
        return result;
	}
		

	public static boolean updateAttendanceSubmit(String attKey,Connection con,int value){
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		 String inssql="Update attendence set " +
        "isSubmitted="+value+" where key='"+attKey+"'";// empId='"+att.getEmpid()+"' and month="+att.getMonth()+" and year="+att.getYear();
        
		 int _result=0;
		    try
		    {
		    	//System.out.println(" Att cal " + inssql);
		    	ps=con.prepareStatement(inssql);

		       
		           
		        ps.executeUpdate();
		        _result=1;
		       //con.commit();
		    }
		    catch(SQLException se)
	        {
		    	//transactionRollback(con);
	             se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        if(_result==0)
	        {
	        	return false;
	        }
	        else
	        {
	        	return true;
	        }
		
	}

}
