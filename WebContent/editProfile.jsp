<%@page import="com.pro.emp.domain.Designation"%>
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
<script type="text/javascript" src="jjs/bootstrap.js"></script>
<script type="text/javascript" src="jjs/bootstrap-datepicker.js"></script>
		<script type="text/javascript" src="jjs/bootstrap-modal-popover.js"></script>
		<link rel="stylesheet" href="css/bootstrap.css"/>
		
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/myOwnPopup.css" type="text/css" media="screen" />

<script type="text/javascript">
function callmeToClose(){
	window.location.reload();
}

</script>

<script>
/* $('form').submit(function() {
	  alert($(this).serialize());
	  return false;
	}); */
//var dataString = 'name='+ name + '&email=' + email + '&phone=' + phone;  
function submitFormEmpInfo(){
	if(!validateEmpInfo())
		return false;
//	validateEmpInfo
//	validateAddress
//alert (dataString);return false;  
$("#messageDivEmpInfo").html("");
//var rairCom = document.getElementById('btnRair').checked;//document.editProfile.btnRair.checked;
//var acsCom = document.getElementById('btnAcs').checked;//document.editProfile.btnAcs.checked;
//var qualcommCom = document.getElementById('btnQualcomm').checked;//document.editProfile.btnQualcomm.checked;
//var allfaxCom = document.getElementById('btnAllfax').checked;//document.editProfile.btnAllfax.checked;

var qStringIs= 'txtEmpId='+document.editProfile.txtEmpId.value +
				'&txtDOB='+document.editProfile.txtDOB.value + 
				'&txtEmpName='+document.editProfile.txtEmpName.value+
				'&txtDOJ='+document.editProfile.txtDOJ.value+
				'&txtEmail='+document.editProfile.txtEmail.value+
				'&txtLoginName='+document.editProfile.txtLoginName.value+
				'&txtPassword='+document.editProfile.txtPassword.value+
				'&txtDesignation='+document.editProfile.desg.value+
				'&txtDepartment='+document.editProfile.dept.value+
				'&key='+document.editProfile.txtKey.value+
				'&creationDate='+document.editProfile.creationDate.value+
				'&processPart=empinfo';
				
alert(" qStringIs "+qStringIs);
$.ajax({  
type: "POST",  
url: "EditProfileServlet",  
data: qStringIs,  
success: function(data) {  
	//alert(" data " + data);
	  //$('#errorDiv').html(data); 
	  $("#messageDivEmpInfo").html(data);
	 // $('#message').html("<h2>Contact Form Submitted!</h2>");
	/*   .append("<p>We will be in touch soon.</p>")  
	  .hide()  
	  .fadeIn(1500, function() {  
	    $('#message').append("<img id='checkmark' src='images/check.png' />");  
	  });  */ 
	}  
});   
}

function submitFormAddress(){
	if(!validateAddress())
		return false;
//	validateEmpInfo
//	validateAddress
//alert (dataString);return false;  
$("#messageDivAddress").html("");

var qStringIs= 'txtAddress='+document.editProfile.txtAddress.value +
				'&txtCity='+document.editProfile.txtCity.value + 
				'&txtState='+document.editProfile.txtState.value+
				'&txtCountry='+document.editProfile.txtCountry.value+
				'&txtPincode='+document.editProfile.txtPincode.value+
				'&txtTelephone='+document.editProfile.txtTelephone.value+
				'&txtMobile='+document.editProfile.txtMobile.value+
				'&refContact='+document.editProfile.refContact.value+	
				'&txtEmpId='+document.editProfile.txtEmpId.value +
				'&key='+document.editProfile.txtKey1.value+
				'&processPart=address';
//alert(" url is " + qStringIs);
$.ajax({  
type: "POST",  
url: "EditProfileServlet",  
data: qStringIs,  
success: function(data) {  
	//alert(" data " + data);
	  //$('#errorDiv').html(data); 
	  $("#messageDivAddress").html(data);
	 // $('#message').html("<h2>Contact Form Submitted!</h2>");
	/*   .append("<p>We will be in touch soon.</p>")  
	  .hide()  
	  .fadeIn(1500, function() {  
	    $('#message').append("<img id='checkmark' src='images/check.png' />");  
	  });  */ 
	}  
});   
}
</script>
<script>
function validateEmpInfo(){
	
	if(isEmpty(document.editProfile.txtEmpId.value)){
		alert(" Employee ID cannot be empty");
		document.editProfile.txtEmpId.focus();
		return false;
	}
	
	if(isEmpty(document.editProfile.txtEmpName.value)){
		alert(" Employee Name cannot be empty");
		document.editProfile.txtEmpName.focus();
		return false;
	}
	
	if(isEmpty(document.editProfile.txtDOB.value)){
		alert(" Date of Birth cannot be empty");
		document.editProfile.txtDOB.focus();
		return false;
	}

	if(isEmpty(document.editProfile.txtDOJ.value)){
		alert(" Date of joining cannot be empty");
		document.editProfile.txtDOJ.focus();
		return false;
	}

	if(isEmpty(document.editProfile.txtEmail.value)){
		alert(" Email ID cannot be empty");
		document.editProfile.txtEmail.focus();
		return false;
	}

	if(isValideEmailID(document.editProfile.txtEmail.value)){
		alert(" Email ID is not valid");
		document.editProfile.txtEmail.focus();
		return false;
	}
	var e = document.getElementById("dept");
    var str = e.options[e.selectedIndex].value;
	if(str=='choose'){
		alert(" You have to select a Department");
		document.editProfile.dept.focus();
		return false;
	}
	var e1 = document.getElementById("desg");
    var str1 = e1.options[e1.selectedIndex].value;
	if(str1=='choose'){
		alert(" You have to select a Designation");
		document.editProfile.desg.focus();
		return false;
	}
	
	if(isEmpty(document.editProfile.txtLoginName.value)){
		alert(" Login name cannot be empty");
		document.editProfile.txtLoginName.focus();
		return false;
	}
	
	if(isEmpty(document.editProfile.txtPassword.value)){
		alert(" Password cannot be empty");
		document.editProfile.txtPassword.focus();
		return false;
	}
	if(isEmpty(document.editProfile.txtPass.value)){
		alert(" Confirm password cannot be empty");
		document.editProfile.txtPass.focus();
		return false;
	}
	if(isPasswordMatch(document.editProfile.txtPassword.value,document.editProfile.txtPass.value)){
		alert(" Password not matching ");
		document.editProfile.txtPassword.focus();
		return false;
	}

	return true;
}

function validateAddress(){
	
	if(isEmpty(document.editProfile.txtAddress.value)){
		alert(" Address cannot be empty");
		document.editProfile.txtAddress.focus();
		return false;
	}
	if(isEmpty(document.editProfile.txtPincode.value)){
		alert(" Pincode number cannot be empty");
		document.editProfile.txtPincode.focus();
		return false;
	}
	if(isNaN(document.editProfile.txtPincode.value)){
		alert(" Enter proper pincode value");
		document.editProfile.txtPincode.focus();
		return false;
	}
	 if (document.editProfile.txtPincode.value.length!=6)
     {
          alert("Pincode value should be 6 digit");
          document.editProfile.txtPincode.focus();
          return false;
     }
	if(isEmpty(document.editProfile.txtMobile.value)){
		alert(" Mobile number cannot be empty");
		document.editProfile.txtMobile.focus();
		return false;
	}
	if(isNaN(document.editProfile.txtMobile.value)||document.editProfile.txtMobile.value.indexOf(" ")!=-1)
    {
       alert("Enter proper mobile number");
       document.editProfile.txtMobile.focus();
       return false; 
    }
    if (document.editProfile.txtMobile.value.length!=10)
    {
         alert("Mobile number should be 10 digit");
         document.editProfile.txtMobile.focus();
         return false;
    }

	return true;
}
</script>
<script>
function submitMe() 
{ 
document.editProfile.action="EditProfileServlet"; 
document.editProfile.submit(); 
return; 
}
function loadservlet_onclick() { 
	  document.location.href = "doneBack?flows=doneBackList"; 
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
			System.out.println(" emp " + emp);
			String id = null;
			String message="";
			EmpInfo eeee= Session_control.getSession(request);
			 id = eeee.getId();
			
			if(request.getParameter("message")!=null) {
			 message = request.getParameter("message").toString();
			}
			
			  EmployeeDomain empInfo = EmployeeInfo.getCompleteEmpInfo(id);
			  EmployeeInfo.setToSession(id,request);
			  
			  EmpInfo employeeInfo = empInfo.getEmpInfo();
			  
			  
			  
		
			   Set<Department> depSet= Util.getAllDept();
			   Set<Designation> desSet= Util.getAllDesignation();
			 /*  HashMap<String,Boolean> roleMap = EmployeeInfo.getRolesForEmpId(empInfo.getEmpInfo().getId());
				if(!(roleMap.get(Constant.PROFILE_EDIT)==null?false:true)){
					response.sendRedirect(request.getContextPath()+"/home.jsp");
				}  */
			
  %>
		<script language="JavaScript" type="text/javascript">
		javascript:window.history.forward(1);
</script>
<title><%=emp.getEmpName()%> - Edit Profile</title>
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
<style type="text/css">
.hover_caption {
background-image: url(hover_caption_bg.png);
/* NOTE: if you're img elements have paddings
or margins you'll need to match them here
to get things lined up properly. */
}
</style>
  <body class="textStyle" marginheight="0" marginwidth="0">
  
  	<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="js/jquery.slimscroll.js"></script>

	
	<div class="navbar navbar-inverse navbar-static-top"
		style="margin:0;background-color:#000000;">
		<div class="container" style="margin-top: -35px; margin-bottom: 0px;">
			<h3>
				<a href="#" class="navbar-brand" style="font-size: 30px;margin-top: 5px;"><span style="color:white;"><span class="rednum" style="font-family: calibri;font-weight:bold ;font-size:30px; ">e&nbsp; </span>Huddle</span></a>
			</h3>
		</div>
		<div class="container" style="margin:0;">
			
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>

			</button>
			<div class="collapse navbar-collapse navHeaderCollapse">

				<ul class="nav navbar-nav navbar-right">
					
					<!-- class="hoverOnStyle" -->
					
					
					<li ><a href="feeds.jsp"><i class="fa fa-home fa-2x"></i>Home</a></li>
					<li><a href="profilefeed.jsp"><i class="fa fa-user fa-2x"></i> Profile</a></li>
					<!-- <li onclick="showLinks()"><a id="links" onclick="$('#manageLinks').modalPopover('show')"><i class="fa fa-home fa-2x"></i>link</a>
					</li> -->
					<li><a href="photoall.jsp"><i class="fa fa-picture-o fa-2x"></i>Photo</a></li>
					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-2x"></i>Logout</a></li>
				</ul>
		
				<!--  comment dailog -->
				
			
			</div>
		</div>
	</div>


 <!--  <div align="left" style="padding-bottom: 10px;" class="shadowStyle">
  <img src="images/logo.jpg"/>
  </div> -->
  
 


<div class="container" style="margin-top: 0px;">
	<div class="row" style="margin-top: 0px;">
			<div class="col-lg-2">
				<div onclick="coloseAllAlret()"
					style="display: table-cell; width: 15%; vertical-align: top;"
					align="left" onmouseover="hideUserInfo('sdf')">
					
			
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
					
	
<div class="col-lg-10" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default" style="margin-top: 0px;">
					<div class="panel-body"
						style="margin-top: 0px; margin-left: -0px;">
						
						
						<form action="EditProfileServlet" name="editProfile" onsubmit="return validate();" method="post">
	<table  border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td valign="top" width="100%" style="padding-bottom: 10px;">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr >
						<td class="titleSection" height="35px" style="background-color : #ADD8E6">
						<table width="100%" height="100%;" >
							<tr >
								<td class="headerSectionStyle" height="100%" style="background-color : #ADD8E6">
									<table cellpadding="0" cellspacing="0" height="100%">
										<tr>
											<td class="profileSubHeader" height="100%" style="background-color: #00688B;">
												<a style="text-decoration: none;color:white;" href="editProfile.jsp">Basic Information</a>
												
											</td>
											
											<td class="profileSubHeaderNone" height="100%" style="background-color: #40CFFF;">
												<a style="text-decoration: none;color:black;" href="editdetailInfo.jsp">Education/Experience</a>
											</td>
										</tr>
									</table>
								</td>
								<td align="right"><span style="color:white;font-size: 12px;"><%=message %></span></td>
							</tr>
						</table>
					
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table>
								<tr>
									<td>
										<a class="SendStyle" style="text-decoration: none;cursor:pointer;cursor:hand;color:blue;font-size: 15px;" onclick="callDivPopupCover()">Click here to upload cover photo</a> 
										<table>
											<tr>
												<td>
												<%
												System.out.println(" emp.getCoverImage() " + emp.getCoverImage());
												if(emp.getCoverImage()!=null){ %>
													<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%=emp.getCoverImage().getPhotoPath().replace("a_","c_")%>" width="500px" height="300px">
													<%} %>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							
							</table>
							<table border="0" style="background-color:white;" width="100%">
								<tr>
									
									<td valign="top">
										<table width="100%">
											<tr>
												<td class="columnHeader">Employee Id </td>
												<td  class="valueColumn">
												<input type="hidden" id="txtKey" name="txtKey" value="<%=employeeInfo.getId() %>"/>
												<input type="hidden" id="creationDate" name="creationDate" value="<%=employeeInfo.getCreationDate()%>"/>
												<input type="text" style="width:250px;"  class="form-control" value="<%= Util.getFormat(employeeInfo.getEmpid())%>" name="txtEmpId" /></td>
												<td class="columnHeader">Date of Birth</td>
			          							<td class="valueColumn">
			          								<div class='input-group date' id='datetimepicker1'>
									                    <input type='text' id="txtDOB" name="txtDOB" class="form-control" />
									                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
									                    </span>
									                </div>
										            <script type="text/javascript">
											            $(function () {
											                $('#datetimepicker1').datepicker({});
											            });
										        	</script>
			          							
			          						</td>
												
												
											</tr>
											<tr>
												<td class="columnHeader">Employee Name</td>
			          							<td  class="valueColumn"><input type="text" class="form-control" style="width:250px;" value="<%=employeeInfo.getEmpName() %>" name="txtEmpName"/></td>
												
			          							<td  class="columnHeader">Date of Joining</td>
			          							<td  style="vertical-align:middle;" class="valueColumn"><input type="text" class="form-control" style="width:250px;" value="<%=Util.getFormat(employeeInfo.getDoj()) %>"  size="10px" id="txtDOJ" name="txtDOJ"/>
			          							<a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtDOJ')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a>
			          							
			          							</td>
			          							
											</tr>
										 	<tr>
												<td  class="columnHeader">Department</td>
			         							<td   class="valueColumn">
			         							<!--  <input type="text" value="" name="txtDepartment"/>-->
			         							<select id="dept"  class="form-control" style="width:250px;">
			         								<option value="choose" >Choose Department</option>
			         								<%for(Department de:depSet){ 
			         									System.out.println(" emp dep info " + employeeInfo.getDepartment());
			         									if(employeeInfo.getDepartment().equalsIgnoreCase(de.getKey())){
			         								%>
			         									<option value="<%=de.getKey()%>" selected="selected"><%=de.getName()%></option>
			         								<%}else{ %>
			         								<option value="<%=de.getKey()%>"><%=de.getName()%></option>
			         								<%} }%>
			         							</select>
			         							
			         							</td>
												<td  class="columnHeader">Designation</td>
			          							<td  class="valueColumn">
			          							<%-- <input type="text"  value="<%=employeeInfo.getDesignation() %>" name="txtDesignation"/> --%>
			          									<option value="choose" >Choose Designation</option>
			          									<%for(Designation des:desSet){ 
					         								if(employeeInfo.getDesignation().equalsIgnoreCase(des.getKey())){
					         								%>
					         									<option value="<%=des.getKey()%>" selected="selected"><%=des.getName()%></option>
					         								<%}else{ %>
					         								<option value="<%=des.getKey()%>"><%=des.getName()%></option>
					          									
			          									<%}} %>
			          								</select>
			          							</td>
			          						
			          							
											</tr> 
											<tr>
												<td  class="columnHeader">Email</td>
			          							<td class="valueColumn"><input class="form-control" style="width:250px;" type="text"  value="<%=Util.getFormat(employeeInfo.getEmailid()) %>" name="txtEmail"/></td>
			          						
			          							
											</tr>
										</table>
										
										<table style="padding-top: 5px;">
											<tr>
												<td class="columnHeader">
													Login Name
												</td>
												<td class="valueColumn">
													<input type="text" class="form-control" style="width:250px;" name="txtLoginName" value="<%=Util.getFormat(employeeInfo.getLoginName()) %>">
												</td>
												<td class="columnHeader">
													Password
												</td>
												<td  class="valueColumn">
													<input type="password" class="form-control" style="width:250px;" value="<%=Util.getFormat(employeeInfo.getPassWord()) %>" name="txtPassword">
												</td>
											</tr>
											<tr>
												<td >
												</td>
												<td>
												</td>
												<td class="columnHeader">
													Confirm Password
												</td>
												<td  class="valueColumn">
													<input type="password" class="form-control" style="width:250px;" value="<%=Util.getFormat(employeeInfo.getPassWord()) %>" name="txtPass">
												</td>
												
											</tr>
										</table>
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="tableButton">
					<tr>
						<td width="70%" align="left">
							<div id="messageDivEmpInfo"></div>
						</td>
						<td width="30%" align="right" style="padding-top: 5px;padding-bottom: 5px;padding-right: 8px;">
							<input type="button" value="Update" class="form-control" style="width:250px;" onclick="submitFormEmpInfo()" class="Button" style="height: 25px;font-size: 13px;">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
	</table> 

	
    <table width="100%" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="left"> &nbsp;&nbsp;</td>
    		<td align="right">
    		&nbsp;&nbsp;
    		<input type="hidden" name="flows" value="editBackList"> 
			<!-- <input type="button" style="width: 70px;height: 30px;font-size: 13px;" value="Done" onclick="loadservlet_onclick()" class="Button"> -->
			
    		</td>
    		
    	</tr>
    	
    </table>
       

        </form>
						
						
						
					</div>				
					
					
					
  		

        
           
        
      </div>
      </div>
      </div>
      
      <form name="myForm" action="">
	<input type="hidden" class="textBox" name="empid" value="<%=id %>" > 
     <div id="popupContact" style="width: 700px;height: 400px;">
		<a id="popupContactClose" style="padding-right: 20px;color:red" onclick="callmeToClose()">x</a>
		
		<iframe src="photoUpload.jsp" style="margin: 0px;" width="100%" scrolling="auto" height="100%" id="myIFrameUpload"  name="myIFrameUpload" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopup" style=" height: 100%;"></div> 
	</form>
     <form name="myFormCover" action="">
	<input type="hidden" class="textBox" name="empidCover" value="<%=id %>" > 
     <div id="popupContactCover" style="width: 700px;height: 400px;">
		
		<iframe src="photoUploadCoverPage.jsp" style="margin:0px;" width="100%" scrolling="auto" height="100%" id="myIFrameUploadCover"  name="myIFrameUploadCover" frameborder="0" ></iframe>
	</div>
	<div id="backgroundPopupCover" style=" height: 100%;"></div> 
	</form>
     </body>
</html>

      