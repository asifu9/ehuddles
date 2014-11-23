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
import com.pro.emp.domain.LinkDomain;
import com.pro.emp.domain.MessageDetails;

public class LinkInfoDAO extends EmpCommanDAO{

	
	public  String createLink( LinkDomain link) 
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
		  *  empId text,\n"
		        + "    url text,\n"
		        + "    userName text,\n"
		        + "    password text,\n"
		        + "    orderNum int,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		  */
	    String inssql="INSERT INTO empInfoDB.linkInfo(key," +
	    						"empId,url,userName,password,orderNum,"+
	                            "indicator,creationDate,name "+
	                            ") values(?,?,?,?,?,?,?,?,?)";// +
	                           // "(\'"+id+"\',\'"+message.getSubject()+"\',\'"+message.getMessage()+"\',
	    						//\'"+ss+"\',1,0)";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    				link.getEmpId(),
	    				link.getUrl(),link.getUserName(),link.getPassword(),link.getOrderNum(),1,Calendar.getInstance().getTime()
	    				,link.getName()
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
    public Set<LinkDomain> getLinksByEmpId(String empId){

    	LinkDomain link=null;
    	Set<LinkDomain> set=new HashSet<LinkDomain>();

    	String _sql="select key," +
	    						"empId,url,userName,password,orderNum,"+
	                            "indicator,creationDate,name "+
    				
    				" from  empInfoDB.linkInfo where empId=? and indicator=1";
        try{
        	Session session= CassandraDB.getCassConnection();
         	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(empId));
        	for(Row rs: rss)
        	{
        		link=new LinkDomain();
        		link.setKey(rs.getString(0));
        		link.setEmpId(rs.getString(1));
        		link.setUrl(rs.getString(2));
        		link.setUserName(rs.getString(3));
        		link.setPassword(rs.getString(4));
        		link.setOrderNum(rs.getInt(5));
        		link.setName(rs.getString(8));
        		set.add(link);
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
    public  int deleteLink(String key) 
  	{
      	Session session= CassandraDB.getCassConnection();
  		 int result=-1;
  		
  	    String inssql="delete from empInfoDB.linkInfo where indicator=1 and key=?";
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
    
    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     *//*
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
    	System.out.println(new LinkInfoDAO().deleteDepartment("0f1478fc-fd8e-41fc-becf-88d3f0cf6419"));
    }
    public  int deleteDepartment(String key) 
	{
    	Session session= CassandraDB.getCassConnection();
		 int result=-1;
		 *//**
		  *  + "    KEY text,\n"
		        + "    depName text,\n"
		        + "    orderDep int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		  *//*
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
	  }*/
}
