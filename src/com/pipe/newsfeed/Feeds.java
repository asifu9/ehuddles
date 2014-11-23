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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
import javax.servlet.jsp.JspWriter;
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
import com.pro.emp.domain.ForumNotification;
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
import com.pro.forum.dao.ForumNotificationDAO;
import com.pro.post.dao.CommentNotificationsDAO;
import com.pro.post.dao.LikeNotificationsDAO;
import com.pro.post.domain.CommentNotificationJson;
import com.pro.post.domain.LikeNotificationJson;

/**
 * Servlet implementation class Feeds
 */
@javax.servlet.annotation.WebServlet(urlPatterns = { "/Feeds" }, asyncSupported = true)
public class Feeds extends HttpServlet {
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
    public Feeds() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
   	 final AsyncContext ctx = req.startAsync();
        final HttpSession session = req.getSession();

        // set the timeout
        ctx.setTimeout(1000000);

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
   	
   	
   	
   	
   	
   	
   	
   	
        ServletResponse response = ctx.getResponse();
      //   response.setCharacterEncoding("UTF-8");
      //  System.out.println(" here i am ---------------------------------------------- ");
        final EmpInfo emp=Session_control.getSession(req);
        HashMap<String,Boolean> roleMap=new HashMap<String, Boolean>();
        if(emp!=null)
        roleMap = EmployeeInfo.getRolesForEmpId(emp.getId());
        String albumPath =PropertyReader.getValue("photoAlbumPath",req);
        String doctype = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
        response.getWriter().write(doctype);
        response.getWriter().flush();
        String head = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\"> \n" +
        		
                "<head> \n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"icon/browserIcon.png\">"+
                "<title>"+emp.getEmpName()+"</title>\n"+
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" /> \n" +
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>\n"+
                
                "<link href=\"css/PostCss.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>\n"+
                "<link href=\"css/hrmsstyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>\n"+
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>\n"+
                
                "    <script type=\"text/javascript\" src=\"js/Jquery8ui.min.js\"></script>"+
                "<script type=\"text/javascript\" src=\"js/Jquery8.js\"></script>"+
                "<script language=\"Javascript\" type=\"text/javascript\" src=\"js/HoverMinified.js\"></script>"+
    
    "<link href=\"css/arrowBox.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">"+
    "<link href=\"css/googleAdds.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">\n"+
    "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/SrapesUtil.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/TickersUtil.js\"></script>\n"+
    "<script type=\"text/javascript\" src=\"js/forum/lazyLoading.js\"></script>\n"+
    
    "<style type=\"text/css\">$(\"img.lazy\").lazyload();"+
    
    	".tickerSection{"+
	
	"display: block;"+
	"border-bottom-color:#CFCFCF;"+
	"border-bottom-style: inset;"+
	"border-bottom-width: 1px;"+
	"vertical-align: top;"+
	"padding-top: 5px;"+
	"padding-bottom: 5px;"+
	"cursor:pointer;cursor:hand;"+
	"}"+
    "</style>";
                              
 
        response.getWriter().write(head);
        response.getWriter().flush();
        response.getWriter().println("\n<script>function arrived(id,type,text) {");
        		//"{alert('hi');"+
        		response.getWriter().println("var newJ= $.parseJSON(text);");
        		response.getWriter().flush();
        		response.getWriter().println("if(type=='post'){");
        		response.getWriter().println("addMyPostDynamically(newJ,0);}");
        		response.getWriter().println("else if(type=='likeNotification'){");
        		response.getWriter().flush();
        		response.getWriter().println("addLikeNotificationDynamically(newJ);}");
        		response.getWriter().println("else if(type=='commentNotification'){");
        		response.getWriter().println("addCommentNotificationDyna(newJ);}");
        		response.getWriter().println("else if(type=='likeNotificationCount'){");
        		response.getWriter().println("addLikeNofiCount(text);}");
        		response.getWriter().flush();
        		response.getWriter().println("else if(type=='CommentNotificationCount'){");
        		response.getWriter().println("addCommentNotiCount(text);}");
        		response.getWriter().println("else if(type=='messageNofitication'){");
        		response.getWriter().println("addMessageNotification(text);}");
        		response.getWriter().println("else if(type=='forumNotification'){");
        		response.getWriter().println("addForumNotificationText(text);}");
        		response.getWriter().flush();
        		response.getWriter().println("else if(type=='ticker'){");
        		response.getWriter().println("addMyTickerDynamically(newJ);}");
        		response.getWriter().println("else if(type=='birth'){");
        		response.getWriter().println("addMyDynamicBirthEvents(newJ);}");
        		response.getWriter().println("else if(type=='public'){");
        		response.getWriter().println("addPublicChats(newJ);}");
        		response.getWriter().flush();
        		response.getWriter().println("else if(type=='album'){");
        		response.getWriter().println("addMyRecentlyUploadedPhotoAlbums(newJ);}");
				response.getWriter().println(" }</script>");
       response.getWriter().flush();
//        response.getWriter().write(" <script>" +
//        		"window.onbeforeunload = function () {"+
//
//        		"alert('hi closing');"+
//        ""+
//    "};" +
//        		"</script>" +
//        		"");
//        response.getWriter().flush();
        String feedLink = PropertyReader.getValue("rssNewFeeds",req);
        response.getWriter().write("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script> "+
          		"<script src=\"http://www.google.com/uds/solutions/dynamicfeed/gfdynamicfeedcontrol.js\" "+
          		"type=\"text/javascript\"></script>");
        response.getWriter().write("<style type=\"text/css\"></style><script type=\"text/javascript\">function load() {var feed =\""+feedLink+"\";new GFdynamicFeedControl(feed, \"feedControl\");}google.load(\"feeds\", \"1\");google.setOnLoadCallback(load);</script>");
        response.getWriter().flush(); 
        response.getWriter().write("</HEAD><body  marginheight=\"0\" marginwidth=\"0\" style=\"z-index:-10;\"");
          response.getWriter().flush();
        String body="";
        body="<div align=\"center\" id=\"header\" class=\"mydivStyleIns\">";
        
	  body=body+"<div style=\"background-color: #00688B;height: 60px;width: 100%;\" onmouseover=\"hideUserInfo('sdf')\">"+
	  		"<div style=\"height: 10px;\"></div>"+
	  		"<div style=\"padding-left: 100px;vertical-align: baseline;\" align=\"left\">"+
	  		
	  			"<span  class=\"NewHeaderStyle\">"+AppConstants.APP_NAME+"</span>"+
	  		"</div>"+
	  "</div>";
	  response.getWriter().write(body);
      response.getWriter().flush();
	  String homeImage="";
      if(emp!=null && emp.getImage()!=null){
    	  homeImage="<img src=\""+PropertyReader.getValue("photoAlbumPath",req)+emp.getImage().getPhotoPath().replaceFirst("0_","7_")+"\" width=\"30px\" height=\"30px\"> ";
      }
        body="<div align=\"center\" style=\"z-index:-20;\"><div style=\"width: 100%;z-index:-20;\" align=\"center\" onmouseover=\"hideUserInfo('sdf')\">"+
		"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  onmouseover=\"hideUserInfo('sdf')\">"+
			"<tr>"+
			"<td style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;padding-left:5px;background-color: #33A1C9;height=\"30px;\"\">"+
			"<img id=\"myLNotification\" onclick=\"showLikeNoticiation(\'"+emp.getId()+"\')\" class=\"iconstyle\"  src=\"img/likeNotificiation.png\" width=\"30px\" height=\"25px\"><span class=\"myCommetnIconStyle1\" id=\"likeNotifyIcon\"></span>&nbsp;&nbsp;<img id=\"myCNotification\" onclick=\"showCommentNoticiation(\'"+emp.getId()+"\')\" class=\"iconstyle\"  src=\"img/commentsMain.png\" width=\"30px\" height=\"25px\"><span class=\"myCommetnIconStyle\" id=\"commentNotificationIcon\"></span>"+
			""+
		"</td>"+
				"<td valign=\"bottom\" width=\"70%\" align=\"right\" style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
					"<table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\">"+
						"<tr>"+
							"<td align=\"right\" class=\"smallIconstle\">"+
							"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
							"onmouseover=\"this.className='headerLinkMO'\" href=\"Feeds\" >"+homeImage+"</a></td>"+
							"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
								"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
								"onmouseover=\"this.className='headerLinkMO'\" href=\"Feeds\" ><span class=\"homeImage1\">Home</span></a>"+
							"</td>"+
						//	"<% if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {%>"+
							"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
								"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
								"onmouseover=\"this.className='headerLinkMO'\" href=\"UserFeedInfo\">Profile</a>"+
							"</td>"+
					
							
							//"<% if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {%>"+
							"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
								"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
								"onmouseover=\"this.className='headerLinkMO'\" href=\"photoall.jsp\" >Photo</a>"+
							"</td>"+
							
							
							"<td class=\"headerGap\" onmouseover=\"this.className='headerLinkMOBlock'\" onmouseout=\"this.className='headerLinkBlock'\" align=\"center\">"+
								"<a class=\"headerLink\" onmouseout=\"this.className='headerLink'\""+
								"onmouseover=\"this.className='headerLinkMO'\"  href=\"logout.jsp\" >Log out</a>"+
							"</td>"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+
		"</table></div>";
        response.getWriter().write(body);
        response.getWriter().flush();
        body="<div id=\"LikeInfoNotification\" class=\"uparrowdiv\">"+
    	"<div width=\"100%\" class=\"myPopupStyleInNotification\"><table width=\"100%\"><tr><td align=\"left\"> <span class=\"NoficationHeading\" align=\"left\">Like Notification </span></td><td align=\"right\"><span id=\"myCLoseButton\" class=\"likeColose\" onclick=\"closeNotification()\"><img src=\"icon/close.png\" class=\"deleteIconStyhle\"/></span></td></tr></table></div>"+
    	"<div id=\"addDynamicNotify\"></div><div class=\"showMoreNotificaiton\" id=\"showmoreOption\"></div> "+
    "</div>";
        response.getWriter().write(body);
        response.getWriter().flush();
    body="<div id=\"CommentInfoNotification\" class=\"uparrowdiv\">"+
	"<div width=\"100%\" class=\"myPopupStyleInNotification\"><table width=\"100%\"><tr><td align=\"left\"> <span class=\"NoficationHeading\" align=\"left\">Comment Notification </span></td><td align=\"right\"><span id=\"myCLoseButtonComment\" class=\"likeColose\"  onclick=\"closeCNotification()\"><img src=\"icon/close.png\" class=\"deleteIconStyhle\"/></span></td></tr></table></div>"+
	"<div id=\"addDynamicCommentNotify\"></div> <div class=\"showMoreNotificaiton\" id=\"showmoreOptionComment\"></div> "+
"</div>";
    response.getWriter().write(body);
    response.getWriter().flush();
       body=""+
		"<div style=\"display: table; width: 100%;vertical-align: top;\" align=\"center\">"+
			// left corner side
			"<div style=\"display: table-cell; width: 5%;vertical-align: top;\" align=\"left\" onmouseover=\"hideUserInfo('sdf')\">"+
			
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
													body+="<img src=\""+PropertyReader.getValue("photoAlbumPath",req) + emp.getImage().getPhotoPath().replaceFirst("0_", "6_")+"\"  width=\"200\"/>";
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
							"</tr>";
							 }
							 body+="<tr>"+
						"<td class=\"headerGap1\" style=\"background-color: #AEC9E2;\" onmouseover=\"this.className='headerLinkMOBlock1' \" onmouseout=\"this.className='headerLinkBlock1'\">"+
							"<a href=\"Feeds\" class=\"leftLinkHeader\">"+
							"<img class=\"iconStyleLeftHeadings\" src=\"icon/feeds.jpg\"/>Feeds</a>"+
						"</td>"+
					"</tr>";
							 body+="<tr>"+
								"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1' \" onmouseout=\"this.className='headerLinkBlock1'\">"+
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
							"<a href=\"Forums\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/forum.jpg\"/>Forums<span id=\"forumNotificationCount\"></span></a>"+
						"</td>"+
					"</tr>"+
					"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
					"<a href=\"videoUploader.jsp?videoId="+emp.getId()+"\"  class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/Video.jpg\"/>Videos</span></a>"+
				"</td>"+
			"</tr>"+
					
						"</table>"+
							"</td>"+
						"</tr>"+
					"</table>"+
				"</div>"+
				
			"</div>";
							 response.getWriter().write(body);
							 response.getWriter().flush();
							// end of left corner, now middle one
			body="<div style=\"display: table-cell; vertical-align: top;\" class=\"middleSectionStyulePost\" align=\"left\">"+
				
			 "<br>"+
				"<div style=\"vertical-align: top; width: 70%;padding-left: 5px;\" class=\"borderSectionStyhle\" onmouseover=\"hideUserInfo('sdf')\">";
					if(emp.getImage()!=null)
				body+=		"<input type=\"hidden\" id=\"txtPhotoPath\" value=\""+emp.getImage().getPhotoPath()+"\">	";
					else
						body+=		"<input type=\"hidden\" id=\"txtPhotoPath\" value=\"images/person.jpg\">	";
					
			body+=	"<input type=\"hidden\" id=\"txtEmpId\" value=\""+emp.getId()+"\">	"+
					"<input type=\"hidden\" value=\""+emp.getId()+"\" id=\"hiddenCurrentUser\" >"+
					"<input type=\"hidden\" id=\"txtEmpIdTarget\" value=\""+emp.getId()+"\">	"+
					"<input type=\"hidden\" id=\"txtEmpName\" value=\""+emp.getEmpName()+"\">		"+	
					"<input type=\"hidden\" id=\"basePathOfPhoto\" value=\""+PropertyReader.getValue("photoAlbumPath",req)+"\" >"+
					"<input type=\"hidden\" id=\"videoPath\" value=\""+PropertyReader.getValue("videoPath",req)+"\" >"+
					"<input type=\"hidden\" value=\""+emp.getId()+"\" id=\"hiddenCurrentUser\" >"+
					"<input type=\"hidden\" value=\"1\" id=\"flow\" >"+
					"<div><table class=\"mainTableStyule\" cellpadding=\"0\" cellspacing=\"0\" ><tr><td><textarea style=\"width:520px;\"  class=\"comment_empty1\" id=\"txtNewPost\" >what's on your mind?</textarea></td></tr>"+
					"<tr><td id=\"enlargeMe\" class=\"myPostStyleBox\" width=\"100%\"><table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\"><tr><td><input type=\"button\" onclick=\"addPost3()\" value=\"Post\" class=\"txtPostButton\"></td></tr></table></td></tr></table></div>"+
				"</div>"+
				
				"<div id=\"insertPost\" class=\"borderSectionStyhle\" style=\"width: 20%;vertical-align: top;\" onmouseover=\"hideUserInfo('sdf')\" >"+
					
				"</div>"+
				"<div class=\"borderSectionStyhle1\"><input type=\"hidden\" name=\"lastTime\" id=\"lastTime\"><table><tr><td><img src=\"img/progressbar.gif\" id=\"progressMore\" width=\"50px\" height=\"20px\" class=\"hideMeFirst\"></td><td><span onclick=\"showmorepost()\" class=\"moreStylePostMore\">more post</span></td></tr></table></div>"+
				
			"</div>"+
			// end of middle one, now last Ticker column
			"<div style=\"display: table-cell; width: 15%;vertical-align: top;\" align=\"left\">"+
			"<div  class=\"BirthdDayHeading\"   onmouseover=\"hideUserInfo('sdf')\">Photo Updates</div>"+
				"<div id=\"tickerDiv\"  align=\"left\"  style=\"width:100%;height: 200px;overflow: auto;color: #334422;vertical-align: top;background-color:#DFF0F7;\">"+
				//"<div>Photo Updates</div>"+
				
				
					"<div id=\"insertTickers\"  align=\"left\" style=\"border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;overflow: auto;background-color:#DFF0F7;\">"+
					
					"</div>"+
				"</div>"+
				"<div style=\"height: 3px;\" class=\"borderLineTOSeparate\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
				"<div class=\"BirthdDayHeading\" onmouseover=\"hideUserInfo('sdf')\">Upcoming events</div>"+
				"<div id=\"birdthDayDiv\" style=\"height: 50px;overflow:hidden;\" onmouseover=\"hideUserInfo('sdf')\">"+
					
				"</div>"+
				
				
				"<div style=\"height: 5px;\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
				"<div style=\"height: 3px;\" class=\"borderLineTOSeparate\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
				"<div  class=\"BirthdDayHeading\"   onmouseover=\"hideUserInfo('sdf')\"> Recently Uploaded Photo Albums</div>"+
				// add online users list
				"<div class=\"onlineUserStyle\" style=\"height: 200px;\" id=\"myDiv\"   ></div>"+
				"<div style=\"height: 3px;\" class=\"borderLineTOSeparate\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
			
//"<div id=\"userInfo\" class=\"tickerPopup\"  ></div>"+
				// Style=\"background-color:pink;height:auto;position: absolute;overflow: auto;background-color: white;z-index: 10;width: 310px;\"
				"<div id=\"userInfo\" class=\"rightarrowdiv\"></div>"+
				"<div id=\"myPostPopUp\" class=\"leftarrowdiv\" style=\"width:500px;\"></div>"+
			"</div>"+
			// now bulletien column
			"<div style=\"display: table-cell; width: 15%;vertical-align: top;padding-left:5px;\" align=\"left\" onmouseover=\"hideUserInfo('sdf')\">"+
			
			"<div id=\"bullentinDiv1\"  align=\"left\"  class=\"lastSectionBullentinNews\" onmouseover=\"hideUserInfo('sdf')\">"+
			"<div class=\"BirthdDayHeading\" >News</div>"+
			"<div id=\"bulletinColumns\"  align=\"center\" class=\"displayBulletien\" onmouseover=\"hideUserInfo('sdf')\">"+
				"<div id=\"feedControl\" class=\"feedControlCSS\">Loading...</div>"+
			"</div>"+
			"</div>";
		
			 response.getWriter().write(body);
			 response.getWriter().flush();
			
			body="<div style=\"height: 3px;\" class=\"borderLineTOSeparate\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
			"<div class=\"BirthdDayHeading\" >Huddle</div>"+
			"<input type=\"text\" name=\"chatId\" id=\"chatId\" class=\"chatTextBox\" >"+
			"<div id=\"bullentinDiv\"  align=\"left\"  class=\"lastSectionBullentin\" onmouseover=\"hideUserInfo('sdf')\">"+
				
				"<div id=\"bulletinColumn\"  align=\"left\" class=\"displayBulletien\" onmouseover=\"hideUserInfo('sdf')\">"+
				
				"</div>"+
			"</div>"+
			"<div style=\"height: 3px;\" class=\"borderLineTOSeparate\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
			
		"</div>"+
		
		"</div>"+
	
	
	   " <div id=\"popupContact\" style=\"width: 50%;height: 50%;\" align=\"center\">"+
		"<a id=\"popupContactClose\" class=\"redbuttonColor\">x</a>"+
		
		"<iframe  style=\"margin: 0px;\" width=\"100%\" scrolling=\"no\" height=\"100%\" id=\"myIFrame\" frameborder=\"0\" ></iframe>"+
	"</div>"+
	"<div id=\"backgroundPopup\" style=\" height: 100%;\"></div>"+ 
          
 "";
        	
        	
        	
        	
        	
        	
        
			 response.getWriter().write(body);
			 response.getWriter().flush();
		      ctx.getResponse().getWriter().write(" <div id=\"dynaPopup\" ></div> ");
		      ctx.getResponse().getWriter().write(" <div id=\"popupContact\" style=\"width: 50%;height: 50%;\" align=\"center\">");
		      ctx.getResponse().getWriter().write("<a id=\"popupContactClose\" class=\"redbuttonColor\">x</a>");
		      ctx.getResponse().getWriter().flush();
		      ctx.getResponse().getWriter().write("<iframe  style=\"margin: 0px;\" width=\"100%\" scrolling=\"no\" height=\"100%\" id=\"myIFrame\" frameborder=\"0\" ></iframe>");
		      ctx.getResponse().getWriter().write("</div></div>");
		      ctx.getResponse().getWriter().flush();
		      ctx.getResponse().getWriter().write("<div id=\"backgroundPopup\" style=\" height: 100%;\"></div> ");
		      ctx.getResponse().getWriter().write("</BODY></HTML>");
		      ctx.getResponse().getWriter().write("<script>$('#tickerDiv').slimScroll({");
		   	  ctx.getResponse().getWriter().write("height: '220px',");
		      ctx.getResponse().getWriter().write("alwaysVisible: false,");
		      ctx.getResponse().getWriter().write("size: '12px',");
		      ctx.getResponse().getWriter().write("color: '#555555'");
			  ctx.getResponse().getWriter().write("});</script>");
			  
			  ctx.getResponse().getWriter().flush();
			  ctx.getResponse().getWriter().write("<script>$('#bullentinDiv').slimScroll({");
			  ctx.getResponse().getWriter().write("       height: '500px',");
			  ctx.getResponse().getWriter().write("       alwaysVisible: false,");
			  ctx.getResponse().getWriter().write("       size: '12px',");
			  ctx.getResponse().getWriter().write("       color: '#555555'");
			  ctx.getResponse().getWriter().write("       });</script>");
			  ctx.getResponse().getWriter().flush();
			  ctx.getResponse().getWriter().write("<script>$('#myDiv').slimScroll({");
			  ctx.getResponse().getWriter().write("height: '236px',");
			  ctx.getResponse().getWriter().write("alwaysVisible: false,");
			  ctx.getResponse().getWriter().write("size: '12px',");
			  ctx.getResponse().getWriter().write("color: '#555555'");
			  ctx.getResponse().getWriter().write("});registerMyChatTextBox();</script>");
			  ctx.getResponse().getWriter().flush();
			 // spawn some task in a background thread
			  ctx.start(new Runnable() {
			    public void run() {

			      try {
//			        ctx.getResponse().getWriter().write(
//			            MessageFormat.format("<h1>Processing task in bgt_id:[{0}]</h1>",
//			            ctx.getResponse().getWriter().write(" <div id=\"sharePopupHere\" class=\"sharepopupClass\" ></div>");
			    	System.out.println("---------------------------------------------------------------------");
			        List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
			        LeftHeaderMethod ob= new LeftHeaderMethod(ctx.getResponse().getWriter(),resp, emp);
			       RightBirthDays ob3=new RightBirthDays(ctx.getResponse().getWriter(),resp);
			        TickerRightSection ob2=new TickerRightSection(ctx.getResponse().getWriter(),resp);
			        LikeInfoNotificationCall ob4=new LikeInfoNotificationCall(ctx.getResponse().getWriter(),resp, emp);
			        CommentInfoNotificationCall ob5=new CommentInfoNotificationCall(ctx.getResponse().getWriter(),resp, emp);
			        RecentlyUploadedphotoAlbums ob6=new  RecentlyUploadedphotoAlbums(ctx.getResponse().getWriter(),resp);
			        PublicChatSection ob7=new PublicChatSection(ctx.getResponse().getWriter(), resp);
			        tasks.add(ob);
			        tasks.add(ob3);
			        tasks.add(ob2);
			       tasks.add(ob4);
			        tasks.add(ob5);
			        tasks.add(ob6);
			        tasks.add(ob7);
			        Long t1 = Calendar.getInstance().getTimeInMillis();
			        try {
			            exec.invokeAll(tasks, 10, TimeUnit.MINUTES);
			        } catch (InterruptedException ignored) {
			            // ignored
			        	ignored.printStackTrace();
			        }
//			        try{
//			        	exec.awaitTermination(7, TimeUnit.MINUTES);
//			        }catch(Exception ex){
//			        	
//			        }
			        
			        
			      }
			      catch (IOException e) {
			        e.printStackTrace();
			      }
//			      String lastSection="<script>$('.tickerSection').children().bind('mouseenter', function () {"+
//						   "var keyFull=$(this).parent().attr('id');"+
//						   "var k = '#'+keyFull;"+
//						   "$(k).css({'background-color':'#EEEEEE'});"+
//						  " getTickerDisplay(keyFull);"+
//					   
//					"});"+
//					"$('.tickerSection').children().bind('mouseleave', function () {"+
//						  " var keyFull=$(this).parent().attr('id');"+
//						   "var k = '#'+keyFull;"+
//						   "$(k).css({'background-color':'#DFF0F7'});"+
//						  // getTickerDisplay(keyFull);
//					   
//					"});registerEvent();</script>";
			      String  lastSection="<script>registerEvent();</script>";
				
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
				
     
					try {
						System.out.println(" ok last section here rpinting " + lastSection);
						ctx.getResponse().getWriter().write(lastSection);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				try {
					ctx.getResponse().getWriter().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     ctx.complete();
			    }
			  });
			  
			  
 
       // executor.shutdown();
       
       
    }
 

 
//    private void pagelet(PrintWriter writer, String id, String content,String type,HttpServletResponse resp) {
//    	
//        if (writer.checkError()){
//        	//println(" Sopme error while writer " );
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
			        "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
			        "</script>\n");
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class TickerRightSection implements Callable<Boolean>{
    	
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public TickerRightSection(PrintWriter w,HttpServletResponse resp) {
			// TODO Auto-generated constructor stub
    		this.writer=w;
    		this.resp=resp;
    		
    	//	System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		tickerContents();
    		return true;
    	}
    	
    	private void tickerContents() {
//            if (writer.checkError()){ 
//              try {
//				writer = resp.getWriter();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
              
          //  System.out.println(" man eror here again " );
            
//            writer.flush();
//            }
    		Session con = CassandraDB.getCassConnection();
    		
    		try {
    			List<TickerInfoDomain> list= PostUtil.getAllTickers();
    			
    			int i=0;
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
    			String resp1 = strWriter.toString();
    			
    			//System.out.println(" value si " + value1);
    			resp1=resp1.replaceAll("\"","\\\\\"");
    			
    			
    			while(resp1.contains("\\\\\\\\")){
    				int ss = resp1.indexOf("\\\\\\\\");
    				StringBuffer b=new StringBuffer(resp1);
    				b=b.replace(ss, ss+1, "");
    				resp1=b.toString();
    			}
    		//	resp=resp.replaceAll("\\\\","\\");
    			//pageletLeftHeader(writer,resp);
    			pagelet(writer,"insertTickers",resp1,"ticker",resp);
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
           // if (writer.checkError()){ writer.flush();}
           // System.out.println(" here i am called  - " + id +" - " + Calendar.getInstance().getTimeInMillis());
            try {
				writer.write("<script>" +
				        "registerMyEvents();" +
				        "</script>\n");
				 writer.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
        }
        
    }
    /**------------------------------------------------------------------------------*/
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class LeftHeaderMethod implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public LeftHeaderMethod(PrintWriter w,HttpServletResponse resp, EmpInfo info) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		pageletLeftHeaderContents();
    		return false;
    	}
    	
    	private void pageletLeftHeaderContents() {
           
    		Session con = CassandraDB.getCassConnection();
    		try {
    			
    			int i=0;
    			HashMap<Date,List<PostInfo>> ob=new HashMap<Date, List<PostInfo>>();
    			Calendar start= Calendar.getInstance();
    			Timestamp lastTime =new Timestamp(start.getTimeInMillis());
    			//ob= PostUtil.getAllPosts(new Date(lastTime.getTime()));
    			List<PostInfo> p  = ob.get(lastTime);
    			int count =0;
    			
    			int maxCount=0;
    			
    				//ob= PostUtil.getAllPosts(lastTime);
    				p  = ob.get(lastTime);
    				
	    			for(PostInfo list:p){
	    				//System.out.println(" list. " + list.getPostedDesc());
	    				if(list.getPostedPhotoId()!=null){
	    					if(list.getPostType()==1){}else{
	    					//	list.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(list.getPostedPhotoId(), con));
	    					}
	    				}else{
	    					list.setPostedByPhotoInfo(null);
	    				}
	    				System.out.println(" posted type = " + list.getPostType());
	    				count+=1;
	    				
	            		list.setLikes(PostLikeDAO.getLikesForPostId(con, list.getKey()));
	            		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(list.getKey(), con);
	            		//SortComments s=new SortComments("PostComments");
	    				//Collections.sort(commList,s);
	            		list.setComments(commList);
	            		lastTime = list.getPostedTime();
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
	    			String resp1 = strWriter.toString();
	    			
	    			System.out.println(" post JSON " + resp1);
	    			resp1=resp1.replaceAll("\"","\\\\\"");
	    			
	    			
	    			while(resp1.contains("\\\\\\\\")){
	    				int ss = resp1.indexOf("\\\\\\\\");
	    				StringBuffer b=new StringBuffer(resp1);
	    				b=b.replace(ss, ss+1, "");
	    				resp1=b.toString();
	    			}
	    		//	resp=resp.replaceAll("\\\\","\\");
	    			
	    			//pageletLeftHeader(writer,resp);
	    			pagelet(writer,"insertPost",resp1,"post",resp);
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
    public class RightBirthDays implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public RightBirthDays(PrintWriter w,HttpServletResponse resp) {
			// TODO Auto-generated constructor stub
    		this.writer=w;
    		this.resp=resp;
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		birthdayEvents();
    		return false;
    	}
    	
    	private void birthdayEvents() {
//            if (writer.checkError()) {
//            	 try {
//     				writer = resp.getWriter();
//     			} catch (IOException e) {
//     				// TODO Auto-generated catch block
//     				e.printStackTrace();
//     			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    			java.util.List<EmpInfo> empList=	EmpInfoDAO.getUpcomingBirthdays(con);
    			//System.out.println(" List of birthd days are " + empList.size());
    			SortComments ob=new SortComments("dateBirth");
    			Collections.sort(empList,ob);
    			int i=0;
    			if(empList!=null && empList.size()>0){
    			EmpInfo list = empList.get(0);
    				
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, list);
    			String resp1 = strWriter.toString();
    			
    			//System.out.println(" value si " + resp1);
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
    			pagelet(writer,"insertPost",resp1,"birth",resp);
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
    public class LikeInfoNotificationCall implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	public LikeInfoNotificationCall(PrintWriter w,HttpServletResponse resp,EmpInfo info) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		likeInfoNotificationFlow();
    		return true;
    	}
    	
    	private void likeInfoNotificationFlow() {
//            if (writer.checkError()){
//            	//System.out.println(" hhhhhh errororr ");
//            	 try {
//     				writer = resp.getWriter();
//     			} catch (IOException e) {
//     				// TODO Auto-generated catch block
//     				e.printStackTrace();
//     			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    			int i=0;
    			List<LikeNotificationJson> p= LikeNotificationsDAO.getLikeNotificationsLive( info.getId());
    			//SortComments s=new SortComments("likeTime");
				//Collections.sort(p,s);
    			for(LikeNotificationJson list:p){
            		if(list.getStatus()==1)
            			i=i+1;
    			//ObjectMapper mapper = new ObjectMapper();
    			//Writer strWriter = new StringWriter();
    			//mapper.writeValue(strWriter, list);
    			//String resp1 = strWriter.toString();
    			//System.out.println(" notification resp " + resp1);
    			//System.out.println(" value si " + value1);
    			//resp1=resp1.replaceAll("\"","\\\\\"");
    			
    			
    			//while(resp1.contains("\\\\\\\\")){
    			//	int ss = resp1.indexOf("\\\\\\\\");
    			//	StringBuffer b=new StringBuffer(resp1);
    			//	b=b.replace(ss, ss+1, "");
    			//	resp1=b.toString();
    			//}
    		
    			//pagelet(writer,"insertPost",resp1,"likeNotification",resp);
    			
            	
    			}
    			//System.out.println(" total count  " +i);
    			
    				pagelet(writer,"insertPost",String.valueOf(i),"likeNotificationCount",resp);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
        
    }
    /**------------------------------------------------------------------------------*/
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
    public class CommentInfoNotificationCall implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 PrintWriter writer;
    	HttpServletResponse resp;
    	
    	public CommentInfoNotificationCall(PrintWriter w,HttpServletResponse resp,EmpInfo info) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		commentInfoNotificationFlow();
    		return false;
    	}
    	
    	private void commentInfoNotificationFlow() {
//            if (writer.checkError()){
//            	//System.out.println(" hhhhhh errororr ");
//            	 try {
//     				writer = resp.getWriter();
//     			} catch (IOException e) {
//     				// TODO Auto-generated catch block
//     				e.printStackTrace();
//     			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    			int i=0;
    			List<CommentNotificationJson> p= CommentNotificationsDAO.getCommentNotifications( info.getId());
    			//SortComments s=new SortComments("commentTime");
				//Collections.sort(p,s);
    			for(CommentNotificationJson list:p){
            		if(list.getStatus()==1)
            			i=i+1;
    			//ObjectMapper mapper = new ObjectMapper();
    			//Writer strWriter = new StringWriter();
    			//mapper.writeValue(strWriter, list);
    			//String resp1 = strWriter.toString();
    			//System.out.println(" value si " + value1);
    			//resp1=resp1.replaceAll("\"","\\\\\"");
    			
    			
    			//while(resp1.contains("\\\\\\\\")){
    			//	int ss = resp1.indexOf("\\\\\\\\");
    			//	StringBuffer b=new StringBuffer(resp1);
    		//		b=b.replace(ss, ss+1, "");
    		//		resp1=b.toString();
    		//	}
    		
    			//pagelet(writer,"insertPost",resp1,"commentNotification",resp);
    			
            	
    			}
    			//System.out.println(" total count  " +i);
    			List<MessageLinks> links= MessageLinksDAO.getMessageLinksForUserId(con,info.getId());
    			int cccc=0;
    			HashMap<String,Integer> cc=new HashMap<String, Integer>();
    			for(MessageLinks l:links){
    				if(l.getStatus()==0){
    					if(cc.containsKey(l.getFromId())){
    						int val=cc.get(l.getFromId());
    						val+=1;
    						cc.put(l.getFromId(), val);
    						cccc+=1;
    					}else{
    					cccc=cccc+1;
    					cc.put(l.getFromId(),cccc);
    					}
    				}
    			}
    			//
//    			List<ForumNotification> lisNot= ForumNotificationDAO.getActiveForumNotification(con, info.getId());
//    				pagelet(writer,"insertPost",String.valueOf(i),"CommentNotificationCount",resp);
//    				pagelet(writer,"insertPost",String.valueOf(cccc),"messageNofitication",resp);
//    				if(lisNot!=null && lisNot.size()>0)
//    				pagelet(writer,"insertPost",String.valueOf(lisNot.size()),"forumNotification",resp);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
    }
    public class RecentlyUploadedphotoAlbums implements Callable<Boolean>{
   

   	PrintWriter writer;
   	HttpServletResponse resp;
   	
   	public  RecentlyUploadedphotoAlbums(PrintWriter w,HttpServletResponse resp) {
			// TODO Auto-generated constructor stub
		
		this.writer=w;
   		this.resp=resp;
   		//System.out.println(" 1nd time consruction");
		}
   	
   	@Override
   	public Boolean call() throws Exception {
   		// TODO Auto-generated method stub
   		RecentlyUploadadphotoAlbumFlow();
   		return false;
   	}
   	
   

	private void RecentlyUploadadphotoAlbumFlow() {
//           if (writer.checkError()){
//           	//System.out.println(" hhhhhh errororr ");
//           	 try {
//    				writer = resp.getWriter();
//    			} catch (IOException e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    			}
//           	writer.flush();}
		Session con = CassandraDB.getCassConnection();
   		try{
   			HashMap<String, String> map=new HashMap<String,String>(); 
    		
    	
   			int i=0;
   			@SuppressWarnings("unchecked")
			List<PhotoAlbumInfo> r=PhotoAlbumDAO.getAllAblumnsWithLatest(con);
   			///SortComments oob=new SortComments("PhotoDisplaySort");
   			//Collections.sort(r,oob);
   			
   			for(PhotoAlbumInfo list: r){
//           		if(list.getIdPhotoAlbum() != null)
           			i=i+1;
           	if(!(i<=5))
           		break;
   			ObjectMapper mapper = new ObjectMapper();
   			Writer strWriter = new StringWriter();
   			mapper.writeValue(strWriter, list);
   			String resp1 = strWriter.toString();
   			//System.out.println(" value si " + value1);
   			resp1=resp1.replaceAll("\"","\\\\\"");
   			
   			
   			while(resp1.contains("\\\\\\\\")){
   				int ss = resp1.indexOf("\\\\\\\\");
   				StringBuffer b=new StringBuffer(resp1);
   				b=b.replace(ss, ss+1, "");
   				resp1=b.toString();
   			}
   		
   			pagelet(writer,"UploadPhoto",resp1,"album", resp);
   			
           	
   			}
   			//System.out.println(" total count  " +i);
   			/*List<MessageLinks> links= MessageLinksDAO.getMessageLinksForUserId(con,info.getId());
   			int cccc=0;
   			HashMap<String,Integer> cc=new HashMap<String, Integer>();
   			for(MessageLinks l:links){
   				if(l.getStatus()==0){
   					if(cc.containsKey(l.getFromId())){
   						int val=cc.get(l.getFromId());
   						val+=1;
   						cc.put(l.getFromId(), val);
   						cccc+=1;
   					}else{
   					cccc=cccc+1;
   					cc.put(l.getFromId(),cccc);
   					}
   				}
   			}*/
   			//	pagelet(writer,"UploadPhoto","RecentlyUploadedPhotoAlbums",String.valueOf(i),resp);
   				/*pagelet(writer,"UploadPhoto",String.valueOf(cccc),"messageNofitication",resp);*/
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
    
    public class PublicChatSection implements Callable<Boolean>{
    	   

    	PrintWriter writer;
       	HttpServletResponse resp;
       	
       	public  PublicChatSection(PrintWriter w,HttpServletResponse resp) {
    			// TODO Auto-generated constructor stub
    		
    		this.writer=w;
       		this.resp=resp;
       		//System.out.println(" 1nd time consruction");
    		}
       	
       	@Override
       	public Boolean call() throws Exception {
       		// TODO Auto-generated method stub
       		publicChats();
       		return false;
       	}
       	
       

    	private void publicChats() {
//               if (writer.checkError()){
//               	//System.out.println(" hhhhhh errororr ");
//               	 try {
//        				writer = resp.getWriter();
//        			} catch (IOException e) {
//        				// TODO Auto-generated catch block
//        				e.printStackTrace();
//        			}
//               	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
       		
       		try{
       			java.util.List<PublicChat> chats= null;//PublicChatDAO.getAllChats(con);
       			//SortComments sort= new SortComments("chatSort");
       			//Collections.sort(chats,sort);
        		for(PublicChat d:chats){
        		
        			ObjectMapper mapper = new ObjectMapper();
           			Writer strWriter = new StringWriter();
           			mapper.writeValue(strWriter, d);
           			String resp1 = strWriter.toString();
           			//System.out.println("PUblic chat " + resp1);
           			//System.out.println(" value si " + value1);
           			resp1=resp1.replaceAll("\"","\\\\\"");
           			
           			
           			while(resp1.contains("\\\\\\\\")){
           				int ss = resp1.indexOf("\\\\\\\\");
           				StringBuffer b=new StringBuffer(resp1);
           				b=b.replace(ss, ss+1, "");
           				resp1=b.toString();
           			}
           		
           			pagelet(writer,"UploadPhoto",resp1,"public", resp);
        			
        		}
       		
       			//System.out.println(" total count  " +i);
       			/*List<MessageLinks> links= MessageLinksDAO.getMessageLinksForUserId(con,info.getId());
       			int cccc=0;
       			HashMap<String,Integer> cc=new HashMap<String, Integer>();
       			for(MessageLinks l:links){
       				if(l.getStatus()==0){
       					if(cc.containsKey(l.getFromId())){
       						int val=cc.get(l.getFromId());
       						val+=1;
       						cc.put(l.getFromId(), val);
       						cccc+=1;
       					}else{
       					cccc=cccc+1;
       					cc.put(l.getFromId(),cccc);
       					}
       				}
       			}*/
       			//	pagelet(writer,"UploadPhoto","RecentlyUploadedPhotoAlbums",String.valueOf(i),resp);
       				/*pagelet(writer,"UploadPhoto",String.valueOf(cccc),"messageNofitication",resp);*/
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
}
