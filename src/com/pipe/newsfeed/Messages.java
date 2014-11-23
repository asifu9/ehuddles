package com.pipe.newsfeed;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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
import javax.xml.ws.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.AppConstants;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.dao.PublicChatDAO;
import com.pro.emp.dao.RoleDAO;
import com.pro.emp.dao.TickersInfoDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.MessageLinks;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.domain.PublicChat;
import com.pro.emp.domain.TickerInfoDomain;
import com.pro.emp.message.dao.MessageLinksDAO;
import com.pro.emp.util.Constant;
import com.pro.emp.util.PostUtil;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class Feeds
 */
@javax.servlet.annotation.WebServlet(urlPatterns = { "/Messages" }, asyncSupported = true)
public class Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ExecutorService exec;

    @Override
    public void init(ServletConfig config) throws ServletException {

      super.init(config);
     
      exec = Executors.newFixedThreadPool(200);
    }


	public Connection con;

	public String id;    /**
     * @see HttpServlet#HttpServlet()
     */
    public Messages() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
    	//System.out.println("Feeds in Asyn servlet ");
   	 final AsyncContext ctx = req.startAsync();
        final HttpSession session = req.getSession();

        // set the timeout
       // ctx.setTimeout(CALLBACK_TIMEOUT);

        // attach listener to respond to lifecycle events of this AsyncContext
        ctx.addListener(new AsyncListener() {

          @Override
          public void onComplete(AsyncEvent event) throws IOException {

           // System.out.println("onComplete called");
          }

          @Override
          public void onTimeout(AsyncEvent event) throws IOException {

          	System.out.println("onTimeout called");
          }

          @Override
          public void onError(AsyncEvent event) throws IOException {

          	System.out.println("onError called: " + event.toString());
          }

          @Override
          public void onStartAsync(AsyncEvent event) throws IOException {

          	System.out.println("onStartAsync called");
          }
        });
   	
   	
   	
   	
   	
   	
   	
   	
        ServletResponse response = ctx.getResponse();
    	
      //  System.out.println(" here i am ---------------------------------------------- ");
        final EmpInfo emp=Session_control.getSession(req);
        HashMap<String,Boolean> roleMap=new HashMap<String, Boolean>();
        if(emp!=null)
        roleMap = EmployeeInfo.getRolesForEmpId(emp.getId());
        String albumPath =PropertyReader.getValue("photoAlbumPath",req);
        String doctype = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
 
        String head = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\"> \n" +
        		
                "<head> \n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"icon/browserIcon.png\">"+
                "<title>"+emp.getEmpName()+"</title>"+
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" /> \n" +
                "<script type=\"text/javascript\" src=\"js/Jquery8ui.min.js\"></script>" +
                "<script type=\"text/javascript\" src=\"js/Jquery8.js\"></script>" +
               "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.js\"></script>" +
               "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.min.js\"></script>" +
                "<link href=\"css/hrmsstyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">" +
                "<link href=\"css/PostCss.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">" +
                "<link href=\"css/messages.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">" +
                "<link href=\"css/mystyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">" +
                

                "<script type=\"text/javascript\" src=\"js/TMessageUtil.js\"></script>\n";
 
               response.getWriter().write(doctype);
        response.getWriter().write(head);
        response.getWriter().flush();
        response.getWriter().write("<script type=\"text/javascript\">function arrivedMessage(id,type,text) {"+
        		//"{alert('hi');"+
        		"var newJ= $.parseJSON(text);"+
        		"if(type=='message'){"+
        		"addInboxDynamically(newJ);}"+
        		"else if(type=='sentItem'){"+
        		"addSentItemDynamically(newJ);}"+
        		"else if(type=='commentNotification'){"+
        		"addCommentNotificationDyna(newJ);}"+
        		"else if(type=='likeNotificationCount'){"+
        		"addLikeNofiCount(text);}"+
        		"else if(type=='CommentNotificationCount'){"+
        		"addCommentNotiCount(text);}"+
        		"else if(type=='messageNofitication'){"+
        		"addMessageNotification(text);}"+
        		"else if(type=='ticker'){"+
        		"addMyTickerDynamically(newJ);}"+
        		"else if(type=='birth'){"+
        		"addMyDynamicBirthEvents(newJ);}"+
        		"else if(type=='public'){"+
        		"addPublicChats(newJ);}"+
        		"else if(type=='album'){"+
        		"addMyRecentlyUploadedPhotoAlbums(newJ);}"+
        		" }</script>\n");
        response.getWriter().write("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n"+
        		"<script src=\"http://www.google.com/uds/solutions/dynamicfeed/gfdynamicfeedcontrol.js\" "+
        		"type=\"text/javascript\"></script>\n");
        response.getWriter().flush();
        head="<script type=\"text/javascript\">\n"+



     

     	"var cssObj = { 'box-shadow' : '#888 5px 10px 10px', "+

     		"'-webkit-box-shadow' : '#888 5px 10px 10px', "+

     		"'-moz-box-shadow' : '#888 5px 10px 10px'}; \n"+

     	"$(\"#suggestions\").css(cssObj);"+
     	
     	""+
     	" $(\"input\").blur(function(){"+
     	 "	$('#suggestions').fadeOut();"+
     	 	
     	" });"+
     
     	" $(\"select\").blur(function(){"+
     	" 	$('#suggestions').fadeOut();"+
     	" });"+
     	 //$(\"p\").height()

     

     

  
 
     
     
  " \n  function showNewMessageSection(){"+
   " 	 $(\"#addUserMails\").html(\"\");"+
   " 	 $(\"#newMailSection\").css('display','block');"+
    	 
    	 
   "  }"+
   " </script>\n";
        response.getWriter().write(head);
        response.getWriter().flush();
        		

       // writer.write(\"<script type=\\"text/javascript\\">function arrivedTicker(id,text) {alert('hi');var newT= $.parseJSON(text); addMyTickerDynamically(\"+albumPath+\",newT);var b=document.getElementById(id); }</script>\");
        response.getWriter().write("<script type=\"text/javascript\">function arrivedLeftHeaderContent(data){var b=document.getElementById(\"insertPost\");}</script>");
        response.getWriter().write("</HEAD><BODY  marginheight=\"0\" marginwidth=\"0\" style=\"z-index:-10;\">");
        String body="";
        body="<div align=\"center\" id=\"header\" class=\"mydivStyleIns\">";
        
	  body=body+"<div style=\"background-color: #00688B;height: 60px;width: 100%;\">"+
	  		"<div style=\"height: 10px;\"></div>"+
	  		"<div style=\"padding-left: 100px;vertical-align: baseline;\" align=\"left\">"+
	  		
	  			"<span  class=\"NewHeaderStyle\">"+AppConstants.APP_NAME+"</span>"+
	  		"</div>"+
	  "</div>\n";
	  String homeImage="";
      if(emp!=null && emp.getImagePath()!=null){
    	  homeImage="<img src=\""+emp.getImagePath().replace(".JPG", "_Icon.JPG")+"\" width=\"30px\" height=\"30px\"> ";
      }
      response.getWriter().write(body);
      response.getWriter().flush();
        body="<div align=\"center\" style=\"z-index:-20;\"><div style=\"width: 100%;z-index:-20;\" align=\"center\" >"+
        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  >"+
		"<tr>"+
			"<td style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
			"	<span class=\"headerLink\" style=\"padding-left: 5px;\">"+emp.getEmpName()+"</span>"+
			"</td>"+
			"<td valign=\"bottom\" width=\"70%\" align=\"right\" style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color:  #33A1C9\">"+
				"<table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\">"+
					"<tr>"+
						"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"onmouseover=\"this.className='headerLinkMO'\" href=\"Feeds\" >Home</a>"+
						"</td>"+
						"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"onmouseover=\"this.className='headerLinkMO'\" href=\"UserFeedInfo\">Profile</a>"+
						"</td>"+
						
						"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"onmouseover=\"this.className='headerLinkMO'\" href=\"photoall.jsp?userId="+emp.getId()+"\" >Photo</a>"+
						"</td>"+
						
						"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"onmouseover=\"this.className='headerLinkMO'\" href=\"logout.jsp\" >Log out</a>"+
						"</td>"+
						
					"</tr>"+
				"</table>"+
			"</td>"+
		"</tr>"+
	"</table>";
        response.getWriter().write(body);
        response.getWriter().flush();
        
    
       body=""+
		"<div style=\"display: table; width: 100%;vertical-align: top;\" align=\"center\">"+
			// left corner side
			"<div style=\"display: table-cell; width: 5%;vertical-align: top;\" align=\"left\" >"+
			
				"<div>"+
					"<table>"+
						"<tr>"+
							"<td width=\"200px\" align=\"right\">"+
								"<table>"+
									"<tr>"+
										"<td style=\"padding-top: 20px\" align=\"right\">";
											
											 if(emp!=null && emp.getImage()==null ) {
												body+="<img src=\"images/person.jpg\" width=\"150\" height=\"100\" />";
												}else{ 
													body+="<img src=\""+PropertyReader.getValue("photoAlbumPath",req)+emp.getImage().getPhotoPath().replaceFirst("0_","6_")+"\"  width=\"160\"/>";
											}
											
											 body+="</td>"+
									"</tr>"+
									
								"</table>"+
								"<div style=\"padding-top: 5px\"></div>"+
						"<table width=\"100%\">";
							
							 if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { 
								 body+="<tr> "+
								"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"editProfile.jsp?editEmpId="+emp.getId()+"\" class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/edit_profile.jpg\"/>Profile</a>"+
								"</td>"+
							"</tr>\n";
							 }
							 body+="<tr>"+
						"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1' \" onmouseout=\"this.className='headerLinkBlock1'\">"+
							"<a href=\"Feeds\" class=\"leftLinkHeader\">"+
							"<img class=\"iconStyleLeftHeadings\" src=\"icon/feeds.jpg\"/>Feeds</a>"+
						"</td>"+
					"</tr>";
							 body+="<tr>"+
								"<td class=\"headerGap1\" style=\"background-color: #AEC9E2;\"  onmouseover=\"this.className='headerLinkMOBlock1' \" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"Messages\" class=\"leftLinkHeader\">"+
									"<img class=\"iconStyleLeftHeadings\" src=\"icon/messages1.jpg\"/>Messages <span id=\"messageCountId\"></span></a>"+
								"</td>"+
							"</tr>";
							 
							if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){
							 body+="<tr> "+
								"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"photo.jsp?editEmpId="+emp.getId()+"\" class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/photos.jpg\"/>Photo</a>"+
								"</td>"+
							"</tr>";
							} 
							
							body+="<tr>"+
								"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"colleagueList.jsp\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/colleagues.png\"/>Colleagues</a>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
							"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
								"<a href=\"UserGroup\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/group_final.png\"/>Groups</a>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
						"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
							"<a href=\"Forums\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/forum.jpg\"/>Forums</a>"+
						"</td>"+
					"</tr>"+
						"</table>"+
							"</td>"+
						"</tr>"+
					"</table>"+
				"</div>"+
				
			"</div>\n";
							// end of left corner, now middle one
							response.getWriter().write(body);
						      response.getWriter().flush();
						      
			body="<div align=\"left\" style=\"padding-top: 0px;\">"+
		"<div style=\"display: table-cell; vertical-align: top;width: 250px; border-right-color: balck;border-right-style: inset;border-right-width: 1px;padding-right: 4px;\" >"+
			"<div class=\"Inboxsection\" style=\"width: 100%;\">"+
				"<span class=\"inboxStyle\" style=\"width: 100%;\">Messages</span>"+
				"<div id=\"addMails\" style=\"width: 100%;width: 250px;\" >"+
				
				"</div>"+
			"</div>"+
			
		"</div>"+
		"<input type=\"hidden\" id=\"currentId\" value=\""+emp.getId()+"\">"+
		"<div style=\"display: table-cell; width: 400px;\" align=\"left\">"+
			"<div class=\"messagesection\" align=\"right\" ><input class=\"newMailStyle\" type=\"button\" onclick=\"showNewMessageSection()\" value=\"New Message\"></div>"+
			"<div id=\"newMailSection\" class=\"newMessageSectionStyle\" style=\"display: none;\">"+
			"<span class=\"mesageDetailsHeade\">Type a contact name and click to select</span>"+
			"<form name=\"myForm\" action=\"\" method=\"post\">"+
				"<div>"+
					"<input type=\"text\" class=\"searchtextBox\" size=\"48\" value=\"\" id=\"inputString\" onclick=\"lookup(this.value,myForm.searchType.value);\" onkeyup=\"lookup(this.value,myForm.searchType.value);\" />"+
					
						" <select style=\"display: none;\" class=\"searchComboBox\" name=\"searchType\" id=\"searchType\" onchange=\"lookup(inputString.value,this.value)\">"+ 
		    	  			"<option class=\"searchcomboBoxItems\" id=\"view\" selected=\"selected\">View</option>"+
				
		    			"</select> "+
					"<div id=\"suggestions\" class=\"displayOptions\" style=\"width:300px;\"></div>"+
				"</div>"+
				"<div id=\"addNamesDynamically\" class=\"selectedContactDiv\">"+
					
				"</div>"+
				"<div style=\"padding-top: 15px;\">"+
					"<span class=\"mesageDetailsHeade\" >Write your message here</span><br>"+
					"<textarea rows=\"2\" cols=\"48\" id=\"mes\" name=\"mes\"></textarea><br>"+
						"<span id=\"myText\"></span>"+
					
					"<div align=\"right\"><input type=\"button\" class=\"buttonStyleInSend\" value=\"Send\" onclick=\"sendMessage()\"></div></div>"+
					
					
				
			"</form>"+
			"</div >"+
					
					"<div class=\"\" id=\"addUserMails\"></div>"+
					"<div class=\"borderForMails\" id=\"test\" style=\"height: 2px;\"></div>"+
				
		"</div>"+
		
		"<div class=\"Inboxsection\" style=\"display: table-cell; width: 300px;\" align=\"left\">"+
		"<span class=\"inboxStyle\" style=\"width: 100%;\">Sentitems</span>"+
				"<div id=\"noReplay\" style=\"width: 250px;\" >"+
				
				"</div>"+
		"</div>"+
		
		"<input type=\"hidden\" id=\"txtEmpId\" value=\""+emp.getId()+"\">	"+
		"</div>\n";
			response.getWriter().write(body);
		      response.getWriter().flush();
		      	
				
				
//				\"<div style=\"vertical-align: top; width: 70%;padding-left: 5px;\" class=\"borderSectionStyhle\" onmouseover=\"hideUserInfo('sdf')\">"+
//					"<input type=\"hidden\" id=\"txtPhotoPath\" value=\""+emp.getImagePath()+"\">	"+
//					"<input type=\"hidden\" id=\"txtEmpId\" value=\""+emp.getId()+"\">	"+
//					"<input type=\"hidden\" value=\""+emp.getId()+"\" id=\"hiddenCurrentUser\" >"+
//					"<input type=\"hidden\" id=\"txtEmpIdTarget\" value=\""+emp.getId()+"\">	"+
//					"<input type=\"hidden\" id=\"txtEmpName\" value=\""+emp.getEmpName()+"\">		"+	
//					"<input type=\"hidden\" id=\"basePathOfPhoto\" value=\""+PropertyReader.getValue("photoAlbumPath",req)+"\" >"+
//					"<input type=\"hidden\" value=\""+emp.getId()+"\" id=\"hiddenCurrentUser\" >"+
//					"<input type=\"hidden\" value=\"1\" id=\"flow\" >"+
//					"<div><table class=\"mainTableStyule\" cellpadding=\"0\" cellspacing=\"0\" ><tr><td><textarea style=\"width:520px;\"  class=\"comment_empty1\" id=\"txtNewPost\" >what's on your mind?</textarea></td></tr>"+
//					"<tr><td id=\"enlargeMe\" class=\"myPostStyleBox\" width=\"100%\"><table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\"><tr><td><input type=\"button\" onclick=\"addPost3()\" value=\"Post\" class=\"txtPostButton\"></td></tr></table></td></tr></table></div>"+
				
        	
        	
        		 
			 // spawn some task in a background thread
			  ctx.start(new Runnable() {
			    public void run() {

			      try {
//			        ctx.getResponse().getWriter().write(
//			            MessageFormat.format("<h1>Processing task in bgt_id:[{0}]</h1>",
//			            ctx.getResponse().getWriter().write(" <div id=\"sharePopupHere\" class=\"sharepopupClass\" ></div>");
				      ctx.getResponse().getWriter().write("</BODY></HTML>");
				      ctx.getResponse().getWriter().flush();
			        System.out.println(" ------------------------------------------------------------------------ ");
			        List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
			        LeftHeaderMessagesInbox ob= new LeftHeaderMessagesInbox(ctx.getResponse().getWriter(),resp, emp);
			        RightSentItems ob3=new RightSentItems(ctx.getResponse().getWriter(),resp,emp);
			        tasks.add(ob);
			        tasks.add(ob3);
			        try {
			            exec.invokeAll(tasks, 7, TimeUnit.MINUTES);
			        } catch (InterruptedException ignored) {
			            // ignored
			        	ignored.printStackTrace();
			        }
			        
			        System.out.println("fist completed completed");
			        
			      }
			      catch (IOException e) {
			        e.printStackTrace();
			      }
			      try {
			     
			     
			        
//			        lastSection+="<script language=\"Javascript\" type=\"text/javascript\">"+
			//
//						"function callback(model){"+
//							//simply append the model to a div, for demo purposes
//							"var myDiv = document.getElementById(\"myDiv\");"+
//							"myDiv.innerHTML =  model;"+
//						"}"+
			//
//						"new PushClient(\"myChannel\", callback).login();"+
			//
//						"</script>";
			        
			      
			      ctx.complete();
			      System.out.println("final completed");
			        } 
			      catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			  });
			  
			  
 
       // executor.shutdown();
       
       
    }
 

 
//    private void pagelet(PrintWriter writer, String id, String content,String type,HttpServletResponse resp) {
//    	
//        if (writer.checkError()){
//        	//System.out.println(" Sopme error while writer " );
//        	 try {
//  				writer = resp.getWriter();
//  			} catch (IOException e) {
//  				// TODO Auto-generated catch block
//  				e.printStackTrace();
//  			}
//        	writer.flush();}
//      //  System.out.println(" -- " + "<script>" +
//        //        "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
//        //        "</script>\n");
//        writer.write("<script>" +
//                "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
//                "</script>\n");
//        writer.flush();
//    }
 private void pagelet(PrintWriter writer, String id, String content,String type,HttpServletResponse resp) {
    	
        try {
			writer.write("<script>" +
			        "arrivedMessage(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
			        "</script>\n");
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class LeftHeaderMessagesInbox implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public LeftHeaderMessagesInbox(PrintWriter w,HttpServletResponse resp, EmpInfo info) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		displayInboxMessages();
    		return false;
    	}
    	
    	private void displayInboxMessages() {
           
    		Session con = CassandraDB.getCassConnection();
    		try {
    			
    			int i=0;
    			List<MessageLinks> data=MessageLinksDAO.getMessageLinksForUserId(con,info.getId());
				
			//SortComments sort=new SortComments("mailSort");
				//Collections.sort(data,sort);
				
    				
    				//System.out.println(" last time " + lastTime);
    				
	    			for(MessageLinks list:data){
	    				//System.out.println(" list. " + list.getPostedDesc());
	    			
	    			ObjectMapper mapper = new ObjectMapper();
	    			Writer strWriter = new StringWriter();
	    			mapper.writeValue(strWriter, list);
	    			String resp1 = strWriter.toString();
	    			
	    			//System.out.println(" message inbox comment JSON " + resp1);
	    			resp1=resp1.replaceAll("\"","\\\\\"");
	    			
	    			
	    			while(resp1.contains("\\\\\\\\")){
	    				int ss = resp1.indexOf("\\\\\\\\");
	    				StringBuffer b=new StringBuffer(resp1);
	    				b=b.replace(ss, ss+1, "");
	    				resp1=b.toString();
	    			}
	    		//	resp=resp.replaceAll("\\\\","\\");
	    			//pageletLeftHeader(writer,resp);
	    			pagelet(writer,"insertPost",resp1,"message",resp);
	    			i+=1;
	            
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
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class RightSentItems implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public RightSentItems(PrintWriter w,HttpServletResponse resp,EmpInfo emp) {
			// TODO Auto-generated constructor stub
    		this.writer=w;
    		this.resp=resp;
    		this.info=emp;
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		SendItemBox();
    		return false;
    	}
    	
    	private void SendItemBox() {
    		Session con = CassandraDB.getCassConnection();
    		try {
    			HashMap<String, MessageLinks> obb=new HashMap<String, MessageLinks>();
    			List<MessageLinks> data=MessageLinksDAO.getSentMessageLinksForUserId(con,info.getId());
				
			//	SortComments sort=new SortComments("mailSortSent");
			//	Collections.sort(data,sort);
				for(MessageLinks s:data){
					if(!obb.containsKey(s))
						obb.put(s.getToId(),s);
				}
					
				Set<String> set= obb.keySet();
				data=new ArrayList<MessageLinks>();
				for(String ss:set)
					data.add(obb.get(ss));
				
				for(MessageLinks list:data){
    				
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, list);
    			String resp1 = strWriter.toString();
    			
    			System.out.println(" value si " + resp1);
    			resp1=resp1.replaceAll("\"","\\\\\"");
    			
    			
    			while(resp1.contains("\\\\\\\\")){
    				int ss = resp1.indexOf("\\\\\\\\");
    				StringBuffer b=new StringBuffer(resp1);
    				b=b.replace(ss, ss+1, "");
    				resp1=b.toString();
    			}
    			//System.out.println(" birthday is " + resp);
    		//	resp=resp.replaceAll("\\\\","\\");
    			//pageletLeftHeader(writer,resp);
    			pagelet(writer,"insertPost",resp1,"sentItem",resp);
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
    	
    /**------------------------------------------------------------------------------*/
    // for main pagelet
}
}