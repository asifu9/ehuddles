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
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.datastax.driver.core.Session;
import com.pro.emp.AppConstants;
import com.pro.emp.CassandraDB;
import com.pro.emp.EmployeeInfo;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.SortComments;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpActInfoDAO;
import com.pro.emp.dao.EmpAddInfoDAO;
import com.pro.emp.dao.EmpCompanyInfoDAO;
import com.pro.emp.dao.EmpIdProofDAO;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.dao.PhotoInfoDAO;
import com.pro.emp.dao.PostCommentsDAO;
import com.pro.emp.dao.PostLikeDAO;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.PostComments;
import com.pro.emp.domain.PostInfo;
import com.pro.emp.util.Constant;
import com.pro.emp.util.PostUtil;
import com.pro.friends.domain.dao.FriendsDAO;

/**
 * Servlet implementation class Feeds
 */
@WebServlet("/UserOtherFeedInfo")
public class UserOtherFeedInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static ExecutorService executor = Executors.newFixedThreadPool(500, new ThreadFactory() {
	        public Thread newThread(Runnable r) {
	            Thread t = new Thread(r);
	            t.setName("Service Thread "+ t.getId());
	            t.setDaemon(true);
	            return t;
	        }
	    });
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOtherFeedInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter wri = resp.getWriter();
        BufferedWriter writer=new BufferedWriter(wri);
        EmpInfo sessionEmp=null;
        sessionEmp=Session_control.getSession(req);
        String otherKey="";
        if(req.getParameter("u")!=null){
        	otherKey=req.getParameter("u");
        }
        EmpInfo emp= Util.getEmployeeById(otherKey);
       
       // System.out.println(" emp " + emp.getEmpName());
        HashMap<String,Boolean> roleMap = EmployeeInfo.getRolesForEmpId(otherKey);
        String albumPath =PropertyReader.getValue("photoAlbumPath",req);
        String doctype = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "   \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
 
        String head = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"de\" lang=\"de\"> \n" +
                "<head> \n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"icon/browserIcon.png\">"+
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" /> \n" +
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "<title>"+emp.getEmpName()+"</title>"+
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" /> \n" +
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                
                "<link href=\"css/PostCss.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "<link href=\"css/hrmsstyle.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "<link href=\"css/myOwnPopup.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\"></link>"+
                "    <script type=\"text/javascript\" src=\"js/Jquery8ui.min.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/Jquery8.js\"></script>"+

    "<script type=\"text/javascript\" src=\"js/jquery.slimscroll.js\"></script>"+
    "<script type=\"text/javascript\" src=\"js/TickersUtil.js\"></script>"+
    "<style type=\"text/css\">"+
    
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
    "</style>"+
                "<script type=\"text/javascript\" src=\"js/SrapesUtil.js\"></script>";
               head=head+
               "<script type=\"text/javascript\">"+
               
               "function hideUserInfo(key){"+
	  
  "$('#userInfo').css('display','none');"+
"}"+

               //SETTING UP OUR POPUP
               //0 means disabled; 1 means enabled;
               "var popupStatus = 0;"+

               //loading popup with jQuery magic!
               "function loadPopup(albumId,ownerId,path,width,height){"+
               	//loads popup only if it is disabled
               	"if(popupStatus==0){"+
               		"$(\"#backgroundPopup\").css({"+
               			"\"opacity\": \"0.8\""+
               			
               		"});"+
               		//alert(" here data " + albumId + " : " + ownerId);
               		"document.getElementById(\"myIFrame\").src=\"\";"+
               		"var windowWidth2 = document.documentElement.clientWidth;"+
               		"var windowHeight2 = $(window).height(); "+
               		"windowHeight2 = windowHeight2+$(document).height()-$(window).scrollTop() ;  "+
               		"var urrl='photoList2.jsp?albumId='+albumId+'&ownerId='+ownerId+'&path='+path+'&windowWidth2='+windowWidth2+'&windowHeight2='+windowHeight2+'&acutalWidth='+width+'&actualHeight='+height;"+
               		
               		"document.getElementById(\"myIFrame\").src=urrl; "+
               	
               		//document.getElementById("myIFrame").contentDocument.location.reload(true);
               		"$(\"#backgroundPopup\").fadeIn(\"slow\");"+
               		"$(\"#popupContact\").fadeIn(\"slow\");"+
               		"popupStatus = 1;"+
               	"}"+
               "}"+

               //disabling popup with jQuery magic!
               "function disablePopup(){"+
               	//disables popup only if it is enabled
               	"if(popupStatus==1){"+
               		"$(\"#backgroundPopup\").fadeOut(\"slow\");"+
               		"$(\"#popupContact\").fadeOut(\"slow\");"+
               		"popupStatus = 0;"+
               	"}"+
               "}"+

               //centering popup
               "function centerPopup(){"+
            

               	"var windowWidth = document.documentElement.clientWidth;"+
               "	var windowHeight = document.documentElement.clientHeight;"+
               	"var popupHeight = $(\"#popupContact\").height()+20;"+
               	"var popupWidth = $(\"#popupContact\").width();"+
               	//centering
               	"$(\"#popupContact\").css({"+
               		"\"position\": \"absolute\","+
               		"\"top\": windowHeight/2-popupHeight/2,"+
               		"\"left\": windowWidth/2-popupWidth/2"+
               	"});"+
               	//only need force for IE6
               	
               	"$(\"#backgroundPopup\").css({"+
               		"\"height\": windowHeight"+
               	"});"+
               	
               "}"+

               "$(document).ready(function(){"+

               
               	"$(\".SendStyle\").click(function(){"+

               "	});"+
             
               	"$(\"#popupContactClose\").click(function(){"+
               		"disablePopup();"+
               		
               	"});"+
               	
               	"$(\"#backgroundPopup\").click(function(){"+
               		"disablePopup();"+
               	"});"+
               	//Press Escape event!
               	"$(document).keypress(function(e){"+
               		"if(e.keyCode==27 && popupStatus==1){"+
               			"disablePopup();"+
               		"}"+
               	"});"+

              " });"+

              " function updateHiddenBox (albumId,ownerId,path,pcount,width,height) {"+

              " $('#userInfo').css('display','none');"+
               		"centerPopup();"+
               		//load popup
               		"loadPopup(albumId,ownerId,path,width,height);"+
               		
               	"}"+
               	
                " </script>";

               
 
        writer.write(doctype);
        writer.write(head);
        writer.write("<script type=\"text/javascript\">function arrived(id,type,text) {"+
        		//"{alert('hi');"+
        		"var newJ= $.parseJSON(text);"+
        		"if(type=='post'){"+
        		"addMyPostDynamically(newJ);}"+
        		"else if(type=='ticker'){"+
        		"addMyTickerDynamically(newJ);}"+
        		"else if(type='basic'){"+
        		"addDynamicBasicInfoOthers(newJ);}"+
        		"else if(type='birth'){"+
        		"addMyDynamicBirthEvents(newJ);}"+
        		" }</script>");
        writer.write("<script type=\"text/javascript\">function registerMyEvents(){registerEvents();}</script>");
       // writer.write("<script type=\"text/javascript\">function arrivedTicker(id,text) {alert('hi');var newT= $.parseJSON(text); addMyTickerDynamically("+albumPath+",newT);var b=document.getElementById(id); }</script>");
        writer.write("<script type=\"text/javascript\">function arrivedLeftHeaderContent(data){alert(\"hi\");var b=document.getElementById(\"insertPost\");}</script>");
        writer.write("</HEAD><BODY  marginheight=\"0\" marginwidth=\"0\">");
        String body="";
        body="<div align=\"center\" id=\"header\" >"+
	  "<div style=\"background-color: #00688B;height: 60px;width: 100%;\">"+
	  		"<div style=\"height: 10px;\"></div>"+
	  		"<div style=\"padding-left: 100px;vertical-align: baseline;\" align=\"left\">"+
	  		
	  			"<span  class=\"NewHeaderStyle\">"+AppConstants.APP_NAME+"</span>"+
	  		"</div>"+
	  "</div>";
        
        body=body+"<div style=\"width: 80%\" >"+
		"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  onmouseover=\"hideUserInfo('sdf')\">"+
			"<tr>"+
			"<td style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
			"<span class=\"headerLink\" style=\"padding-left: 5px;\">"+sessionEmp.getEmpName()+"</span>"+
		"</td>"+
				"<td valign=\"bottom\" width=\"70%\" align=\"right\" style=\"padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;\">"+
					"<table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\">"+
						"<tr>"+
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
		"</table>";
        	
       body+=""+
       		"<div style=\"display: table; width: 100%;vertical-align: top;\" align=\"center\">"+
  		"<div id=\"addAllInfo\"></div>"+
  "</div>"+
		"<div style=\"display: table; width: 100%;vertical-align: top;\" align=\"center\">"+
		
			"<div style=\"display: table-cell; width: 15%%;vertical-align: top;\" align=\"left\" onmouseover=\"hideUserInfo('sdf')\">"+
			
				"<div>"+
					"<table>"+
						"<tr>"+
							"<td width=\"200px\">"+
								"<table>"+
									"<tr>"+
										"<td style=\"padding-top: 20px\">";
											
											
											
											 body+="<div id=\"photoInsert\"></div></td>"+
									"</tr>"+
									
								"</table>"+
								"<div style=\"padding-top: 5px\"></div>"+
						"<table width=\"100%\">";
							
							 if(sessionEmp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { 
								 body+="<tr> "+
								"<td class=\"headerGap1\" onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"editProfile.jsp?editEmpId="+sessionEmp.getId()+"\" class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/edit_profile.jpg\"/>Profile</a>"+
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
									"<a href=\"messages.jsp\" class=\"leftLinkHeader\">"+
									"<img class=\"iconStyleLeftHeadings\" src=\"icon/messages1.jpg\"/>Messages <span id=\"messageCountId\"></span></a>"+
								"</td>"+
							"</tr>";
							if(sessionEmp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){
							 body+="<tr> "+
								"<td class=\"headerGap1\"  onmouseover=\"this.className='headerLinkMOBlock1'\" onmouseout=\"this.className='headerLinkBlock1'\">"+
									"<a href=\"photo.jsp?editEmpId="+sessionEmp.getId()+"\" class=\"leftLinkHeader\"><img class=\"iconStyleLeftHeadings\" src=\"icon/photos.jpg\"/>Photo</a>"+
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
				
			"</div>";
		
			body+="<div style=\"display: table-cell; width: 55%;vertical-align: top;\" align=\"left\">"+
			"<div id=\"basicInfo\" class=\"borderSectionStyhle\" style=\"width: 80%;vertical-align: top;\"></div>"+
			 "<br>"+
				"<div style=\"vertical-align: top; width: 100%;padding-left: 5px;\" class=\"borderSectionStyhle\" onmouseover=\"hideUserInfo('sdf')\">"+
					
				
				
				"<div style=\"vertical-align: top; width: 70%;padding-left: 5px;\" class=\"borderSectionStyhle\" onmouseover=\"hideUserInfo('sdf')\">";
				if(emp.getImage()!=null)
			body+=		"<input type=\"hidden\" id=\"txtPhotoPath\" value=\""+emp.getImage().getPhotoPath()+"\">	";
				else
					body+=		"<input type=\"hidden\" id=\"txtPhotoPath\" value=\"images/person.jpg\">	";
					body+="<input type=\"hidden\" id=\"txtEmpId\" value=\""+sessionEmp.getId()+"\">	"+
					"<input type=\"hidden\" id=\"txtEmpIdTarget\" value=\""+emp.getId()+"\">	"+
					"<input type=\"hidden\" value=\""+sessionEmp.getId()+"\" id=\"hiddenCurrentUser\" >"+
					"<input type=\"hidden\" id=\"txtEmpName\" value=\""+sessionEmp.getEmpName()+"\">		"+	
					"<input type=\"hidden\" id=\"basePathOfPhoto\" value=\""+PropertyReader.getValue("photoAlbumPath",req)+"\" >"+
					"<input type=\"hidden\" value=\""+sessionEmp.getId()+"\" id=\"hiddenCurrentUser\" >"+
					"<input type=\"hidden\" value=\"0\" id=\"flow\" >"+
					"<div><input type=\"hidden\" name=\"lastTime\" id=\"lastTime\"><table class=\"mainTableStyule\" cellpadding=\"0\" cellspacing=\"0\" ><tr><td><textarea style=\"width: 420px;\" class=\"comment_empty1\" id=\"txtNewPost\" >what's on your mind?</textarea></td></tr>"+
					"<tr><td id=\"enlargeMe\" class=\"myPostStyleBox\" width=\"100%\"><table  cellpadding=\"0\" cellspacing=\"0\" align=\"right\"><tr><td><input type=\"button\" onclick=\"addPost1()\" value=\"Post\" class=\"txtPostButton\"></td></tr></table></td></tr></table></div>"+
				"</div>"+
				
				"<div id=\"insertPost\" class=\"borderSectionStyhle\" style=\"width: 80%;vertical-align: top;\" onmouseover=\"hideUserInfo('sdf')\">"+
					
				"</div>"+

			"</div></div>"+
			
			"<div style=\"display: table-cell; width: 30%;vertical-align: top;\" align=\"left\">"+
				"<div id=\"tickerDiv1\"  align=\"left\"  style=\"width:100%;height:700px;overflow: auto;color: #334422;vertical-align: top;\">"+
					"<div id=\"insertTickers1\"  align=\"left\" style=\"height:700px;border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;overflow: auto;\">"+
					
					"</div>"+
				"</div>"+
				"<div style=\"height: 5px;\" onmouseover=\"hideUserInfo('sdf')\"></div>"+
				
//"<div id=\"userInfo\" class=\"tickerPopup\"  ></div>"+
				"<div id=\"userInfo\"  Style=\"background-color:pink;height:auto;position: absolute;overflow: auto;background-color: white;z-index: 10;width: 310px;\" ></div>"+
			"</div>"+
		
		"</div>"+
	
	
	   " <div id=\"popupContact\" style=\"width: 50%;height: 50%;\" align=\"center\">"+
		"<a id=\"popupContactClose\" class=\"redbuttonColor\">x</a>"+
		
		"<iframe  style=\"margin: 0px;\" width=\"100%\" scrolling=\"no\" height=\"100%\" id=\"myIFrame\" frameborder=\"0\" ></iframe>"+
	"</div>"+
	"<div id=\"backgroundPopup\" style=\" height: 100%;\"></div>"+ 
          
 "";
        	
        	
        	
        	
        	
        	
        
        writer.write(body);
        writer.flush();
 
        List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
        LeftHeaderMethodINfo ob= new LeftHeaderMethodINfo(writer, emp,resp);
       // RightBirthDays ob3=new RightBirthDays(writer,resp);
        //TickerRightSection ob2=new TickerRightSection(writer);
        UserBasicInfo ob4=new UserBasicInfo(writer, emp,resp,sessionEmp.getId());
       
        tasks.add(ob);
        //tasks.add(ob3);
        //tasks.add(ob2);
        tasks.add(ob4);
      
 
        try {
            executor.invokeAll(tasks, 5, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {
            // ignored
        	ignored.printStackTrace();
        }
        writer.write(" <div id=\"dynaPopup\" ></div> ");
        writer.write(" <div id=\"popupContact\" style=\"width: 50%;height: 50%;\" align=\"center\">");
        writer.write("<a id=\"popupContactClose\" class\"redbuttonColor\">x</a>");

        writer.write("<iframe  style=\"margin: 0px;\" width=\"100%\" scrolling=\"no\" height=\"100%\" id=\"myIFrame\" frameborder=\"0\" ></iframe>");
        writer.write("</div>");
        		writer.write("<div id=\"backgroundPopup\" style=\" height: 100%;\"></div> ");
        String lastSection="<script>$('.tickerSection').children().bind('mouseenter', function () {"+
				   "var keyFull=$(this).parent().attr('id');"+
				   "var k = '#'+keyFull;"+
				   "$(k).css({'background-color':'#EEEEEE'});"+
				  " getTickerDisplay(keyFull);"+
			   
			"});"+
			"$('.tickerSection').children().bind('mouseleave', function () {"+
				  " var keyFull=$(this).parent().attr('id');"+
				   "var k = '#'+keyFull;"+
				   "$(k).css({'background-color':'white'});"+
				  // getTickerDisplay(keyFull);
			   
			"});registerEvent();</script>";
        writer.write(lastSection);
        writer.write("</BODY></HTML>");
        writer.flush();
    }
 
    private void content(PrintWriter writer, String... contentIds) {
        for (String id : contentIds) {
            writer.write("<div id=\"" + id + "\">-</div>\n");
        }
    }
 
    private void pagelet(BufferedWriter writer, String id, String content,String type) throws IOException {
//        if (writer.checkError()){
//        	//System.out.println(" Sopme error while writer " );
//        	writer.flush();}
       // System.out.println(" -- " + "<script>" +
       //         "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
       //         "</script>\n");
        writer.write("<script>" +
                "arrived(\"" + id + "\", \"" + type + "\", \""+content+"\");" +
                "</script>\n");
        writer.flush();
    }

    
    
    
    
    /**------------------------------------------------------------------------------*/
    // for main pagelet
     class LeftHeaderMethodINfo implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 BufferedWriter writer;
    	HttpServletResponse resp;
    	
    	public LeftHeaderMethodINfo(BufferedWriter w,EmpInfo info,HttpServletResponse resp) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		pageletLeftHeaderContentsdd();
    		return false;
    	}
    	
    	private void pageletLeftHeaderContentsdd() {
//            if (writer.checkError()){
//            	//System.out.println(" hhhhhh errororr ");
//            	 try {
//      				writer = resp.getWriter();
//      			} catch (IOException e) {
//      				// TODO Auto-generated catch block
//      				e.printStackTrace();
//      			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    		
    			int i=0;
    			HashMap<Timestamp,List<PostInfo>> ob=new HashMap<Timestamp, List<PostInfo>>();
    			Calendar start= Calendar.getInstance();
    			Timestamp lastTime =new Timestamp(start.getTimeInMillis());
    			
    			int count =0;
    			
    			int maxCount=0;
    			while(count <10)
    			// get post by 10 at a time
    			{
    				maxCount+=1;
    				//System.out.println(" count is "+count);
    				//System.out.println(" maxCount is "+maxCount);
    				if(maxCount>50)
    					break;
    				//ob= PostUtil.getAllPostsById(info.getId(),lastTime);
        			List<PostInfo> p  = ob.get(lastTime);
    				//ob= PostUtil.getAllPosts(lastTime);
    				//p  = ob.get(lastTime);
    				Calendar ccc= Calendar.getInstance();
					ccc.setTime(lastTime);
					ccc.add((Calendar.HOUR_OF_DAY), -10);
					lastTime= new Timestamp(ccc.getTimeInMillis());
    			
	    			for(PostInfo list:p){
	    			//	System.out.println(" list. " + list.getPostedDesc());
	    				if(list.getPostedPhotoId()!=null){
	    					//list.setPostedByPhotoInfo(PhotoInfoDAO.getPhotoById(list.getPostedPhotoId(), con));
	    				}else{
	    					list.setPostedByPhotoInfo(null);
	    				}
	     		
	
	    				
	            		list.setLikes(PostLikeDAO.getLikesForPostId(con, list.getKey()));
	            		List<PostComments> commList= PostCommentsDAO.getCommentsForPostId(list.getKey(), con);
	            		//SortComments s=new SortComments("PostComments");
	    			//	Collections.sort(commList,s);
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
	        				System.out.println(" \\t present 3");
	        			}
	        			if(value1.contains("\n")){
	        				System.out.println(" \\n present 4");
	        			}
	        			if(value1.contains("\b")){
	        				System.out.println(" \\b present 5");
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
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			///CassandraDB.freeConnection(con);
    		}
        }
    	
        private void pageletLeftHeader(PrintWriter writer,String data) {
            if (writer.checkError()){ System.out.println(" ------ erroir " ); writer.flush(); }
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
     class UserBasicInfo implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 BufferedWriter writer;
    	HttpServletResponse resp;
    	String ownerId;
    	public UserBasicInfo(BufferedWriter w,EmpInfo info,HttpServletResponse resp,String ownerId) {
			// TODO Auto-generated constructor stub
    		this.info=info;
    		this.writer=w;
    		this.resp=resp;
    		this.ownerId=ownerId;
    		//System.out.println(" 1nd time consruction");
		}
    	
    	@Override
    	public Boolean call() throws Exception {
    		// TODO Auto-generated method stub
    		basicInfo();
    		return false;
    	}
    	
    	private void basicInfo() {
//            if (writer.checkError()){
//            	System.out.println(" hhhhhh errororr ");
//            	 try {
//      				writer = resp.getWriter();
//      			} catch (IOException e) {
//      				// TODO Auto-generated catch block
//      				e.printStackTrace();
//      			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		//java.util.List<EmpInfo> empList= EmployeeInfo.getAllEmployees();
    		try {
//    			HashMap<String, String> map=new HashMap<String,String>(); 
//        		HashMap<String, String> map2=new HashMap<String,String>(); 
//        		for(EmpInfo d:empList){
//        			String name=d.getEmpName();
//        			String path =d.getImagePath();
//        			map.put(String.valueOf(d.getId()), name);
//        			map2.put(String.valueOf(d.getId()), path);
//        			
//        		}
    			// get emp basic information
    			com.pro.post.domain.UserBasicInfo infos=new com.pro.post.domain.UserBasicInfo();
    			infos.setKey(info.getId());
    			infos.setEmpInfo(info);
    			//infos.setActList(EmpActInfoDAO.getEmpActivityInfoById(con, info.getId()));
    			//infos.setAddInfo(EmpAddInfoDAO.getEmpAdditionaDetailsById(con, info.getId()));
    		////	infos.setComList(EmpCompanyInfoDAO.getEmpCompanyInfo(con, info.getId()));
    		//	infos.setIdProofList(EmpIdProofDAO.getEmpIdproofInfoById(con, info.getId()));
    			infos.setPhotoAlbumns(PhotoAlbumDAO.getAlbumByUserId(info.getId()));
    			//infos.setColleagues(EmployeeInfo.getAllColleageEmployeesInfo());
    		//	infos.setFriendStatus(FriendsDAO.getFriendStatus(ownerId, info.getId(), con));
    			ObjectMapper mapper = new ObjectMapper();
    			Writer strWriter = new StringWriter();
    			mapper.writeValue(strWriter, infos);
    			String resp = strWriter.toString();
    			System.out.println(" basic info " + resp);
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
    			pagelet(writer,"insertPost",resp,"basic");
            	/*for(BookContentDomain bcont:list){
            		buf=new StringBuffer();
            		buf.append(bcont.getId()+"~~"+bcont.getBookId()+"~~"+bcont.getChapterNo()+"~~"+bcont.getVerseNo()+"~~"+bcont.getMeaning());
            		pageletMainContentsWrite(writer,buf.toString());
            	}*/
    			
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		finally {
    			//CassandraDB.freeConnection(con);
    		}
        }
    	
        private void pageletLeftHeader(PrintWriter writer,String data) {
            if (writer.checkError()){ System.out.println(" ------ erroir " ); writer.flush(); }
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
     class RightBirthDays implements Callable<Boolean>{
    	
    	 EmpInfo info;
    	 BufferedWriter writer;
    	HttpServletResponse resp;
    	public RightBirthDays(BufferedWriter w,HttpServletResponse resp) {
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
//      				writer = resp.getWriter();
//      			} catch (IOException e) {
//      				// TODO Auto-generated catch block
//      				e.printStackTrace();
//      			}
//            	writer.flush();}
    		Session con = CassandraDB.getCassConnection();
    		try {
    			java.util.List<EmpInfo> empList=	EmpInfoDAO.getUpcomingBirthdays(con);
    			//System.out.println(" List of birthd days are " + empList.size());
    		//	SortComments ob=new SortComments("date");
    		//	Collections.sort(empList,ob);
    			int i=0;
    			for(EmpInfo list:empList){
    				
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
    			//System.out.println(" birthday is " + resp);
    		//	resp=resp.replaceAll("\\\\","\\");
    			//pageletLeftHeader(writer,resp);
    			pagelet(writer,"insertPost",resp,"birth");
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
            if (writer.checkError()){ System.out.println(" ------ erroir " ); writer.flush(); }
           // System.out.println(" here i am called  - " + id +" - " + Calendar.getInstance().getTimeInMillis());
          //  writer.write("<script>" +
          //          "arrivedLeftHeaderContent(\""+ data +"\");" +
          //          "</script>\n");
          //  writer.flush();
        }
        
    }
    /**------------------------------------------------------------------------------*/
}
