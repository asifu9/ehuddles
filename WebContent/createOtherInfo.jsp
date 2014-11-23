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
height:400px;
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

  .headerSectionStyle {
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #6F5B7B;
 			color:#FFFFFF;
 			padding-left: 0px;
 }
</style>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.2.6.min.js" ></script>

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
		
		var fra=document.getElementById("myIFrame").src="photoUpload.jsp";
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
	var windowHeight = document.documentElement.clientHeight-100;
	var popupHeight = $("#popupContact").height();
	var popupWidth = $("#popupContact").width();
	//centering
	$("#popupContact").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2-200,
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
		centerPopup();
		loadPopup();
	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose").click(function(){
		disablePopup();
	});
	//Click out event!
	$("#backgroundPopup").click(function(){
		disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			disablePopup();
		}
	});

});

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
</script>


  <%			
			String id = "";
  			String message1 = "";
  			String message2 = "";
			if(request.getParameter("empid")!=null) {
			 id = request.getParameter("empid").toString();
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
		  response.setHeader("Pragma","no-cache"); 
		  response.setHeader("Cache-Control","no-store"); 
		  response.setHeader("Expires","0"); 
		  response.setDateHeader("Expires",-1); 
  %>
  <script language="JavaScript" type="text/javascript">
window.history.forward(1);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <style type="text/css">
 .titleSection {
 			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #6F5B7B;
 			color:#FFFFFF;
 			padding-left: 5px;
 }
 
 .contentsDisplay {
 
 			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 12px;
 			font-weight: normal;
 			background-color: #F5FCFB;
 			border-color: white;
 			border-width: 2px;
 			border-style: solid;
 			padding-left: 2px;
 			
 }
 
  .contentsDisplayHeader {
 
 			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 12px;
 			font-weight: bold;
 			background-color: #BEB0C6;
 			padding-left: 6px;
 }
 
 .borderForTable {
 
 			border-bottom-color: #6F5B7B;
 			border-bottom-style: solid;
 			border-bottom-width: 1px;
 			
 			border-right-color: #6F5B7B;
 			border-right-style: solid;
 			border-right-width: 1px;
 			
 			border-left-color: #6F5B7B;
 			border-left-style: solid;
 			border-left-width: 1px;
 			
 			padding-bottom: 10px;
 }
 .Button {
 		 background-color:#6F5B7B;
        border-color: #B5A6BD rgb(111, 91, 123) rgb(111, 91, 123) rgb(181, 166, 189);
        border-style: solid;
        font-weight:bold;
        border-width: 1px;
        color: #fff;
        font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
        font-size: 15px;
        margin: 0 2px;
        padding: 2px 18px;
 }
  .Button1 {
 		 background-color:#6F5B7B;
        border-color: #B5A6BD rgb(111, 91, 123) rgb(111, 91, 123) rgb(181, 166, 189);
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
         font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
         
       }
   .linkButton {
   		font-size:12px;
         font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
         font-weight: bold;
         color:red;
         text-decoration: none;
   
   }
  </style>
</head>
<body>
<div align="center">
	<div style="width: 70%;">
	
	  <div align="left" >
	  	<img src="images/logo.jpg"/>
	  </div>
	<hr>
	
<table>
	<tr>
			<td valign="top" width="800px" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px" valign="middle" style="padding-top: 6px;">
							<a href="#" class="SendStyle" style="color: white;" onclick="callDivPopup()" > Click here to upload photo</a>
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
									<td class="headerSectionStyle"  width="20%" align="left">
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
							<table width="100%">
								<tr  >
									<td style="height:25px; vertical-align:middle; width:250px;border-color: #6F5B7B;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Type of Activity</td>
									
									<td style="height:25px;vertical-align:middle;width:420px;border-color: #6F5B7B;border-style: solid;border-width: 1px;" class="contentsDisplayHeader" align="left">Comments</td>
									<td width="30px"></td>
								</tr>
								<tr>
									<form action="addActivity" onsubmit="return validate1();" name="addActivity" method="post">
										<td class="contentsDisplay"><input type="text"  value="" name="txtTypeOfActivity">
										<input type="hidden" name="empid" value="<%=id %>" > 
										<input type="hidden" name="flow" value="creation" > 
										</td>
										<td class="contentsDisplay"><input type="text"  name="ActivityComment"  size="50px"></td>
										<td class="contentsDisplay"> <input  type="submit" class="Button1" value="Add"> </td>
									</form>
								</tr>
								<% for(EmpActivityInfo info: activityInfo) { %>
								<tr>
									<td class="contentsDisplay"> <%= info.getActivityType() %> </td>
									<td class="contentsDisplay"> <%= info.getComments() %></td>
									<td class="contentsDisplay"> <a class="linkButton" href="deleteActivity?activityId=<%= info.getId()%>&empid=<%=info.getEmpId()%>&flow=creation"><img src="images/deleteImage.jpg">&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
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
									<td  style="height:25px; vertical-align:middle;width:233px;border-color: #6F5B7B;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Attachment</td>
									<td  style="height:25px; vertical-align:middle;width:233px;border-color: #6F5B7B;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Type of Document</td>
									<td  style="height:25px; vertical-align:middle;width:233px;border-color: #6F5B7B;border-style: solid;border-width: 1px;" class="contentsDisplayHeader">Description</td>
									<td></td>
								</tr>
								<tr>
									<form action="addProof" name="addProof"  enctype="multipart/form-data" onsubmit="return validate2();"  method="post">
										<td class="contentsDisplay"><input type="file"  name="txtAttachmentPath">
										<input type="hidden" name="empid" value="<%=id %>" > 
										<input type="hidden" name="flow" value="creation" > 
										</td>
										<td class="contentsDisplay"><input type="text" value="" name="documentType"></td>
										<td class="contentsDisplay"><input type="text" value=""  name="description"></td>
										<td class="contentsDisplay"><input type="Submit" class="Button1" value="Add">
									</form>
								</tr>	
								<% 								
								for(EmpIdproofInfo idProof:idProofSet) {
								%>
								<tr>
									<td class="contentsDisplay"><a href="<%=(idProof.getAttachment()==null?"":idProof.getAttachment()) %>" target="_blank" ><%=(idProof.getAttachment()==null?"":idProof.getAttachment()) %></a></td>
									<td class="contentsDisplay"> <%=(idProof.getType()==null?"":idProof.getType()) %></td>
									<td class="contentsDisplay"><%=(idProof.getDescription()==null?"":idProof.getDescription()) %></td>
									<td class="contentsDisplay"><a class="linkButton" href="deleteProof?proofId=<%=idProof.getId() %>&empid=<%=id %>&flow=creation"><img src="images/deleteImage.jpg">&nbsp;&nbsp;&nbsp;&nbsp;</a> </td>
								</tr>							
								<% } %>
								
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
	<div align="right" style="padding-right: 17px;">
		<form action="doneBack" method="post">
			<input type="hidden" name="flows" value="userCreationDone"> 
			<input type="submit" value=" Finish " style="height: 25px;" class="Button">
		</form>
	</div>
	
	
	<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->
	<form name="myForm" action="">
	<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
     <div id="popupContact" style="width: 500px;height: 100px;">
		<a id="popupContactClose">x</a>
		
		<iframe  src="photoUpload.jsp" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 900px;"></div> 
	<!--  second pop up -->
	</form>
</div>
</div>
</body>
</html>