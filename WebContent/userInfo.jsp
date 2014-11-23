<%@page import="com.pro.emp.domain.PhotoAlbum"%>
<%@page import="com.pro.emp.Util"%>
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
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<!--  send info style -->
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
			background-color: #A2A2A2;
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
			color:pink;
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
			background-color: #A2A2A2;
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
			color:pink;
	}
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
<script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.2.6.min.js" ></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript" src="js/jquery4.1.min.js"></script>
<script type="text/javascript">
function arrived(data){
	//alert(' flushing data '+ data);
	$('#suggestions').html(data);
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
		<%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}else{
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			
			String id="";
			
				id=emp.getId();
		%>
	
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
 <link rel="stylesheet" href="css/SendInfoPopup.css" type="text/css" media="screen" />


<% 

EmpInfo userInfo=EmployeeInfo.getEmployeeById(id);


%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
</head>
<body marginheight="0" marginwidth="0">


<!--  code from home page -->
<div align="center">
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<img src="images/logo.jpg"/>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px; border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<table  cellpadding="0" cellspacing="0" align="right">
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
								onmouseover="this.className='headerLinkMO'" href="photo.jsp" >Photo</a>
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
		

<!--  -->
<form name="myForm" action="" method="post">

	<div id="search" align="left">
		<div >
			<input type="hidden" name="flag" value="1"/>
			<input type="hidden" name="calenderEditEmpId" value=""/>
			
		</div>
		<div id="suggestions"></div>
		    
	</div>
	<div style="height: 4px;"></div>
	<div style="width: 100%;">
	
	<div align="left">
	<ul style="display:table;padding-top:0px;width: 100%;border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: thin;"  ></ul>
		<ul style="display:table;padding-top:0px;width: 100%;"  >
			
			<li style="display: table-cell ;width: 170px;padding-top:5px;border-right-color: #EEEEEE;border-right-style: inset;border-right-width: 1px;" align="right" >
				<% if(userInfo.getImagePath()==null || userInfo.getImagePath().trim().equalsIgnoreCase("")) {%>
								<img src="images/person.jpg" width="160" ></img>
								<%}else{ %>
								<img src="<%= userInfo.getImagePath()%>"  width="160"></img>
								<%} %>
				<br/>
				<div style="padding-top: 5px"></div>
				<table width="100%">
					
				
					<tr> 
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="userPhoto.jsp" style="font-family: tahoma;font-weight:bold;font-size: 15px;">Photo</a>
						</td>
					</tr>
					<tr> 
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="viewCal.jsp" style="font-family: calibri;font-size: 20px;">View</a>
						</td>
					</tr>
				</table>
			</li>
			<li style="display:table-cell;vertical-align: top;padding-top:5px;padding-left: 20px;border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: thin;">
				<% if(emp!=null && emp.getEmpName()!=null){%>
					<span class="headingUserNameClass1"><%=userInfo.getEmpName() %></span>
					&nbsp;<span class="empIdData"><%=userInfo.getEmpid() %></span>
				<%} %>
				
			    <!-- need to past here -->
			    	
			</li>
		
		</ul>
	
	</div>
		<br>
		</br>
		<%-- <table width="100%" cellpadding="0" cellspacing="0" border="1" style="background-color: pink;">
			
			<tr>
				<td valign="top" style="background-color: gray;width: 300px;height: 100px;" >
					
					<table border="1" style="background-color: fuchsia;" width="100%">
						<tr>
							<td valign="top" style="border-color: black;border-style: solid;border-width: 2px;">
							
								<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
								<img src="images/person.jpg" width="100" height="100px"></img>
								<%}else{ %>
								<img src="<%= emp.getImagePath()%>"  width="100" height="100px"></img>
								<%} %>
							</td>
							<td valign="top" align="right" style="width:200px ;border-color: black;border-style: solid;border-width: 2px;">hi howoare </td>
						</tr>
					</table>
					
					
				</td>
				
				<td valign="top"  style="">
					kashti
					
					sfdsfdsf
					
				</td>
			
			</tr>
		</table> --%>
			<%-- <table border="1" style="border-color: pink;">
			<% for(EmpInfo info: set){ %>
			<tr>
				<td class="listConntents">
					<% if(info.getImagePath()==null || info.getImagePath().trim().equalsIgnoreCase("")) {%>
					<img src="images/person.jpg" width="100px" height="100px"></img>
					<%}else{ %>
					<img src="<%= info.getImagePath()%>" width="100px" height="100px"></img>
					<%} %>
				</td>
				<td class="myline">
				<span class="listNameheader" ><%= info.getEmpName()%></span> [<span class="empIdData"><%=info.getEmpid() %></span> ]
				<div style="padding-top: 5px;"/>
				 Email: <span class="EmailIdStyle"><%= info.getEmailid()%></span> 
				 ,
				 Designation: <span class="EmailIdStyle"><%=info.getDesignation() %></span>
				 ,
				 Department: <span class="EmailIdStyle"><%=info.getDepartment() %></span>
				<div style="padding-top: 5px"></div>
				<% if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { %>
				<a href="editProfile.jsp?editEmpId=<%=info.getEmpid()%>">Modify</a>
				<%}%>
					
				
				<%if(emp!=null && (roleMap.get(Constant.CALENDAR_EDIT)==null?false:true)) {%>
					<a class="SendStyle" onclick="updateHiddenBox('<%=info.getEmpid() %>')" href="#">Modify</a>
				<%}else if(emp!=null && (roleMap.get(Constant.CALENDAR_VIEW)==null?false:true)){ %>
					<a href="viewCal.jsp?editEmpId=<%=info.getEmpid()%>">View</a>
				<%} %>
				
				
				</td>
			</tr>
			
			<%} %>
			</table> --%>
			<!-- </td>
			</tr>
		</table> -->
		
	</div>
	<!-- <div id="popupContact" style="width: 776px;height: 620px;"> -->
     <div id="popupContact" style="width: 952px;height: 650px;border-color:#211B25;border-style:solid; border-width: 10px;">
		<a id="popupContactClose">x</a>
		
		<iframe src="calendar.jsp" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 850px;"></div> 
	<!--  second pop up -->

	</form>
	
	</div>
	</div>
	</body>
	
</body>
</html>