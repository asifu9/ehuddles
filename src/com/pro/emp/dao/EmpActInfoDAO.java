package com.pro.emp.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.pro.emp.domain.EmpActivityInfo;



public class EmpActInfoDAO extends EmpCommanDAO{

public static int crateEmpActivityInfo( EmpActivityInfo emp,String empid,Connection con) 
{
	PreparedStatement ps=null;
	ResultSet rs=null;
	int result=0;
	UUID id= java.util.UUID.randomUUID();
    String inssql="INSERT INTO empInfoDB.empextraactivities(" +
    						"key,"+
                            "empId," +
                            "typeOfActivity, "+
                            "comment) values('"+id
                            +"','"+empid
                            +"','"+emp.getActivityType()
                            +"','"+emp.getComments()
                            +"')";
    try
    {
    	if(isActivityExists(con,empid.trim(),emp.getActivityType().trim())){
    		return 2;
    	}
    	
    	ps=con.prepareStatement(inssql);

        ps.executeUpdate();
        
      
    	result=1;
    }
    catch(SQLException se)
    {
        System.out.println("Insertion Error");
        result=3;
        //transactionRollback(con);
        se.printStackTrace();
    }
    finally
    {
    	
    	//CloseConnections(ps, rs);
    }
    return result;
  }


public static boolean isActivityExists(Connection con, String id,String activityName){

	PreparedStatement ps=null;
	ResultSet rs=null;
	

	boolean  result=false;
	String _sql="select key,empId,typeOfActivity, comment  from empInfoDB.empextraactivities where empId = '"+id+"'"; // and TypeOfActivity = ?";
    try{
    	//System.out.println(" in activitiy info " + _sql);
       	ps = con.prepareStatement(_sql);
    	rs = ps.executeQuery();
    	while(rs.next())
    	{
    		if(rs.getString(3).equalsIgnoreCase(activityName))
    			result=true;
    	}
    }
    catch(SQLException se)
    {
    	//transactionRollback(con);
    	result=true;
        se.printStackTrace();
    }
    finally
    {
    	//CloseConnections(ps, rs);
    }
    return result;
}


public static boolean deleteEmpActivityInfo(Connection con, String id)
{
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String _sql="DELETE FROM empInfoDB.empextraactivities WHERE key = '"+id+"'";
	int _result=0;
    try
    {
    	ps = con.prepareStatement(_sql);
    	ps.executeUpdate();
    	_result=1;
    }
    catch(SQLException se)
    {
    	//transactionRollback(con);
    	_result=0;
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
//
//public static boolean update(Connection con, EmpActivityInfo emp)
//{
//	PreparedStatement ps=null;
//	ResultSet rs=null;
//	
//	String _sql="UPDATE empextraactivities SET " + 
//				"TypeOfActivity = ?," +
//                "Comment = ?, ";
//                
//
//	int _result=0;
//    try
//    {
//    	 ps = con.prepareStatement(_sql);
//    	
//         ps.setString(1, emp.getActivityType());
//         ps.setString(2,  emp.getComments());
//       
//    	 _result= ps.executeUpdate();
//    	 con.commit();
//    }
//    catch(SQLException se)
//    {
//    	transactionRollback(con);
//         se.printStackTrace();
//    }
//    finally
//    {
//    	CloseConnections(ps, rs);
//    }
//    if(_result==0)
//    {
//    	return false;
//    }
//    else
//    {
//    	return true;
//    }
//}

public static Set<EmpActivityInfo> getEmpActivityInfoById(Connection con, String id){

	PreparedStatement ps=null;
	ResultSet rs=null;
	EmpActivityInfo emp=new EmpActivityInfo();
	Set<EmpActivityInfo> list= new HashSet<EmpActivityInfo>();

	String _sql="select " + 
				"key," +
				"empId," +
                "typeOfActivity, "+
                "comment " +             
				" from empInfoDB.empextraactivities where empId = '"+id+"'";
    try{
       	ps = con.prepareStatement(_sql);
    	rs = ps.executeQuery();
    	while(rs.next())
    	{
    		emp=new EmpActivityInfo();
    		emp.setId(rs.getString(1));
    		emp.setEmpId(rs.getString(2));
    		emp.setActivityType(rs.getString(3));
    		emp.setComments(rs.getString(4));  		
    		list.add(emp);
    	}
    }
    catch(SQLException se)
    {
    	//transactionRollback(con);
    	//emp=null;
        se.printStackTrace();
    }
    finally
    {
    	//CloseConnections(ps, rs);
    }
    return list;
}


}