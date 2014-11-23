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
<link rel="icon" type="image/png" href="icon/browserIcon.png">
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/styles.css" rel="stylesheet" media="screen"  />
 <link href="css/style.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="js/jquery_.min.js" ></script>
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
		//alert(" privacy " + privacy);
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
		})
})
</script>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}

String userEmpId="";
if(request.getParameter("editEmpId")!=null)
	userEmpId=request.getParameter("editEmpId").toString();


EmpInfo userEmp= EmployeeInfo.getEmployeeById(userEmpId);
List<PhotoAlbum> list= Util.getAllPhotoAlbulmByUser(userEmp.getId());
 HashMap<String,Boolean> roleMap= EmployeeInfo.getRolesForEmpId(emp.getId()); 

	if(!(roleMap.get(Constant.PHOTO_VIEW)==null?false:true)){
		response.sendRedirect(request.getContextPath()+"/home.jsp");
	}
%>

<title>Insert title here</title>
<script type="text/javascript" src="js/jquery4.1.min.js" ></script>
</head>
<body>
<div align="center">
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<img src="images/logo.jpg"/>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="home.jsp" >Home</a>
							</td>
							<% if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="profilefeed.jsp">Profile</a>
							</td>
							<% }else if(emp !=null && (roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){ %>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="UserFeedInfo" >View</a>
							</td>
							<%} %>
							<%  if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photoall.jsp?userId=<%=emp.getId() %>" >Photo</a>
							</td>
							<%} %>
							
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
		
	<div align="left" style="padding-top: 0px;">
		<div style="display: table-cell; vertical-align: top;width: 100px; border-right-color: balck;border-right-style: inset;border-right-width: 1px;padding-right: 10px;" >
			<table>
				<tr>
					<td width="200px">
						<table>
							<tr>
								<td style="padding-top: 20px">
									<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
										<img src="images/person.jpg" width="200px" height="200px">
										<%}else{ %>
										<img src="<%= emp.getImagePath()%>" width="200px" height="200px">
										<%} %>
									<%-- <img src="<%=(emp.getImagePath().trim()%>" width="200px" height="200px"> --%>
								</td>
							</tr>
							<tr>
								<td>
									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</div>
		<div style="display: table-cell; width: 100%;" >
			<table width="100%">
				<tr>
					<td style="padding-bottom: 50px; padding-top: 20px; padding-left: 10px; border-bottom-color: #EEEEEE;
	border-bottom-style: inset;
	border-bottom-width: thin;">
						<table width="100%">
							<tr>
								<td valign="top">
									<span class="photoHeadingstyle"><%=userEmp.getEmpName() %>'s Photo Albums </span>
								</td>
								<td align="right"  valign="top" style="padding-top:5px;">
									
								</td>
							</tr>
						</table>
						
					</td>
				</tr>
			
				<% 
				for(PhotoAlbum p:list){
					if(p.getPrivacy()==1){
					%>
					<tr>
					<td  class="photoAlbumSaperator" style="padding-left: 0px;">
						
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="photoAlbumSaperatorBlck" width="100px" style="padding-left: 10px;">
								<a href="photoList.jsp?albumId=<%=p.getIdPhotoAlbum()%>">
									<%if(p.getCoverScreenPath().trim().equals("")) {%>
										<img src="images/camera.jpg" width="200px" height="120px">
									<%}else{ %>
									
										
										<img src="<%=PropertyReader.getValue("photoAlbumPath",request)+""+p.getCoverScreenPath().replace("/","/thumbsmall_") %>" width="200px" height="120px">
									
									<%} %>
									</a>
								</td>
								<td valign="top" class="photoAlbumSaperatorBlck" style="padding-left: 5px;">
									<table>
										<tr>
											<td>
												<a class="photoAlbumnNameLink" href="photoList.jsp?album=<%=p.getIdPhotoAlbum()%>">
													<%=p.getName() %>
												</a>
											</td>
										</tr>
										<tr>
											<td>
												<%if(roleMap.get(Constant.PHOTO_EDIT)==null?false:true){ %>
												<a href="fileUploader.jsp?albumId=<%=p.getIdPhotoAlbum()%>&eed=<%=emp.getId()%>">Edit Album</a>
												<%} %>
											</td>
										</tr>
										<tr>
											<td>
												<span class="photoAlbumDateStyle" >Created on <%= p.getUpdatedOn() %></span>
											</td>
										</tr>
									</table>
									
								</td>
							</tr>
							
						</table>
						
					
					</td>
				</tr>
				<% }
				}%>
				
			</table>
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
</body>
</html>