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
	
	HashMap<String,Boolean> roleMap=new HashMap<String,Boolean>();
		if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
	
		}
		else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
		}
%>
<html>
<head>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/TickersUtil.js"></script>
<script type="text/javascript" src="js/SrapesUtil.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
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
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css"
	media="all"></link>

<link href="css/myOwnPopup.css" rel="stylesheet" type="text/css"
	media="all"></link>



<!-- start -->

<!--  end -->
<script type="text/javascript">
  // getUpcomingBirths();
   
  
  // var refreshId = setInterval("test()", 10000);
   var path='<%=PropertyReader.getValue("photoAlbumPath",request)%>';
  // getTickers(path);
   </script>
<script type="text/javascript">
    var empId='<%=emp.getId()%>';
    var imagePPPPath = '<%=emp.getImagePath()%>';
    var paaaaath = '<%=PropertyReader.getValue("photoAlbumPath",request)%>';

	$.getJSON("GetPost", {
		'key' : empId
	}, function(datas) { // Do an AJAX call

		$.each(datas, function(k, item) {
			addMyPostDynamically(item);

		});

	});
	$.getJSON("GetUserBasicInfo", {}, function(data) {
		var info = data.info;
		//	var birth=data.emp;
		// 	var photo=data.photo;
		addDynamicBasicInfo(info);
		//$.each(info, function( i,item){
		///	addDynamicBasicInfo(item);
		//});

		//	$.each(birth, function( i,item2){
		//		addMyDynamicBirthEvents(item2);
		//	});

	});
	registerEvent();
</script>

<script type="text/javascript">
	
</script>
<title>Inter Communication</title>





</head>

<body marginheight="0" marginwidth="0">
	<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
	<div class="navbar navbar-inverse navbar-static-top"
		style="margin: 0; background-color: #000000;">
		<div class="container" style="margin-top: -35px; margin-bottom: 0px;">
			<h3>
				<a href="#" class="navbar-brand"
					style="font-size: 30px; margin-top: 5px;"><span
					style="color: white;"><span class="rednum"
						style="font-family: calibri; font-weight: bold; font-size: 30px;">e&nbsp;
					</span>Huddle</span></a>
			</h3>
		</div>
		<div class="container" style="margin: 0;">

			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>
			<div class="collapse navbar-collapse navHeaderCollapse">

				<ul class="nav navbar-nav navbar-right">
					<li><a href="feeds.jsp"><i class="fa fa-home fa-2x"></i>Home</a></li>
					<li class="active"><a href="profilefeed.jsp"><i
							class="fa fa-user fa-2x"></i> Profile</a></li>
					
					<li><a href="photoall.jsp"><i
							class="fa fa-picture-o fa-2x"></i>Photo</a></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-2x"></i>Logout</a></li>
				</ul>
				

				
			</div>
		</div>
	</div>


	<!-- <div id="basicInfo" class="borderSectionStyhle" style="width: 80%;vertical-align: top;"></div> -->
	<div class="container" style="margin-top: 0px;">
		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-12">
				<div style=" vertical-align: top;"
					align="center" class="newBackgroundColor">
					<div id="addAllInfo" style="width: 100%;"></div>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-2">
				<div onclick="coloseAllAlret()"
					style="display: table-cell; width: 15%; vertical-align: top;"
					align="left" onmouseover="hideUserInfo('sdf')">

					<div>
						<table>
							<tr>
								<td width="200px">

									<ul class="nav nav-pills nav-stacked" style="margin-top: 5px;">
										<li><a href="editProfile.jsp"><i
												class="fa fa-user fa-lg"></i>Profile</a></li>
										<li><a href="feeds.jsp"><i
												class="fa fa-envelope-square fa-lg"></i> Feeds<!-- <img
												src="img/progressbar.gif" style="padding-left: 5px;"
												width="40px" height="10px" id="feedProgressBar"> --></a></li>
										<li><a href="photo.jsp"><i
												class="fa fa-picture-o fa-lg"></i> Photo</a></li>
										<li><a href="messages.jsp"><i
												class="fa fa-envelope-square fa-lg"></i> Message</a></li>
										<li><a href="colleagueList.jsp"><i
												class="fa fa-users fa-lg"></i> Colleagues</a></li>
									</ul>


								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
			<!--  middle column -->
			<div class="col-lg-7" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body"
						style="margin-top: 0px; margin-left: -15px;">
						<div style="vertical-align: top; width: 100%; padding-left: 5px;"
							class="borderSectionStyhle" onmouseover="hideUserInfo('sdf')">
							<input type="hidden" id="txtPhotoPath"
								value="<%=emp.getImagePath()%>"> <input type="hidden"
								id="txtEmpId" value="<%=emp.getId()%>"> <input
								type="hidden" id="txtEmpIdTarget" value="<%=emp.getId()%>">
							<input type="hidden" id="txtEmpName" value=<%=emp.getEmpName()%>">
							<input type="hidden" value="0" id="flow"> <input
								type="hidden" value="<%=emp.getId()%>" id="hiddenCurrentUser">
							<input type="hidden" id="basePathOfPhoto"
								value="<%=PropertyReader.getValue("photoAlbumPath", request)%>">
							<input type="hidden" id="videoPath"
								value="<%=PropertyReader.getValue("videoPath", request)%>">
							<input type="hidden"
								value="<%=PropertyReader.getValue("photoAlbumPath", request)%>"
								id="Albumpath"> <input type="hidden" id="txtEmpName"
								value="<%=emp.getEmpName()%>">
							<textarea style="width: 400px;" class="comment_empty1"
								id="txtNewPost">what's on your mind?</textarea>
							<input type="button" onclick="addPost()" value="Post"
								class="txtPostButton">
						</div>

						<div id="insertPost" class="borderSectionStyhle"
							style="width: 80%; vertical-align: top;"
							onmouseover="hideUserInfo('sdf')"></div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!--  end here -->

	<div style="display: table; width: 80%; vertical-align: top;"
		align="center">

		<div style="display: table-cell; width: 15%; vertical-align: top;"
			align="left" onmouseover="hideUserInfo('sdf')"></div>



		<!--  end of left section -->
		<!--  start of middle section -->
		<div style="display: table-cell; width: 45%; vertical-align: top;"
			align="left">
			<br>


		</div>
		<div id="userInfo1"
			style="display: table-cell; width: 50%; vertical-align: top;"
			align="left">






		</div>
		<!--  end of last section -->

	</div>

	<!-- popup for images -->
	<div id="popupContact" style="width: 50%; height: 50%;" align="center">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" style="margin: 0px;" width="100%" scrolling="no"
			height="100%" id="myIFrame" frameborder="0"></iframe>
	</div>
	<div id="backgroundPopup" style="height: 100%;"></div>
	<script type="text/javascript" src="jjs/bootstrap.js"></script>
</body>

</html>
