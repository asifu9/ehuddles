package com.pro.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.pro.emp.domain.CompanyInfo;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpIdproofInfo;



public class EmpCompanyInfoDAO extends EmpCommanDAO{/*

	
public static int createCompanyInfo(EmpCompanyInfo info,String empid,Connection con){
		
		PreparedStatement ps=null;
    	ResultSet rs=null;
    	int result=0;
    	UUID id= java.util.UUID.randomUUID();
    	try
        {
    		
    		
	        String inssql="INSERT INTO emp_company_info(" +
	        						"key,"+
	                                "empId," +
	                                "companyId) values ('"+id+"','"+empid+"','"+info.getCompanyId()+"')";
	        //System.out.println(" company info " + inssql);
            ps=con.prepareStatement(inssql);
           // System.out.println(" inssql sql " + inssql);
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
	
	public static int deleteAllCompanyForId(Connection con, String key){
		PreparedStatement ps=null;
    	int result=0;
    	
    	 try{
    		 Set<EmpCompanyInfo> set= EmpCompanyInfoDAO.getEmpCompanyInfo(con, key);
    		 for(EmpCompanyInfo f:set){
    			 String _sql="delete from emp_company_info where key='"+f.getId()+"'";
	           	ps = con.prepareStatement(_sql);
	           	ps.executeUpdate();
    		 	}
	        	result=1;
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	            se.printStackTrace();
	            result=0;
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
    	return result;
	}
	
	 public static Set<EmpCompanyInfo> getEmpCompanyInfo(Connection con, String id){

		 	//System.out.println(" Id is " + id);
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	EmpCompanyInfo emp=null;
	    	Set<EmpCompanyInfo> empList=new HashSet<EmpCompanyInfo>();
	    	String sql = " select key, empId,companyId from emp_company_info where empId='"+id+"'";
	    	

	    	
	        try{
	           	ps = con.prepareStatement(sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		//System.out.println(" EmpCompanyInfo " + rs.getString(3));
	        		emp=new EmpCompanyInfo();
	        		emp.setId(rs.getString(1));		
	        		emp.setEmpId(rs.getString(2));
	        		emp.setCompanyId(rs.getString(3));
	        		emp.setCompanyImage(getCompanyName(con, rs.getString(3)));
	        		
	        		//System.out.println(" added company " + emp.getCompanyImage());
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
	 
	 
	 public static String getCompanyName(Connection con,String companyId){


	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	//EmpCompanyInfo emp=null;
	    	//Set<EmpCompanyInfo> empList=new HashSet<EmpCompanyInfo>();
	    	String sql = " select key, companyImage from companyinfo where key='"+companyId+"'";
	    	String name="";
	    	
//	    	String _sql="select " + 
//	    				"emp_company_info.id," +
//	    				"emp_company_info.Empid," +
//	                    "emp_company_info.companyId, "+
//	                    "companyinfo.companyImage" +
//	    				" from emp_company_info,companyinfo where emp_company_info.companyId=companyInfo.id "+
//	    				" and emp_company_info.empId=?";
	    	
	        try{
	           	ps = con.prepareStatement(sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		name =rs.getString(2);
	        	}
	        }
	        catch(SQLException se)
	        {
	        	//transactionRollback(con);
	        	//emp=null;
	            se.printStackTrace();
	        }
	        finally
	        {
	        	//CloseConnections(ps, rs);
	        }
	        return name;
	    
	 }
	 

	 public static Set<CompanyInfo> getAllCompanyInfo(Connection con){

		 	
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	CompanyInfo emp=null;
	    	Set<CompanyInfo> empList=new HashSet<CompanyInfo>();
//	    	private String id;
//	    	private String empId;
//	    	private String companyId;
//	    	private String companyImage;
	    	String sql = " select key, companyName,companyURL,companyImage from companyinfo";
	    	
	    	
//	    	String _sql="select " + 
//	    				"emp_company_info.id," +
//	    				"emp_company_info.Empid," +
//	                    "emp_company_info.companyId, "+
//	                    "companyinfo.companyImage" +
//	    				" from emp_company_info,companyinfo where emp_company_info.companyId=companyInfo.id "+
//	    				" and emp_company_info.empId=?";
	    	
	        try{
	           	ps = con.prepareStatement(sql);
	        	rs = ps.executeQuery();
	        	while(rs.next())
	        	{
	        		emp=new CompanyInfo();
	        		emp.setKey(rs.getString(1));		
	        		emp.setCompanyName(rs.getString(2));
	        		emp.setCompanyURL(rs.getString(3));
	        		emp.setCompanyImage(rs.getString(4));
	        		//System.out.println(" added company " + emp.getCompanyImage());
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
*/}
