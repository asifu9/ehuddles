package com.pro.emp.dao;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmpSortByEmpID;
import com.pro.emp.Util;
import com.pro.emp.domain.Attendance;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.emp.util.PasswordService;

public class EmpInfoDAO extends EmpCommanDAO{


	/**
	 * Method to create new employee information
	 * @param emp
	 * @return
	 */
//	public static String createEmpInfo(EmpInfo emp,Session session){
//		
//    	UUID id= java.util.UUID.randomUUID();
//    	java.sql.Date dt= new Date(emp.getDob().getTime());
//        String inssql="INSERT INTO empInfoDB.emp_info(" +
//        						"key,"+
//                                "empid," +
//                                "empName, "+
//                                "password,"+
//                                "DOB," +
//                                "department," +
//                                "designation," +
//                                "dateofjoining," +
//                                "creationDate,imagePath,emailid,loginName,isCalendarReview,mobile,gender,indicator"+
//                                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" +
//                                "('"+id.toString()+"','"+emp.getEmpid()+
//                                "','"+emp.getEmpName()+"','"+emp.getPassWord()+
//                                "','"+emp.getDob()+
//                                "','"+emp.getDepartment()+
//                                "','"+emp.getDesignation()+
//                                "','"+Util.getCurrentTimestamp(emp.getDoj())+
//                                "','"+Util.getCurrentTimestamp(dt)+
//                                "','"+emp.getImagePath()+
//                                "','"+emp.getEmailid()+
//                                "','"+emp.getLoginName()+"',0,'"+emp.getMobile()+"','"+emp.getGender()+"',1)";
//        try
//        {
//          
//        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
//	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
//	    	ResultSet rss=session.execute(boundStatement.bind(
//	    			id,
//	    			emp.getEmpid(),
//	    			emp.getEmpName(),
//	    			emp.getPassWord(),
//	    			emp.getDob(),
//	    			emp.getDepartment(),
//	    			emp.getDesignation(),
//	    			Util.getCurrentTimestamp(emp.getDoj()),
//	    			Util.getCurrentTimestamp(dt),
//	    			emp.getImagePath(),
//	    			emp.getEmailid(),
//	    			emp.getLoginName(),
//	    			0,
//	    			emp.getMobile(),
//	    			emp.getGender(),
//	    			1
//	    			));
//           
//           // session.commit();
//
//        }
//        catch(Exception se)
//        {
//        	//transactionRollback(session);
//            System.out.println("Insertion Error");
//            se.printStackTrace();
//        }
//        finally
//        {
//        	//CloseSessions(ps, rs);
//        }
//        return ""+id;
//		
//	}
	
	/**
	 * Method to create new employee information
	 * @param emp
	 * @return
	 */
	public static EmpInfo createNewEmpInfo(EmpInfo emp,Session session){
		
    	//System.out.println(" emp " + emp.getEmpid() + " : "+emp.getLoginName()+ " : " + emp.getPassWord());
    	UUID id= java.util.UUID.randomUUID();
        String inssql="INSERT INTO empInfoDB.emp_info(" +
        						"key,"+
        						"empName,"+        						
                                "password,"+
                                "loginName,"+
                                "mobile,gender,indicator,creationDate,department,designation"+
                                ") VALUES(?,?,?,?,?,?,?,?,?,?)";// +
//                                "'"+id+
//                                "','"+emp.getEmpName()+                               
//                                "','"+emp.getPassWord()+
//                                "','"+emp.getLoginName()+
//                                "','"+emp.getMobile()+
//                                "','"+emp.getGender()+"',1)";
        try
        {
        	//System.out.println(" insqql " + inssql);
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			id.toString(),
	    			emp.getEmpName(),
	    			emp.getPassWord(),
	    			emp.getLoginName(),
	    			emp.getMobile(),
	    			emp.getGender(),
	    			1,
	    			Util.getCurrentTimestamp(),
	    			emp.getDepartment(),
	    			emp.getDesignation()
	    			));
	    	
	    	
            emp.setId(id.toString());
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            System.out.println("Insertion Error");
            se.printStackTrace();
        }
        finally
        {
        	
        }
        return emp;
		
	}
	 /**
     * 
     * This returns true or false to indedicate if the record was deleted or not.
     * @@param Session Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean deleteEmp(Session session, EmpInfo emp)
    {
    	boolean result=false;
    	String _sql="DELETE FROM empInfoDB.emp_info WHERE key = '"+emp.getId()+"'";
        try
        {
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(emp.getId()));
        	result=true;
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
            result=false;
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
       return result;
    }
    
    /**
     * 
     * This will return true to indicate if the update was successful or not. It Updates all record except Id, Creation time & update time 
     * @@param Session Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean update(Session session, EmpInfo emp)
    {
    	boolean result=false;
    	String _sql="UPDATE empInfoDB.emp_info SET " + 
    			"empId=?,"+
				"empName = ?," +
                "dob = ?,"+
                "department =?," +
                "designation = ?," +
                "doj = ?," +
                "emailId=?,"+
                "password=?,"+
                "loginName=?"+
				" WHERE indicator=1 and key = ? and creationDate=?";
//    	
//    				"empId='"+emp.getEmpid()+"',"+
//    				"empName = '"+emp.getEmpName()+"'," +
//                    "dob = '"+Util.getCurrentTimestamp(emp.getDob())+"', "+
//                    "department = '"+emp.getDepartment()+"'," +
//                    "designation = '"+emp.getDesignation()+"'," +
//                    "doj = '"+Util.getCurrentTimestamp(emp.getDoj())+"', " +
//                    "emailId='"+emp.getEmailid()+"', "+
//                    "password='"+emp.getPassWord()+"', "+
//                    "loginName='"+emp.getLoginName()+"' "+
//    				" WHERE key = '"+emp.getId()+"'";
        try
        {
        	PasswordService ps=new PasswordService();
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(
	    			emp.getEmpid(),
	    			emp.getEmpName(),
	    			emp.getDob(),
	    			emp.getDepartment(),
	    			emp.getDesignation(),
	    			emp.getDoj(),
	    			emp.getEmailid(),
	    			ps.encrypt(emp.getPassWord()),
	    					
	    			emp.getLoginName(),
	    			emp.getId(),
	    			emp.getCreationDate()
	    			));
	    	
        	//System.out.println(" SQL is " + _sql);
        	 result=true;
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	result=false;
             se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
      
        	return result;
        
    }

    
    /**
     * 
     * This will return true to indicate if the update was successful or not. It Updates all record except Id, Creation time & update time 
     * @@param Session Object
     * @@param docClient Obj
     * @@return true/false
     */
    public static boolean updateImagePath(Session session, EmpInfo emp)
    {
    	
    	String _sql="UPDATE empInfoDB.emp_info SET " + 
    				"imagePath=?" +
    				" WHERE indicator=1 and key =? and creationdate=?";
    	System.out.println(" sql is " + _sql);
    	boolean _result=false;
        try
        {
        	Calendar dd=Calendar.getInstance();
        	dd.setTimeInMillis(emp.getCreationDate().getTime());
        	
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(emp.getImagePath(),emp.getId(),dd.getTime()));
        	 _result=true;
        	 System.out.println(" process successsfully " + emp.getImagePath());
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	_result=false;
             se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
       
        	return _result;
        
    }
    public static boolean updateCoverPath(Session session, EmpInfo emp,java.util.Date d)
    {
    
    	System.out.println(" cover path " + emp.getCoverPath() + " emp id " + emp.getId());
    	String _sql="UPDATE empInfoDB.emp_info SET " + 
    				"coverPath=? " +
    				" WHERE indicator=1 and key = ? and creationdate=?";
    	//System.out.println(" sql is " + _sql);
    	boolean _result=false;
        try
        {
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(emp.getCoverPath(),emp.getId(),d));
        	 _result=true;
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	_result=false;
             se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
       
        	return _result;
        
    }
    
/*    public static void main(String args[]){
    	
    	getEmpById(CassandraDB.getCassConnection(),"59a456af-08b3-4b37-b33e-e1df3ed89471");
    }
    
    
    */
    
    public static void main(String args[]) throws  IOException{
    	//getEmpById
    	try {
    	PasswordService ps=new PasswordService();
    	 List<EmpInfo> info= getAllEmployeesInfo(CassandraDB.getCassConnection());
    	 for(EmpInfo i:info)
			
				System.out.println(" " + i.getId() + " : " +ps.decrypt(i.getPassWord()));
    	 
    	 EmpInfo infos= getEmpById("5ab9c7b4-8d32-46d6-bc6f-f3eaf537187a");
     	System.out.println(" ---- " +infos.getId() + " : " +ps.decrypt(infos.getPassWord()));
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    }
     /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */

    public static EmpInfo getEmpById( String id){
    	EmpInfo emp=null;

    	String _sql="select " + 
    				"key," +
    				"empId," +
    				"password,"+
                    "empName, "+
                    "dob," +
                    "department," +
                    "designation," +
                    "doj,imagePath,emailId,loginName,coverPath,creationDate" +
    				" from empInfoDB.emp_info where key = ?  ALLOW FILTERING";
        try{
        	//System.out.println(" sql in empById " + _sql);
        	Session session = CassandraDB.getCassConnection();
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	    	PasswordService ps=new PasswordService();
        	for(Row rs:rss)
        	{
        		emp=new EmpInfo();
        		emp.setId(rs.getString(0));
        		emp.setEmpid(rs.getString(1));
        		emp.setPassWord(ps.decrypt(rs.getString(2)));
        		emp.setEmpName(rs.getString(3));
        		emp.setDob(rs.getDate(4));
        		emp.setDepartment(rs.getString(5));
        		emp.setDesignation(rs.getString(6));
        		System.out.println(" employee department info " + rs.getString(5) + " : " + rs.getString(6));
        		emp.setDoj(rs.getDate(7));   
        		System.out.println(" image path id is " + rs.getString(8));
        		
        		if(rs.getString(8)!=null){
        			PhotoInfo iii=PhotoInfoDAO.getPhotoByIdDynamicName(rs.getString(8), session);
        			emp.setImage(iii);
        			if(iii!=null)
        			emp.setImagePath(iii.getPhotoPath());
        		}
        		emp.setLoginName(rs.getString(10));
        		emp.setEmailid(rs.getString(9));
        		
        		if(rs.getString(11)!=null){
        			PhotoInfo iii=PhotoInfoDAO.getPhotoByIdDynamicName(rs.getString(8), session);
        			emp.setCoverImage(iii);
        			if(iii!=null)
        			emp.setCoverPath(iii.getPhotoPath());
        		}
        		if(rs.getDate(12)!=null)
        			emp.setCreationDate(Util.convertDateToTimestamp(rs.getDate(12)));
        		System.out.println(" cover page in strin " + emp.getCreationDate());
//        		if(rs.getString(5)!=null){
//        		emp.setStrDepartment(DepartmentDAO.getDepartmentNameById(session, emp.getDepartment()));
//        		}
//        		if(rs.getString(6)!=null){
//        		emp.setStrDesignation(DesignationDAO.getDesgNameByDepAndId(session, emp.getDepartment(),emp.getDesignation()));
//        		}
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return emp;
    }

    
    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
    public static EmpInfo isEmpExists(Session session, String id,String password){

    	EmpInfo emp=null;
    	PasswordService ps=new PasswordService();
    	String _sql="select " + 
    				"key," +
    				//"empId," +
    				"password,"+
                    "empName, "+
                    "dob," +
                    "department," +
                    "designation," +
                    "doj" +
    				" from empInfoDB.emp_info where loginName = ? and password=? ALLOW FILTERING";
        try{
        	//System.out.println(" sql si " + _sql);
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id,ps.decrypt(password)));
        	
	    	
        	//System.out.println(" in isEmpExist " + _sql);
        	for(Row rs:rss)
        	{
	        		emp=new EmpInfo();
	        		emp.setId(rs.getString(0));
	        		//emp.setEmpid(rs.getString(2));
	        		emp.setPassWord(ps.decrypt(rs.getString(1)));
	        		emp.setEmpName(rs.getString(2));
	        		emp.setDob(rs.getDate(3));
	        		emp.setDepartment(rs.getString(4));
	        		emp.setDesignation(rs.getString(5));
	        		emp.setDoj(rs.getDate(6));      
	        	//emp.setStrDepartment(DepartmentDAO.getDepartmentNameById(session, emp.getDepartment()));
	        	//	emp.setStrDesignation(DesignationDAO.getDesgNameByDepAndId(session, emp.getDepartment(),emp.getDesignation()));
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return emp;
    }
    

    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
    public static List<EmpInfo> getAllEmployeesInfo(Session session){

    	EmpInfo emp=null;
    	List<EmpInfo> listEmp = new ArrayList<EmpInfo>();

    	String _sql="select " + 
    				"key," +
    				"empid," +
    				"password,"+
                    "empName, "+
                    "dob," +
                    "department," +
                    "designation," +
                    "doj,imagePath,emailId,loginName" +
    				" from empInfoDB.emp_info";
        try{
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
        	PasswordService ps=new PasswordService();
        	
        	for(Row rs:rss)
        	{
        		emp=new EmpInfo();
        		System.out.println(" iod si " + rs.getString(0));
        		emp.setId(rs.getString(0));
        		emp.setEmpid(rs.getString(1));
        		emp.setPassWord(ps.decrypt(rs.getString(2)));
        		emp.setEmpName(rs.getString(3));
        		
        		emp.setDob(rs.getDate(4));
        		emp.setDepartment(rs.getString(5));
        		if(rs.getString(5)!=null)
        			emp.setStrDepartment(new DepartmentDAO().getDepartmentNameById(rs.getString(5)));
        		emp.setDesignation(rs.getString(6));
        		if(rs.getString(6)!=null)
        			emp.setStrDesignation(new DesignationDAO().getDesgNameByKey(rs.getString(6)));
        		emp.setDoj(rs.getDate(7));  
        		emp.setImagePath(rs.getString(8));
        		if(emp.getImagePath()!=null)
        			emp.setImage(PhotoInfoDAO.getPhotoByIdDynamicName(emp.getImagePath(), session));
        		emp.setEmailid(rs.getString(9));
        		emp.setLoginName(rs.getString(10));
        	//	emp.setStrDepartment(DepartmentDAO.getDepartmentNameById(session, emp.getDepartment()));
        	//	emp.setStrDesignation(DesignationDAO.getDesgNameByDepAndId(session, emp.getDepartment(),emp.getDesignation()));
        		listEmp.add(emp);

        	}
        	
        	
        	Collections.sort(listEmp, new EmpSortByEmpID());
        
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return listEmp;
    }
    
    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
   
    /**
     * 
     * This will return true to indicate if the update was successful or not. It Updates all record except Id, Creation time & update time 
     * @@param Session Object
     * @@param docClient Obj
     * @@return true/false
     */
//    public static boolean updateRole(Session session, EmpInfo emp)
//    {
//    	PreparedStatement ps=null;
//    	ResultSet rs=null;
//    	
//    	String _sql="UPDATE empInfoDB.emp_info SET " + 
//    				"role=? " +
//    				" WHERE empid = ?";
//    	String _id = emp.getEmpid();
//    	int _result=0;
//        try
//        {
//        	 ps = session.prepareStatement(_sql);
//        	 ps.setString(1, emp.getRole());
//        	 ps.setString(2, _id);
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
    
    public static boolean isEmpidExists(Session session, String empid){

    	boolean result=false;
    	String _sql="select " + 
    				
    				"key " +
    				
    				" from empInfoDB.emp_info where empid = ?  ALLOW FILTERING";
        try{
           	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(empid));
        	for(Row rs:rss)
        	{
        		result=true;       		
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
            result=true;  
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return result;
    }
    
    public static boolean isLoginNameExists(Session session, String loginName){

    	boolean result=false;
    	String _sql="select " + 
    				"key" +
    				
    				" from empInfoDB.emp_info where loginName =?  ALLOW FILTERING";
        try{
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(loginName));
        	if(rss.all().size()>0)
        	{
        		result=true;     		
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
            result=true;
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return result;
    }


    public static boolean isLoginNameExistsInUpdate(Session session, String loginName,String key){

    	boolean result=false;
    	String _sql="select " + 
    				"key " +
    				
    				" from empInfoDB.emp_info where loginName =?  ALLOW FILTERING"; // and empid != ?";
        try{
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(loginName));
        	result=true; 
        	for(Row rs:rss)
        	{
        		//System.out.println(" rs.getString(1) " + rs.getString(1));
        		//System.out.println("key " + key);
        		if(rs.getString(0).toString().equalsIgnoreCase(key)){
        			result=false;
        		}
        		    		
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
            se.printStackTrace();
            result=false;
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return result;
    }


	/**
	 * Fetch client details by ID
	 * @@param DB Session object session
	 * @@param client id
	 * @@return docClient Obj
	 */
	public static List<EmpInfo> getEmpByName(Session session,String name){
	
		EmpInfo emp=null;
		List<EmpInfo> listEmp = new ArrayList<EmpInfo>();
	
		String _sql="select " + 
					"key," +
					"empid," +
					"password,"+
	                "empName, "+
	                "dob," +
	                "department," +
	                "designation," +
	                "doj,imagePath,emailId,loginName" +
					" from empInfoDB.emp_info";
	    try{
	      
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement);
        	
	    	PasswordService ps=new PasswordService();
        	
        		
	    	name = name.toLowerCase();
	    	for(Row rs:rss)
	    	{
	    		if(rs.getString(3)!=null){
	    		String mn=rs.getString(3).toLowerCase();
	    		if(mn.startsWith(name)){
		    		emp=new EmpInfo();
		    		emp.setId(rs.getString(0));
		    		emp.setEmpid(rs.getString(1));
		    		emp.setPassWord(ps.decrypt(rs.getString(2)));
		    		emp.setEmpName(rs.getString(3));
		    		emp.setDob(rs.getDate(4));
		    		emp.setDepartment(rs.getString(5));
		    		emp.setDesignation(rs.getString(6));
		    		emp.setDoj(rs.getDate(7));  
		    		emp.setImagePath(rs.getString(8));
		    		if(rs.getString(8)!=null)
		    			emp.setImage(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(0),rs.getString(8), session,true));
		    		emp.setEmailid(rs.getString(9));
		    		emp.setLoginName(rs.getString(10));
		    		//emp.setStrDepartment(DepartmentDAO.getDepartmentNameById(session, emp.getDepartment()));
	        		//emp.setStrDesignation(DesignationDAO.getDesgNameByDepAndId(session, emp.getDepartment(),emp.getDesignation()));
		    		listEmp.add(emp);
	    		}
	    		}
	    	}
	    	
	    	
	    	//Collections.sort(listEmp, new EmpSortByEmpID());
	    
	    }
	    catch(Exception se)
	    {
	    	//transactionRollback(session);
	    	//emp=null;
	        se.printStackTrace();
	    }
	    finally
	    {
	    	//CloseSessions(ps, rs);
	    }
	    return listEmp;
	}
	
	  /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
    public static EmpInfo getBasicEmpById(Session session, String id){

    	EmpInfo emp=null;
    	if(id==null)
    		return new EmpInfo();
    	String _sql="select " + 
    				"key," +
                    "empName, "+
                    "imagePath" +
    				" from empInfoDB.emp_info where key = ?  ALLOW FILTERING";
        try{
        	//System.out.println(" sql in empById " + _sql);
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
        	for(Row rs:rss)
        	{
        		emp=new EmpInfo();
        		emp.setId(rs.getString(0));
        		emp.setEmpName(rs.getString(1));
        		if(rs.getString(2)!=null){
        			emp.setImage(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(0),rs.getString(2), session,true));
        		}
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return emp;
    }
    
    public static EmpBasicInfo getBasicEmpById_(Session session, String id){

    	EmpBasicInfo emp=null;
    	if(id==null )
    		return null;
    	String _sql="select " + 
    				"key," +
                    "empName, "+
                    "imagePath,creationdate,department,designation,gender " +
    				" from empInfoDB.emp_info where key = ? ALLOW FILTERING";
        try{
        	
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	    	
        	for(Row rs:rss)
        	{
        		emp=new EmpBasicInfo();
        		emp.setId(rs.getString(0));
        		emp.setEmpName(rs.getString(1));
        		if(rs.getString(2)!=null)
        		emp.setImagePath(PhotoInfoDAO.getPhotoByIdDynamic(rs.getString(0),rs.getString(2), session,true));
        		emp.setCreationdate(rs.getDate(3));
        		if(rs.getString(4)!=null)
        			emp.setDepartment(new DepartmentDAO().getDepartmentNameById(rs.getString(4)));
        		if(rs.getString(5)!=null)
        			emp.setDesignation(new DesignationDAO().getDesgNameByKey(rs.getString(5)));
        		emp.setGender(rs.getString(6));
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	System.out.println(" ---------- ERROR in cache get ---------");
        	emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return emp;
    }
    
    /**
     * Fetch client details by ID
     * @@param DB Session object session
     * @@param client id
     * @@return docClient Obj
     */
    public static List<EmpInfo> getUpcomingBirthdays(Session session){

    	List<EmpInfo> empList=new ArrayList<EmpInfo>();

    	String _sql="select " + 
    				"key," +
                    "empName, "+
                    "imagePath,dob" +
    				" from empInfoDB.emp_info where indicator=?";
        try{
        	//System.out.println(" sql in empById " + _sql);
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(1));
        	for(Row rs:rss)
        	{
        		
        		EmpInfo emp=new EmpInfo();
        		EmpBasicInfo bInfo= CacheRecords.getInstance().getCacheData(rs.getString(0));
        		emp.setId(rs.getString(0));
        		emp.setEmpName(rs.getString(1));
        		System.out.println(" bInfo " + bInfo + " : " + bInfo.getImagePath());
        		if(bInfo.getImagePath()!=null)
        			emp.setImagePath(bInfo.getImagePath().getPhotoPath());
        		else
        			emp.setImagePath(null);
        		Calendar c= Calendar.getInstance();
        		Calendar current= Calendar.getInstance();
        		if(rs.getDate(3)!=null){
        			
        			
        			
			        		c.setTimeInMillis(rs.getDate(3).getTime());
			        		int birthMonth = c.get(Calendar.MONTH);
			        		int birthDay = c.get(Calendar.DATE);
			        		boolean isFutureYear=false;
			        		boolean isToday=false;
			        		if(birthMonth<current.get(Calendar.MONTH)){
			        			isFutureYear=true;	
			        		}
			        		if(birthMonth<=current.get(Calendar.MONTH) && birthDay <= current.get(Calendar.DATE)){
			        			isFutureYear=true;
			        		}
			        		if(birthMonth==current.get(Calendar.MONTH) && birthDay == current.get(Calendar.DATE)){
			        			isFutureYear=false;
			        			isToday=true;
			        		}
			        		emp.setTodayBirth(isToday);
			        		Calendar ddd=Calendar.getInstance();
			        		ddd.set(Calendar.MONTH, birthMonth);
			        		ddd.set(Calendar.DAY_OF_MONTH,birthDay);
			        		if(isFutureYear)
			        			ddd.set(Calendar.YEAR,current.get(Calendar.YEAR)+1);
			        		else
			        			ddd.set(Calendar.YEAR,current.get(Calendar.YEAR));
			        		emp.setDobForSort(ddd.getTime());
			        		emp.setDobStr(Util.getBirthday(Util.convertDateToTimestamp(rs.getDate(3))));
			        		emp.setDob(c.getTime());
			        		empList.add(emp);
        			}
        		
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	//emp=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return empList;
    }
}