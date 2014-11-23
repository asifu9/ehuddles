package com.pro.emp.dao;


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

public class EducationDAO extends EmpCommanDAO {
	
	
	public static String createEducationInfo(Education edu,Session session){
		
    	//ResultSet rs=null;
    	int result=0;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
//    		if(isFilePathExists(session, proof.getAttachment(), proof.getEmpId())){
//    			return 2;
//    		}
    		
	        String inssql="INSERT INTO empInfoDB.education(" +
	        						"key,"+
	                                "empId," +
	                                "collegeName,course, "+
	                                "start," +
	                                "end,city,indicator) values (?,?,?,?,?,?,?,?)";// +
//	                                "'"+id
//	                                +"','"+edu.getEmpId()
//	                                +"','"+edu.getCollegeName()
//	                                +"','"+edu.getCourseName()
//	                                +"','"+ Util.getCurrentTimestamp(edu.getFrom())
//	                                +"','"+Util.getCurrentTimestamp(edu.getTo())+"','"+edu.getCity()+"')";
	        
	       // System.out.println(" ID education " + inssql);
	       // System.out.println(" SQL " + inssql);
	     	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			edu.getEmpId(),
	    			edu.getCollegeName(),
	    			edu.getCourseName(),
	    			edu.getFrom(),
	    			edu.getTo(),
	    			edu.getCity(),
	    			1
	    			));
   		
    		result=1;
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            System.out.println("Insertion Error");
            se.printStackTrace();
            id=null;
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
	
	public static boolean deleteEducation(Session session, String key)
    {
    	
    	String _sql="DELETE FROM empInfoDB.education WHERE indicator=1 and key =?";
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
	 public static Set<Education> getEmpIdproofInfoById(Session session, String id){

	    	Education edu=null;
	    	Set<Education> empList=new HashSet<Education>();

	    	String _sql="select " + 
	    				"key," +
	    				"empId," +
	                    "collegeName, "+
	                    "course," +
	                    "start ,end,city" +
	    				" from empInfoDB.education where empId = ?";
	        try{
	         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(id));
	        	for(Row rs:rss)
	        	{
	        		edu=new Education();
	        		edu.setKey(rs.getString(0));		
	        		edu.setEmpId(rs.getString(1));
	        		edu.setCollegeName(rs.getString(2));
	        		edu.setCourseName(rs.getString(3));
	        		edu.setFrom(Util.convertDateToTimestamp(rs.getDate(4)));
	        		edu.setTo(rs.getDate(5));
	        		edu.setCity(rs.getString(6));
	        		edu.setStrFrom(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(4))));
	        		edu.setStrTo(Util.getPhotoCreationTime(Util.convertDateToTimestamp(rs.getDate(5))));
	        		empList.add(edu);
	        	}
	        }
	        catch(Exception se)
	        {
	        	//transactionRollback(session);
	        	edu=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseSessions(ps, rs);
	        }
	        return empList;
	    }
	 
	
}
 