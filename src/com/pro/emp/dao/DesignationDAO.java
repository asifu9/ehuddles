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
import com.pro.emp.domain.Designation;

public class DesignationDAO extends EmpCommanDAO{


	public  String createDesignation( Designation designation) 
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
	    String inssql="INSERT INTO empInfoDB.empdesignation(key," +
	    						"orderNum,"+
	                            "name," +
	                            "indicator,creationDate "+
	                            ") values(?,?,?,?,?)";// +
	                           // "(\'"+id+"\',\'"+message.getSubject()+"\',\'"+message.getMessage()+"\',
	    						//\'"+ss+"\',1,0)";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    				designation.getOrderNum(),
	    				designation.getName(),
	    				1,
	    				Calendar.getInstance().getTime()
	    				));
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
	
	public  int deleteDesignation( String key) 
	{
		int result=-1;
		 /**
		  *  + "    KEY text,\n"
		        + "    depName text,\n"
		        + "    orderDep int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		  */
	    String inssql="delete from empInfoDB.empdesignation where indicator=1 and key=?";
	    try
	    {
	    	
    		Session session=CassandraDB.getCassConnection();
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(key
	    				));
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
	
	public  Set<Designation> getAllDesg(){

    	Designation des=null;
    	Set<Designation> set=new HashSet<Designation>();
    	
    	String _sql="select " + 
    				"key,"+
    				"orderNum," +
    				"name" +
    				
    				" from empInfoDB.empdesignation where indicator=1";//'+deptId;
        try{
        	Session session = CassandraDB.getCassConnection();
         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
	    	
        	for(Row rs:rss)
        	{
        		des=new Designation();
        		des.setKey(rs.getString(0));
        		des.setOrderNum(rs.getInt(1));
        		des.setName(rs.getString(2));
        		set.add(des);
        	}
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
        return set;
    }
	
	public  String getDesgNameByKey(String key){

    	String name="";

    	String _sql="select " + 
    				"key,"+
    				"orderNum," +
    				"name" +
    				" from empInfoDB.empdesignation where indicator=1 and key=?";// and id=?";
        try{
        	Session session=CassandraDB.getCassConnection();
         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(key));
	    	
        	for(Row rs:rss)
        	{
        			name=rs.getString(2);
        	}
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
        return name;
    }
}
