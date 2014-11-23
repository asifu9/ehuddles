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
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.util.Constant;
import com.pro.emp.util.PasswordService;

public class CasandraTestMore {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//new CasandraTest().insertTable();
		//new CasandraTest().createIndex();
		new CasandraTestMore().selectTable();
		//CasandraTestMore t=new CasandraTestMore();
		//t.forumNotification();
		//t.createVideoInfo();
		//t.createGroupPost();
		//t.createGroupPostLikeTable();
		//t.createGroupPostComments();
		//t.createFriendRequestTable();
		//t.createBulletinInfo();
		//t.createPhotAlbumNewField();
		//t.createPhotoInfoNewField();
		//t.createActivityTable();
		//t.createForumTable();
		//t.createForumDiscussionTable();
		//t.createGroupInfo();
		//t.createGroupUserLinkInfo();
		//t.updatePostTOPRivacy();
		//t.udpdateAddPostType();
		//t.AlterAddCoverPage();
		//t.createPostLikeTable();
		//t.createPostComments();
		//t.createPost();
		//t.craetePostNEwField();
		//t.createTickerInfo();
		//t.createLikeNotification();
		//t.createMessageNotification();
		//t.createDBTable();
		//t.createPhotoComments();
		//t.createPhotoInfo();
		//t.createPhotoAlbum();
		//t.createEmpAddress();
		//t.createEmpInfo();
		//t.createRoleTypes();
		//t.createDBTable();
		//t.createPhotoComments();
		//t.createPhotoAlbum();
		//t.createPhotoInfo();
		//t.createPhotoComments();
		//t.createDepartmentStatic();
		//t.createDesignationStatic();
		//t.createEmpExtraActivity();
		//t.createEmpIdProof();
		//t.createEduucationDetails();
		//t.createProfessionDetail();
		//t.createRole();
		//t.createCompanyInfoStatic();
		//t.createEmp_Company_info();
		//t.createAttendance();
		//t.createAttendanceComment();
		//t.createLeaveManagement();
	//	t.createMailTable();
	//	t.createMailLinkTable();
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
	
	
	public void insertTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			UUID id= java.util.UUID.randomUUID();
			//System.out.println(" uuid " + id.toString());
			//UUIDGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    
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
	    String sqlInsert12="INSERT INTO role (key,id,role,isActive,createDate,updateDate)"+
		"values(\'"+id12+"\','661a9626-9a74-4b96-bcf2-a100905588c4','"+Constant.SUPER_ADMIN+"',1,'"+Util.getCurrentTimestamp()+"','"+Util.getCurrentTimestamp()+"');";
	    
	  //  String sql="INSERT INTO liketable (key,itemId,likedUserId,likedDate)"+
	   // 			"values(\'"+id+"\',3,5,\'"+result+"\');";
	    		   /*"KEY uuid PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    
	    PreparedStatement statement = con.prepareStatement(sqlInsert12);
	    statement.executeUpdate();
//System.out.println(" DOne");
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
	public void createIndex(){
		//CREATE INDEX user_state
		//   ON users (state);


		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.11:9160/sanahempinfo");
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
			//System.out.println(" uuid " + id.toString());
			//UUIDGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
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
	    		   /*"KEY uuid PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    //String sql="select key,empid,year, month, day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31  from attendence where empid = 'cc341c64-6028-4b72-b470-2cce09b8ce10' and year = 2012 and month =1";
	   //String sql="select key,id,role,isActive,createdDate,updateDate  from role where 	id = 'cc341c64-6028-4b72-b470-2cce09b8ce10'";
	    //String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where id='cc341c64-6028-4b72-b470-2cce09b8ce10'"
	   // String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where privacy=1";
	    //String sql="select key,empid,year, month, day, description, time, commentedBy  from comments where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and year= 2012 and month=10 and day=7";
	   //String sql="select key,empid,year, month from attendence where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and  year= 2012";
	    //String sql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime from  post where indicator=1 AND postedTime < '2013-02-22 15:34:51' and postedTime > '2013-02-22 05:34:51'";

	    //String sql="Alter table commentNotification add message text";
	    //String sql="Alter table likeNotification add message text";
//	    String sql1="DROP INDEX idPhotoAlbum on  photoinfo ";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
	    
	   // PasswordService ps= new PasswordService();
	    //String pass=ps.encrypt("swetha");
	    String sql="select key,postedById ,postedDesc,postedPhotoId,postedVideoId ,postedToId,postedTime,postType from  post where indicator=1 AND postedTime < '2013-09-04 15:34:51' and postedTime > '2013-09-01 05:34:51'";
	    //String sql="update emp_info set password='"+pass+"' where key='f353987a-939e-40c6-b1f6-1b6d4ed1c05f'";
	    PreparedStatement statement = con.prepareStatement(sql);
	    ResultSet rs=statement.executeQuery();
	    System.out.println("done");
	    System.out.println(" here  i am " + sql);
	    int i=0;
	    while(rs.next()){
	    	System.out.println(" i " + i++);
	    	System.out.println(" rs.get next  "  + rs.getString(1) + " : " + " : " + rs.getString(5)+" : " + rs.getInt(8));//(//)+" : "+ rs.getString(2)+" : "+ rs.getString(3)+" : "+ rs.getString(4)+" : ");//+ rs.getString(3) +" : "+ rs.getTimestamp(7) +
	    }
//	    			//"  ");// + rs.getInt(5)+" : " + rs.getString(6)
//	    	//		+" : "+ rs.getString(7) + " : " + rs.getString(8));
//	    	//System.out.println(" rs.get next  "  + rs.getString(1)+" : "+ rs.getString(2) +" : "+ rs.getInt(3) + " : " + rs.getInt(4));
////	    	Timestamp s=rs.getTimestamp(4);
////	    	Calendar c= Calendar.getInstance();
////	    	Timestamp curre= new Timestamp(c.getTime().getTime());
////	    	int days= (int)(curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
////	    	int hours= (int)(curre.getTime()-s.getTime())/(60 * 60 * 1000);
////	    	int min= (int)(curre.getTime()-s.getTime())/(1000);
////	    	//double days= (curre.getTime()-s.getTime())/(24 * 60 * 60 * 1000);
////	    	System.out.println(" Days " + days + " hours " + hours  + " min " + min);
//	    	
//	    }
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
	public void updatePostTOPRivacy(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			UUID id= java.util.UUID.randomUUID();
			//System.out.println(" uuid " + id.toString());
			//UUIDGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/sanahempinfo");
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
	    		   /*"KEY uuid PRIMARY KEY,"+
	    		   "itemId bigint,"+
	    		   "likedUserId bigint,"+
	    		   "likedDate timestamp )";*/
	    //String sql="select key,empid,year, month, day1, day2, day3, day4, day5, day6, day7, day8, day9, day10, day11, day12, day13, day14, day15, day16, day17, day18, day19, day20, day21, day22, day23, day24, day25, day26, day27, day28, day29, day30, day31  from attendence where empid = 'cc341c64-6028-4b72-b470-2cce09b8ce10' and year = 2012 and month =1";
	   //String sql="select key,id,role,isActive,createdDate,updateDate  from role where 	id = 'cc341c64-6028-4b72-b470-2cce09b8ce10'";
	    //String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where id='cc341c64-6028-4b72-b470-2cce09b8ce10'"
	   // String sql="select key,name,coverScreenPath,createdOn,updatedOn,id,privacy from  photoAlbum where privacy=1";
	    //String sql="select key,empid,year, month, day, description, time, commentedBy  from comments where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and year= 2012 and month=10 and day=7";
	   //String sql="select key,empid,year, month from attendence where empid='cc341c64-6028-4b72-b470-2cce09b8ce10' and  year= 2012";
	    String sql="select key  from post";
	    PreparedStatement statement = con.prepareStatement(sql);
	    ResultSet rs= statement.executeQuery();
	    System.out.println(" here  i am " + sql);
	    while(rs.next()){
	    	//System.out.println(" rs.get next  "  + rs.getString(1)+" : "+ rs.getString(2) +" : "+ rs.getString(3) + 
	    	//		" : " + rs.getInt(4) +" : ");// + rs.getInt(5)+" : " + rs.getString(6)
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
	    	String sss= "update post set privatestatus=0 where key='"+rs.getString(1)+"'";
	    	PreparedStatement statement2 = con.prepareStatement(sss);
		    statement2.executeUpdate();
	    	statement2.close();
	    }
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
	public void udpdateAddPostType(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
	    Connection con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/sanahempinfo");
	    String sss= "alter table post ADD photoCount int";
	    PreparedStatement statement = con.prepareStatement(sss);
	    statement.executeUpdate();
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
	
	public void AlterAddCoverPage(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
	    Connection con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/sanahempinfo");
	    String sss= "alter table emp_info ADD coverPage text";
	    PreparedStatement statement = con.prepareStatement(sss);
	    statement.executeUpdate();
	    statement.close();
	    con.close();
	    System.out.println(" done ");
	    
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
			//System.out.println(" uuid " + id.toString());
			//UUIDGen.
			//2de92e58-593c-491e-bed4-3112726e0fc6
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.11:9160/sanahempinfo");
	    //System.out.println("2");
	    String sql="delete  from liketable ";// (key,itemid,likedUserId,likedDate)"+
	  // String sql="select key,name,id, privacy from photoAlbum";
	  //  String sql="select key,idPhotoAlbum,photoPath, description from photoinfo";
	    			//"values(\'"+id+"\',1,1,'2012-01-01');";
	    		   /*"KEY uuid PRIMARY KEY,"+
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
	public void createDBTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.11:9160/sanahempinfo");
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

	public void createPhotoComments(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.11:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE photoComments";
	    String sql="CREATE TABLE photoComments ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "photoId text,"+
	    		   "commentUserId text,"+
	    		   "commentUserName text,"+
	    		   "commentUserPhoto text,"+
	    		   "commentDesc text,"+
	    		   "commentDate timestamp )";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();

	    String sql1="CREATE INDEX photoId on  photoComments (photoId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX commentUserId on  photoComments (commentUserId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    statementss.close();
	    statement1.close();
	    statement2.close();
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
	
	public void createPhotoInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.11:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE photoinfo";
	    String sql="CREATE TABLE photoinfo ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "idPhotoAlbum text,"+
	    		   "photoPath text,"+
	    		   "description text,"+
	    		   "ownerId text,"+
	    		   "createdOn timestamp )";

	    PreparedStatement statementss = con.prepareStatement(query);
	    try {
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();

	    String sql1="CREATE INDEX idPhotoAlbum on  photoinfo (idPhotoAlbum);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();
	    statementss.close();
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
	
	public void createVideoInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE videoinfo";
	    String sql="CREATE TABLE videoinfo ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "videoPath text,"+
	    		   "videoPhotoPath text,"+
	    		   "description text,"+
	    		   "ownerId text,"+
	    		   "createdOn timestamp,indicator int )";

	    PreparedStatement statementss = con.prepareStatement(query);
	    try {
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();

	    String sql1="CREATE INDEX ownerIdssss on  videoinfo (ownerId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql3="CREATE INDEX indicatorsdxcxcddd on  videoinfo (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    statement1.close();
	    statement3.close();
	    statementss.close();
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
	

	public void createPhotoAlbum(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE photoAlbum";
	    String sql="CREATE TABLE photoAlbum ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "name text,"+
	    		   "coverScreenPath text,"+
	    		   "createdOn timestamp,"+
	    		   "updatedOn timestamp,"+
	    		   "id text,"+
	    		   "privacy bigint)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    String sql1="CREATE INDEX id on  photoAlbum (id);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    System.out.println(" craeted index  first one");
	    String sql2="CREATE INDEX privacy on  photoAlbum (privacy);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    System.out.println(" craete second first one");
	    statement1.close();
	    statement2.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//photoalbum
	
	
	public void createEmpInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE emp_info";
	    String sql="CREATE TABLE emp_info ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "empName text,"+
	    		   "dob timestamp,"+
	    		   "department bigint,"+
	    		   "designation bigint,"+
	    		   "doj timestamp,"+
	    		   "password text,"+
	    		   "imagePath text,"+
	    		   "loginName text,"+
	    		   "emailId text,"+
	    		   "isCalendarReview int,"+
	    		   "creationDate timestamp)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    String sql1="CREATE INDEX empId on  emp_info (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    System.out.println(" craeted index  first one");
	    String sql2="CREATE INDEX empName on  emp_info (empName);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    System.out.println(" craete second first one");
	    
	    String sql3="CREATE INDEX loginName on  emp_info (loginName);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    System.out.println(" craete third first one");
	    
	    
	    String sql4="CREATE INDEX department on  emp_info (department);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    System.out.println(" craete fourth first one");
	    
	    String sql5="CREATE INDEX designation on  emp_info (designation);";
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    System.out.println(" craete fifth first one");
	    statement1.close();
	    statement2.close();
	    statement4.close();
	    statement3.close();
	    statement5.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void createEmpAddress(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE empadddetails";
	    String sql="CREATE TABLE empadddetails ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "telephoneNo text,"+
	    		   "mobileNo text,"+
	    		   "Address text,"+
	    		   "city text,"+
	    		   "state text,"+
	    		   "country text,"+
	    		   "pincode text,"+
	    		   "refrence text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIds on  empadddetails (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createDepartmentStatic(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE empdepartment";
	    String sql="CREATE TABLE empdepartment ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "id bigint,"+
	    		   "depName text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX ids on  empdepartment (id);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    System.out.println(" craeted new one 2");
		    // insert records
	    	UUID id= java.util.UUID.randomUUID();
		    String sqlInsert1="INSERT INTO empdepartment (key,id,depName)"+
			"values(\'"+id+"\',1,'Software');";
		 
		    
		    
		    UUID id2= java.util.UUID.randomUUID();
		    String sqlInsert2="INSERT INTO empdepartment (key,id,depName)"+
			"values(\'"+id2+"\',2,'Operations');";
		    
		    UUID id3= java.util.UUID.randomUUID();
		    String sqlInsert3="INSERT INTO empdepartment (key,id,depName)"+
			"values(\'"+id3+"\',3,'AllFax');";
		    
		    UUID id4= java.util.UUID.randomUUID();
		    String sqlInsert4="INSERT INTO empdepartment (key,id,depName)"+
			"values(\'"+id4+"\',4,'Accounts');";

		    System.out.println(" craeted new : " + sqlInsert1);
		    
			PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
			inset1.executeUpdate();
			PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
			inset2.executeUpdate();
			PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
			inset3.executeUpdate();
			PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
			inset4.executeUpdate();
			


		inset1.close();
		inset2.close();
		inset3.close();
		inset4.close();
		statement.close();
	    statement1.close();
	    statementss.close();
	  
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createDesignationStatic(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE empdesignation";
	    String sql="CREATE TABLE empdesignation ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "id bigint,"+
	    		   "depId bigint,"+
	    		   "name text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX depId on  empdesignation (depId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    String sql2="CREATE INDEX idss on  empdesignation (id);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    
		    // insert records
			UUID id= java.util.UUID.randomUUID();
		    String sqlInsert1="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id+"\',1,1,'Software Engineer');";
		 
		    UUID id2= java.util.UUID.randomUUID();
		    String sqlInsert2="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id2+"\',2,1,'Team Lead');";
		    
		    UUID id3= java.util.UUID.randomUUID();
		    String sqlInsert3="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id3+"\',3,1,'Junior Software Engineer');";
		    
		    UUID id4= java.util.UUID.randomUUID();
		    String sqlInsert4="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id4+"\',4,2,'Manager');";
		   
		    UUID id5= java.util.UUID.randomUUID();
		    String sqlInsert5="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id5+"\',5,2,'Data Entry');";
		    
		    UUID id6= java.util.UUID.randomUUID();
		    String sqlInsert6="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id6+"\',6,2,'QA');";
		    
		    UUID id7= java.util.UUID.randomUUID();
		    String sqlInsert7="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id7+"\',7,2,'Review');";
		    
		    UUID id8= java.util.UUID.randomUUID();
		    String sqlInsert8="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id8+"\',8,2,'Team Lead');";
		
		    //
		    UUID id9= java.util.UUID.randomUUID();
		    String sqlInsert9="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id9+"\',9,3,'Team Lead');";
		    
		    UUID id10= java.util.UUID.randomUUID();
		    String sqlInsert10="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id10+"\',10,3,'Data Entry');";
		    
		    UUID id11= java.util.UUID.randomUUID();
		    String sqlInsert11="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id11+"\',11,3,'Manager');";
		    
		    UUID id12= java.util.UUID.randomUUID();
		    String sqlInsert12="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id12+"\',12,3,'QA');";
		    
		    UUID id13= java.util.UUID.randomUUID();
		    String sqlInsert13="INSERT INTO empdesignation (key,id,depId,name)"+
			"values(\'"+id13+"\',13,4,'Accounts');";
		    
		    
		    
		    
			PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
			inset1.executeUpdate();
			PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
			inset2.executeUpdate();
			PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
			inset3.executeUpdate();
			PreparedStatement inset4 = con.prepareStatement(sqlInsert5);
			inset4.executeUpdate();
			PreparedStatement inset5 = con.prepareStatement(sqlInsert6);
			inset5.executeUpdate();
			PreparedStatement inset6 = con.prepareStatement(sqlInsert7);
			inset6.executeUpdate();
			PreparedStatement inset7 = con.prepareStatement(sqlInsert8);
			inset7.executeUpdate();
			PreparedStatement inset8 = con.prepareStatement(sqlInsert9);
			inset8.executeUpdate();
			PreparedStatement inset9 = con.prepareStatement(sqlInsert10);
			inset9.executeUpdate();
			PreparedStatement inset10 = con.prepareStatement(sqlInsert11);
			inset10.executeUpdate();
			PreparedStatement inset11 = con.prepareStatement(sqlInsert12);
			inset11.executeUpdate();
			PreparedStatement inset12 = con.prepareStatement(sqlInsert13);
			inset12.executeUpdate();
			PreparedStatement inset13 = con.prepareStatement(sqlInsert4);
			inset13.executeUpdate();
			
		


			inset1.close();
			inset2.close();
			inset3.close();
			inset4.close();
			inset5.close();
			inset6.close();
			inset7.close();
			inset8.close();
			inset9.close();
			inset10.close();
			inset11.close();
			inset12.close();
			inset13.close();
	    
	    statement2.close();
	    statement1.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createEmpExtraActivity(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE empextraactivities";
	    String sql="CREATE TABLE empextraactivities ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "typeOfActivity text,"+
	    		   "comment text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdss on  empextraactivities (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createEmpIdProof(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE id_proof";
	    String sql="CREATE TABLE id_proof ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "attachment text,"+
	    		   "description text,"+
	    		   "attachmentPath text,"+
	    		   "typeOfProof text,"+
	    		   "attachmentThumbnail text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsss on  id_proof (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createFriendRequestTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	   
	   // String query = "DROP TABLE friends";
	    String sql="CREATE TABLE friends ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "fromId text,"+
	    		   "toId text,"+
	    		   "status int,"+
	    		   "requestedTime timestamp,"+
	    		   "responsedTime timestamp,"+
	    		   "indicator int)";
//	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	   // statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one ddd" + sql);
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	  System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX fromIdff on  friends (fromId);";
	    System.out.println("2" + sql1);
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    System.out.println("3");
	    statement1.executeUpdate();
	    System.out.println("4");
	    
	    String sql2="CREATE INDEX toIdff on  friends (toId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX statusssssf on  friends (status);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    String sql4="CREATE INDEX indicator5443322f on  friends (indicator);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    

	    statement1.close();

	   // statementss.close();
	   // statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertRole(){
		
		try {
				Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
				System.out.println("1");
				String empId="";
			    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
			    System.out.println("2");
			    String role="";
		    
		    
		    	role="";
		    	UUID id= java.util.UUID.randomUUID();
				 String inssql="INSERT INTO role(key,id,role,isActive,createdDate,updateDate" +
		         ") VALUES('"+id+"','"+empId+"','"+role+"',1,'"+Util.getCurrentTimestamp()+"','"+Util.getCurrentTimestamp()+"')";
		}
		
		
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void createRole(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE role";
	    String sql="CREATE TABLE role ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "id text,"+
	    		   "role text,"+
	    		   "isActive int,"+
	    		   "createdDate timestamp,"+
	    		   "updateDate timestamp)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX idsss on  role (id);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    String sql3="CREATE INDEX role1 on  role (role);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    String sql2="CREATE INDEX isActive on  role (isActive);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createCompanyInfoStatic(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE companyinfo";
	    String sql="CREATE TABLE companyinfo ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "companyName text,"+
	    		   "companyURL text,"+
	    		   "companyImage text)";
	    		
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    
			    // insert records
		    	UUID id= java.util.UUID.randomUUID();
			    String sqlInsert1="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
				"values(\'"+id+"\','RAIR','www.rair.com','images/rairLogo.jpg');";
			 
			    UUID id2= java.util.UUID.randomUUID();
			    String sqlInsert2="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
				"values(\'"+id2+"\','ACS','acs.com','images/acsLogo.png');";
			    
			    UUID id3= java.util.UUID.randomUUID();
			    String sqlInsert3="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
				"values(\'"+id3+"\','Qualcomm','qualcomm.com','images/qualcommLogo.png');";
			    
			    UUID id4= java.util.UUID.randomUUID();
			    String sqlInsert4="INSERT INTO companyinfo (key,companyName,companyURL,companyImage)"+
				"values(\'"+id4+"\','AllFax','www.allfax.com','images/allfaxLogo.png');";
		
				PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
				inset1.executeUpdate();
				PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
				inset2.executeUpdate();
				PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
				inset3.executeUpdate();
				PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
				inset4.executeUpdate();
				
			    inset1.close();
			    inset2.close();
			    inset3.close();
			    inset4.close();
			    
//	    String sql1="CREATE INDEX id on  role (id);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
	    
//	    String sql2="CREATE INDEX isActive on  role (isActive);";
//	    PreparedStatement statement2 = con.prepareStatement(sql2);
//	    statement2.executeUpdate();
	    
				
	   // statement1.close();
	   // statement2.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createEmp_Company_info(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE emp_company_info";
	    String sql="CREATE TABLE emp_company_info ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "companyId text)";
	    		
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsssss on  emp_company_info (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
//	    
	    String sql2="CREATE INDEX companyId on  emp_company_info (companyId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
//	    
	    
	    statement1.close();
	    statement2.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createRoleTypes(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE roletypes";
	    String sql="CREATE TABLE roletypes ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "name text,"+
	    		   "department text)";
	    		   
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    String sql1="CREATE INDEX department_index on  roletypes (department);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    
	    //

			    // insert records
		    	UUID id= java.util.UUID.randomUUID();
			    String sqlInsert1="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id+"\','profileedit','Data entry');";
			 
			    UUID id2= java.util.UUID.randomUUID();
			    String sqlInsert2="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id2+"\','profileview','Data entry');";
			    
			    UUID id3= java.util.UUID.randomUUID();
			    String sqlInsert3="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id3+"\','photoview','Data entry');";
			    
			    UUID id4= java.util.UUID.randomUUID();
			    String sqlInsert4="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id4+"\','photoedit','Data entry');";
			    
			    UUID id5= java.util.UUID.randomUUID();
			    String sqlInsert5="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id5+"\','calendarview','Data entry');";
			    
			    UUID id6= java.util.UUID.randomUUID();
			    String sqlInsert6="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id6+"\','calendaredit','Data entry');";
			    
			    UUID id7= java.util.UUID.randomUUID();
			    String sqlInsert7="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id7+"\','depAdmin','Data entry');";
			    
			    UUID id8= java.util.UUID.randomUUID();
			    String sqlInsert8="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id8+"\','linkProfile','Data entry');";
			    
			    UUID id9= java.util.UUID.randomUUID();
			    String sqlInsert9="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id9+"\','linkPhoto','Data entry');";
			    
			    UUID id10= java.util.UUID.randomUUID();
			    String sqlInsert10="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id10+"\','linkCalendar','Data entry');";
			    
			    UUID id11= java.util.UUID.randomUUID();
			    String sqlInsert11="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id11+"\','roleEdit','Data entry');";
			    
			    UUID id12= java.util.UUID.randomUUID();
			    String sqlInsert12="INSERT INTO roletypes (key,name,department)"+
				"values(\'"+id12+"\','roleView','Data entry');";
		
				PreparedStatement inset1 = con.prepareStatement(sqlInsert1);
				inset1.executeUpdate();
				PreparedStatement inset2 = con.prepareStatement(sqlInsert2);
				inset2.executeUpdate();
				PreparedStatement inset3 = con.prepareStatement(sqlInsert3);
				inset3.executeUpdate();
				PreparedStatement inset4 = con.prepareStatement(sqlInsert4);
				inset4.executeUpdate();
				PreparedStatement inset5 = con.prepareStatement(sqlInsert5);
				inset5.executeUpdate();
				PreparedStatement inset6 = con.prepareStatement(sqlInsert6);
				inset6.executeUpdate();
				PreparedStatement inset7 = con.prepareStatement(sqlInsert7);
				inset7.executeUpdate();
				PreparedStatement inset8 = con.prepareStatement(sqlInsert8);
				inset8.executeUpdate();
				PreparedStatement inset9 = con.prepareStatement(sqlInsert9);
				inset9.executeUpdate();
				PreparedStatement inset10 = con.prepareStatement(sqlInsert10);
				inset10.executeUpdate();
				PreparedStatement inset11 = con.prepareStatement(sqlInsert11);
				inset11.executeUpdate();
				PreparedStatement inset12 = con.prepareStatement(sqlInsert12);
				inset12.executeUpdate();
				
				//
	    
				inset1.close();
				inset2.close();
				inset3.close();
				inset4.close();
				inset5.close();
				inset6.close();
				inset7.close();
				inset8.close();
				inset9.close();
				inset10.close();
				inset11.close();
				inset12.close();
	    
	    statement1.close();
	   
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createAttendance(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE attendence";
	    String sql="CREATE TABLE attendence ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "year int,"+
	    		   "month int,"+
	    		   "day1 text,"+
	    		   "day2 text,"+
	    		   "day3 text,"+
	    		   "day4 text,"+
	    		   "day5 text,"+
	    		   "day6 text,"+
	    		   "day7 text,"+
	    		   "day8 text,"+
	    		   "day9 text,"+
	    		   "day10 text,"+
	    		   "day11 text,"+
	    		   "day12 text,"+
	    		   "day13 text,"+
	    		   "day14 text,"+
	    		   "day15 text,"+
	    		   "day16 text,"+
	    		   "day17 text,"+
	    		   "day18 text,"+
	    		   "day19 text,"+
	    		   "day20 text,"+
	    		   "day21 text,"+
	    		   "day22 text,"+
	    		   "day23 text,"+
	    		   "day24 text,"+
	    		   "day25 text,"+
	    		   "day26 text,"+
	    		   "day27 text,"+
	    		   "day28 text,"+
	    		   "day29 text,"+
	    		   "day30 text,"+
	    		   "isSubmitted int,"+
	    		   "submitDeportment text,"+
	    		   "day31 text)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdssssss on  attendence (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
//	    
	    String sql2="CREATE INDEX year on  attendence (year);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX month on  attendence (month);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    String sql4="CREATE INDEX isSubmitted on  attendence (isSubmitted);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	    String sql5="CREATE INDEX submitDeportment on  attendence (submitDeportment);";
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
//	    
	    
	    
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createAttendanceComment(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE comments";
	    String sql="CREATE TABLE comments ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "description text,"+
	    		   "time timestamp,"+
	    		   "commentedBy text,"+
	    		   "day int,"+
	    		   "year int,"+
	    		   "month int)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsssssss on  comments (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
//	    
	    String sql2="CREATE INDEX year2 on  comments (year);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX month2 on  comments (month);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    String sql4="CREATE INDEX day2 on  comments (day);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
//	    
	    
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createLeaveManagement(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE leavemanagement";
	    String sql="CREATE TABLE leavemanagement ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "year int,"+
	    		   "paidLeavesTaken int,"+
	    		   "sickLeavesTaken int,"+
	    		   "doj timestamp,"+
	    		   "carryOverLeaves float,"+
	    		   "month text,"+
	    		   "endyear int,"+
	    		   "endmonth int,"+
	    		   "totalPaidLeaves float)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdssssssss on  leavemanagement (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
//	    
	    String sql2="CREATE INDEX year3 on  leavemanagement (year);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX month3 on  leavemanagement (month);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
//	    String sql4="CREATE INDEX day2 on  leavemanagement (day);";
//	    PreparedStatement statement4 = con.prepareStatement(sql4);
//	    statement4.executeUpdate();
//	    
	    
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void createProfessionDetail(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE professional";
	    String sql="CREATE TABLE professional ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "companyName text,"+
	    		   "designation text,"+
	    		   "workedFrom timestamp,"+
	    		   "workedTo timestamp)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empId2ssss on  professional (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createEduucationDetails(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE education";
	    String sql="CREATE TABLE education ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "empId text,"+
	    		   "collegeName text,"+
	    		   "course text,"+
	    		   "start timestamp,"+
	    		   "end timestamp)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsss1ss on  education (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createCalendarSumbitLink(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE cal_submit";
	    String sql="CREATE TABLE cal_submit ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "department text,"+
	    		   "submitEmpId text,"+
	    		   "course text,"+
	    		   "start timestamp,"+
	    		   "end timestamp)";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	    String sql1="CREATE INDEX empIdsss1ss on  education (empId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void craetePostNEwField(){
		
		
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/sanahempinfo");
	    System.out.println("2");
	   // String query = "DROP TABLE post";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="ALTER TABLE post ADD privatestatus int";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	   // PreparedStatement statementss = con.prepareStatement(query);
	    //try{
	    //statementss.executeUpdate();
	   // }catch(Exception exxx){}
	    //System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX privatestatus on  post (privatestatus);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    statement1.close();
	   
	    statement.close();
	    
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPost(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE post";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="CREATE TABLE post ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "postedById text,"+
	    		   "postedDesc text,"+
	    		   "postedPhotoId varchar,"+
	    		   "postedVideoId text,"+
	    		   "postedToId text,"+
	    		   "indicator int,postedTime timestamp) WITH comparator=timestamp ";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX postedById on  post (postedById);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    String sql2="CREATE INDEX postedVideoId on  post (postedVideoId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX postedPhotoId on  post (postedPhotoId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    
	    statement1.close();
	    statement2.close();
	    statement3.close();

	    statementss.close();
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicator447 on  post (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createGroupPost(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE GroupDiscussionPost";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="CREATE TABLE GroupDiscussionPost ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "groupId text,"+
	    		   "postedById text,"+
	    		   "desc text,"+
	    		   "postedPhotoId text,"+
	    		   "postedVideoId text,"+
	    		   "indicator int,postedTime timestamp)";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX postedByIssd on  GroupDiscussionPost (postedById);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    String sql2="CREATE INDEX postedVidedddoId on  GroupDiscussionPost (postedVideoId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql3="CREATE INDEX groupIdss on  GroupDiscussionPost (groupId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql7="CREATE INDEX postdsdsedTime on  GroupDiscussionPost (postedTime);";
	    PreparedStatement statement7 = con.prepareStatement(sql7);
	    statement7.executeUpdate();
	    
	    statement7.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();

	    statementss.close();
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicatoddr447 on  GroupDiscussionPost (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createGroupInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE group";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="CREATE TABLE group ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "name text,"+
	    		   "ownerId text,description text,photoId text,"+
	    		   "indicator int,createdOn timestamp) ";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX ownerIdss on  group (ownerId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	  

	    
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicatorss447df on  group (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createGroupUserLinkInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE userGroups";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="CREATE TABLE userGroups ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "groupId text,"+
	    		   "userId text,joinedOn timestamp,"+
	    		   "indicator int) ";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX groupIdfsds on  userGroups (groupId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	    String sql6="CREATE INDEX usdfserId on  userGroups (userId);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();

	    
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    statement6.close();
	    String ssfff="CREATE INDEX indicatorss4ss47df on  userGroups (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/////// for post info
	public void createBulletinInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE publicChat";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="CREATE TABLE publicChat ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "postedFromId text,"+
	    		   "postedInfo text,"+
	    		   "indicator int,postedTime timestamp) ";
	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" deleted first one");
	    
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    System.out.println(" craeted new one");
	    
	  
	    String sql1="CREATE INDEX postedByIdssssfdfdfddf on  publicChat (postedFromId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    
	  

	    
	    
	    statement1.close();

	    statementss.close();
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicator447df on  publicChat (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createPostLikeTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			System.out.println("hi");
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    //System.out.println("2");
	    String query = "DROP TABLE likePost";
	    String sql="CREATE TABLE likePost ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "likedUserId text,"+
	    		   "likedDate timestamp, indicator int)";
	    try{
	    PreparedStatement statement1 = con.prepareStatement(query);
	  
	    statement1.executeUpdate();
	    statement1.close();
	    }catch(Exception exxx ){exxx.printStackTrace();}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    String sql2="CREATE INDEX postId on  likePost (postId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    String sql3="CREATE INDEX likedUserId2 on  likePost (likedUserId);";
	    PreparedStatement statement4 = con.prepareStatement(sql3);
	    statement4.executeUpdate();
	    statement2.close();
	    statement4.close();
	    
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicator17 on  likePost (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    
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
	public void createGroupPostLikeTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			//System.out.println("1");
			System.out.println("hi");
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    //System.out.println("2");
	    String query = "DROP TABLE likeGroupPost";
	    String sql="CREATE TABLE likeGroupPost ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "likedUserId text,"+
	    		   "likedDate timestamp, indicator int)";
	    try{
	    PreparedStatement statement1 = con.prepareStatement(query);
	  
	    statement1.executeUpdate();
	    statement1.close();
	    }catch(Exception exxx ){exxx.printStackTrace();}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    String sql2="CREATE INDEX postIdss on  likeGroupPost (postId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    String sql3="CREATE INDEX likedUserIdd2 on  likeGroupPost (likedUserId);";
	    PreparedStatement statement4 = con.prepareStatement(sql3);
	    statement4.executeUpdate();
	    statement2.close();
	    statement4.close();
	    
	    statement.close();
	    
	    String ssfff="CREATE INDEX indicatdsdssor17 on  likeGroupPost (indicator);";
	    PreparedStatement sssfffff = con.prepareStatement(ssfff);
	    sssfffff.executeUpdate();
	    sssfffff.close();
	    
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
	public void createPostComments(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE postComments";
	    String sql="CREATE TABLE postComments ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "commentUserId text,"+
	    		   "commentDesc text,"+
	    		   "indicator int,"+
	    		   "commentDate timestamp )";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX indicator1111 on  postComments (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX postId2 on  postComments (postId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX commentUserId2 on  postComments (commentUserId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
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
	
	public void createGroupPostComments(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE postGroupComments";
	    String sql="CREATE TABLE postGroupComments ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "commentUserId text,"+
	    		   "commentDesc text,"+
	    		   "indicator int,"+
	    		   "commentDate timestamp )";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX indicator1fds111 on  postGroupComments (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX postIdsdsdd2 on  postGroupComments (postId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX commesdsdsntUserId2 on  postGroupComments (commentUserId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
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

	// start from here
	public void createTickerInfo(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://127.0.0.1:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE tickerinfo";
	    String sql="CREATE TABLE tickerinfo ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "userId text,"+
	    		   "targetUserId text,"+
	    		   "photoId text,"+
	    		   "commentId text,"+
	    		   "likeId text,"+
	    		   "commentDesc text,"+
	    		   "tickerType text,"+
	    		   "indicator int,"+
	    		   "tickertime timestamp )";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX indicator11311 on  tickerinfo (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX photoIdsdf on  tickerinfo (photoId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX commentIdwewe on  tickerinfo (commentId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql4="CREATE INDEX likeIddfd on  tickerinfo (likeId);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	 
	    
	    
	    String sql6="CREATE INDEX userIdss on  tickerinfo (userId);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	    
	    String sql7="CREATE INDEX targetUserIdsds on  tickerinfo (targetUserId);";
	    PreparedStatement statement7 = con.prepareStatement(sql7);
	    statement7.executeUpdate();
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	  
	    statement6.close();
	    statement7.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void createLikeNotification(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE likeNotification";
	    String sql="CREATE TABLE likeNotification ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "postedByUserId text,postLikedByUserId text,"+
	    		   "likeId text,"+
	    		   "likedTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "flow int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX postedByUserId on  likeNotification (postedByUserId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX postLikedByUserId on  likeNotification (postLikedByUserId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX likedTime on  likeNotification (likedTime);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql4="CREATE INDEX status on  likeNotification (status);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	    String sql5="CREATE INDEX flow on  likeNotification (flow);";
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    
	    
	    String sql6="CREATE INDEX indicator23232 on  likeNotification (indicator);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	   
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statement6.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createMailLinkTable(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE mailLinks";
	    String sql="CREATE TABLE mailLinks ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "mailId text,"+
	    		   "fromId text,"+
	    		   "toId text,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "mailTime timestamp"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX mailId on  mailLinks (mailId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    String sql4="CREATE INDEX statusf334 on  mailLinks (status);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	    String sql2="CREATE INDEX fromId on  mailLinks (fromId);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql5="CREATE INDEX toId on mailLinks  (toId);";
	    PreparedStatement statement51 = con.prepareStatement(sql5);
	    statement51.executeUpdate();
	    
	    String sql7="CREATE INDEX mailT2ime on mailLinks  (mailTime);";
	    PreparedStatement statement7 = con.prepareStatement(sql7);
	    statement7.executeUpdate();
	    
	    
	    String sql6="CREATE INDEX indicator232sf3342 on  mailLinks (indicator);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	   
	    statementss.close();
	    statement3.close();
	    statement4.close();
	    statement51.close();
	    statement7.close();
	    statement6.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public void createMailTable(){
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE mailDetails";
	    String sql="CREATE TABLE mailDetails ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "subject text,"+
	    		   "message text,"+
	    		   "mailTime timestamp,"+
	    		   "indicator int,"+
	    		   "ishidden int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX mailTime on  mailDetails (mailTime);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	    
	    
	    
	    String sql6="CREATE INDEX indicator2334343342343 on  mailDetails (indicator);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	   
	    statementss.close();
	    statement3.close();
	    statement6.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public void createMessageNotification(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.10:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE commentNotification";
	    String sql="CREATE TABLE commentNotification ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "postId text,"+
	    		   "postedByUserId text,postcommentedByUserId text,"+
	    		   "commentId text,"+
	    		   "commentTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "flow int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX postedByUserIdssf on  commentNotification (postedByUserId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX postcommentedByUserId on  commentNotification (postcommentedByUserId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX commentTime on  commentNotification (commentTime);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql4="CREATE INDEX status34 on  commentNotification (status);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	    String sql5="CREATE INDEX flow43 on  commentNotification (flow);";
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    
	    
	    String sql6="CREATE INDEX indicator232342 on  commentNotification (indicator);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	   
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statement6.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPhotAlbumNewField(){
		
		
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	   // String query = "DROP TABLE post";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
//	    String sql="ALTER TABLE photoAlbum ADD albumType int";
//	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
//	   // PreparedStatement statementss = con.prepareStatement(query);
//	    //try{
//	    //statementss.executeUpdate();
//	   // }catch(Exception exxx){}
//	    //System.out.println(" deleted first one");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	  
//	    String sql1="CREATE INDEX albumType on  photoAlbum (albumType);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
	    
	    String sql3="Update photoAlbum set albumType=1";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    
	  //  statement1.close();
	   
	  //  statement.close();
	    
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void createPhotoInfoNewField(){
		
		
		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	   // String query = "DROP TABLE post";
	  // String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
	    String sql="ALTER TABLE photoInfo ADD photoCreationTime timestamp";
//	    		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
	    PreparedStatement statementss = con.prepareStatement(sql);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    System.out.println(" added new column");
//	    
//	    PreparedStatement statement = con.prepareStatement(sql);
//	    statement.executeUpdate();
//	    System.out.println(" craeted new one");
//	    
//	  
//	    String sql1="CREATE INDEX albumType on  photoAlbum (albumType);";
//	    PreparedStatement statement1 = con.prepareStatement(sql1);
//	    statement1.executeUpdate();
	    
//	    String sql3="Update photoAlbum set albumType=1";
//	    PreparedStatement statement3 = con.prepareStatement(sql3);
//	    statement3.executeUpdate();
//	    
	  //  statement1.close();
	   
	  //  statement.close();
	    
	    con.close();
	    System.out.println(" Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

public void updateTimeFieldOfPhoto(){
	try{
	Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
	System.out.println("1");
	
Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
System.out.println("2");
// String query = "DROP TABLE post";
// String sql="CREATE TABLE post (postKey uuid PRIMARY KEY,postedTime timestamp ) WITH comparator=timestamp AND default_validation=int";
String sql="ALTER TABLE photoInfo ADD photoCreationTime timestamp";
//		   //"//WITH comparator=timestamp AND comment='user information' AND read_repair_chance = 1.0;";
PreparedStatement statementss = con.prepareStatement(sql);
try{
statementss.executeUpdate();
}catch(Exception exxx){}
System.out.println(" added new column");
//
//PreparedStatement statement = con.prepareStatement(sql);
//statement.executeUpdate();
//System.out.println(" craeted new one");
//
//
//String sql1="CREATE INDEX albumType on  photoAlbum (albumType);";
//PreparedStatement statement1 = con.prepareStatement(sql1);
//statement1.executeUpdate();

//String sql3="Update photoAlbum set albumType=1";
//PreparedStatement statement3 = con.prepareStatement(sql3);
//statement3.executeUpdate();
//
//  statement1.close();

//  statement.close();

con.close();
System.out.println(" Done");
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
	public void createActivityTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE activity";
	    String sql="CREATE TABLE activity ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "activityType text,"+
	    		   "fromId text,toId text,"+
	    		   "postId text,"+
	    		   "groupId text,"+
	    		    "commentId text,"+
	    		   "activityTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int,"+
	    		   "flow int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX activityType on  activity (activityType);";
	    String sql4="CREATE INDEX fromId11 on  activity (fromId);";
	    String sql5="CREATE INDEX toId11 on  activity (toId);";
	    String sql6="CREATE INDEX postId11 on  activity (postId);";
	    String sql7="CREATE INDEX groupId11 on  activity (groupId);";
	    String sql8="CREATE INDEX activityTime11 on  activity (activityTime);";
	    String sql9="CREATE INDEX status11 on  activity (status);";
	    String sql10="CREATE INDEX indicator3232 on  activity (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    PreparedStatement statement7 = con.prepareStatement(sql7);
	    statement7.executeUpdate();
	    PreparedStatement statement8 = con.prepareStatement(sql8);
	    statement8.executeUpdate();
	    PreparedStatement statement9 = con.prepareStatement(sql9);
	    statement9.executeUpdate();
	    PreparedStatement statement10 = con.prepareStatement(sql10);
	    statement10.executeUpdate();
	   
	    statementss.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statement6.close();
	    statement7.close();
	    statement8.close();
	    statement9.close();
	    statement10.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * private String key;
	private String ownerId;
	private String subject;
	private String forumType;
	private String body;
	private Timestamp time;
 */
	
	public void createForumTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE forum";
	    String sql="CREATE TABLE forum ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "ownerId text,"+
	    		   "body text,"+
	    		   "subject text,"+
	    		   "forumType int,"+
	    		   "time timestamp,"+
	    		   "status int,"+
	    		   "indicator int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX ownerIxdss on  forum (ownerId);";
	    String sql4="CREATE INDEX forumTypess on  forum (forumType);";
	    String sql5="CREATE INDEX timessddsd on  forum (time);";
	    String sql6="CREATE INDEX statussdsdsd on  forum (status);";
	    String sql7="CREATE INDEX indicatordsdsds on  forum (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    PreparedStatement statement7 = con.prepareStatement(sql7);
	    statement7.executeUpdate();
	   
	    statementss.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statement6.close();
	    statement7.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void createForumDiscussionTable(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			/**
			 * private String key;
	private String forumId;
	private String replierId;
	private EmpInfo replierIdInfo;
	private String body;
	private Timestamp time;
	private String timeStr;
			 */
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE forumDiscussion";
	    String sql="CREATE TABLE forumDiscussion ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "forumId text,"+
	    		   "replierId text,"+
	    		   "body text,"+
	    		   "time timestamp,"+
	    		   "indicator int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX forumId123 on  forumDiscussion (forumId);";
	    String sql4="CREATE INDEX timesdsdssss on  forumDiscussion (time);";
	    String sql5="CREATE INDEX indicatorwe2323 on  forumDiscussion (indicator);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    PreparedStatement statement5 = con.prepareStatement(sql5);
	    statement5.executeUpdate();
	    statementss.close();
	    statement3.close();
	    statement4.close();
	    statement5.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void forumNotification(){

		try {
			Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
			System.out.println("1");
			
	    Connection con = DriverManager.getConnection("jdbc:cassandra://172.16.136.16:9160/sanahempinfo");
	    System.out.println("2");
	    String query = "DROP TABLE forumNotification";
	    String sql="CREATE TABLE forumNotification ("+
	    		   "KEY uuid PRIMARY KEY,"+
	    		   "forumId text,"+
	    		   "notifyingUserId text,"+
	    		   "postedByUserId text,"+
	    		   "postedTime timestamp,"+
	    		   "status int,"+
	    		   "indicator int"+
	    		  ")";
	    
	    PreparedStatement statementss = con.prepareStatement(query);
	    try{
	    statementss.executeUpdate();
	    }catch(Exception exxx){}
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.executeUpdate();
	    
	    String sql3="CREATE INDEX forumIdssdf on  forumNotification (forumId);";
	    PreparedStatement statement3 = con.prepareStatement(sql3);
	    statement3.executeUpdate();
	    String sql1="CREATE INDEX postedByUserIdsdffs on  forumNotification (postedByUserId);";
	    PreparedStatement statement1 = con.prepareStatement(sql1);
	    statement1.executeUpdate();
	    String sql2="CREATE INDEX postedTimessdf on  forumNotification (postedTime);";
	    PreparedStatement statement2 = con.prepareStatement(sql2);
	    statement2.executeUpdate();
	    
	    String sql4="CREATE INDEX statuszsddsdss on  forumNotification (status);";
	    PreparedStatement statement4 = con.prepareStatement(sql4);
	    statement4.executeUpdate();
	    
	  
	    
	    String sql6="CREATE INDEX indicator23dfdd2342 on  forumNotification (indicator);";
	    PreparedStatement statement6 = con.prepareStatement(sql6);
	    statement6.executeUpdate();
	    
	   
	    statementss.close();
	    statement1.close();
	    statement2.close();
	    statement3.close();
	    statement4.close();
	    statement6.close();
	    statement.close();
	    con.close();
	    System.out.println(" Done ");
	    
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
