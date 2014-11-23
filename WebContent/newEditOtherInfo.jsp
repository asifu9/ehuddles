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
#backgroundPopup1{
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
height:400px;
width:420px;
background:#FFFFFF;
border:0px solid #cecece;
z-index:2;
padding:0px;
font-size:13px;
}
#popupContact1{
display:none;
position:fixed;
_position:absolute; /* hack for internet explorer 6*/
height:580px;
width:900px;
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
color:white;
font-weight:700;
display:block;
}
#popupContactClose1{
font-size:16px;
line-height:14px;
right:6px;
top:4px;
padding-right:20px;
position:absolute;
color:red;
font-weight:700;
display:block;
}
#button{
text-align:center;
margin:100px;
}
</style>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.2.6.min.js" ></script>
<script type="text/javascript" src="js/jsValidation.js" ></script>

<script>
function validate1(){

	if(isEmpty(document.addActivity.txtTypeOfActivity.value)){
		alert(" Type of Activity cannot be empty");
		document.addActivity.txtTypeOfActivity.focus();
		return false;
	}
	
	if(isEmpty(document.addActivity.ActivityComment.value)){
		alert(" Activity comment cannot be empty");
		document.addActivity.ActivityComment.focus();
		return false;
	}
	return true;
}

function validate2(){

	if(isEmpty(document.addProof.txtAttachmentPath.value)){
		alert("Please select any document");
		document.addProof.txtAttachmentPath.focus();
		return false;
	}
	
	if(isEmpty(document.addProof.documentType.value)){
		alert(" Type of document cannot be empty");
		document.addProof.documentType.focus();
		return false;
	}
	
	if(isEmpty(document.addProof.description.value)){
		alert(" Description cannot be empty");
		document.addProof.description.focus();
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
	var windowHeight = document.documentElement.clientHeight-100;
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

document.addProof.reset();
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
			if(request.getParameter("empId")!=null) {
			 id = request.getParameter("empId").toString();
			}
			if(request.getParameter("message1")!=null) {
				 message1 = request.getParameter("message1").toString();
			}
			if(request.getParameter("message2")!=null) {
				 message2 = request.getParameter("message2").toString();
			}
			  EmployeeDomain empInfo = EmployeeInfo.getCompleteEmpInfo(id);
			  Set<EmpActivityInfo> activityInfo = empInfo.getEmpActInfo();
			  Set<EmpIdproofInfo> idProofSet = empInfo.getEmpIdProofs();
			
			  HashMap roleMap = EmployeeInfo.getRolesForEmpId(empInfo.getEmpInfo().getId());
				
  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <style type="text/css">
 .titleSection {
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #ADC8E2;
 			color:#FFFFFF;
 			padding-left: 5px;
 }
  .headerSectionStyle {
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #ADC8E2;
 			color:#FFFFFF;
 			padding-left: 0px;
 }
 .contentsDisplay {
 
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 12px;
 			font-weight: normal;
 			background-color: #F5FCFB;
 			height: 16px;
 }
 .contentsDisplay1 {
 
 font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 12px;
 			font-weight: normal;
 			background-color: #F5FCFB;
 			height: 15px;
 
 }
  .contentsDisplayHeader {
 
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 12px;
 			font-weight: bold;
 			background-color: #CADBEC;
 			
 }
 
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
        font-size: 11px;
        margin: 0 2px;
        padding: 2px 18px;
 }
.head
       {   
          font-size:20px;
          font-weight: bolder;
          font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
          color: #544E4F;
       }

    .textStyle
       {   
         font-size:15px;
         font-family: Tahoma;
         
       }
   .linkButton {
   		font-size:12px;
         font-family:Tahoma;
         font-weight: bold;
         color:red;
         text-decoration: none;
   
   }
         .columnHeader {
  		font-family: Tahoma;
  		font-size: 12px;
  		font-weight: bold;
  		width: 150px;
  		background-color: #E8F7F4;
  		padding: 2px;
  }
  .valueColumn {
  		font-family: Tahoma;
  		font-size: 12px;
  		font-weight: normal;
  		width: 150px;
  		 background-color: #F5FCFB;
  		 padding: 2px;
  }
   
   .textBox {
   
   		font-size:15px;
         font-family:Tahoma;
         font-weight: normal;
         border-color: #52435A;
         border-width: 1px;
   }
  </style>
  <script>
  javascript:window.history.forward(1);
  </script>
  <meta http-equiv="Expires"       content="Sat, 01 Dec 2001 00:00:00 GMT" />
  <meta http-equiv="pragma"        content="no-cache" />
  <meta http-equiv="Cache-Control" content="no-cache" />
</head>
<body class="textStyle">
<div align="center">
 <div style="width: 80%;" align="center">
   <div style="width: 100%;">
   <div align="left" style="padding-bottom: 10px;" class="shadowStyle">
  <img src="images/logo.jpg"/>
  </div>
  <table align="left" cellpadding="0" cellspacing="0">
  		<tr>
  			<td width="20%" class="editHeadersCurrent1">
  				<a style="text-decoration: none;color:gray;" href="newEditProfile.jsp?editEmpId=<%=id%>">Basic Information</a>
  			</td>
  			<td width="15%"  class="editHeaders1">
  				<a style="text-decoration: none;color:white;" href="newEditOtherInfo.jsp?empId=<%=id %>">Other information</a>
  			</td>
  			
  			<td width="50%">
  			</td>
  		</tr>
  </table>
  </div>
<table width="100%">
	<tr>
			<td valign="top" width="100%" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px" valign="middle" style="padding-top: 6px;" width="100%">
						
							<table width="100%">
								<tr>
									<td class="headerSectionStyle">
										<a href="#" class="SendStyle" style="color: white;" onclick="callDivPopup()" > Click here to update photo</a>
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
										Extra Activity
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
									<td style="width:250px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px;padding-top: 6px;" class="contentsDisplayHeader">Type of Activity</td>
									<td style="width:420px;border-color: #CADBEC;border-style: solid;border-width: 1px; height: 22px; padding-top: 6px;" class="contentsDisplayHeader" align="left">Comments</td>
									<td width="30px"></td>
								</tr>
								<tr>
									<form action="addActivity" name="addActivity" onsubmit="return validate1();" method="post">
									<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
										<input type="hidden" class="textBox"  name="flow" value="modification" > 
										<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;" valign="middle"><input type="text" class="textBox"  value="" name="txtTypeOfActivity">
										
										</td>										
										<td class="contentsDisplay" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  name="ActivityComment" size="40px"></td>
										<td class="contentsDisplay1" valign="middle" style="border-color: white;border-width: 2px;border-style: solid;"> <input class="Button" type="submit" value="Add"> </td>
									</form>
								</tr>
								<% for(EmpActivityInfo info: activityInfo) { %>
								<tr>
									<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getActivityType() %> </td>
									<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%= info.getComments() %></td>
									<td class="contentsDisplay1" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <a class="linkButton" href="deleteActivity?activityId=<%= info.getId()%>&empid=<%=info.getEmpId()%>&flow=modification"><img height="20px" src="images/deleteImage.jpg">&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
								</tr>
								
								<%} %>
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
										ID Proof
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
							<table width="100%">
								<tr>
									<td  style="width:150px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Attachment</td>
									<td  style="width:304px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Type of Document</td>
									<td  style="width:304px;border-color: #ADC8E2;height:22px;padding-top:6px;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Description</td>
									<td></td>
								</tr>
								<tr>
									<form action="addProof" name="addProof" onsubmit="return validate2();" method="post" enctype="multipart/form-data">
										<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input type="file" name="txtAttachmentPath" id="txtAttachmentPath">
										<input type="hidden" name="empid" value="<%=id %>" id="empid" > 
										<input type="hidden" name="flow" value="modification" id="flow"> 
										</td>
										<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  type="text" value="" name="documentType" ></td>
										<td class="contentsDisplay" style="border-color: white;border-width: 2px;border-style: solid;"><input class="textBox"  type="text" value="" name="description" ></td>
									 	<td class="contentsDisplay1" style="border-color: white;border-width: 2px;border-style: solid;"><input type="Submit" class="Button" value="Add">
									</form>
								</tr>	
								<% 								
								for(EmpIdproofInfo idProof:idProofSet) {
								%>
								<tr>
									<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;">
									<a href="<%=(idProof.getAttachment()==null?"":idProof.getAttachment()) %>" target="_blank" >
									<%if(idProof.getAttachment().contains(".gif")|| idProof.getAttachment().contains(".GIF")
										 || idProof.getAttachment().contains(".jpg")|| idProof.getAttachment().contains(".JPG")
										 || idProof.getAttachment().contains(".jpeg")|| idProof.getAttachment().contains(".JPEG")
										 || idProof.getAttachment().contains(".bmp")|| idProof.getAttachment().contains(".BMP")) {%>
										 
									<img width="30px" height="30px" border="0" src="<%=(idProof.getAttachmentThumbnail()==null?"":idProof.getAttachmentThumbnail()) %>">
									<%}else{ %>
									<%=idProof.getAttachment() %>
									<%} %>
									</a>
									</td>
									<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"> <%=(idProof.getType()==null?"":idProof.getType()) %></td>
									<td class="contentsDisplay" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><%=(idProof.getDescription()==null?"":idProof.getDescription()) %></td>
									<td class="contentsDisplay1" style="padding-top:5px;border-color: white;border-width: 2px;border-style: solid;"><a class="linkButton" href="deleteProof?proofId=<%=idProof.getId() %>&empid=<%=id %>&flow=modification">
									
									<img height="20px" src="images/deleteImage.jpg"> 
									
									&nbsp;&nbsp;&nbsp;&nbsp;</a> </td>
								</tr>							
								<% } %>
								
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
					<input type="submit" style="width: 70px;height: 30px;" value="Done" class="Button">
				</form>
			</div>
		</div>
	</div>
	</div>
		<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->
	<form name="myForm" action="">
	<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
     <div id="popupContact" style="width: 500px;height: 100px;">
		<a id="popupContactClose">x</a>
		
		<iframe src="photoUpload.jsp" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrameUpload"  name="myIFrameUpload" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 650px;"></div> 
	</form>
	
		<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->
	<form name="myForm1" action="">
	<input type="hidden" class="textBox" name="empid1" value="<%=id %>" > 
     <div id="popupContact1" style="width: 905px;height: 580px;">
		<a id="popupContactClose1">x</a>
		
		<iframe src="ImageCrop.jsp" style="margin: 0px;" width="100%" scrolling="auto" height="100%" id="myIFrameCrop"  name="myIFrameCrop" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup1" style=" height: 950px;"></div> 
	</form>
	</div>
</body>
</html>