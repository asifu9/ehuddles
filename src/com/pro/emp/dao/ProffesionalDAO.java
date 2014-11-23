package com.pro.emp.dao;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//import com.sanah.emp.domain.EmpAdditionalInfo;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
//import com.sanah.emp.domain.EmpAdditionalInfo;
import com.pro.emp.Util;
import com.pro.emp.domain.Education;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpIdproofInfo;
import com.pro.emp.domain.Proffesional;

public class ProffesionalDAO extends EmpCommanDAO {
	
	
	public static String createEducationInfo(Proffesional pro,Session session){
		
    	//ResultSet rs=null;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
//    		if(isFilePathExists(session, proof.getAttachment(), proof.getEmpId())){
//    			return 2;
//    		}
    		
	        String inssql="INSERT INTO professional(" +
	        						"key,"+
	                                "empId," +
	                                "companyName,designation, "+
	                                "workedFrom," +
	                                "workedTo,city,indicator,creationdate) values (?,?,?,?,?,?,?,?,?)";// +
//	                                "'"+id
//	                                +"','"+pro.getEmpId()
//	                                +"','"+pro.getCompanyName()
//	                                +"','"+pro.getDesignation()
//	                                +"','"+ Util.getCurrentTimestamp(pro.getWorkedFrom())
//	                                +"','"+Util.getCurrentTimestamp(pro.getWorkedTo())+"','"+pro.getCity()+"')";
	        
	       // System.out.println(" ID professional " + inssql);
	        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			pro.getEmpId(),
	    			pro.getCompanyName(),
	    			pro.getDesignation(),
	    			pro.getWorkedFrom(),
	    			pro.getWorkedTo(),
	    			pro.getCity(),
	    			1,
	    			Calendar.getInstance().getTime()
	    			));
   		
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            System.out.println("Insertion Error");
            se.printStackTrace();
            
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
		return ""+id;
	}
	
	
//	 public static boolean isFilePathExists(Session session, String name,String id){
//
//	    	PreparedStatement ps=null;
//	    	ResultSet rs=null;
//	    	boolean result=false;
//
//	    	String _sql="select " + 
//	    				"key," +
//	    				"Empid," +
//	                    "attachmentPath, "+
//	                    "description," +
//	                    "Type " +
//	    				" from id_proof where attachmentPath = ? and Empid= ?";
//	        try{
//	           	ps = session.prepareStatement(_sql);
//	        	ps.setString(1, name);
//	        	ps.setString(2, id);
//	        	rs = ps.executeQuery();
//	        	if(rs.next())
//	        	{
//	        		result=true;
//	        	}
//	        }
//	        catch(SQLException se)
//	        {
//	        	transactionRollback(session);
//	        	result=true;
//	            se.printStackTrace();
//	        }
//	        finally
//	        {
//	        	CloseSessions(ps, rs);
//	        }
//	        return result;
//	    }
	
	public static boolean deleteProffession(Session session, String key)
    {
    	
    	String _sql="DELETE FROM professional WHERE key = ?";
    	int _result=0;
        try
        {
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(key));
        	_result=1;
        }
        catch(Exception se)
        {_result=0;
        	//transactionRollback(session);
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
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
//	public static boolean update(Session session, EmpIdproofInfo emp)
//    {
//    	PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	
//    	String _sql="UPDATE id_proof SET " + 
//    				"EmpId = ?," +
//                    "attachmentPath = ?, "+
//                    "description = ?," +
//                    "Type = ?" +
//    				" WHERE empid = ?";
//    	String _id = emp.getEmpId();
//    	int _result=0;
//        try
//        {
//        	 ps = session.prepareStatement(_sql);
//        	 ps.setString(1, emp.getAttachment());
//        	 ps.setString(2, emp.getDescription());
//             ps.setString(3, emp.getType());            
//             ps.setString(9, _id);
//        	 _result= ps.executeUpdate();
//        	 session.commit();
//        }
//        catch(SQLException se)
//        {
//        	transactionRollback(session);
//             se.printStackTrace();
//        }
//        finally
//        {
//        	CloseSessions(ps, rs);
//        }
//        if(_result==0)
//        {
//        	return false;
//        }
//        else
//        {
//        	return true;
//        }
//    }
	 public static Set<Proffesional> getAllProffesionalByEmpId(Session session, String id){

	    	Proffesional pro=null;
	    	Set<Proffesional> proList=new HashSet<Proffesional>();

	    	String _sql="select " + 
	    				"key," +
	    				"empId," +
	                    "companyName, "+
	                    "designation," +
	                    "workedFrom ,workedTo,city" +
	    				" from professional where empId = ?";
	        try{
	        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(id));
		        for(Row rs:rss)

	        	{
	        		pro=new Proffesional();
	        		pro.setKey(rs.getString(0));		
	        		pro.setEmpId(rs.getString(1));
	        		pro.setCompanyName(rs.getString(2));
	        		pro.setDesignation(rs.getString(3));
	        		pro.setWorkedFrom(rs.getDate(4));
	        		pro.setWorkedTo(rs.getDate(5));
	        		pro.setStrWorkedFrom(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(4))));
	        		pro.setStrWorkedTo(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(5))));
	        		pro.setCity(rs.getString(6));
	        		proList.add(pro);
	        	}
	        }
	        catch(Exception se)
	        {
	        	//transactionRollback(session);
	        	pro=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseSessions(ps, rs);
	        }
	        return proList;
	    }
	 
	
}
 