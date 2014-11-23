<%@page import="ch.maxant.blog.nio.servlet3.GetConnectToWebSite"%>
<%@page import="com.pro.emp.CassandraDB"%>
<%@page import="com.pro.emp.dao.PhotoInfoDAO"%>
<%@page import="com.pro.emp.domain.PhotoInfo"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.pro.emp.domain.PostLikeTable"%>
<%@page import="com.pro.emp.domain.PostComments"%>
<%@page import="com.pro.emp.domain.PostInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.util.PostUtil"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%
    
    EmpInfo emp=null;

/*  for(String s:cook){
	 System.out.println(" sssssssssssssssssssssss " + s);
	  
	
 } */
	HashMap<String,Boolean> roleMap=new HashMap<String,Boolean>();
		if(Session_control.getSession(request)!=null){
			emp= Session_control.getSession(request);
			System.out.println(" ---------------------- " + emp.getImagePath());
			System.out.println(" ---------------------- " + emp.getImage());
		}
		else {
			response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
		}
    
    %>
<html>
    <head>
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="jjs/jquery.min.js"></script>
    <script type="text/javascript" src="jjs/jquery-ui.js"></script>
    <script type="text/javascript" src="js/SrapesUtil.js"></script>
    <script type="text/javascript" src="js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="js/TickersUtil.js"></script>
     <link rel="stylesheet" href="jjs/managedivision.css">
    <script type="text/javascript" src="jjs/division.js"></script>
  
    
    <script type="text/javascript">
    var ssss='<%=emp.getId()%>';
    var ws = new WebSocket("ws://localhost:8080/sanahempinfo/wschat?"+ssss);
    ws.onopen = function(){
    };
    ws.onmessage = function(message){
    	console.log (" message raw " + message.data);
    	var obj = JSON.parse(message.data);
    	if(obj!=null){
    		if(obj.type=='Comment'){
    			insertNotifyComment(JSON.parse(obj.json));
    		}else if(obj.type=='Like'){
    			insertNOtifyLike(JSON.parse(obj.json));
    		}else if(obj.type=='Ticker' || obj.type=='LikeTicker'){
    			addMyTickerDynamically(JSON.parse(obj.json));
    		}else if(obj.type=='Chat'){
    			addPublicChatsInTop(JSON.parse(obj.json));
    		}else if(obj.type=='post'){
    			addMyPostDynamically(JSON.parse(obj.json),5);
    		}else if(obj.type=='other'){
    			addFollowCountNotify();
    		}else if(obj.type=='message'){
    			//addMessageCount(JSON.parse(obj.json));
    			addFollowCountNotify();
    		}
    		
    	}
		
    };
    function addFollowCountNotify(){
    	var sss=document.getElementById("messageCount").innerHTML;
		console.log("ssss " + sss);
		if(sss=='' ||sss==null){
			document.getElementById("messageCount").innerHTML= '(1)';
		}else{
			sss=sss.replace("(","");
			sss=sss.replace(")","");
			var count = parseInt(sss.trim())+1;
			document.getElementById("messageCount").innerHTML='('+count + ')';
		}
    }
google.load("feeds", "1"); //Load Google Ajax Feed API (version 1)
</script>
    <style type="text/css">
    
    	.tickerSection{
	
	display: block;
	border-bottom-color:#CFCFCF;
	border-bottom-style: inset;
	border-bottom-width: 1px;
	vertical-align: top;
	padding-top: 5px;
	padding-bottom: 5px;
	cursor:pointer;cursor:hand;
	}
    </style>

  <!--    <script type="text/javascript" src="js/jquery4.1.min.js"></script> -->
     
     <link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"></link>
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
 
     <link href="css/myOwnPopup.css" rel="stylesheet" type="text/css" media="all"></link>


  
  <!-- start -->

  <!--  end -->
   <script type="text/javascript">
  // getUpcomingBirths();
   
  registerEvent();
  // var refreshId = setInterval("test()", 10000);
   var path='<%=PropertyReader.getValue("photoAlbumPath",request)%>';
  // getTickers(path);
   </script>
    <script type="text/javascript">
    var empId='<%= emp.getId() %>';
    var imagePPPPath = '<%=emp.getImagePath()%>';
    var paaaaath = '<%=PropertyReader.getValue("photoAlbumPath",request)%>';
	 $("#feedProgressBar").css({'display':'block'});
	 $("#moresection").css({'display':'none'});
    $.getJSON("GetPost", {'key' :'','flow':'1'}, function(datas) { // Do an AJAX call
    	

    	
    	$.each(datas, function(k,item){
    		console.log("here i am" + item);
    		//try{
    		addMyPostDynamically(item,1);
    	/* 	}catch(e){
    			console.log(" errror " +e);
    		}
 */			
 			 });
    	$("#feedProgressBar").css({'display':'none'});
    	$("#moresection").css({'display':'block'});
	  });
    
    $.getJSON("GetNotificationData",{},function(data){
    	var likeCount=data.likeCount;
    	var commentCount=data.commCount;
    	var followCount =  data.followCount;
    	var messageCount = data.messageCount;
    	addLikeNofiCount(likeCount);
    	addCommentNotiCount(commentCount);
    	addFollowCount(followCount);
    	addMessageCount(messageCount);
    	//followCount
    });
    $.getJSON("GetMoreData",{},function(data){
    	var chats= data.chat;
    	var birth=data.emp;
    	var photo=data.photo;
    	var tickers=data.ticker;
    	$.each(chats, function( i,item){
    		addPublicChats(item);
    	});
    	
    	$.each(birth, function( i,item2){
    		addMyDynamicBirthEvents(item2);
    	});
    	$.each(tickers, function( i,item3){
    		 console.log(" --------- " + item3.dateTime);
    		 count=1;
    		addMyTickerDynamically(item3);
    	});
    	
    	
    	
    });
   /*  setInterval(function()
    		{ 
    	var tickerDateVar = document.getElementById("tickerDate").value;
    	console.log(" tickerDateVar " + tickerDateVar);
    	 $.getJSON("GetTickersLatest?datee="+tickerDateVar,{},function(data){
    		 $.each(data, function( i,item5){
	    		  if(item5!=null)
	    		  
	    	  		addMyTickerDynamically(item5);
	    	  });
    	 });
    		}, 10000); */
    		    
    </script>
    
      <script type="text/javascript">

   
      </script>
        <title>Inter Communication </title>
		



 
   </head>

<body marginheight="0" marginwidth="0">
 <script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="js/jquery.slimscroll.js"></script>
	<div align="center" id="header" >
	  <div style="background-color: #225566;height: 60px;width: 100%;">
	  		<div style="height: 10px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span  class="NewHeaderStyle">Inter Communication</span>
	  		</div>
	  </div>
<!-- 	  <div style="width: 100%" > -->
		
			
		<!-- <div id="showLinks" style="display: none;width:170px;z-index: 58;height:200px;background-color: #E1F0F5;"></div> -->
		<div id="manageLinks" style="display: none;width:170px;z-index: 58;height:200px;background-color: #E1F0F5;"></div>
		<div id="arrowDiv" class="arrowStyle" style="background-color: #9ECEDE;"></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" onclick="coloseAllAlret()">
			<tr>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #3D9AB9;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'"  onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="feeds.jsp" ><span class="homeImage1">Home</span></a>
							</td>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" id="links" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="#" onclick="showLinks()" ><span class="homeImage1">Link</span></a>
							</td>
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="profilefeed.jsp">Profile</a>
							</td>
						
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photoall.jsp" >Photo</a>
							</td>
							
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'"  href="logout.jsp" >Log out</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
		
		<div style="display: table; width: 100%;vertical-align: top;" align="center">
		
			<div onclick="coloseAllAlret()"  style="display: table-cell; width: 15%;vertical-align: top;" align="left" onmouseover="hideUserInfo('sdf')">
			
				<div>
					<table>
						<tr>
							<td width="200px">
								<table width="100%">
									<tr>
										<td style="padding-top: 20px;border-bottom-color: gray;border-bottom-style: ridge;border-bottom-width: thin;">
										<% System.out.println(" dddd "+emp.getImagePath()); %>
											<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
												<img src="images/person.jpg" width="150" height="100" />
												<%}else{%>
												<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%= emp.getImage().getPhotoPath().replaceFirst("a_", "g_")%>"  width="160"/>
												<%} %>
											<%-- <img src="<%=(emp.getImagePath().trim()%>" width="200px" height="200px"> --%>
												<div align="center">
													<span class="empNameDisplayStyle"><%=emp.getEmpName() %></span><br>
													<span class="empFollowersDisplayStyle">Followers <span id="followCount"></span></span>
												</div>
										</td>
									</tr>
									
								</table>
								<div style="padding-top: 5px"></div>
						<table width="100%">
							
							
							<tr> 
								<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="editProfile.jsp" class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/edit_profile.jpg"/>Profile</a>
								</td>
							</tr>
							<tr>
						<td class="headerGap1" style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1' " onmouseout="this.className='headerLinkBlock1'">
							<a href="feeds.jsp" class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/feeds.jpg"/></span>
							Feeds<img src="img/progressbar.gif" style="padding-left: 5px;" width="40px" height="10px" id="feedProgressBar" ></a>
						</td>
					</tr>
						
							<tr> 
								<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="photo.jsp" class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/photos.jpg"/></span>Photo</a>
								</td>
							</tr>
							 
							<tr> 
								<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="messages.jsp" class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/message.jpg"></span>Message &nbsp<span id="messageCount"></span></a>
								</td>
							</tr>
							
							<tr>
								<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
									<a href="colleagueList.jsp"  class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/colleagues.png"/></span>Colleagues</a>
								</td>
							</tr>
						</table>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
			<!--  end of left section -->
			<!--  start of middle section -->
			<div style="display: table-cell; width: 45%;vertical-align: top;" align="left" >
				<!-- scrapping layer begins here... enjoy -->
			 
				<div style="vertical-align: top; width: 100%;padding-left: 5px;" class="borderSectionStyhle" onmouseover="hideUserInfo('sdf')">
					<input type="hidden" id="txtPhotoPath" value="<%=emp.getImagePath()%>">	
					<input type="hidden" id="txtEmpId" value="<%=emp.getId()%>">	
					<input type="hidden" id="txtEmpIdTarget" value="<%= emp.getId()%>">
					<input type="hidden" id="txtEmpName" value=<%=emp.getEmpName() %>">
					<input type="hidden" value="1" id="flow" >
					<input type="hidden" value="<%=emp.getId() %>" id="hiddenCurrentUser" >
					<input type="hidden" id="basePathOfPhoto" value="<%=PropertyReader.getValue("photoAlbumPath",request) %>" >
					<input type="hidden" id="videoPath" value="<%= PropertyReader.getValue("videoPath",request)%>" >
					<input type="hidden" value="<%=PropertyReader.getValue("photoAlbumPath",request)%>" id="Albumpath">
					<input type="hidden" id="txtEmpName" value="<%=emp.getEmpName()%>">				
					<table style="background-color: #E1F0F5;border-color: #E1F0F1;border-style: ridge;border-width: thin;" width="97%" cellpadding="0" cellspacing="0" >
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" >
									<tr>
											<td id="commDiv" class="iconstyle" onmouseover="this.style.background='#3D9AB9';" onmouseout="this.style.background='#E1F0F5';" onclick="showCommentNoticiation('<%=emp.getId() %>')" style="width:60px; padding-top: 2px;padding-bottom: 2px;">
												<div class="arrowStyle"  style="border-style: outset ;border-color: gray;background-color: gray;" id="commentArrow"></div>
												<div class="arrowStyle"  style="border-style: outset ;border-color: gray;background-color: gray;" id="likeArrow"></div>
												<div class="arrowUserStyle"  style="border-style: outset ;border-color: gray;background-color: gray;" id="userArrow"></div>
												<div class="arrowUserStyle"  style="border-style: outset ;border-color: gray;background-color: gray;" id="tickerArrow"></div>
												<span id="myCNotification" class="notificationTextStyle"  width="30px" height="25px">Comments</span><span style="width: 10px;"></span><span class="myCommetnIconStyle" id="commentNotificationIcon"></span>
											</td>
											<td id="likeDiv" onmouseover="this.style.background='#3D9AB9';" onmouseout="this.style.background='#E1F0F5';" onclick="showLikeNoticiation('<%=emp.getId()%>')" class="iconstyle"  style="width:60px; padding-top: 2px;padding-bottom: 2px;">
												<span id="myLNotification" class="notificationTextStyle"  width="30px" height="25px">Likes</span><span class="myCommetnIconStyle" id="likeNotifyIcon"></span>
											
											</td>
											
									</tr>
								</table>
							</td>
							
						</tr>
					</table>
					<div style="height: 5px;"></div>
					<textarea style="width: 400px;" class="comment_empty1" id="txtNewPost" >what's on your mind?</textarea>
					<input type="button" onclick="addPost()" value="Post" class="txtPostButton">
				</div>
				
				
				<div id="insertPost" onclick="coloseAllAlret()" class="borderSectionStyhle"  style="width: 80%;vertical-align: top;" onmouseover="hideUserInfo('sdf')">
					
				</div>
				<div class="borderSectionStyhle1" style="display: none;" id="moresection"><input type="hidden" name="lastTime" id="lastTime"><table><tr><td><img src="img/progressbar.gif" id="progressMore" width="50px" height="20px" class="hideMeFirst"></td><td><span onclick="showmorepost()" class="moreStylePostMore">more post</span></td></tr></table></div>
				

			</div>
			<!--  end of middle section -->
			<!--  start of last section -->
			<div id="userInfo1" style="display: table-cell; width: 50%;vertical-align: top;"  align="left" >
			
			<div id="userInfo" class="tickerPopup" onclick="coloseAllAlret()"  ></div>
				<div style="display: table;width:100%;" onclick="coloseAllAlret()" >
					<div style="display: table-row;width:100%;">
						<div style="display: table-cell;width:49%;">
							<div class="BirthdDayHeading" >News</div>
							<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') " id="newDiv">
							
								<div id="feedControl" class="feedControlCSS">Loading...</div>
								

								
							
							</div>
						</div>
						<div style="display: table-cell;width:2%;">
							
						</div>
						<div style="display: table-cell;width:49%;">
							<input type="hidden" name="tickerDate" id="tickerDate" >
							<div class="BirthdDayHeading" >Tickers</div>
							<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
							<div id="tickerDiv"  align="left"  style="width:100%;height: 200px;overflow: auto;color: #334422;vertical-align: top;">
								<div id="insertTickers" onmouseover="hideUserInfo('sdf')" align="left" style="border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;overflow: auto;">
									
								</div>
								<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
								
							</div>
						</div>
					</div>
					<div style="display: table-row;width:100%;">
						<div style="display: table-cell;width:49%;">
							<div class="BirthdDayHeading" onmouseover="hideUserInfo('sdf')">Huddle</div>
							<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
								<input type="text" name="chatId" id="chatId" class="chatTextBox" >
								<div id="bullentinDiv"  align= "left"  class= "lastSectionBullentin" onmouseover= "hideUserInfo('sdf') ">
									
									<div id= "bulletinColumn"  align= "left" class= "displayBulletien" onmouseover= "hideUserInfo('sdf') ">
									
									</div>
								</div>
								<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
						</div>
						<div style="display: table-cell;width:2%;">
							
						</div>
						<div style="display: table-cell;width:49%;">
							<div class="BirthdDayHeading" onmouseover="hideUserInfo('sdf')">Upcomming Birthdays</div>
							<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
								<div id="birdthDayDiv" style="height: 100px;" onmouseover="hideUserInfo('sdf')">
					
								</div>
								<div style= "height: 3px; " class= "borderLineTOSeparate " onmouseover= "hideUserInfo('sdf') "></div>
						</div>
					</div>
				</div>
				
				
			
				
				
			</div>
			<!--  end of last section -->
			
		</div>
	
	<!-- popup for images -->
	    <div id="popupContact" style="width: 50%;height: 50%;" align="center">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	
	<div id="backgroundPopup" style=" height: 100%;"></div> 
          <div id="userInfo" class="rightarrowdiv"></div>
		<div id="myPostPopUp" class="leftarrowdiv" style="width:500px;"></div>
		<div id="userInfoPopup" class="leftarrowdiv" style="width:300px;"></div>
		<div id="LikeInfoNotification" class="uparrowdiv">
    	<div width="100%" class="myPopupStyleInNotification"><table width="100%"><tr><td align="left"> <span class="NoficationHeading" align="left">Like Notification </span></td><td align="right"><span id="myCLoseButton" class="likeColose" onclick="closeNotification()"><img src="icon/close.png" class="deleteIconStyhle"/></span></td></tr></table></div>
    	<div id="addDynamicNotify"></div><div class="showMoreNotificaiton" id="showmoreOption"></div> 
    </div>
    
		<div id="CommentInfoNotification" class="uparrowdiv">
	<div width="100%" class="myPopupStyleInNotification"><table width="100%"><tr><td align="left"> <span class="NoficationHeading" align="left">Comment Notification </span></td><td align="right"><span id="myCLoseButtonComment" class="likeColose"  onclick="closeCNotification()"><img src="icon/close.png" class="deleteIconStyhle"/></span></td></tr></table></div>
	<div id="addDynamicCommentNotify"></div> <div class="showMoreNotificaiton" id="showmoreOptionComment"></div> 
</div>

 </body>
  <script>
 	
  </script>
 
</html> 