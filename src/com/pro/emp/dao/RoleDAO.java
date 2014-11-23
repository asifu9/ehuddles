package com.pro.emp.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.Util;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Role;

public class RoleDAO extends EmpCommanDAO {

public static int createRoles(EmpInfo emp,Session session,HashMap<String,Boolean> roles){
		
    	boolean isError=false;
    	int recordID=0;
    	
    	try {
    		Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter= 
            	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
            	  String dateNow = formatter.format(currentDate.getTime());
            	  
    		Set<String> rolesSet = roles.keySet();
			for(String role:rolesSet){
				UUID id= java.util.UUID.randomUUID();
		        String inssql="INSERT INTO  empInfoDB.role(" +
		        						"key,"+
		                                "id," +
		                                "role, "+
		                                "isActive,"+
		                                "createdDate," +
		                                "updateDate" +
		                                ") VALUES(?,?,?,?,?,?)";// +
		                                /*"('"+id
		                                +"','"+emp.getId()
		                                +"','"+role
		                                +"',"+(roles.get(role)==true?1:0)
		                                +",'"+Util.getCurrentTimestamp()
		                                +"','"+Util.getCurrentTimestamp()
		                                +"')";*/
		        try
		        {
		        	//System.out.println(" SLQ in create role is " + inssql);
		        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
			    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
			    	ResultSet rss=session.execute(boundStatement.bind(id,
			    			emp.getId(),
			    			role,
			    			(roles.get(role)==true?1:0),
			    			Util.getCurrentTimestamp(),
			    			Util.getCurrentTimestamp()));
		           	recordID=1;            
		
		        }
		        catch(Exception se)
		        {
		        	isError=true;
		        	se.printStackTrace();
		        	recordID=0;
		        }
			}
			if(isError){
				throw new Exception();
			}else{
			//session.commit();
			}
    	}
    	catch(Exception e){
    		
    		//transactionRollback(session);
            System.out.println("Insertion Error");
            e.printStackTrace();
            recordID=0;
    	}
    	finally{
    		//CloseSessions(ps, rs);
    	}
        //finally
        //{
        	//CloseSessions(ps, rs);
        //}
        return recordID;
		
	}


public static int updateRole(EmpInfo emp,Session session,String role,boolean isActive){
		
    	//ResultSet rs=null;

    	int recordID=-1;
    	  Calendar currentDate = Calendar.getInstance();
          SimpleDateFormat formatter= 
          	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
          	  String dateNow = formatter.format(currentDate.getTime());
	       
	                                
	        try
	        {
	        	List<Role> rolesList=getAllRoleTypesByDepartment(session,emp.getId());
	        	
	        	Iterator<Role> itr=rolesList.iterator();
	        	while(itr.hasNext()){
	        		Role r = itr.next();
	        		if(r.getRole().equalsIgnoreCase(role)){
	        			 String inssql="Update  empInfoDB.role set " +
                         "isActive=?,"+
                         "updateDate=?" +
                         " where key = ?";
	        			 
	        			 com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	        		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	        		    session.execute(boundStatement.bind((isActive==true?1:0),dateNow,r.getRoleId()));
	        		}
	        		
	        	}
	        	
	            
	           	
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
        return recordID;
		
	}


public static int updateRoleById(String roleId,boolean isActive,Session session){
		
    	//ResultSet rs=null;

    	int recordID=-1;
    	  Calendar currentDate = Calendar.getInstance();
          SimpleDateFormat formatter= 
          	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
          	  String dateNow = Util.getCurrentTimestamp(currentDate.getTime());  //formatter.format(currentDate.getTime());
	       
	                                
	        try
	        {
    			 String inssql="Update  empInfoDB.role set " +
                 "isActive=?,"+
                 "updateDate=?" +
                 " where key = ?";
    			// System.out.println(" sql is " + inssql);
    			 com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
    		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
    		    	session.execute(boundStatement.bind((isActive==true?1:0),dateNow,roleId));
	        		
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
        return recordID;
		
	}
public static int deleteRole1(String key,Session session){
		
    	//ResultSet rs=null;

    	int recordID=-1;
	                                
	        try
	        {
	        	String _sql="delete from  empInfoDB.role where key= ?"; 
		    	//System.out.println(" sql for role is " + _sql);
	        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	session.execute(boundStatement.bind(key));
		       	recordID=1;
	        }
	        catch(Exception se)
	        {
	        	//transactionRollback(session);
	            System.out.println("Insertion Error");
	            se.printStackTrace();
	            recordID=0;
	        }
    	
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return recordID;
		
	}

	public static List<Role> getAllRoleTypesByDepartment(Session session, String empId){
	
		Role role=null;
		List<Role> list=new ArrayList<Role>();
		
		String _sql="select " + 
					
					"key," +
					"id," +
					"role,isActive,createdDate,updateDate "+
					" from  empInfoDB.role where 	id = ? and isActive=1"; //and isActive=1";
	    try{
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(empId));
	        for(Row rs:rss)
	    	{
		    		role=new Role();
		    		role.setRoleId(rs.getString(0));
		    		role.setEmpId(rs.getString(1));
		    		role.setRole(rs.getString(2));
		    		role.setActive((rs.getInt(3)==1?true:false));
		    		role.setCreatedDate(rs.getString(4));
		    		role.setUpdatedDate(rs.getString(5));
		    		list.add(role);
	    	}
	    }
	    catch(Exception se)
	    {
	    	role=null;
	        se.printStackTrace();
	    }
	    finally
	    {
	    }
	    return list;
	}
	
	public static List<Role> getAllRoleTypesByDepartmentFull(Session session, String empId){
		
		Role role=null;
		List<Role> list=new ArrayList<Role>();
		
		String _sql="select " + 
					
					"key," +
					"id," +
					"role,isActive,createdDate,updateDate "+
					" from  empInfoDB.role where 	id = ?"; //and isActive=1";
	    try{
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(empId));
	        for(Row rs:rss)
	    	{
		    		role=new Role();
		    		role.setRoleId(rs.getString(0));
		    		role.setEmpId(rs.getString(1));
		    		role.setRole(rs.getString(2));
		    		role.setActive((rs.getInt(3)==1?true:false));
		    		role.setCreatedDate(rs.getString(4));
		    		role.setUpdatedDate(rs.getString(5));
		    		list.add(role);
	    	}
	    }
	    catch(Exception se)
	    {
	    	role=null;
	        se.printStackTrace();
	    }
	    finally
	    {
	    }
	    return list;
	}
	public static int updateUIRoles(String roleName,boolean isActive,Session session){
			
	    	ResultSet rs=null;
	    	 Calendar currentDate = Calendar.getInstance();
	            SimpleDateFormat formatter= 
	            	  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
	            	  String dateNow = Util.getCurrentTimestamp(currentDate.getTime());  //formatter.format(currentDate.getTime());
	    	int recordID=-1;
	    	
		   
		                                
		        try
		        {
		        	List<Role> roles= getAllRolesByRoleName(session,roleName);
		        	//System.out.println(" hi here is count " + roles.size());
		        	for(Role r:roles){
			            String inssql="Update  empInfoDB.role set " +
	                    "isActive=?,"+
	                    "updateDate=?" +
	                    " where key=?";
			        	//System.out.println(" insert is  " + inssql);
			            com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
				    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
				    	session.execute(boundStatement.bind((isActive==true?1:0),dateNow,r.getRoleId()));
		        	}
		        	
		        	
		           // session.commit();
		
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
	        return recordID;
			
		}

	public static List<Role> getAllRolesByRoleName(Session session, String roleName){
		
		Role role=null;
		List<Role> list=new ArrayList<Role>();
		
		String _sql="select " + 
					
					"key," +
					"id," +
					"role,isActive,createdDate,updateDate "+
					" from  empInfoDB.role where 	role=?"; //and isActive=1";
	    try{
	    	//System.out.println(" insnsql " + _sql);
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(roleName.toLowerCase()));
	        for(Row rs:rss)
	    	{
	    		role=new Role();
	    		role.setRoleId(rs.getString(0));
	    		role.setEmpId(rs.getString(1));
	    		role.setRole(rs.getString(2));
	    		role.setActive((rs.getInt(3)==1?true:false));
	    		role.setCreatedDate(rs.getString(4));
	    		role.setUpdatedDate(rs.getString(5));
	    		list.add(role);
	    	}
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	    	role=null;
	        se.printStackTrace();
	    }
	    finally
	    {
	    }
	    return list;
	}
//	
//public static List<Role> getAllUn(Session session, String roleName){
//		
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		Role role=null;
//		List<Role> list=new ArrayList<Role>();
//		
//		String _sql="select " + 
//					
//					"key," +
//					"id," +
//					"role,isActive,createdDate,updateDate "+
//					" from role where 	role= '"+roleName.toLowerCase()+"'"; //and isActive=1";
//	    try{
//	    	//System.out.println(" insnsql " + _sql);
//	       	ps = session.prepareStatement(_sql);
//	    	rs = ps.executeQuery();
//	    	while(rs.next())
//	    	{
//	    		role=new Role();
//	    		role.setRoleId(rs.getString(1));
//	    		role.setEmpId(rs.getString(2));
//	    		role.setRole(rs.getString(3));
//	    		role.setActive((rs.getInt(4)==1?true:false));
//	    		role.setCreatedDate(rs.getString(5));
//	    		role.setUpdatedDate(rs.getString(6));
//	    		list.add(role);
//	    	}
//	    }
//	    catch(SQLException se)
//	    {
//	    	//transactionRollback(session);
//	    	role=null;
//	        se.printStackTrace();
//	    }
//	    finally
//	    {
//	    	CloseSessions(ps, rs);
//	    }
//	    return list;
//	}
}
