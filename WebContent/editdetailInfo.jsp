<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.domain.Proffesional"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.Education"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmployeeDomain"%>
<%@page import="com.pro.emp.domain.EmpIdproofInfo"%>
<%@page import="com.pro.emp.domain.EmpAdditionalInfo"%>
<%@page import="com.pro.emp.domain.EmpActivityInfo"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
 <link rel="stylesheet" href="css/SendInfoPopup.css" type="text/css" media="screen" />
 <link rel="stylesheet" href="css/hrmsstyle.css" type="text/css" media="screen" />
 <link type="text/css" href="css/calpopup.css" rel="stylesheet" media="screen">
 <script type="text/javascript" src="js/calpopup.js"></script>
 <link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/dateparse.js"></script>
<script type="text/javascript" src="js/date-parser.js"></script>
<script type="text/javascript" src="js/myOwnPopup.js"/></script>
<link rel="stylesheet" href="css/myOwnPopup.css" type="text/css" media="screen" />
 <meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
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





#button{
text-align:center;
margin:100px;
}
</style>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.2.6.min.js" ></script>
<script type="text/javascript" src="js/jsValidation.js" ></script>
<script type="text/javascript" src="js/ajaxfileupload.js"/></script>

<script type="text/javascript">
function callmeToClose(){
	window.location.reload();
}

</script>
<script >

function ajaxFileUpload()
{
	
	/* $("#loading")
	.ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	}); */
	//alert(" hi ");
	var empid1= document.addProof.empid.value;
	//alert(" hi 1");
	var flow1 = document.addProof.flow.value;
	//alert(" hi 2");
	var documentType1 = document.addProof.documentType.value;
	//alert(" hi 3");
	var description1 = document.addProof.description.value;

	$.ajaxFileUpload
	(
			
		{
			url:'addProof',
			secureuri:false,
			fileElementId:'txtAttachmentPath',
			dataType: 'json',
			data:{empid:empid1, flow1:flow1,documentType:documentType1,description:description1},
			success: function (data, status)
			{
				if(typeof(data.res) != 'undefined')
				{
					//alert(" data is sss " + data.res);
					 var url ="editOtherInfo.jsp?empId="+empid1;
					// alert(" url " + url);
					  window.location.href=url;
				}
			},
			error: function (data, status, e)
			{
				//alert(" data " + data.msg);
				//alert(" EEOR " + e + " status " + status);
				 var url ="editOtherInfo.jsp?empId="+empid1;
				// alert(" url " + url);
				  window.location.href=url;
			}
		
		});
	
	
	return false;

}

</script>
<script>
function onloadData(){
	  document.addEducation.txtStartDate.disabled=true;
	  document.addEducation.txtEndDate.disabled=true;
	  document.addProffession.txtStartDate1.disabled=true;
	  document.addProffession.txtEndDate1.disabled=true;	
}
//addProffession
function validate1(){

	if(isEmpty(document.addEducation.txtCollegeName.value)){
		alert("College Name cannot be empty");
		document.addEducation.txtCollegeName.focus();
		return false;
	}
	
	if(isEmpty(document.addEducation.txtCourse.value)){
		alert(" Course name cannot be empty");
		document.addEducation.txtCourse.focus();
		return false;
	}
	
	if(isEmpty(document.addEducation.txtCity.value)){
		alert(" City cannot be empty");
		document.addEducation.txtCity.focus();
		return false;
	}
	
	if(isEmpty(document.addEducation.txtStartDate.value)){
		alert(" Start date cannot be empty");
		document.addEducation.txtStartDate.focus();
		return false;
	}
	
	if(isEmpty(document.addEducation.txtEndDate.value)){
		alert(" End date cannot be empty");
		document.addEducation.txtEndDate.focus();
		return false;
	}
	return true;
}

function validate2(){

	if(isEmpty(document.addProffession.txtCompany.value)){
		alert(" Company cannot be empty");
		document.addProffession.txtCompany.focus();
		return false;
	}
	if(isEmpty(document.addProffession.txtDesignation.value)){
		alert(" Designation cannot be empty");
		document.addProffession.txtDesignation.focus();
		return false;
	}
	if(isEmpty(document.addProffession.txtCity.value)){
		alert(" City cannot be empty");
		document.addProffession.txtCity.focus();
		return false;
	}
	if(isEmpty(document.addProffession.txtStartDate1.value)){
		alert(" Start Date cannot be empty");
		document.addProffession.txtStatDate1.focus();
		return false;
	}
	if(isEmpty(document.addProffession.txtEndDate1.value)){
		alert(" End Date cannot be empty");
		document.addProffession.txtEndDate1.focus();
		return false;
	}
	
	return true;
}
</script>
<script>

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;
var popupStatus1 = 0;

//loading popup with jQuery magic!
function loadPopup(){
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		//var fra=document.getElementById("myIFrameUpload").src="photoUpload.jsp";
		document.getElementById('myIFrameUpload').src = document.getElementById('myIFrameUpload').src;
		document.getElementById("myIFrameUpload").contentDocument.location.reload(true);
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}
function loadPopup1(){
	//loads popup only if it is disabled
	if(popupStatus1==0){
		$("#backgroundPopup1").css({
			"opacity": "0.7"
		});
		var fra=document.getElementById("myIFrameCrop").src="ImageCrop.jsp";
		
		document.getElementById("myIFrameCrop").contentDocument.location.reload(true);
		$("#backgroundPopup1").fadeIn("slow");
		$("#popupContact1").fadeIn("slow");
		popupStatus1 = 1;
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

//disabling popup with jQuery magic!
function disablePopup1(){
	//disables popup only if it is enabled
	if(popupStatus1==1){
		$("#backgroundPopup1").fadeOut("slow");
		$("#popupContact1").fadeOut("slow");
		popupStatus1 = 0;
	}
}


//centering popup
function centerPopup(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height();
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

//centering popup
function centerPopup1(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight-100;
	var popupHeight = $("#popupContact1").height();
	var popupWidth = $("#popupContact1").width();
	//centering
	$("#popupContact1").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2+50,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup1").css({
		"height": windowHeight+200,
		"width": windowWidth+100
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
	//	disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			//disablePopup();
		}
	});

});

$(document).ready(function(){

	//LOADING POPUP
	//Click the button event!
	$(".CropImage").click(function(){

	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose1").click(function(){
		disablePopup1();
	});
	//Click out event!
	$("#backgroundPopup1").click(function(){
	//	disablePopup1();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			//disablePopup1();
		}
	});

});

//document.addProof.reset();
function callDivPopup(){
	
	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "ManageCalSession?empid="+document.myForm.empid.value;
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

function callDivPopup1(){
	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "ManageCalSession?empid="+document.myForm1.empid1.value;
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
	centerPopup1();
	//load popup
	loadPopup1();
}
</script>

  <%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			String id = null;
			String message1 = "";
  			String message2 = "";
			 id = emp.getId();
			if(request.getParameter("message1")!=null) {
				 message1 = request.getParameter("message1").toString();
			}
			if(request.getParameter("message2")!=null) {
				 message2 = request.getParameter("message2").toString();
			}
			  EmployeeDomain empInfo = EmployeeInfo.getCompleteEmpInfo(id);
			  Set<Education> education = empInfo.getEducation();
			  Set<Proffesional> proffession = empInfo.getProffesional();
			   System.out.println("emp info " + empInfo+"  emdutcion " + education  + " proi " + proffession);
			
  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=emp.getEmpName()%> - Edit Information</title>
 <style type="text/css">

 .borderForTable {
 
 			border-bottom-color: #ADC8E2;
 			border-bottom-style: solid;
 			border-bottom-width: 1px;
 			
 			border-right-color: #ADC8E2;
 			border-right-style: solid;
 			border-right-width: 1px;
 			
 			border-left-color: #ADC8E2;
 			border-left-style: solid;
 			border-left-width: 1px;
 			
 			padding-bottom: 10px;
 }
 .Button {
 		 background-color:#C5D8EB;
        border-color: #ADC8E2;
        border-style: solid;
        font-weight:bold;
        border-width: 1px;
        color: #fff;
        font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
        font-size: 12px;
        margin: 0 2px;
        padding: 2px 18px;
 }

  </style>
  
  <script type="text/javascript">

  //var startDate1 =document.getElementById("txtStartDate");
 //   var endDate1=document.getElementById("txtEndDate");
  
  //  alert(" hi ");
  //startDate1.disabled=true;
 // endDate1.disabled=true;
  function addNewEducation() {

	  if(!validate1())
			return false;
	  var empidd=document.addEducation.empid.value;
	  var collegeName=document.addEducation.txtCollegeName.value;
	  var course=document.addEducation.txtCourse.value;
	  var city=document.addEducation.txtCity.value;
	  var startDate =document.addEducation.txtStartDate.value;
	  var endDate=document.addEducation.txtEndDate.value;
	  
	  $.getJSON("CreateEducation", {'empId' :empidd,'collegeName':collegeName,'course':course,'city':city,'startDate':startDate,'endDate':endDate}, function(datas) { // Do an AJAX call
		//alert("7");
		var url ="editdetailInfo.jsp?empId="+empidd;
		// alert(" url " + url);
		  window.location.href=url;
		 // alert(" data s" + datas.empId);
	  
	  });
	}
  
  function addPreffessinal() {

	  if(!validate2())
			return false;
	  //alert(" hi ");
	  var empId=document.addProffession.empid11.value;
	  var company=document.addProffession.txtCompany.value;
	  var designation=document.addProffession.txtDesignation.value;
	  var city=document.addProffession.txtCity.value
	  var startDate=document.addProffession.txtStartDate1.value;
	  var endDate =document.addProffession.txtEndDate1.value;
	 // alert(" ok " + city);
	  $.getJSON("CreateProffession", {'empId' :empId,'company':company,'designation':designation,'city':city,'startDate':startDate,'endDate':endDate}, function(datas) { // Do an AJAX call
		//alert("7");
		var url ="editdetailInfo.jsp?empId="+empId;
		// alert(" url " + url);
		  window.location.href=url;
		 // alert(" data s" + datas.empId);
	  
	  });
	}
  
  </script>
  
  <script>
  javascript:window.history.forward(1);
  </script>
    <script type="text/javascript" src="js/locale2.js" ></script>
  <script>
$(document).ready(function() {
	$('span img').hover_caption();
	});

$('span img').hover_caption({
	caption_font_size: '10px',
	caption_color: 'white',
	caption_bold: false,
	caption_default: ""
	});
</script>
<style type="text/css">
.hover_caption {
background-image: url(hover_caption_bg.png);
/* NOTE: if you're img elements have paddings
or margins you'll need to match them here
to get things lined up properly. */
}
</style>
  <meta http-equiv="Expires"       content="Sat, 01 Dec 2001 00:00:00 GMT" />
  <meta http-equiv="pragma"        content="no-cache" />
  <meta http-equiv="Cache-Control" content="no-cache" />
</head>
<body class="textStyle" onload="onloadData()"  marginheight="0" marginwidth="0">
<div align="center">
 <div style="background-color: #00688B;height: 60px;width: 100%;">
 <div style="height: 10px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  		</div>
	  </div>
 <div style="width: 80%;" align="center">
   <div style="width: 100%;">
   
   <table width="100%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<span class="headerLink" style="padding-left: 5px;"><%=emp.getEmpName() %></span>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<table  cellpadding="0" cellspacing="0" align="right" style="vertical-align: bottom;">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="feeds.jsp" >Home</a>
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
								onmouseover="this.className='headerLinkMO'" href="logout.jsp" >Log out</a>
							</td>
							<!-- <td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="services.jsp" target="contents">Services</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="aboutus.jsp" target="contents">About Us</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="contactus.jsp" target="contents">Contact Us</a>
							</td>
							 -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
   

  </div>
  <table width="100%">
  	<tr>
  		<td valign="top" style="padding-right: 2px;padding-top: 5px;">	
  		<a href="#" class="SendStyle" style="color: white;cursor:pointer;cursor:hand;" onclick="callDivPopup()" > 
  			<span>
  			<% if(empInfo.getEmpInfo().getImage()==null) {%>
										<img src="images/person.jpg" width="150" height="100" title="Click here to update">
										<%}else{ %>
										<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%= empInfo.getEmpInfo().getImage().getPhotoPath().replaceFirst("a_","f_")%>" width="200" title="Click here to update">
										<%} %>
										</span>
			</a>
			<table width="100%" style="padding-top: 5px;">
					
					<tr> 
						<td class="headerGap1"  style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="editProfile.jsp" class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/edit_profile.jpg"/>Profile</a>
						</td>
					</tr>
					<tr>
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="feeds.jsp" class="leftLinkHeader">
							<img class="iconStyleLeftHeadings" src="icon/feeds.jpg"/>Feeds</a>
						</td>
					</tr>
					<tr> 
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="photo.jsp" class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/photos.jpg"/>Photo</a>
						</td>
					</tr>
					<tr>
						<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="colleagueList.jsp"  class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/colleagues.png"/>Colleagues</a>
						</td>
					</tr>
				</table>
  		</td>
  		<td valign="top" style="vertical-align: top;padding-left: 2px;">
			<table width="100%">
				<tr>
						<td valign="top" width="100%" style="padding-bottom: 10px;">
					<table width="100%" cellpadding="0" cellspacing="0" height="35px" border="1" style="padding-top: 3px;">
						<tr>
							<td class="titleSection"  height="100%" valign="middle"  width="100%" style="vertical-align: middle;"style="background-color : #ADD8E6">
								<table width="100%" height="100%" style="vertical-align: middle;">
										<tr>
										<td class="headerSectionStyle1" height="35px" style="background-color : #ADD8E6">
										<table cellpadding="0" cellspacing="0" height="100%" border="1" style="padding-bottom: 3px;padding-top: 3px;">
											<tr>
												<td  class="profileSubHeaderNone"  style="padding-left: 5px;padding-right: 5px;vertical-align: middle;background-color: #40CFFF;" height="100%">
													<a style="text-decoration: none;color:black;font-weight: bold;font-size: 12px;" href="editProfile.jsp">Basic Information</a>
													
												</td>
												
												<td style="vertical-align: middle;background-color: #00688B;"  class="profileSubHeader" height="100%">
													<a style="text-decoration: none;color:white;font-weight: bold;font-size: 12px;" href="editdetailInfo.jsp">Education/Experience</a>
												</td>
											</tr>
										</table>
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
					<td valign="top" width="800px" style="padding-bottom: 10px;">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="titleSection" height="25px" width="100%">
								<table cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td class="headerSectionStyle" width="20%" align="left">
											Education Details
										</td>
										<td width="80%" align="right">
											<span style="color:white;font-size: 12px;"><%=message1 %></span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="borderForTable">
								<table width="100%" >
									<tr  >
										<td style="width:170px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px;padding-top: 6px;" class="contentsDisplayHeader">College Name</td>
										<td style="width:170px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px; padding-top: 6px;" class="contentsDisplayHeader" align="left">Course</td>
										<td style="width:170px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px; padding-top: 6px;" class="contentsDisplayHeader" align="left">City</td>
										<td style="width:120px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px; padding-top: 6px;" class="contentsDisplayHeader" align="left">Start Date</td>
										<td style="width:120px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px; padding-top: 6px;" class="contentsDisplayHeader" align="left">End Date</td>
										<td></td>
									</tr>
									<tr>
										<form action="addEducation" name="addEducation"  method="post">
										<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
											<input type="hidden" class="textBox"  name="flow" value="modification" > 
											<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;" valign="middle"><input type="text" class="textBox"   value="" size="15px" name="txtCollegeName"></td>										
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  name="txtCourse" size="15px"></td>
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  name="txtCity"  size="15px"></td>
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input type="text" style="font-size: 10px;" id="txtStartDate" size="10px" value="" name="txtStartDate">
			          								 <a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtStartDate')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a></td>
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input type="text" style="font-size: 10px;"  id="txtEndDate" size="10px" value="" name="txtEndDate">
			          								 <a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtEndDate')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a></td>
											<td class="contentsDisplay1" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"> <input class="Button" type="button" onclick="addNewEducation()" value="Add"> </td>
										</form>
									</tr>
									<%
									if(education!=null){
									for(Education info: education) { %>
									<tr>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getCollegeName() %> </td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getCourseName() %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getCity() %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getStrFrom() %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getStrTo() %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <a class="linkButton" href="DeleteEducation?key=<%= info.getKey()%>&empId=<%=info.getEmpId()%>&flow=modification"><img height="20px" src="images/deleteImage.jpg">&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
									</tr>
									
									<%} }%>
								</table>
							</td>
							
						</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td style="padding-bottom: 10px;">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="titleSection" height="25px" width="100%">
								<table cellpadding="0" cellspacing="0" width="100%">
									<tr>
										<td class="headerSectionStyle"  width="20%" align="left">
											Proffesional Details
										</td>
										<td width="80%" align="right">
											<span style="color:white;font-size: 12px;"><%=message2 %></span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="borderForTable">
								<table width="100%" border="0">
									<tr>
										<td  style="width:170px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Company</td>
										<td  style="width:170px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Designation</td>
										<td  style="width:170px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">City</td>
										<td  style="width:120px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Start Date</td>
										<td  style="width:120px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">End Date</td>
										
										<td style="width: 30px;"></td>
									</tr>
									<tr>
										<form action="" name="addProffession"  method="post" enctype="multipart/form-data">
											<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox" type="text" name="txtCompany" size="15px" id="txtCompany" value="">
											<input type="hidden" name="empid11" value="<%=id %>" id="empid11" > 
											<input type="hidden" name="flow" value="modification" id="flow"> 
											</td>
											<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  type="text" value="" size="15px" name="txtDesignation" id="txtDesignation" ></td>
											<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox" type="text" value="" size="15px" name="txtCity" id="txtCity"></td>
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input  class="textBox" type="text" style="font-size: 10px;" id="txtStartDate1" size="10px" value="" name="txtStartDate1">
			          								 <a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtStartDate1')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a></td>
											<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input type="text" style="font-size: 10px;"  id="txtEndDate1" size="10px" value="" name="txtEndDate1">
			          								 <a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtEndDate1')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a></td>
											<td class="contentsDisplay1" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"> <input class="Button" type="button" onclick="addPreffessinal()" value="Add"> </td>
										
										 	
										</form>
									</tr>	
									<% 	if(proffession!=null){							
									for(Proffesional pro:proffession) {
									%>
									<tr>
										<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;">
										<%=(pro.getCompanyName() ==null?"":pro.getCompanyName()) %> 
										</td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%=(pro.getDesignation()==null?"":pro.getDesignation()) %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><%=(pro.getCity()==null?"":pro.getCity()) %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><%=(pro.getStrWorkedFrom()==null?"":pro.getStrWorkedFrom()) %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><%=(pro.getStrWorkedTo()==null?"":pro.getStrWorkedTo()) %></td>
										<td class="contentsDisplayEdit" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><a class="linkButton" href="DeleteProffession?key=<%=pro.getKey() %>&empid=<%=id %>&flow=modification">
										
										<img height="20px" src="images/deleteImage.jpg"> 
										
										&nbsp;&nbsp;&nbsp;&nbsp;</a> </td>
									</tr>							
									<% }} %>
										
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	
  
	<div align="center">
		<div  style="width: 100%;">
			<div align="right" style="padding-right: 0px;">
				<form action="doneBack" method="post">
					<input type="hidden" name="flows" value="doneBackList"> 
					<input type="hidden" name="empid" value="<%=id %>">
					<!-- <input type="submit" style="width: 70px;height: 30px;" value="Done" class="Button"> -->
				</form>
			</div>
		</div>
	</div>
	</td>
  	</tr>
  </table>
	</div>
		<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->

	</div>
		   <form name="myForm" action="">
	<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
     <div id="popupContact" style="width: 900px;height: 600px;">
		<a id="popupContactClose" style="padding-right: 20px;color:red;" onclick="callmeToClose()">x</a>
		
		<iframe src="photoUpload.jsp" style="margin: 0px;" width="100%" scrolling="auto" height="100%" id="myIFrameUpload"  name="myIFrameUpload" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
	</form>
</body>
</html>