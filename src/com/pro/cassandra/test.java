package com.pro.cassandra;

import java.io.File;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.datastax.driver.core.querybuilder.QueryBuilder;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		    PoolingOptions pools = new PoolingOptions();
	        pools.setMaxSimultaneousRequestsPerConnectionThreshold(HostDistance.LOCAL, 10);
	        pools.setCoreConnectionsPerHost(HostDistance.LOCAL, 10);
	        pools.setMaxConnectionsPerHost(HostDistance.LOCAL, 10);
	        pools.setCoreConnectionsPerHost(HostDistance.REMOTE, 10);
	        pools.setMaxConnectionsPerHost(HostDistance.REMOTE, 10);
			
	        
	        Cluster cluster = new Cluster.Builder()
            .addContactPoints(String.valueOf("127.0.0.1"))
            .withPoolingOptions(pools)
             .withSocketOptions(new SocketOptions().setTcpNoDelay(true))
            .build();
			
			//if (options.has("compression"))
			//cluster.getConfiguration().getProtocolOptions().setCompression(ProtocolOptions.Compression.SNAPPY);
			Session session = cluster.connect();
           //session.execute("CREATE KEYSPACE mytest WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");

            session.execute("USE mytest");
            
	
			/* String sql="CREATE TABLE mytable ("+
		    		   "KEY uuid PRIMARY KEY,"+
		    		   "itemId text,"+
		    		   "mynum text,"+
		    		   "indicator int,"+
		    		   "likedDate timestamp )";
			 session.execute(sql);*/
            UUID id= UUID.randomUUID();
		     String insetQ="insert into ks(k,v1,v2,i) values('555',22,22,22);";
		  //  System.out.println(" query " + insetQ);
		    session.execute(insetQ);
		    // System.out.println("Done");
		    String qu = "select k,v1,v2,i from ks where k='555'  order by v2 desc";
		    ResultSet rs= session.execute(qu);
		     List<Row> l= rs.all();
		     //		        + "    PRIMARY KEY (indicator,KEY,postedTime)\n"
		        //+ ") WITH CLUSTERING ORDER BY (KEY DESC,postedTime DESC)\n"
		     /*String mytables = "CREATE TABLE ks (\n"
	                    + "    k text,\n"
	                    + "    v1 int,\n"
	                    + "    v2 int,\n"
	                    + "    i int,\n"
	                    + "    PRIMARY KEY (k, v1, v2)\n"
	                    + ") WITH CLUSTERING ORDER BY (v1 DESC, v2 ASC)\n"
	                    + "   AND read_repair_chance = 0.5\n"
	                    + "   AND dclocal_read_repair_chance = 0.6\n"
	                    + "   AND replicate_on_write = true\n"
	                    + "   AND gc_grace_seconds = 42\n"
	                    + "   AND bloom_filter_fp_chance = 0.01\n"
	                    + "   AND caching = 'ALL'\n"
	                    + "   AND comment = 'My awesome table'\n"
	                    + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
	                    + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";

		     session.execute(mytables);*/
		     for(Row r:l){
		    	 
		   	 System.out.println(" key " + r.getString(0)+ " : "+ r.getInt(1) + " : " + r.getInt(2));
		    }
		     
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
