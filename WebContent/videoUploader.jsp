<%@page import="java.util.Enumeration"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.VideoInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String osType=""; %>
<script>

if (navigator.appVersion.indexOf("Mac")!=-1 ||navigator.appVersion.indexOf("X11")!=-1 ||navigator.appVersion.indexOf("Linux")!=-1){
	<%osType="other";%>
}else{
	<%osType="win";%>
}

</script>

 <%

 System.out.println("  osType " + osType);



			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			
			String id="";
			if(request.getParameter("videoId")!=null)
				id=request.getParameter("videoId");
		
			
						
  %>
<!--
/*
 * jQuery File Upload Plugin Demo 6.4.1
 *  	s://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */
-->
<html lang="en">
<head>
<link rel="icon" type="image/jsp" href="icon/Video.jsp">
<script type="text/javascript" src="js/jquery_.min.js"></script>
<script type="text/javascript" src="js/jquery-1.2.6.min (1).js" ></script>
<script type="text/javascript" src="js/jspapi.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript" src="js/jquery4.1.min.js"></script>
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
<style>

</style>

<style>
 

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
color:red;
font-weight:700;
display:block;
}
#button{
text-align:center;
margin:100px;
}
 </style>
 <script type="text/javascript">
 function validationUpload(){
	 var dooo= document.getElementById("videoUploader").value;
	 if(dooo==""){
		 return false;
		 
	 }else{
		 return true;
	 }
 }
 </script>
 <script type="text/javascript">
 function updateVideo(id,videoId){
	 var checkboxItem = document.getElementById(id);
	 var videoItem = document.getElementById(videoId);
	 if(checkboxItem.checked){
		videoItem.style.opacity='0.5';
		 
	 }else{
		 videoItem.style.opacity='1.0';
	 }
 }
 
 </script>
 
 
   <script type="text/javascript">
/*   $(document).ready(function(){
	    var submit = $("#buttons");
	   // submit.hide();
	    $("textarea").blur(function() {
	        if ($(this).val() == "") {
	            $(this).val("Write a description")
	                   .removeClass("comment_filled3")
	                   .addClass("comment_empty3");
	            var dd= jQuery(this).attr("id");
	            var kk = 'but_'+dd;
	            document.getElementById(kk).style.display='none';
	            submit.hide();
	        }
	    }).focus(function() {
	        if ($(this).val() == "Write a description") {
	            $(this).val("")
	                   .removeClass("comment_empty3")
	                   .addClass("comment_filled3");
	            
	            var dd= jQuery(this).attr("id");
	            var kk = 'but_'+dd;
	            document.getElementById(kk).style.display='block';
	            submit.show();
	        }else{
	        	 var dd= jQuery(this).attr("id");
		            var kk = 'but_'+dd;
		            document.getElementById(kk).style.display='block';
	        }
	    });
	}); */
  
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
		document.getElementById("myIFrame").src="";
		var urrl='fileVideoPopup?userId='+'<%=id%>';
		document.getElementById("myIFrame").src=urrl; 
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
	var windowHeight =$(window).height();
	var scrollHieght=$(window).scrollTop();

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
		location.reload();
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

function updateHiddenBox (value) {

	/*document.myForm.calenderEditEmpId.value = value;

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

	  }*/

		//centering with css
		centerPopup();
		//load popup
		loadPopup();

	}
</script>
<% HashMap<String,Boolean> roleMap= EmployeeInfo.getRolesForEmpId(emp.getId()); %>
 <link rel="stylesheet" href="css/mystyle.css">
  <link rel="stylesheet" href="css/hrmsstyle.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
 <link rel="stylesheet" href="css/mystyle.css">
<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
<link rel="stylesheet" href="css/bootstrap-image-gallery.min.css">
<link rel="stylesheet" href="css/jquery.fileupload-ui.css">
<script type="text/javascript">
function displayXmark(key){
	//alert("hi" + key);
	var k = 'divId_'+key;
	document.getElementById(k).style.display='block';
	var l = 'divCover_'+key;
	document.getElementById(l).style.display='block';
	//document.getElementById(key).insertHTML="hi how";
/* 	$(key).css({
		"display": "block" }); */
	
}
function displayXmarkHide(key){
	var k = 'divId_'+key;
	document.getElementById(k).style.display='none';
	var l = 'divCover_'+key;
	document.getElementById(l).style.display='none';
}

function deleteVideo(key){
	 $.getJSON("DeleteVideo", {'videoId' :key}, function(datas) { // Do an AJAX call
			//alert(" result " + datas + ' : ' + datas.result);
			  if(datas.result=="SUCCESS"){
				  //document.getElementById(key).style.display='none';
				  window.location.reload();
			  }
		  });
	
	
}


function saveMe(key){
	//alert(" key " + key);
	var textBx = 'desc_'+key;
	//alert(" desc " + textBx);
	var value1 = document.getElementById(textBx).value;
	//alert(" value " + value1);
	$.getJSON("VideoAddDesc", {'key' :key,'desc':value1}, function(datas) { // Do an AJAX call
		//alert(" result " + datas + ' : ' + datas.result);
		  //if(datas.result=="SUCCESS"){
			  //document.getElementById(key).style.display='none';
			  //window.location.reload();
		  //}
		 var kk = 'but_'+textBx;
         document.getElementById(kk).style.display='none';
	  });
	
}
function updateOtherInfo(key){
	//alert(" key " + key);
	
	var name=window.document.getElementById("txtAlbumName").value;
	var desc=document.getElementById("desc").value;
	//alert(" name " + name);
	//var privacy = document.getElementById("privacy").value;
	var privacy =$("#privacy option:selected").text();
	//alert(" pv " + privacy);
	//alert(" desc " + textBx);
	// var value1 = document.getElementById(textBx).value;
	//alert(" value " + value1);
	$.getJSON("UpdateAlbumInfo", {'key' :key,'name':name,'pv':privacy,'desc':desc}, function(datas) { // Do an AJAX call
		//alert(" result " + datas + ' : ' + datas.result);
		  if(datas.result=="SUCCESS"){
			  //document.getElementById(key).style.display='none';
			  window.location.reload();
		  }
		// var kk = 'but_'+textBx;
        // document.getElementById(kk).style.display='none';
	  }); 
	
}

</script>
</head>
<body marginheight="0" marginwidth="0">
<div align="center">
 <div style="background-color: #00688B;height: 60px;width: 100%;">
 			<div style="height: 10px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left" >
	  		
	  			<span class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  		</div>
	 </div>
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
			<tr>
			<td style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<span class="headerLink" style="padding-left: 5px;"><%=emp.getEmpName() %></span>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px; border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9">
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
								onmouseover="this.className='headerLinkMO'" href="photoall.jsp?userId=<%=emp.getId() %>" >Photo</a>
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

<!-- <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
        </div>
    </div>
</div> -->
<td valign="top"> 
 
    				<tr>
    <table align="left">
    	<tr>
    		<td valign="top" align="left" style="border-right-color: #EEEEEE;border-right-style:ridge;border-right-width: 2px;">
    			<table border="0">
    				<tr valign="top">
    					<td valign="top">
    						<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
												<img src="images/person.jpg" width="150" height="100" />
												<%}else{ %>
												<img src="<%= emp.getImagePath()%>"  width="160"/>
												<%} %>
    					</td>
    				</tr>
    				<tr>
    					<td width="100%" class="titleSection22">
    						<%=emp.getEmpName() %>
    					</td>
    				</tr>
    			</table>
    			
    			
    					<td width="600px">
    						<form id="fileUpload" onsubmit="return validationUpload()"  action="tt" method="POST" enctype="multipart/form-data">
					        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
					        <div  style="width: 100%; width: 900px; background-color: white;">
					            <div  width="100%">
					                <!-- The fileinput-button span is used to style the file input field as button -->
					               <input type="hidden" name="empId" value="<%=id%>" >
					               <table>
					               	<tr>
					               		<td style="padding-left: 10px;">
					              		<table>
					              			<tr>
					              				<td style="padding-left: 20px;">
					              				<span class="btn btn-success fileinput-button">
									                    <span><i class="icon-plus icon-white"></i> Upload Video...</span>
									                    <input type="button" onclick="updateHiddenBox()" value="upload"/>
									                    <!-- <input style="display: block;" type="file" id="fileUploader" name="files[]" multiple> -->
									                </span>
									                
					              					 
					              				</td>
					              				<td>
					              					
					              						
					              						
					              				</td>
					              			</tr>
					              			<table>
					              		
					              		
					              		</td></tr>
    				
    				
    				<tr> 
    					<% List<VideoInfo> lisst= Util.getAllVideos(emp.getId()); %>
    				<% for(VideoInfo a:lisst){ System.out.println("  path is " + a.getVideoPath());%>
    				<tr> 
    					
	    					<td class="headerGap1" style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							
							<%if(osType.equalsIgnoreCase("win")){ %>
							<video width="320" height="240" controls true>
 <source src="<%=PropertyReader.getValue("videoPath") %><%=a.getVideoPath() %>" type="video/mp4">
 <object data="<%=PropertyReader.getValue("videoPath") %><%=a.getVideoPath() %>" width="320" height="240">
    <embed width="320" height="240" >
 </object>
</video>
							<%}else{ %>
							<embed type="application/x-vlc-plugin"
		        pluginspage="http://www.videolan.org" version="VideoLAN.VLCPlugin.2"
		        id="video1"
		        autoplay="no" loop="yes" width="400" height="300"
		         src="<%=PropertyReader.getValue("videoPath") %><%=a.getVideoPath() %>">

	</embed>
							<%} %>
							

</td>
    					
						
					</tr>
					<%} %>
				</table>
				
    		</td>
    	
						               
					                
					                
					                 <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                <button type="reset" class="btn btn-warning cancel">
					                    <i class="icon-ban-circle icon-white"></i> Cancel upload
					                </button>
					              <button type="button" class="btn btn-danger delete">
					                    <i class="icon-trash icon-white"></i> Delete
					                </button>
					                <input type="checkbox" class="toggle"> -->
					                </td>
					               	</tr>
					               </table>
					            </div>
					           
					        </div>
					        <!-- The loading indicator is shown during image processing -->
					        
					        
					        <!-- The table listing the files available for upload/download -->
					        <table class="table table-striped"><tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody></table>
					    </form>
					    
					<!--     <div class="well">
					        <h3>Demo Notes</h3>
					        <ul>
					            <li>The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).</li>
					            <li>Only image files (<strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction).</li>
					            <li>Uploaded files will be deleted automatically after <strong>5 minutes</strong> (demo setting).</li>
					            <li>You can <strong>drag &amp; drop</strong> files from your desktop on this webpage with Google Chrome, Mozilla Firefox and Apple Safari.</li>
					            <li>Please refer to the <a href="https://github.com/blueimp/jQuery-File-Upload">project website</a> and <a href="https://github.com/blueimp/jQuery-File-Upload/wiki">documentation</a> for more information.</li>
					            <li>Built with Twitter's <a href="http://twitter.github.com/bootstrap/">Bootstrap</a> toolkit and Icons from <a href="http://glyphicons.com/">Glyphicons</a>.</li>
					        </ul>
					    </div> -->
					<!-- modal-gallery is the modal dialog used for the image gallery -->
					
					<!-- The template to display files available for upload -->
					
					<!-- The template to display files available for download -->
					
					<script src="js/jquery_.min.js"></script>
					
					  <!-- The file upload form used as target for the file upload widget -->
    <div align="center" id="popupContact" style="width: 400px;height: 150px;border-color:#211B25;border-style:solid; border-width: 10px;">
		<a id="popupContactClose">x</a>
		
		<iframe align="middle" src="fileVideoPopup.jsp?eed=<%=emp.getId()%>" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
</body> 
</html>
					
					
					