<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="com.pro.emp.domain.Designation"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.Department"%>
<%@page import="java.util.Set"%>
<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<script type="text/javascript" src="js/jsValidation.js"></script>
<script type="text/javascript" src="js/events.js"></script>
<script type="text/javascript" src="js/calpopup.js"></script>
<script type="text/javascript" src="js/dateparse.js"></script>
<script type="text/javascript" src="js/date-parser.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="jjs/jquery.min.js"></script>
<script type="text/javascript" src="jjs/jquery-ui.js"></script>

<link rel="stylesheet" href="css/bootstrap.css">
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="js/SrapesUtil.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/TickersUtil.js"></script>
<link rel="stylesheet" href="jjs/managedivision.css">
<script type="text/javascript" src="jjs/division.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="jjs/bootstrap.js"></script>

<script type="text/javascript">

	
	console.log(document.cookie);
	$('#singUpSectionId').css('display', 'none');
	function cancelForm() {

		$('#loginDailog').css('display', 'none');

	}
	function updateLocation(){
		$('#loginDailog').css('display', 'block');
		$('#loginDailog').css('z-index', '10');
		var obj = document.getElementById("createButtonId");
		// alert(" hi 3 "+obj);
		var posX = obj.offsetLeft;
		//  alert(" hi 2"+keyUser);
		var posY = obj.offsetTop;
		//alert(" hi d" + keyUser);
		//$("#popupMainDiv").css('left',offset.left);    
		//$("#popupMainDiv").css('top',offset.top);
		//alert(" hi off set " + obj.offsetLeft);
		//
		while (obj.offsetParent) {
			posX = posX + obj.offsetParent.offsetLeft;
			posY = posY + obj.offsetParent.offsetTop;
			if (obj == document.getElementsByTagName('body')[0]) {
				break;
			} else {
				obj = obj.offsetParent;
			}
		}

		//
		posY = posY + 20;
		//alert(" posX " + posX  + " posY " + posY);
		//$('#downloadInfo').css('box-shadow','0px 2px 2px 0px black');
		console.log(" posX "  + posX);
	 	$('#loginDailog').css('left', posX-670);
		$('#loginDailog').css('top', posY -25);
		$('#loginDailog').css('display', 'block'); 
	}
	/* function showCreatePopUp() {
		$('#singUpSectionId').css('display', 'block');
		$('#singUpSectionId').css('z-index', '10');

		var obj = document.getElementById("createButtonId");
		// alert(" hi 3 "+obj);
		var posX = obj.offsetLeft;
		//  alert(" hi 2"+keyUser);
		var posY = obj.offsetTop;
		//alert(" hi d" + keyUser);
		//$("#popupMainDiv").css('left',offset.left);    
		//$("#popupMainDiv").css('top',offset.top);
		//alert(" hi off set " + obj.offsetLeft);
		//
		while (obj.offsetParent) {
			posX = posX + obj.offsetParent.offsetLeft;
			posY = posY + obj.offsetParent.offsetTop;
			if (obj == document.getElementsByTagName('body')[0]) {
				break;
			} else {
				obj = obj.offsetParent;
			}
		}

		//
		posY = posY + 20;
		//alert(" posX " + posX  + " posY " + posY);
		//$('#downloadInfo').css('box-shadow','0px 2px 2px 0px black');
		$('#singUpSectionId').css('left', posX);
		$('#singUpSectionId').css('top', posY + 10);
		$('#singUpSectionId').css('display', 'block');
	} */
</script>
<style>
.bodyStyle {
	/*background-color :#659EC7;/*9BBCD8; */
	/*background-image : url(img/Optimized-body-background1.jpg);/**/
	background-repeat: no-repeat;
	background-color: white;
}

.style3 {
	padding-top: 10px;
}

h1 {
	color: Blue;
	font-weight: bold;
	font-family: Calibri;
	/* 
    background:url(home-left-title.png) no-repeat;
    */
}

.sanah {
	width: 100%;
	/* background-color : #41627E; */
}

.upper {
	text-align: left;
	font-family: Calibri;
	font-size: 38pt;
	font-weight: bold;
	color: blue;
}

.lower {
	/*background-color : #659EC7; /*9BBCD8;*/
	
}

.SignUp {
	/*background-image :url(background.jpg); */
	/* background-color : #41627E;*/
	padding-top: 10px;
	padding-bottom: 10px;
	text-align: left;
	color: black;
	font-family: calibri;
	font-size: 25px;
	display: none;
	font-style: normal;
	font-weight: bold;
	width: 100%;
	/*border: 2px solid White;*/
}

.divUpdates {
	background-color: White;
	height: 88px;
}

.hr {
	color: White;
	height: -12px;
	border-top-color: white;
	border-top-style: ridge;
	border-top-width: 2px;
	width: 100%;
	z-index: 10;
}

.tr1 {
	border: 1px solid White;
}

.textbox {
	color: white;
	height: 23px;
	width: 120px;
	padding: 3px 5px 3px 5px;
	border-style: none;
	/* 
    background-image: url(./media/textfieldwidget.png); 
    */
	font-family: courier new;
	font-size: 14px
}

span {
	/* 
    background-image: url(./media/textfieldwidget.png); 
    */
	
}
</style>
<script>
	/* $('form').submit(function() {
	 alert($(this).serialize());
	 return false;
	 }); */
	//var dataString = 'name='+ name + '&email=' + email + '&phone=' + phone;  
	function submitForm() {
		//	alert("hi");
		if (!validateCreate())
			return false;

		//alert (dataString);return false;  
		var qStringIs = 'txtEmpId=' + '&txtDOB=' + '&txtEmpName='
				+ document.createProfile.txtEmpName.value + '&txtDOJ='
				+ '&txtEmailId=' + '&txtLoginName='
				+ document.createProfile.txtLoginName.value + '&txtPassword='
				+ document.createProfile.txtPassword.value + '&txtGender='
				+ document.createProfile.genderSelect.value + '&txtMobile='
				+ document.createProfile.txtMobile.value + '&txtDesignation='
				+ document.createProfile.desSelect.value + '&txtDepartment='
				+ document.createProfile.depSelect.value;
		alert(" value is " + qStringIs);
		$.ajax({
			type : "POST",
			url : "createProfile",
			data : qStringIs,
			success : function(data) {
				//alert(" data " + data);
				//$('#errorDiv').html(data); 
				//alert(" data " + data);
				$("#errorDiv").html(data);
				$("#errorDiv2").html(data);

				$('#singUpSectionId').css('display', 'none');
				// $('#message').html("<h2>Contact Form Submitted!</h2>");
				/*   .append("<p>We will be in touch soon.</p>")  
				 .hide()  
				 .fadeIn(1500, function() {  
				 $('#message').append("<img id='checkmark' src='images/check.png' />");  
				 });  */
			}
		});
	}
	function validateCreate() {

		if (isEmpty(document.createProfile.txtEmpName.value)) {
			alert(" Name cannot be empty");
			document.createProfile.txtName.focus();
			return false;
		}

		if (isEmpty(document.createProfile.txtLoginName.value)) {
			alert(" Login name cannot be empty");
			document.createProfile.txtLoginName.focus();
			return false;
		}
		if (isEmpty(document.createProfile.txtPassword.value)) {
			alert(" Password cannot be empty");
			document.createProfile.txtPassword.focus();
			return false;
		}
		if (isEmpty(document.createProfile.txtPass.value)) {
			alert(" Confirm password cannot be empty");
			document.createProfile.txtPass.focus();
			return false;
		}
		if (isPasswordMatch(document.createProfile.txtPassword.value,
				document.createProfile.txtPass.value)) {
			alert(" Password not matching ");
			document.createProfile.txtPassword.focus();
			return false;
		}
		if (isEmpty(document.createProfile.txtMobile.value)) {
			alert(" Mobile number cannot be empty");
			document.createProfile.txtMobile.focus();
			return false;
		}
		if (document.createProfile.genderSelect.value == 'Choose geneder') {
			alert(" Choose a gender");
			document.createProfile.genderSelect.focus();
			return false;
		}

		return true;
	}
	function validate() {
		//alert(" login name " + document.loginForm.txtLoginName.value);
		if (isEmpty(document.loginForm.txtLoginName.value)) {
			alert(" Login Name cannot be empty");
			return false;
		}

		if (isEmptyPassword(document.loginForm.txtPassword.value)) {
			alert(" Password cannot be empty");
			return false;
		}

		return true;
	}
	function popupRise() {
		//centering with css
		centerPopup();
		//load popup
		loadPopup();
	}
</script>
<!--  send info style -->
<style>
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,font,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td
	{
	border: 0pt none;
	font-family: inherit;
	font-size: 100%;
	font-style: inherit;
	font-weight: inherit;
	margin: 0pt;
	padding: 0pt;
	vertical-align: baseline;
}

body {
	background: #fff none repeat scroll 0%;
	line-height: 1;
	font-size: 12px;
	font-family: arial, sans-serif;
	margin: 0pt;
	height: 100%;
}

table {
	border-collapse: separate;
	border-spacing: 0pt;
}

caption,th,td {
	font-weight: normal;
	text-align: left;
}

blockquote:before,blockquote:after,q:before,q:after {
	content: "";
}

blockquote,q {
	quotes: "" "";
}

a {
	cursor: pointer;
	text-decoration: none;
}

br.both {
	clear: both;
}

#backgroundPopup {
	display: none;
	position: fixed;
	_position: absolute; /* hack for internet explorer 6*/
	height: 100%;
	width: 100%;
	top: 0;
	left: 0;
	background: #000000;
	border: 1px solid #cecece;
	z-index: 1;
}

#popupContact {
	display: none;
	position: fixed;
	_position: absolute; /* hack for internet explorer 6*/
	height: 500px;
	width: 420px;
	background: #FFFFFF;
	border: 0px solid #cecece;
	z-index: 2;
	padding: 0px;
	font-size: 13px;
}

#popupContact h1 {
	text-align: left;
	color: #6FA5FD;
	font-size: 22px;
	font-weight: 700;
	border-bottom: 1px dotted #D3D3D3;
	padding-bottom: 2px;
	margin-bottom: 20px;
}

#popupContactClose {
	font-size: 16px;
	line-height: 40px;
	right: 16px;
	top: 4px;
	position: absolute;
	color: #FFFFFF;
	font-weight: 700;
	display: block;
	color: white;
	font-weight: bold;
}

.popupDiv {
	display: none;
	position: absolute;
	left: 300px;
	top: 200px;
	z-index: 10;
	width: 450px;
	background-color: #3d9ab9;
}

.LoginStyleNew {
	padding-left: 10px;
	padding-top: 10px;
	font-weight: bold;
	font-family: Calibri;
	color: white;
	font-size: 20pt;
	vertical-align: middle;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sing in - Employee Huddles</title>
<link type="text/css" href="css/hrmsstyle.css" rel="stylesheet"
	media="screen">
<style>
.linkButton {
	font-size: 15px;
	font-family: Tahoma;
	font-weight: bold;
	color: #52435A;
	text-decoration: none;
}

.lables {
	font-size: 15px;
	font-family: Tahoma arial;
	font-weight: bold;
	color: black;
}

.updateLine {
	font-size: 15px;
	font-family: 'Lucida Grande', Tahoma, Verdana, Arial, sans-serif;
	font-weight: bold;
	color: white;
}
</style>

<%
	String msg = "";

	if (request.getParameter("msg") != null)
		msg = request.getParameter("msg").toString();

	Set<Department> depSet = Util.getAllDept();
	Set<Designation> desSet = Util.getAllDesignation();
%>
</head>
<body>
	<script type="text/javascript" src="jjs/bootstrap-modal-popover.js"></script>
	<div class="navbar navbar-inverse navbar-static-top"
		style="margin: 0; background-color: #000000;">
		<div class="container" style="margin-top: -15px; margin-bottom: 0px;">
			<h3>
				<a href="#" class="navbar-brand"
					style="font-size: 30px; margin-top: 5px;"><span
					style="color: white;"><span class="rednum"
						style="font-family: calibri; font-weight: bold; font-size: 30px;">e&nbsp;
					</span>Huddle</span></a>
			</h3>
		</div>

	</div>
	<div class="container" style="margin-top: 0px;">
		<div class="row" style="margin-top: 0px;">
			<div class="col-lg-6">
				<table style="padding-top: 0px;width: 300px;">
					<tr>
						<td class="">
							<table>
								<tr>
									<td class="style3"><input type="image"
										src="images/connect.jpg" width="70" height="70" /></td>
									<td class="LoginStyleNew">Connect With Your Colleagues</td>
								</tr>
								<tr>
									<td class="style3"><input type="image"
										src="images/share photos.jpg" width="70" height="70" /></td>
									<td class="LoginStyleNew">Share Your Captured Life</td>
								</tr>
								<tr>
									<td class="style3"><input type="image"
										src="images/share feelings.jpg" width="70" height="70" /></td>
									<td class="LoginStyleNew">Feelings To Share</td>
								</tr>
								<tr>
									<td class="style3"><input type="image"
										src="images/messages.jpg" width="70" height="70" /></td>
									<td class="LoginStyleNew">Conversations</td>
								</tr>
							</table></td>

					</tr>
				</table>


			</div>

			<div class="col-lg-3" style="margin-top: 0px; margin-left: 0px;">
				<div class="panel panel-default"
					style="margin-top: 0px; width: 330px;">
					<div class="panel-body" style="margin-top: 0px; margin-left: -0px;">
					
						<table border="1" class="loginBorderStyleCSS"
							align="center">
							<tr>
								<td align="right" style="padding-bottom: 10px;">
								
							 	<div >
							 	<div onclick="updateLocation()">
													<button id="createButtonId" onclick="$('#loginDailog').modalPopover('show');"  class="btn btn-info btn-lg" 
										>Create an Account</button>
										</div>
										<div id="loginDailog" class="popover" style="width: 400px;">
						<div class="arrow"></div>
						<h3 class="popover-title">Create an account</h3>
						<div class="popover-content">
							<form style="" id="createProfile" name="createProfile">
								<table align="right" width="100%" border="1"
									style="padding: 5px 5px 5px 5px">

									<tr>
										<td style="" class="loginTableStyle">
											<table width="100%">
												<tr>
													<td width="300px"><span class="loginHeaderStyle">Name
													</span> <br>
														<div style="height: 6px;"></div> <input
														class="textBoxLogin" width="25px" type="text"
														name="txtEmpName" /></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Login Name </span><br>
														<div style="height: 6px;"></div> <input
														class="textBoxLogin" width="15px" type="text"
														name="txtLoginName" /></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Password </span> <br>
													<div style="height: 6px;"></div> <input
														class="textBoxLogin" width="15px" type="password"
														name="txtPassword" /></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Confirm
															Password </span><br>
														<div style="height: 6px;"></div> <input
														class="textBoxLogin" type="password" width="15px"
														name="txtPass" /></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Department </span><br>
														<div style="height: 6px;"></div> <select id="depSelect"
														class="textBoxLoginCombox">
															<option id="none">Choose department</option>
															<%
																for (Department de : depSet) {
															%>
															<option value="<%=de.getKey()%>"><%=de.getName()%></option>
															<%
																}
															%>
													</select></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Designation
													</span><br>
														<div style="height: 6px;"></div> <select id="desSelect"
														class="textBoxLoginCombox">
															<option id="none">Choose designation</option>
															<%
																for (Designation de : desSet) {
															%>
															<option value="<%=de.getKey()%>"><%=de.getName()%></option>
															<%
																}
															%>
													</select></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Mobile </span><br>
														<div style="height: 6px;"></div> <input
														class="textBoxLogin" type="text" width="15px"
														name="txtMobile" /></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="" class="loginTableStyle">
											<table>
												<tr>
													<td><span class="loginHeaderStyle">Gender </span><br>
														<div style="height: 6px;"></div> <select id="genderSelect"
														class="textBoxLoginCombox">
															<option id="none">Choose geneder</option>
															<option id="male">Male</option>
															<option id="female">Female</option>
													</select></td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="padding: 5px 5px 5px 5px;" align="right"
											class="loginTableStyle">
											<table align="right" style="padding-top: 10px;">
												<tr>
													<td width="100px" align="right"><input
														Style="height: 30px; width: 100px;" onclick="submitForm()"
														class="bttn" type="button" value=" Sign up "></td>
													<Td style="padding-left: 5px;"><input
														Style="height: 30px; width: 100px;" onclick="cancelForm()"
														class="bttn" type="button" value=" Cancel "></Td>
												</tr>
											</table>

										</td>
									</tr>
									<tr>
										<td style="">
											<div id="errorDiv"></div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
						<script>
	 	$('#loginDailog').modalPopover({
			target : '#createButtonId',
			placement : 'bottom'
		}); 
	</script>
												</div>
												
									
								</td>
							</tr>
						</table>
						<div class="panel panel-default"
							style="margin-top: 0px;width: 300px;">
							<div class="panel-body" style="">
								<form action="LoginServlet" name="loginForm"
									onsubmit="return validate();" method="post">

									<table border="1" class="loginBorderStyleCSS">
										<tr>
											<td style="padding-top: 10px; padding-bottom: 10px;">

												<table align="right" width="100%" border="0"
													style="padding: 5px 5px 5px 5px;" >

													<tr>
														<td style="padding-bottom: 10px; padding-top: 10px;">
															<span class="SignupHeading">Sign In</span>
														</td>
													</tr>
													<tr>
														<td align="left"><span class="loginHeaderStyle"
															style="text-align: left;">User Name </span> <br>
															<div style="height: 6px;"></div></td>
													</tr>
													<tr>
														<td align="center"><input class="loginTextBoxStyle"
															type="text" name="txtLoginName" /></td>
													</tr>
													<tr>
														<td align="left" style="padding-top: 20px;"><span
															class="loginHeaderStyle">Password </span> <br>
															<div style="height: 6px;"></div></td>
													</tr>
													<tr>
														<td><input class="loginTextBoxStyle" type="password"
															name="txtPassword" /></td>
													</tr>
													<Tr>
														<td style="padding-top: 20px;">
															<button value="Login" class="btn btn-primary"
																style="width: 70px; height: 30px;">Login</button>
														</td>
													</Tr>
													<tr>
														<td>
															<div id="errorDiv2"></div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</form>
						
					
					<!-- <div style=" display: table-cell;padding: 50px 10px 10px 100px;width: 50%;vertical-align: top;" align="right"> -->

					


				</div>
				<!--	</div> -->
</div>
			</div>
		</div>
</div>
</div>
	</div>

</body>
</html>