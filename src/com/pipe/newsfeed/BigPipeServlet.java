package com.pipe.newsfeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;
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
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.util.PostUtil;

/**
 * Servlet implementation class BigPipeServlet
 */
@WebServlet("/BigPipeServlet")
public class BigPipeServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
		
		
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Service Thread "+ t.getId());
            t.setDaemon(true);
            return t;
        }
    });
 
    protected void service(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        //System.out.println(" start.. ");
        String doctype = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
 
        String head = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\"> \n" +
                "<head> \n" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" /> \n" +
                "<meta http-equiv=\"Content-language\" content=\"de\" />\n";
        writer.write(doctype);
        writer.write(head);
    writer.write("<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                
                "<link href=\"css/PostCss.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "<link href=\"css/hrmsstyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "    <script type=\"text/javascript\" src=\"js/Jquery8ui.min.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/Jquery8.js\"></script>"+
    "<link href=\"css/arrowBox.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">"+
    "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/TickersUtil.js\"></script>");

       


writer.write("<script type=\"text/javascript\">function arrived(id,type,text) {"+
		"{alert('hi');"+
		"var newJ= $.parseJSON(text);"+
		"if(type=='post'){alert('type is '+type);"+
		"addMyPostDynamically(newJ);}"+
		"else if(type=='likeNotification'){"+
		"addLikeNotificationDynamically(newJ);}"+
		"else if(type=='likeNotificationCount'){"+
		"addLikeNofiCount(text);}"+
		"else if(type=='ticker'){"+
		"addMyTickerDynamically(newJ);}"+
		"else if(type='birth'){"+
		"addMyDynamicBirthEvents(newJ);}"+
		" }</script>");


        //writer.write(doctype);
        writer.write("<script type=\"text/javascript\">function arrived1(id,text) { var b=document.getElementById(id); b.innerHTML = text; }</script>");
        writer.write("</HEAD><BODY><a href=\"BigPipeServlet\">call again</a> ");
        writer.write("<div id=\"content1\">-</div>\n");
        writer.write("<div id=\"insertPost\" class=\"borderSectionStyhle\" style=\"width: 80%;vertical-align: top;\" onmouseover=\"hideUserInfo('sdf')\"></div>");
        writer.write("<div id=\"tickerDiv\"  align=\"left\"  style=\"width:100%;height: 200px;overflow: auto;color: #334422;vertical-align: top;\">"+
					"<div id=\"insertTickers\"  align=\"left\" style=\"border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;overflow: auto;\">"+
					
					"</div>"+
				"</div>");
        final Random random = new Random();
 
        List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
        RightBirthDays b=new RightBirthDays(writer);
        TickerRightSection tic=new TickerRightSection(writer);
        EmpInfo info = Session_control.getSession(req);
        LeftHeaderMethod lf=new LeftHeaderMethod(writer, info);
        tasks.add(tic);
        tasks.add(lf);
        tasks.add(b);
//        for (int i = 0; i < 6; i++) {
//            final int id = i + 1;
//            tasks.add(new Callable<Boolean>() {          
//                public Boolean call() {
//                    try {
//                        // One service call is nasty and takes 50sec
//                        if (id == 3) {
//                            Thread.sleep(50);
//                        } else {
//                            Thread.sleep(random.nextInt(20));
//                        }
//                        pagelet(writer, "content" + id, "Wohooo" + id);
//                    } catch (InterruptedException e) {
//                        return false;
//                    }
//                    return true;
//                }
//            });
//        }
 
        try {
            executor.invokeAll(tasks, 1500, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
            // ignored
        }
        writer.write("</BODY></HTML>");
    }
 
    private void content(PrintWriter writer, String... contentIds) {
        for (String id : contentIds) {
            writer.write("<div id=\"" + id + "\">-</div>\n");
        }
    }
 
    private void pagelet1(PrintWriter writer, String id, String content) {
        if (writer.checkError()) return;
        writer.write("<script>" +
                "arrived1(\"" + id + "\", \"" + content + "\");" +
                "</script>\n");
        writer.flush();
    }
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class RightBirthDays implements Callable<Boolean>{
    	
    	// EmpInfo info;
    	PrintWriter writer;
    	
    	public RightBirthDays(PrintWriter w) {
			// TODO Auto-generated constructor stub
    		this.writer=w;
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		birthdayEvents();
    		return true;
    	}
    	
    	private void birthdayEvents() {
            if (writer.checkError()) {
            	writer.flush();}
    		//Connection con = CassandraDB.getCassConnection();
    		try {
    			for (int  i=0;i<100;i++){
    				try{
    					
    					Thread.sleep(100);
    				}
    				catch(InterruptedException e){}
    		//	pagelet(writer,"insertPost",resp,"birth");
    			 pagelet1(writer, "content1", "Wohooo" + i);
    			}
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
        private void pageletLeftHeader(PrintWriter writer,String data) {
            if (writer.checkError()){ writer.flush(); }
           // System.out.println(" here i am called  - " + id +" - " + Calendar.getInstance().getTimeInMillis());
          //  writer.write("<script>" +
          //          "arrivedLeftHeaderContent(\""+ data +"\");" +
          //          "</script>\n");
          //  writer.flush();
        }
        
    }
    /**------------------------------------------------------------------------------*/
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class TickerRightSection implements Callable<Boolean>{
    	
    	PrintWriter writer;
    	
    	public TickerRightSection(PrintWriter w) {
			// TODO Auto-generated constructor stub
    		this.writer=w;
    	//	System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		tickerContents();
    		return true;
    	}
    	
    	private void tickerContents() {
            if (writer.checkError()){ 
            
          //  System.out.println(" man eror here again " );
            
            writer.flush();
            }
    		Session con = CassandraDB.getCassConnection();
    		
    		try {
    			List<TickerInfoDomain> list= null;//TickersInfoDAO.getAllTickers(con);
    			//SortComments osb=new SortComments("ticker");
    			//Collections.sort(list,osb);
    			int i=0;
    			List<PostInfo> p=null;//; PostUtil.getAllPosts();
    			for(TickerInfoDomain l:list){
    			
    				String value1 = l.getMessage();
    				
            		
    				if(value1!=null){
    					value1=value1.replaceAll("\"","\\\\\"");
        			value1=value1.replaceAll("\n","<br/>");
        			value1=value1.replaceAll("\\n","<br/>");
            		if(value1.contains("\b")){
        			}
        			if(value1.contains("\r")){
        				value1=value1.replaceAll("\r","");
        			}
//        			if(value1.contains(",")){
//        				System.out.println(" \\r present 2 111");
//        				value1=value1.replaceAll(",","");
//        			}
        			if(value1.contains("\t")){
        				//System.out.println(" \\t present 3");
        			}
        			if(value1.contains("\n")){
        				//System.out.println(" \\n present 4");
        			}
        			if(value1.contains("\b")){
        				//System.out.println(" \\b present 5");
        			}
        			if(value1.contains("\'")){
        				value1 =value1.replaceAll("\'","\\'");
        			}
    				
    				l.setMessage(value1);
    				}
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, l);
    			String resp = strWriter.toString();
    			
    			//System.out.println(" value si " + value1);
    			resp=resp.replaceAll("\"","\\\\\"");
    			
    			
    			while(resp.contains("\\\\\\\\")){
    				int ss = resp.indexOf("\\\\\\\\");
    				StringBuffer b=new StringBuffer(resp);
    				b=b.replace(ss, ss+1, "");
    				resp=b.toString();
    			}
    		//	resp=resp.replaceAll("\\\\","\\");
    			//pageletLeftHeader(writer,resp);
    			pagelet(writer,"insertTickers",resp,"ticker");
    			i+=1;
            	/*for(BookContentDomain bcont:list){
            		buf=new StringBuffer();
            		buf.append(bcont.getId()+"~~"+bcont.getBookId()+"~~"+bcont.getChapterNo()+"~~"+bcont.getVerseNo()+"~~"+bcont.getMeaning());
            		pageletMainContentsWrite(writer,buf.toString());
            	}*/
    			}
    			pageletRegisterEvent(writer);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
        private void pageletRegisterEvent(PrintWriter writer) {
            if (writer.checkError()){ writer.flush();}
           // System.out.println(" here i am called  - " + id +" - " + Calendar.getInstance().getTimeInMillis());
            writer.write("<script>" +
                    "registerMyEvents();" +
                    "</script>\n");
            writer.flush();
        }
        
    }
    
    private void pagelet(PrintWriter writer, String id, String content,String type) {
        if (writer.checkError()){
        	//System.out.println(" Sopme error while writer " );
        	writer.flush();}
        //System.out.println(" -- " + "<script>" +
       //         "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
       //         "</script>\n");
        writer.write("<script>" +
                "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
                "</script>\n");
        writer.flush();
    
    }
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class LeftHeaderMethod implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	PrintWriter writer;
    	
    	public LeftHeaderMethod(PrintWriter w,EmpInfo info) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		pageletLeftHeaderContents();
    		return true;
    	}
    	
    	private void pageletLeftHeaderContents() {
            if (writer.checkError()){
            	//System.out.println(" hhhhhh errororr ");
            	
            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    			
    			int i=0;
    			List<PostInfo> p= null;//PostUtil.getAllPosts();
    			for(PostInfo list:p){
    				//System.out.println(" list. " + list.getPostedDesc());
    				if(list.getPostedPhotoId()!=null){
    					//list.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(list.getPostedPhotoId(), con));
    				}else{
    					list.setPostedByPhotoInfo(null);
    				}
     		

    				
            		list.setLikes(PostLikeDAO.getLikesForPostId(con, list.getKey()));
            		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(list.getKey(), con);
            		//SortComments s=new SortComments("PostComments");
    				//Collections.sort(commList,s);
            		list.setComments(commList);
//            		System.out.println("  info.getPostedById() " +  info.getPostedById());
    				String value1 = list.getPostedDesc();
    				//value1=value1.replaceAll("\"","\\\\\"");
    				if(value1!=null){
    					value1=value1.replaceAll("\"","\\\\\"");
        			value1=value1.replaceAll("\n","<br/>");
        			value1=value1.replaceAll("\\n","<br/>");
            		if(value1.contains("\b")){
        			}
        			if(value1.contains("\r")){
        				value1=value1.replaceAll("\r","");
        			}
//        			if(value1.contains(",")){
//        				System.out.println(" \\r present 2 111");
//        				value1=value1.replaceAll(",","");
//        			}
        			if(value1.contains("\t")){
        				//System.out.println(" \\t present 3");
        			}
        			if(value1.contains("\n")){
        				//System.out.println(" \\n present 4");
        			}
        			if(value1.contains("\b")){
        				//System.out.println(" \\b present 5");
        			}
        			if(value1.contains("\'")){
        				value1 =value1.replaceAll("\'","\\'");
        			}
    				
    				list.setPostedDesc(value1);
    				}
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, list);
    			String resp = strWriter.toString();
    			
    			//System.out.println(" value si " + value1);
    			resp=resp.replaceAll("\"","\\\\\"");
    			
    			
    			while(resp.contains("\\\\\\\\")){
    				int ss = resp.indexOf("\\\\\\\\");
    				StringBuffer b=new StringBuffer(resp);
    				b=b.replace(ss, ss+1, "");
    				resp=b.toString();
    			}
    		//	resp=resp.replaceAll("\\\\","\\");
    			//pageletLeftHeader(writer,resp);
    			pagelet(writer,"insertPost",resp,"post");
    			i+=1;
            	/*for(BookContentDomain bcont:list){
            		buf=new StringBuffer();
            		buf.append(bcont.getId()+"~~"+bcont.getBookId()+"~~"+bcont.getChapterNo()+"~~"+bcont.getVerseNo()+"~~"+bcont.getMeaning());
            		pageletMainContentsWrite(writer,buf.toString());
            	}*/
    			}
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
        private void pageletLeftHeader(PrintWriter writer,String data) {
            if (writer.checkError()){  writer.flush(); }
           // System.out.println(" here i am called  - " + id +" - " + Calendar.getInstance().getTimeInMillis());
          //  writer.write("<script>" +
          //          "arrivedLeftHeaderContent(\""+ data +"\");" +
          //          "</script>\n");
          //  writer.flush();
        }
        
    }
    /**------------------------------------------------------------------------------*/
}
