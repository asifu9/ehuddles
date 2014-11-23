package com.pro.forum.feed;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.pro.emp.AppConstants;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.SortComments;
import com.pro.emp.dao.GroupDAO;
import com.pro.emp.dao.UserGroupDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Forum;
import com.pro.emp.domain.ForumNotification;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.UserGroups;
import com.pro.emp.util.Constant;
import com.pro.forum.dao.ForumDAO;
import com.pro.forum.dao.ForumNotificationDAO;

/**
 * Servlet implementation class GroupFeeds
 */

@javax.servlet.annotation.WebServlet(urlPatterns = { "/Forums" }, asyncSupported = true)
public class Forums extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public Forums() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static final int CALLBACK_TIMEOUT = 1000000; // ms

    *//** executor service *//*
    private ExecutorService exec;

    @Override
    public void init(ServletConfig config) throws ServletException {

      super.init(config);
     
      exec = Executors.newFixedThreadPool(200);
    }
    
    @Override
    public void service(HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {

      final AsyncContext ctx = req.startAsync();
      final HttpSession session = req.getSession();

      // set the timeout
     // ctx.setTimeout(CALLBACK_TIMEOUT);

      // attach listener to respond to lifecycle events of this AsyncContext
      ctx.addListener(new AsyncListener() {

        @Override
        public void onComplete(AsyncEvent event) throws IOException {

          System.out.println("onComplete called");
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
      final EmpInfo emp=Session_control.getSession(req);
      
    
      String groupId="";
      HashMap<String,Boolean> roleMap= EmployeeInfo.getRolesForEmpId(emp.getId()); 

      String str="";
      String str2="";
      ServletResponse response = ctx.getResponse();
      response.getWriter().write(str2.toString());
      str2=("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"+
    		  "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\"> \n"+
    		  "<head>");
      response.getWriter().write(str2);
      response.getWriter().flush();
      str2="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
				"<link rel=\"icon\" type=\"image/png\" href=\"icon/browserIcon.png\">"+
				"<link href=\"css/hrmsstyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">"+
				"<link href=\"css/PostCss.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">"+
				 "<link href=\"css/styles.css\" rel=\"stylesheet\" media=\"screen\"  />"+
				 "<link href=\"css/style.css\" rel=\"stylesheet\" media=\"screen\" />" +
				 "    <script type=\"text/javascript\" src=\"js/Jquery8ui.min.js\"></script>"+
				 "<script type=\"text/javascript\" src=\"js/Jquery8.js\"></script>"+
				 "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.js\"></script>"+
				 	"<script type=\"text/javascript\" src=\"js/forum/TForumUtil.js\" ></script>"+

				"";
      response.getWriter().write(str2);
      response.getWriter().flush();
      str2="<script type=\"text/javascript\">function arrivedGroupInfo(id,type,text) {"+
      		//"{alert('hi');"+
      		"var newJ= $.parseJSON(text);"+
      		"if(type=='addForum'){"+
      		"addForumDynamically(newJ);}"+
      		"else if(type=='GroupOtherAdd'){"+
      		"addOthersGroupDivDynamically(newJ);}"+
      		"else if(type=='addForumNotificationDynamically'){"+
      		"addForumNotificationDynamically(newJ);}"+
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
      		" }</script>";
      response.getWriter().write(str2);
     
      response.getWriter().flush();
		str="</head>";
      
      str+="<body>"+
    		  "<div align=\"center\" id=\"header\" style=\"margin-top: 0px;\">"+
    		  "<div style=\"background-color: #00688B;height: 60px;width: 100%;\">"+
    		  "<div style=\"height: 20px;\"></div>"+
	  			"<div style=\"padding-left: 100px;vertical-align: baseline;\" align=\"left\">"+
	  			"<span class=\"NewHeaderStyle\">"+AppConstants.APP_NAME+"</span>"+
	  			"</div>"+
	  			"</div>"+
	  			"<div style=\"width:80%;\">";
      response.getWriter().write(str);
      
      response.getWriter().flush();
      
      str="<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  >"+
			"<tr>"+
				"<td style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
					"<span class=\"headerLink\" style=\"padding-left: 5px;\">"+emp.getEmpName()+"</span>"+
				"</td>"+
				"<td valign=\"bottom\" width=\"70%\" align=\"right\" style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
					"<table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\">"+
						"<tr>"+
							"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"Feeds\" >Home</a>"+
							"</td>";
							if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {
								str+="<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">";
								str+="	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"UserFeedInfo\">Profile</a>"+
							"</td>";
							 }else if(emp !=null && (roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){
								 str+=("<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"listUsers.jsp\" >View</a>"+
							"</td>");
							 }
							if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {
								str+="<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"photoall.jsp?userId="+emp.getId()+"\" >Photo</a>"+
							"</td>";
							}
							
							str+=("<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"logout.jsp\" >Log out</a>"+
							"</td>"+
							"<!-- <td class=\"headerGap\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"services.jsp\" target=\"contents\">Services</a>"+
							"</td>"+
							"<td class=\"headerGap\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"aboutus.jsp\" target=\"contents\">About Us</a>"+
							"</td>"+
							"<td class=\"headerGap\">"+
							"	<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"	onmouseover=\"this.className='headerLinkMO'\" href=\"contactus.jsp\" target=\"contents\">Contact Us</a>"+
							"</td>"+
							" -->"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+
		"</table>");
	response.getWriter().write(str);		
	response.getWriter().flush();
	  str="<div align=\"left\" style=\"padding-top: 0px;width:100%;\">"+
		"<div style=\"display: table-cell; vertical-align: top;width: 100px; border-right-color: balck;border-right-style: inset;border-right-width: 0px;padding-right: 10px;\" >"+
			"<table>"+
				"<tr >"+
					"<td width=\"200px\">"+
						"<table>"+
							"<tr >"+
								"<td style=\"padding-top: 20px\">";
									 if(emp.getImage()==null) {
										 str+="<img src=\"images/person.jpg\" width=\"150px\" height=\"100px\">";
										}else{
											str+="<img src=\""+PropertyReader.getValue("photoAlbumPath",req)+emp.getImage().getPhotoPath().replaceFirst("0_","6_")+"\" width=\"160px\" >";
										}
									
									 str+="</td>"+
							"</tr>"+
							"<tr>"+
								"<td>"+
									"<table width=\"100%\" border=\"0\">";
					
										 if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { 
											 str+="<tr> "+
											"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
												"<a href=\"editProfile.jsp?editEmpId="+emp.getId()+"\" class=\"leftLinkHeader\">"+
												"<img class=\"iconStyleLeftHeadings\" src=\"icon/edit_profile.jpg\"/>Profile</a>"+
											"</td>"+
										"</tr>";
										}
										 str+="<tr>"+
											"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
												"<a href=\"Feeds\" class=\"leftLinkHeader\">"+
												"<span><img class=\"iconStyleLeftHeadings\" src=\"icon/feeds.jpg\"/></span>Feeds</a>"+
											"</td>"+
										"</tr>";
										 str+="<tr>"+
											"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1' \" onmouseout=\"this.className='headerLinkBlock1'\">"+
												"<a href=\"Messages\" class=\"leftLinkHeader\">"+
												"<img class=\"iconStyleLeftHeadings\" src=\"icon/messages1.jpg\"/>Messages <span id=\"messageCountId\"></span></a>"+
											"</td>"+
										"</tr>";
										
										if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){ 
											str+="<tr> "+
											"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
												"<a href=\"photo.jsp?editEmpId="+emp.getId()+"\" class=\"leftLinkHeader\"><span><img class=\"iconStyleLeftHeadings\" src=\"icon/photos.jpg\"/></span>Photo</a>"+
											"</td>"+
										"</tr>";
										}
										 if(emp!=null && (roleMap.get(Constant.DEP_ADMIN)==null?false:true)){ 
											 str+="<tr> "+
											"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
												"<a href=\"RoleManage.jsp\" class=\"leftLinkHeader\">Roles</a>"+
											"</td>"+
										"</tr>";
										}else{
										
										 if(emp!=null && (roleMap.get(Constant.ROLE_EDIT)==null?false:true)){ 
											 str+="<tr> "+
											"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
											"	<a href=\"RoleManage.jsp\" class=\"leftLinkHeader\">Roles</a>"+
											"</td>"+
										"</tr>";
										}}
										
										 str+="<tr>"+
											"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
											"	<a href=\"colleagueList.jsp\"  class=\"leftLinkHeader\"><span><img class=\"iconStyleLeftHeadings\" src=\"icon/colleagues.png\"/></span>Colleagues</a>"+
											"</td>"+
										"</tr>"+
										"<tr>"+
										"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
											"<a href=\"UserGroup\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/group_final.png\"/>Groups</a>"+
										"</td>"+
									"</tr>"+
									"<tr>"+
									"<td class=\"headerGap1\" style=\"background-color: #AEC9E2;\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
										"<a href=\"Forums\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/forum.jpg\"/>Forums</a>"+
									"</td>"+
								"</tr>"+
				"</table>"+
								"</td>"+
							"</tr>"+
						"</table>"+
					"</td>"+
				"</tr>"+
			"</table>"+
			
		"</div>"+
		"<div style=\"display: table-cell; vertical-align: top;width: 60%; border-right-color: balck;border-right-style: inset;border-right-width: 0px;padding-right: 10px;\">"+
		
		"<div align=\"left\">"+
				"<table align=\"right\" width=\"100%\">"+
					"<tr>"+
						"<td class=\"HeadingOfGroupList \" >"+
						"	Forums"+
						"</td>"+
						
					"</tr>"+
				"</table>"+
			"</div>"+
			"<div style=\"width: 100%;\">"+
			"<div style=\"display:table;width:100%;height: 450px;\">"+
				"<div style=\"display:table-cell;width:100%\" align=\"left\">"+
					"<div id=\"insertForums\"></div>"+
				"</div>"+
				"<div style=\"display:table-cell;width:50%\" >"+
				"</div>"+
				
			"</div>"+
		
		"</div>"+
		
		"</div>"+
		
		"<!--  last column -->"+
		"<div style=\"display: table-cell; vertical-align: top;width: 300px; border-right-color: balck;border-right-style: inset;border-right-width: 0px;\" align=\"right\">"+
			"<div style=\"display:table;width:100%;\" align=\"right\">"+
				
				"<div id=\"otherDisplaysection\" style=\"display:table-cell;width:100%;height: 20px;\" align=\"left\">"+
				"	<div class=\"HeadingOfGroupList\" style=\"height: 27px;\"> "+
				"<span><input type=\"button\" value=\"+ New Thread\" onclick=\"showNewThread()\" class=\"bttn\"> </span>" +
					"</div>"+
					"<input type=\"hidden\" id=\"basePathOfPhoto\" value=\""+PropertyReader.getValue("photoAlbumPath",req)+"\" >"+		
						
					"<div id=\"insertNotifications\"></div>"+
				"</div>"+
				"<div style=\"display:table-cell;\" >"+
				"</div>"+
				
			"</div>"+
		"</div>"+
		
		"</div>";
		response.getWriter().write(str);
		response.getWriter().flush();
		str="<div id=\"newGroup\" class=\"newGroupCss\" style=\"margin-top: 0px;\">"+
			"<div class=\"newGroupTextStyle\" style=\"margin-top: 0px;\">Create New Group</div>"+
			"<div style=\"padding-top:20px;\">"+
			"<Br>"+
				"<table class=\"tableStyleForGroup\"  style=\"padding-top: 10px;\">"+
					"<tr>"+
						"<td >"+
							"Group Name"+
						"</td>"+
						"<td>"+
							"<input type=\"hidden\" id=\"hiddenUserId\" value=\""+emp.getId()+"\">"+
							"<input type=\"text\" name=\"groupName\" id=\"groupName\" >"+
						"</td>"+
					"</tr>"+
					"<tr>"+
						"<td>"+
							"Description"+
						"</td>"+
						"<td>"+
							"<input type=\"text\" name=\"description\" id=\"description\" >"+
						"</td>"+
					"</tr>"+
				
				"</table>"+
			"</div>"+
			
			"<div class=\"newGroupTextStyle2\" style=\"margin-top: 0px;\">"+
			"<table style=\"padding-top: 10px;\">"+
				"<tr>"+
				
					"<td>"+
						"<input class=\"bttn\" type=\"button\" value=\"Cancel\" onclick=\"hideCreateGroup()\" >"+
					"</td>"+
					"<td>"+
						"<input class=\"bttn\" type=\"button\" value=\"Create\" onclick=\"createGroup()\" >"+
					"</td>"+
						
				"</tr>"+
			"</table>"+
			" </div>"+
		"</div>";					

		
								//	response.getWriter().write(str);
								//	response.getWriter().flush();
									String abc="	</div>"+
											"</div>";
									response.getWriter().write(abc);
									response.getWriter().flush();
									//enqueLongGrouppRunningTask(ctx, session,emp);
								
									System.out.println("Done here");
		
		String popup="<form name=\"myFormCover\" action=\"\">"+
		"<input type=\"hidden\"  name=\"groupIdHidden\" value=\""+groupId+"\" > "+
	     "<div id=\"popupContact\" style=\"width: 600px;height: 500px;\">"+
			
			"<iframe src=\"\" style=\"margin:0px;\" width=\"100%\" scrolling=\"auto\" height=\"100%\" id=\"uploadGroupPhoto\"  name=\"uploadGroupPhoto\" frameborder=\"0\" ></iframe>"+
		"</div>"+
		"<div id=\"backgroundPopup\" style=\" height: 100%;\"></div> "+
		"</form></body></html>";
		response.getWriter().write(popup);
		response.getWriter().flush();
			
	 
			
			ctx.start(new Runnable() {
			    public void run() {

			      try {
//			        ctx.getResponse().getWriter().write(
//			            MessageFormat.format("<h1>Processing task in bgt_id:[{0}]</h1>",
//			            ctx.getResponse().getWriter().write(" <div id=\"sharePopupHere\" class=\"sharepopupClass\" ></div>");
				      ctx.getResponse().getWriter().write(" <div id=\"dynaPopup\" ></div> ");
				      ctx.getResponse().getWriter().write(" <div id=\"popupContact\" style=\"width: 50%;height: 50%;\" align=\"center\">");
				      ctx.getResponse().getWriter().write("<a id=\"popupContactClose\" class=\"redbuttonColor\">x</a>");

				      ctx.getResponse().getWriter().write("<iframe  style=\"margin: 0px;\" width=\"100%\" scrolling=\"no\" height=\"100%\" id=\"myIFrame\" frameborder=\"0\" ></iframe>");
				      ctx.getResponse().getWriter().write("</div></div>");
				      ctx.getResponse().getWriter().write("<div id=\"backgroundPopup\" style=\" height: 100%;\"></div> ");
				      ctx.getResponse().getWriter().write("</BODY></HTML>");
				      ctx.getResponse().getWriter().flush();
			        System.out.println(" ------------------------------------------------------------------------ ");
			        List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
			        DisplayListOfForums ob=null;
			        AddNotifications ob2=null;
			        try {
						ob= new DisplayListOfForums(res.getWriter(),res, emp);
						ob2=new AddNotifications(res.getWriter(), res, emp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        tasks.add(ob);
			        tasks.add(ob2);
			     
			        try {
			            exec.invokeAll(tasks, 5, TimeUnit.MINUTES);
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
			     
			        String lastSection="<script>$('.tickerSection').children().bind('mouseenter', function () {"+
							   "var keyFull=$(this).parent().attr('id');"+
							   "var k = '#'+keyFull;"+
							   "$(k).css({'background-color':'#EEEEEE'});"+
							  " getTickerDisplay(keyFull);"+
						   
						"});"+
						"$('.tickerSection').children().bind('mouseleave', function () {"+
							  " var keyFull=$(this).parent().attr('id');"+
							   "var k = '#'+keyFull;"+
							   "$(k).css({'background-color':'#DFF0F7'});"+
							  // getTickerDisplay(keyFull);
						   
						"});</script>";
			        
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
			        
			      
						ctx.getResponse().getWriter().write(lastSection);
						String aaa=		"<script type=\"text/javascript\">"+
						//"registerEventGroup();"+
						"</script>"+
"";
			        
			        ctx.getResponse().getWriter().flush();
			    	ctx.getResponse().getWriter().write(aaa);
					ctx.getResponse().getWriter().flush();
					ctx.complete();
			      System.out.println("final completed");
			        } 
			      catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			  });
			
	
		 
    }

    *//**
     * if something goes wrong in the task, it simply causes timeout condition that causes the async context listener to be invoked (after the fact)
     * <p/>
     * if the {@link AsyncContext#getResponse()} is null, that means this context has already timed out (and context listener has been invoked).
     *//*
//    private void enqueLongGrouppRunningTask(final AsyncContext ctx, final HttpSession session,EmpInfo emp) {
//    	final ServletResponse response = ctx.getResponse();
//    	List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
//        final EmpInfo ii = emp;
//        AddOwnGrouups ob=null;
//        AddOthersGrouups ob2=null;
//        try {
//			ob= new AddOwnGrouups(response.getWriter(),response, ii);
//			ob2=new AddOthersGrouups(response.getWriter(), response, ii);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		tasks.add(ob);
//		tasks.add(ob2);
//        try {
//            exec.invokeAll(tasks, 10, TimeUnit.MINUTES);
//        } catch (InterruptedException ignored) {
//            // ignored
//        	ignored.printStackTrace();
//        }
//        System.out.println("completed all tasks");
//     
//    }

//    /** destroy the executor */
//    @Override
//    public void destroy() {
//
//      exec.shutdown();
//    }/*
   /* public class AddNotifications implements Callable<Boolean>{
    	
      	 EmpInfo info;
PrintWriter writer;
      	ServletResponse resp;
      	public AddNotifications(PrintWriter w,ServletResponse resp, EmpInfo info) {
   			// TODO Auto-generated constructor stub
      		this.info=info;
      		this.writer=w;
      		this.resp=resp;
      		//System.out.println(" 1nd time consruction");
   		}
      	
      	@Override
      	public Boolean call() throws Exception {
      		// TODO Auto-generated method stub
      		addForumNotifications();
      		return false;
      	}
      	
      	private void addForumNotifications() {
      		
      		Connection con = CassandraDB.getCassConnection();
      		List<ForumNotification> list=null;
      		try {
      			list=ForumNotificationDAO.getActiveForumNotification(con, info.getId());
      			SortComments s=new SortComments("fourmNotification");
   			Collections.sort(list,s);
      		  				
   			for(ForumNotification l:list){

   				ObjectMapper mapper = new ObjectMapper();
       			Writer strWriter = new StringWriter();
       			mapper.writeValue(strWriter, list);
       			String resp1 = strWriter.toString();
       			
       			System.out.println(" Forum notification JSOn " + resp1);
       			resp1=resp1.replaceAll("\"","\\\\\"");
   	    			
   	    			
       			while(resp1.contains("\\\\\\\\")){
       				int ss = resp1.indexOf("\\\\\\\\");
       				StringBuffer b=new StringBuffer(resp1);
       				b=b.replace(ss, ss+1, "");
       				resp1=b.toString();
       			}
       			pageletGroup(writer,"insertPost",resp1,"addForumNotificationDynamically",resp);
   			}
      		}
      		catch(Exception e){
      			e.printStackTrace();
      		}
      		finally {
      			CassandraDB.freeConnection(con);
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
    public class DisplayListOfForums implements Callable<Boolean>{
    	
   	 EmpInfo info;
   	PrintWriter writer;
   	ServletResponse resp;
   	public DisplayListOfForums(PrintWriter w,ServletResponse resp, EmpInfo info) {
			// TODO Auto-generated constructor stub
   		this.info=info;
   		this.writer=w;
   		this.resp=resp;
   		//System.out.println(" 1nd time consruction");
		}
   	
   	@Override
   	public Boolean call() throws Exception {
   		// TODO Auto-generated method stub
   		getForums();
   		return false;
   	}
   	
   	private void getForums() {
   		GroupDAO dao=new GroupDAO();
   		Connection con = CassandraDB.getCassConnection();
   		List<Forum> g=null;
   		try {
   			
   			int i=0;
   			
   			g=ForumDAO.getAllForums(con);//(con,info.getId());
   			SortComments s=new SortComments("SortForumsList");
			Collections.sort(g,s);
   		  				
			for(Forum list:g){
				String value1 = list.getBody();
				if(value1!=null){
					value1=value1.replaceAll("\"","\\\\\"");
    			value1=value1.replaceAll("\n","<br/>");
    			value1=value1.replaceAll("\\n","<br/>");
        		if(value1.contains("\b")){
    			}
    			if(value1.contains("\r")){
    				value1=value1.replaceAll("\r","");
    			}
    			if(value1.contains("\'")){
    				value1 =value1.replaceAll("\'","\\'");
    			}
				
				list.setBody(value1);
	    		}
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, list);
    			String resp1 = strWriter.toString();
    			
    			System.out.println(" Forum Post JSON " + resp1);
    			resp1=resp1.replaceAll("\"","\\\\\"");
	    			
	    			
    			while(resp1.contains("\\\\\\\\")){
    				int ss = resp1.indexOf("\\\\\\\\");
    				StringBuffer b=new StringBuffer(resp1);
    				b=b.replace(ss, ss+1, "");
    				resp1=b.toString();
    			}
    			pageletGroup(writer,"insertPost",resp1,"addForum",resp);
			}
   		}
   		catch(Exception e){
   			e.printStackTrace();
   		}
   		finally {
   			CassandraDB.freeConnection(con);
   		}
       }
   	    
   }
 private void pageletGroup(PrintWriter writer, String id, String content,String type,ServletResponse resp) {
    	
        try {
			writer.write("<script>" +
			        "arrivedGroupInfo(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
			        "</script>\n");
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
 
 public class GroupMembers implements Callable<Boolean>{
 	
   	 EmpInfo info;
   	PrintWriter writer;
   	ServletResponse resp;
   	String groupId;
   	public GroupMembers(PrintWriter w,ServletResponse resp, EmpInfo info,String groupId) {
			// TODO Auto-generated constructor stub
   		this.info=info;
   		this.writer=(w);
   		this.resp=resp;
   		this.groupId=groupId;
   		//System.out.println(" 1nd time consruction");
		}
   	
   	@Override
   	public Boolean call() throws Exception {
   		// TODO Auto-generated method stub
   		getGroupMembers();
   		return false;
   	}
   	
   	private void getGroupMembers() {
          
   		Connection con = CassandraDB.getCassConnection();
   		try {
   			
   			List<UserGroups> p  = new ArrayList<UserGroups>();
   		   	UserGroupDAO ob=new UserGroupDAO();
   		   	p=ob.getUsersForGroup(con, groupId);
   				
    			for(UserGroups list:p){
	    			ObjectMapper mapper = new ObjectMapper();
	    			Writer strWriter = new StringWriter();
	    			mapper.writeValue(strWriter, list);
	    			String resp1 = strWriter.toString();
	    			
	    			System.out.println(" Get user member JSON " + resp1);
	    			resp1=resp1.replaceAll("\"","\\\\\"");
	    			
	    			
	    			while(resp1.contains("\\\\\\\\")){
	    				int ss = resp1.indexOf("\\\\\\\\");
	    				StringBuffer b=new StringBuffer(resp1);
	    				b=b.replace(ss, ss+1, "");
	    				resp1=b.toString();
	    			}
	    			pageletGroup(writer,"insertPost",resp1,"member",resp);
	            	
	    			
	    		}
   		}
   		catch(Exception e){
   			e.printStackTrace();
   		}
   		finally {
   			CassandraDB.freeConnection(con);
   		}
       }
   }
*/}
