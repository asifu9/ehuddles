package com.pro.emp;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;

public class CassandraDB {
	private static Session session=null;
	private static CassandraDB ob=null;
	private CassandraDB(){
		 try {
				PoolingOptions pools = new PoolingOptions();
		        pools.setCoreConnectionsPerHost(HostDistance.LOCAL, 2);
		       pools.setMaxConnectionsPerHost(HostDistance.LOCAL, 1);
		        pools.setCoreConnectionsPerHost(HostDistance.REMOTE, 8);
		        pools.setMaxConnectionsPerHost(HostDistance.REMOTE, 2);
				
		        
		        Cluster cluster = new Cluster.Builder()
		        .addContactPoints(String.valueOf("127.0.0.1"))
		        .withPoolingOptions(pools)
		         .withSocketOptions(new SocketOptions().setTcpNoDelay(true))
		        .build();
		       
				//if (options.has("compression"))
				//cluster.getConfiguration().getProtocolOptions().setCompression(ProtocolOptions.Compression.SNAPPY);
				 session = cluster.connect();
				 session.execute("USE empInfoDB");
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static Session getCassConnection(){
		if(ob==null)
			ob=new CassandraDB();
		return session;
		
		

	}
	
	public static void freeConnection(Session con){
		
	}
}
