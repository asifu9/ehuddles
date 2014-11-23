package com.pro.cassandra;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.pro.emp.Util;
import com.pro.emp.util.PasswordService;

public class CasandraTest {

	static Session session=null;
	/**
	 * @param args
	 */
	public static String URL="jdbc:cassandra://172.16.136.10:9160/sanahempinfo";
	public static void main(String[] args) throws Exception {
		
		
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
		 session = cluster.connect();
//		 session.execute("CREATE KEYSPACE empInfoDB WITH replication " + 
//       	      "= {'class':'SimpleStrategy', 'replication_factor':3};");
		 session.execute("USE empInfoDB");
//		Calendar start=Calendar.getInstance();
//		Timestamp t=new Timestamp(start.getTimeInMillis());
//		Calendar end=Calendar.getInstance();
//		start.add(Calendar.HOUR_OF_DAY, -5);
//		System.out.println(" start.get(Calendar.HOUR) " + start.get(Calendar.HOUR_OF_DAY));
//		//Timestamp t=new Timestamp(start.getTimeInMillis());
//		Timestamp t2=new Timestamp(start.getTimeInMillis());
//		String s1=""+t;
//		String s2= ""+t2;
		
//		String tttt=s1.substring(0, 19);
//		System.out.println(" t " + tttt + " ");
//		if(s1.contains("."))System.out.println(" yes");
//		//String s11[]=s1.split(".");
//		//System.out.println(" yes size"  + s11.length + " :  " + s11);
//		System.out.println(" t " + s1 + " : " + s2);
//		s2=s2.split(".")[0];
//		System.out.println(" t " + s1 + " : " + s2);
//		
		
		// TODO Auto-generated method stub
//		new CasandraTest().insertTable();
//		new CasandraTest().createIndex();
//		new CasandraTest().createDBTable();
//		new CasandraTest().createPhotoComments();
	//		new CasandraTest().createMessageNotification();
			/*new CasandraTest().createMailTable();
		new CasandraTest().createMailLinkTable();
		new CasandraTest().createLikeNotification();*/
		//	new CasandraTest().createTickerInfo();
		// new CasandraTest().createFollowerTable();
		// new CasandraTest().createMailTable();
		// new CasandraTest().createMailLinkTable();
		//				new CasandraTest().createPostComments();
		///new CasandraTest().createPostLikeTable();
	//	new CasandraTest().createBulletinInfo();
	//		new CasandraTest().createPost();
			/*				new CasandraTest().createVideoInfo();
		new CasandraTest().createEduucationDetails();
		new CasandraTest().createProfessionDetail();
		new CasandraTest().createRole();
		new CasandraTest().createEmpAddress();*/
		//new CasandraTest().createEmpInfo();
		//new CasandraTest().createLinksInfo();
		 new CasandraTest().createTagInfo();
	//	new CasandraTest().createPhotoAlbum();
//	new CasandraTest().createPhotoInfo();
	/*		new CasandraTest().createPhotoComments();*/
	//	new CasandraTest().createLiketable();
		
		
		
	//	new CasandraTest().updateRow();
	//	CasandraTest t=new CasandraTest();
	//	t.recreateIndexForPhotoInfo();
	//	new CasandraTest().createDBTable();
//		t.createPhotoComments();
	//	t.createPhotoInfo();
	//	t.createPhotoAlbum();
//		t.createEmpAddress();
		//t.createEmpInfo();
	//	t.createRoleTypes();
	//	new CasandraTest().createDepartmentStatic();
//		t.createRoleTypes();
//		t.createDBTable();
//		t.createPhotoComments();
	//	t.createPhotoAlbum();
//		t.createPhotoInfo();
//		t.createPhotoComments();
//	t.createDepartmentStatic();
//		new CasandraTest().createDesignationStatic();
//		t.createEmpExtraActivity();
//		t.createEmpIdProof();
//		t.createEduucationDetails();
//		t.createProfessionDetail();
//		t.createRole();
//		t.createCompanyInfoStatic();
//		t.createEmp_Company_info();
//		t.createAttendance();
//		t.createAttendanceComment();
//		t.createLeaveManagement();
//		String tt=""+t;
//		System.out.println("yes top " + tt);
//	String result="";
//		if(tt.indexOf(".")!=0){
//			System.out.println("yes " + tt.indexOf(".") +  " : " + tt.length());
//			result= tt.substring(0,tt.indexOf("."));
//		}else{
//			System.out.println("no");
//		}
	//	double diff= (t.getTime()-info.getCommentDate().getTime())/(24 * 60 * 60 * 1000);
		
		
	}
	public void updateRow(){


		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			UUID id= java.util.UUID.randomUUID();
	    Connection con = DriverManager.getConnection(URL);
	    
	    Calendar currentDate = Calendar.getInstance();
		Timestamp t=new Timestamp(currentDate.getTime().getTime());
		String tt=""+t;
	String result="";
	UUID id12= java.util.UUID.randomUUID();
	    String sql="Update emp_info set imagePath=null where key in ('18b17169-e10a-4461-8fbf-e4a2828d8e09','313a5587-b984-40d4-915f-9ee803a79da6','d6bc5b50-0d21-43ad-a043-889054cfaf92','df7fe5b1-8e0e-44aa-91ea-3157bd751887','becbffe5-a677-4058-9c69-ce3a188682af','95564321-4921-4d01-b34b-9e49dd54aaa8','d1302f96-23d8-4791-a91d-0059caf1bd31','2b19a0bd-70f7-4f9a-91e6-a57dbf0ca578','4b84f159-eb48-4487-b9bd-a3cdc25a6567','ed003031-887d-4f78-83e3-389cb0c660c7','75f7bd1c-a0cb-41bb-afc7-a4ab503a84ca','e7ede3b8-d0c9-4115-8d36-c7b3c05a5b60','47e4b3ef-af16-4faa-a6f0-333cec068c32','15ed250f-afe1-49be-a72c-893f25f6981f','be9b028a-0d34-4a69-b6f0-04d780342995','61ebd5db-da82-4280-a656-0d1b62b14aa2','807c7e81-d782-4205-893b-fd97cfd580d8','3d52c76c-a919-41b9-adcd-425e7c6aa0ca','7f9487f6-f89d-4f47-9306-c0f6ce220ddb','f8087cfc-a88d-449d-9197-032a7f5a7cac','be67921b-f79f-41be-afd8-e12db94cbd48','db074983-0e05-4856-8a01-d1d1d50fa0f0','c95c7097-3b7a-433e-800e-a7191985a30c','99cd4d89-cdd9-4798-a7a2-15d193f5d827','90136654-aa62-4f3e-827f-da6acab67271','daa8dc80-c867-4f09-8edc-18b845d19b41')";
	    PreparedStatement statement = con.prepareStatement(sql);
	   statement.executeUpdate();
System.out.println(" DOne");
	   // statement.close();
	    con.close();
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void insertTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			UUID id= java.util.UUID.randomUUID();
			//System.out.println(" text " + id.toString());
			//textGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection(URL);
	    
	    Calendar currentDate = Calendar.getInstance();
		Timestamp t=new Timestamp(currentDate.getTime().getTime());
		String tt=""+t;
		//System.out.println("yes top " + tt);
	String result="";
		if(tt.indexOf(".")!=0){
			//System.out.println("yes " + tt.indexOf(".") +  " : " + tt.length());
			result= tt.substring(0,tt.indexOf("."));
		}else{
			//System.out.println("no");
		}
	   // System.out.println(" tt " +result);
	    UUID id12= java.util.UUID.randomUUID();
	   // String sqlInsert12="INSERT INTO role (key,id,role,isActive,createDate,updateDate)"+
		//"values(\'"+id12+"\','661a9626-9a74-4b96-bcf2-a100905588c4','"+Constant.SUPER_ADMIN+"',1,'"+Util.getCurrentTimestamp()+"','"+Util.getCurrentTimestamp()+"');";
	    
	  //  String sql="INSERT INTO liketable (key,itemId,likedUserId,likedDate)"+
	   // 			"values(\'"+id+"\',3,5,\'"+result+"\');";
	    		   /*"KEY text PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    
	  //  PreparedStatement statement = con.prepareStatement(sqlInsert12);
	  //  statement.executeUpdate();
//System.out.println(" DOne");
	   // statement.close();
	    con.close();
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createIndex(){
		//CREATE INDEX user_state
		//   ON users (state);


		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			
	    Connection con = DriverManager.getConnection(URL);
	    //System.out.println("2");
	    //String sql="CREATE INDEX itemID on  liketable (itemId);";
	    //String sql="CREATE INDEX likedUserId on  liketable (likedUserId);";
	    String sql="CREATE INDEX likedDate on  liketable (likedDate);";
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();

	    statement.close();
	    con.close();
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void selectTable() throws Exception{
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			UUID id= java.util.UUID.randomUUID();
			//System.out.println(" text " + id.toString());
			//textGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection(URL);
	   // System.out.println("2");
	   // String sql="select key,photoId,commentDesc,commentDate from photoComments";// (key,itemid,likedUserId,likedDate)"+
	    //String sql="select key,empId,typeOfActivity, comment  from empextraactivities where empId = 'cc341c64-6028-4b72-b470-2cce09b8ce10' and key='b2cdda90-489c-4b1d-ae7a-c54704a1fcaa'";
	    int idd=1;
//	    String _sql="select " + 
//		"key,"+
//		"id," +
//		"depName" +
//		
//		" from empdepartment where id="+idd;
	    
	   // String _sql="select key,name,department from roletypes where 	department = 'Data entry'";
	    // String sql="select key,name,id, privacy from photoAlbum";
	  //  String sql="select key,idPhotoAlbum,photoPath, description from photoinfo";
	    			//"values(\'"+id+"\',1,1,'2012-01-01');";
	    		   /*"KEY text PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    //String sql="select key,empid,year, month, day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31  from attendence where empid = 'cc341c64-6028-4b72-b470-2cce09b8ce10' and year = 2012 and month =1";
	   //String sql="select key,id,role,isActive,createdDate,updateDate  from role where 	id = 'cc341c64-6028-4b72-b470-2cce09b8ce10'";
	    //String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where id='cc341c64-6028-4b72-b470-2cce09b8ce10'"
	   // String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where privacy=1";
	    //String sql="select key,empid,year, month, day, description, time, commentedBy  from comments where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and year= 2012 and month=10 and day=7";
	  // String sql="select key,empid,year, month from attendence where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and  year= 2012";
	   // String sql=" select key,loginName,password from emp_info";
	  //  String sql="select key ,postId,postedByUserId,postLikedByUserId,likeId,likedTime,status,indicator,flow from likeNotification where indicator=1 and postedByUserId='d935bbdc-b767-4caf-8fb3-03cf1dcc518b' and flow=1";
	  //  String sql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime from  post where indicator=1 AND privatestatus=0 AND postedTime > '2013-02-22 15:46:58' and postedTime < '2013-02-22 01:46:58'";
	   // String sql="UPDATE empadddetails SET telephoneNo = 'dfg',mobileNo =  '4334343434', address =  '1111111',city =  'dfg',state =  'dddd',country =  'dfg',pincode =  '343434',refrence =  'dfgfgdfgfdg' WHERE key =  'a0a682c8-34c9-4ecd-a571-bc2cc46ac55a'";
	    //String sql="select key,message from mailDetails";
	    //2b1c86cf-8ded-423d-8c14-e3f5663fae42
	   // String sql="select *  from photoinfo where indicator=1";// and idPhotoAlbum='10095614-40e2-4d3a-a234-2ca6a3bf6a4a' ";//and key='17adffa9-d130-47f2-85e5-80f59bfa30ad'";//,replierId, body,time from forumDiscussion where forumId='7f034614-b787-4130-92c4-113b3e4f9e81' and indicator = 1";
	  //  System.out.println(" sql " + sql);
	    String qqq= "select empName,creationDate from  emp_info";// where creationDate > '2013-10-01 15:46:58'";
	    /**
	     * ---e5df99ce-a8da-47ed-83e0-700ce1821f0a : null
---38550009-309b-476d-a6ec-826fd815c4ec : null
---e202cad3-e6d0-44de-94a1-0abd0e59a1ad : null
---d935bbdc-b767-4caf-8fb3-03cf1dcc518b : null
---789ba336-825d-4c74-a577-5d54a1da8fc3 : null
	     * 
	     */
	    //String sql="select key,idPhotoAlbum,photoPath,description,createdOn,width,height,time from  photoinfo where indicator=1";
	    //String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy,desc from  photoAlbum where key='3a52a1a5-1072-45a0-9839-762c5914f71b' and  indicator=1";
	    //String sql="select key,idPhotoAlbum from  photoinfo";// where idPhotoAlbum='7df69559-e1be-4292-b8c0-e0cbe79ad07c'";
	   // PreparedStatement statement = con.prepareStatement(qqq);
	    //statement.setString(1, "aaa");
	   // ResultSet rs= statement.executeQuery();
//	    String deleteQ="delete from likePost where key='bdbe7c9d-ccec-4983-8b07-fd3cb84d379a'";
//	    PreparedStatement statement1 = con.prepareStatement(deleteQ);
//	    statement1.executeUpdate();
	   System.out.println(" rs.get next " + qqq);
	    int i=0;
	    PasswordService serv=new PasswordService();
	    String fff= "Update emp_info set password='"+serv.encrypt("yogi123")+"' where key = '369b60f2-88dd-4338-a2d5-f54b36841143'";
    	PreparedStatement st = con.prepareStatement(fff);
    	st.executeUpdate();
	   // while(rs.next()){
	    	i+=1;
	    //	System.out.println("---"  + rs.getString(1) + " : " + rs.getString(2));//+ " : " + rs.getString(5) + " : "+ rs.getString(6));// + ": " + rs.getString(2) + " : "+rs.getString(3) + " : " +rs.getString(4) + " : " +rs.getString(5) + " : " +rs.getString(6) + " : " +rs.getString(7) + " : " +rs.getString(8) + " : " +rs.getString(9));//) + " : " +rs.getInt(9) + " : " +rs.getInt(9));//+" :  "+rs.getString(2)+ " : "+ rs.getString(3) + " : " + rs.getString(4)+ " : " +rs.getTimestamp(6) );//+" : "+ rs.getString(2)+" : "+ rs.getString(3)+" : "+ rs.getString(4)+" : "+ rs.getString(5));//+" : "+ rs.getString(6)+" : "+ rs.getString(2));
//	    	String path = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/PhotoStatic/userUploads/"+rs.getString(3);
//	    	try {
//	    	File f=new File(path);
//	    	String time=Util.getPhotoCreationDate(f);
//	    		    	System.out.println( "  time is " + time);
//	    	if(time!=null || !time.equals("")){
//	    	    PreparedStatement statements = con.prepareStatement("Update photoinfo set time='"+time+"' where key='"+rs.getString(1)+"'");
//	    	    statements.executeUpdate();
//	    	}
////	    	
	    	//}
//	    	catch(Exception e){
//	    		System.out.println( "  problem i");
//	    	}
//	    	
	    	
	  //  }
	    System.out.println(" i " + i);
	    	//+":"+rs.getString(3));// + 
	    	//		" : " + rs.getInt(4) +" : " + rs.getInt(5)+" : " + rs.getString(6)
	    	//		+" : "+ rs.getString(7) + " : " + rs.getString(8));
	    	//System.out.println(" rs.get next  "  + rs.getString(1)+" : "+ rs.getString(2) +" : "+ rs.getInt(3) + " : " + rs.getInt(4));
//	    	Timestamp s=rs.getTimestamp(4);
//	    	Calendar c= Calendar.getInstance();
//	    	Timestamp curre= new Timestamp(c.getTime().getTime());
//	    	int days= (int)(curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
//	    	int hours= (int)(curre.getTime()-s.getTime())/(60 * 60 * 1000);
//	    	int min= (int)(curre.getTime()-s.getTime())/(1000);
//	    	//double days= (curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
//	    	System.out.println(" Days " + days + " hours " + hours  + " min " + min);
	    	
	    //}
	    //statement.close();
	    con.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void deleteTable(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			UUID id= java.util.UUID.randomUUID();
			//System.out.println(" text " + id.toString());
			//textGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection(URL);
	    //System.out.println("2");
	    String sql="delete  from liketable ";// (key,itemid,likedUserId,likedDate)"+
	  // String sql="select key,name,id, privacy from photoAlbum";
	  //  String sql="select key,idPhotoAlbum,photoPath, description from photoinfo";
	    			//"values(\'"+id+"\',1,1,'2012-01-01');";
	    		   /*"KEY text PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	   // System.out.println(" here  i am ");
	    //while(rs.next()){
	    //	System.out.println(" rs.get next  "  + rs.getString(1)+" : "+ rs.getString(2) +" : "+ rs.getString(3) + " : "+rs.getString(4));
	    //}
	    statement.close();
	    con.close();
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void createLiketable(){

		try {
			
			//System.out.println("1");
			

	    String query = "DROP TABLE liketable";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	 
	     String mytables = "CREATE TABLE liketable (\n"
			        + "    KEY text,\n"
			        + "    itemId text,\n"
			        + "    LikedUserId text,\n"
			        + "    indicator int,\n"
			        + "    likedDate timestamp,\n"
			        + "    PRIMARY KEY (indicator,KEY)\n"
			        + ") WITH CLUSTERING ORDER BY ( KEY DESC)\n"
			        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
				       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
			        + "   AND read_repair_chance = 0.5\n"
			        + "   AND dclocal_read_repair_chance = 0.6\n"
			        + "   AND replicate_on_write = true\n"
			        + "   AND gc_grace_seconds = 42\n"
			        + "   AND bloom_filter_fp_chance = 0.01\n"
			        + "   AND caching = 'ALL'\n"
			        + "   AND comment = 'My awesome table'\n"
			        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
			        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";

	     	session.execute(mytables);
	    
	    
	    
	  
	    
	    String sql2="CREATE INDEX itemId on  liketable (itemId);";
	    session.execute(sql2);
	    String sql3="CREATE INDEX likedUserId on  liketable (likedUserId);";
	    session.execute(sql3);
    
	    System.out.println(" dione");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createPhotoComments(){

		try {
			
	    System.out.println("2");
	    String query = "DROP TABLE photoComments";
	    try{
	    	 session.execute(query);
	    }catch(Exception exxx){exxx.printStackTrace();}
	    
	    
	    String mytables = "CREATE TABLE photoComments (\n"
		        + "    KEY text,\n"
		        + "    photoId text,\n"
		        + "    commentUserId text,\n"
		        + "    commentUserName text,\n"
		        + "    commentUserPhoto text,\n"
		        + "    commentDesc text,\n"
		        + "    indicator int,\n"
		        + "    commentDate timestamp,\n"
		        + "    PRIMARY KEY (indicator, commentDate)\n"
		        + ") WITH CLUSTERING ORDER BY (commentDate DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   

	    try{
	    	 session.execute(mytables);
	    }catch(Exception exxx){exxx.printStackTrace();}

	    String sql1sdf="CREATE INDEX photoCommnetKeyIndex on  photoComments (KEY);";
	    session.execute(sql1sdf);
	    
	    String sql1="CREATE INDEX photoId on  photoComments (photoId);";
	    session.execute(sql1);

	    String sql2="CREATE INDEX commentUserId on  photoComments (commentUserId);";
	    session.execute(sql2);
	    
	    System.out.println(" ok done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPhotoInfo(){

		try {
			System.out.println("1");
			
	    System.out.println("2");
	    String query = "DROP TABLE photoinfo";
	    try{
	    session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE photoinfo (\n"
		        + "    KEY text,\n"
		        + "    idPhotoAlbum text,\n"
		        + "    photoPath text,\n"
		        + "    description text,\n"
		        + "    createdOn timestamp,\n"
		        + "    ownerId text,\n"
		        + "    width int,\n"
		        + "    height int,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,createdOn, KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (createdOn DESC, KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    
	    try {
	    	session.execute(mytables);
	    }catch(Exception exxx){}

	    String sql1="CREATE INDEX idPhotoAlbum on  photoinfo (idPhotoAlbum);";
	    session.execute(sql1);
	    String sql12="CREATE INDEX ownerId on  photoinfo (ownerId);";
	    session.execute(sql12);
	    String sql13="CREATE INDEX photoPath on  photoinfo (photoPath);";
	    session.execute(sql13);
	    
	    
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void createPhotoAlbum(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE photoAlbum";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    System.out.println("started");
	    String mytables = "CREATE TABLE photoAlbum (\n"
		        + "    KEY text,\n"
		        + "    name text,\n"
		        + "    coverScreenPath text,\n"
		        + "    description text,\n"
		        + "    createdOn timestamp,\n"
		        + "    updatedOn timestamp,\n"
		        + "    id  text,\n"
		        + "    privacy int,\n"
		        + "    indicator int,\n"
		        + "    albumType int,\n"
		        + "    PRIMARY KEY (KEY )\n"
		        + ") WITH \n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "    read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    try{
	    session.execute(mytables);
	    }catch(Exception exxx){}
	    String sql1="CREATE INDEX id on  photoAlbum (id);";
	    session.execute(sql1);
	    
	    String sql13="CREATE INDEX albumType on  photoAlbum (albumType);";
	    session.execute(sql13);
	    
	    String sql145="CREATE INDEX indicator on  photoAlbum (indicator);";
	    session.execute(sql145);
	    System.out.println(" creating key index");
	    
	    String sql2="CREATE INDEX privacy on  photoAlbum (privacy);";
	    session.execute(sql2);
	    System.out.println(" craete second first one");
	    
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//photoalbum
	
	
	public void createEmpInfo(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE emp_info";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE emp_info (\n"
		        + "    KEY text,\n"
		        + "    empId text,\n"
		        + "    empName text,\n"
		        + "    dob timestamp,\n"
		        + "    department text,\n"
		        + "    designation text,\n"
		        + "    doj timestamp,\n"
		        + "    password text,\n"
		        + "    imagePath text,\n"
		        + "    loginName text,\n"
		        + "    emailId  text,\n"
		        + "    mobile text,\n"
		        + "    gender text,\n"
		        
		        + "  coverPath text,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY,creationDate )\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC, creationDate DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    session.execute(mytables);
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empId on  emp_info (empId);";
	    session.execute(sql1);
	    System.out.println(" craeted index  first one");
	    String sql2="CREATE INDEX empName on  emp_info (empName);";
	    session.execute(sql2);
	    
	    String sql3="CREATE INDEX loginName on  emp_info (loginName);";
	    session.execute(sql3);
	    System.out.println(" craete third first one");
	    
	    
	    String sql4="CREATE INDEX department on  emp_info (department);";
	    session.execute(sql4);
	    System.out.println(" craete fourth first one");
	    
	    String sql5="CREATE INDEX designation on  emp_info (designation);";
	    session.execute(sql5);
	    System.out.println(" craete fifth first one");
	    
	    System.out.println(" Done");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void createEmpAddress(){

		try {
			
	    System.out.println("2");
	    String query = "DROP TABLE empadddetails";
	    try{
	    	session.execute(query);
	    }
	    catch(Exception ex){}
	    

	    String mytables = "CREATE TABLE empadddetails (\n"
		        + "    KEY text,\n"
		        + "    empId text,\n"
		        + "    telephoneNo text,\n"
		        + "    mobileNo text,\n"
		        + "    Address text,\n"
		        + "    city text,\n"
		        + "    state text,\n"
		        + "    country text,\n"
		        + "    pincode text,\n"
		        + "    refrence text,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		        + "    PRIMARY KEY (indicator,creationDate,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (creationDate DESC,KEY DESC)\n"
			     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    
	    try{
	    session.execute(mytables);
	    
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIds on  empadddetails (empId);";
	    session.execute(sql1);
	    
	    
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createDepartmentStatic(){

		try {
			System.out.println("1");
			

			
			//	    System.out.println("2");
	    String query = "DROP TABLE empdepartment";
//	    String sql="CREATE TABLE empdepartment ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "id bigint,"+
//	    		   "depName text)";
	    try{
	    	session.execute(query);
	    }catch(Exception ex)
	    {}

	    String mytables = "CREATE TABLE empdepartment (\n"
		        + "    KEY text,\n"
		        + "    depName text,\n"
		        + "    orderDep int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    session.execute(mytables);
	    
	    String sql1="CREATE INDEX orderDep on  empdepartment (orderDep);";
	    session.execute(sql1);
	    
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX ids on  empdepartment (id);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
//	    System.out.println(" craeted new one 2");
//		    // insert records
//	    	text id= java.util.text.randomtext();
//		    String sqlInsert1="INSERT INTO empdepartment (key,id,depName)"+
//			"values(\'"+id+"\',1,'Software');";
//		 
//		    
//		    
//		    text id2= java.util.text.randomtext();
//		    String sqlInsert2="INSERT INTO empdepartment (key,id,depName)"+
//			"values(\'"+id2+"\',2,'Operations');";
//		    
//		    text id3= java.util.text.randomtext();
//		    String sqlInsert3="INSERT INTO empdepartment (key,id,depName)"+
//			"values(\'"+id3+"\',3,'AllFax');";
//		    
//		    text id4= java.util.text.randomtext();
//		    String sqlInsert4="INSERT INTO empdepartment (key,id,depName)"+
//			"values(\'"+id4+"\',4,'Accounts');";
//
//		    System.out.println(" craeted new : " + sqlInsert1);
//		    
//			PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
//			inset1.executeUpdate();
//			PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
//			inset2.executeUpdate();
//			PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
//			inset3.executeUpdate();
//			PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
//			inset4.executeUpdate();
			
//			  text id5= java.util.text.randomtext();
//			    String sqlInsert5="INSERT INTO empdepartment (key,id,depName)"+
//				"values(\'"+id5+"\',5,'IT Operation');";
//			    PreparedStatement inset5 = con.prepareStatement(sqlInsert5);
//				inset5.executeUpdate();
//				inset5.close();

//		inset1.close();
//		inset2.close();
//		inset3.close();
//		inset4.close();
//		statement.close();
//	    statement1.close();
//	    statementss.close();
	  
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createDesignationStatic(){

		try {
			System.out.println("1");
			
//	    System.out.println("2");
//	    String query = "DROP TABLE empdesignation";
//	    String sql="CREATE TABLE empdesignation ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "id bigint,"+
//	    		   "depId bigint,"+
//	    		   "name text)";
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
	    String query = "DROP TABLE empdesignation";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}

	    String mytables = "CREATE TABLE empdesignation (\n"
		        + "    KEY text,\n"
		        + "    orderNum int,\n"
		        + "    name text,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
	
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
//	    
//	    String sql2="CREATE INDEX idss on  empdesignation (id);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
//	    
//	    
//		    // insert records
//			text id= java.util.text.randomtext();
//		    String sqlInsert1="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id+"\',1,1,'Software Engineer');";
//		 
//		    text id2= java.util.text.randomtext();
//		    String sqlInsert2="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id2+"\',2,1,'Team Lead');";
//		    
//		    text id3= java.util.text.randomtext();
//		    String sqlInsert3="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id3+"\',3,1,'Junior Software Engineer');";
//		    
//		    text id4= java.util.text.randomtext();
//		    String sqlInsert4="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id4+"\',4,2,'Manager');";
//		   
//		    text id5= java.util.text.randomtext();
//		    String sqlInsert5="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id5+"\',5,2,'Data Entry');";
//		    
//		    text id6= java.util.text.randomtext();
//		    String sqlInsert6="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id6+"\',6,2,'QA');";
//		    
//		    text id7= java.util.text.randomtext();
//		    String sqlInsert7="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id7+"\',7,2,'Review');";
//		    
//		    text id8= java.util.text.randomtext();
//		    String sqlInsert8="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id8+"\',8,2,'Team Lead');";
//		
//		    //
//		    text id9= java.util.text.randomtext();
//		    String sqlInsert9="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id9+"\',9,3,'Team Lead');";
//		    
//		    text id10= java.util.text.randomtext();
//		    String sqlInsert10="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id10+"\',10,3,'Data Entry');";
//		    
//		    text id11= java.util.text.randomtext();
//		    String sqlInsert11="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id11+"\',11,3,'Manager');";
//		    
//		    text id12= java.util.text.randomtext();
//		    String sqlInsert12="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id12+"\',12,3,'QA');";
//		    
//		    text id13= java.util.text.randomtext();
//		    String sqlInsert13="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id13+"\',13,4,'Accounts');";
//		    
//		    text id14= java.util.text.randomtext();
//		    String sqlInsert14="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id14+"\',14,5,'IT Management');";
		    
//		    text id15= java.util.text.randomtext();
//		    String sqlInsert15="INSERT INTO empdesignation (key,id,depId,name)"+
//			"values(\'"+id15+"\',15,3,'Medical Transcription');";
		    
//		    
//			PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
//			inset1.executeUpdate();
//			PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
//			inset2.executeUpdate();
//			PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
//			inset3.executeUpdate();
//			PreparedStatement inset4 = con.prepareStatement(sqlInsert5);
//			inset4.executeUpdate();
//			PreparedStatement inset5 = con.prepareStatement(sqlInsert6);
//			inset5.executeUpdate();
//			PreparedStatement inset6 = con.prepareStatement(sqlInsert7);
//			inset6.executeUpdate();
//			PreparedStatement inset7 = con.prepareStatement(sqlInsert8);
//			inset7.executeUpdate();
//			PreparedStatement inset8 = con.prepareStatement(sqlInsert9);
//			inset8.executeUpdate();
//			PreparedStatement inset9 = con.prepareStatement(sqlInsert10);
//			inset9.executeUpdate();
//			PreparedStatement inset10 = con.prepareStatement(sqlInsert11);
//			inset10.executeUpdate();
//			PreparedStatement inset11 = con.prepareStatement(sqlInsert12);
//			inset11.executeUpdate();
//			PreparedStatement inset12 = con.prepareStatement(sqlInsert13);
//			inset12.executeUpdate();
//			PreparedStatement inset13 = con.prepareStatement(sqlInsert4);
//			inset13.executeUpdate();
//			PreparedStatement inset14 = con.prepareStatement(sqlInsert14);
//			inset14.executeUpdate();
//			PreparedStatement inset15 = con.prepareStatement(sqlInsert15);
//			inset15.executeUpdate();
		


//			inset1.close();
//			inset2.close();
//			inset3.close();
//			inset4.close();
//			inset5.close();
//			inset6.close();
//			inset7.close();
//			inset8.close();
//			inset9.close();
//			inset10.close();
//			inset11.close();
//			inset12.close();
//			inset13.close();
//			inset14.close();
//			inset15.close();
//	    statement2.close();
//	    statement1.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insertRole(){
		
		try {
				Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
				System.out.println("1");
				String empId="";
			    Connection con = DriverManager.getConnection(URL);
			    System.out.println("2");
			    String role="";
		    
		    
		    	role="";
		    	UUID id= java.util.UUID.randomUUID();
				 String inssql="INSERT INTO role(key,id,role,isActive,createdDate,updateDate" +
		         ") VALUES('"+id.toString()+"','"+empId+"','"+role+"',1,'"+Util.getCurrentTimestamp()+"','"+Util.getCurrentTimestamp()+"')";
		}
		
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void createRole(){

		try {
			
	    System.out.println("2");
	    String query = "DROP TABLE role";
	try{
		session.execute(query);
	}catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE role (\n"
		        + "    KEY text,\n"
		        + "    id text,\n"
		        + "    role text,\n"
		        + "    isActive int,\n"
		        + "    indicator int,\n"
		        + "    creationDate timestamp,\n"
		        + "    updateDate timestamp,\n"
		        + "    PRIMARY KEY (indicator,role,creationDate,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (role DESC,creationDate DESC,KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"   
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
	    
	    
	    String sql1="CREATE INDEX idsss on  role (id);";
	   session.execute(sql1);
	    
	    String sql2="CREATE INDEX isActive on  role (isActive);";
	    session.execute(sql2);
	    
	    
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void createCompanyInfoStatic(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE companyinfo";
//	    String sql="CREATE TABLE companyinfo ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "companyName text,"+
//	    		   "companyURL text,"+
//	    		   "companyImage text)";
//	    		
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    
//			    // insert records
//		    	text id= java.util.text.randomtext();
//			    String sqlInsert1="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
//				"values(\'"+id+"\','RAIR','www.rair.com','images/rairLogo.jpg');";
//			 
//			    text id2= java.util.text.randomtext();
//			    String sqlInsert2="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
//				"values(\'"+id2+"\','ACS','acs.com','images/acsLogo.png');";
//			    
//			    text id3= java.util.text.randomtext();
//			    String sqlInsert3="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
//				"values(\'"+id3+"\','Qualcomm','qualcomm.com','images/qualcommLogo.png');";
//			    
//			    text id4= java.util.text.randomtext();
//			    String sqlInsert4="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
//				"values(\'"+id4+"\','AllFax','www.allfax.com','images/allfaxLogo.png');";
//		
//				PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
//				inset1.executeUpdate();
//				PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
//				inset2.executeUpdate();
//				PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
//				inset3.executeUpdate();
//				PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
//				inset4.executeUpdate();
//				
//			    inset1.close();
//			    inset2.close();
//			    inset3.close();
//			    inset4.close();
//			    
////	    String sql1="CREATE INDEX id on  role (id);";
////	    PreparedStatement statement1 = con.prepareStatement(sql1);
////	    statement1.executeUpdate();
//	    
////	    String sql2="CREATE INDEX isActive on  role (isActive);";
////	    PreparedStatement statement2 = con.prepareStatement(sql2);
////	    statement2.executeUpdate();
//	    
//				
//	   // statement1.close();
//	   // statement2.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public void createEmp_Company_info(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE emp_company_info";
//	    String sql="CREATE TABLE emp_company_info ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "empId text,"+
//	    		   "companyId text)";
//	    		
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX empIdsssss on  emp_company_info (empId);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
////	    
//	    String sql2="CREATE INDEX companyId on  emp_company_info (companyId);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
////	    
//	    
//	    statement1.close();
//	    statement2.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void createRoleTypes(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE roletypes";
	    try{
	    	session.execute(query);
	    	
	    }catch(Exception ex){}
	    String sql="CREATE TABLE roletypes ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "name text,"+
	    		   "department text)";
	    session.execute(sql);
	  
	    System.out.println(" craeted new one");
	    String sql1="CREATE INDEX department_index on  roletypes (department);";
	    session.execute(sql1);
		}
		catch(Exception ex){}
	}
//	    
//	    //
//
//			    // insert records
//		    	text id= java.util.text.randomtext();
//			    String sqlInsert1="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id+"\','profileedit','Data entry');";
//			 
//			    text id2= java.util.text.randomtext();
//			    String sqlInsert2="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id2+"\','profileview','Data entry');";
//			    
//			    text id3= java.util.text.randomtext();
//			    String sqlInsert3="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id3+"\','photoview','Data entry');";
//			    
//			    text id4= java.util.text.randomtext();
//			    String sqlInsert4="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id4+"\','photoedit','Data entry');";
//			    
//			    text id5= java.util.text.randomtext();
//			    String sqlInsert5="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id5+"\','calendarview','Data entry');";
//			    
//			    text id6= java.util.text.randomtext();
//			    String sqlInsert6="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id6+"\','calendaredit','Data entry');";
//			    
//			    text id7= java.util.text.randomtext();
//			    String sqlInsert7="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id7+"\','depAdmin','Data entry');";
//			    
//			    text id8= java.util.text.randomtext();
//			    String sqlInsert8="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id8+"\','linkProfile','Data entry');";
//			    
//			    text id9= java.util.text.randomtext();
//			    String sqlInsert9="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id9+"\','linkPhoto','Data entry');";
//			    
//			    text id10= java.util.text.randomtext();
//			    String sqlInsert10="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id10+"\','linkCalendar','Data entry');";
//			    
//			    text id11= java.util.text.randomtext();
//			    String sqlInsert11="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id11+"\','roleEdit','Data entry');";
//			    
//			    text id12= java.util.text.randomtext();
//			    String sqlInsert12="INSERT INTO roletypes (key,name,department)"+
//				"values(\'"+id12+"\','roleView','Data entry');";
//		
//				PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
//				inset1.executeUpdate();
//				PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
//				inset2.executeUpdate();
//				PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
//				inset3.executeUpdate();
//				PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
//				inset4.executeUpdate();
//				PreparedStatement inset5 = con.prepareStatement(sqlInsert5);
//				inset5.executeUpdate();
//				PreparedStatement inset6 = con.prepareStatement(sqlInsert6);
//				inset6.executeUpdate();
//				PreparedStatement inset7 = con.prepareStatement(sqlInsert7);
//				inset7.executeUpdate();
//				PreparedStatement inset8 = con.prepareStatement(sqlInsert8);
//				inset8.executeUpdate();
//				PreparedStatement inset9 = con.prepareStatement(sqlInsert9);
//				inset9.executeUpdate();
//				PreparedStatement inset10 = con.prepareStatement(sqlInsert10);
//				inset10.executeUpdate();
//				PreparedStatement inset11 = con.prepareStatement(sqlInsert11);
//				inset11.executeUpdate();
//				PreparedStatement inset12 = con.prepareStatement(sqlInsert12);
//				inset12.executeUpdate();
//				
//				//
//	    
//				inset1.close();
//				inset2.close();
//				inset3.close();
//				inset4.close();
//				inset5.close();
//				inset6.close();
//				inset7.close();
//				inset8.close();
//				inset9.close();
//				inset10.close();
//				inset11.close();
//				inset12.close();
//	    
//	    statement1.close();
//	   
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void createAttendance(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE attendence";
//	    String sql="CREATE TABLE attendence ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "empId text,"+
//	    		   "year int,"+
//	    		   "month int,"+
//	    		   "day1 text,"+
//	    		   "day2 text,"+
//	    		   "day3 text,"+
//	    		   "day4 text,"+
//	    		   "day5 text,"+
//	    		   "day6 text,"+
//	    		   "day7 text,"+
//	    		   "day8 text,"+
//	    		   "day9 text,"+
//	    		   "day10 text,"+
//	    		   "day11 text,"+
//	    		   "day12 text,"+
//	    		   "day13 text,"+
//	    		   "day14 text,"+
//	    		   "day15 text,"+
//	    		   "day16 text,"+
//	    		   "day17 text,"+
//	    		   "day18 text,"+
//	    		   "day19 text,"+
//	    		   "day20 text,"+
//	    		   "day21 text,"+
//	    		   "day22 text,"+
//	    		   "day23 text,"+
//	    		   "day24 text,"+
//	    		   "day25 text,"+
//	    		   "day26 text,"+
//	    		   "day27 text,"+
//	    		   "day28 text,"+
//	    		   "day29 text,"+
//	    		   "day30 text,"+
//	    		   "isSubmitted int,"+
//	    		   "submitDeportment text,"+
//	    		   "day31 text)";
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX empIdssssss on  attendence (empId);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
////	    
//	    String sql2="CREATE INDEX year on  attendence (year);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
//	    
//	    String sql3="CREATE INDEX month on  attendence (month);";
//	    PreparedStatement statement3 = con.prepareStatement(sql3);
//	    statement3.executeUpdate();
//	    
//	    String sql4="CREATE INDEX isSubmitted on  attendence (isSubmitted);";
//	    PreparedStatement statement4 = con.prepareStatement(sql4);
//	    statement4.executeUpdate();
//	    
//	    String sql5="CREATE INDEX submitDeportment on  attendence (submitDeportment);";
//	    PreparedStatement statement5 = con.prepareStatement(sql5);
//	    statement5.executeUpdate();
////	    
//	    
//	    
//	    statement1.close();
//	    statement2.close();
//	    statement3.close();
//	    statement4.close();
//	    statement5.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void createAttendanceComment(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE comments";
//	    String sql="CREATE TABLE comments ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "empId text,"+
//	    		   "description text,"+
//	    		   "time timestamp,"+
//	    		   "commentedBy text,"+
//	    		   "day int,"+
//	    		   "year int,"+
//	    		   "month int)";
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX empIdsssssss on  comments (empId);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
////	    
//	    String sql2="CREATE INDEX year2 on  comments (year);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
//	    
//	    String sql3="CREATE INDEX month2 on  comments (month);";
//	    PreparedStatement statement3 = con.prepareStatement(sql3);
//	    statement3.executeUpdate();
//	    
//	    String sql4="CREATE INDEX day2 on  comments (day);";
//	    PreparedStatement statement4 = con.prepareStatement(sql4);
//	    statement4.executeUpdate();
////	    
//	    
//	    statement1.close();
//	    statement2.close();
//	    statement3.close();
//	    statement4.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void createLeaveManagement(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE leavemanagement";
//	    String sql="CREATE TABLE leavemanagement ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "empId text,"+
//	    		   "year int,"+
//	    		   "paidLeavesTaken int,"+
//	    		   "sickLeavesTaken int,"+
//	    		   "doj timestamp,"+
//	    		   "carryOverLeaves float,"+
//	    		   "month text,"+
//	    		   "endyear int,"+
//	    		   "endmonth int,"+
//	    		   "totalPaidLeaves float)";
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX empIdssssssss on  leavemanagement (empId);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
////	    
//	    String sql2="CREATE INDEX year3 on  leavemanagement (year);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
//	    
//	    String sql3="CREATE INDEX month3 on  leavemanagement (month);";
//	    PreparedStatement statement3 = con.prepareStatement(sql3);
//	    statement3.executeUpdate();
//	    
////	    String sql4="CREATE INDEX day2 on  leavemanagement (day);";
////	    PreparedStatement statement4 = con.prepareStatement(sql4);
////	    statement4.executeUpdate();
////	    
//	    
//	    statement1.close();
//	    statement2.close();
//	    statement3.close();
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	
	public void createProfessionDetail(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE professional";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}

	    
	    String mytables = "CREATE TABLE professional (\n"
		        + "    KEY text,\n"
		        + "    empId text,\n"
		        + "    companyName text,\n"
		        + "    designation text,\n"
		        + "    workedFrom timestamp,\n"
		        + "    workedTo timestamp,\n"
		        + "    city text,\n"
		        + "    country text,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,workedFrom,creationDate,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (workedFrom DESC,creationDate DESC,KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"   
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
	    
	    
	    String sql1="CREATE INDEX empId2ssss on  professional (empId);";
	    session.execute(sql1);
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createEduucationDetails(){

		try {
			System.out.println("1");
			
	    System.out.println("2");
	    String query = "DROP TABLE education";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE education (\n"
		        + "    KEY text,\n"
		        + "    empId text,\n"
		        + "    collegeName text,\n"
		        + "    course text,\n"
		        + "    start timestamp,\n"
		        + "    end timestamp,\n"
		        + "    city text,\n"
		        + "    country text,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,start,end,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (start DESC,end DESC,KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"   
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
	    
	    
	    String sql1="CREATE INDEX empIdsss1ss on  education (empId);";
	    session.execute(sql1);
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void createCalendarSumbitLink(){
//
//		try {
//			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
//			System.out.println("1");
//			
//	    Connection con = DriverManager.getConnection(URL);
//	    System.out.println("2");
//	    String query = "DROP TABLE cal_submit";
//	    String sql="CREATE TABLE cal_submit ("+
//	    		   "KEY text PRIMARY KEY,"+
//	    		   "department text,"+
//	    		   "submitEmpId text,"+
//	    		   "course text,"+
//	    		   "start timestamp,"+
//	    		   "end timestamp)";
//	    PreparedStatement statementss = con.prepareStatement(query);
//	    try{
//	    statementss.executeUpdate();
//	    }catch(Exception exxx){}
//	    System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	    String sql1="CREATE INDEX empIdsss1ss on  education (empId);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
//	    
//	    statement1.close();
//
//	    statementss.close();
//	    statement.close();
//	    con.close();
//	    System.out.println(" Done");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void createVideoInfo(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE videoinfo";
	    try{
	    	session.execute(query);
	    	
	    }catch(Exception ex){}


	    String mytables = "CREATE TABLE videoinfo (\n"
		        + "    KEY text,\n"
		        + "    videoPath text,\n"
		        + "    videoPhotoPath text,\n"
		        + "    description text,\n"
		        + "    ownerId text,\n"
		        + "    createdOn timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,createdOn,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (createdOn DESC,KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"  
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);

	    String sql1="CREATE INDEX ownerIdssss on  videoinfo (ownerId);";
	    session.execute(sql1);
	    
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPost(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE post";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	  // String sql="CREATE TABLE post (postKey text PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";

	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    
	    String mytables = "CREATE TABLE post (\n"
		        + "    KEY text,\n"
		        + "    postedById text,\n"
		        + "    postedDesc text,\n"
		        + "    postedPhotoId text,\n"
		        + "    postedVideoId text,\n"
		        + " postedToId text,\n"
		        + " postType int,\n"
		        +" photoCount int,\n"
		        + " flow int,\n "
		        + "    privatestatus int,\n"
		        + "    postedTime timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,postedTime)\n"
		        + ") WITH CLUSTERING ORDER BY (postedTime DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"  
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
	    
	  
	    String sql1="CREATE INDEX postedById on  post (postedById);";
	   session.execute(sql1);
	   String sql33="CREATE INDEX KEY123  on  post (KEY);";
	   session.execute(sql33);
	    
	    String sql2="CREATE INDEX postedVideoId on  post (postedVideoId);";
	    session.execute(sql2);
	    
	    String sql7="CREATE INDEX postedToId23 on  post (postedToId);";
	    session.execute(sql7);
	    
	    String sql3="CREATE INDEX postedPhotoId on  post (postedPhotoId);";
	    session.execute(sql3);
	    String sql4="CREATE INDEX privatestatus on  post (privatestatus);";
	    session.execute(sql4);
	    
	    
	    System.out.println(" Done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createBulletinInfo(){

		try {
	    String query = "DROP TABLE publicChat";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}

	    
	    String mytables = "CREATE TABLE publicChat (\n"
		        + "    KEY text,\n"
		        + "    postedFromId text,\n"
		        + "    postedInfo text,\n"
		        + "    postedTime timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,postedTime,postedFromId,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (postedTime DESC,postedFromId DESC,KEY DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"  
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    session.execute(mytables);
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    
	  
	    String sql1="CREATE INDEX postedByIdssssfdfdfddf on  publicChat (postedFromId);";
	    session.execute(sql1);
	  

	    
	    
	    System.out.println(" Done");
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPostLikeTable(){

		try {
			System.out.println("hi");
	    //System.out.println("2");
	    String query = "DROP TABLE likePost";
	    try{
	    session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE likePost (\n"
		        + "    KEY text,\n"
		        + "    postId text,\n"
		        + "    likedUserId text,\n"
		        + "    likedDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"  
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    session.execute(mytables);
	    
	    String sql2="CREATE INDEX postId on  likePost (postId);";
	    session.execute(sql2);
	    
	 
	    String sql3="CREATE INDEX likedUserId2 on  likePost (likedUserId);";
	    session.execute(sql3);
	    System.out.println(" dione");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPostComments(){

		try {
			
	    System.out.println("2");
	    String query = "DROP TABLE postComments";
	    try{
	    	session.execute(query);
	    }
	    catch(Exception ex){}
	 
	    
	    String mytables = "CREATE TABLE postComments (\n"
		        + "    KEY text,\n"
		        + "    postId text,\n"
		        + "    commentUserId text,\n"
		        + "    commentDesc text,\n"
		        + "    commentDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,commentDate)\n"
		        + ") WITH CLUSTERING ORDER BY (commentDate DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"  
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	    session.execute(mytables);
	    String sql1="CREATE INDEX postId2 on  postComments (postId);";
	    session.execute(sql1);
	    String sql2="CREATE INDEX commentUserId2 on  postComments (commentUserId);";
	    session.execute(sql2);
	    String sql3="CREATE INDEX KEYsss on  postComments (KEY);";
	    session.execute(sql3);
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createTickerInfo(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE tickerinfo";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE tickerinfo (\n"
		        + "    KEY text,\n"
		        + "    userId text,\n"
		        + "    targetUserId text,\n"
		        + "    photoId text,\n"
		        + "    commentId text,\n"
		        + "    likeId text,\n"
		        + "    commentDesc text,\n"
		        + "    tickerType text,\n"
		        + "    tickertime timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,tickertime)\n"
		        + ") WITH CLUSTERING ORDER BY (tickertime DESC )\n"
		       // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	  session.execute(mytables);
	   String sql1111="CREATE INDEX keyIndextickerInfo on  tickerinfo (KEY);";
	    session.execute(sql1111);

	    String sql1="CREATE INDEX photoIdsdf on  tickerinfo (photoId);";
	    session.execute(sql1);
	    String sql2="CREATE INDEX commentIdwewe on  tickerinfo (commentId);";
	    session.execute(sql2);	    
	    String sql4="CREATE INDEX likeIddfd on  tickerinfo (likeId);";
	    session.execute(sql4);	    
	 
	    
	    
	    String sql6="CREATE INDEX userIdss on  tickerinfo (userId);";
	    session.execute(sql6);
	    
	    
	    String sql7="CREATE INDEX targetUserIdsds on  tickerinfo (targetUserId);";
	    session.execute(sql7);
	    System.out.println(" Done ");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void createLikeNotification(){

		try {
	    String query = "DROP TABLE likeNotification";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE likeNotification (\n"
		        + "    KEY text,\n"
		        + "    postId text,\n"
		        + "    postedByUserId text,\n"
		        + "    postLikedByUserId text,\n"
		        + "    likeId text,\n"
		        + "    likedTime timestamp,\n"
		        + "    status int,\n"
		        + "    flow int,\n"
		        + "    indicator int,\n"
		        + "    message text,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   session.execute(mytables);
	    
	    String sql3="CREATE INDEX postedByUserId on  likeNotification (postedByUserId);";
	    session.execute(sql3);
	    String sql1="CREATE INDEX postLikedByUserId on  likeNotification (postLikedByUserId);";
	    session.execute(sql1);
	    String sql2="CREATE INDEX likedTime on  likeNotification (likedTime);";
	    session.execute(sql2);
	    
	    String sql4="CREATE INDEX status on  likeNotification (status);";
	    session.execute(sql4);
	    String sql5="CREATE INDEX flow on  likeNotification (flow);";
	    session.execute(sql5);
	    
	    

	    
	   
	    System.out.println(" Done ");
	    
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createMailLinkTable(){
		try {
	    System.out.println("2");
	    String query = "DROP TABLE mailLinks";
	    try{
	    	 session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE mailLinks (\n"
		        + "    KEY text,\n"
		        + "    mailId text,\n"
		        + "    fromId text,\n"
		        + "    toId text,\n"
		        + "    status int,\n"
		        + "    mailTime timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   session.execute(mytables);
	   	    
	    String sql3="CREATE INDEX mailId on  mailLinks (mailId);";
	    session.execute(sql3);
	    
	    String sql4="CREATE INDEX statusf334 on  mailLinks (status);";
	    session.execute(sql4);
	    String sql2="CREATE INDEX fromId on  mailLinks (fromId);";
	    session.execute(sql2);
	    String sql5="CREATE INDEX toId on mailLinks  (toId);";
	    session.execute(sql5);
	    

	    
	   
	    System.out.println(" Done ");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public void createMailTable(){
		try {
	    System.out.println("2");
	    String query = "DROP TABLE mailDetails";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE mailDetails (\n"
		        + "    KEY text,\n"
		        + "    subject text,\n"
		        + "    message text,\n"
		        + "    mailTime timestamp,\n"
		        + "    ishidden int,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   session.execute(mytables);
	   
	    
	    
	   
	    System.out.println(" Done ");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public void createMessageNotification(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE commentNotification";
	  
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE commentNotification (\n"
		        + "    KEY text,\n"
		        + "    postId text,\n"
		        + "    postedByUserId text,\n"
		        + "    postcommentedByUserId text,\n"
		        + "    commentId text,\n"
		        + "    commentTime timestamp,\n"
		        + "    status int,\n"
		        + "    flow int,\n"
		        + "    indicator int,\n"
		        + "    message text,\n"
		        + "    commentIdDate timestamp,\n"
		        + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   session.execute(mytables);
	   

	    
	    String sql3="CREATE INDEX postedByUserIdssf on  commentNotification (postedByUserId);";
	    session.execute(sql3);
	    String sql1="CREATE INDEX postcommentedByUserId on  commentNotification (postcommentedByUserId);";
	    session.execute(sql1);

	    
	    String sql4="CREATE INDEX status34 on  commentNotification (status);";
	    session.execute(sql4);
	    String sql5="CREATE INDEX flow43 on  commentNotification (flow);";
	    session.execute(sql5);
	    
	    
	    System.out.println(" Done ");
	    
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createFollowerTable(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE follow";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE follow (\n"
		        + "    KEY text,\n"
		        + "    followBy text,\n"
		        + "    followTo text,\n"
		        + "    followStartTime timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (KEY,followTo)\n"
		        + ") WITH CLUSTERING ORDER BY (followTo DESC)\n"
		       // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "  AND   read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	    
	  session.execute(mytables);
	

	    String sql1="CREATE INDEX followBy on  follow (followBy);";
	    session.execute(sql1);
	 /*   String sql2="CREATE INDEX KEY334343 on  follow (KEY);";
	    session.execute(sql2);	    */
	    String sql4="CREATE INDEX indicator22 on  follow (indicator);";
	    session.execute(sql4);	    
	 
	    
	    
	/*    String sql6="CREATE INDEX userIdss on  tickerinfo (userId);";
	    session.execute(sql6);
	    
	    
	    String sql7="CREATE INDEX targetUserIdsds on  tickerinfo (targetUserId);";
	    session.execute(sql7);*/
	    System.out.println(" Done ");
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createLinksInfo(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE linkInfo";
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    
	    String mytables = "CREATE TABLE linkInfo (\n"
		        + "    KEY text,\n"
		        + "    name text,\n"
		        + "    empId text,\n"
		        + "    url text,\n"
		        + "    userName text,\n"
		        + "    password text,\n"
		        + "    orderNum int,\n"
		        + "    creationDate timestamp,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY )\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		     // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
		       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   
	    session.execute(mytables);
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsss on  linkInfo (empId);";
	    session.execute(sql1);
	   
	    
	    System.out.println(" Done");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createTagInfo(){

		try {
	    System.out.println("2");
	    String query = "DROP TABLE tag";
	  
	    try{
	    	session.execute(query);
	    }catch(Exception ex){}
	    String mytables = "CREATE TABLE tag (\n"
		        + "    KEY text,\n"
		        + "    photoId text,\n"
		        + "    ownerId text,\n"
		        + "    taggedUserId text,\n"
		        + "    description text,\n"
		        + "    tagTime timestamp,\n"
		        + "    follow int,\n"
		        + "    indicator int,\n"
		        + "    PRIMARY KEY (indicator,KEY)\n"
		        + ") WITH CLUSTERING ORDER BY (KEY DESC)\n"
		        // + "    PRIMARY KEY (indicator,commentTime,KEY)\n"
			       // + ") WITH CLUSTERING ORDER BY (commentTime DESC,KEY DESC)\n"
		        + "   AND read_repair_chance = 0.5\n"
		        + "   AND dclocal_read_repair_chance = 0.6\n"
		        + "   AND replicate_on_write = true\n"
		        + "   AND gc_grace_seconds = 42\n"
		        + "   AND bloom_filter_fp_chance = 0.01\n"
		        + "   AND caching = 'ALL'\n"
		        + "   AND comment = 'My awesome table'\n"
		        + "   AND compaction = { 'class' : 'org.apache.cassandra.db.compaction.LeveledCompactionStrategy', 'sstable_size_in_mb' : 15 }\n"
		        + "   AND compression = { 'sstable_compression' : 'org.apache.cassandra.io.compress.SnappyCompressor', 'chunk_length_kb' : 128 };";
	   session.execute(mytables);
	   
	   /**
	    * photoId text,\n"
		        + "    ownerId text,\n"
		        + "    taggedUserId text,\n"
		        + "    desc text,\n"
		        + "    tagTime timestamp,\n"
		        + "    follow int,\n"
		        + "    
	    */
	    
	    String sql3="CREATE INDEX photoIdabc on  tag (photoId);";
	    session.execute(sql3);
	    String sql1="CREATE INDEX ownerId34 on  tag (ownerId);";
	    session.execute(sql1);

	    
	    String sql4="CREATE INDEX taggedUserIdss on  tag (taggedUserId);";
	    session.execute(sql4);
	    String sql5="CREATE INDEX followss on  tag (follow);";
	    session.execute(sql5);
	    
	    
	    System.out.println(" Done ");
	    
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
