package com.pro.emp;


import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;


public class ConnectionPool
{
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    public synchronized static ConnectionPool getInstance()
    {
        if (pool == null)
        {
            pool = new ConnectionPool();
        }
        return pool;
    }

//    private ConnectionPool()
//    {
//        try
//        {
//            InitialContext ic = new InitialContext();
//            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/sanahempinfo");
//            
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

//    public Connection getConnection()
//    {
//        try
//        {
//            Connection conn= dataSource.getConnection();
//            conn.setAutoCommit(false);
//            
//            return conn;
//        }
//        catch (SQLException sqle)
//        {
//            sqle.printStackTrace();
//            return null;
//        }
//    }
   
    public void freeConnection(Connection c)
    {
        try
        {
        	if(c!=null){
        		c.close();
        	}
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
    
    public void test(){


    }
}
