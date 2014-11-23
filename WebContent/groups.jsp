<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.AppConstants"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.Group"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}



 HashMap<String,Boolean> roleMap= EmployeeInfo.getRolesForEmpId(emp.getId()); 

	
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" type="image/png" href="icon/browserIcon.png">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/hrmsstyle.css" rel="stylesheet" type="text/css" media="all">
 <link href="css/styles.css" rel="stylesheet" media="screen"  />
 <link href="css/style.css" rel="stylesheet" media="screen" />

<script type="text/javascript" src="js/Jquery8ui.min.js"></script>
 <script type="text/javascript" src="js/Jquery8.js"></script>
  
<script type="text/javascript" src="js/jquery.slimscroll.js"></script>
	<script type="text/javascript" src="js/TGroupUtil.js" ></script>
	
	<script>

	</script>
</head>
<body>
<div align="center" id="header" style="margin-top: 0px;">
 <div style="background-color: #00688B;height: 60px;width: 100%;">
 			<div style="height: 20px;"></div>
	  		<div style="padding-left: 100px;vertical-align: baseline;" align="left">
	  		
	  			<span class="NewHeaderStyle"><%=AppConstants.APP_NAME %></span>
	  			
	  		</div>
	  </div>
	  
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0"  >
			<tr>
				<td style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<span class="headerLink" style="padding-left: 5px;"><%=emp.getEmpName() %></span>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;background-color: #33A1C9;">
					<table  cellpadding="0" cellspacing="0" align="right">
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
		<div align="left" style="padding-top: 0px;width:100%;">
		<div style="display: table-cell; vertical-align: top;width: 100px; border-right-color: balck;border-right-style: inset;border-right-width: 0px;padding-right: 10px;" >
			<table>
				<tr >
					<td width="200px">
						<table>
							<tr >
								<td style="padding-top: 20px">
									<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
										<img src="images/person.jpg" width="150px" height="100px">
										<%}else{ %>
										<img src="<%= emp.getImagePath()%>" width="160px" >
										<%} %>
									<%-- <img src="<%=(emp.getImagePath().trim()%>" width="200px" height="200px"> --%>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0">
					
										<% if(emp!=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) { %>
										<tr> 
											<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="editProfile.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader">
												<img class="iconStyleLeftHeadings" src="icon/edit_profile.jpg"/>Profile</a>
											</td>
										</tr>
										<%}%>
										<tr>
											<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="Feeds" class="leftLinkHeader">
												<span><img class="iconStyleLeftHeadings" src="icon/feeds.jpg"/></span>Feeds</a>
											</td>
										</tr>
										<%if(emp!=null && (roleMap.get(Constant.CALENDAR_EDIT)==null?false:true)) {%>
									
										<%} if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){ %>
										<tr> 
											<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="photo.jsp?editEmpId=<%=emp.getId()%>" class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/photos.jpg"/></span>Photo</a>
											</td>
										</tr>
										<% %>
										<%} 
										 if(emp!=null && (roleMap.get(Constant.DEP_ADMIN)==null?false:true)){ %>
										<tr> 
											<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="RoleManage.jsp" class="leftLinkHeader">Roles</a>
											</td>
										</tr><%}else{ 
										
										 if(emp!=null && (roleMap.get(Constant.ROLE_EDIT)==null?false:true)){ %>
										<tr> 
											<td class="headerGap1" onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="RoleManage.jsp" class="leftLinkHeader">Roles</a>
											</td>
										</tr>
										<%}} %>
										
										<tr>
											<td class="headerGap1"  onmouseover="this.className='headerLinkMOBlock1'" onmouseout="this.className='headerLinkBlock1'">
												<a href="colleagueList.jsp"  class="leftLinkHeader"><span><img class="iconStyleLeftHeadings" src="icon/colleagues.png"/></span>Colleagues</a>
											</td>
										</tr>
				</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</div>
		<div style="display: table-cell; vertical-align: top;width: 60%; border-right-color: balck;border-right-style: inset;border-right-width: 0px;padding-right: 10px;">
		
		<div align="left">
				<table align="right" width="100%">
					<tr>
						<td class="HeadingOfGroupList " >
							Groups Created by you
						</td>
						<td align="right" class="HeadingOfGroupList">
							<input type="button" id="createButton" class= "bttn" value="New Group" onclick="showCreatePopup()">
						</td>
					</tr>
				</table>
			</div>
			<div style="width: 100%;">
			<div style="display:table;width:100%;height: 450px;">
				<div style="display:table-cell;width:100%" align="left">
					<div id="insertGroups"></div>
				</div>
				<div style="display:table-cell;width:50%" >
				</div>
				
			</div>
		
		</div>
		
		</div>
		
		<!--  last column -->
		<div style="display: table-cell; vertical-align: top;width: 300px; border-right-color: balck;border-right-style: inset;border-right-width: 0px;" align="right">
			<div style="display:table;width:100%;" align="right">
				
				<div id="otherDisplaysection" style="display:table-cell;width:100%;height: 20px;" align="left">
					<div class="HeadingOfGroupList" style="height: 27px;"> 
						Groups created by others
					</div>
							
						
					<div id="insertOthersGroups"></div>
				</div>
				<div style="display:table-cell;" >
				</div>
				
			</div>
		</div>
		
		</div>
		
		
		
		
		<!-- Data after -->
		
		<div id="newGroup" class="newGroupCss" style="margin-top: 0px;">
			<div class="newGroupTextStyle" style="margin-top: 0px;">Create New Group</div>
			<div style="padding-top:20px;">
			<Br>
				<table class="tableStyleForGroup"  style="padding-top: 10px;">
					<tr>
						<td >
							Group Name
						</td>
						<td>
							<input type="hidden" id="hiddenUserId" value="<%=emp.getId() %>">
							<input type="text" name="groupName" id="groupName" >
						</td>
					</tr>
					<tr>
						<td>
							Description
						</td>
						<td>
							<input type="text" name="description" id="description" >
						</td>
					</tr>
				
				</table>
			</div>
			
			<div class="newGroupTextStyle2" style="margin-top: 0px;">
			<table style="padding-top: 10px;">
				<tr>
				
					<td>
						<input class="bttn" type="button" value="Cancel" onclick="hideCreateGroup()" >
					</td>
					<td>
						<input class="bttn" type="button" value="Create" onclick="createGroup()" >
					</td>
					
				</tr>
			</table>
			 </div>
		</div>
	
	
		
		
		
	</div>
</div>
<script type="text/javascript">
populateData();
</script>
</body>
</html>