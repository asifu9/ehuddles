<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <link rel="icon" type="image/png" href="icon/browserIcon.png">
        <title>Sanah Employee Management System </title>
		<%
			EmpInfo emp=null;
		HashMap<String,Boolean> roleMap=new HashMap<String,Boolean>();
			if(Session_control.getSession(request)!=null){
				emp= Session_control.getSession(request);
				roleMap= EmployeeInfo.getRolesForEmpId(emp.getId());
			}
			else {
				response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
			}
			
			
			
		%>

 <style type="text/css">
.frameprofile
{
background-color:white;
position:absolute;
left:200px;
top:100px;
border: 1px ;
}

 </style>
 <link href="../css/mystyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all"></link>
   </head>

<body marginheight="0" marginwidth="0">

	<div align="center" id="header" >
	  <div style="background-color: #00688B;height: 60px;width: 100%;">
	  		<div style="height: 10px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  		</div>
	  </div>
	  <div style="width: 80%" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
			<tr>
				<!-- <td width="30%" style="padding-bottom: 0px; border-bottom-style: groove;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<img src="images/logo.jpg"/>
				</td> -->
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="Feed" ><span class="homeImage1">Home</span></a>
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
								onmouseover="this.className='headerLinkMO'"  href="logout.jsp" >Log out</a>
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
		<!-- <div style="width: 100%">
			<hr class="line">
		</div> -->
		
		<div align="left" style="width: 100%;padding-top: 5px; height: 605px;" >
			Hi &nbsp; <% if(emp!=null) {%>
    	<%= emp.getEmpName() %>
    <%} %>
    <br>
    <br>
		</div>
		
		
	

		<hr>
		<div align="left">
			<table align="left" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left"> <span style="font-size: 10px;color:gray;"> Copyright © Sanah Inc. 2008. All rights reserved.</span>
					</td>
					<td>
					</td>
				</tr>
			</table>
		</div>
          </div>
        </div>
          
 </body>
           
</html>