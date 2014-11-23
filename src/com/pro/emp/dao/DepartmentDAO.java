package com.pro.emp.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.domain.Department;
import com.pro.emp.domain.MessageDetails;

public class DepartmentDAO extends EmpCommanDAO{

	
	public  String createDepartment( Department dep) 
	{
		Calendar currentDate = Calendar.getInstance();
		 int result=-1;
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
       	  Session session=CassandraDB.getCassConnection();
		 UUID id= java.util.UUID.randomUUID();
		 /**
		  *  + "    KEY text,\n"
		        + "    depName text,\n"
		        + "    orderDep int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		  */
	    String inssql="INSERT INTO empInfoDB.empdepartment(key," +
	    						"depName,"+
	                            "orderDep," +
	                            "indicator,creationDate "+
	                            ") values(?,?,?,?,?)";// +
	                           // "(\'"+id+"\',\'"+message.getSubject()+"\',\'"+message.getMessage()+"\',
	    						//\'"+ss+"\',1,0)";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    				dep.getName(),
	    				dep.getOrderDep(),
	    				1,
	    				Calendar.getInstance().getTime()
	    				));
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // session.commit();
	    	result=1;
	    }
	    catch(Exception se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(session);
	        se.printStackTrace();
	    }
	    
	    return ""+id;
	  }
	
	 /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     * 
     * 
     */
    public Set<Department> getAllDepartment(){

    	Department dep=null;
    	Set<Department> set=new HashSet<Department>();

    	String _sql="select " + 
    				"key,"+
    				"orderDep," +
    				"depName" +
    				
    				" from empdepartment";
        try{
        	Session session= CassandraDB.getCassConnection();
         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
        	for(Row rs: rss)
        	{
        		dep=new Department();
        		dep.setKey(rs.getString(0));
        		dep.setOrderDep(rs.getInt(1));
        		dep.setName(rs.getString(2));
        		set.add(dep);
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return set;
    }
    
    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
    public  String getDepartmentNameById(String id){

    	String name="";

    	String _sql="select " + 
    				"key,"+
    				"depName" +
    				
    				" from empdepartment where key=? ALLOW FILTERING";
        try{
        	Session session = CassandraDB.getCassConnection();
         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
        	for(Row rs:rss)
        	{
        		name=rs.getString(1);
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return name;
    }
    public static void main(String args[]){
    	System.out.println(new DepartmentDAO().deleteDepartment("0f1478fc-fd8e-41fc-becf-88d3f0cf6419"));
    }
    public  int deleteDepartment(String key) 
	{
    	Session session= CassandraDB.getCassConnection();
		 int result=-1;
		 /**
		  *  + "    KEY text,\n"
		        + "    depName text,\n"
		        + "    orderDep int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		  */
	    String inssql="delete from empInfoDB.empdepartment where indicator=1 and key=?";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(key
	    				));
	        //rs=ps.getGeneratedKeys();
	        
	       
	       // session.commit();
	    	result=1;
	    }
	    catch(Exception se)
	    {
	        System.out.println("Insertion Error");
	        result=3;
	        //transactionRollback(session);
	        se.printStackTrace();
	    }
	    
	    return result;
	  }
}
