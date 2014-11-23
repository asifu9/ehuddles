 <%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%@page import="com.pro.emp.Session_control"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}
String photoId = "";
String path="";

if(request.getParameter("photoId")!=null){
	photoId= request.getParameter("photoId");
}
if(request.getParameter("path")!=null){
	path= request.getParameter("path");
}
path = path.replaceFirst("b_","f_");
System.out.println(" 1111111 " + photoId + " : " + path);
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/PostCss.css" rel="stylesheet" type="text/css" media="all"></link>
<title>Insert title here</title>

<style type="text/css">
.newStyle{




width:100%;
height: 100%;
position:absolute;
top:0px;
}
</style>
  <script type="text/javascript" src="js/jquery4.1.min.js"></script>
<script type="text/javascript">
function shareMyPost(){
	
	var shareComment = document.getElementById("txtShareComments").value;
	var photoId = '<%=photoId%>';
	var userId = '<%=emp.getId()%>';
	
	$.getJSON("CreateNewPost", {'photoId' :photoId,'userId':userId,'postDesc':shareComment,'targetId':null,'privacy':0,'flow':3}, function(datas) { // Do an AJAX call
			// alert(" data s" + datas);
			 // $.each(datas, function(l,items){
				  parent.disablePopup();
			 });
	
}
	  
	  function closeWindow(){
		 // alert(" close");
		  parent.disablePopup();
		 
	  
	  }
</script>
</head>
<body class="newStyle" marginheight="0" marginwidth="0" >

<div class="rightalignClose"><a id="popupContactClose2" onclick="closeWindow()">x</a></div>
<div >
	<div >
		<table cellpadding="0" cellspacing="0" class="myShareDivStyle" width="100%" border="0">
			<tr>
				<td valign="top">
					<table border="0">
						<tr>
							<td valign="top">
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<% if(emp.getImagePath()==null || emp.getImagePath().trim().equalsIgnoreCase("")) {%>
												<img src="images/person.jpg" width="150" height="100" />
												<%}else{%>
												<img src="<%=PropertyReader.getValue("photoAlbumPath",request) %><%= emp.getImage().getPhotoPath().replaceFirst("a_", "g_")%>"  width="160"/>
												<%} %>
										</td>
										<td>
											<textarea rows="3" cols="40" id="txtShareComments"></textarea>
										</td>
									</tr>
									<tr>
										<td>
										</td>
										<td valign="bottom">
												<table align="right">
													<tr>
														<td>
															<input type="button" class="shareButton" value="Share" onclick="shareMyPost()">
														</td>
														
														<td style="padding-left: 10px;">
															<input type="button" class="shareButtonCancel" value="Cancel" onclick="closeWindow()">
														</td>
													</tr>
												</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			
				<td class="sharePopupVideo" width="100px" height="100px" align="center">
					<img src="<%=path %>">
				</td>
			</tr>
			
		
				
			
		</table>
		
	
	</div>

</div>
</body>
</html>