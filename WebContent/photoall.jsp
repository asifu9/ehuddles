<%@page import="com.pro.emp.AppConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="com.pro.emp.domain.PhotoAlbumInfo"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.dao.PhotoAlbumDAO"%>
<%@page import="com.pro.emp.domain.PhotoAlbum"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/styles.css" rel="stylesheet" media="screen"  />
 <link href="css/style.css" rel="stylesheet" media="screen" />
 	<link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="js/jquery_.min.js" ></script>


	
<!-- code from herer -->

		
		
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
<script>

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;

//loading popup with jQuery magic!
function loadPopup(albumId,ownerId){
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.8"
		});
		//alert(" here data " + albumId + " : " + ownerId);
		
		document.getElementById("myIFrame").src="";
		
		var urrl='photoList.jsp?albumId='+albumId+'&ownerId='+ownerId;
		//alert(" url i s " + urrl);
		document.getElementById("myIFrame").src=urrl; 
		//document.getElementById("myIFrame").contentDocument.location.reload(true);
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}
function setAlbumId(id){
	$.getJSON("AlbumID", {'id' :id}, function(datas) { // Do an AJAX call
		window.location.href="photo_all.jsp";
	});

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
		//location.reload();
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

function updateHiddenBox (albumId,ownerId,pcount) {

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
		//alert(" hi " + pcount );
		//centering with css
		if(pcount==0){
			
		}else{
		centerPopup();
		//load popup
		loadPopup(albumId,ownerId);
		}
	}
	
</script>
<!--  code till hrere -->
<script  type="text/javascript" language="javascript">
function AjaxToSubmit(){
	var is_chrome = navigator.userAgent.toLowerCase().indexOf('chrome') > -1;
	var txtName = document.popForm.txtName.value;
	var userId = document.popForm.txtUserId.value;
	
	var privacy ="";

	if(is_chrome){
		privacy= document.popForm.txtPrivacy.value;
		
	}else {
		var getSelectedIndex1 =document.popForm.txtPrivacy.selectedIndex;
		privacy = document.popForm.txtPrivacy[getSelectedIndex1].text;
		
	}
	

	var queryString=txtName+"~~~#"+privacy+"~~~#"+userId;
	
	$.post("createAlbum", {inputQueryString : queryString}, function(datas) { // Do an AJAX call
		var divId= document.getElementById("updateDiv");
		divId.innerHTML=datas;
	});
}

$(document).ready(function(){
	$(".QTPopup").css('display','none')
	$(".lnchPopop").click(function(){
		$(".QTPopup").animate({width: 'show'}, 'slow');})
		$(".closeBtn").click(function(){			
			$(".QTPopup").css('display', 'none');
			window.location.reload();
		});
});


</script>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
 <style>
 

.logo
{

}



 </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}


List<PhotoAlbumInfo> list= Util.getAllPhotoAlbumns(emp.getId());

	//List<PhotoAlbumInfo> list2= Util.getAllPhotoAlbumns(emp.getId());
	List<String> orderedList=Util.getAlbumUserId();
	System.out.println(" order list " + orderedList);
	 %>

<title>Photo album</title>
<script type="text/javascript" src="js/jquery4.1.min.js" ></script>
<script type="text/javascript" src="js/locale2.js" ></script>
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
	<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="jjs/bootstrap.js"></script>
	<script type="text/javascript" src="js/SrapesUtil.js"></script>

<script>
$(document).ready(function() {
	$('.fixedCap').hover_caption();
	});

$('.fixedCap').hover_caption({
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
</head>
<body topmargin="0px">
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
					
					<li class="active"><a href="photoall.jsp"><i
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
										<li><a href="feeds.jsp"><i class="fa fa-envelope-square fa-lg"></i>  Feeds</a></li>
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
		
		

<div class="col-lg-10" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body"
						style="margin-top: 0px; margin-left: -0px;">

		
		
		<table width="100%" style="padding-top: 20px;">
							<tr>
								<td valign="top" style="padding-top: 20px;">
									<span class="photoHeadingstyle">Photo Albums by all users</span>
								</td>
								 <td align="right"  valign="top" style="padding-top:5px;">
									<span >
										<!-- <a href="#" class="lnchPopop"  style="font-size:16px;font-weight:bold;border:0px solid #dadada; background:#fff; line-height:5px; padding:0px; color:gray; text-decoration:none; ">
											Create New Album
										</a>  -->
									</span>
								</td> 
							</tr>
						</table>
						<div style="height: 10px;"></div>
			
				<% int counter=0;
				  int acutalSize = list.size();
				  HashMap<String,List<PhotoAlbumInfo>> ob=new HashMap<String,List<PhotoAlbumInfo>>();
				  for(PhotoAlbumInfo p:list){
					  System.out.println( p.getName()+  " time is " + p.getTimeInStr());
					  if(ob.containsKey(p.getUserId())){
						  List<PhotoAlbumInfo> l = ob.get(p.getUserId());
						  l.add(p);
						  ob.put(p.getUserId(),l);
					  }else{
						  	List<PhotoAlbumInfo> l=new ArrayList<PhotoAlbumInfo>();
						  	l.add(p);
					  		ob.put(p.getUserId(),l);
					  }
				  }
				  for(String k:orderedList){
					  counter=0;
					  List<PhotoAlbumInfo> list1=  ob.get(k); %>
					  <%
					  System.out.println(" list on " + list1);
					  if (list1!=null){ %>
					  <span> <%=list1.get(0).getUserName() %> </span>
					  <%} %>
					  <table  border="0">
						
						<tr>
						<%
						if(list1!=null){
				for(PhotoAlbumInfo p:list1){
					
					counter =counter+1;
					System.out.println(" count " + counter + " size " + list.size());
					if(counter>=4){
					%>
					
					<td  class="photoAlbumSaperator" style="padding-left: 4px; " width="200px" align="left">
					<% } else { %>
					
					<td  class="photoAlbumSaperator" style="padding-left: 4px;  " width="200px" align="left">
						<%} %>
						
							<table cellpadding="0" cellspacing="0" style="width: 200px;" width="200px" border="0" align="left">
								<tr>
									<td class="photoAlbumSaperatorBlck" width="100%" style="padding-left: 1px;">
										
										<%
										if(p.getHeight()<200 && p.getWidth()<200 ){
										%>
															
												
														
									  <a  href="#" onclick="setAlbumId('<%=p.getIdPhotoAlbum()%>')">
									  <% String descdccc = p.getDesc();
									  if(descdccc==null || descdccc.equalsIgnoreCase("null"))descdccc=""; System.out.println(" desc " + descdccc);%>
										<%if(p.getCoverScreenPath()==null || p.getCoverScreenPath().trim().equals("")) {%>
											<img class="fixedCap" src="images/camera.jpg"  alt="<%=p.getCount() %> Photos" title="<span class='albumNameStyleCaption'><%=p.getName()%></span><span class='photoAlbumUserName'> (<%=p.getUserName()%>)</span><br><sapn class='albumDescStyleCaption' ><%=descdccc%></span><br><span class='albumDescStyleCaption'> <%=p.getCount()%> Photos </span> ">
										<%}else{System.out.println(" p.getCoverScreenPath() " + p.getCoverScreenPath()); %>
										
											<img class="fixedCap" width="200px" height="200px" src="<%=PropertyReader.getValue("photoAlbumPath",request)+""+p.getCoverScreenPath().replaceFirst("a_","f_") %>"  title="<span class='albumNameStyleCaption'><%=p.getName()%> </span><span class='photoAlbumUserName'>(<%=p.getUserName()%>)</span><br><sapn class='albumDescStyleCaption' ><%=descdccc%></span><br><span class='albumDescStyleCaption'> <%=p.getCount()%> Photos </span> ">
										<%} %>
										</a>
										
											
										<br>
										
									</td>
											<%}else { %>
											  <a   href="#" onclick="setAlbumId('<%=p.getIdPhotoAlbum()%>')" >
									  <% String descdccc = p.getDesc();
									  if(descdccc==null || descdccc.equalsIgnoreCase("null"))descdccc=""; System.out.println(" desc " + descdccc);%>
										<%if(p.getCoverScreenPath()==null || p.getCoverScreenPath().trim().equals("")) {%>
											<img class="fixedCap" src="images/camera.jpg"  alt="<%=p.getCount() %> Photos" title="<span class='albumNameStyleCaption'><%=p.getName()%></span><span class='photoAlbumUserName'> (<%=p.getUserName()%>)</span><br><sapn class='albumDescStyleCaption' ><%=descdccc%></span><br><span class='albumDescStyleCaption'> <%=p.getCount()%> Photos </span> ">
										<%}else{ %>
											<img class="fixedCap"  src="<%=PropertyReader.getValue("photoAlbumPath",request)+""+p.getCoverScreenPath().replaceFirst("a_","f_") %>"  title="<span class='albumNameStyleCaption'><%=p.getName()%> </span><span class='photoAlbumUserName'>(<%=p.getUserName()%>)</span><br><sapn class='albumDescStyleCaption' ><%=descdccc%></span><br><span class='albumDescStyleCaption'> <%=p.getCount()%> Photos </span> ">
										<%} %>
										</a>
										
											
										<br>
										
									</td>
											
											
											<%} %>
								</tr>
								
							</table>
							<%
					System.out.println(" counter and size " + counter);
					if(counter==list.size()){ %>
					</td>
						</tr>
					<%} %>
					<%if(counter>=4){
						counter=0;
						
							
					%>
					
					</td>
					</tr>
					<tr>
					<% } else { %>
					<%if(counter==list.size()){ }else{ %>
					</td>
					<%} %>
					
						<%} %>
						
						
				<% 
				}
					%>	</table><%	}} %>
				
			</table>
		</div>
		</div>
		</div>
	</div> 
	</div>
	
	<!--  popup code -->
<form name="popForm" action="">
<input type="hidden" value="<%=emp.getId() %>" name="txtUserId">
<div class="QTPopup">
	<div class="popupGrayBg"></div>
	<div class="QTPopupCntnr">
		<div class="gpBdrLeftTop"></div>
		<div class="gpBdrRightTop"></div>
		<div class="gpBdrTop"></div>
		<div class="gpBdrLeft">
			<div class="gpBdrRight">
				<div class="caption">
					Create new Album
				</div>
				<a href="#" class="closeBtn" title="Close"></a>
				
				<div class="content">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td>Album name</td>
							<td width="260">
								<div class="titlebarLeftc">
								<div class="titlebarRightc">
								<div class="titlebar" style="width:250px;">
								<input type="text" value="" name="txtName"  style="border:0px;  background:none; margin-top:5px; width:245px;"/>
								</div>
								</div>
								</div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>Privacy</td>
							<td width="260">
								<div class="titlebarLeftc">
								<div class="titlebarRightc">
								<div class="titlebar" style="width:250px;">
							
								<select name="txtPrivacy" style="border:0px;  background:none; margin-top:5px; width:245px;">
									<option id="1" value="1">Public</option>
									<option id="2" value="2">Private</option>
								</select>
								</div>
								</div>
								</div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td></td>
							<td width="200px" >
								
							<input type="button" value="Create" onclick="AjaxToSubmit()" style="  margin-top:5px; width:100px;">
								<div id="updateDiv">
							</div>
							
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td></td>
							<td width="200px" >
								
							
								
							
							</td>
						</tr>
					</table>
					<br />
					
				</div>
			</div>
		</div>
		<div class="gpBdrLeftBottom"></div>
		<div class="gpBdrRightBottom"></div>
		<div class="gpBdrBottom"></div>
</div>
</div>
</form>
<!--  end of popup code -->
  <!-- The file upload form used as target for the file upload widget -->
    <div id="popupContact" style="width: 90%;height: 85%;">
		<a id="popupContactClose">x</a>
		<!-- photoList.jsp?albumId=63&ownerId=62 -->
		<iframe src="#" style="margin: 0px;" width="100%" scrolling="no" height="100%" id="myIFrame" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
</body>
</html>
