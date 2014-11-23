<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.domain.Attendance"%>
<%@page import="com.pro.emp.domain.EmpIdproofInfo"%>
<%@page import="com.pro.emp.domain.EmpActivityInfo"%>
<%@page import="com.pro.emp.domain.EmpAdditionalInfo"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.Role"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List" %>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de" id="facebook" class=" no_js"> 
<html>
<%//System.out.println(" Here i am calling 1 " + request.getRequestURI());
//System.out.println(" Here i am calling 2 " + request.getRequestURL());%>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<!--  send info style -->
<title>Colleagues</title>
<style>


.table1
{
width:200%;
height:110px;
}
.logo
{

}
.headerLink {
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			display: block;
			color:#696969;
			font-weight: bold;
	}
	
.headerLinkMO{
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			color:white;
			font-size: 16px;
			font-style: normal;
			font-weight: bold;
			display: block;
			

	}
.headerLinkMOBlock{
			background-color: #AEC9E2;
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:white;
}
.headerLinkBlock{
			 background-color: white; 
			 padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:balck;
}
.link {
	text-decoration: none;
	font-family: Tahoma;
	font-weight: bold;
	font-size: 16px;

}
.headerGap {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			
	}
</style>
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em,
font, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody,
 tfoot, thead, tr, th, td {
border:0pt none;
font-family:inherit;
font-size:100%;
font-style:inherit;
font-weight:inherit;
margin:0pt;
padding:0pt;
vertical-align:baseline;
}
body{
background:#fff none repeat scroll 0%;
line-height:1;
font-size: 12px;
font-family:arial,sans-serif;
margin:0pt;
height:100%;
}
table {
border-collapse:separate;
border-spacing:0pt;
}
caption, th, td {
font-weight:normal;
text-align:left;
}
blockquote:before, blockquote:after, q:before, q:after {
content:"";
}
blockquote, q {
quotes:"" "";
}
a{
cursor: pointer;
text-decoration:none;
}
br.both{
clear:both;
}
#backgroundPopup{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:100%;
width:100%;
top:0;
left:0;
background:#000000;
border:1px solid #cecece;
z-index:1;
}
#popupContact{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:500px;
width:420px;
background:#FFFFFF;
border:0px solid #cecece;
z-index:2;
padding:0px;
font-size:13px;
}
#popupContact h1{
text-align:left;
color:#6FA5FD;
font-size:22px;
font-weight:700;
border-bottom:1px dotted #D3D3D3;
padding-bottom:2px;
margin-bottom:20px;
}
#popupContactClose{
font-size:16px;
line-height:14px;
right:6px;
top:4px;
position:absolute;
color:#FFFFFF;
font-weight:700;
display:block;
}
#button{
text-align:center;
margin:100px;
}


/* ---------------------- */
.headerLink1 {
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			display: block;
			color:#696969;
			font-weight: bold;
	}
	
.headerLinkMO1{
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			color:white;
			font-size: 16px;
			font-style: normal;
			font-weight: bold;
			display: block;
			

	}
.headerLinkMOBlock1{
			background-color: #AEC9E2;
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:white;
}
.headerLinkBlock1{
			 background-color: white; 
			 padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:balck;
}
.link1 {
	text-decoration: none;
	font-family: Tahoma;
	font-weight: bold;
	font-size: 16px;

}
.headerGap1 {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			
	}
	
	.infoHeader{
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			border-top-color: #EEEEEE;
		border-top-style: outset;
		border-top-width: 1px;
		padding-top: 5px;
		padding-bottom: 5px;
	border-right-width: 1px;
			border-right-color: #EEEEEE;
		border-right-style: inset;
		font-weight: bold;
		font-size: 15px;
		font-family: verdane;
		padding-left: 4px;
		
	}
	.infoHeaderh{
	
		padding-left:2px;
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			border-top-color: #EEEEEE;
		border-top-style: outset;
		border-top-width: 1px;
		background-color: #EEEEEE;
		
		padding-top: 5px;
		padding-bottom: 5px;
		
		border-left-color: #EEEEEE;
		border-left-style: outset;
		border-left-width: 1px;
	
	}
	.infoMiddleh{
	border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
		background-color:#EEEEEE;
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.infoMiddle{
	border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.infoFooter{
		border-bottom-color: #EEEEEE;
		border-bottom-style: inset;
		border-bottom-width: 1px;
			
		padding-top: 5px;
		padding-bottom: 5px;
	}
</style>
<script type="text/javascript" src="js/search.js"></script>
<link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javasript" src="js/SrapesUtil.js"></script>
<script type="text/javascript" src="js/jquery4.1.min.js"></script>

<script type="text/javascript">
function arrived(data){
	//alert(' flushing data '+ data);
	$('#suggestions').html(data);
	}
	
function callme(id){
	 $.getJSON("SetOtherId", {'id' :id}, function(datas) { // Do an AJAX call
		 window.location.href="userfeed.jsp";
	 });
	 
}

</script>
<script  type="text/javascript">

google.load("jquery", "1.3.1");

google.setOnLoadCallback(function()

{

	// Safely inject CSS3 and give the search results a shadow

	var cssObj = { 'box-shadow' : '#888 5px 10px 10px', // Added when CSS3 is standard

		'-webkit-box-shadow' : '#888 5px 10px 10px', // Safari

		'-moz-box-shadow' : '#888 5px 10px 10px'}; // Firefox 3.5+

	$("#suggestions").css(cssObj);
	
	// Fade out the suggestions box when not active
	 $("input").blur(function(){
	 	$('#suggestions').fadeOut();
	 	
	 });
	// Fade out the suggestions box when not active
	 $("select").blur(function(){
	 	$('#suggestions').fadeOut();
	 });
	 //$("p").height()

});

function lookup(inputString,searchType) {

	if(inputString.length == 0) {
		$('#suggestions').fadeOut(); // Hide the suggestions box
	} else {
		$("#suggestions").height("auto");
			//alert(" value si " + inputString + " ~ view" + searchType);
		$.post("SearchUsers", {queryString: ""+inputString+"~"+searchType+""}, function(data) { // Do an AJAX call
			//alert("data is " + data);
			$('#suggestions').fadeIn(); // Show the suggestions box
			//alert(" data is  " + $("#searchresults").height());
			$('#suggestions').html(data); // Fill the suggestions box
			
			//.css("display", "inline")
			//alert(" data is before  " + $("#searchresults").height());
			//$("#searchresults").height(10);
			///$("#searchresults").css("overflow", "auto");
			//$("#suggestions").css('display','inline');
			//var divTag=document.myForm.getElementById("suggestions");
			//divTag.style.height='10px';
			//divTag.style.display='inline';
			//divTag.style.overflow='auto';
			//alert(" data is after  " + $("#searchresults").height());
			//alert(" height " +  $("#suggestions").height() + " width is " + $("#suggestions").width());
		});
	}
}



</script>


<script>

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;

//loading popup with jQuery magic!
function loadPopup(){
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		document.getElementById("myIFrame").contentDocument.location.reload(true);
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}

//disabling popup with jQuery magic!
function disablePopup(){
	//disables popup only if it is enabled
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
	}
}

//centering popup
function centerPopup(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height()+20;
	var popupWidth = $("#popupContact").width();
	//centering
	$("#popupContact").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup").css({
		"height": windowHeight
	});
	
}

$(document).ready(function(){

	//LOADING POPUP
	//Click the button event!
	$(".SendStyle").click(function(){

	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose").click(function(){
		disablePopup();
	});
	//Click out event!
	$("#backgroundPopup").click(function(){
		//disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			disablePopup();
		}
	});

});

</script>
<!--  end of send info style -->
<script>
function updateHiddenBox (value) {

document.myForm.calenderEditEmpId.value = value;
//alert("value "+value);
  // This AJAX call will save the Navigator's state to session.
  // We don't need a callback function because nothing happens
  // once said state is saved.
  var url = "ManageCalSession?empid="+document.myForm.calenderEditEmpId.value;
  try {
  req = new ActiveXObject("Microsoft.XMLHTTP");
  req.open("POST", url, true);				
  req.send();

  }catch(e){
	 req= new window.XMLHttpRequest();
	 req.open("POST", url, false);				
	  req.send(null);

  }

	//centering with css
	centerPopup();
	//load popup
	loadPopup();

}


function updateRole(value) {

	document.myForm.calenderEditEmpId.value = value;
	 var actualValue= document.getElementById('role_'+value).innerHTML;
	 var reqValue="";
	 if(actualValue=="ADMIN"){
		 return;
	 }
	 if(actualValue=="VIEWONLY"){
		 reqValue="MODIFY";
	 }else{
		 reqValue ="VIEWONLY";
	 }
	 document.getElementById('role_'+value).innerHTML=reqValue;

	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "UpdateRole?empid="+document.myForm.calenderEditEmpId.value+"&roleValue="+reqValue;
	  try {
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	  req.open("POST", url, true);				
	  req.send();

	  }catch(e){
		 req= new window.XMLHttpRequest();
		 req.open("POST", url, false);				
		  req.send(null);

	  }
	

	}
</script>
<script>
function refreshMe(){
//	alert(" hiiii ");
	//window.location.reload();
	var months= document.myForm.cmdMonth.value;
	var years= document.myForm.cmdYear.value;
	var url ="colleagueList.jsp?month="+months+"&year="+years;
	//alert(" rul " + url);
	window.location.href=url;
}
</script>
		<%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}else{
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
		%>
	
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
 <link rel="stylesheet" href="css/SendInfoPopup.css" type="text/css" media="screen" />
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
<script type="text/javascript" src="jjs/bootstrap.js"></script>

<% List<EmpInfo> set = EmployeeInfo.getAllEmployees(); %>
<%// System.out.println("1"); %>
<%// EmpAdditionalInfo addInfo= EmployeeInfo.getEmployeeAdditionalInfo(emp.getEmpid());
//System.out.println("5");
//Set<EmpActivityInfo> empActSet = EmployeeInfo.getEmployeeActivityInfo(emp.getEmpid());
//System.out.println("5");
//Set<EmpIdproofInfo> empIdProofSet = EmployeeInfo.getEmployeeIdProofInfo(emp.getEmpid());
// System.out.println(" addinfo " + addInfo.getAddress()); %>
<% //System.out.println("1"); %>
<% HashMap<String,Boolean> roleMap= EmployeeInfo.getRolesForEmpId(emp.getId()); %>
<% //System.out.println("2 " + roleMap); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
</head>
<body marginheight="0" marginwidth="0">
<%
String month_="";
String year_="";
%>

<!--  code from home page -->
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
					<li ><a href="profilefeed.jsp"><i
							class="fa fa-user fa-2x"></i> Profile</a></li>
					
					<li><a href="photoall.jsp"><i
							class="fa fa-picture-o fa-2x"></i>Photo</a></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-2x"></i>Logout</a></li>
				</ul>
				

				
			</div>
		</div>
	</div>
	
	
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
										<li ><a href="feeds.jsp"><i class="fa fa-envelope-square fa-lg"></i>  Feeds</a></li>
										<li><a href="photo.jsp"><i class="fa fa-picture-o fa-lg"></i>  Photo</a></li>
										<li><a href="messages.jsp"><i class="fa fa-envelope-square fa-lg"></i>  Message</a></li>
										<li class="active"><a href="colleagueList.jsp"><i class="fa fa-users fa-lg"></i> Colleagues</a></li>
									</ul>


								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		
		

<div class="col-lg-10" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body"
						style="margin-top: 0px; margin-left: -0px;">
						<table border="1" width="100%">
					
					
				<% for(EmpInfo info:set){ %>
					<tr valign="top">
						<td valign="top" style="border-top-color: #EEEEEE;border-top-style: ridge;border-top-width: 1px;
												border-left-color: #EEEEEE;border-left-style: ridge;border-left-width: 1px;
												padding-left: 5px; ">
							<table border="1"  width="100%">
								<tr>
									<td valign="top" style="width:15%;padding-top: 5px; padding-bottom: 5px;">
									<%if(info.getImage()!=null){ %>
									<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=info.getImage().getPhotoPath().replaceFirst("a_","f_")%>" width="200px" ></img>
									<%}else{ %>
										<img src="images/person.jpg" width="200px" ></img>
										<%} %>
									</td>
									<td style="vertical-align: top; padding-left: 10px;padding-top: 5px;width: 30%; " align="left">
										<table>
											<tr>
												<td><span style="font-family: Tahoma;font-weight: bold;"><a onclick="callme('<%=info.getId()%>')"><%=info.getEmpName() %></a></span></td>
											</tr>
											 <tr>
												<td style="padding-top: 5px;"><img src="images/department.jpg" width="10px" height="10px" />&nbsp;<%=info.getStrDepartment() %> | <%=info.getStrDesignation() %></td>
											</tr> 
											<tr>
												<td style="padding-top: 5px;"><img src="images/email.jpg" width="10px" height="10px" /> &nbsp;<%=info.getEmailid() %></td>
											</tr>
										</table>
										
									</td>
									<td style="background-color: white; vertical-align: top;padding-bottom: 15px;width: 55%;text-align: right;" align="right">
									
									
									
									
									</td>
								</tr>
							</table>
						</td>
					</tr>
				<%} %>
				</table>
					</div>
				</div>
			</div>
		</div>
	
					

<!--  -->
<form name="myForm" action="" method="post">
	<% if(emp!=null && (roleMap.get(Constant.DEP_ADMIN)==null?false:true)) { %>
	 <div id="search" align="left">
		<div >
			<input type="hidden" name="flag" value="1"/>
			<input type="hidden" name="calenderEditEmpId" value=""/>
			<input type="text" class="searchtextBox" size="30" value="" id="inputString" onclick="lookup(this.value,myForm.searchType.value);" onkeyup="lookup(this.value,myForm.searchType.value);" />
			
			<select class="searchComboBox" name="searchType" id="searchType" onchange="lookup(inputString.value,this.value)"> 
		    	<!-- <option class="searchcomboBoxItems" id="view" selected="selected">View</option> -->
		    	<%-- <% if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { %>
					<option class="searchcomboBoxItems" id="profile">Profile</option>
				<%}%> --%>
		    </select>
		</div>
		<div id="suggestions"></div>
		    
	</div>
	 <%} %>
	<div style="height: 4px;"></div>
	<div style="width: 100%;">
	
	
	
			
		
			
				
				
				
				
			</li>
		
		</ul>
		<%-- <ul style="display:table;	width:100%;padding-top: 0px;" >
			
			<li style="display: table-cell ;width: 170px;border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;"></li>
			<li style="display:table-cell;vertical-align: top;padding-left: 0px; ">
			
			
				<table border="1" style="padding-top: 10px;">
				<% for(EmpInfo info: set){ %>
				<tr>
					<td class="listConntents">
						<a href="userInfo.jsp?id=<%=info.getEmpid()%>">
							<% if(info.getImagePath()==null || info.getImagePath().trim().equalsIgnoreCase("")) {%>
							<img src="images/person.jpg" width="100px" height="100px"></img>
							<%}else{ %>
							<img src="<%= info.getImagePath()%>" width="100px" height="100px"></img>
							<%} %>
						</a>
					</td>
					<td class="myline">
					<span class="listNameheader" ><%= info.getEmpName()%></span> [<span class="empIdData"><%=info.getEmpid() %></span> ]
					<div style="padding-top: 5px;"/>
					 Email: <span class="EmailIdStyle"><%= info.getEmailid()%></span> 
					 ,
					 Designation: <span class="EmailIdStyle"><%=info.getDesignation() %></span>
					 ,
					 Department: <span class="EmailIdStyle"><%=info.getDepartment() %></span>
					
					
					
					</td>
				</tr>
				
				<%} %>
				</table> 
			</li>
		</ul> --%>
	</div>
	</form>
		<br>
		</br>
		
		
	</div>
	<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->
     <div id="popupContact" style="width: 952px;height: 650px;border-color:#211B25;border-style:solid; border-width: 10px;">
		<a id="popupContactClose">x</a>
		
		<iframe src="calendar.jsp" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 850px;"></div> 
	<!--  second pop up -->

	
	
	
	</body>
	
</html>