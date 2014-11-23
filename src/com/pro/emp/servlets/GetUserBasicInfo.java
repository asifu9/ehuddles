package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.Session_control;
import com.pro.emp.SortComments;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.FollowDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PublicChatDAO;
import com.pro.emp.domain.Domain;
import com.pro.emp.domain.DomainMix;
import com.pro.emp.domain.DomainUserMix;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PublicChat;

/**
 * Servlet implementation class GetUserBasicInfo
 */
@WebServlet("/GetUserBasicInfo")
public class GetUserBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
     public GetUserBasicInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {

      super.init(config);
     
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
    	response.setContentType("application/json");
    	PrintWriter writer = response.getWriter();
    	String id=null;
    	if(request.getParameter("id")!=null){
    		id=request.getParameter("id");
    	}
    	final ExecutorService exec = Executors.newSingleThreadExecutor();
    	 DomainUserMix mix=new DomainUserMix();
    	 
 	   		String empId= Session_control.getSession(request).getId();
 	   		if(id!=null)
 	   			empId=id;
	        List<Callable<List<Domain>>> tasks = new ArrayList<Callable<List<Domain>>>();
	      //  List<Callable<Boolean>> tasks2 = new ArrayList<Callable<Boolean>>();
	        Callable<List<Domain>> basicInfo=new UserBasicInfo(empId);
	       // Callable<List<Domain>> birth=new BirthdayList();
	      //  Callable<List<Domain>> chaats=new PublicChatAsch();
	        tasks.add(basicInfo);
	      //  tasks.add( birth);
	       // tasks.add( chaats);
	       
	         try {
				List<Future<List<Domain>>> f=exec.invokeAll(tasks,5,TimeUnit.MINUTES);
				
				
				for(Future<List<Domain>> result:f){
					System.out.println(" result.getClass() "  );
					List<Domain> ss=result.get();
					if(ss!=null){
						for(Domain d:ss){
							System.out.println(d.getClass());
							if(d instanceof com.pro.post.domain.UserBasicInfo){
								mix.setInfo((com.pro.post.domain.UserBasicInfo)d);
							}
							/*else if(d instanceof EmpInfo){
								mix.getEmp().add((EmpInfo)d);
								System.out.println(" ok not understanding");
							}*/
						}
					}
					//for(     resut1:)
				}
				
				
				System.out.println(" first one " + Calendar.getInstance().getTime());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         System.out.println(" first one 2 " + Calendar.getInstance().getTime());
	         ObjectMapper mapper = new ObjectMapper();
	 		Writer strWriter = new StringWriter();
	 		mapper.writeValue(strWriter, mix);
	 		String resp = strWriter.toString();
	 		System.out.println(exec.isTerminated() + " JQUERY IS --------------- " + resp);
	 		writer.println( resp);
	 		writer.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	 class UserBasicInfo implements Callable<List<Domain>>{
	    	
	    	String empId;
	    	public UserBasicInfo(String empId) {
				// TODO Auto-generated constructor stub
	    		//System.out.println(" 1nd time consruction");
	    		this.empId= empId;
			}
	    	
	    	@Override
	    	public List<Domain> call() throws Exception {
	    		// TODO Auto-generated method stub
	    		return getUserBasicInfo();
	    		
	    	}
	    	
	    	private List<Domain> getUserBasicInfo() {
	    		
	    		List<com.pro.post.domain.UserBasicInfo> c=new ArrayList<com.pro.post.domain.UserBasicInfo>();
	    		com.pro.post.domain.UserBasicInfo infos=new com.pro.post.domain.UserBasicInfo();
	    		List<Domain> dd=new ArrayList<Domain>();
	    		try {
	    		
	    			try{
	    				Session session = CassandraDB.getCassConnection();
	        		//	infos.setCoverPage(PhotoInfoDAO.getPhotoByIdDynamic(empId, session));
	        			infos.setKey(empId);
	        			infos.setEmpInfo(EmployeeInfo.getEmployeeById(empId));
	        			infos.setFollowers(new FollowDAO().getAllFollowers(empId));
	        			//infos.setActList(EmpActInfoDAO.getEmpActivityInfoById(con, info.getId()));
	        			//infos.setAddInfo(EmpAddInfoDAO.getEmpAdditionaDetailsById(con, info.getId()));
	        			//infos.setComList(EmpCompanyInfoDAO.getEmpCompanyInfo(con, info.getId()));
	        			//infos.setIdProofList(EmpIdProofDAO.getEmpIdproofInfoById(con, info.getId()));
	        			infos.setPhotoAlbumns(PhotoAlbumDAO.getAlbumByUserId(empId));
	        			//infos.setColleagues(EmployeeInfo.getAllColleageEmployeesInfo());
	    				
	    	   			dd.add(infos);
	    	   			
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    		finally {
	    			//CassandraDB.freeConnection(con);
	    		}
	    		return dd;
	        }
	    	
	      
	        
	    }
		 
		 
		/*  class BirthdayList implements Callable<List<Domain>>{
		    	
		    	
		    	public BirthdayList() {
					// TODO Auto-generated constructor stub
		    		//System.out.println(" 1nd time consruction");
				}
		    	
		    	@Override
		    	public List<Domain> call() throws Exception {
		    		// TODO Auto-generated method stub
		    		return getBirthDays();
		    		
		    	}
		    	
		    	private List<Domain> getBirthDays() {
		    		java.util.List<EmpInfo> empList=	EmpInfoDAO.getUpcomingBirthdays(CassandraDB.getCassConnection());
	    			//System.out.println(" List of birthd days are " + empList.size());
	    			SortComments ob=new SortComments("dateBirth");
	    			Collections.sort(empList,ob);
	    			List<Domain> dd=new ArrayList<Domain>();
		   			for(EmpInfo pai:empList){
		   				dd.add(pai);
		   			}
		   			return dd;
		        }
		    	
		      
		        
		    }
		 
		  class PhotoAlbumList implements Callable<List<Domain>>{
		    	
		    	
		    	public PhotoAlbumList() {
					// TODO Auto-generated constructor stub
		    		//System.out.println(" 1nd time consruction");
				}
		    	
		    	@Override
		    	public List<Domain> call() throws Exception {
		    		// TODO Auto-generated method stub
		    		return getPhotoAlbums();
		    		
		    	}
		    	
		    	private List<Domain> getPhotoAlbums() {
		    		List<PhotoAlbumInfo> r=PhotoAlbumDAO.getAllAblumnsWithLatest(CassandraDB.getCassConnection());
		   			SortComments oob=new SortComments("PhotoDisplaySort");
		   			Collections.sort(r,oob);
		   			List<Domain> dd=new ArrayList<Domain>();
		   			for(PhotoAlbumInfo pai:r){
		   				dd.add(pai);
		   			}
		   			return dd;
		        }
		    	
		  }*/
}
