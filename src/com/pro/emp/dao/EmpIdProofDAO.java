package com.pro.emp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//import com.sanah.emp.domain.EmpAdditionalInfo;

//import com.sanah.emp.domain.EmpAdditionalInfo;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpIdproofInfo;

public class EmpIdProofDAO extends EmpCommanDAO {
	
	
	public static int createEmpIdproofInfo(EmpIdproofInfo proof,String empid,Connection con){
		
		PreparedStatement ps=null;
    	//ResultSet rs=null;
    	int result=0;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
//    		if(isFilePathExists(con, proof.getAttachment(), proof.getEmpId())){
//    			return 2;
//    		}
    		
	        String inssql="INSERT INTO empInfoDB.id_proof(" +
	        						"key,"+
	                                "empId," +
	                                "attachment, "+
	                                "description," +
	                                "typeOfProof,attachmentPath,attachmentThumbnail) values (" +
	                                "'"+id
	                                +"','"+empid
	                                +"','"+proof.getAttachment()
	                                +"','"+proof.getDescription()
	                                +"','"+proof.getType()
	                                +"','"+proof.getAttachment()
	                                +"','"+proof.getAttachmentThumbnail()+"')";
	       // System.out.println(" ID PRoof proof.getAttachment() " + proof.getAttachment());
	       // System.out.println(" ID PRoof proof.getDescription() " + proof.getDescription());
	       // System.out.println(" ID PRoof proof.getType() " + proof.getType());
	       // System.out.println(" ID PRoof proof.getAttachment() " + proof.getAttachment());
	       // System.out.println(" ID PRoof proof.getAttachmentThumbnail() " + proof.getAttachmentThumbnail());
	        
	       // System.out.println(" ID PRoof " + inssql);
            ps=con.prepareStatement(inssql);
             
            ps.executeUpdate();
   		
    		result=1;
        }
        catch(SQLException se)
        {
        	//transactionRollback(con);
            System.out.println("Insertion Error");
            se.printStackTrace();
            result=3;
        }
        finally
        {
        	//CloseConnections(ps, rs);
        }
		return result;
	}
	
	
//	 public static boolean isFilePathExists(Connection con, String name,String id){
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
//	           	ps = con.prepareStatement(_sql);
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
//	        	transactionRollback(con);
//	        	result=true;
//	            se.printStackTrace();
//	        }
//	        finally
//	        {
//	        	CloseConnections(ps, rs);
//	        }
//	        return result;
//	    }
	
	public static boolean deleteEmpIdproofInfo(Connection con, String key)
    {
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	String _sql="DELETE FROM empInfoDB.id_proof WHERE key = '"+key+"'";
    	int _result=0;
        try
        {
        	ps = con.prepareStatement(_sql);
        	 ps.executeUpdate();
        	_result=1;
        }
        catch(SQLException se)
        {_result=0;
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
//	public static boolean update(Connection con, EmpIdproofInfo emp)
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
//        	 ps = con.prepareStatement(_sql);
//        	 ps.setString(1, emp.getAttachment());
//        	 ps.setString(2, emp.getDescription());
//             ps.setString(3, emp.getType());            
//             ps.setString(9, _id);
//        	 _result= ps.executeUpdate();
//        	 con.commit();
//        }
//        catch(SQLException se)
//        {
//        	transactionRollback(con);
//             se.printStackTrace();
//        }
//        finally
//        {
//        	CloseConnections(ps, rs);
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
	 public static Set<EmpIdproofInfo> getEmpIdproofInfoById(Connection con, String id){

	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	EmpIdproofInfo emp=null;
	    	Set<EmpIdproofInfo> empList=new HashSet<EmpIdproofInfo>();

	    	String _sql="select " + 
	    				"key," +
	    				"empId," +
	                    "attachment, "+
	                    "description," +
	                    "typeOfProof ,attachmentThumbnail" +
	    				" from empInfoDB.id_proof where empId = '"+id+"'";
	        try{
	           	ps = con.prepareStatement(_sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		emp=new EmpIdproofInfo();
	        		emp.setId(rs.getString(1));		
	        		emp.setEmpId(rs.getString(2));
	        		emp.setAttachment(rs.getString(3));
	        		emp.setDescription(rs.getString(4));
	        		emp.setType(rs.getString(5));
	        		emp.setAttachmentThumbnail(rs.getString(6));
	        		empList.add(emp);
	        	}
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	        	emp=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return empList;
	    }
	 
	
}
 