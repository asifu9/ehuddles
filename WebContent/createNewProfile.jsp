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
  <meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
<link type="text/css" href="css/calpopup.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/events.js"></script>
<script type="text/javascript" src="js/calpopup.js"></script>
<script type="text/javascript" src="js/dateparse.js"></script>
<script type="text/javascript" src="js/date-parser.js"></script>
<script language="JavaScript" type="text/javascript">
window.history.forward(1);
</script>
<script type="text/javascript" src="js/jsValidation.js" ></script>
<script type="text/javascript" src="js/jquery4.1.min.js"/></script>
<script>
/* $('form').submit(function() {
	  alert($(this).serialize());
	  return false;
	}); */
//var dataString = 'name='+ name + '&email=' + email + '&phone=' + phone;  
function submitForm(){
	if(!validate())
		return false;

//alert (dataString);return false;  
var qStringIs= 'txtEmpId='+document.createProfile.txtEmpId.value +
				'&txtDOB='+document.createProfile.txtDOB.value + 
				'&txtEmpName='+document.createProfile.txtEmpName.value+
				'&txtDOJ='+document.createProfile.txtDOJ.value+
				'&txtEmailId='+document.createProfile.txtEmailId.value+
				'&txtLoginName='+document.createProfile.txtLoginName.value+
				'&txtPassword='+document.createProfile.txtPassword.value+
				'&txtDesignation='+document.createProfile.txtDesignation.value+
				'&txtDepartment='+document.createProfile.txtDepartment.value;
	//alert(" value is " + qStringIs);
$.ajax({  
type: "POST",  
url: "createProfile",  
data: qStringIs,  
success: function(data) {  
	//alert(" data " + data);
	  //$('#errorDiv').html(data); 
	  $("#errorDiv").html(data);
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
function validate(){

	if(isEmpty(document.createProfile.txtEmpId.value)){
		alert(" Employee ID cannot be empty");
		document.createProfile.txtEmpId.focus();
		return false;
	}

	if (document.createProfile.txtEmpId.value != "") {
	    if ( /[^A-Za-z\d]/.test(document.createProfile.txtEmpId.value)) {
	        alert("Please enter only letter and numeric characters in employee id field");
	        document.createProfile.txtEmpId.focus();
	        return false;
	    }
	}
	
	if(isEmpty(document.createProfile.txtEmpName.value)){
		alert(" Employee Name cannot be empty");
		document.createProfile.txtEmpName.focus();
		return false;
	}
	
	if(isEmpty(document.createProfile.txtDOB.value)){
		alert(" Date of Birth cannot be empty");
		document.createProfile.txtDOB.focus();
		return false;
	}
	if(isEmpty(document.createProfile.txtDOJ.value)){
		alert(" Date of joining cannot be empty");
		document.createProfile.txtDOJ.focus();
		return false;
	}
	if(isEmpty(document.createProfile.txtEmailId.value)){
		alert(" Email ID cannot be empty");
		document.createProfile.txtEmailId.focus();
		return false;
	}
	if(isValideEmailID(document.createProfile.txtEmailId.value)){
		alert(" Email ID is not valid");
		document.createProfile.txtEmailId.focus();
		return false;
	}
	
	if(isEmpty(document.createProfile.txtLoginName.value)){
		alert(" Login name cannot be empty");
		document.createProfile.txtLoginName.focus();
		return false;
	}
	if(isEmpty(document.createProfile.txtPassword.value)){
		alert(" Password cannot be empty");
		document.createProfile.txtPassword.focus();
		return false;
	}
	if(isEmpty(document.createProfile.txtPass.value)){
		alert(" Confirm password cannot be empty");
		document.createProfile.txtPass.focus();
		return false;
	}
	if(isPasswordMatch(document.createProfile.txtPassword.value,document.createProfile.txtPass.value)){
		alert(" Password not matching ");
		document.createProfile.txtPassword.focus();
		return false;
	}
	/* if(isEmpty(document.createProfile.txtPincode.value)){
		alert(" Pincode number cannot be empty");
		document.createProfile.txtPincode.focus();
		return false;
	}
	if(isNaN(document.createProfile.txtPincode.value)){
		alert(" Enter proper pincode value");
		document.createProfile.txtPincode.focus();
		return false;
	}
	 if (document.createProfile.txtPincode.value.length!=6)
     {
          alert("Pincode value should be 6 digit");
          document.createProfile.txtPincode.focus();
          return false;
     }
	if(isEmpty(document.createProfile.txtAddress.value)){
		alert(" Address cannot be empty");
		document.createProfile.txtAddress.focus();
		return false;
	}
	if(isEmpty(document.createProfile.txtMobile.value)){
		alert(" Mobile number cannot be empty");
		document.createProfile.txtMobile.focus();
		return false;
	}
	 if(isNaN(document.createProfile.txtMobile.value)||document.createProfile.txtMobile.value.indexOf(" ")!=-1)
     {
        alert("Enter proper mobile number");
        document.createProfile.txtMobile.focus();
        return false; 
     }
     if (document.createProfile.txtMobile.value.length!=10)
     {
          alert("Mobile number should be 10 digit");
          document.createProfile.txtMobile.focus();
          return false;
     }
	if(isEmpty(document.createProfile.refContact.value)){
		alert(" Reference contact cannot be empty");
		document.createProfile.refContact.focus();
		return false;
	} */
	return true;
}
</script>

<script>

$('#createProfile').submit(function() {
	//alert(" hi ");
    return validate()($(this));
});
/* $(function() {
    $('.errorDVI').hide();
    $(".Button").click(function() {
      // validate and process form here

	  $('.errorDVI').hide();
  	  var name = $("input#txtEmpName").val();
  		if (name == "") {
  		$('.errorDVI').replaceWith('<h2>Employee name required</h2>');
        $(".errorDVI").show();
        $("input#name").focus();
        return false;
      }


    });
  }); */
  
</script>
</head>
     <% 
	 EmpInfo empInfo=new EmpInfo();
     EmpAdditionalInfo empAddInfo = new EmpAdditionalInfo();
     if(session.getAttribute("createEmpInfo")!=null){
    	 empInfo =(EmpInfo)session.getAttribute("createEmpInfo");
     }
     if(session.getAttribute("createEmpAddInfo")!=null){
    	 empAddInfo =(EmpAdditionalInfo)session.getAttribute("createEmpAddInfo");
     }

%> 




     
     
     
 <style type="text/css">
 .titleSection {
 			font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
 			font-size: 15px;
 			font-weight: bold;
 			background-color: #6F5B7B;
 			color:#FFFFFF;
 			padding-left: 5px;
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
      .columnHeader {
  		font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
  		font-size: 12px;
  		font-weight: bold;
  		width: 150px;
  		background-color: #E8F7F4;
  		padding: 2px;
  }
  .valueColumn {
  		font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
  		font-size: 12px;
  		font-weight: normal;
  		width: 150px;
  		 background-color: #F5FCFB;
  		 padding: 2px;
  }
   
   .textBox {
   
   		font-size:15px;
         font-family:"lucida grande", tahoma, verdana, arial, sans-serif;
         font-weight: normal;
         border-color: #52435A;
         border-width: 1px;
   }
  </style>
  <body class="textStyle">
  <div align="center">


 
  <form id="createProfile" name="createProfile">

	<table  border="0" align="center" width="100%">
		<tr>
			<td valign="top" width="800px" style="padding-bottom: 0px;">
				<table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td class="titleSection" height="25px">Sign up</td>
					</tr>
					<tr>
						<td class="borderForTable">
							<table>
								<tr>
									<td class="columnHeader">Employee Id </td>
									<td class="valueColumn"><input class="textBox" type="text" value="<%=(empInfo.getEmpid()==null?"":empInfo.getEmpid()) %>" name="txtEmpId" /></td>
									<td  class="columnHeader">Employee Name</td>
          							<td class="valueColumn"><input class="textBox" type="text" value="<%=(empInfo.getEmpName()==null?"":empInfo.getEmpName()) %>" name="txtEmpName"/></td>
								</tr>
								<tr>
									<td  class="columnHeader">Date of Birth</td>
          							<td class="valueColumn" valign="center"><input readonly="readonly" class="textBox" type="text" id="txtDOB" size="10px" value="<%=(empInfo.getDob()==null?"":empInfo.getDob()) %>" name="txtDOB">
          								 <a href="javascript:void(0);"
											onclick="g_Calendar.show(event, 'txtDOB')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a>
          							</td>
          							          							<td  class="columnHeader">Date of Joining</td>
          							<td  class="valueColumn" valign="center"><input readonly="readonly" class="textBox" type="text" value="<%=(empInfo.getDoj()==null?"":empInfo.getDoj()) %>"  size="10px" id="txtDOJ" name="txtDOJ"/>
          							<a href="javascript:void(0);"
											onclick="g_Calendar.show(event, 'txtDOJ')" title="Show popup calendar"><img src="images/calendar.gif" class="cp_img" alt="Open popup calendar"></a>
          							
          							</td>
          							
          							</tr>
								<tr>
									<td  class="columnHeader">Designation</td>
          							<td class="valueColumn"><input  class="textBox" type="text"  value="" name="txtDesignation"/></td>
          							<td  class="columnHeader">Department</td>
         							<td class="valueColumn" >
         								<select id="txtDepartment">
         									<option value="Data Entry"> Data Entry </option>
         									<option value="Software"> Software </option>
         								</select>
         								
         							</td>
								
          							

								</tr>
								<tr>
									<td class="columnHeader">Email ID</td>
          							<td class="valueColumn"><input type="text" class="textBox" name="txtEmailId" value="<%=(empInfo.getEmailid()==null?"":empInfo.getEmailid()) %>"></td>
								
								</tr>
							</table>
							<table>
								<tr>
									<td class="columnHeader">Login name</td>
          							<td class="valueColumn"><input type="text" class="textBox" name="txtLoginName" value="<%=(empInfo.getLoginName()==null?"":empInfo.getLoginName()) %>"></td>
									<td  class="columnHeader">Password</td>
          							<td class="valueColumn"><input type="password"  class="textBox" value="<%=(empInfo.getPassWord()==null?"":empInfo.getPassWord()) %>" name="txtPassword"/></td>
          							
								</tr>
								<tr>
									<td class="columnHeader"></td>
          							<td class="valueColumn"></td>
									<td  class="columnHeader">Confirm Password</td>
          							<td class="valueColumn"><input type="password"  class="textBox" value="<%=(empInfo.getPassWord()==null?"":empInfo.getPassWord()) %>" name="txtPass"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
	</table> 
	<div style="width: 100%; "  >
		<table width="100%" style="background-color: white;width: 100%;" >
			<tr>
				<td align="left">
					<div id="errorDiv" align="left" style="font-size: 14px;font-weight: bold;color:red; "></div>
				</td>
				<td align="right">
					<div align="right" style="vertical-align: top;background-color: white;display:table-cell; ">
						<input Style="height: 25px;" onclick="submitForm()" class="Button" type="button" value=" Sing up " >
					</div>
				</td>
			</tr>
		</table>
		
	
		
	</div>
        </form>
      </div>
     
     </body>
</html>
      