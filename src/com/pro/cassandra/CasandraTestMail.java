package com.pro.cassandra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.cassandra.utils.UUIDGen;

import com.pro.emp.Util;
import com.pro.emp.util.Constant;

public class CasandraTestMail {

	/**
	 * @param args
	 */
	public static String URL="jdbc:cassandra://127.0.0.1:9160/sanahempinfo";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new CasandraTest().insertTable();
//		new CasandraTest().createIndex();
//		new CasandraTest().selectTable();
		CasandraTestMail t=new CasandraTestMail();
//		t.createDBTable();

	}
	
	
	public void createMailTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			
	    Connection con = DriverManager.getConnection(URL);
	    //System.out.println("2");
	    String query = "DROP TABLE liketable";
	    String sql="CREATE TABLE liketable ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "itemId text,"+
	    		   "likedUserId text,"+
	    		   "likedDate timestamp )";
	    try{
	    PreparedStatement statement1 = con.prepareStatement(query);
	  
	    statement1.executeUpdate();
	    statement1.close();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    String sql2="CREATE INDEX itemId on  liketable (itemId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    String sql3="CREATE INDEX likedUserId on  liketable (likedUserId);";
	    PreparedStatement statement4 = con.prepareStatement(sql3);
	    statement4.executeUpdate();
	    statement2.close();
	    statement4.close();
	    
	    statement.close();
	    con.close();
	    System.out.println(" dione");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
