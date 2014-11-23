package com.pro.emp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.SortComments;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.dao.PublicChatDAO;
import com.pro.emp.domain.Domain;
import com.pro.emp.domain.DomainMix;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.PublicChat;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.util.PostUtil;

/**
 * Servlet implementation class GetMoreData
 */
@WebServlet("/GetMoreData")
public class GetMoreData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 final ExecutorService exec = Executors.newFixedThreadPool(10);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMoreData() {
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
	        // set the timeout
	       
	        DomainMix mix=new DomainMix();
	   	
	        List<Callable<List<Domain>>> tasks = new ArrayList<Callable<List<Domain>>>();
	      //  List<Callable<Boolean>> tasks2 = new ArrayList<Callable<Boolean>>();
	        Callable<List<Domain>> photo=new PhotoAlbumList();
	        Callable<List<Domain>> birth=new BirthdayList();
	        Callable<List<Domain>> chaats=new PublicChatAsch();
	        Callable<List<Domain>> tickers = new TickerInfoTrackers();
	        tasks.add(photo);
	        tasks.add( birth);
	        tasks.add( chaats);
	        tasks.add(tickers);
	        
	         try {
				List<Future<List<Domain>>> f=exec.invokeAll(tasks,5,TimeUnit.MINUTES);
				
				
				for(Future<List<Domain>> result:f){
					//System.out.println(" result.getClass() "  );
					List<Domain> ss=result.get();
					if(ss!=null){
						for(Domain d:ss){
							System.out.println(d.getClass());
							if(d instanceof PublicChat){
								//System.out.println(" got public chat " + Calendar.getInstance().getTime());
								mix.getChat().add((PublicChat)d);
							}else if(d instanceof EmpInfo){
								mix.getEmp().add((EmpInfo)d);
								//System.out.println(" ok not understanding");
							}else if(d instanceof TickerInfoDomain){
								mix.getTicker().add((TickerInfoDomain)d);
								//System.out.println(" ok not understanding");
							}else if(d instanceof PhotoAlbumInfo){
								mix.getPhoto().add((PhotoAlbumInfo)d);
								//System.out.println(" ok not understanding");
							}
						}
					}
					//for(     resut1:)
				}
				
				
				//ystem.out.println(" first one " + Calendar.getInstance().getTime());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         //System.out.println(" first one 2 " + Calendar.getInstance().getTime());
	         ObjectMapper mapper = new ObjectMapper();
	 		Writer strWriter = new StringWriter();
	 		mapper.writeValue(strWriter, mix);
	 		String resp = strWriter.toString();
	 		//System.out.println(exec.isTerminated() + " JQUERY IS more data:  " + resp);
	 		writer.println( resp);
	 		writer.flush();
	        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
	
	  class PublicChatAsch implements Callable<List<Domain>>{
	    	
    	
    	public PublicChatAsch() {
			// TODO Auto-generated constructor stub
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public List<Domain> call() throws Exception {
    		// TODO Auto-generated method stub
    		return getPublicChats();
    		
    	}
    	
    	private List<Domain> getPublicChats() {
    		List<PublicChat> c=null;
    		List<Domain> dd=new ArrayList<Domain>();
    		try {
    			PublicChatDAO pcdao=new PublicChatDAO();
    		
    			try{
    				c= pcdao.getAllChats(CassandraDB.getCassConnection());
    				
    	   			for(PublicChat pai:c){
    	   				dd.add(pai);
    	   			}
    	   			
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
	 
	 
	  class BirthdayList implements Callable<List<Domain>>{
	    	
	    	
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
    		//	SortComments ob=new SortComments("dateBirth");
    		//	Collections.sort(empList,ob);
    			List<Domain> dd=new ArrayList<Domain>();
	   			for(EmpInfo pai:empList){
	   				dd.add(pai);
	   				//System.out.println(" ------------------ -------------- ------");
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
	   		//	SortComments oob=new SortComments("PhotoDisplaySort");
	   		//	Collections.sort(r,oob);
	   			List<Domain> dd=new ArrayList<Domain>();
	   			for(PhotoAlbumInfo pai:r){
	   				dd.add(pai);
	   			}
	   			return dd;
	        }
	    	
	  }
	    
	  class TickerInfoTrackers implements Callable<List<Domain>>{
	    	
	    	
	    	public TickerInfoTrackers() {
				// TODO Auto-generated constructor stub
	    		//System.out.println(" 1nd time consruction");
			}
	    	
	    	@Override
	    	public List<Domain> call() throws Exception {
	    		// TODO Auto-generated method stub
	    		return getAllTickers();
	    		
	    	}
	    	
	    	private List<Domain> getAllTickers() {
	    		List<TickerInfoDomain> list= PostUtil.getAllTickers();
	   			List<Domain> dd=new ArrayList<Domain>();
	   			for(TickerInfoDomain pai:list){
	   				dd.add(pai);
	   			}
	   			return dd;
	        }
	    	
	  }

