package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pro.emp.Util;
import com.pro.emp.domain.LeavesManagement;

public class LeaveManagementDAO  extends EmpCommanDAO{

	public static boolean create(LeavesManagement lm,Connection con){
		boolean result=false;
		PreparedStatement ps=null;
		UUID id= java.util.UUID.randomUUID();
		String inssql="INSERT INTO leavemanagement(key," +
        "empid," +
        "year, "+
        "paidLeavesTaken, "+
        "sickLeavesTaken, "+
        "doj, "+
        "carryOverLeaves, "+
        "month, "+
        "endmonth, "+
        "endyear, "+
        "totalPaidLeaves "+
        ") values('"+id
        +"','"+lm.getEmpid()
        +"','"+lm.getYear()
        +"',"+lm.getPaidLeavesTaken()
        +","+lm.getSickLeavesTaken()
        +",'"+Util.getCurrentTimestamp(lm.getDoj())
        +"',"+lm.getCarryOverLeaves()
        +",'"+lm.getMonth()
        +"','"+lm.getEndMonth()
        +"','"+lm.getEndyear()
        +"',"+lm.getTotalPaidLeaves()+")";
        
		    try
		    {
		    	//System.out.println(" SQL is " + inssql);
		    	ps=con.prepareStatement(inssql);
		        ps.executeUpdate();
		        
		        	result=true;
		        

		    	//con.commit();
		    }
		    catch(SQLException se)
		    {
		    	//transactionRollback(con);
		        System.out.println("Insertion Error");
		        se.printStackTrace();
		        result=false;
		    }
		    finally
		    {
		    	//CloseConnections(ps, rs);
		    }
		return result;
	}
	
	 /**
     * 
     * This will return true to indicate if the update was successful or not. It Updates all record except Id, Creation time & update time 
     * @@param Connection Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean update(Connection con, LeavesManagement lm)
    {
    	PreparedStatement ps=null;
    	String _sql="UPDATE leavemanagement SET " + 
                    "paidLeavesTaken = "+lm.getPaidLeavesTaken()+"," +
                    "sickLeavesTaken = "+lm.getSickLeavesTaken()+"," +
                    "carryOverLeaves = "+lm.getCarryOverLeaves()+" " +
                   
    				" WHERE key='"+lm.getId()+"'";
    	int _result=0;
        try
        {
        	//System.out.println(" SQL IS IS " + _sql);
        	 ps = con.prepareStatement(_sql);
        	 ps.executeUpdate();
        	 _result= 1;
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
	
    /**
     * Fetch client details by ID
     * @@param DB Connection object con
     * @@param client id
     * @@return docClient Obj
     */
    public static double getLastYearCarryOverLeaves(Connection con, String empid,int year){

    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	double result=0.0;
    	
    	String _sql="select " + 
    				"carryOverLeaves " +    				
    				" from leavemanagement where empid = '"+empid+"' and year ="+year;
        try{
           	ps = con.prepareStatement(_sql);
        	rs = ps.executeQuery();
        	if(rs.next())
        	{
        		result= rs.getInt(1);
        	}
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
        return result;
    }
    public static LeavesManagement getLeaveManByYear(String empid,int year,Connection con){
    	PreparedStatement ps=null;
    	ResultSet rs=null;

    	LeavesManagement lm=null;
    	String _sql="select key," + 
    	"empid," +
        "year, "+
        "paidLeavesTaken, "+
        "sickLeavesTaken, "+
        "doj, "+
        "carryOverLeaves, "+
        "month, "+
        "endmonth, "+
        "endyear, "+
        "totalPaidLeaves "+    				
    				" from leavemanagement where empid = '"+empid+"' and year = "+year;
        try{
        	//System.out.println(" SQL is "+_sql);
           	ps = con.prepareStatement(_sql);
        	rs = ps.executeQuery();
        	if(rs.next())
        	{
        		lm=new LeavesManagement();
        		lm.setId(rs.getString(1));
        		lm.setEmpid(rs.getString(2));
        		lm.setYear(rs.getString(3));
        		lm.setPaidLeavesTaken(rs.getInt(4));
        		lm.setSickLeavesTaken(rs.getInt(5));
        		lm.setDoj(rs.getDate(6));
        		lm.setCarryOverLeaves(rs.getFloat(7));
        		lm.setMonth(rs.getString(8));
        		lm.setEndMonth(rs.getString(9));
        		lm.setEndyear(rs.getString(10));
        		lm.setTotalPaidLeaves(rs.getFloat(11));
        	}
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
        return lm;
    }

}
