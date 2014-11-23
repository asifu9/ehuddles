package com.pro.emp.message.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.EmpCommanDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.MessageDetails;

public class MessageDetailsDAO extends EmpCommanDAO{

	

	public static String createMessageDetails( MessageDetails message,Session session) 
	{
		Calendar currentDate = Calendar.getInstance();
		 int result=-1;
       	  java.sql.Date dt= new java.sql.Date(currentDate.getTimeInMillis());
       	  dt.setTime(currentDate.getTime().getTime());
       	  Timestamp tm=new Timestamp(currentDate.getTimeInMillis());
       	  String ss=""+tm;
       	  ss=ss.substring(0,19);
		 UUID id= java.util.UUID.randomUUID();
	    String inssql="INSERT INTO empInfoDB.mailDetails(key," +
	    						"subject,"+
	                            "message," +
	                            "mailTime,indicator,ishidden "+
	                            ") values(?,?,?,?,?,?)";// +
	                           // "(\'"+id+"\',\'"+message.getSubject()+"\',\'"+message.getMessage()+"\',
	    						//\'"+ss+"\',1,0)";
	    try
	    {
	    	
    		
	    	com.datastax.driver.core.PreparedStatement statement =  session.prepare(inssql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	session.execute(boundStatement.bind(id.toString(),
	    				message.getSubject(),
	    				message.getMessage(),
	    				Calendar.getInstance().getTime(),
	    				1,
	    				0));
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
	
public static void main(String aerg[]){
	/* sql in empById 894eac93-15c8-467a-9ba9-ebe3cba90a9b
	 userId  abf5faab-ac39-406b-9ddf-3baa8e78873e : abf5faab-ac39-406b-9ddf-3baa8e78873e
	  sql in empById f3390655-42fe-4008-98e6-919dcae446f1
	 userId  abf5faab-ac39-406b-9ddf-3baa8e78873e : abf5faab-ac39-406b-9ddf-3baa8e78873e
	  sql in empById 15bc9237-695b-4f23-a6ba-40ea1796d21d*/
	  getMessagesById(CassandraDB.getCassConnection(),"894eac93-15c8-467a-9ba9-ebe3cba90a9b");
}
    public static MessageDetails getMessagesById(Session session, String id){

    	MessageDetails message=null;

    	String _sql="select " + 
    				"key," +
                    "message, "+
                    "ishidden" +
    				" from empInfoDB.mailDetails where key =? and indicator=1  ALLOW FILTERING";
        try{
        	System.out.println(" sql in empById " + id);
        	com.datastax.driver.core.PreparedStatement statement =  session.prepare(_sql);
	    	BoundStatement boundStatement = new BoundStatement((com.datastax.driver.core.PreparedStatement) statement);
	    	ResultSet rss=session.execute(boundStatement.bind(id));
	        for(Row rs:rss)
        	{
        		try{
        			
        		message=new MessageDetails();
        		message.setKey(rs.getString(0));
        		message.setMessage(rs.getString(1));
        		System.out.println(" ok here i am " + rs.getString(1));
        		}
        		catch(Exception ex){
        			ex.printStackTrace();
        			message=null;
        		}
        		
        	}
        }
        catch(Exception se)
        {
        	//transactionRollback(session);
        	message=null;
            se.printStackTrace();
        }
        finally
        {
        	//CloseSessions(ps, rs);
        }
        return message;
    }
}
