package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpCommanDAO {

		
	    /**
	     * Used to close & release connection objects
	     * @@param PreparedStatment Obj
	     * @@param ResultSet Obj
	     */
	    public static void CloseConnections(PreparedStatement ps, ResultSet rs)
	    {
		//System.out.println("******Releasing Connection now....******");
			 //Result Set Object Exception Handling
	    	try
	        {
	            if(rs!=null)
	            {
	                rs.close();
	                rs=null;
	            }
	                                     
	        }
	        catch(SQLException se)
	        {
	            System.out.println("******Result Set Obj Closing Error******");
	            se.printStackTrace();
	        } 
	        
	        //Prepared Statement Object Exception Handling
	        try{
	        	if(ps!=null)
	            {
	            	ps.close();
	            	ps=null;
	            }
	        }
	        catch(SQLException se)
	        {
	        	System.out.println("******Prepared Statement Closing Error******");
	            se.printStackTrace();
	        } 
	        
	        //System.out.println("******Released all Connections..******");
	    }
	    /**
	     * Used to close & release Server connection object
	     * @@param Connection Obj
	     */
	    public static void CloseServerConnection(Connection con){
	    //Connection Object Exception Handling
	      //  System.out.println("******Releasing server Conn Obj.....******");
	        try{
	        	if(con!=null)
	            {
	                con.close();
	                con=null;
	            }  
	        }
	        catch(SQLException se)
	        {
	            System.out.println("******Connecetion Object Closing Error******");
	            se.printStackTrace();
	        } 
	       // System.out.println("******Server Conn Obj Released.******");
	    }
	    
	    /**
	     * Used to close & release connection objects
	     * @@param Statement Obj
	     * @@param ResultSet Obj
	     */
	    public static void CloseStmtConnection(Statement stmt, ResultSet rs)
	    {
		//System.out.println("*****Releasing Connection now....*****");
			 //Result Set Object Exception Handling
	    	try
	        {
	            if(rs!=null)
	            {
	                rs.close();
	                rs=null;
	            }
	                                     
	        }
	        catch(SQLException se)
	        {
	            System.out.println("*****Result Set Obj Closing Error******");
	            se.printStackTrace();
	        } 
	        
	        //Statement Object Exception Handling
	        try{
	            if(stmt!=null)
	            {
	            	stmt.close();
	            	stmt=null;
	            }
	        }
	        catch(SQLException se)
	        {
	        	System.out.println("*****Statement Obj Closing Error****");
	            se.printStackTrace();
	        } 
	        
	      //  System.out.println("***Released all Connections..****");
	    }   
	    
	    
	    public static void transactionRollback(Connection con){
	    	try {
	    		if(con!=null)
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }

}
