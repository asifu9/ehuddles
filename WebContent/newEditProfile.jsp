<%@page import="java.util.Map"%>
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
<script type="text/javascript" src="js/events.js"></script>
<script type="text/javascript" src="js/calpopup.js"></script>
<script type="text/javascript" src="js/dateparse.js"></script>
<script type="text/javascript" src="js/date-parser.js"></script>
<script type="text/javascript" src="js/jsValidation.js" ></script>
<script type="text/javascript" src="js/jquery4.1.min.js"/></script>
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
var rairCom = document.getElementById('btnRair').checked;//document.editProfile.btnRair.checked;
var acsCom = document.getElementById('btnAcs').checked;//document.editProfile.btnAcs.checked;
var qualcommCom = document.getElementById('btnQualcomm').checked;//document.editProfile.btnQualcomm.checked;
var allfaxCom = document.getElementById('btnAllfax').checked;//document.editProfile.btnAllfax.checked;

var qStringIs= 'txtEmpId='+document.editProfile.txtEmpId.value +
				'&txtDOB='+document.editProfile.txtDOB.value + 
				'&txtEmpName='+document.editProfile.txtEmpName.value+
				'&txtDOJ='+document.editProfile.txtDOJ.value+
				'&txtEmail='+document.editProfile.txtEmail.value+
				'&txtLoginName='+document.editProfile.txtLoginName.value+
				'&txtPassword='+document.editProfile.txtPassword.value+
				'&txtDesignation='+document.editProfile.desg.value+
				'&txtDepartment='+document.editProfile.dept.value+
				'&rairCom='+rairCom+
				'&acsCom='+acsCom+
				'&qualcommCom='+qualcommCom+
				'&allfaxCom='+allfaxCom+
				'&key='+document.editProfile.txtKey.value+
				'&processPart=empinfo';
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
	  window.location.href="Feed";
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
	if(isEmpty(document.editProfile.dept.value)){
		alert(" Please select any department");
		document.editProfile.dept.focus();
		return false;
	}
	if(isEmpty(document.editProfile.desg.value)){
		alert(" Please select any designation");
		document.editProfile.desg.focus();
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
	if(isEmpty(document.editProfile.refContact.value)){
		alert(" Reference contact cannot be empty");
		document.editProfile.refContact.focus();
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
<% HashMap<String,String> companyData=new HashMap<String,String>(); %>

  <%
			EmpInfo emp=null;
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			String id = null;
			String message="";
			if(request.getParameter("editEmpId")!=null) {
			 id = request.getParameter("editEmpId").toString();
			}
			if(request.getParameter("message")!=null) {
			 message = request.getParameter("message").toString();
			}
			//System.out.println(" id in emp " + id);
			  EmployeeDomain empInfo = EmployeeInfo.getCompleteEmpInfo(id);
			  EmployeeInfo.setToSession(id,request);
			  EmpAdditionalInfo additionalInfo= empInfo.getEmpAddInfo();
			  EmpInfo employeeInfo = empInfo.getEmpInfo();
			  Set<EmpCompanyInfo> empComs = empInfo.getEmpComInfo();
			 // System.out.println(" comount " + empComs.size());
			
			  Set<Department> depSet= Util.getAllDept();
			
			
  %>
		<script language="JavaScript" type="text/javascript">
		javascript:window.history.forward(1);
</script>
     </head>
     
     
     
 <style type="text/css">
 .titleSection {
 			font-family: Tahoma;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #ADC8E2;
 			color:#FFFFFF;
 			padding-left: 5px;
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
	  $.post("DepartmentChanged", {id: $(this).val()}, function(data) {
      $('.myCoboStyleDesignation').html(data);
    });
  });
});

function changedMe(value){
	 
		  $.post("DepartmentChanged", {id: value}, function(data) {
	 
	      $('.myCoboStyleDesignation').html(data);
	    
	  });
}
</script>
  <body class="textStyle">
  <div align="center">
  <div style="width: 80%;" align="center">

  <div style="width: 100%;">
  <div align="left" style="padding-bottom: 10px;" class="shadowStyle">
  <img src="images/logo.jpg"/>
  </div>
  <table align="left" cellpadding="0" cellspacing="0">
  		<tr>
  			<td width="20%" class="editHeadersCurrent">
  				<a style="text-decoration: none;color:white;" href="newEditProfile.jsp?editEmpId=<%=id%>">Basic Information</a>
  			</td>
  			<%-- <td width="15%"  class="editHeaders">
  				<a style="text-decoration: none;color:gray;" href="newEditOtherInfo.jsp?empId=<%=id %>">Other information</a>
  			</td> --%>
  			
  			<td width="50%">
  			</td>
  		</tr>
  </table>
  </div>
    <form action="EditProfileServlet" name="editProfile" onsubmit="return validate();" method="post">
	<table  border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td valign="top" width="100%" style="padding-bottom: 10px;">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="titleSection" height="25px">
						<table width="100%">
							<tr>
								<td class="headerSectionStyle">Basic Employee information
								</td>
								<td align="right"><span style="color:white;font-size: 12px;"><%=message %></span></td>
							</tr>
						</table>
					
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
						
							<table border="0" style="background-color:white;" width="100%">
								<tr>
									<td width="180px" style="padding: 2px;">
									<%//System.out.println("111" + employeeInfo); %>
										<% if(employeeInfo.getImagePath()==null || employeeInfo.getImagePath().trim().equalsIgnoreCase("")) {%>
										<img src="images/person.jpg" width="100%" height="200px">
										<%}else{ %>
										<img src="<%= employeeInfo.getImagePath()%>" width="100%" height="200px">
										<%} %>
									</td>
									<td valign="top">
										<table width="100%">
											<tr>
												<td class="columnHeader">Employee Id </td>
												<td  class="valueColumn">
												<input type="hidden" name="txtKey" value="<%=employeeInfo.getId() %>"/>
												<input type="text"  value="<%= Util.getFormat(employeeInfo.getEmpid())%>" name="txtEmpId" /></td>
												<td class="columnHeader">Date of Birth</td>
			          							<td style="vertical-align:middle;" class="valueColumn"><input type="text" id="txtDOB" size="10px" value="<%=Util.getFormat(employeeInfo.getDob())%>" name="txtDOB">
			          								 <a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtDOB')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a>
			          							</td>
												
												
											</tr>
											<tr>
												<td class="columnHeader">Employee Name</td>
			          							<td  class="valueColumn"><input type="text" value="<%=employeeInfo.getEmpName() %>" name="txtEmpName"/></td>
												
			          							<td  class="columnHeader">Date of Joining</td>
			          							<td  style="vertical-align:middle;" class="valueColumn"><input type="text" value="<%=Util.getFormat(employeeInfo.getDoj()) %>"  size="10px" id="txtDOJ" name="txtDOJ"/>
			          							<a href="javascript:void(0);"
														onclick="g_Calendar.show(event, 'txtDOJ')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a>
			          							
			          							</td>
			          							
											</tr>
											<tr>
												<td  class="columnHeader">Department</td>
			         							<td   class="valueColumn">
			         							<!--  <input type="text" value="" name="txtDepartment"/>-->
			         							<select id="dept" class="myComboStyleEditPage">
			         							
			         								<%for(Department de:depSet){ %>
			         								<option value="<%=de.getId()%>"><%=de.getName()%></option>
			         								<%} %>
			         							</select>
			         							
			         							</td>
												<td  class="columnHeader">Designation</td>
			          							<td  class="valueColumn">
			          							<%-- <input type="text"  value="<%=Util.getFormat(employeeInfo.getDesignation()) %>" name="txtDesignation"/> --%>
			          								<select  id="desg" class="myCoboStyleDesignation">
			          									
			          								</select>
			          							</td>
			          						
			          							
											</tr>
											<tr>
												<td  class="columnHeader">Email</td>
			          							<td class="valueColumn"><input type="text"  value="<%=Util.getFormat(employeeInfo.getEmailid()) %>" name="txtEmail"/></td>
			          							<td colspan="2">
			          							<%if(companyData.containsKey("rair")){ %>
			          								<input type="checkbox" id="btnRair" checked="checked"> <img alt="" src="images/rairLogo.jpg" width="50px" height="20px">
			          							<%}else{ %>
			          								<input type="checkbox" id="btnRair" > <img alt="" src="images/rairLogo.jpg" width="50px" height="20px">
			          							<%} %>
			          							&nbsp;&nbsp;&nbsp;
			          							<%if(companyData.containsKey("acs")){ %>
			          								<input type="checkbox" id="btnAcs" checked="checked" > <img alt="" src="images/acsLogo.png" width="50px" height="20px">
			          							<%}else{ %>
			          								<input type="checkbox" id="btnAcs" > <img alt="" src="images/acsLogo.png" width="50px" height="20px">
			          							<%} %>
			          							&nbsp;&nbsp;&nbsp;
			          							<%if(companyData.containsKey("qualcomm")){ %>
			          								<input type="checkbox" id="btnQualcomm" checked="checked"> <img alt="" src="images/qualcommLogo.png" width="50px" height="20px">
			          							<%}else{ %>
			          								<input type="checkbox" id="btnQualcomm"> <img alt="" src="images/qualcommLogo.png" width="50px" height="20px">
			          							<%} %>
			          							&nbsp;
			          							<%if(companyData.containsKey("allfax")){ %>
			          								<input type="checkbox" id="btnAllfax" checked="checked"> <img alt="" src="images/allfaxLogo.png" width="50px" height="20px">
			          							<%}else{ %> 
			          								<input type="checkbox" id="btnAllfax"> <img alt="" src="images/allfaxLogo.png" width="50px" height="20px">
			          							<%} %>
			          							</td>
			          							
											</tr>
										</table>
										
										<table style="padding-top: 5px;">
											<tr>
												<td class="columnHeader">
													Login Name
												</td>
												<td class="valueColumn">
													<input type="text" name="txtLoginName" value="<%=Util.getFormat(employeeInfo.getLoginName()) %>">
												</td>
												<td class="columnHeader">
													Password
												</td>
												<td  class="valueColumn">
													<input type="text" value="<%=Util.getFormat(employeeInfo.getPassWord()) %>" name="txtPassword">
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
													<input type="password" value="<%=Util.getFormat(employeeInfo.getPassWord()) %>" name="txtPass">
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
							<input type="button" value="Update" onclick="submitFormEmpInfo()" class="Button" style="height: 25px;font-size: 13px;">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<%-- <tr>
			<td width="100%" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px">
							Additional Information
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table>
								<tr>
									<td class="columnHeader">Address</td>
									<td class="valueColumn">
									<input type="hidden" name="txtKey1" value="<%=employeeInfo.getId() %>"/>
									<input type="text"  value="<%if(additionalInfo!=null &&additionalInfo.getAddress()!=null) { %><%=additionalInfo.getAddress() %><%} %>" name="txtAddress"></td>
									<td class="columnHeader">City</td>
									<td class="valueColumn"><input type="text"  value="<%if(additionalInfo!=null &&additionalInfo.getCity()!=null) { %><%=additionalInfo.getCity() %><%} %>" name="txtCity"></td>
								</tr>
								<tr>
									<td class="columnHeader">State</td>
									<td class="valueColumn"><input type="text"  value="<%if(additionalInfo!=null &&additionalInfo.getState()!=null) { %><%=additionalInfo.getState() %><%} %>" name="txtState"></td>
									<td class="columnHeader">Country	</td>
									<td class="valueColumn"><input type="text"   value="<%if(additionalInfo!=null &&additionalInfo.getCountry()!=null) { %><%=additionalInfo.getCountry() %><%} %>" name="txtCountry"></td>									
								</tr>
								<tr>
									<td class="columnHeader">Pincode	</td>
									<td class="valueColumn"><input type="text"   value="<%if(additionalInfo!=null &&additionalInfo.getPincode()!=null) { %><%=additionalInfo.getPincode() %><%} %>" name="txtPincode"></td>
									<td class="columnHeader">Telephone No</td>
									<td class="valueColumn"><input type="text"  value="<%if(additionalInfo!=null &&additionalInfo.getTelephone()!=null) { %><%=additionalInfo.getTelephone() %><%} %>" name="txtTelephone"></td>
								</tr>
								<tr>
									<td class="columnHeader">Mobile No</td>								
									<td class="valueColumn"><input type="text"  value="<%if(additionalInfo!=null &&additionalInfo.getMobile()!=null) { %><%=additionalInfo.getMobile() %><%} %>" name="txtMobile"></td>
									<td class="columnHeader">Reference Address</td>
									<td class="valueColumn"><textarea name="refContact"  rows="2" cols="35"><%if(additionalInfo!=null &&additionalInfo.getRefContact()!=null) { %> <%=additionalInfo.getRefContact() %><%} %></textarea></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="tableButton">
					<tr>
						<td width="70%" align="left">
							<div id="messageDivAddress"></div>
						</td>
						<td width="30%" align="right" style="padding-top: 5px;padding-bottom: 5px;padding-right: 8px;">
							<input type="button" value="Update" onclick="submitFormAddress()" class="Button" style="height: 25px;font-size: 13px;">
						</td>
					</tr>
				</table>
			</td>
		</tr> --%>
		<%-- <tr>
			<td valign="top" width="800px" style="padding-bottom: 10px;">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td class="titleSection" height="25px">
							Extra Activity
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table>
								<tr>
									<td>Type of Activity<td>
									<td><input type="text"  value="<%=activityInfo.getActivityType() %>" name="txtTypeOfActivity">
									<td>Comments</td>
									<td><textarea name="ActivityComment"  rows="2" cols="35"><%=activityInfo.getComments() %></textarea></td>
								</tr>
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
						<td class="titleSection" height="25px">
							ID Proof
						</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table>
								<tr>
									<td>Attachment</td>
									<td>Type of Document</td>
									<td>Description</td>
								</tr>
								<% 
								int i=1;
								for(EmpIdproofInfo idProof:idProofSet) {
									if(i==1){
										o1= idProof;
										i+=1;
									}else if(i==2){
										o2=idProof;
										i+=1;
									}else if(i==3){
										o3=idProof;
										i+=1;
									}else if(i==4){
										o4=idProof;
										i+=1;
									}else if(i==5){
										o5=idProof;
										i+=1;
									}
								}%>
								
								<% if(i==1) %>
								<tr>
									<td><input type="text" value="<%= (o1.getAttachment()==null?"":o1.getAttachment()) %>" name="txtAttachmentPath1"></td>
									<td><input type="text" value="<%= (o1.getType()==null?"":o1.getType()) %>" name="documentType1"></td>
									<td><input type="text" value="<%= (o1.getDescription()==null?"":o1.getDescription()) %>"  name="description1"></td>
								</tr>
								<tr>
									<td><input type="text" value="<%= (o2.getAttachment()==null?"":o2.getAttachment()) %>" name="txtAttachmentPath2"></td>
									<td><input type="text" value="<%= (o2.getType()==null?"":o2.getType()) %>"  name="documentType2"></td>
									<td><input type="text" value="<%= (o2.getDescription()==null?"":o2.getDescription()) %>" name="description2"></td>
								</tr>
								<tr>
									<td><input type="text" value="<%= (o3.getAttachment()==null?"":o3.getAttachment()) %>" name="txtAttachmentPath3"></td>
									<td><input type="text" value="<%= (o3.getType()==null?"":o3.getType()) %>"  name="documentType3"></td>
									<td><input type="text" value="<%= (o3.getDescription()==null?"":o3.getDescription()) %>" name="description3"></td>
								</tr>
								<tr>
									<td><input type="text" value="<%= (o4.getAttachment()==null?"":o4.getAttachment()) %>" name="txtAttachmentPath4"></td>
									<td><input type="text" value="<%= (o4.getType()==null?"":o4.getType()) %>"  name="documentType4"></td>
									<td><input type="text" value="<%= (o4.getDescription()==null?"":o4.getDescription()) %>" name="description4"></td>
								</tr>
								<tr>
									<td><input type="text" value="<%= (o5.getAttachment()==null?"":o5.getAttachment())%>" name="txtAttachmentPath5"></td>
									<td><input type="text" value="<%= (o5.getType()==null?"":o5.getType()) %>"  name="documentType5"></td>
									<td><input type="text" value="<%= (o5.getDescription()==null?"":o5.getDescription()) %>" name="description5"></td>
								</tr>
								
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>--%>
	</table> 

    <table width="100%" cellpadding="0" cellspacing="0">
    	<tr>
    		<td align="left"> &nbsp;&nbsp;</td>
    		<td align="right">
    		&nbsp;&nbsp;
    		<input type="hidden" name="flows" value="editBackList"> 
			<input type="button" style="width: 70px;height: 30px;font-size: 13px;" value="Done" onclick="loadservlet_onclick()" class="Button">
			
    		</td>
    		
    	</tr>
    	
    </table>
      

        </form>
        <br>
           
        
      </div>
      </div>
     </body>
</html>

<script>
var secondCombo = document.getElementById ( "dept" );
secondCombo.value='<%=emp.getDepartment()%>';
changedMe('<%=emp.getDepartment()%>'+'~~~'+'<%=emp.getDesignation()%>');

</script>
      