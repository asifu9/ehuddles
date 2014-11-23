<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.dao.EmpCompanyInfoDAO"%>
<%@page import="com.pro.emp.domain.CompanyInfo"%>
<%@page import="com.pro.emp.domain.Department"%>
<%@page import="com.pro.emp.domain.EmpCompanyInfo"%>
<%@page import="com.pro.emp.Util"%>
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
   <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
<link type="text/css" href="css/calpopup.css" rel="stylesheet" media="screen">
	<script language="Javascript" type="text/javascript" src="js/push_client.js"></script>
<script type="text/javascript" src="js/events.js"></script>
<script type="text/javascript" src="js/calpopup.js"></script>
<link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/dateparse.js"></script>
<script type="text/javascript" src="js/date-parser.js"></script>
<script type="text/javascript" src="js/jsValidation.js" ></script>
<script type="text/javascript" src="js/jquery4.1.min.js"/></script>
<script type="text/javascript" src="js/myOwnPopup.js"/></script>
<script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
<link rel="stylesheet" href="css/myOwnPopup.css" type="text/css" media="screen" />

<script type="text/javascript">
function callmeToClose(){
	window.location.reload();
}

</script>
<% HashMap<String,String> companyData=new HashMap<String,String>(); %>

  <%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			  HashMap<String,Boolean> roleMap = EmployeeInfo.getRolesForEmpId(emp.getId());
				if(!(roleMap.get(Constant.PROFILE_EDIT)==null?false:true)){
					response.sendRedirect(request.getContextPath()+"/home.jsp");
				}
			
  %>
		<script language="JavaScript" type="text/javascript">
</script>
<title><%=emp.getEmpName()%> - Forum</title>
     </head>
     
     
     
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
        color: #000;
        font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
        font-size: 11px;
        margin: 0 2px;
        padding: 2px 18px;
        cursor:pointer;cursor:hand;
 }
.head
       {   
          font-size:20px;
          font-weight: bolder;
          font-family:"Courier New";
          color: #544E4F;
       }

    .textStyle
       {   
         font-size:15px;
         font-family:Tahoma;
         
       }
 
  


	
	
</style>
  <script type="text/javascript" charset="utf-8">
$(function(){
  $('.myComboStyleEditPage').change(function(){
	  //alert("ji");
	  $.post("DepartmentChanged", {id: $(this).val()}, function(data) {
 
      $('.myCoboStyleDesignation').html(data);
    });
  });
});

function changedMe(value){
	 
		  //alert(' value ' +value);
		  $.post("DepartmentChanged", {id: value}, function(data) {
	 
	      $('.myCoboStyleDesignation').html(data);
	    
	  });
}
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
<script language="Javascript" src="js/htmlEditor/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script language="Javascript" src="js/htmlEditor/htmlbox.colors.js" type="text/javascript"></script>
	<script language="Javascript" src="js/htmlEditor/htmlbox.styles.js" type="text/javascript"></script>
	<script language="Javascript" src="js/htmlEditor/htmlbox.syntax.js" type="text/javascript"></script>
	<script language="Javascript" src="js/htmlEditor/xhtml.js" type="text/javascript"></script>
	<script language="Javascript" src="js/htmlEditor/htmlbox.min.js" type="text/javascript"></script>
<script type="text/javascript">

function checkdata(){
	document.getElementById("hiddenValue").value= document.getElementById("idTextEditor").value;
}
</script>
<style type="text/css">
.hover_caption {
background-image: url(hover_caption_bg.png);
/* NOTE: if you're img elements have paddings
or margins you'll need to match them here
to get things lined up properly. */
}
</style>
  <body class="textStyle" marginheight="0" marginwidth="0">
  <div align="center">
  <div style="background-color: #00688B;height: 60px;width: 100%;">
			<div style="height: 10px;">
			</div>
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
								onmouseover="this.className='headerLinkMO'" href="Feeds" >Home</a>
							</td>
							<% if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="profilefeed.jsp">Profile</a>
							</td>
							<% }else if(emp !=null && (roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){ %>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="listUsers.jsp" >View</a>
							</td>
							<%} %>
							<% if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {%>
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
 

  </div>
   <table width="100%">
  	<tr>
  		<td valign="top" width="20%">
  		
  			<span>
  			<% if(emp.getImage()==null) {%>
										<img src="images/person.jpg" width="160" height="100">
										<%}else{ %>
										<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%= emp.getImage().getPhotoPath().replaceFirst("0_","6_")%>" width="200">
										<%} %>
			</span>
			<table width="100%">
					
					<% if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { %>
					<tr> 
						<td class="headerGap1"   onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="editProfile.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/edit_profile.jpg"/>Profile</a>
						</td>
					</tr>
					<%}%>
					<tr>
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="Feeds" class="leftLinkHeader">
							<img class="iconStyleLeftHeadings" src="icon/feeds.jpg"/>Feeds</a>
						</td>
					</tr>
					<tr>
						<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1' " onmouseout="this.className='headerLinkBlock1'">
							<a href="Messages" class="leftLinkHeader">
								<img class="iconStyleLeftHeadings" src="icon/messages1.jpg"/>Messages <span id="messageCountId"></span>
							</a>
						</td>
					</tr>
					<%if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){ %>
					<tr> 
						<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="photo.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/photos.jpg"/>Photo</a>
						</td>
					</tr>
					<% %>
					<%} %>
					<tr>
						<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="colleagueList.jsp"  class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/colleagues.png"/>Colleagues</a>
						</td>
					</tr>
					<tr>
						<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
							<a href="UserGroup"  class="leftLinkHeader"><img class="iconStyleLeftHeadings" src="icon/group_final.png"/>Groups</a>
						</td>
					</tr>
					
					
					<tr>
						<td class="headerGap1" style="background-color: #AEC9E2;" onmouseover="this.className='headerLinkMOBlock1' " onmouseout="this.className='headerLinkBlock1'">
							<a href="Forums" class="leftLinkHeader">
								<img class="iconStyleLeftHeadings" src="icon/forum.jpg"/>Forum <span id="messageCountId"></span>
							</a>
						</td>
					</tr>
				</table>
  		</td>
  		<td valign="top">
  		
    <form action="CreateNewForum" name="editProfile" onsubmit="checkdata()"  method="post">
		<div>
			<table>
				<tr>
					<td>
						Create new Forum
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									<input type="hidden" value= "<%=emp.getId()%>" name="userId" id="userId">
									<input type="hidden" id="hiddenValue" name="hiddenValue">
									Subject: &nbsp;<input type="text" id="sub" name= "sub" size="100">
								</td>
							</tr>
							<tr>
								<td>
									<textarea class="idTextEditorcss"  id="idTextEditor"></textarea>
								</td>
							</tr>
							<tr>
								<td align="right">
									<input type="submit" class="bttn" value="submit" onclick="saveData()">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>	

	

        </form>
   </td>
  	</tr>
  </table>
        <br>
           
        
      </div>
      </div>
      
     <script>
$("#idTextEditor").css("height","100%").css("width","100%").htmlbox({
    toolbars:[
	    [
		// Cut, Copy, Paste
		//"separator","cut","copy","paste",
		// Undo, Redo
		"separator","undo","redo",
		// Bold, Italic, Underline, Strikethrough, Sup, Sub
		"separator","bold","italic","underline","strike","sup","sub",
		// Left, Right, Center, Justify
		"separator","justify","left","center","right",
		// Ordered List, Unordered List, Indent, Outdent
		"separator","ol","ul","indent","outdent",
		// Hyperlink, Remove Hyperlink, Image
		"separator","link","unlink","image"
		
		],
		[// Show code
		"separator","code",
        // Formats, Font size, Font family, Font color, Font, Background
        "separator","formats","fontsize","fontfamily",
		"separator","fontcolor","highlight",
		],
		[
		//Strip tags
		"separator","removeformat","striptags","hr","paragraph",
		// Styles, Source code syntax buttons
		"separator","quote","styles","syntax"
		]
	],
	skin:"blue"
});
</script>

     </body>
</html>


      