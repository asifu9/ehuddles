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
<!DOCTYPE html>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="jjs/jquery.min.js"></script>
<script type="text/javascript" src="jjs/jquery-ui.js"></script>


<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="js/SrapesUtil.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/TickersUtil.js"></script>
<link rel="stylesheet" href="jjs/managedivision.css">
<script type="text/javascript" src="jjs/division.js"></script>
<script type="text/javascript" src="jjs/bootstrap.js"></script>
		<script type="text/javascript" src="jjs/bootstrap-modal-popover.js"></script>
		<link rel="stylesheet" href="css/bootstrap.css"/>

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
//google.load("feeds", "1"); //Load Google Ajax Feed API (version 1)
</script>
<style type="text/css">
.tickerSection {
	display: block;
	border-bottom-color: #CFCFCF;
	border-bottom-style: inset;
	border-bottom-width: 1px;
	vertical-align: top;
	padding-top: 5px;
	padding-bottom: 5px;
	cursor: pointer;
	cursor: hand;
}
</style>

<!--    <script type="text/javascript" src="js/jquery4.1.min.js"></script> -->

<link href="css/PostCss.css" rel="stylesheet" type="text/css"
	media="all"></link>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all"></link>
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css"
	media="all"></link>

<link href="css/myOwnPopup.css" rel="stylesheet" type="text/css"
	media="all"></link>



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
    var empId='<%=emp.getId()%>';
    var imagePPPPath = '<%=emp.getImagePath()%>';
    var paaaaath = '<%=PropertyReader.getValue("photoAlbumPath",request)%>';

	$("#feedProgressBar").css({
		'display' : 'block'
	});
	$("#moresection").css({
		'display' : 'none'
	});
	$.getJSON("GetPost", {
		'key' : '',
		'flow' : '1'
	}, function(datas) { // Do an AJAX call

		$.each(datas, function(k, item) {
			console.log("here i am" + item);
			//try{
			addMyPostDynamically(item, 1);
			/* 	}catch(e){
					console.log(" errror " +e);
				}
			 */
			console.log(" k " + k + "actual is " + datas.length);
			if (k == parseInt(datas.length) - 1) {
				console.log(" going to hide ");
				$("#feedProgressBar").css({
					'display' : 'none'
				});
			}
		});

		$("#moresection").css({
			'display' : 'block'
		});
	});

	$.getJSON("GetNotificationData", {}, function(data) {
		$("#commentNotificationIcon").css("display","none");
		var likeCount = data.likeCount;
		var commentCount = data.commCount;
		var followCount = data.followCount;
		var messageCount = data.messageCount;
		addLikeNofiCount(likeCount);
		addCommentNotiCount(commentCount);
		addFollowCount(followCount);
		addMessageCount(messageCount);
		//followCount
	});
	$.getJSON("GetMoreData", {}, function(data) {
		var chats = data.chat;
		var birth = data.emp;
		var photo = data.photo;
		var tickers = data.ticker;
		$.each(chats, function(i, item) {
			addPublicChats(item);
		});

		$.each(birth, function(i, item2) {
			addMyDynamicBirthEvents(item2);
		});
		$.each(tickers, function(i, item3) {
			console.log(" --------- " + item3.dateTime);
			count = 1;
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
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<title>Employee Huddle</title>





</head>

<body marginheight="0" marginwidth="0">
	<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="js/jquery.slimscroll.js"></script>

	

	<div class="navbar navbar-inverse navbar-static-top"
		style="margin:0;background-color:#000000;">
		<div class="container" style="margin-top: -35px; margin-bottom: 0px;">
			<h3>
				<a href="#" class="navbar-brand" style="font-size: 30px;margin-top: 5px;"><span style="color:white;"><span class="rednum" style="font-family: calibri;font-weight:bold ;font-size:30px; ">e&nbsp; </span>Huddle</span></a>
			</h3>
		</div>
		<div class="container" style="margin:0;">
			
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>
			<div class="collapse navbar-collapse navHeaderCollapse">

				<ul class="nav navbar-nav navbar-right">
					<li id="myLNotification"
												onclick="showLikeNoticiation('<%=emp.getId()%>')">
											<div
													id="likeTarget"></div>
												<a href="#" id="likeDiv"
													onclick="$('#Likedialog').modalPopover('show');">
													
													<i class="fa fa-thumbs-up fa-2x" id="likeNotifyIcon"></i><!-- <span id="likeNotifyIcon" class='rednum'></span> -->
												</a>
												<!--  -->
											<div id="Likedialog" class="popover">
													<div class="arrow"></div>
													<h3 class="popover-title">Like Notificastions</h3>
													<div class="popover-content">
														<div id="addDynamicNotify"></div>
														<div class="showMoreNotificaiton" id="showmoreOption"></div>
													</div>
												</div>		
												
					</li>
					
					<!-- class="hoverOnStyle" -->
					<li id="myCNotification"  
												onclick="showCommentNoticiation('<%=emp.getId()%>')"  style="padding-right: 10px;"> 
												<div
													id="commTarget"></div>
												<a href="#"  id="commDiv" 
													onclick="$('#commentdailog').modalPopover('show');">
													<i class="fa fa-comments fa-2x" id="commentNotificationIcon"></i><!-- <span id="commentNotificationIcon" class='rednum'></span> -->
												</a>
												
													<!--  like dailog  -->
												<div id="commentdailog" class="popover">
					<div class="arrow"></div>
					<h3 class="popover-title">Comment Notifications</h3>
					<div class="popover-content" style="margin-top: 0px;">
						<div id="addDynamicCommentNotify" style="padding-top: 0px;"></div>
						<div class="showMoreNotificaiton"
							id="showmoreOptionComment"></div>
					</div>
				</div>
				
					</li>
					
					<li class="active"><a href="feeds.jsp"><i class="fa fa-home fa-2x"></i>Home</a></li>
					<li><a href="profilefeed.jsp"><i class="fa fa-user fa-2x"></i> Profile</a></li>
					<!-- <li onclick="showLinks()"><a id="links" onclick="$('#manageLinks').modalPopover('show')"><i class="fa fa-home fa-2x"></i>link</a>
					</li> -->
					<li><a href="photoall.jsp"><i class="fa fa-picture-o fa-2x"></i>Photo</a></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-2x"></i>Logout</a></li>
				</ul>
		
				<!--  comment dailog -->
				
				<script>
				$('#Likedialog').modalPopover({
					target : '#myLNotification',
					placement : 'bottom'
				});
				$('#commentdailog')
				.modalPopover(
				{
					target : '#commTarget',
					placement : 'bottom'
				});
				</script>
				
				<div id="manageLinks" class="popover" style="width:1200px;height:500px;">
							<div class="arrow"></div>
							<h3 class="popover-title">hi</h3>
							<table style="width: 1000px;">
							<tr>
							<td>
								Manage Links
							</td>
							<td align="right">
								<span id="addShow" >Create</span>
							</td>
							</tr>
							</table>
							
							<div class="popover-content">
								
							</div>
						</div>
							
				<script>
				$('#manageLinks')
				.modalPopover(
						{
							target : '#links',
							placement : 'bottom'
						});
				$('#addShow').click(function(){
					console.log(" show manage linkjs");
					$('#manageDivLinks').toggle( "slow" );
				});
				</script>
			</div>
		</div>
	</div>

	<!--  main contain for bootstrap -->

	<div class="container" style="margin-top: 0px;">
		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-2">
				<div onclick="coloseAllAlret()"
					style="display: table-cell; width: 15%; vertical-align: top;"
					align="left" onmouseover="hideUserInfo('sdf')">

					<div>
						<table>
							<tr>
								<td width="200px">
									<table width="100%">
										<tr>
											<td
												style="padding-top: 20px; border-bottom-color: gray; border-bottom-style: ridge; border-bottom-width: thin;">
												<%
													System.out.println(" dddd " + emp.getImagePath());
												%> <%
 	if (emp.getImagePath() == null
  											    			|| emp.getImagePath().trim().equalsIgnoreCase("")) {
 %> <img src="images/person.jpg" width="150" height="100" /> <%
 	} else {
 %> <img
												src="<%=PropertyReader.getValue("photoAlbumPath", request)%><%=emp.getImage().getPhotoPath()
																		.replaceFirst("a_", "g_")%>"
												width="160" /> <%
 	}
 %>

												<div align="center">
													<span class="empNameDisplayStyle"><%=emp.getEmpName()%></span><br>
													<span class="empFollowersDisplayStyle">Followers <span
														id="followCount"></span></span>
												</div>
											</td>
										</tr>

									</table>
									<div style="padding-top: 5px"></div>

									<ul class="nav nav-pills nav-stacked" style="margin-top: 5px;">
										<li><a href="editProfile.jsp"><i class="fa fa-user fa-lg"></i>Profile</a></li>
										<li class="active"><a href="feeds.jsp"><i class="fa fa-envelope-square fa-lg"></i>  Feeds<img
												src="img/progressbar.gif" style="padding-left: 5px;"
												width="40px" height="10px" id="feedProgressBar"></a></li>
										<li><a href="photo.jsp"><i class="fa fa-picture-o fa-lg"></i>  Photo</a></li>
										<li><a href="messages.jsp"><i class="fa fa-envelope-square fa-lg"></i>  Message</a></li>
										<li><a href="colleagueList.jsp"><i class="fa fa-users fa-lg"></i> Colleagues</a></li>
									</ul>


								</td>
							</tr>
						</table>
						
						
					</div>

				</div>
			</div>


			<div class="col-lg-7" style="margin-top: 0px;margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body" style="margin-top: 0px;margin-left: -15px;">
						<!--  feeds column -->
						
						<div class="arrowUserStyle"
							style="border-style: outset; border-color: gray; background-color: gray;"
							id="userArrow"></div>
					
						<!-- <span id="myCNotification"
											class="notificationTextStyle" width="30px" height="25px">Comments</span> -->



						<span style="width: 10px;"></span>

						<!-- <div
							style="vertical-align: top; width: 100%; padding-left: 0px; margin-left: -15px;"
							class="borderSectionStyhle" onmouseover="hideUserInfo('sdf')"> -->
							<input type="hidden" id="txtPhotoPath"
								value="<%=emp.getImagePath()%>"> <input type="hidden"
								id="txtEmpId" value="<%=emp.getId()%>"> <input
								type="hidden" id="txtEmpIdTarget" value="<%=emp.getId()%>">
							<input type="hidden" id="txtEmpName" value=<%=emp.getEmpName()%>">
							<input type="hidden" value="1" id="flow"> <input
								type="hidden" value="<%=emp.getId()%>" id="hiddenCurrentUser">
							<input type="hidden" id="basePathOfPhoto"
								value="<%=PropertyReader.getValue("photoAlbumPath", request)%>">
							<input type="hidden" id="videoPath"
								value="<%=PropertyReader.getValue("videoPath", request)%>">
							<input type="hidden"
								value="<%=PropertyReader.getValue("photoAlbumPath", request)%>"
								id="Albumpath"> <input type="hidden" id="txtEmpName"
								value="<%=emp.getEmpName()%>">

							<div style="padding-left: 0px; padding-bottom: 0px;">

								<div class="panel panel-default" style="margin-top: -5px;margin-left: 10px;">
									<div class="panel-body" style="margin-top: 0px;">

										
										<table>
											<tr>
												<td><textarea style="width: 400px;"
														class="comment_empty1" id="txtNewPost">what's on your mind?</textarea></td>
												<td style="padding-left: 5px;"><input type="button"
													onclick="addPost()" value="Post" class="btn btn-primary"
													style="margin-top: 0px; margin-bottom: 0px; height: 30px;">
												</td>
											</tr>
										</table>
									</div>
								</div>

							</div>
							<div id="insertPost" onclick="coloseAllAlret()"
								class="borderSectionStyhle"
								style="width: 70%; vertical-align: top;"
								onmouseover="hideUserInfo('sdf')"></div>

							<div class="borderSectionStyhle1" style="display: none;"
								id="moresection">
								<input type="hidden" name="lastTime" id="lastTime">
								<table>
									<tr>
										<td><img src="img/progressbar.gif" id="progressMore"
											width="50px" height="20px" class="hideMeFirst"></td>
										<td><span onclick="showmorepost()"
											class="moreStylePostMore">more post</span></td>
									</tr>
								</table>
							</div>
						

					</div>
				</div>
			</div>

			<div class="col-lg-3">

				<div class="row">
					<div class="col-lg-12">
						<!--  third column -->
						<div class="row">

							<div class="col-lg-12">
								<div class="page" style="margin-left: 0px;">
									<input type="hidden" name="tickerDate" id="tickerDate">
									<div class="BirthdDayHeading" style="padding-bottom: 25px;">Tickers</div>
									<div id="insertTickers" onmouseover="hideUserInfo('ticker')"
										align="left"
										style="border-right-color: #EEEEEE; border-right-style: inset; border-right-width: 1px; overflow: auto;">

										<div id="tickerDiv" align="left"
											style="width: 100%; height: 300px; overflow: auto; color: #334422; vertical-align: top;">

										</div>

									</div>
									<div id="tickerdailog" class="popover" style="z-index: 200;">
										<div class="arrow" style="top: 20px;"></div>

										<div class="popover-content" style="margin-top: 0px;">
											<div id="appendTickerData"></div>
										</div>
									</div>

								</div>
							</div>

						</div>

						<div class="row">
							<div class="col-lg-12">
								<!-- Huddle  -->
								<div class="BirthdDayHeading" onmouseover="hideUserInfo('sdf')"
									style="padding-bottom: 25px;">Huddle</div>
								<!-- <div id="bullentinDiv" align="left" class="lastSectionBullentin"
								onmouseover="hideUserInfo('sdf') "></div> -->
								<!-- <input type="text"  class="chatTextBox"> -->
								<input type="text" class="form-control"
									placeholder="public chat..." name="chatId" id="chatId">
								<div id="bulletinColumn" align="left" class="displayBulletien"
									style="height: 300px;" onmouseover="hideUserInfo('sdf') "></div>
							</div>
						</div>
						<script>
							/* $('#tickerdailog').modalPopover({
								target : '#tarrgetTicker',
								placement : 'left'
							}); */
						</script>


						<div class="row">
							<div class="col-lg-12" style="padding-top: 10px;">
								<div class="BirthdDayHeading" style="padding-bottom: 25px;">News</div>
								<div id="feedControl"
									style="height: 220px; padding-bottom: 25px;"
									class="feedControlCSS">Loading...</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12" style="padding-top: 10px;">
								<div class="BirthdDayHeading" onmouseover="hideUserInfo('sdf')"
									style="padding-bottom: 25px;">Upcomming Birthdays</div>

								<div id="birdthDayDiv" onmouseover="hideUserInfo('sdf')"
									style="height: 300px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <div id="showLinks" style="display: none;width:170px;z-index: 58;height:200px;background-color: #E1F0F5;"></div> -->
<!-- 	<div id="manageLinks"
		style="display: none; width: 170px; z-index: 58; height: 200px; background-color: #E1F0F5;"></div>
	<div id="arrowDiv" class="arrowStyle"
		style="background-color: #9ECEDE;"></div> -->
		
	<!--  here is the code -->


	<div style="display: table; width: 100%; vertical-align: top;"
		align="center">

		<!--  end of left section -->
		<!--  start of middle section -->
		<div style="display: table-cell; width: 45%; vertical-align: top;"
			align="left">
			<!-- scrapping layer begins here... enjoy -->
		</div>
		<!--  end of middle section -->
		<!--  start of last section -->
		<div id="userInfo1"
			style="display: table-cell; width: 50%; vertical-align: top;"
			align="left">

			<div id="userInfo" class="tickerPopup" onclick="coloseAllAlret()"></div>

		</div>
	</div>
	<!-- popup for images -->
	<div id="popupContact" style="width: 50%; height: 50%;" align="center">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" style="margin: 0px;" width="100%" scrolling="no"
			height="100%" id="myIFrame" frameborder="0"></iframe>
	</div>

	<div id="backgroundPopup" style="height: 100%;"></div>
	<div id="userInfo" class="rightarrowdiv"></div>
	<div id="myPostPopUp" class="leftarrowdiv" style="width: 500px;"></div>
	<div id="userInfoPopup" class="leftarrowdiv" style="width: 300px;"></div>


	<div class="navbar navbar-default navbar-static-bottom">
		<div class="container">
			<a href="#" class="navbar-text">Thanks for visiting site</a>
		</div>
	</div>
		
</body>

</html>
