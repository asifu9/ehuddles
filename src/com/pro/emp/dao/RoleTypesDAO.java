package com.pro.emp.dao;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.domain.RoleTypes;

public class RoleTypesDAO extends EmpCommanDAO{

	
	 public static List<RoleTypes> getAllRoleTypesByDepartment(Session session, String department){

	    	RoleTypes roleTypes=null;
	    	List<RoleTypes> list=new ArrayList<RoleTypes>();
	    	
	    	String _sql="select " + 
	    				"key," +
	    				"name," +
	    				"department"+
	    				" from roletypes where 	department = ?";
	        try{
	        	//System.out.println(" SQL in reoletypes " + _sql);
	        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
		    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
		    	ResultSet rss=session.execute(boundStatement.bind(department));
		        for(Row rs:rss)
	        	{
	        		roleTypes=new RoleTypes();
	        		roleTypes.setId(rs.getString(0));
	        		roleTypes.setName(rs.getString(1));
	        		roleTypes.setDepartment(rs.getString(2));
	        		list.add(roleTypes);
	        	}
	        }
	        catch(Exception se)
	        {
	        	//transactionRollback(con);
	        	//roleTypes=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return list;
	    }
}
