package com.pro.emp.dao;


import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.domain.EmpAdditionalInfo;



public class EmpAddInfoDAO extends EmpCommanDAO{

	public static String createEmpAdditionalInfo(EmpAdditionalInfo emp,String empid,Session session){
			
	    	UUID id= java.util.UUID.randomUUID();
	    	String recordID="";
	    	String inssql="";
	    	if(emp.getId()!=null || !emp.getId().equals("")){
	    		 inssql="INSERT INTO empInfoDB.empadddetails(" +
				"key,"+
                "empId," +
                "telephoneNo, "+
                "mobileNo," +
                "Address," +
                "city," +
                "state," +
                "country," +
                "pincode," +
                "refrence" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?)"; 
//                "'"+emp.getId()
//                +"','"+empid
//                +"','"+emp.getTelephone()
//                +"','"+emp.getMobile()
//                +"','"+emp.getAddress()
//                +"','"+emp.getCity()
//                +"','"+emp.getState()
//                +"','"+emp.getCountry()
//                +"','"+emp.getPincode()
//                +"','"+emp.getRefContact()+"')";
	    	}else{
	         inssql="INSERT INTO empInfoDB.empadddetails(" +
	        						"key,"+
	                                "empId," +
	                                "telephoneNo, "+
	                                "mobileNo," +
	                                "Address," +
	                                "city," +
	                                "state," +
	                                "country," +
	                                "pincode," +
	                                "refrence" +
	                                ") VALUES(?,?,?,?,?,?,?,?,?,?)";/* +
	                                "'"+id
	                                +"','"+empid
	                                +"','"+emp.getTelephone()
	                                +"','"+emp.getMobile()
	                                +"','"+emp.getAddress()
	                                +"','"+emp.getCity()
	                                +"','"+emp.getState()
	                                +"','"+emp.getCountry()
	                                +"','"+emp.getPincode()
	                                +"','"+emp.getRefContact()+"')";*/
	    	}
	        try
	        {
		        com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	if(emp.getId()!=null || !emp.getId().equals("")){
		    		session.execute(boundStatement.bind(emp.getId(),empid,emp.getTelephone(),emp.getMobile(),emp.getAddress(),emp.getCity(),emp.getState(),emp.getCountry(),emp.getPincode(),emp.getRefContact()));
		    	}else{
		    		session.execute(boundStatement.bind(id,empid,emp.getTelephone(),emp.getMobile(),emp.getAddress(),emp.getCity(),emp.getState(),emp.getCountry(),emp.getPincode(),emp.getRefContact()));
		    	}
	            recordID = ""+id;
	        }
	        catch(Exception se)
	        {
	        	//transactionRollback(con);
	            System.out.println("Insertion Error");
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return recordID;
			
		}
	
	
	/**
     * 
     * This returns true or false to indicate if the record was deleted or not.
     * @@param Connection Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean deleteEmpAdditionalDetails(Session session, String empid)
    {
    	
    	String _sql="DELETE FROM empInfoDB.empadddetails WHERE key = ?";
    	int _result=0;
        try
        {
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			empid));
        	// con.commit();
        	 _result=1;
        }
        catch(Exception se)
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
     * 
     * This will return true to indicate if the update was successful or not. It Updates all record except Id, Creation time & update time 
     * @@param Connection Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean update(Session session, EmpAdditionalInfo emp)
    {
    	
    	String _sql="UPDATE empInfoDB.empadddetails SET " + 
    				"telephoneNo = ?," +
                    "mobileNo =  ?, "+
                    "Address =  ?," +
                    "city = ?," +
                    "state =  ?," +
                    "country =  ?," +
                    "pincode =  ?," +
                    "refrence =  ?" +

    				" WHERE key =  ?";
    	System.out.println(" problem " + _sql);
    	int _result=0;
        try
        {
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			emp.getTelephone(),
	    			emp.getMobile(),
	    			emp.getAddress(),
	    			emp.getCity(),
	    			emp.getState(),
	    			emp.getCountry(),
	    			emp.getPincode(),
	    			emp.getRefContact(),
	    			emp.getId()
	    			));
	    	
        	 _result=1;
        }
        catch(Exception se)
        {
        	_result=0;
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
    public static EmpAdditionalInfo getEmpAdditionaDetailsById(Session session, String id){

    	EmpAdditionalInfo emp=null;

    	String _sql="select " + 
    				"key," +
    				"empId, " +
                    "telephoneNo, "+
                    "mobileNo, " +
                    "Address," +
                    "city," +
                    "state," +
                    "country," +
                    "pincode," +
                    "refrence " +
    				" from empInfoDB.empadddetails where empId = '"+id+"'";
        try{
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
        	for(Row rs:rss)
        	{
        		emp=new EmpAdditionalInfo();
        		emp.setId(rs.getString(1));		
        		emp.setEmpId(rs.getString(2));
        		emp.setTelephone(rs.getString(3));
        		emp.setMobile(rs.getString(4));
        		emp.setAddress(rs.getString(5));
        		emp.setCity(rs.getString(6));
        		emp.setState(rs.getString(7));
        		emp.setCountry(rs.getString(8));
        		emp.setPincode(rs.getString(9));
        		emp.setRefContact(rs.getString(10));
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(con);
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseConnections(ps, rs);
        }
        return emp;
    }

	
}
